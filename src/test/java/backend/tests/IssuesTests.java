package backend.tests;


import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import testProject.issuesgithub.BackendCalls;

public class IssuesTests {
	
	private static final String ISSUE_TITLE = "Test Issue Title " + RandomStringUtils.randomNumeric(5);
	private static final String ISSUE_BODY = "Test Issue Body";
	private static final String STATE_CLOSED = "closed";
	private BackendCalls backendCalls;
	String createdIssueId;
	
	
@BeforeClass
public void setUp() {
	backendCalls = new BackendCalls();
    createdIssueId = backendCalls.createIssue(ISSUE_TITLE, ISSUE_BODY);
}

@Test
public void getIssueByIssueNumber() {

	String titleOfIssue = backendCalls.getIssueByIssueNumber(createdIssueId);
	Assert.assertEquals(titleOfIssue, ISSUE_TITLE,"The returned ticket is not the expected one.");

}


@Test
public void getAllIssues() {

	String titleOfExistingIssue = backendCalls.getIssueByIssueNumber(createdIssueId);
	List<String> titlesOfIssues = backendCalls.getAllIssues();
	Assert.assertTrue(titlesOfIssues.contains(titleOfExistingIssue), "Not all tickets are in the response");

}

@Test
public void lockAndUnlockIssue() {

     backendCalls.lockIssue(createdIssueId);
     Assert.assertTrue(backendCalls.getLockStatusOfIssue(createdIssueId),"Issue is not locked. ");
     backendCalls.unlockIssue(createdIssueId);
     Assert.assertFalse(backendCalls.getLockStatusOfIssue(createdIssueId),"Issue is locked. ");

}

@Test(priority=1)
public void closeIssue() {
	Assert.assertEquals(backendCalls.changeStateOfIssue(createdIssueId, STATE_CLOSED),STATE_CLOSED,"Issue was not closed. ") ;
}
}
