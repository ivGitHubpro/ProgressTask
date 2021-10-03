package issues.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LogInPage {

    @FindBy(how = How.XPATH, using = "//input[@id='login_field']")
    private WebElement usernameField;

    @FindBy(how = How.XPATH, using = "//input[@id='password']")
    private WebElement passwordField;

    @FindBy(how = How.XPATH, using = "//input[@type='submit']")
    private WebElement submmitButton;

    private final WebDriver driver;

    public LogInPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * LogiIn page by given password and username
     *
     * @param username which to be open
     * @param password which to be open
     */
    public MainPage logIn(String username, String password) {

        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        submmitButton.click();

        return new MainPage(driver);
    }
}
