package ui;

import common.BaseTest;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class Dekan extends BaseTest {
  String password = "1234";
  String dekan ="eti.ulf.steinke@fh-stralsund.de";
  Anmeldung anmeldung = new Anmeldung();
  @Test
  public void anmeldung(){
    anmeldung.anmelden(dekan, password);
    driver.findElement(By.xpath("/html/body/div[1]/h2")).isDisplayed();
  }
}
