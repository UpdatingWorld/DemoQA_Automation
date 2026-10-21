import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BookStoreApplication {

    public static void main(String[] args) throws InterruptedException {
        ChromeDriver driver = new ChromeDriver();
        driver.get("https://demoqa.com/");

        driver.manage().window().maximize();

        //click on Interactions module
        driver.findElement(By.xpath("(//div[@class='card mt-4 top-card'])[6]")).click();

        Thread.sleep(2000);

        //click on Login submenu
        driver.findElement(By.xpath("//span[text()='Login']")).click();

        JavascriptExecutor js = (JavascriptExecutor)driver;
        //Verify Login page is opened
        if (driver.findElement(By.xpath("//h1[text()='Login']")).isDisplayed()) {
            //Click on new user
            driver.findElement(By.id("newUser")).click();

            //Verify register page is opened
            if (driver.findElement(By.xpath("//form[@id='userForm']//h4")).getText().equals("Register to Book Store"))
            {
                //Add details
                driver.findElement(By.id("firstname")).sendKeys("Abhinavt");
                driver.findElement(By.id("lastname")).sendKeys("Chatappty");
                driver.findElement(By.id("userName")).sendKeys("Abhinn");
                driver.findElement(By.id("password")).sendKeys("AbhiChatapvp@.1101");

                //click on register button
                driver.findElement(By.id("register")).click();

                // Check whether alert is present
                try
                {
                    driver.switchTo().alert();
                    System.out.println("Alert is present.");
                }
                catch (NoAlertPresentException e)
                {
                    System.out.println("Alert is not present.");
                }

                js.executeScript("window.scrollBy(400,700)");

                //back to login
                driver.findElement(By.id("gotologin")).click();

                //Verify user form is open
                if(driver.findElement(By.id("userForm")).isDisplayed())
                {
                    //add login details
                    driver.findElement(By.id("userName")).sendKeys("Abhinn");
                    driver.findElement(By.id("password")).sendKeys("AbhiChatapvp@.1101");

                    //click on login button
                    driver.findElement(By.id("login")).click();
                }
                else
                {
                    System.err.println("user form is not opened.");
                }
            }
            else
            {
                System.err.println("register page is not opened.");
            }

        }
        else
        {
            System.err.println("Login page is not opened.");
        }

        //click on Book Store submenu
        driver.findElement(By.xpath("//span[text()='Book Store']")).click();

        Thread.sleep(3000);
        //Verify Book Store page is opened
        if (driver.findElement(By.xpath("(//tbody//td[3])[4]")).isDisplayed())
        {
            //search book name
            driver.findElement(By.id("searchBox")).sendKeys("Git Pocket Guide");

            //Verify Searched bok is showing
            if(driver.findElement(By.xpath("//span[@class='mr-2']/a")).getText().equals("Git Pocket Guide"))
            {
                System.out.println("Searched book is showing in list.");
            }
            else
            {
                System.err.println("Searched book is not showing in list.");
            }

            WebElement textbox = driver.findElement(By.id("searchBox"));
            textbox.sendKeys(Keys.CONTROL, "a", Keys.DELETE);

            String data[] = {"Title","Author","Publisher"};
            String locators[] = {"//div[@class='action-buttons']//a","//tbody//td[3]","//tbody//td[4]"};
            String singlelocator[]={"//span[text()='Title']","//span[text()='Author']","//span[text()='Publisher']"};

            for(int i=0;i<3;i++)
            {
                //click on title and amke it accessing order
                driver.findElement(By.xpath(singlelocator[i])).click();

                // Fetch all titles
                List<WebElement> elements = driver.findElements(By.xpath(locators[i]));

                List<String> actualTitles = new ArrayList<>();

                for (WebElement e : elements) {
                    actualTitles.add(e.getText());
                }

                // Create a sorted list
                List<String> sortedTitles = new ArrayList<>(actualTitles);
                Collections.sort(sortedTitles, String.CASE_INSENSITIVE_ORDER);

                // Verify ascending order
                if (actualTitles.equals(sortedTitles)) {
                    System.out.println(data[i]+" : are in ascending order");
                } else {
                    System.out.println(data[i]+" : are not in ascending order");
                }

                Thread.sleep(2000);

                //click on title and amke it accessing order
                driver.findElement(By.xpath(singlelocator[i])).click();

                // Fetch titles again after descending sort
                elements = driver.findElements(By.xpath(locators[i]));

                actualTitles.clear();

                for (WebElement e : elements) {
                    actualTitles.add(e.getText());
                }

                // Sort expected titles in descending order
                sortedTitles = new ArrayList<>(actualTitles);
                sortedTitles.sort(Collections.reverseOrder(String.CASE_INSENSITIVE_ORDER));

                // Verify descending order
                if (actualTitles.equals(sortedTitles)) {
                    System.out.println(data[i] + " : are in descending order");
                } else {
                    System.out.println(data[i] + " : are not in descending order");
                }
            }
        }
        else
        {
            System.err.println("Book store page is not open.");
        }
     }
}