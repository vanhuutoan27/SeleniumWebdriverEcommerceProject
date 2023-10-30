package TestCase;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import driver.driverFactory;

import java.io.File;

public class TC03 {

    @Test
    public void testTC03() {
        int scc = 0;
        StringBuffer verificationErrors = new StringBuffer();

        // Initiate web driver session
        WebDriver driver = driverFactory.getChromeDriver();

        try {
            // Step 1. Go to http://live.techpanda.org/
            driver.get("http://live.techpanda.org/");

            // Step 2. Click on "MOBILE" menu
            driver.findElement(By.linkText("MOBILE")).click();

            // Debug purpose only
            Thread.sleep(2000);

            // Step 3. In the list of all mobiles, click on "ADD TO CART" for Sony Xperia mobile
            WebElement addToCartButton = driver.findElement(By.xpath("//a[@title='Sony Xperia']/ancestor::li[@class='item last']//button[@title='Add to Cart']"));
            addToCartButton.click();

            // Debug purpose only
            Thread.sleep(2000);

            // Step 4. Change the "QTY" value to 1000 and click the "UPDATE" button
            WebElement qtyInput = driver.findElement(By.xpath("//input[@title='Qty']"));
            qtyInput.clear();
            qtyInput.sendKeys("1000");
            driver.findElement(By.xpath("//button[@title='Update']")).click();

            // Debug purpose only
            Thread.sleep(2000);

            // Step 5. Verify the error message
            WebElement errorMessage = driver.findElement(By.xpath("//p[@class='item-msg error']"));
            String expectedErrorMessage = "The requested quantity for \"Sony Xperia\" is not available";
            AssertJUnit.assertEquals(errorMessage.getText(), expectedErrorMessage);

            // Debug purpose only
            Thread.sleep(2000);

            // Step 6. Click on "EMPTY CART" link
            driver.findElement(By.xpath("//button[@title='Empty Cart']")).click();

            // Debug purpose only
            Thread.sleep(2000);

            // Step 7. Verify cart is empty
            try {
                WebElement emptyCartMessage = driver.findElement(By.xpath("//h1[normalize-space()='Shopping Cart is Empty']"));
                String expectedEmptyCartMessage = "SHOPPING CART IS EMPTY";
                Assert.assertTrue(emptyCartMessage.getText().contains(expectedEmptyCartMessage));
                System.out.println("Cart is empty!");
            } catch (NoSuchElementException e) {
                System.out.println("Cart is not empty!");
            }

            // Debug purpose only
            Thread.sleep(2000);

            scc = (scc + 1);
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String png = "C:\\Users\\Admin\\Desktop\\SWT301\\Assignment9+10\\selenium-webdriver-java-master\\src\\test\\java\\TestCase\\" + scc + ".png";
            FileUtils.copyFile(srcFile, new File(png));

            // Step 8. Quit browser session
            driver.quit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
