package Chapter1;

import java.util.function.BooleanSupplier;
public class test {

	public static void main(String[] args) { 
		if (((BooleanSupplier)(() -> { System.out.print("a"); return false; })).getAsBoolean()){
				System.out.println("a");
			}else{
				System.out.println("b");
			}
	}

}
