package project4;
import java.util.Random;

public class Matrix {
	private double[][] matrix;
	private int numMultReg=0,numMultSt=0;

	public Matrix(double[][] matrix) {
		this.matrix = matrix;
	}
	public Matrix(int size) {
		this.matrix = new double [size][size];
	}

	/**
	 * Compute matrix multiplication using regular method O(n3) solution. This
	 * method should return product matrix.
	 *
	 * @param matrixB
	 * @return
	 */
	public double[][] productRegular(double matrixB[][]) {
		
		// Implement your algorithm here.
		double [][] AB=new double[matrix.length][matrixB[0].length];
		
		for (int i=0;i< matrix.length;i++)
		{
			for(int j=0;j<matrixB[0].length;j++)
			{
				for(int k=0;k<matrix[0].length;k++)
				{
					AB[i][j]+=matrix[i][k]*matrixB[k][j];
					numMultReg++;
				}
			}
		}
		return AB;
		
	}

	/**
	 * Compute matrix multiplication using Strassen's method. This method should
	 * return product matrix.
	 *
	 * @param matrixB
	 * @return
	 */
	public double[][] productStrassen(double matrixB[][]) {
		// Implement your algorithm here.
		return productStrassen(matrix,matrixB);
	}
	
	/**
	 * Auxiliar product Strassen that takes two two dimensional arrays. 
	 * @param A First matrix
	 * @param B Second MAtrix
	 * @return AB
	 */
	private double[][] productStrassen(double A[][],double B[][])
	{
		double [][] AB=new double[A.length][B[0].length];
		int size=A.length;
		
		if(size==1)
		{
			AB[0][0]=A[0][0]*B[0][0];
			numMultSt++;
		}
		else
		{
			//Dividing matrixes 
			double[][] A11=divide(A,size/2,0,0);
			double[][] A12=divide(A,size/2,0,size/2);
			double[][] A21=divide(A,size/2,size/2,0);
			double[][] A22=divide(A,size/2,size/2,size/2);

			double[][] B11=divide(B,size/2,0,0);
			double[][] B12=divide(B,size/2,0,size/2);
			double[][] B21=divide(B,size/2,size/2,0);
			double[][] B22=divide(B,size/2,size/2,size/2);
			
			//Using Strassen relations
			
			double[][] M1=productStrassen(add(A11,A22),add(B11,B22));
			double[][] M2=productStrassen(add(A21,A22),B11);
			double[][] M3=productStrassen(A11,sub(B12,B22));
			double[][] M4=productStrassen(A22,sub(B21,B11));
			double[][] M5=productStrassen(add(A11,A12),B22);
			double[][] M6=productStrassen(sub(A21,A11),add(B11,B12));
			double[][] M7=productStrassen(sub(A12,A22),add(B21,B22));
			
			//MAtrix AB parts
			
			double[][] C11=add(sub(add(M1,M4),M5),M7);
			double[][] C12=add(M3,M5);
			double[][] C21=add(M2,M4);
			double[][] C22=add(add(sub(M1,M2),M3),M6);
			
			joinSUbs(AB,C11,0,0);
			joinSUbs(AB,C12,0,size/2);
			joinSUbs(AB,C21,size/2,0);
			joinSUbs(AB,C22,size/2,size/2);
			
		}
		return AB;
		
	}
	
	
	//Auxiliar methods for testing
	/**
	 * Fills with random doubles the matrix
	 * @return
	 */
	public Matrix random()
	{
		for(int i=0;i<matrix.length;i++)
		{
			for(int j=0;j<matrix[0].length;j++)
			{
				Random r = new Random();
				matrix[i][j] = 0 + (10 - 0) * r.nextDouble();
			}
		}
		return this;
		
	}
	/**
	 * Returns the two dimensional array
	 * @return The matrix data
	 */
	public double[][] data()
	{
		return matrix;
	}
	/**
	 * Restarts the counts of the number of multiplications of the regular and strassen multiplications
	 */
	public void restartCounts()
	{
		numMultReg=0;
		numMultSt=0;
	}
	/**
	 * Gives the number of multiplications performed in the regular multiplication
	 * @return
	 */
	public int getNumMultiplicationsReg()
	{
		return numMultReg;
	}
	/**
	 * Gives the number of multiplications performed in the Strassen multiplication
	 * @return
	 */
	public int getNumMultiplicationsStra()
	{
		return numMultSt;
	}
	//Auxiliar functions for Strassen
	
	/**
	 * Replaces a part of the bigger matrix with one smaller
	 * @param matrix The big matrix
	 * @param part The small matrix
	 * @param ini The start i position to replace
	 * @param end The start j position to replace
	 */
	private void joinSUbs(double matrix[][],double part[][] , int ini,int end)
	{
		for (int i=0;i<part.length;i++)
		{
			for(int j=0;j<part[0].length;j++)
			{
				matrix[i+ini][j+end]=part[i][j];
			}
		}
	}
	
	/**
	 * Divides a big matrix
	 * @param matrix The matrix to divide
	 * @param size The size of the divide
	 * @param init The initial i position
	 * @param end The initial j position
	 * @return
	 */
	private double[][] divide(double matrix[][],int size,int init,int end)
	{
		double[][] divided= new double[size][size];
		for(int i=0;i<size;i++)
		{
			for(int j=0;j<size;j++)
			{
				divided[i][j]=matrix[i+init][j+end];
			}
		}
		return divided;
		
	}
	
	/**
	 * Adds two matrixes
	 * @param A Matrix A
	 * @param B Matrix B
	 * @return The sum of the two matrixes
	 */
	private double[][] add(double A[][], double B[][])
	{
		double [][] sum= new double[A.length][A[0].length];
		for(int i=0;i<A.length;i++)
		{
			for(int j=0;j<A[0].length;j++)
			{
				sum[i][j]=A[i][j]+B[i][j];
			}
		}
		return sum;
	}
	/**
	 * Subtracts two matrixes
	 * @param A Matrix A
	 * @param B Matrix B
	 * @return The subtraction of the two matrixes
	 */	
	private double[][] sub(double A[][], double B[][])
	{
		double [][] subs= new double[A.length][A[0].length];
		for(int i=0;i<A.length;i++)
		{
			for(int j=0;j<A[0].length;j++)
			{
				subs[i][j]=A[i][j]-B[i][j];
			}
		}
		return subs;
	}
	
	public boolean equalsM(double A[][] ) {
		
		for(int i=0;i<A.length;i++)
		{
			for(int j=0;j<A[0].length;j++)
			{
				if (Math.abs(A[i][j]-matrix[i][j])>0.001)
				{
					return false;
				}
			}
		}
		return true;
	}
	
	@Override
	public String toString() {
		String rta = "";
		for(int i=0;i<matrix.length;i++)
		{
			rta +="|\t";
			for(int j=0;j<matrix[0].length;j++)
			{
				rta +=String.format( "%.2f", matrix[i][j] ) + "\t";
			}
			rta+="|\n";
		}
		return rta;
		
	}
}