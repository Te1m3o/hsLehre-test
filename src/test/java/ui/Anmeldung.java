package ui;

import common.BaseTest;
import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

import net.bytebuddy.implementation.bytecode.Throw;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class Anmeldung extends BaseTest {
  String password = "1234";
  String professor ="eti.ulf.steinke@fh-stralsund.de";
  @Test
  public void professorLogin(){
    login(professor, password);
  }
  public void login(String username, String password) {
    driver.get("http://localhost:8080/login");
    //Maxzimize current window
    driver.manage().window().maximize();

    driver.findElement(By.xpath("//*[@id=\"username\"]")).clear();
    driver.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys(username);

    driver.findElement(By.xpath("//*[@id=\"password\"]")).clear();
    driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(password);

    driver.findElement(By.xpath("/html/body/div/div[2]/form/input[3]")).click();
  }
}
