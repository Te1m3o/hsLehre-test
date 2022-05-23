package ui;

import common.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Professor extends BaseTest {
    String password = "1234";
    String professor = "eti.ulf.steinke@fh-stralsund.de";
    String professorLehrerhebung = "eti.berthold.heisterkamp@fh-stralsund.de";
    String professorKrankheitssemester = "eti.erika.burstedt@fh-stralsund.de";
    String professorForschungssemester = "roy.keipke@fh-stralsund.de";
    String professorAlteLehrerhebung = "olivia.merle@fh-stralsund.de";
    Anmeldung anmeldung = new Anmeldung();

    @Test
    public void Anmeldung() {
        anmeldung.anmelden(professor, password);
        driver.findElement(By.xpath("/html/body/div[1]/h2")).isDisplayed();
    }

    @Test
    public void Abmeldung() {
        anmeldung.anmelden(professor, password);
        driver.findElement(By.xpath("/html/body/div[1]/div[1]/a")).click();
        String expectedLabel = "Logout erfolgreich.";
        String actualLabel = driver.findElement(By.xpath("/html/body/div/div[2]/div/label")).getText();
        Assert.assertEquals(expectedLabel, actualLabel);
    }

    @Test
    public void erstellenLehrerhebung() {
        anmeldung.anmelden(professorLehrerhebung, password);

        driver.findElement(By.xpath("/html/body/div[1]/h2")).isDisplayed();
//    neue Lehrerhebung erstellen
        driver.findElement(By.xpath("/html/body/div[1]/a/span")).click();
//    Ermäßigung wegen besonderer Aufgabe/Funktion anlegen
        driver.findElement(By.xpath("//button[@name=\'addRowErmaessigungAufgabe\']")).click();
        driver.findElement(By.xpath("//input[@id=\'ermaessigungAufgabeGrund\']")).sendKeys("Dokumentation");
        driver.findElement(By.xpath("//input[@id=\'ermaessigungAufgabeSWSAnzahl\']")).sendKeys("4");
//    Ermäßigung wegen Schwerbehinderung anlegen
        driver.findElement(By.xpath("//input[@id=\'ermaessigungBehinderung\']")).click();
        driver.findElement(By.xpath("//input[@id=\'ermaessigungBehinderung\']")).sendKeys(Keys.BACK_SPACE);
        driver.findElement(By.xpath("//input[@id=\'ermaessigungBehinderung\']")).sendKeys("4");
//    scrollen
        {
            WebElement element = driver.findElement(By.xpath("/html/body/div[1]/form/div[5]/h3/span"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).perform();
        }
//    Lehrtätigkeit anlegen
        driver.findElement(By.xpath("/html/body/div[1]/form/div[4]/table/tfoot/tr/td[9]")).click();
        driver.findElement(By.id("lehrveranstaltungsname[0][0]")).sendKeys("Mathematik I");
        driver.findElement(By.id("lehrtaetigkeitStudierendenAnzahl")).sendKeys("16");
        driver.findElement(By.id("lehrtaetigkeitSwsAnzahl")).sendKeys("2");
//    scrollen
        {
            WebElement element = driver.findElement(By.xpath("/html/body/div[1]/form/div[6]/h3"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).perform();
        }
//    Lehrtätigkeit außerhalb der Prüfungsordnung anlegen
        driver.findElement(By.xpath("/html/body/div[1]/form/div[5]/table/tfoot/tr/td[9]/button")).click();
        driver.findElement(By.id("lehrfakultaetAusserhalbPO[1][0]")).sendKeys("MB");
        driver.findElement(By.id("lehrveranstaltungsname[1][0]")).sendKeys("Mathematik II");
        driver.findElement(By.name("lehrtaetigkeitList[1].teilnehmerAnzahl")).sendKeys("16");
        driver.findElement(By.name("lehrtaetigkeitList[1].swsAnzahl")).sendKeys("2");
//    scrollen
        {
            WebElement element = driver.findElement(By.xpath("/html/body/div[1]/form/div[7]/h3"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).perform();
        }
//    Blockveranstaltung anlegen
        driver.findElement(By.name("addRowBlockveranstaltung")).click();
        driver.findElement(By.id("blockveranstaltungsname[0][0]")).sendKeys("Mathematik I");
        driver.findElement(By.id("blockveranstaltungDatumStart[0]")).sendKeys("22.05.2022");
        driver.findElement(By.id("blockveranstaltungDatumEnde[0]")).sendKeys("23.05.2022");
        driver.findElement(By.id("blockveranstaltungStudierendenAnzahl")).sendKeys("16");
        driver.findElement(By.id("blockveranstaltungStundenAnzahl")).sendKeys("2");
//    scrollen
        {
            WebElement element = driver.findElement(By.xpath("/html/body/div[1]/form/div[8]/h3"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).perform();
        }
//    Bachelor-/Master-/Diplom-/Projektarbeiten anlegen
        driver.findElement(By.name("addRowArbeit")).click();
        driver.findElement(By.xpath("//*[@id=\"arbeit-titel\"]")).sendKeys("Was ich noch machen wollte");
        driver.findElement(By.id("arbeitAutor")).sendKeys("Erni");
        driver.findElement(By.id("arbeitAnmeldedatum[0]")).sendKeys("22.10.2222");
//    zum Ende der Seite scrollen
        {
            ((JavascriptExecutor) driver)
                    .executeScript("window.scrollTo(0, document.body.scrollHeight)");
        }
//    speichern
        driver.findElement(By.xpath("/html/body/div[1]/form/div[9]/button[1]")).click();
//    auf Hauptseite gespeicherte Lehrerhebung aufrufen
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/table/tbody/tr/td[4]/form/button/span")).click();
//    Vergleich, ob Daten geladen wurden
        String expectedEntry = "Dokumentation";
        String actualEntry = driver.findElement(By.id("ermaessigungAufgabeGrund")).getAttribute("value");
        Assert.assertEquals(expectedEntry, actualEntry);
//    Eingabe ändern
        driver.findElement(By.xpath("//input[@id=\'ermaessigungAufgabeGrund\']")).sendKeys("Bearbeitet");
//    zum Ende der Seite scrollen
        {
            ((JavascriptExecutor) driver)
                    .executeScript("window.scrollTo(0, document.body.scrollHeight)");
        }
//     abschicken
        driver.findElement(By.xpath("/html/body/div[1]/form/div[9]/button[2]")).click();
//      prüfen, ob an Dekan gesendet wurde
        String expectedLabel = "An Dekan gesendet";
        String actualLabel = driver.findElement(By.xpath("/html/body/div[1]/div[2]/table/tbody/tr/td[3]")).getText();
        Assert.assertEquals(expectedLabel, actualLabel);
    }

    @Test
    public void erstellenKrankheitssemester() {
        anmeldung.anmelden(professorKrankheitssemester, password);

        driver.findElement(By.xpath("/html/body/div[1]/h2")).isDisplayed();
//    neue Lehrerhebung erstellen
        driver.findElement(By.xpath("/html/body/div[1]/a/span")).click();

        driver.findElement(By.xpath("//*[@id=\"lehrerhebungsTyp\"]")).sendKeys("Krankheitssemester");

        driver.findElement(By.xpath("//*[@id=\"regellehrverpflichtung\"]")).sendKeys("18");
        //    zum Ende der Seite scrollen
        {
            ((JavascriptExecutor) driver)
                    .executeScript("window.scrollTo(0, document.body.scrollHeight)");
        }
//     abschicken
        driver.findElement(By.xpath("/html/body/div[1]/form/div[9]/button[2]")).click();
        //      prüfen, ob an Dekan gesendet wurde
        String expectedLabel = "An Dekan gesendet";
        String actualLabel = driver.findElement(By.xpath("/html/body/div[1]/div[2]/table/tbody/tr/td[3]")).getText();
        Assert.assertEquals(expectedLabel, actualLabel);
    }

    @Test
    public void erstellenForschungssemester() {
        anmeldung.anmelden(professorForschungssemester, password);

        driver.findElement(By.xpath("/html/body/div[1]/h2")).isDisplayed();
//    neue Lehrerhebung erstellen
        driver.findElement(By.xpath("/html/body/div[1]/a/span")).click();
        driver.findElement(By.xpath("//*[@id=\"lehrerhebungsTyp\"]")).sendKeys("Forschungssemester");

        driver.findElement(By.xpath("//*[@id=\"regellehrverpflichtung\"]")).sendKeys("18");
        //    zum Ende der Seite scrollen
        {
            ((JavascriptExecutor) driver)
                    .executeScript("window.scrollTo(0, document.body.scrollHeight)");
        }
//     abschicken
        driver.findElement(By.xpath("/html/body/div[1]/form/div[9]/button[2]")).click();
        //      prüfen, ob an Dekan gesendet wurde
        String expectedLabel = "An Dekan gesendet";
        String actualLabel = driver.findElement(By.xpath("/html/body/div[1]/div[2]/table/tbody/tr/td[3]")).getText();
        Assert.assertEquals(expectedLabel, actualLabel);
    }

    @Test
    public void uebernehmenAlterLehrerhebung() {
        anmeldung.anmelden(professorAlteLehrerhebung, password);

        driver.findElement(By.xpath("/html/body/div[1]/h2")).isDisplayed();
//    neue Lehrerhebung erstellen
        driver.findElement(By.xpath("/html/body/div[1]/a/span")).click();
//      alte Lehrerhebung laden
        driver.findElement(By.cssSelector(".caret")).click();
        driver.findElement(By.linkText("Lehrerhebung WS 2016")).click();
//      zum Ende der Seite scrollen
        {
            ((JavascriptExecutor) driver)
                    .executeScript("window.scrollTo(0, document.body.scrollHeight)");
        }
//      abschicken
        driver.findElement(By.xpath("/html/body/div[1]/form/div[9]/button[2]")).click();
//      prüfen, ob an Dekan gesendet wurde
        String expectedLabel = "An Dekan gesendet";
        String actualLabel = driver.findElement(By.xpath("/html/body/div[1]/div[2]/table/tbody/tr/td[3]")).getText();
        Assert.assertEquals(expectedLabel, actualLabel);
    }
}
