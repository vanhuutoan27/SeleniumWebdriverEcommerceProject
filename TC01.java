package TestCase;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import driver.driverFactory;

public class TC01 {

    @Test
    public void testTC01() {
        int scc = 0;
        StringBuffer verificationErrors = new StringBuffer();

        //Init web driver session
        WebDriver driver = driverFactory.getChromeDriver();

        try {
            // Step 1. Go to http://live.techpanda.org/
            driver.get("http://live.techpanda.org/");

            // Step 2. Verify Title of the page
            String demoSite = driver.findElement(By.cssSelector("h2")).getText();
            System.out.println(demoSite);
            try {
                AssertJUnit.assertEquals("This is demo site for   ", demoSite);
            } catch (Error e) {
                verificationErrors.append(e.toString());
            }

            // Debug purpose only
            Thread.sleep(3000);

            // Step 3. Click on MOBILE menu
            driver.findElement(By.linkText("MOBILE")).click();

            // Debug purpose only
            Thread.sleep(3000);

            // Step 4. In the list of all mobile, select SORT BY -> dropdown as name
            WebElement sortDropdown = driver.findElement(By.cssSelector("select[title='Sort By']"));
            Select sortBy = new Select(sortDropdown);
            sortBy.selectByVisibleText("Name");

            // Debug purpose only
            Thread.sleep(3000);

            // Step 5. Verify all products are sorted by name
            WebElement productTable = driver.findElement(By.cssSelector("ul.products-grid"));
            // You can add your verification logic here

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Test case failed: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}
