package TestCase;

import POM.LoginPage;
import driver.driverFactory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import java.io.File;

public class TC07 {
    WebDriver driver = driverFactory.getChromeDriver();

    @Test
    public void testTC07(){
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

            Thread.sleep(5000);

            //4. Click on 'My Orders'
            driver.findElement(By.linkText("MY ORDERS")).click();
            //5. Click on 'View Order'
            driver.findElement(By.xpath("//tr[@class='first odd']//a[contains(text(),'View Order')]")).click();
            //6. Click on 'Print Order' link
            driver.findElement(By.xpath("//a[@class='link-print']")).click();

            // switching to new window
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }

            File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

            String png = ("C:\\Users\\Admin\\Desktop\\SWT301\\selenium-webdriver-java-master\\src\\test\\java\\TestCase\\screenshots\\TestCase07.png");
            FileUtils.copyFile(scrFile, new File(png));
        }catch(Exception ex){
            ex.printStackTrace();
        }

        driver.quit();
    }
}