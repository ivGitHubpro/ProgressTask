package testProject.issuesgithub;

import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
public class DriverDefinition {

	public WebDriver driver;
	protected static final String URL = "https://github.com/progressim/progressrepo/issues";
	
    public void setup()  {
        WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }    
}
