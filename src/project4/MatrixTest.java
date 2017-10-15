package project4;
/*
   sample JUnit tests for checking and comparing the result of
   regular O(n^3) matrix multiplication algorithm with Strassen
   multiplication algorithm.	 
 */

import static org.junit.Assert.assertArrayEquals;
import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;


/**
 * The main JUnit Test class to test regular multiplication 
 * and Strassen multiplication method.
 * 
 *
 */
public class MatrixTest extends TestCase {

	private Matrix A, B; //input matrices
	private double[][] productRegularResult, productStrassenResult; // Matrices for storing the results
	private int N; // size of the NXN matrix
	@Before
	public void setUp() throws Exception
	{
	   N = 4; // size of the matrix
	   double[][] array1 = new double[N][N];
	   double[][] array2 = new double[N][N];
	   A = new Matrix(array1);
	   B = new Matrix(array2);

	   
	} // setUp()

	
	/* compare result matrices of regular multiplication method and Strassen multiplication method:
	 */
	@Test
	public void testProductCompare() {
	    
	     
	     //run user defined random() method to generate the matrices
	     A = A.random();
	     B = B.random();
	     
	      
	     // run productRegular()
	     productRegularResult = A.productRegular(B.data());
	     
	     // run productStrassen()
		 productStrassenResult = A.productStrassen(B.data());
		 
	     for (int i = 0; i < N; i++) {
	    	assertArrayEquals(productRegularResult[i], productStrassenResult[i], 0.0001 ); // data[][] is a data member for storing matrix values in class Matrix.
		}
		
	    
	}
	
	
	/* multiplying a 2D array using the regular method:
	 */
	@Test
	public void testProductRegular() {
	    
	    //expected output
		double[][] expected = {{96.0,94.0,81.0,128.0},{144.0,117.0,112.0,162.0},{132.0,112.0,101.0,152.0},{112.0,86.0,87.0,130.0}};
	    
		// input 2D arrain
		double[][] array1 = {{2.0,4.0,5.0,7.0},{6.0,7.0,2.0,8.0},{4.0,6.0,3.0,9.0},{8.0,4.0,1.0,5.0}};
		double[][] array2 = {{6.0,4.0,5.0,8.0},{8.0,7.0,8.0,8.0},{2.0,6.0,5.0,9.0},{6.0,4.0,2.0,5.0}}; 		
	    
		Matrix m1 = new Matrix(array1);
		Matrix m2 = new Matrix(array2);
	      
	    // run productRegular()
		double[][] result = m1.productRegular(array2);
	     
	    for (int i = 0; i < N; i++) {
			assertArrayEquals(expected[i],result[i], 0.0); // data[][] is a data member for storing matrix values in class Matrix.
		}
	    
	}
	
	/*
	 * The number of multiplication using Strassen should be less than the regular method
	 */
	@Test
	public void testNumMultiplication() {
		int[] pot= {2,4,8,16,32,64};
		for(int i=0;i<pot.length;i++)
		{
			Matrix A= new Matrix(pot[i]).random();
			Matrix B= new Matrix(pot[i]).random();
			
			double[][] multReg=A.productRegular(B.data());
			double[][] multSt=A.productStrassen(B.data());
			assertEquals(true,A.getNumMultiplicationsReg()>A.getNumMultiplicationsStra());
		}
	}
	
	
	/* multiplying a 2D array using the Strassen method:
	 */
	@Test
	public void testProductStrassen() {
	    
	    //expected output
		double[][] expected = {{96.0,94.0,81.0,128.0},{144.0,117.0,112.0,162.0},{132.0,112.0,101.0,152.0},{112.0,86.0,87.0,130.0}};
	    
		// input 2D array
		double[][] array1 = {{2.0,4.0,5.0,7.0},{6.0,7.0,2.0,8.0},{4.0,6.0,3.0,9.0},{8.0,4.0,1.0,5.0}};
		double[][] array2 = {{6.0,4.0,5.0,8.0},{8.0,7.0,8.0,8.0},{2.0,6.0,5.0,9.0},{6.0,4.0,2.0,5.0}}; 		
	    
		Matrix m1 = new Matrix(array1);
		Matrix m2 = new Matrix(array2);
	      
	    // run productRegular()
		double[][]  result= m1.productStrassen(array2);
	     
	    for (int i = 0; i < N; i++) {
			assertArrayEquals(expected[i],result[i], 0.0); // data[][] is a data member for storing matrix values in class Matrix.
		}
	    
	}
	
	
	
} // class MatrixTest