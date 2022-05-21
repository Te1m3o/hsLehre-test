package ui;

import common.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FakultaetStellvertreter extends BaseTest {
  String password = "1234";
  String FV ="mb.erika.mustermann@fh-stralsund.de";
  Anmeldung anmeldung = new Anmeldung();
  @Test
  public void anmeldung(){
    anmeldung.anmelden(FV, password);
    driver.findElement(By.xpath("/html/body/div[1]/h2[1]")).isDisplayed();
  }
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
  @Test
  public void abmeldung(){
    anmeldung.anmelden(FV, password);
    driver.findElement(By.xpath("/html/body/div[1]/div[1]/a")).click();
    String expectedLabel = "Logout erfolgreich.";
    String actualLabel = driver.findElement(By.xpath("/html/body/div/div[2]/div/label")).getText();
    Assert.assertEquals(expectedLabel,actualLabel);
  }
}
