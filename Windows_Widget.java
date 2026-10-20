import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Windows_Widget {
    public static void main(String[] args) throws InterruptedException {
        ChromeDriver driver = new ChromeDriver();
        driver.get("https://demoqa.com/");

        driver.manage().window().maximize();

        //click on Windows module
        driver.findElement(By.xpath("(//div[@class='card mt-4 top-card'])[4]")).click();

        Thread.sleep(2000);

        //click on Accordian submenu
        driver.findElement(By.xpath("//span[text()='Accordian']")).click();

        //Verify Accordian page is opened
        if (driver.findElement(By.xpath("//h1[text()='Accordian']")).isDisplayed())
        {
            String[] data = {"What is Lorem Ipsum?","Where does it come from?","Why do we use it?"};

            for(int i=0;i<3;i++)
            {
                if(i!=0)
                {
                    //open module
                    driver.findElement(By.xpath("(//button[@class='accordion-button collapsed'])["+ (i+1) +"]")).click();

                }
                // Verify  module is opened
                if(driver.findElement(By.xpath("//h2[@class='accordion-header']/button[@aria-expanded='true']")).isDisplayed())
                {
                    System.out.println(data[i]+ " module is opened.");
                }
                else
                {
                    System.err.println(data[i] +" module is not opened.");
                }

                //close module
                driver.findElement(By.xpath("//button[@class='accordion-button']")).click();

                // Verify  module is opened
                if(driver.findElement(By.xpath("//h2[@class='accordion-header']/button[@aria-expanded='false']")).isDisplayed())
                {
                    System.out.println(data[i]+ " module is closed.");
                }
                else
                {
                    System.err.println(data[i] +" module is not closed.");
                }
            }
        }
        else
        {
            System.err.println("Accordian page is not opened.");
        }

        System.out.println("*********************************************");
        //click on Auto Complete submenu
        driver.findElement(By.xpath("//span[text()='Auto Complete']")).click();

        //Verify Auto Complete page is opened
        if (driver.findElement(By.xpath("//h1[text()='Auto Complete']")).isDisplayed())
        {
            Thread.sleep(2000);
            String[] colors={"Green","Yellow","Purple","Red"};

            // Locate the actual input field
            WebElement color = driver.findElement(By.xpath("(//input[@class='auto-complete__input'])[1]"));

            // Select multiple colors
            for (String c : colors) {
                color.sendKeys(c);
                color.sendKeys(Keys.ENTER);
            }

            int m=0;
            //verify all colors are entered
            for(int i =0;i<4;i++)
            {
               String ActualTest=  driver.findElement(By.xpath("(//div[@class='auto-complete__multi-value__label css-9jq23d'])["+ (i+1) +"]")).getText();
                if(!ActualTest.equals(colors[i]))
                {
                    System.err.println(colors[i]+ " color is not added.");
                    m++;
                }
            }
            if (m==0)
            {
                System.out.println("All data is added successfully.");
            }

            //Add sing data in textbox
            WebElement Singlecolor = driver.findElement(By.xpath("(//input[@class='auto-complete__input'])[2]"));
            Singlecolor.sendKeys("Yellow");
            Singlecolor.sendKeys(Keys.ENTER);
            Singlecolor.sendKeys("Green");
            Singlecolor.sendKeys(Keys.ENTER);

            //Verify how many colors are added
            System.out.println("Added color is : "+driver.findElement(By.xpath("//div[@class='auto-complete__single-value css-1dimb5e-singleValue']")).getText());
        }
        else
        {
            System.err.println("'Auto Complete' page is not opened.");
        }

        System.out.println("*********************************************");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(400,500)");

        //click on date picker submenu
        driver.findElement(By.xpath("//span[text()='Date Picker']")).click();

        //Verify date picker page is opened
        if (driver.findElement(By.xpath("//h1[text()='Date Picker']")).isDisplayed()) {

            //Click on select date options
            driver.findElement(By.id("datePickerMonthYearInput")).click();

            //Select date
            Select sc= new Select(driver.findElement(By.className("react-datepicker__month-select")));
            sc.selectByVisibleText("May");

            Select year = new Select(driver.findElement(By.className("react-datepicker__year-select")));
            year.selectByVisibleText("2001");

            driver.findElement(By.xpath("(//div[text()='30'])[2]")).click();

            //enter select date options
            driver.findElement(By.id("dateAndTimePickerInput")).click();

            //select month
            driver.findElement(By.className("react-datepicker__month-read-view")).click();
            driver.findElement(By.xpath("//div[text()='May']")).click();

            //select year
            driver.findElement(By.className("react-datepicker__year-read-view--down-arrow")).click();
            driver.findElement(By.xpath("//div[text()='2021']")).click();

            //select date,time
            driver.findElement(By.xpath("//div[text()='11']")).click();
            driver.findElement(By.xpath("//li[text()='18:00']")).click();

            //Verify date
            String date2 = driver.findElement(By.id("dateAndTimePickerInput")).getAttribute("value");

            //Click on select date options
            String date = driver.findElement(By.id("datePickerMonthYearInput")).getAttribute("value");

            if(date.equals("05/30/2001") && date2.equals("May 11, 2021 6:00 PM"))
            {
                System.out.println("Date and time stored accurately.");
            }
        }
        else
        {
            System.err.println("Date picker page is not opened.");
        }

        js.executeScript("window.scrollBy(600,500)");

        //click on Slider submenu
        driver.findElement(By.xpath("//span[text()='Slider']")).click();

        //Verify Slider page is opened
        if (driver.findElement(By.xpath("//h1[text()='Slider']")).isDisplayed())
        {
            //Define value in slider
            WebElement slider = driver.findElement(By.id("slider"));
            System.out.println("Minimum: " + slider.getAttribute("min"));
            System.out.println("Maximum: " + slider.getAttribute("max"));
            System.out.println("Current value: " + slider.getAttribute("value"));
        }
        else
        {
            System.out.println("Slider page is not open.");
        }

        System.out.println("*********************************************");
        //click on Progress Bar submenu
        driver.findElement(By.xpath("//span[text()='Progress Bar']")).click();

        //Verify Progress Bar page is opened
        if (driver.findElement(By.xpath("//h1[text()='Progress Bar']")).isDisplayed())
        {
            WebElement progress = driver.findElement(By.xpath("//div[@role='progressbar']"));

            System.out.println("Progress: " + progress.getAttribute("aria-valuenow") + "%");

            //clcik on start button
            driver.findElement(By.xpath("//button[@class='mt-3 btn btn-primary']")).click();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait.until(d -> d.findElement(By.xpath("//div[@role='progressbar']")).getAttribute("aria-valuenow").equals("100"));

            if( progress.getAttribute("aria-valuenow").equals("100"))
            {
                System.out.println("Progress is completed at 100%");
            }
            else
            {
                System.err.println("Progress is not completed at 100%");
            }

            //click on reset button
            driver.findElement(By.xpath("//button[@class='mt-3 btn btn-primary']")).click();

            Thread.sleep(2000);
            if( progress.getAttribute("aria-valuenow").equals("0"))
            {
                System.out.println("Progress bar is reset successfully.");
            }
            else
            {
                System.err.println("Progress bar is not reset");
            }
        }
        else
        {
            System.err.println("Progress Bar is not opened.");
        }

        System.out.println("*********************************************");
        //click on Tabs submenu
        driver.findElement(By.xpath("//span[text()='Tabs']")).click();

        //Verify Tabs page is opened
        if (driver.findElement(By.xpath("//h1[text()='Tabs']")).isDisplayed())
        {
            //Verify 'What' tab is opened
            if(driver.findElement(By.xpath("(//li[@class='nav-item'])[1]/button[@aria-selected='true']")).isDisplayed())
            {
                System.out.println("What tab is opened by default.");
            }
            else
            {
                System.out.println("What tab is not opened by default.");
            }

            //Open origin tab
            driver.findElement(By.id("demo-tab-origin")).click();

            //verify origin tab is opened
            if(driver.findElement(By.xpath("(//li[@class='nav-item'])[2]/button[@aria-selected='true']")).isDisplayed())
            {
                System.out.println("Origin tab is opened.");
            }
            else
            {
                System.out.println("Origin tab is not opened.");
            }

            //click on Use tab
            driver.findElement(By.id("demo-tab-use")).click();

            //verify use tab is opened
            if(driver.findElement(By.xpath("(//li[@class='nav-item'])[3]/button[@aria-selected='true']")).isDisplayed())
            {
                System.out.println("Use tab is opened.");
            }
            else
            {
                System.out.println("Use tab is not opened.");
            }

            Thread.sleep(2000);
            //click on More tab
            String value = driver.findElement(By.id("demo-tab-more")).getAttribute("aria-disabled");

            //verify More tab is opened
            if(value.equals("true"))
            {
                System.out.println("More tab is disabled by default.");
            }
            else
            {
                System.out.println("More tab is not disabled by default.");
            }
        }
        else
        {
            System.err.println("Tabs page is not opened.");
        }

        System.out.println("*********************************************");
        js.executeScript("window.scrollBy(600,500)");

        //click on Tool Tips submenu
        driver.findElement(By.xpath("//span[text()='Tool Tips']")).click();

        // Verify Tool Tips page is opened
        if (driver.findElement(By.xpath("//h1[text()='Tool Tips']")).isDisplayed())
         {
                     String[] locators = {
                    "toolTipButton",
                    "toolTipTextField",
                    "//a[text()='Contrary']",
                    "//a[text()='1.10.32']"
            };

            Actions action = new Actions(driver);

            for (int i = 0; i < locators.length; i++) {

                WebElement element;

                // Find element using the correct locator type
                if (i < 2) {
                    element = driver.findElement(By.id(locators[i]));
                } else {
                    element = driver.findElement(By.xpath(locators[i]));
                }

                // Hover over element
                action.moveToElement(element).perform();

                WebDriverWait wait =
                        new WebDriverWait(driver, Duration.ofSeconds(5));

                // Wait until aria-describedby appears
                String id = wait.until(d ->
                        element.getAttribute("aria-describedby")
                );

                System.out.println("Tooltip ID: " + id);

                // Find tooltip element and get text
                WebElement tooltip = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));

                System.out.println(
                        "Tooltip text: " + tooltip.getText()
                );
            }
        }
        else
        {
            System.err.println("Tool Tips page is not opened.");
        }

        System.out.println("*********************************************");

        js.executeScript("window.scrollBy(800,800)");
        //click on Menu submenu
        driver.findElement(By.xpath("//span[text()='Menu']")).click();

        //Verify Menu page is opened
        if (driver.findElement(By.xpath("//h1[text()='Menu']")).isDisplayed())
        {
            System.out.println("Menu names are :");
            //Describe all menu names
            for(int i=0;i<=2;i++)
            {
                System.out.println(driver.findElement(By.xpath("//ul[@id='nav']/li[" + (i + 1) + "]/a[@href='#']")).getText());
            }

            System.out.println("Submenu names of 'Menu Item2' are :");
            //Show sub menu items
            List<WebElement> submenus = driver.findElements(By.xpath("//ul[@id='nav']/li[2]/ul/li/a"));
            System.out.println("Submenu count: " + submenus.size());
            for (WebElement item : submenus) {
                System.out.println(((JavascriptExecutor) driver).executeScript("return arguments[0].innerText;", item));
            }

            System.out.println("Submenu names of 'Sub sub list' are :");
            //Submenu names are
            List<WebElement> submenu = driver.findElements(By.xpath("//ul[@id='nav']/li[2]/ul/li[3]/ul/li[\"+(i + 1)+\"]/a"));
            System.out.println("Submenu count: " + submenu.size());
            for (WebElement item : submenu) {
                System.out.println(((JavascriptExecutor) driver).executeScript("return arguments[0].innerText;", item));
            }

        }
        else
        {
            System.err.println("Menu page is not opened.");
        }
        System.out.println("*********************************************");

        //click on Select Menu submenu
        driver.findElement(By.xpath("//span[text()='Select Menu']")).click();

        //Verify Select Menu page is opened
        if (driver.findElement(By.xpath("//h1[text()='Select Menu']")).isDisplayed())
        {
            Thread.sleep(3000);

            //Add value in select box
            WebElement value = driver.findElement(By.xpath("(//div[@class='css-19bb58m'])[1]/input"));
            value.sendKeys("Group 1, option 1");
            value.sendKeys(Keys.ENTER);

            //verify selected value is stored in select box
            if(driver.findElement(By.xpath("(//div[@class='css-1dimb5e-singleValue'])[1]")).getText().equals("Group 1, option 1"))
            { System.out.println("'Group 1, option 1' value is added 'Select value' select box.");
            }
            else
            {System.err.println("'Group 1, option 1' value is not added 'Select value' select box.");
            }

            //Add value in 'select one' select box
            WebElement one = driver.findElement(By.xpath("(//div[@class='css-19bb58m'])[2]/input"));
            one.sendKeys("Ms.");
            one.sendKeys(Keys.ENTER);

            //verify selected value is stored in select box
            if(driver.findElement(By.xpath("(//div[@class='css-1dimb5e-singleValue'])[2]")).getText().equals("Ms."))
            { System.out.println("'Ms.' value is added 'Select one' select box.");
            }
            else
            {System.err.println("'Ms.' value is not added 'Select one' select box.");
            }

            //Select color
            Select sc =new Select(driver.findElement(By.id("oldSelectMenu")));
            sc.selectByVisibleText("Purple");

            //verify selected value is stored in select box
            if(sc.getFirstSelectedOption().getText().equals("Purple"))
            { System.out.println("'Purple' value is added 'Old Style Select Menu' select box.");
            }
            else
            {System.err.println("'Purple' value is not added 'Old Style Select Menu' select box.");
            }

            //Add multiple values in select box
            WebElement colors = driver.findElement(By.xpath("(//div[@class='css-19bb58m'])[3]/input"));
            colors.sendKeys("Green");
            colors.sendKeys(Keys.ENTER);
            colors.sendKeys("Blue");
            colors.sendKeys(Keys.ENTER);

            //verify selected value is stored in select box
            WebElement color1 =driver.findElement(By.xpath("(//div[@class='css-9jq23d'])[1]"));
            WebElement color2 =driver.findElement(By.xpath("(//div[@class='css-9jq23d'])[2]"));
            if(color1.getText().equals("Green") && color2.getText().equals("Blue"))
            { System.out.println("'Green','Blue' value is added 'Multiselect drop down' select box.");
            }
            else
            {System.err.println("'Green','Blue' value is not added 'Multiselect drop down' select box.");
            }

            //Select any car type
            Select car = new Select(driver.findElement(By.id("cars")));
            car.selectByVisibleText("Opel");

            if(car.getFirstSelectedOption().getText().equals("Opel"))
            { System.out.println("'Opel' value is added 'Standard multi select' select box.");
            }
            else
            {System.err.println("'Opel' value is not added 'Standard multi select' select box.");
            }
        }
        else
        {
            System.err.println("Select Menu page is not open.");
        }

    }
}