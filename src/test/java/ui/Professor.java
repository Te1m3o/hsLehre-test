package ui;

import common.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Professor extends BaseTest {
  String password = "1234";
  String professor ="eti.ulf.steinke@fh-stralsund.de";
  String professorLehrerhebung = "eti.berthold.heisterkamp@fh-stralsund.de";
  Anmeldung anmeldung = new Anmeldung();
  @Test
  public void Anmeldung(){
    anmeldung.anmelden(professor, password);
    driver.findElement(By.xpath("/html/body/div[1]/h2")).isDisplayed();
  }
  @Test
  public void Abmeldung(){
    anmeldung.anmelden(professor, password);
    driver.findElement(By.xpath("/html/body/div[1]/div[1]/a")).click();
    String expectedLabel = "Logout erfolgreich.";
    String actualLabel = driver.findElement(By.xpath("/html/body/div/div[2]/div/label")).getText();
    Assert.assertEquals(expectedLabel,actualLabel);
  }
  @Test
  public void erstellenLehrerhebung() {
    anmeldung.anmelden(professorLehrerhebung, password);

    driver.findElement(By.xpath("/html/body/div[1]/h2")).isDisplayed();
//        neue Lehrerhebung erstellen
    driver.findElement(By.xpath("/html/body/div[1]/a/span")).click();
    driver.findElement(By.xpath("//button[@name=\'addRowErmaessigungAufgabe\']")).click();
    // 10 | click | xpath=//input[@id='ermaessigungAufgabeGrund'] |
//        driver.findElement(By.xpath("//input[@id=\'ermaessigungAufgabeGrund\']")).click();
    // 11 | type | xpath=//input[@id='ermaessigungAufgabeGrund'] | Dokumentation
    driver.findElement(By.xpath("//input[@id=\'ermaessigungAufgabeGrund\']")).sendKeys("Dokumentation");
    // 12 | click | xpath=//input[@id='ermaessigungAufgabeSWSAnzahl'] |
//        driver.findElement(By.xpath("//input[@id=\'ermaessigungAufgabeSWSAnzahl\']")).click();
    // 13 | type | xpath=//input[@id='ermaessigungAufgabeSWSAnzahl'] | 4
    driver.findElement(By.xpath("//input[@id=\'ermaessigungAufgabeSWSAnzahl\']")).sendKeys("4");
    // 14 | click | xpath=//input[@id='ermaessigungBehinderung'] |
    driver.findElement(By.xpath("//input[@id=\'ermaessigungBehinderung\']")).click();
    // 15 | type | xpath=//input[@id='ermaessigungBehinderung'] | 4
    driver.findElement(By.xpath("//input[@id=\'ermaessigungBehinderung\']")).sendKeys(Keys.BACK_SPACE);
    driver.findElement(By.xpath("//input[@id=\'ermaessigungBehinderung\']")).sendKeys("4");

    {
      WebElement element = driver.findElement(By.xpath("/html/body/div[1]/form/div[5]/h3/span"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).perform();
    }

    driver.findElement(By.xpath("/html/body/div[1]/form/div[4]/table/tfoot/tr/td[9]")).click();

    driver.findElement(By.id("lehrveranstaltungsname[0][0]")).sendKeys("Mathematik I");

    driver.findElement(By.id("lehrtaetigkeitStudierendenAnzahl")).sendKeys("16");

    driver.findElement(By.id("lehrtaetigkeitSwsAnzahl")).sendKeys("2");

    driver.findElement(By.id("lehrtaetigkeitSwsAnzahl")).click();

    {
      WebElement element = driver.findElement(By.xpath("/html/body/div[1]/form/div[6]/h3"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).perform();
    }

    driver.findElement(By.xpath("/html/body/div[1]/form/div[5]/table/tfoot/tr/td[9]/button")).click();
    // 12 | click | id=lehrfakultaetAusserhalbPO[1][0] |
//        driver.findElement(By.id("lehrfakultaetAusserhalbPO[1][0]")).click();
//        // 13 | select | id=lehrfakultaetAusserhalbPO[1][0] | label=MB
//        {
//            WebElement dropdown = driver.findElement(By.id("lehrfakultaetAusserhalbPO[1][0]"));
//            dropdown.findElement(By.xpath("//option[. = 'MB']")).click();
//        }
    driver.findElement(By.id("lehrfakultaetAusserhalbPO[1][0]")).sendKeys("MB");

    driver.findElement(By.id("lehrveranstaltungsname[1][0]")).sendKeys("Mathematik II");

    driver.findElement(By.name("lehrtaetigkeitList[1].teilnehmerAnzahl")).sendKeys("16");

    driver.findElement(By.name("lehrtaetigkeitList[1].swsAnzahl")).sendKeys("2");


    {
      WebElement element = driver.findElement(By.xpath("/html/body/div[1]/form/div[7]/h3"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).perform();
    }

    driver.findElement(By.name("addRowBlockveranstaltung")).click();

    driver.findElement(By.id("blockveranstaltungsname[0][0]")).sendKeys("Mathematik I");

    driver.findElement(By.id("blockveranstaltungDatumStart[0]")).sendKeys("22.05.2022");

    driver.findElement(By.id("blockveranstaltungDatumEnde[0]")).sendKeys("23.05.2022");

    driver.findElement(By.id("blockveranstaltungStudierendenAnzahl")).sendKeys("16");

    driver.findElement(By.id("blockveranstaltungStundenAnzahl")).sendKeys("2");

    {
      WebElement element = driver.findElement(By.xpath("/html/body/div[1]/form/div[8]/h3"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).perform();
    }

    driver.findElement(By.name("addRowArbeit")).click();
    // 10 | click | id=arbeit-titel |
    // 11 | type | id=arbeit-titel | Was ich noch machen wollte
    driver.findElement(By.xpath("//*[@id=\"arbeit-titel\"]")).sendKeys("Was ich noch machen wollte");
    // 12 | click | id=arbeitAutor |
    // 13 | type | id=arbeitAutor | Erni
    driver.findElement(By.id("arbeitAutor")).sendKeys("Erni");
    // 14 | click | id=arbeitAnmeldedatum[0] |
    driver.findElement(By.id("arbeitAnmeldedatum[0]")).sendKeys("22.10.2222");
    {
      WebElement element = driver.findElement(By.xpath("/html/body/div[1]/form/div[9]/button[2]"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).perform();
    }
    driver.findElement(By.xpath("/html/body/div[1]/form/div[9]/button[2]"));
  }
}
