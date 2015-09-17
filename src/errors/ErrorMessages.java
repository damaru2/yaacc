package errors;

public class ErrorMessages {
	public static void printErr(String s){
		System.err.println(s);
		System.exit(1);
	}
	public static void printWarning(String s){
		System.err.println(s);
		errors = true;
	}
	public static boolean getErrors() {
		return errors;
	}
	private static boolean errors;
}
