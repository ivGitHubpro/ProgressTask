package testProject.issuesgithub;

import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Driver definition class
 */
public class DriverDefinition {

	public WebDriver driver;
    private final  DataLoader dataLoader = new DataLoader();
    protected static final String URL = "https://github.com/progressim/progressrepo/issues";
    protected String loginUserName = dataLoader.getUserName();
    protected String loginPassword = dataLoader.getPassword();


    public void setup()  {
        WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();

        }    
}
