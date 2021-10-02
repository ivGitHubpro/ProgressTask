package frontend.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import issues.pageobjects.MainPage;
import junit.framework.Assert;
import testProject.issuesgithub.DriverDefinition;

public class IssuesFrontendTests extends DriverDefinition {
	
	private static final String ISSUES_TITLE = "Issues";
	private MainPage mainPage;
	
@BeforeMethod
public void setUp() {
	setup();
	mainPage = new MainPage(driver);
}
/**
 * Test if the correct page is opened
 */
@Test
public void verifyUserOnCorrectPage() {
	mainPage.openURL(URL);
	Assert.assertTrue(mainPage.getTitleText().contains(ISSUES_TITLE));

}

@AfterMethod
public void tearDown() {
	driver.quit();
}
}
