
package com.mycompany.dbconstrainthandling;



import Jama.Matrix;
import ch.akuhn.matrix.Vector;
import ch.akuhn.matrix.eigenvalues.SingularValues;

public class ThinSingularValueDecomposition {
	/** The U matrix */
	public Matrix U;
	/** The singular values */
	public double[] S;
	/** The transpose of the V matrix */
	public Matrix Vt;

	/**
	 * Perform thin SVD on matrix, calculating at most ndims dimensions.
	 * 
	 * @param matrix
	 *            the matrix
	 * @param ndims
	 *            the number of singular values/vectors to calculate; actual
	 *            number may be less.
	 */
	public ThinSingularValueDecomposition(Matrix matrix, int ndims) {
		this(new JamaDenseMatrix(matrix), ndims);
	}

	/**
	 * Perform thin SVD on matrix, calculating at most ndims dimensions.
	 * 
	 * @param matrix
	 *            the matrix
	 * @param ndims
	 *            the number of singular values/vectors to calculate; actual
	 *            number may be less.
	 */
	public ThinSingularValueDecomposition(ch.akuhn.matrix.Matrix matrix, int ndims) {
		// FIXME: I'm (Jon) not sure why this was added, but it causes problems
		// with big matrices... commented it out for the time being
		// if (ndims > Math.min(matrix.rowCount(), matrix.columnCount())) {
		// try {
		// final no.uib.cipr.matrix.DenseMatrix mjtA = new
		// no.uib.cipr.matrix.DenseMatrix(matrix.asArray());
		// no.uib.cipr.matrix.SVD svd;
		// svd = no.uib.cipr.matrix.SVD.factorize(mjtA);
		// this.S = svd.getS();
		// this.U = MatrixUtils.convert(svd.getU());
		// this.Vt = MatrixUtils.convert(svd.getVt());
		//
		// this.S = Arrays.copyOf(this.S, Math.min(ndims, this.S.length));
		// this.U = U.getMatrix(0, U.getRowDimension() - 1, 0, Math.min(ndims,
		// U.getColumnDimension()) - 1);
		// this.Vt = Vt.getMatrix(0, Math.min(Vt.getRowDimension(), ndims) - 1,
		// 0, Vt.getColumnDimension() - 1);
		// } catch (final NotConvergedException e) {
		// throw new RuntimeException(e);
		// }
		// } else {
		final SingularValues sv = new SingularValues(matrix, ndims);

		// Note: SingularValues uses JARPACK which isn't currently
		// thread-safe :-(
		synchronized (ThinSingularValueDecomposition.class) {
			sv.decompose();
		}

		S = reverse(sv.value);
		U = vectorArrayToMatrix(sv.vectorLeft, false);
		Vt = vectorArrayToMatrix(sv.vectorRight, true);
		// }
	}

	protected double[] reverse(double[] vector) {
		for (int i = 0; i < vector.length / 2; i++) {
			final double tmp = vector[i];
			vector[i] = vector[vector.length - i - 1];
			vector[vector.length - i - 1] = tmp;
		}
		return vector;
	}

	protected Matrix vectorArrayToMatrix(Vector[] vectors, boolean rows) {
		final int m = vectors.length;

		final double[][] data = new double[m][];

		for (int i = 0; i < m; i++)
			data[m - i - 1] = vectors[i].unwrap();

		Matrix mat = new Matrix(data);

		if (!rows) {
			mat = mat.transpose();
		}
		return mat;
	}

	/**
	 * @return The S matrix
	 */
	public Matrix getSmatrix() {
		final Matrix Smat = new Matrix(S.length, S.length);

		for (int r = 0; r < S.length; r++)
			Smat.set(r, r, S[r]);

		return Smat;
	}

	/**
	 * @return The sqrt of the singular vals as a matrix.
	 */
	public Matrix getSmatrixSqrt() {
		final Matrix Smat = new Matrix(S.length, S.length);

		for (int r = 0; r < S.length; r++)
			Smat.set(r, r, Math.sqrt(S[r]));

		return Smat;
	}

	/**
	 * Reduce the rank of the input matrix using the thin SVD to get a lower
	 * rank least-squares estimate of the input.
	 * 
	 * @param m
	 *            matrix to reduce the rank of
	 * @param rank
	 *            the desired rank
	 * @return the rank-reduced matrix
	 */
	public static Matrix reduceRank(Matrix m, int rank) {
		if (rank > Math.min(m.getColumnDimension(), m.getRowDimension())) {
			return m;
		}

		final ThinSingularValueDecomposition t = new ThinSingularValueDecomposition(m, rank);
		return t.U.times(t.getSmatrix()).times(t.Vt);
	}
}
