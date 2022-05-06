package common;

import java.io.IOException;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import utilities.TestUtils;

public class Listeners extends TestUtils implements ITestListener {
  public void onTestStart(ITestResult result) {
    Reporter.log("Method name is - " + result.getName());
  }

  public void onTestSuccess(ITestResult result) {
    Reporter.log("<br>Status of execution is - " + "passed");
  }

  public void onTestFailure(ITestResult result) {
    try {
      Thread.sleep(2000);
    } catch (InterruptedException _e) {
      _e.printStackTrace();
    }
    System.out.println("Test failed - screenshot captured");
    Reporter.log("Status of execution is - " + "failed");
    try {
      String screenshotFileName = getScreenshot();
      String screenshotPath = "file:///hsLehre-test/screenshot"+ screenshotFileName+".png";
      Reporter.log("<br>Screenshot - <a href=\""+screenshotPath + "\">Test Results</a>");
    } catch (IOException _e) {
      _e.printStackTrace();
    }
  }

  public void onTestSkipped(ITestResult result) {
  }

  public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
  }

  public void onTestFailedWithTimeout(ITestResult result) {
    this.onTestFailure(result);
  }

  public void onStart(ITestContext context) {
  }

  public void onFinish(ITestContext context) {
  }
}
