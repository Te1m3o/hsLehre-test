package utilities;

import common.BaseTest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class TestUtils extends BaseTest {
  public String getScreenshot() throws IOException {
    Date currentDate = new Date();
    String screenshotFileName = currentDate.toString().replace(" ", "-").replace(":", "-");
    File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    String screenshotPath = ".//screenshot/" + screenshotFileName + ".png";
    FileUtils.copyFile(screenshotFile, new File(screenshotPath));
    return screenshotFileName;
  }
}
