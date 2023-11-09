package TestCase;

import POM.RegisterPage;
import driver.driverFactory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import java.io.File;

public class TC05 {
    @Test
    public void testTC05() {
        //1. Init web-driver session
        WebDriver driver = driverFactory.getChromeDriver();

        try {
            // default info
            String firstName = "Van";
            String lastName = "Huu Toan";
            String email = "zeushehe@gmail.com";
            String password = "123456";
            String confirmPass = "123456";

            //1. Go to http://live.techpanda.org/
            driver.get("http://live.techpanda.org/");
            //2. Click on my account link
            WebElement myAccountLink = driver.findElement(By.xpath("//a[@class='skip-link skip-account']"));
            myAccountLink.click();
            //3. Click Create an Account link and fill New User information excluding the registered Email ID.
            driver.findElement(By.xpath(("//div[@id='header-account']//a[@title='My Account'][normalize-space()='My Account']"))).click();
            //4. Click Register
            driver.findElement(By.xpath(("//span[contains(text(),'Create an Account')]"))).click();

            Thread.sleep(2000);

            //5. init register page model
            RegisterPage registerPage = new RegisterPage(driver);

            registerPage.enterFirtstName(firstName);
            registerPage.enterLastName(lastName);
            registerPage.enterEmail(email);
            registerPage.enterPassword(password);
            registerPage.enterConfirmPassword(confirmPass);
            Thread.sleep(2000);

            driver.findElement(By.xpath("//span[contains(text(),'Register')]")).click();

            //5.1 Verify Registration is done. Expected account registration done.
            WebElement msg = driver.findElement(By.xpath("//p[@class='welcome-msg']"));
            System.out.println("Expected message: " + "WELCOME, VAN HUU TOAN!");
            System.out.println("Actual message: " + msg.getText());
            AssertJUnit.assertEquals(msg.getText(), "WELCOME, VAN HUU TOAN!");
            // stop 1s
            Thread.sleep(1000);
            //6. Go to TV menu
            driver.findElement(By.xpath("//a[normalize-space()='TV']")).click();
            // stop 1s
            Thread.sleep(1000);
            //7. Add product in your wish list - use product - LG LCD
            driver.findElement(By.xpath("//li[1]//div[1]//div[3]//ul[1]//li[1]//a[1]")).click();
            //8. Click SHARE WISHLIST
            driver.findElement(By.xpath("//span[contains(text(),'Share Wishlist')]")).click();
            // stop 1s
            Thread.sleep(1000);
            //9. In next page enter Email and a message and click SHARE WISHLIST
            driver.findElement(By.xpath("//textarea[@id='email_address']")).sendKeys(email);
            // sleep 1s
            Thread.sleep(1000);
            driver.findElement(By.xpath("//textarea[@id='message']")).sendKeys("Hello, I'm Toan");
            // sleep 1s
            Thread.sleep(1000);
            //10.Check wishlist is shared. Expected wishlist shared successfully.
            driver.findElement(By.xpath("//span[contains(text(),'Share Wishlist')]")).click();

            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            String png = ("C:\\Users\\Admin\\Desktop\\SWT301\\selenium-webdriver-java-master\\src\\test\\java\\TestCase\\screenshots\\TestCase05.png");
            FileUtils.copyFile(scrFile, new File(png));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        driver.quit();
    }
}