package issues.pageobjects;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
    @FindBy(how = How.XPATH, using = "//input[@id='username']")
    private WebElement emailField;

    private static final int WEBDRIVER_WAIT_SECONDS_FOR_LOADING_ELEMENTS = 120;
    //private static final int SLEEP_TIME_FOR_ELEMENT_USEABILITY = 2000;
    private final WebDriver driver;
//    private final WebDriverWait wait;

    public MainPage(WebDriver driver) {
    	

        
        this.driver = driver;
        PageFactory.initElements(driver, this);
   //     wait = new WebDriverWait(driver, WEBDRIVER_WAIT_SECONDS_FOR_LOADING_ELEMENTS);
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
        //    WebDriverMethods.safeSleep(SLEEP_TIME_FOR_ELEMENT_USEABILITY);
        }
        
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public String getTitleText() {
    	return driver.getTitle();
    }

	
}
