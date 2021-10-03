package issues.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Page Object for Issue Detail Page
 */
public class IssueDetailsPage {

    @FindBy(how = How.XPATH, using = "//span[@class='js-issue-title markdown-title']")
    private WebElement newIssueTitle;

    @FindBy(how = How.XPATH, using = "//button[text()= 'Edit']")
    private WebElement editIssueButton;

    @FindBy(how = How.XPATH, using = "//a[@id='issues-tab']")
    private WebElement issuesTab;

    @FindBy(how = How.XPATH, using = "//input[@id='issue_title']")
    private WebElement issueTitleEdit;

    @FindBy(how = How.XPATH, using = "//div[@id='partial-discussion-header']//button[contains(text(),'Save')]")
    private WebElement saveButton;

    @FindBy(how = How.XPATH, using = "//div//span[contains(text(),'Close issue')]")
    private WebElement closeIssue;

    @FindBy(how = How.XPATH, using = "//div[@class='flex-shrink-0 mb-2 ']/span[@title = 'Status: Closed']")
    private WebElement closedStatusButton;

    private WebDriver driver;


    public IssueDetailsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String newIssueTitle() {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.elementToBeClickable(newIssueTitle));
        return newIssueTitle.getText();
    }

    public MainPage clickIssueTab() {
        issuesTab.click();
        return new MainPage(driver);
    }

    public void selectEditButton() {
        editIssueButton.click();
    }

    public void editIssueTitle(String editedIssueTitle) {
        issueTitleEdit.clear();
        issueTitleEdit.sendKeys(editedIssueTitle + " Updated");
        saveButton.click();
    }

    public void closeIssue() {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.elementToBeClickable(closeIssue));
        closeIssue.click();
    }

    public String closedStatusText() {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOf(closedStatusButton));
        return closedStatusButton.getText();
    }
}


