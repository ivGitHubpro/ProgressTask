package issues.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class CreateIssuePage {
    @FindBy(how = How.XPATH, using = "//input[@id='issue_title']")
    private WebElement issueTitle;

    @FindBy(how = How.XPATH, using = "//*[@id='issue_body']")
    private WebElement issueBody;

    @FindBy(how = How.XPATH, using = "//button[@class='btn-primary btn']")
    private WebElement subbmitIssue;

    private WebDriver driver;

    public CreateIssuePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public IssueDetailsPage createIssue(String titleIssueText, String issueBodyText) {
        issueTitle.sendKeys(titleIssueText);
        issueBody.click();
        issueBody.sendKeys(issueBodyText);
        subbmitIssue.click();

        return new IssueDetailsPage(driver);
    }
}
