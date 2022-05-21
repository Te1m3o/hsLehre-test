package ui;

import common.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FakultaetsSV extends BaseTest {
  String password = "1234";
  String FV ="mb.erika.mustermann@fh-stralsund.de";
  Anmeldung anmeldung = new Anmeldung();
   @Test
  public void rollen_zuweisen() {
     anmeldung.anmelden(FV, password);
     String person = "matthias.ahlhaus@fh-stralsund.de";
     String password = "1234";
     // unClick the clicked button
     driver.findElement(By.className("btn-success")).click();
     // click Dekan
     driver.findElement(By.xpath("//*[@id=\"accordion\"]/tr/td[4]/form/button")).click();
     // log out
     driver.findElement(By.xpath("/html/body/div[1]/div[1]/a")).click();
     // login as person
     Anmeldung anmeldung = new Anmeldung();
     anmeldung.anmelden(person,password);
     String expectedHeadLine = "Übersicht: Lehrerhebungen der Professoren";
     String actualHeadline = driver.findElement(By.xpath("/html/body/div[1]/h2")).getText();
     // Check if the person is Dekan
     Assert.assertEquals(expectedHeadLine,actualHeadline);
   }

}
