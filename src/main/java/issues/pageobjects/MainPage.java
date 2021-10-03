package issues.pageobjects;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
    @FindBy(how = How.XPATH, using = "//div/a[contains(text(),'Sign in')]")
    private WebElement signInButton;

    @FindBy(how = How.XPATH, using = "////a[@id='login_field']")
    private WebElement username;

    @FindBy(how = How.XPATH, using = "//a[@class='btn btn-primary']")
    private WebElement newIssue;

    @FindBy(how = How.XPATH, using = "(//a[contains(@id,'issue_')])[1]")
    private WebElement firstIssue;

    private WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Open page by given url, and maximize the window
     *
     * @param url which to be open
     */
    public void openURL(String url) {
        try {
            driver.get(url);
            driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
            driver.manage().window().maximize();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public LogInPage clickSignInButton() {
        signInButton.click();
        return new LogInPage(driver);
    }

    public String getTitleText() {
        return driver.getTitle();
    }

    public CreateIssuePage openNewIssue() {
        newIssue.click();
        return new CreateIssuePage(driver);
    }

    public boolean isIssueExisting() {
        return firstIssue.isDisplayed();
    }

    public String getFirstIssueText() {
        return firstIssue.getText();
    }

    public IssueDetailsPage selectFirstIssue() {
        firstIssue.click();
        return new IssueDetailsPage(driver);
    }
}
