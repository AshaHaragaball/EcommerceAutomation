package io.Test.Telstra.TestProject;

import java.util.Arrays;
import org.testng.TestNG;

public class Test{
	
	public static void main(String[] args) {
		TestNG testng = new TestNG();
		testng.setTestSuites( Arrays.asList(new String[]{"./testng.xml"}));
		testng.setTestClasses(new Class[] { Interpreter.class });
		testng.run();
	}
}