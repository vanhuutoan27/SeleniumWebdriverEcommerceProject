package TestCase;

import POM.CartPage;
import POM.CheckoutPage;
import POM.LoginPage;
import driver.driverFactory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import java.io.File;
import java.util.List;
import java.util.Locale;

public class TC08 {
    WebDriver driver = driverFactory.getChromeDriver();

    @Test
    public void testTC08(){
        try{
            String firstname = "Team";
            String lastname = "Work";
            String email = "marklewis27@gmail.com";
            String password = "123456";
            String address = "United States";
            String country = "US";
            String company = "FPT";
            String region = "57";
            String zip = "2000";
            String city = "Texas";
            String telephone = "0123456789";
            String state = "Ya";

            //1. Go to http://live.techpanda.org/
            driver.get("http://live.techpanda.org/");
            //2. Click on My Account link
            LoginPage loginPage = new LoginPage(driver);
            loginPage.clickMyAccountLink();
            //3. Login in application using previously created credential
            loginPage.enterEmail(email);
            loginPage.enterPassword(password);
            loginPage.clickLoginButton();
            //4. Click on 'REORDER' link , change QTY & click Update
            //4.1 Click Reorder
            driver.findElement(By.xpath("//tr[@class='first odd']//a[@class='link-reorder'][normalize-space()='Reorder']")).click();
            String prevTotal, currTotal;
            prevTotal = driver.findElement(By.cssSelector("strong span[class='price']")).getText().substring(1);
            System.out.println("Previous Grand Total is " + prevTotal);
            //4.2 Update Quanity
            WebElement quantityEle = driver.findElement(By.xpath("//input[@title='Qty']"));
            quantityEle.clear();
            quantityEle.sendKeys("6");
            //4.3 Click update
            driver.findElement(By.xpath("//button[@title='Update']")).click();

            //5. Verify Grand Total is changed
            currTotal = driver.findElement(By.cssSelector("strong span[class='price']")).getText().substring(1);
            System.out.println("Actual Grand Total is " + currTotal);
            Assert.assertNotEquals(prevTotal, currTotal);

            //5.1 Click proceed checkout
            CartPage cartPage = new CartPage(driver);
            cartPage.clickProceedToCheckout();

            //6. Complete Billing & Shipping Information
            CheckoutPage checkoutPage = new CheckoutPage(driver);
            checkoutPage.BillingNewAddress();
            checkoutPage.enterBilling(firstname, lastname, company ,address, region, city, zip, country ,telephone);
            Thread.sleep(2000);
            checkoutPage.clickDifferentAddess();
            //checkoutPage.clickBillingContinue();
            driver.findElement(By.xpath("//button[@onclick='billing.save()']//span//span[contains(text(),'Continue')]")).click();

            Thread.sleep(3000);

            //checkoutPage.ShippingNewAddress();
            WebElement shippingEle = driver.findElement(By.xpath("//select[@id='shipping-address-select']"));
            Select shippingOpts = new Select(shippingEle);
            shippingOpts.selectByIndex(shippingOpts.getOptions().size() - 1);

            checkoutPage.enterShipping(firstname + "haha", lastname + "hihi", company +"Uni", address + "hehe", region, city, zip+"123", country,telephone);
            //checkoutPage.clickUseBillingAddress();
            //checkoutPage.clickShippingContinue();
            // Click continue button
            driver.findElement(By.xpath("//button[@onclick='shipping.save()']//span//span[contains(text(),'Continue')]")).click();
            Thread.sleep(3000);

            String shipMethod = driver.findElement(By.xpath("//dt[normalize-space()='Flat Rate']")).getText();
            Assert.assertEquals(shipMethod, "Flat Rate");

            // Click shipping method continue
            driver.findElement(By.xpath("//button[@onclick='shippingMethod.save()']//span//span[contains(text(),'Continue')]")).click();
            Thread.sleep(3000);

            checkoutPage.selectCheckMoneyOrderPaymentMethod();

            // Click payment continue
            driver.findElement(By.xpath("//button[@class='button']//span//span[contains(text(),'Continue')]")).click();

            //checkoutPage.clickPlaceOrder();

            Thread.sleep(2000);

            // Click place order
            driver.findElement(By.xpath("//span[contains(text(),'Place Order')]")).click();

            Thread.sleep(3000);

            //    7. Verify order is generated and note the order number
            List<WebElement> allLinks = driver.findElements(By.tagName("a"));
            for(WebElement link:allLinks){
                if(link.getText().startsWith("1000")){
                    System.out.println("Created order Id: " + link.getText());
                }
            }

            File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

            String png = ("C:\\Users\\Admin\\Desktop\\SWT301\\selenium-webdriver-java-master\\src\\test\\java\\TestCase\\screenshots\\TestCase08.png");
            FileUtils.copyFile(scrFile, new File(png));

            //    Expected outcomes:
            //    1) Grand Total is Changed
            //    2) Order number is generated
        }catch (Exception ex){
            ex.printStackTrace();
        }

        driver.quit();
    }
}