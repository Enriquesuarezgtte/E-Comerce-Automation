package page;

import java.util.ArrayList;
import java.util.List;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class ProductPage{

  private AndroidDriver<AndroidElement> driver;
  private TouchAction touch;
  private List<String> productNames;

  public ProductPage(AndroidDriver<AndroidElement> driver){
    this.driver = driver;
    this.touch = new TouchAction<>(driver);
    this.productNames = new ArrayList<>();
  }

	public void selectProduct(List<String> productNames) {
    productNames.forEach(product ->{
    this.productNames.add(product);
      driver.findElementByAndroidUIAutomator(
        "new UiScrollable(new UiSelector().resourceId(\"com.androidsample.generalstore:id/rvProductList\")).scrollIntoView(new UiSelector().text(\""+ product +"\"));");
        this.addProductToCart();
      });
	}

	private void addProductToCart() {
    List<AndroidElement> products = driver.findElementsById("com.androidsample.generalstore:id/productName");

    for(int i =0; i<products.size(); i++){
      String product = products.get(i).getText();
      if (this.productNames.contains(product)){
        driver.findElementsById("com.androidsample.generalstore:id/productAddCart").get(i).click();
      }
    }
  }

	public void goToCart() {
    driver.findElementById("com.androidsample.generalstore:id/appbar_btn_cart").click();
	}

	public void checkAndGoToWeb() {
    driver.findElementByClassName("android.widget.CheckBox").click();
    driver.findElementById("com.androidsample.generalstore:id/btnProceed").click();
  }
}
