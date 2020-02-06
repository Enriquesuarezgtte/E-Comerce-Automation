package page;

import static io.appium.java_client.touch.offset.ElementOption.element;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class LoginPage {
  private AndroidDriver<AndroidElement> driver;
  private TouchAction touch;

  public LoginPage(AndroidDriver<AndroidElement> driver){
    this.driver = driver;
    this.touch = new TouchAction<>(driver);
  }

	public void selectCountry() {
    driver.findElementById("com.androidsample.generalstore:id/spinnerCountry").click();
    driver.findElementByAndroidUIAutomator(
      "new UiScrollable(new UiSelector()).scrollIntoView(new UiSelector().textContains(\"Angola\"))").click();

	}

	public void fillName(String name) {
    driver.findElementById("com.androidsample.generalstore:id/nameField").sendKeys(name);
  }

	public void selectGender(String gender) {
    driver.findElementByAndroidUIAutomator("text(\""+gender+"\")").click();
	}

	public void submit() {
    MobileElement letshop = driver.findElementById("com.androidsample.generalstore:id/btnLetsShop");
    touch.tap(element(letshop)).release().perform();
	}

	public String getToastMessage() {
    String name = driver.findElementByXPath("//android.widget.Toast[1]").getAttribute("name");
    System.out.println(name);
    return name;
	}
}
