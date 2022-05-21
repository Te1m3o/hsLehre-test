package ui;

import common.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Professor extends BaseTest {
  String password = "1234";
  String professor ="eti.ulf.steinke@fh-stralsund.de";
  Anmeldung anmeldung = new Anmeldung();
  @Test
  public void anmeldung(){
    anmeldung.anmelden(professor, password);
    driver.findElement(By.xpath("/html/body/div[1]/h2")).isDisplayed();
  }
  @Test
  public void abmeldung(){
    anmeldung.anmelden(professor, password);
    driver.findElement(By.xpath("/html/body/div[1]/div[1]/a")).click();
    String expectedLabel = "Logout erfolgreich.";
    String actualLabel = driver.findElement(By.xpath("/html/body/div/div[2]/div/label")).getText();
    Assert.assertEquals(expectedLabel,actualLabel);
  }
}
