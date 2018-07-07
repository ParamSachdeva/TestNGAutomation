package TestNG;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Sequencing {
	//public String filename = "E://Project//IIJT//TestNGMavenGitFramework//test-data//login.txt" ; 
	public String filename; 
	
	
	//@Test
	public void testCase2(){
		Reporter.log("This is the Test Case 2");
		
	}
	
	//@Parameters({ "browser" })
	//@Test
	public void testCase1(@Optional("chrome")String browser) {
		Reporter.log("This is the Test Case 1");
		Reporter.log("Browser is " + browser);
	}

	//@Test(dataProvider = "Authentication")
	public void testCase2(String username,String password) {
		Reporter.log("This is the Test Case Authentication");
		Reporter.log("User is " + username);
		Reporter.log("Password is " + password);
	}
	
	
	@Parameters({ "filepath" })
	@Test
	public void IntializeFilePath(String filepath) throws IOException {
		filename = filepath;
		//JOptionPane.showMessageDialog(null, filename);
	}
	
		
	@Test(dataProvider = "ParameterLogin")
	public void login(String key,String value) {
		String username = key.split(",")[0];
		String password = key.split(",")[1];
		//Reporter.log("This is login Test Case");
		//Reporter.log("Data set is " + key);
		Reporter.log("Username  is : " + username);
		Reporter.log("Password is : " + password);
	}
	
	
	/*
	@Test(dataProvider = "Login")
	public void login(String key,String value) {
		
		String username = key.split(",")[0];
		String password = key.split(",")[1];
		Reporter.log("This is login Test Case");
		//Reporter.log("Data set is " + key);
		Reporter.log("Username  is : " + username);
		Reporter.log("Password is : " + password);
	}
*/	
	@BeforeMethod
	public void beforeMethod() {
		System.out.println("This will execute before every Method");
	}

	@AfterMethod
	public void afterMethod() {
		System.out.println("This will execute after every Method");
	}

	@BeforeClass
	public void beforeClass() {
		System.out.println("This will execute before the Class");
	}

	@AfterClass
	public void afterClass() {
		System.out.println("This will execute after the Class");
	}

	@BeforeTest
	public void beforeTest() {
		System.out.println("This will execute before the Test");
	}

	@AfterTest
	public void afterTest() {
		System.out.println("This will execute after the Test");
	}

	@BeforeSuite
	public void beforeSuite() {
		System.out.println("This will execute before the Test Suite");
	}

	@AfterSuite
	public void afterSuite() {
		System.out.println("This will execute after the Test Suite");
	}


	@DataProvider(name = "StaticData")		 
	public static Object[][] credentials() {
		return new Object[][] { { "testuser_1", "Test@123" }};
	}

	@DataProvider(name="ParameterLogin")
	public Object[][] login() throws IOException {
		int numLines = 0;
		int currentLine = 0;
		String sended = "";
		File file = new File(filename);

		BufferedReader br = new BufferedReader(new FileReader(file));
		while ((br.readLine()) != null){
			numLines++;
		}
		br.close();

		String[][] testData = new String[numLines][2];
		BufferedReader br2 = new BufferedReader(new FileReader(file));
		while ((sended = br2.readLine()) != null){	        
			testData[currentLine][0] = sended;
			testData[currentLine][1] = "-----Please refer this data";
			currentLine++;
		}
		br2.close();
		return testData;
	}

}