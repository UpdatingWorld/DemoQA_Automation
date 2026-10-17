import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import javax.swing.*;
import java.io.File;
import java.util.List;
import java.util.Set;

public class Elements_Page {

    public static void main(String[] args) throws InterruptedException {
        ChromeDriver driver = new ChromeDriver();
        driver.get("https://demoqa.com/");

        driver.manage().window().maximize();
        //click on elements button
        driver.findElement(By.xpath("(//div[@class='card mt-4 top-card'])[1]")).click();

        Thread.sleep(2000);

        if (driver.findElement(By.xpath("//div[text()='Elements']")).isDisplayed() && driver.findElement(By.xpath("//div[@class='element-list accordion-collapse collapse show']")).isDisplayed())  {

            //click on textbox sub menu
            driver.findElement(By.xpath("//span[text()='Text Box']")).click();

            if(driver.findElement(By.xpath("//h1[text()='Text Box']")).isDisplayed())
            {
                //add username
                driver.findElement(By.id("userName")).sendKeys("Neha Phawade");

                //add email
                driver.findElement(By.id("userEmail")).sendKeys("neha@gmail.com");

                //add current address
                driver.findElement(By.id("currentAddress")).sendKeys("Pune,Maharashtra");

                //add permanent address
                driver.findElement(By.id("permanentAddress")).sendKeys("Pune,Maharashtra");

                //Click on submit button
                driver.findElement(By.id("submit")).click();

                //Verify all details
                String data = driver.findElement(By.xpath("//div[@class='border col-md-12 col-sm-12']")).getText();

                if (data.contains("Neha Phawade") & data.contains("neha@gmail.com") & data.contains("Pune,Maharashtra") & data.contains("Pune,Maharashtra")) {
                    System.out.println("All data is stored.");
                } else {
                    System.out.println("All data is not stored.");
                }
            }
            else
            {
                System.err.println("TextBox page is not opened.");
            }

            System.out.println("*******************************************************");
            //click on checkbox sub-menu
            driver.findElement(By.xpath("//span[text()='Check Box']")).click();

            if(driver.findElement(By.className("text-center")).isDisplayed())
            {
                //select main checkbox
                driver.findElement(By.className("rc-tree-checkbox")).click();

                if(!driver.findElement(By.xpath("(//span[@class='text-success'])[1]")).getText().contains("home"))
                {
                    System.err.println("Home checkbox is not checked.");
                }
                else
                {
                    System.out.println("Home checkbox is checked.");
                }

                //Expand tree of home
                driver.findElement(By.xpath("//span[@class='rc-tree-switcher rc-tree-switcher_close']")).click();

                if(!driver.findElement(By.xpath("//span[text()='Desktop']")).isDisplayed())
                {
                    System.err.println("Home tree is not expanded.");
                }
                else
                {
                    System.out.println("Home tree is expanded.");
                }
                //click on desktop checkbox and expand the tree
                driver.findElement(By.xpath("//span[@title='Desktop']/preceding-sibling::span[@class='rc-tree-switcher rc-tree-switcher_close']")).click();

                //click on documents
                driver.findElement(By.xpath("//span[@title='Documents']/preceding-sibling::span[@class='rc-tree-switcher rc-tree-switcher_close']")).click();

                //Click on WorkSpace
                driver.findElement(By.xpath("//span[@title='WorkSpace']/preceding-sibling::span[@class='rc-tree-switcher rc-tree-switcher_close']")).click();

                //Click on Office
                driver.findElement(By.xpath("//span[@title='Office']/preceding-sibling::span[@class='rc-tree-switcher rc-tree-switcher_close']")).click();

                //click on downloads
                driver.findElement(By.xpath("//span[@title='Downloads']/preceding-sibling::span[@class='rc-tree-switcher rc-tree-switcher_close']")).click();

                //verify given 3 checkboxes are showing
                String[] names = {"Desktop", "Downloads", "Documents","Notes", "Commands","WorkSpace","React","Angular","Veu","Office","Public","Private","Classified","General","Word File.doc","Excel File.doc"};

                int j=0;
                for (int i = 0; i < names.length; i++) {
                    if(driver.findElement(By.xpath("//div[@role='treeitem']/span[@title='" + names[i] + "']/span[@class='rc-tree-title']"
                    )).isDisplayed())
                    {j++;}
                }
                if(j == 16) {
                    System.out.println("All tress are expanding and all data are showing ");
                }
                else
                {
                    System.err.println("Something is missing.");
                }
            }
            else
            {
                System.err.println("Check-box page is not opened.");
            }
            System.out.println("*******************************************************");

            //click on radio-button submenu
            driver.findElement(By.xpath("//span[text()='Radio Button']")).click();

            if(driver.findElement(By.xpath("//h1[text()='Radio Button']")).isDisplayed())
            {
                //click on yes radio button
                driver.findElement(By.xpath("(//input[@class='form-check-input'])[1]")).click();

                //verify yes is selected or not
               System.out.println(driver.findElement(By.xpath("//span[@class='text-success']")).getText().equals("Yes")?" Yes radio button is selected.":"Yes radio button is not selected.");

                //click on Impressive radio button
                driver.findElement(By.xpath("(//input[@class='form-check-input'])[2]")).click();

                //verify Impressive is selected or not
                System.out.println(driver.findElement(By.xpath("//span[@class='text-success']")).getText().equals("Impressive")?"Impressive radio button is selected.":"Impressive radio button is not selected.");

                //Verify No is disabled
                System.out.println(driver.findElement(By.xpath("//label[@class='form-check-label disabled']")).isDisplayed()?"No radio button is disabled." : "No radio button is not disabled.");
            }
            else
            {
                System.err.println("Radio-Button page is not visible.");
            }

            System.out.println("*******************************************************");

            //click on WebTable submenu
            driver.findElement(By.xpath("//span[text()='Web Tables']")).click();

            if(driver.findElement(By.xpath("//h1[text()='Web Tables']")).isDisplayed())
            {
                //Verify columns in table
                System.out.println(driver.findElements(By.xpath("//table//th")).size()==7?"All columns are present in table":"Something is missing");

                String[] columns ={"First Name","Last Name","Age","Email","Salary","Department","Action"};

                for(int i=1;i<=columns.length;i++)
                {
                    if(!driver.findElement(By.xpath("//table//th ["+ i +"]")).getText().equals(columns[i-1]))
                    {
                        System.err.println("Column name " + columns[i-1] +" is not same.");
                    }
                }

                //Verify rows in table
                System.out.println(driver.findElements(By.xpath("//table//tr")).size()==4?"All rows are present in table":"Something is missing");

                //Search element from table
                String[] studName = {"Cierra","Alden", "Kierra"};
                for(int i=0 ;i<=2;i++)
                {
                    WebElement search = driver.findElement(By.id("searchBox"));
                    search.sendKeys(studName[i]);

                    driver.findElement(By.id("basic-addon2")).click();

                    if(!driver.findElement(By.xpath("//tbody//tr/td[1]")).getText().equals(studName[i]))
                    {
                        System.err.println("Properly searching element is not performed.");
                    }
                    search.sendKeys(Keys.CONTROL, "a");
                    search.sendKeys(Keys.BACK_SPACE);
                }

                Thread.sleep(2000);
                //click on add button
                driver.findElement(By.id("addNewRecordButton")).click();

                //Verify registration form is opened
                if(driver.findElement(By.id("registration-form-modal")).isDisplayed())
                {
                    //add all details of student
                    driver.findElement(By.id("firstName")).sendKeys("Raju");

                    driver.findElement(By.id("lastName")).sendKeys("Malotra");

                    driver.findElement(By.id("userEmail")).sendKeys("raju@gmail.com");

                    driver.findElement(By.id("age")).sendKeys("23");

                    driver.findElement(By.id("salary")).sendKeys("30000");

                    driver.findElement(By.id("department")).sendKeys("IT");

                    //click on submit button
                    driver.findElement(By.id("submit")).click();

                    Thread.sleep(2000);
                    int m= 0;
                    //verify student data is added in table or not
                    for(int i= 1;i<=4;i++)
                    {
                        if(driver.findElement(By.xpath("//tbody//tr["+ i +"]//td[1]")).getText().equals("Raju"))
                        {
                            System.out.println("Data is stored in table successfully.");
                            m++;
                        }
                    }
                    if(m==0)
                    {
                        System.err.println("Data is not stored in table successfully.");
                    }

                    Thread.sleep(2000);
                    //Edit student data
                    driver.findElement(By.id("edit-record-1")).click();

                    //registration is opened
                    if(driver.findElement(By.id("registration-form-modal")).isDisplayed())
                    {
                        //edit name
                        WebElement firstName = driver.findElement(By.id("firstName"));
                        firstName.sendKeys(Keys.CONTROL, "a");
                        firstName.sendKeys(Keys.BACK_SPACE);
                        firstName.sendKeys("Puja");

                        //Click on submit button
                        driver.findElement(By.id("submit")).click();

                        //verify data is edited
                        if(!driver.findElement(By.xpath("//tbody//tr[1]/td")).getText().equals("Puja"))
                        {
                            System.err.println("Edit operation is failed.");
                        }

                    }
                    else
                    {
                        System.err.println("Registration page in not open for editing data.");
                    }

                    Thread.sleep(2000);
                    //delete student record
                    driver.findElement(By.id("delete-record-4")).click();

                    //Verify record is deleted from table
                    int rows = driver.findElements(By.xpath("//tbody//tr")).size();

                    System.out.println(rows == 3?"student record is deleted successfully.":"student record is not deleted successfully.");
                  }
                else
                {
                    System.err.println("Registration form is not opened.");
                }
            }
            else
            {
                System.err.println("WebTable page is not opened.");
            }

            System.out.println("*******************************************************");

            //Click on Buttons submenu
            driver.findElement(By.xpath("//span[text()='Buttons']")).click();

            //Verify Buttons page is opened
            if(driver.findElement(By.xpath("//h1[text()='Buttons']")).isDisplayed())
            {
                //create object of action class
                Actions action = new Actions(driver);

                //Click on 'Double click me' button
                WebElement doubleClick = driver.findElement(By.id("doubleClickBtn"));
                action.doubleClick(doubleClick).perform();

                if(driver.findElement(By.id("doubleClickMessage")).isDisplayed() && driver.findElement(By.id("doubleClickMessage")).getText().equals("You have done a double click") )
                {
                    System.out.println("'Double Click' button is clicked and message is also displayed successfully.");
                }
                else
                {
                    System.err.println("Something went to 'Double Click' button");
                }

                //Click on 'Right Click' button
                WebElement rightClick = driver.findElement(By.id("rightClickBtn"));
                action.contextClick(rightClick).perform();

               if(driver.findElement(By.id("rightClickMessage")).isDisplayed() && driver.findElement(By.id("rightClickMessage")).getText().equals("You have done a right click") )
                {
                    System.out.println("'Right Click' button is clicked and message is also displayed successfully.");
                }
                else
                {
                    System.err.println("Something went to 'Right Click' button");
                }

                //Click on 'Click me' button
                WebElement clickBtn= driver.findElement(By.xpath("//button[text()='Click Me']"));
                action.click(clickBtn).perform();

                if(driver.findElement(By.id("dynamicClickMessage")).isDisplayed() && driver.findElement(By.id("dynamicClickMessage")).getText().equals("You have done a dynamic click") )
                {
                    System.out.println("'Click me' button is clicked and message is also displayed successfully.");
                }
                else
                {
                    System.err.println("Something went to 'Click me' button");
                }
            }
            else
            {
                System.err.println("Buttons page is not opened.");
            }

            System.out.println("*******************************************************");
            // click on links submenu
            driver.findElement(By.xpath("//span[text()='Links']")).click();

            //verify links page is opened
            if(driver.findElement(By.xpath("//h1[text()='Links']")).isDisplayed())
            {

                // Store the first tab handle
                String firstTab = driver.getWindowHandle();

                //Click on 'Home' link
                driver.findElement(By.id("simpleLink")).click();

                //click on dynamic link
                driver.findElement(By.id("dynamicLink")).click();

                int count = driver.getWindowHandles().size();
                System.out.println(count == 3 ? "Both link buttons are working properly.":"link buttons are not working properly");

                //close both tabs
                Set<String> handles = driver.getWindowHandles();

                for (String handle : handles) {
                    if (!handle.equals(firstTab)) {
                        driver.switchTo().window(handle);
                        driver.close();
                    }
                }

                // Switch back to the first tab
                driver.switchTo().window(firstTab);

                String data[] ={"Created","No Content","Moved Permanently","Bad Request","Unauthorized","Forbidden","Not Found"};
                String ids[] = {"created","no-content","moved","bad-request","unauthorized","forbidden","invalid-url"};

                for (int i =0 ;i<data.length ; i++)
                {
                    //click on 'created' link
                    driver.findElement(By.id(ids[i])).click();

                    System.out.println(driver.findElement(By.id(ids[i])).getText().contains(data[i])?data[i] + "Link response message showed correctly.":data[i] + "Link response message is not show correctly.");

                }
            }
            else
            {
                System.err.println("Links page is not opened.");
            }

            System.out.println("*******************************************************");
            // scroll down the page
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(200, 500)");

            //Click 'Broken links-images' on submenu
            driver.findElement(By.xpath("//span[text()='Broken Links - Images']")).click();

            //verify Broken Links - Images page os opened.
            if(driver.findElement(By.xpath("//h1[text()='Broken Links - Images']")).isDisplayed())
            {
                // Find all images on the page
                List<WebElement> images = driver.findElements(By.xpath("//p[normalize-space()='Valid image' or " +"normalize-space()='Broken image']/following-sibling::img"));

                System.out.println("Total images: " + images.size());

                for (WebElement img : images) {

                    String imageURL = img.getAttribute("src");

                    Thread.sleep(2000);
                    Boolean isImageLoaded = (Boolean)
                            ((JavascriptExecutor) driver).executeScript(
                                    "return arguments[0].complete && " +
                                            "arguments[0].naturalWidth > 0;", img);

                    if (isImageLoaded) {
                        System.out.println("Valid Image: " + imageURL);
                    } else {
                        System.out.println("Broken Image: " + imageURL);
                    }
                }

                //Click on valid link
                driver.findElement(By.xpath("//a[text()='Click Here for Valid Link']")).click();

                Thread.sleep(2000);
                if(driver.findElement(By.xpath("//img[@alt='Selenium Online Training']")).isDisplayed())
                {
                    System.out.println("Valid page is opened.");
                    driver.navigate().back();
                }
                else
                {
                    System.err.println("Valid link is not clicked or valid page is not opened.");
                }

                //Click on invalid link
                driver.findElement(By.xpath("//a[text()='Click Here for Broken Link']")).click();

                driver.navigate().back();
            }
            else
            {
                System.err.println("'Broken links-images' page is not opened.");
            }

            System.out.println("*******************************************************");
            //Click on 'Upload - download' submenu
            driver.findElement(By.xpath("//span[text()='Upload and Download']")).click();

            //Verify 'Upload-download' page is opened
            if(driver.findElement(By.xpath("//h1[text()='Upload and Download']")).isDisplayed())
            {
                // Click on download button
                driver.findElement(By.id("downloadButton")).click();

                String path = "C:\\Users\\Neha\\Downloads\\sampleFile.jpeg";

                File file = new File(path);

                int i = 0;
                while (i < 15 && !file.exists()) {
                    Thread.sleep(1000);
                    i++;
                }

                if (file.exists()) {
                    System.out.println("File downloaded successfully");
                } else {
                    System.out.println("File not downloaded");
                }

                //Upload the file
                WebElement upload = driver.findElement(By.id("uploadFile"));

                // Upload the file using its absolute path
                upload.sendKeys("C:\\Users\\Neha\\Downloads\\Java_Collections_In_Depth_Notes.pdf");

                System.out.println(driver.findElement(By.id("uploadedFilePath")).isDisplayed()?" File uploaded successfully.":"File uploaded not done successfully.");
            }
            else
            {
                System.err.println("'Upload- download' page is not opened.");
            }

            System.out.println("*******************************************************");

            // scroll down the page
            js.executeScript("window.scrollBy(200, 500)");

            // Click on 'Dynamic Properties' submenu
            driver.findElement(By.xpath("//span[text()='Dynamic Properties']")).click();

            //Verify 'Dynamic Properties' page is opened
            if(driver.findElement(By.xpath("//h1[text()='Dynamic Properties']")).isDisplayed())
            {
                //Check buttons before 5 seconds
                System.out.println(driver.findElement(By.id("enableAfter")).isEnabled()?"'Enable after' button is not disabled.":"'Enable after' button is disabled.");
                String color = driver.findElement(By.id("colorChange")).getCssValue("color");

                //Check buttons after 5 seconds
                Thread.sleep(5000);
                System.out.println(driver.findElement(By.id("enableAfter")).isEnabled()?"'Enable after' button is enabled.":"'Enable after' button is not enabled.");
                String color1 = driver.findElement(By.id("colorChange")).getCssValue("color");
                System.out.println(!color.equals(color1)?"'Change color' button's color is changed.":"'Change color' button's color is not changed.");
                System.out.println(driver.findElement(By.id("visibleAfter")).isDisplayed()?" 'Visible after 5 seconds' button is visible after 5 second. ":"'Visible after 5 seconds' button is not visible after 5 second.");
            }
            else
            {
                System.err.println(" 'Dynamic Properties' page is opened successfully.");
            }

        }
        else
        {
            System.err.println("Elements page is not open");
        }
    }
}
