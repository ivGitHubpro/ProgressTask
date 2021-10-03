package frontend.tests;

import issues.pageobjects.CreateIssuePage;
import issues.pageobjects.IssueDetailsPage;
import issues.pageobjects.LogInPage;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import issues.pageobjects.MainPage;
import junit.framework.Assert;
import testProject.issuesgithub.DriverDefinition;

/**
 * Test Frontend class
 */
public class IssuesFrontendTests extends DriverDefinition {

    private static final String ISSUES_TITLE = "Issues";
    private static final String ISSUE_BODY = "Automation content";
    private static final String STATUS_CLOSED = "Closed";

    private MainPage mainPage;
    private LogInPage loginPage;
    private CreateIssuePage createIssuePage;
    private IssueDetailsPage issueDetailsPage;

    /**
     * setup Driver
     */
    @BeforeMethod
    public void setUp() {
        setup();
        mainPage = new MainPage(driver);
        mainPage.openURL(URL);
        loginPage = mainPage.clickSignInButton();
        loginPage.logIn(loginUserName, loginPassword);
    }

    /**
     * Test if the correct page is opened
     */
    @Test
    public void verifyUserOnCorrectPage() {
        Assert.assertTrue(mainPage.getTitleText().contains(ISSUES_TITLE));
    }

    /**
     * * Test if the issue is created
     */
    @Test
    public void CreateNewIssue() {
        createIssuePage = mainPage.openNewIssue();
        String titleIssue = "Automation title " + RandomStringUtils.randomNumeric(5);
        issueDetailsPage = createIssuePage.createIssue(titleIssue, ISSUE_BODY);
        Assert.assertTrue(issueDetailsPage.newIssueTitle().equals(titleIssue));
        mainPage = issueDetailsPage.clickIssueTab();
        Assert.assertEquals(mainPage.getFirstIssueText(), titleIssue, "Expected text not correct!");

    }

    /**
     * Test if the issue is updated
     */
    @Test
    public void UpdateIssue() {

        if (!mainPage.isIssueExisting()) {
            CreateNewIssue();
        }
        issueDetailsPage = mainPage.selectFirstIssue();
        String issueTitleBeforeUpdate = issueDetailsPage.newIssueTitle();
        issueDetailsPage.selectEditButton();
        issueDetailsPage.editIssueTitle(issueTitleBeforeUpdate);
        String issueTitleAfterUpdate = issueDetailsPage.newIssueTitle();
        Assert.assertTrue("Not Correct! ", !issueTitleAfterUpdate.equals(issueTitleBeforeUpdate));
    }

    /**
     * Test if the issue is closed
     */
    @Test
    public void CloseIssue() {
        issueDetailsPage = mainPage.selectFirstIssue();
        issueDetailsPage.closeIssue();
        Assert.assertTrue(issueDetailsPage.closedStatusText().equals(STATUS_CLOSED));
    }

    /**
     * kill driver
     */
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
