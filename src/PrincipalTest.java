import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import page.CartPage;
import page.LoginPage;
import page.ProductPage;
import static java.time.Duration.ofSeconds;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PrincipalTest extends Capabilities{

  private AndroidDriver<AndroidElement> driver;
  private LoginPage loginPage;
  private ProductPage productPage;
  private CartPage cartPage;
  private List<String> products =new ArrayList<>();

	@Before
	public void setUp() throws MalformedURLException {
  driver = capabilities();
  driver.configuratorSetWaitForSelectorTimeout(ofSeconds(90));
  loginPage = new LoginPage(driver);
  productPage = new ProductPage(driver);
  cartPage = new CartPage(driver);
	}

	@Test
	public void fillFormTest() throws InterruptedException {
    loginPage.selectCountry();
    loginPage.fillName("Jose Suarez");
    loginPage.selectGender("Female");
    loginPage.submit();
    addProductCartTest();
  }

  @Test
  public void fillNotAllFormTest() throws InterruptedException {
    loginPage.selectCountry();
    loginPage.selectGender("Female");
    loginPage.submit();
    Assert.assertTrue(loginPage.getToastMessage().contains("Please enter your name"));
  }


  public void addProductCartTest() throws InterruptedException {
    this.products.add("Air Jordan 4 Retro"); this.products.add("Nike SFB Jungle");
    productPage.selectProduct(this.products);
    productPage.goToCart();
    verifyPurchase();
    verifyAmount();
  }


	private void verifyAmount() {
    Assert.assertEquals(cartPage.getProductPricesToPurchase(),cartPage.getTotalPurchaseAmount());
  }

  public void verifyPurchase() {
    Assert.assertTrue(cartPage.getProductToPurchase().equals(this.products));
  }

  @After
	  public void tearDown() {
	    driver.quit();
	  }
	}
