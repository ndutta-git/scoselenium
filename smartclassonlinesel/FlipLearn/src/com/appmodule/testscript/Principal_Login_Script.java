package com.appmodule.testscript;

import java.io.IOException;
import java.util.HashMap;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

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
			ResultFolder="E:/Git-Repository/scoselenium/smartclassonlinesel/FlipLearn/Results";
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
		@SuppressWarnings("static-access")
		@Test(priority=1)
		public static void Wall_link() throws IOException, Exception{
			NavigationObj.clickonwalllink();
			fn_GetPageLoadTime(IndexHomePage_PageClass.walllogo_onmain);
			verifyUrl("WallPage_Url", 1);
		}
		@SuppressWarnings("static-access")
		@Test(priority=2)
		public static void Message_link() throws IOException, Exception{
			NavigationObj.clickonmessage();
			fn_GetPageLoadTime(IndexHomePage_PageClass.message_headerlogo);
			verifyUrl("MessagePage_Url", 1);
		}
		@SuppressWarnings("static-access")
		@Test(priority=3)
		public static void gallery_link() throws IOException, Exception{
			NavigationObj.clickongallerylink();
			fn_GetPageLoadTime(IndexHomePage_PageClass.gallery_headerlogo);
			verifyUrl("GalleryPage_Url", 1);
		}
		@SuppressWarnings("static-access")
		@Test(priority=4)
		public static void Event_link() throws IOException, Exception{
			NavigationObj.clickonevent();
			fn_GetPageLoadTime(NavigationObj.Events);
			verifyUrl("EventPage_Url", 1);
		}
		@SuppressWarnings("static-access")
		@Test(priority=5)
		public static void Announcement_link() throws IOException, Exception{
			NavigationObj.clickonannouncement();
			fn_GetPageLoadTime(NavigationObj.Announcements);
			verifyUrl("AnnouncementPage_Url", 1);
		}
		@SuppressWarnings("static-access")
		@Test(priority=6)
		public static void Studdent_link() throws IOException, Exception{
			NavigationObj.clickonstudent();
			fn_GetPageLoadTime(NavigationObj.Students);
			verifyUrl("StudentPage_URL", 1);
		}
		@SuppressWarnings("static-access")
		@Test(priority=7)
		public static void Teacher_link() throws IOException, Exception{
			NavigationObj.clickonTeacher();
			fn_GetPageLoadTime(NavigationObj.Teachers);
			verifyUrl("TeacherPage_URL", 1);
		}
		@SuppressWarnings("static-access")
		@Test(priority=8)
		public static void Attendance_link() throws InvalidFormatException, IOException{
			NavigationObj.clickonattendance();
			fn_GetPageLoadTime(NavigationObj.mark_Attendance);
			verifyUrl("AttendanceMarks_URL", 1);
		}
	}


