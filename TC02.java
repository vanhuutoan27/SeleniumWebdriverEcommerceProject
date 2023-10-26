package TestCase;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import driver.driverFactory;

import java.io.File;
import java.time.Duration;

public class TC02 {

    @Test
    public void testTC02() {
        int scc = 0;
        StringBuffer verificationErrors = new StringBuffer();

        // Init web driver session
        WebDriver driver = driverFactory.getChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Step 1. Go to http://live.techpanda.org/
            driver.get("http://live.techpanda.org/");

            // Step 2. Click on MOBILE menu
            driver.findElement(By.linkText("MOBILE")).click();

            // Debug purpose only
            Thread.sleep(2000);

            // Step 3. In the list of all mobiles, read the cost of Sony Xperia mobile (which is $100)
            WebElement sonyXperiaElement = driver.findElement(By.xpath("//a[@title='Sony Xperia']"));
            String sonyXperiaPrice = driver.findElement(By.cssSelector("#product-price-1 > span.price")).getText();
            System.out.println("Sony Xperia Price: " + sonyXperiaPrice);

            // Debug purpose only
            Thread.sleep(2000);

            // Step 4. Click on Sony Xperia mobile
//            driver.findElement(By.xpath("//a[@title='Sony Xperia']")).click();
            sonyXperiaElement.click();

            // Debug purpose only
            Thread.sleep(2000);

            // Step 5. Read the Sony Xperia mobile from detail page
            String sonyXperiaDetailPrice = driver.findElement(By.cssSelector("#product-price-1 > span.price")).getText();
            System.out.println("Sony Xperia Detail Price: " + sonyXperiaDetailPrice);

            // Debug purpose only
            Thread.sleep(2000);

            // Step 6. Compare Product value in list and details page should be equal ($100)
            Assert.assertEquals(sonyXperiaPrice, sonyXperiaDetailPrice);
            System.out.println("Prices are equal.");

            // Debug purpose only
            Thread.sleep(2000);

            scc = (scc + 1);
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String png = "C:\\Users\\Admin\\Desktop\\SWT301\\selenium-webdriver-java-master\\src\\test\\java\\TestCase\\" + scc + ".png";
            FileUtils.copyFile(srcFile, new File(png));

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Test case failed: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}
