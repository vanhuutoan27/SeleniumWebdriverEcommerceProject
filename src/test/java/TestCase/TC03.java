package TestCase;

import driver.driverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

public class TC03 {
    @Test
    public void testTC03(){
        //1. Init web-driver session
        WebDriver driver = driverFactory.getChromeDriver();
        //2. Open target page
        try{

            /**
             * Step 1: Goto http://live.techpanda.org/
             * Click mobile menu
             */
            //1. Open target page
            driver.get("http://live.techpanda.org/");

            //1.1 Click on MOBILE -> Menu
            WebElement mobileLink = driver.findElement(new By.ByCssSelector("body > div:nth-child(1) > div:nth-child(2) > header:nth-child(2) > div:nth-child(1) > div:nth-child(4) > nav:nth-child(1) > ol:nth-child(1) > li:nth-child(1) > a:nth-child(1)"));

            //debug purpose only
            Thread.sleep(2000);

            //1.2 Click MOBILE Link
            mobileLink.click();

            //2. Add to cart
            WebElement sonyXPoduct = driver.findElement(new By.ByCssSelector("body > div:nth-child(1) > div:nth-child(2) > div:nth-child(3) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(3) > ul:nth-child(2) > li:nth-child(2) > div:nth-child(2) > div:nth-child(4) > button:nth-child(1) > span:nth-child(1) > span:nth-child(1)"));

            //2.1 Click add to cart
            sonyXPoduct.click();

            //3.Input quantity
            WebElement quantity = driver.findElement(new By.ByCssSelector("input[title='Qty']"));
            quantity.clear();
            quantity.sendKeys("1000");

            //3.1 Click update btn
            WebElement updateBtn = driver.findElement(new By.ByCssSelector("button[title='Update'] span span"));
            updateBtn.click();

            //4. get error and verify
            WebElement errorMsg = driver.findElement(new By.ByCssSelector(".item-msg.error"));
            System.out.println("Error expected: " + errorMsg.getText());
            System.out.println("Error actual: " + "The requested quantity for 'Sony Xperia' is not available.");
            Assert.assertEquals(errorMsg.getText(),
                    "The requested quantity for 'Sony Xperia' is not available.");

            //5. Click on empty cart
            WebElement emptyCart = driver.findElement(new By.ByCssSelector("button[id='empty_cart_button'] span span"));

            //debug purpose only
            Thread.sleep(1000);

            emptyCart.click();

            WebElement emptyCartMsg = driver.findElement(new By.ByCssSelector("div[class='page-title'] h1"));
            System.out.println("Empty message expected: " + emptyCartMsg.getText());
            System.out.println("Empty message actual: " + "SHOPPING CART IS EMPTY");
            AssertJUnit.assertEquals(emptyCartMsg.getText(), "SHOPPING CART IS EMPTY");
        }catch(Exception ex){
            ex.printStackTrace();
        }

        // quit driver session
        driver.quit();
    }
}