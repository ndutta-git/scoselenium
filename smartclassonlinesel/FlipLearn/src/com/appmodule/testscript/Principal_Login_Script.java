package com.appmodule.testscript;

import java.util.HashMap;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.appmodule.Generic_method.Generic_class;
import com.appmodule.homepageclass.HomePage_PagecClass;
import com.appmodule.homepageclass.IndexHomePage_PageClass;
import com.appmodule.homepageclass.Loginpage_Pageclass;

public class Principal_Login_Script extends Generic_class{

	
		@SuppressWarnings("rawtypes")
		public static HashMap testData_HM =new HashMap();
		
		@SuppressWarnings("static-access")
		@BeforeTest
		
		public static void loginapp() throws Exception{
			TestDataPath="TestData/LoginData/url_excel.xlsx";
			TestDataSheetName="URL";
			Login_Role="Principal";
			ResultSheetPath="FlipLearn/Results/Result.xlsx";
			ResultFolder="C:/Users/ykuld_000/Desktop/smartclassonlinesel/FlipLearn/Results";
			
		    HomePage_PagecClass	loginpageobj=fn_OpenApp("CH", "http://www.fliplearn.com");
		    fn_FetchExcelData(1);
		    fn_GetPageLoadTime(NavigationObj.Fliplearnlogo);
		    verifyUrl("HomePage_School_URL",1);
		    Loginpage_Pageclass loginpageobj1=loginpageobj.clickonloginbutton();
		    fn_GetPageLoadTime(NavigationObj.Fliplearnlogo);
		    verifyUrl("Login_Page_URL",1);
		    loginpageobj1.Principal_login();
		    fn_GetPageLoadTime(IndexHomePage_PageClass.walllogo_onmain);
		    verifyUrl("IndexHomePage_Url",1);
		}
		
		@SuppressWarnings("static-access")
		@AfterTest
		public static void closedapp() throws Exception{
			NavigationObj.fn_logout();
			DriverObj.close();
			//DriverObj.quit();
		}
		
	}


