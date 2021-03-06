package com.appmodule.login;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;

import com.opencsv.CSVReader;


public class Server_User_Login_Test_CSV {
	
	public static WebDriver scoWebDriv;
	
	// Directory path to browse files
	//public static String scoUploadFilePath = "D:\\WWW\\Selenium-SCO\\run.environment\\test.upload.files\\";
	public static String scoUploadFilePath = "E:\\selenium.workingdir\\smartclassonline.testng\\test.upload.files\\";
	
	public static String scoWebURL = "http://www.smartclassonline.com/";
	
	//Login id CSV file path. Change the file name
	String csvUserData=scoUploadFilePath+"newusers.csv";
	
	@BeforeTest
	public static void openBrowser() throws Exception{
		
		scoWebDriv = new FirefoxDriver();
		scoWebDriv.manage().window().maximize();
			
	}
	
	@Test (description="Launch SCO web home and login page")
	public void TCSEL_L01() throws Exception {
		//Launch SCO web home page
		scoWebDriv.get(scoWebURL);
		Thread.sleep(3000);
				
		//Go to login page
		scoWebDriv.findElement(By.className("lgpnl")).click();
		Thread.sleep(3000);
	}
	
	@SuppressWarnings("resource")
	@Test (dependsOnMethods={"TCSEL_L01"}, description="Login with csv users")
	public void TCSEL_L02() throws IOException, Exception{
		//scoWebDriv.get(scoWebURL);
		//Thread.sleep(2000);
		CSVReader readUsers = new CSVReader(new FileReader(csvUserData));
		String [] csvCellData;
		while ((csvCellData = readUsers.readNext()) != null){
			//
			String csvUserName = csvCellData[0];
			String csvUserPaswd = csvCellData[1];
			//String csvSchoolName = csvCellData[2];
			//
			// input school user id and password
			scoWebDriv.findElement(By.id("LoginForm_username")).clear();
			scoWebDriv.findElement(By.id("LoginForm_username")).sendKeys(csvUserName);
			//System.out.println("User ID: " +scoGitSchoolAdminUserID);
			scoWebDriv.findElement(By.id("LoginForm_password")).clear();
			scoWebDriv.findElement(By.id("LoginForm_password")).sendKeys(csvUserPaswd);
			//System.out.println("Password: " +scoGitSchoolAdminPsswd);
			Thread.sleep(1000);
			scoWebDriv.findElement(By.cssSelector("button[type='submit']")).click();
			//System.out.println("PASS TCSEL_L03: User id and password accepted");
			Thread.sleep(2000);
			//System.out.println("Verifying login");
			//
			// test 1
			try{
				Thread.sleep(3000);
				scoWebDriv.findElement(By.cssSelector("#dynamicMessage > div.errorMessage")).isDisplayed();
				System.out.println("LOGIN ERROR. User: " +csvUserName);
				scoWebDriv.findElement(By.className("lgpnl")).click();
				Thread.sleep(2000);
			} catch (NoSuchElementException e){
				//System.out.println("OOPS... EXCEPTION...");
			}
			 			
			// test 2
			try{
				Thread.sleep(1000);
				if(scoWebDriv.findElement(By.className("breadcrumbs")).isDisplayed()|scoWebDriv.getTitle().equalsIgnoreCase("smartclassonline - Agreement User")){
					System.out.println("LOGIN SUCCESS. User: " +csvUserName);
					Thread.sleep(1000);
					WebDriverWait accMenuWait = new WebDriverWait(scoWebDriv, 2);
					accMenuWait.until(ExpectedConditions.presenceOfElementLocated(By.className("caret")));
					scoWebDriv.findElement(By.className("caret")).click();
					Thread.sleep(1000);
					WebDriverWait logoutWait = new WebDriverWait(scoWebDriv, 2);
					logoutWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[href='/index.php/site/logout']")));
					//scoWebDriv.findElement(By.cssSelector("a[href='/index.php/site/logout']")).click();
					try{
						scoWebDriv.findElement(By.cssSelector("a[href='/index.php/site/logout']")).click();
					} catch (NoSuchElementException e){
						scoWebDriv.get("http://www.smartclassonline.com/site/login");
						//System.out.println("OOPS... EXCEPTION...");
					}
				}
			} catch (NoSuchElementException e){
				//System.out.println("LOGIN FAILED.");
			}
			
			Thread.sleep(2000);
		}
		
	}

	@AfterTest
	public static void closeBrowser() throws Exception{
		Thread.sleep(3000);
		scoWebDriv.close();
		scoWebDriv.quit();
	}
	

}
