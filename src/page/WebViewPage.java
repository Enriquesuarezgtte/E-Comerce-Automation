package page;

import java.util.Set;

import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class WebViewPage {

  private AndroidDriver<AndroidElement> driver;
  private TouchAction touch;
  private WebDriverWait webDriver;

  public WebViewPage(AndroidDriver<AndroidElement> driver) {
    this.driver = driver;
    this.touch = new TouchAction<>(driver);
    this.webDriver = new WebDriverWait(driver, 0);
  }

  public void changeDriverContext() {
    this.webDriver
        .until(ExpectedConditions.visibilityOf(driver.findElementById("com.androidsample.generalstore:id/webView")));
    this.driver.context((String) driver.getContextHandles().toArray()[1]);
    Set<String> contexts = driver.getContextHandles();
    for (String contextName : contexts) {
      System.out.println(contextName);
    }
  }

  public void sendText(String text) {
    this.driver.findElementByXPath("//*[@name=\"q\"]").sendKeys(text);
    this.driver.findElementByXPath("//*[@name=\"q\"]").sendKeys(Keys.ENTER);
    this.driver.pressKey(new KeyEvent(AndroidKey.BACK));
    this.driver.context("NATIVE_APP");
    }
}
