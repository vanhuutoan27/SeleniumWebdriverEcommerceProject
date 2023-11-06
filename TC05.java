package TestCase;


import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import POM.registerPage;
import driver.driverFactory;

public class TC05 {
    @Test
    public void testTC05() {
        int scc = 0;
        //Init web-driver session
        WebDriver driver = driverFactory.getChromeDriver();
        registerPage registerPage = new registerPage(driver);
        String firstName = "Van";
        String lastName = "Huu Toan";
        String email_address = "vanhuutoan27@gmail.com";
        String password = "123456";
        String confirmPassword = "123456";

        try {
            //Step 1. Go to http://live.techpanda.org/
            driver.get("http://live.techpanda.org/");

            // Step 2. Click on my account link
            registerPage.clickMyAccountLink();
            // Switching to new window
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }

            // Step 3. Click Create an Account link and fill New User information excluding the registered Email ID.
            registerPage.clickCreateAccountLink();
            // Switching to new window
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            Thread.sleep(2000);
            // fill New User information
            registerPage.enterFirstName(firstName);
            registerPage.enterLastName(lastName);
            registerPage.enterEmail(email_address);
            registerPage.enterPassword(password);
            registerPage.enterConfirmPassword(confirmPassword);


            // Step 4. Click Register
            registerPage.clickRegisterButton();
            Thread.sleep(2000);
            // Switching to new window
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            Thread.sleep(2000);

            // Step 5. Verify Registration is done. Expected account registration done.
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            Thread.sleep(2000);

            WebElement succesRegistrationMsg = driver.findElement(By.xpath("//li[@class='success-msg']//ul//li"));
            String expectedsuccesRegistrationMsg = "Thank you for registering with Main Website Store.";
            String actualsuccesRegistrationMsg = succesRegistrationMsg.getText();
            System.out.println(actualsuccesRegistrationMsg);
            if (actualsuccesRegistrationMsg.equals(expectedsuccesRegistrationMsg)) {
                System.out.println("Verify Registration is done with the message " + actualsuccesRegistrationMsg);
            } else {
                System.out.println("Verify Registration is not done with the message.");
            }

            // Step 6. Go to TV menu
            driver.findElement(By.linkText("TV")).click();
            // for debug only
            Thread.sleep(2000);

            // Step 7. Add product in your wish list - use product - LG LCD
            driver.findElement(By.xpath("//li[1]//div[1]//div[3]//ul[1]//li[1]//a[1]")).click();
            Thread.sleep(2000);


            // Step 8. Click SHARE WISHLIST
            // Switching to new window
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            driver.findElement(By.xpath("//span[contains(text(),'Share Wishlist')]")).click();
            Thread.sleep(2000);

            // Step 9. In next page enter Email and a message and click SHARE WISHLIST
            WebElement emailInput = driver.findElement(By.xpath("//textarea[@id='email_address']"));
            emailInput.clear();
            emailInput.sendKeys("thuan123@gmail.com");

            WebElement msgInput = driver.findElement(By.xpath("//textarea[@id='message']"));
            msgInput.clear();
            msgInput.sendKeys("nothing");

            driver.findElement(By.xpath("//span[contains(text(),'Share Wishlist')]")).click();
            Thread.sleep(2000);


            // Step 10. Check wishlist is shared. Expected wishlist shared successfully.
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            Thread.sleep(2000);

            WebElement succesShareWishListMsg = driver.findElement(By.cssSelector("li[class='success-msg'] ul li span"));
            String expectedsuccesShareWishListMsg = "Your Wishlist has been shared.";
            String actualsuccesShareWishListMsg = succesShareWishListMsg.getText();
            System.out.println(actualsuccesShareWishListMsg);
            if (actualsuccesShareWishListMsg.equals(expectedsuccesShareWishListMsg)) {
                System.out.println("Verify wishlist share is done with the message " + actualsuccesRegistrationMsg);
            } else {
                System.out.println("Verify wishlist share is not done.");
            }

            scc = (scc + 5);
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String png = "C:\\Users\\Admin\\Desktop\\SWT301\\selenium-webdriver-java-master\\src\\test\\java\\TestCase\\ScreenshotTC0" + scc + ".png";
            FileUtils.copyFile(srcFile, new File(png));
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Quit browser
        driver.quit();
    }
}
