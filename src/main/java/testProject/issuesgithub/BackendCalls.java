package testProject.issuesgithub;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jayway.jsonpath.JsonPath;

import jsonRequests.IssueParameters;
/**
 * Class that contains implementation of API calls for issues
 * @author ivanka.miovska
 *
 */
public class BackendCalls {
	private static final String URL = "https://api.github.com/repos/progressim/progressrepo/issues";
	private static final String TOKEN = "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";
	private static final String STATUS_CREATED = "201 Created";
	private static final String STATUS_OK = "200 OK";
	private static final int FIRST_INDEX_STATUS = 0;
	private static final int SECOND_INDEX_RESPONSE_BODY = 1;
	
	private RestClient restOperations = new RestClient();
	
	/** 
	 * API method for creation an issue
	 * @param title - title of the issue
	 * @param body - body of the issue
	 * @return the issue number in String
	 */
	public String createIssue(String title,String body) {
		
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        IssueParameters createIssue = new IssueParameters();
        createIssue.setTitle(title);
        createIssue.setBody(body);
		String createIssueBody = gson.toJson(createIssue);
		List<String> responseDetails = new ArrayList<String>(); 
		try {
			responseDetails = restOperations.sendPostRequest(URL, createIssueBody, TOKEN);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		checkStatusCorrectness(responseDetails,STATUS_CREATED);
		
		String responseBody = responseDetails.get(SECOND_INDEX_RESPONSE_BODY);
        Integer id = JsonPath.read(responseBody,"$.number");
        String numberId = Integer.toString(id);
		return numberId;
	}

	/**
	 * Gets issue by issue number
	 * @param numberId - number of the issue
	 * @return the title of the issue in String
	 */
	public String getIssueByIssueNumber(String numberId) {
		String responseBody = getResponseForIssue(numberId);

        String title = JsonPath.read(responseBody,"$.title");
		return title;
		
	}
	
	/**
	 * Gets issue by issue number
	 * @param numberId - number of the issue
	 * @return the locked status of an issue in String
	 */
	public boolean getLockStatusOfIssue(String numberId) {
		
		String responseBody = getResponseForIssue(numberId);
        boolean lockedStatus = JsonPath.read(responseBody,"$.locked");
		return lockedStatus;
		
	}
	
	/**
	 * Gets all existing issues
	 * @return list of titles of the issues in String
	 */
	public List<String> getAllIssues() {
		List<String> responseDetails = new ArrayList<String>();
		try {
			responseDetails = restOperations.sendGetRequest(URL, TOKEN);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		checkStatusCorrectness(responseDetails,STATUS_OK);

		String responseBody = responseDetails.get(SECOND_INDEX_RESPONSE_BODY);
        List<String> titles = JsonPath.read(responseBody,"$..title");

		return titles;
		
	}

	/**
	 * Locks an issue by numberId
	 * @param numberId - number Id of the issue
	 */
	public void lockIssue(String numberId) {
		try {
			restOperations.sendPutRequest(URL + "/" + numberId + "/lock", TOKEN);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Unlocks an issue by numberId
	 * @param numberId - number Id of the issue
	 */
	public void unlockIssue(String numberId) {
		try {
			restOperations.sendDeleteRequest(URL + "/" + numberId + "/lock", TOKEN);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Change the status to closed or open of an issue
	 * @param numberId - number ID of the issue
	 * @param state - state of an issue (closed or open)
	 * @return - state after change
	 */
	public String changeStateOfIssue(String numberId, String state) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        IssueParameters editIssue = new IssueParameters();
        editIssue.setState(state);
		String issueBody = gson.toJson(editIssue);
		List<String> responseDetails = new ArrayList<String>(); 
		try {
			responseDetails = restOperations.sendPatchRequest(URL + "/" + numberId, issueBody, TOKEN);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		checkStatusCorrectness(responseDetails,STATUS_OK);
		
		String responseBody = responseDetails.get(SECOND_INDEX_RESPONSE_BODY);
        String stateValue = JsonPath.read(responseBody,"$.state");
        
		return stateValue;
	}
	
	/**
	 * Verifies that the response corresponds to expected return call status
	 * @param responseDetails - response returned from the API call
	 * @param responseStatus - expected status for verification
	 */
	private void checkStatusCorrectness(List<String> responseDetails, String responseStatus) {
		if (!responseDetails.get(FIRST_INDEX_STATUS).equals(responseStatus)) {
			Assert.fail("Request failed with status: " + responseDetails.get(FIRST_INDEX_STATUS));
		}	
	}
	
	/**
	 * 
	 * @param numberId - number id of the issue
	 * @return - the response body from get issue request
	 */
	private String getResponseForIssue(String numberId) {
		List<String> responseDetails = new ArrayList<String>();
		try {
			responseDetails = restOperations.sendGetRequest(URL + "/" + numberId, TOKEN);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		checkStatusCorrectness(responseDetails,STATUS_OK);
		
		String responseBody = responseDetails.get(SECOND_INDEX_RESPONSE_BODY);
		return responseBody;
	}


}
