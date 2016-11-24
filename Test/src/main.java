
public class main {

	public static void main(String[] args) {
	
		/*
		int a = 1; // bzw. nextInt()
		int b = 0; // bzw. nextInt()
		int c = 0; // bzw. nextInt()
		int d = 0; // bzw. nextInt()
		int res = (a + b + c + d) & 1;
		System.out.println("Ergebnis ist: " + res);
		*/
		
		MathOperation multiplication = (a, b) -> { return a * b; };
		int result = operation(2,3);
		

	}
	
	interface MathOperation {
	      int operation(int a, int b);
	   }
	
	private static int operation(int a, int b){
		return a+b;
	}

}
