import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Alert_Frame {
    public static void main(String[] args) throws InterruptedException {
        ChromeDriver driver = new ChromeDriver();
        driver.get("https://demoqa.com/");

        driver.manage().window().maximize();

        //click on Forms module
        driver.findElement(By.xpath("(//div[@class='card mt-4 top-card'])[3]")).click();

        Thread.sleep(2000);

        //click on Browser windows submenu
        driver.findElement(By.xpath("//span[text()='Browser Windows']")).click();

        //Verify Browser windows page is opened
        if(driver.findElement(By.xpath("//h1[text()='Browser Windows']")).isDisplayed())
        {
            String first = driver.getWindowHandle();

            //Click on 'New Tab' button
            driver.findElement(By.id("tabButton")).click();

            //Click on 'New Tab' button
            driver.findElement(By.id("windowButton")).click();

            //Click on 'New Window message' button
            driver.findElement(By.id("messageWindowButton")).click();

            int count = driver.getWindowHandles().size();
            if(count == 4)
            {
                System.out.println("All new tab and windows are opened.");
            }
            else
            {
                System.err.println("All new tab and windows are not opened.");
            }

            // Get all window handles
            Set<String> windows = driver.getWindowHandles();

            // Close all windows except the first window
            for (String window : windows) {
                if (!window.equals(first)) {
                    driver.switchTo().window(window);
                    driver.close();
                }
            }
            // Switch back to the first window
            driver.switchTo().window(first);
        }
        else
        {
            System.err.println("Browser windows page is not opened.");
        }

        System.out.println("*************************************************");

        //Click on 'Alerts' submenu
        driver.findElement(By.xpath("//span[text()='Alerts']")).click();

        Thread.sleep(2000);
        //Verify alert page is opened
        if(driver.findElement(By.xpath("//h1[text()='Alerts']")).isDisplayed())
        {

            //click on alert button
            driver.findElement(By.id("alertButton")).click();
            Alert a = driver.switchTo().alert();
            a.accept();

            //Click on alert button
            driver.findElement(By.id("timerAlertButton")).click();
            Thread.sleep(5000);
            a.accept();

            //Click on alert button and then submit button
            driver.findElement(By.id("confirmButton")).click();
            a.accept();
            if(driver.findElement(By.id("confirmResult")).getText().contains("Ok"))
            {System.out.println(" Ok is selected in alert.");
            }
            else
            {System.err.println("Ok is not selected in alert");}

            //CLick ona alert button and add data
            driver.findElement(By.id("promtButton")).click();
            a.sendKeys("Neha");
            a.accept();
            if(driver.findElement(By.id("promptResult")).getText().contains("Neha"))
            {
                System.out.println("Data is added successfully in prompt.");
            }
            else
            {
                System.err.println("Data is not added in prompt.");
            }
        }
        else
        {
            System.err.println("Alerts page is not opened.");
        }

        System.out.println("*************************************************");

        //Click on 'Frame' submenu
        driver.findElement(By.xpath("//span[text()='Frames']")).click();

        Thread.sleep(2000);
        //Verify frame page is opened
        if(driver.findElement(By.xpath("//h1[text()='Frames']")).isDisplayed())
        {
            //Switch to first iframe
            driver.switchTo().frame(driver.findElement(By.id("frame1")));

            String mess = driver.findElement(By.id("sampleHeading")).getText();
            System.out.println(mess + " : string is present in first frame.");

            //go back to main window
            driver.switchTo().parentFrame();

            //Switch to second iframe
            driver.switchTo().frame(driver.findElement(By.id("frame2")));

            String mess1 = driver.findElement(By.id("sampleHeading")).getText();
            System.out.println(mess1 + " : string is present in second frame.");

            //go back to main window
            driver.switchTo().defaultContent();
        }
        else
        {
            System.err.println("Frame page is not opened.");
        }

        System.out.println("*************************************************");

        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("window.scrollBy(200,500)");

        //Click on 'nasted Frame' submenu
        driver.findElement(By.xpath("//span[text()='Nested Frames']")).click();

        Thread.sleep(2000);
        //Verify nested frame page is opened
        if(driver.findElement(By.xpath("//h1[text()='Nested Frames']")).isDisplayed())
        {
            //Switch to first iframe
            driver.switchTo().frame(driver.findElement(By.id("frame1")));

            //switch to nested framework
            driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@srcdoc='<p>Child Iframe</p>']")));

            //Print data
            System.out.println(driver.findElement(By.xpath("//p[text()='Child Iframe']")).getText()+" : string present in nested frame.");

            //Switch to parent frame
            driver.switchTo().parentFrame();

            //print data of first frame
            System.out.println(driver.findElement(By.xpath("//*[contains(text(), 'Parent frame')]")).getText() +" : String prsent in first farme");

            //Switch to parent frame
            driver.switchTo().parentFrame();
        }
        else
        {
            System.err.println("Nested frame is not open.");
        }

        System.out.println("*************************************************");

        js.executeScript("window.scrollBy(200,500)");

        //Click on 'nasted Frame' submenu
        driver.findElement(By.xpath("//span[text()='Modal Dialogs']")).click();

        Thread.sleep(2000);
        //Verify nested frame page is opened
        if(driver.findElement(By.xpath("//h1[text()='Modal Dialogs']")).isDisplayed())
        {
            // Click the button to open the modal
            driver.findElement(By.id("showSmallModal")).click();
            System.out.println(driver.findElement(By.className("modal-body")).getText() + " :small module data");
            driver.findElement(By.id("closeSmallModal")).click();

            //Click on 'large model' button
            driver.findElement(By.id("showLargeModal")).click();
            System.out.println(driver.findElement(By.className("modal-body")).getText() + " : large module data");
            driver.findElement(By.id("closeLargeModal")).click();
        }
        else
        {
            System.err.println("Model dialogs page is not opened.");
        }
    }
}
