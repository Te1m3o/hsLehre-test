package ui;

import common.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Dekan extends BaseTest {
  String password = "1234";
  String dekan ="eti.ulf.steinke@fh-stralsund.de";
  Anmeldung anmeldung = new Anmeldung();
  @Test
  public void Anmeldung(){
    anmeldung.anmelden(dekan, password);
    driver.findElement(By.xpath("/html/body/div[1]/h2")).isDisplayed();
  }
  @Test
  public void Abmeldung(){
    anmeldung.anmelden(dekan, password);
    driver.findElement(By.xpath("/html/body/div[1]/div[1]/a")).click();
    String expectedLabel = "Logout erfolgreich.";
    String actualLabel = driver.findElement(By.xpath("/html/body/div/div[2]/div/label")).getText();
    Assert.assertEquals(expectedLabel,actualLabel);
  }
  @Test
  public void Rollen_wechsel() {
    anmeldung.anmelden(dekan, password);
    // Wechseln zum Prof.
    try {
      Thread.sleep(2000);
    } catch (InterruptedException _e) {
      _e.printStackTrace();
    }
    driver.findElement(By.xpath("//*[@id=\"rolle\"]/select")).click();
    driver.findElement(By.xpath("//*[@id=\"rolle\"]/select/option[2]")).click();
    String actualHeader = driver.findElement(By.xpath("/html/body/div[1]/h2")).getText();
    String expectedHeader = "Übersicht: Eigene Lehrerhebungen";
    Assert.assertEquals(actualHeader,expectedHeader);
    // Wechseln zum Dekan
    driver.findElement(By.xpath("//*[@id=\"rolle\"]/select")).click();
    driver.findElement(By.xpath("//*[@id=\"rolle\"]/select/option[2]")).click();
    String actualHeader1 = driver.findElement(By.xpath("/html/body/div[1]/h2")).getText();
    String expectedHeader1 = "Übersicht: Lehrerhebungen der Professoren";
    Assert.assertEquals(actualHeader1, expectedHeader1);
  }
}
