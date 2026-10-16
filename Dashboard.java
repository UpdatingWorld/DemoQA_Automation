import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Dashboard {

    public static void main(String[] args) throws InterruptedException {
        ChromeDriver driver = new ChromeDriver();
        driver.get("https://demoqa.com/");

        driver.manage().window().maximize();
        Thread.sleep(3000);
        if(driver.findElement(By.xpath("//img[@alt ='Selenium Online Training']")).isDisplayed())
        {
            //
            int count = driver.findElements(By.xpath("//div[@class='card mt-4 top-card']")).size();
            System.out.println("Count : "+count);
            if(count == 6)
            {
                String[] headings = {
                        "//div[text()='Elements']",
                        "//div[text()='Forms']",
                        "//div[text()='Alerts, Frame & Windows']",
                        "//div[text()='Widgets']",
                        "//div[text()='Interactions']",
                        "//div[text()='Book Store Application']"
                };
                String[] pageNames = {
                        "Elements",
                        "Forms",
                        "Alerts,Framework & Windows",
                        "Widgets",
                        "Interactions",
                        "Book Store Application"
                };
                for (int j = 1, i = 0; j <= 6 && i < headings.length ; j++, i++) {
                    JavascriptExecutor js = (JavascriptExecutor) driver;

                    // Scroll down by 500 pixels
                    js.executeScript("window.scrollBy(200, 500)");

                    // Click dashboard quick-launch button
                    driver.findElement(By.xpath("(//div[@class='card mt-4 top-card'])[" + j + "]")).click();

                    Thread.sleep(3000);

                    String xpath1 = "boolean(" + headings[i] + ") and boolean(//div[@class='element-list accordion-collapse collapse show'])";

                    WebElement locator = driver.findElement(By.xpath("//*[ " + xpath1 + " ]"));

                    if (locator.isDisplayed()) {
                        System.out.println(pageNames[i] + " page is visible. ");
                    } else {
                        System.out.println(pageNames[i] + " page is not visible. ");
                    }
                    driver.navigate().back();
                }
            }
            else
            {
                System.out.println("Some modules are missing or new added.");
            }

        }
        else
        {
            System.out.println("Page is not loaded. ");
        }

    }

}
