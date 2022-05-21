package ui;

import common.BaseTest;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class ProfessorAnmeldung extends BaseTest {
  String password = "1234";
  String professor ="eti.ulf.steinke@fh-stralsund.de";
  Anmeldung anmeldung = new Anmeldung();
  @Test
  public void professorAnmeldung(){
    anmeldung.anmelden(professor, password);
    driver.findElement(By.xpath("/html/body/div[1]/h2")).isDisplayed();
  }
}
