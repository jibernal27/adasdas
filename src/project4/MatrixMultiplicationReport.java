package project4;

import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;

public class MatrixMultiplicationReport {
	
	public static void main(String[] args) {
		
        System.out.println("Running tests!");

        JUnitCore engine = new JUnitCore();
        engine.addListener(new TextListener(System.out)); // required to print reports
        engine.run(MatrixTest.class);
		
        int[] cases= {4,16,512,1024};
		for (int i = 0; i < cases.length; i++) {
			int n = cases[i];
			Matrix A=new Matrix(n).random();
			Matrix B=new Matrix(n).random();
			System.out.println("N="+n +" A matrix");
			System.out.println(A);
			double ini=System.currentTimeMillis();
			double [][] productRegular=A.productRegular(B.data());
			double takenREg=(System.currentTimeMillis()-ini);
			ini=System.currentTimeMillis();
			double [][] productStrassen=A.productStrassen(B.data());
			double takenSt=(System.currentTimeMillis()-ini);
			
			System.out.println("Are	the produc matrices the same? "+ new Matrix(productStrassen).equalsM(productRegular));	
			System.out.println("Strassen product");
			System.out.println(new Matrix(productStrassen));
			System.out.printf("Regular took %.2f ms with %d multiplications \n", takenREg,A.getNumMultiplicationsReg());
			System.out.printf("Strassen took %.2f ms with %d multiplications \n", takenSt,A.getNumMultiplicationsStra());
		}

	
		
	}

}
