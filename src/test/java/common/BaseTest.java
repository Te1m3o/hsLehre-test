package common;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class BaseTest {
  public static WebDriver driver = null;
  @BeforeMethod
  @Parameters({"browser"})
  public void launchBrowser(String browser) {
    if (browser.equals("firefox")) {
      WebDriverManager.firefoxdriver().setup();
      driver = new FirefoxDriver();
    }else if (browser.equals("chrome")){
      WebDriverManager.chromedriver().setup();
      driver = new ChromeDriver();
    }else if (browser.equals("edge")){
      WebDriverManager.edgedriver().setup();
      driver = new EdgeDriver();
    }
  }
  @AfterTest
  public void closingBrowser() {
    driver.close();
  }
}
