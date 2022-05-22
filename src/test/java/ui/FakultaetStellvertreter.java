package ui;

import common.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FakultaetStellvertreter extends BaseTest {
  String password = "1234";
  String FV ="mb.erika.mustermann@fh-stralsund.de";
  String professor = "matthias.ahlhaus@fh-stralsund.de";
  Anmeldung anmeldung = new Anmeldung();
  @Test
  public void Anmeldung(){
    anmeldung.anmelden(FV, password);
    driver.findElement(By.xpath("/html/body/div[1]/h2[1]")).isDisplayed();
  }
  @Test
  public void Rollen_zuweisen() {
    anmeldung.anmelden(FV, password);
    String person = "matthias.ahlhaus@fh-stralsund.de";
    String password = "1234";
    // unClick the clicked button
    driver.findElement(By.className("btn-success")).click();
    // click Dekan
    driver.findElement(By.xpath("//*[@id=\"accordion\"]/tr/td[4]/form/button")).click();
    // log out
    driver.findElement(By.xpath("/html/body/div[1]/div[1]/a")).click();
    // login as a person
    Anmeldung anmeldung = new Anmeldung();
    anmeldung.anmelden(person,password);
    String expectedHeadLine = "Übersicht: Lehrerhebungen der Professoren";
    String actualHeadline = driver.findElement(By.xpath("/html/body/div[1]/h2")).getText();
    // Check if the person is a Dekan
    Assert.assertEquals(expectedHeadLine,actualHeadline);
    // log out from the Dekan
    driver.findElement(By.xpath("/html/body/div[1]/div[1]/a")).click();
    // log in as FV
    anmeldung.anmelden(FV, password);
    // unClick the clicked button
    driver.findElement(By.className("btn-success")).click();
    // click professor
    driver.findElement(By.xpath("//*[@id=\"accordion\"]/tr/td[3]/form/button")).click();
    // logout from the Dekan
    driver.findElement(By.xpath("/html/body/div[1]/div[1]/a")).click();
    // log in as the professor
    anmeldung.anmelden(professor, password);
    String expectedSpan = "Lehrerhebung SS 2022 erstellen";
    String actualSpan = driver.findElement(By.xpath("/html/body/div[1]/a/span")).getText();
    // Check if the person is professor
    Assert.assertEquals(actualSpan,expectedSpan);
    // log out from the Prof.
    driver.findElement(By.xpath("/html/body/div[1]/div[1]/a")).click();
    // log in as FV
    anmeldung.anmelden(FV, password);
    // unClick the clicked button
    driver.findElement(By.className("btn-success")).click();
    // Click the FV
    driver.findElement(By.xpath("//*[@id=\"accordion\"]/tr/td[6]/form/button")).click();
    // log out from the FV.
    driver.findElement(By.xpath("/html/body/div[1]/div[1]/a")).click();

    anmeldung.anmelden(person,password);
    // Check if the person is FV
    String expectedHeadLine1 = "Übersicht: Mitarbeiter";
    String actualHeadline1 = driver.findElement(By.xpath("/html/body/div[1]/h2[1]")).getText();
    Assert.assertEquals(expectedHeadLine1,actualHeadline1);
  }
  @Test
  public void Rollen_wechsel(){
    anmeldung.anmelden(FV, password);
    // Wechseln zum Prof.
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
    // Wechseln zum FV
    driver.findElement(By.xpath("//*[@id=\"rolle\"]/select")).click();
    driver.findElement(By.xpath("//*[@id=\"rolle\"]/select/option[4]")).click();
    String actualHeader2 = driver.findElement(By.xpath("/html/body/div[1]/h2[1]")).getText();
    String expectedHeader2 = "Übersicht: Mitarbeiter";
    Assert.assertEquals(actualHeader2, expectedHeader2);
  }
  @Test
  public void neuen_Studiengang_hinterlegen() {
    anmeldung.anmelden(FV,password);
    JavascriptExecutor js = ((JavascriptExecutor) driver);
    js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    // Neuen Studiengang hinzufügen klicken
    driver.findElement(By.xpath("/html/body/div[1]/button")).click();
    driver.findElement(By.xpath("//*[@id=\"bezeichnungStudiengang\"]")).sendKeys("Test Bezeichnung");
    driver.findElement(By.xpath("//*[@id=\"kurzStudiengang\"]")).sendKeys("Test Kürzel");
    // Choose file ist unklar: welche csv Datei soll hochgeladen werden?

    // click importieren
    driver.findElement(By.xpath("//*[@id=\"studiengangHinzufügenCollapse\"]/form/table/tbody/tr/td[6]/input")).click();
    String actualTdElement = driver.findElement(By.xpath("//*[@id=\"accordion\"]/tr/td[2]")).getText();
    String expectedTdElement = "Test Bezeichnung";
    Assert.assertEquals(actualTdElement,expectedTdElement);
  }
  @Test
  public void Abmeldung(){
    anmeldung.anmelden(FV, password);
    driver.findElement(By.xpath("/html/body/div[1]/div[1]/a")).click();
    String expectedLabel = "Logout erfolgreich.";
    String actualLabel = driver.findElement(By.xpath("/html/body/div/div[2]/div/label")).getText();
    Assert.assertEquals(expectedLabel,actualLabel);
  }
}
