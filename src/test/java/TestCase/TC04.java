package TestCase;

import driver.driverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

public class TC04 {
    @Test
    public void testTC04(){
        //1. Init web-driver session
        WebDriver driver = driverFactory.getChromeDriver();
        try{
            /**
             * Step 1: Goto http://live.techpanda.org/
             * Click mobile menu
             */
            //1. Open target page
            driver.get("http://live.techpanda.org/");

            //1.1 Click on MOBILE -> Menu
            WebElement mobileLink = driver.findElement(new By.ByCssSelector("body > div:nth-child(1) > div:nth-child(2) > header:nth-child(2) > div:nth-child(1) > div:nth-child(4) > nav:nth-child(1) > ol:nth-child(1) > li:nth-child(1) > a:nth-child(1)"));
            mobileLink.click();

            //debug purpose only
            Thread.sleep(2000);


            //2.Compare mobile
            WebElement compareELe1 = driver.findElement(By.xpath("//li[1]//div[1]//div[3]//ul[1]//li[2]//a[1]"));

            compareELe1.click();

            //debug purpose only
            Thread.sleep(1000);

            WebElement compareEle2 = driver.findElement(By.xpath("//li[3]//div[1]//div[3]//ul[1]//li[2]//a[1]"));
            compareEle2.click();

            //2.1 click add compare
            Thread.sleep(3000);

            //3.Click compare
            WebElement compareBtn = driver.findElement(new By.ByCssSelector("button[title='Compare']"));

            //debug purpose only
            Thread.sleep(1000);

            compareBtn.click();

            // switching to new window
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }


            //4. Get pop-up info
            WebElement header = driver.findElement(new By.ByCssSelector("div[class='page-title title-buttons'] h1"));
            System.out.println("Header expected: " + "COMPARE PRODUCTS");
            System.out.println("Header actual: " + header.getText());
            AssertJUnit.assertEquals(header.getText(), "COMPARE PRODUCTS");

            //4.1 Element1 header
            WebElement headerEle1 = driver.findElement(new By.ByCssSelector("h2[class='product-name'] a[title='IPhone']"));
            WebElement headerEle2 = driver.findElement(new By.ByCssSelector("h2[class='product-name'] a[title='Samsung Galaxy']"));
            System.out.println("Element 1 name : " + headerEle1.getText());
            System.out.println("Element 2 name : " + headerEle2.getText());

            Thread.sleep(3000);
            //5. Close pop up
            WebElement closePopupBtn = driver.findElement(new By.ByCssSelector("button[title='Close Window'] span span"));
            closePopupBtn.click();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        // quit driver session
        driver.quit();
    }
}