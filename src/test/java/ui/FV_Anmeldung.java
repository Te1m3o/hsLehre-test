package ui;

import common.BaseTest;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class FV_Anmeldung extends BaseTest {
  String password = "1234";
  String FV ="mb.erika.mustermann@fh-stralsund.de";
  Anmeldung anmeldung = new Anmeldung();
  @Test
  public void FV_Anmeldung(){
    anmeldung.anmelden(FV, password);
    driver.findElement(By.xpath("/html/body/div[1]/h2[1]")).isDisplayed();
  }
}
