package page;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static io.appium.java_client.touch.offset.ElementOption.element;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class CartPage{

  private AndroidDriver<AndroidElement> driver;
  private WebDriverWait webDriverWait;
  private TouchAction touch;

  public CartPage(AndroidDriver<AndroidElement> driver){
    this.driver = driver;
    this.webDriverWait = new WebDriverWait(driver,0);
    this.touch = new TouchAction<>(driver);
  }

public List<String> getProductToPurchase() {
    this.webDriverWait.until(ExpectedConditions.visibilityOf(driver.findElementByAndroidUIAutomator("text(\"Cart\")")));
    return driver.findElementsById("com.androidsample.generalstore:id/productName").stream()
    .map(product -> product.getText()).collect(Collectors.toList());
}

public Double getProductPricesToPurchase() {
  this.webDriverWait.until(ExpectedConditions.visibilityOf(driver.findElementByAndroidUIAutomator("text(\"Cart\")")));
  return driver.findElementsById("com.androidsample.generalstore:id/productPrice").stream()
  .map(product -> Double.parseDouble(product.getText().replace("$",""))).collect(Collectors.summingDouble(Double::doubleValue));
}

public Double getTotalPurchaseAmount(){
   return Double.parseDouble(driver.findElementById("com.androidsample.generalstore:id/totalAmountLbl").getText().replace("$",""));
}

public String readTermsOfConditions() {
  MobileElement terms = driver.findElementById("com.androidsample.generalstore:id/termsButton");
  touch.longPress(element(terms)).release().perform();
  String title = driver.findElementById("com.androidsample.generalstore:id/alertTitle").getText();
  driver.findElementByAndroidUIAutomator("text(\"CLOSE\")").click();
  return title;
}

}
