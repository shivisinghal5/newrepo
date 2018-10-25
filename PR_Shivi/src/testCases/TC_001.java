package testCases;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import Base.base_class;

public class TC_001 extends base_class {
	String product1_name, product2_name, product3_name, product4_name;
	List<String> pro_names = new ArrayList<String>();

	@Test
	public void login() throws Throwable {
		driver.get(prop.getProperty("url"));
		driver.findElement(By.xpath(prop.getProperty("home.myaccount"))).click();
		driver.findElement(By.xpath(prop.getProperty("myaccount_username"))).sendKeys("shivis");
		driver.findElement(By.xpath(prop.getProperty("myaccount_password"))).sendKeys("password@12345");
		driver.findElement(By.xpath(prop.getProperty("myaccount_login"))).click();
		Thread.sleep(10000);
		String text = driver.findElement(By.xpath(prop.getProperty("login_verify_your_account"))).getText();
		Assert.assertEquals(text, "Your Account");

	}

	@Test(dependsOnMethods = { "login" })
	public void select_product() throws Throwable {
		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath(prop.getProperty("home.category")))).click().build().perform();
		driver.findElement(By.xpath(prop.getProperty("category.select.accessories"))).click();
		if (driver.findElement(By.xpath(prop.getProperty("accessories.magicmouse"))).isDisplayed() == true) {
			product1_name = driver.findElement(By.xpath(prop.getProperty("accessories.magicmouse"))).getText();
			pro_names.add(product1_name);
			driver.findElement(By.xpath(prop.getProperty("magicmouse.addcart"))).click();
		}
		if (driver.findElement(By.xpath(prop.getProperty("accesssories.apple_tv"))).isDisplayed() == true) {
			product2_name = driver.findElement(By.xpath(prop.getProperty("accesssories.apple_tv"))).getText();
			pro_names.add(product2_name);
			driver.findElement(By.xpath(prop.getProperty("appletv_addcart"))).click();
		}
		if (driver.findElement(By.xpath(prop.getProperty("accesssories.Sennheiser"))).isDisplayed() == true) {
			product3_name = driver.findElement(By.xpath(prop.getProperty("accesssories.Sennheiser"))).getText();
			pro_names.add(product3_name);
			driver.findElement(By.xpath(prop.getProperty("sennheiser.addcart"))).click();
		}
		if (driver.findElement(By.xpath(prop.getProperty("accessories.apple27"))).isDisplayed() == true) {
			product4_name = driver.findElement(By.xpath(prop.getProperty("accessories.apple27"))).getText();
			pro_names.add(product4_name);
			driver.findElement(By.xpath(prop.getProperty("apple27.addcart"))).click();

		}
		Thread.sleep(5000);
		driver.get(driver.getCurrentUrl());

		driver.findElement(By.xpath(prop.getProperty("product.checkout.button"))).click();
		
		
		WebElement table = driver.findElement(By.xpath(prop.getProperty("checkout_table")));
		List<WebElement> rows = table.findElements(By.tagName("tr"));
		int rowsize = rows.size();
		System.out.println("Total number of rows are : " + rowsize);
		for (int i = 1; i < rowsize; i++) {
			if (i == 1) {

				String product = driver.findElement(By.xpath(prop.getProperty("product.confirmation.magic_mouse")))
						.getText();
				if (pro_names.contains(product)) {

					String quantity = driver.findElement(By.xpath(prop.getProperty("product_quantity1")))
							.getAttribute("value");

					Assert.assertEquals(quantity, "1");
					System.out.println("Product name: " + product + " and Quantity: " + quantity + " is same");
				} else {
					System.out.println("Either product name or quantity is wrong for product 1");
				}
			} else if (i == 2) {
				String product = driver.findElement(By.xpath(prop.getProperty("product.confirmation.apple27")))
						.getText();
				if (pro_names.contains(product)) {

					String quantity = driver.findElement(By.xpath(prop.getProperty("product_quantity2")))
							.getAttribute("value");

					Assert.assertEquals(quantity, "1");
					System.out.println("Product name: " + product + " and Quantity: " + quantity + " is same");
				} else {
					System.out.println("Either product name or quantity is wrong for product 1");
				}
			} else if (i == 3) {
				String product = driver.findElement(By.xpath(prop.getProperty("product.confirmation.senheiser")))
						.getText();
				if (pro_names.contains(product)) {
					String quantity = driver.findElement(By.xpath(prop.getProperty("product_quantity3")))
							.getAttribute("value");

					Assert.assertEquals(quantity, "1");
					System.out.println("Product name: " + product + " and Quantity: " + quantity + " is same");
				} else {
					System.out.println("Either product name or quantity is wrong for product 1");
				}
			} else if (i == 4) {
				String product = driver.findElement(By.xpath(prop.getProperty("product.confirmation.apple_tv")))
						.getText();
				if (pro_names.contains(product)) {
					String quantity = driver.findElement(By.xpath(prop.getProperty("product_quantity4")))
							.getAttribute("value");

					Assert.assertEquals(quantity, "1");
					System.out.println("Product name: " + product + " and Quantity: " + quantity + " is same");
				} else {
					System.out.println("Either product name or quantity is wrong for product 1");
				}
			} else {
				System.out.println("Something is wrong");
			}
		}
	}

	@Test(dependsOnMethods = { "select_product" })
	public void Add_address() throws Throwable {
		
		WebElement element = driver.findElement(By.xpath(prop.getProperty("checkout.continue.button")));
		Actions act=new Actions(driver);
		act.moveToElement(element).click().build().perform();
		System.out.println("succss");
		Thread.sleep(10000);
		driver.findElement(By.xpath(prop.getProperty("shipping.state"))).sendKeys("Maharastra");
		driver.findElement(By.xpath(prop.getProperty("billing.email"))).sendKeys("shivi.qa@gmail.com");
		driver.findElement(By.xpath(prop.getProperty("billing.firstname"))).sendKeys("shivi");
		driver.findElement(By.xpath(prop.getProperty("billing.lastname"))).sendKeys("Singhal");
		driver.findElement(By.xpath(prop.getProperty("billing.address"))).sendKeys("Andheri east, mumbai");
		driver.findElement(By.xpath(prop.getProperty("billing.city"))).sendKeys("Mumbai");
		driver.findElement(By.xpath(prop.getProperty("billing.state"))).sendKeys("Maharastra");
		Select s = new Select(driver.findElement(By.xpath(prop.getProperty("billing.country"))));
		s.selectByVisibleText("India");
		driver.findElement(By.xpath(prop.getProperty("billing.phonenumber"))).sendKeys("1234569870");
		driver.findElement(By.xpath(prop.getProperty("billing.same.shipping.check_box"))).click();
		driver.findElement(By.xpath(prop.getProperty("billing.purchase_button"))).click();
	}

}
