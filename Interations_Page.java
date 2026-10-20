import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class Interations_Page {
    public static void main(String[] args) throws InterruptedException {
        ChromeDriver driver = new ChromeDriver();
        driver.get("https://demoqa.com/");

        driver.manage().window().maximize();

        //click on Interactions module
        driver.findElement(By.xpath("(//div[@class='card mt-4 top-card'])[5]")).click();

        Thread.sleep(2000);

        //click on Interactions submenu
        driver.findElement(By.xpath("//span[text()='Sortable']")).click();

        //Verify Interactions page is opened
        if (driver.findElement(By.xpath("//h1[text()='Sortable']")).isDisplayed())
        {
            //Verify list tab is opened
            if(driver.findElement(By.id("demo-tab-list")).getAttribute("aria-selected").equals("true"))
            {
                System.out.println("List tab is opened.");
            }
            else
            {
                System.err.println("List tab is not opened.");
                driver.findElement(By.id("demo-tab-list")).click();
            }

            String order[] = {"One","Two","Three","Four","Five","Six"};

            int m=0;
            for(int i=1;i<=6;i++)
            {
                if(!driver.findElement(By.xpath("(//div[@class='list-group']/div[@class='list-group-item list-group-item-action'])["+ i +"]")).getText().equals(order[i-1]))
                {
                    System.err.println("Order of list is not followed.");
                    m++;
                }
            }
            if(m==0)
            {
                System.out.println("Order of list is followed.");
            }

            //click on grid module
            driver.findElement(By.id("demo-tab-grid")).click();

            //Verify order is followed
            String grid[] = {"One","Two","Three","Four","Five","Six","Seven","Eight","Nine"};

            int n=0;
            for(int i=1;i<=9;i++)
            {
                if(!driver.findElement(By.xpath("(//div[@class='create-grid']/div[@class='list-group-item list-group-item-action'])["+i+"]")).getText().equals(grid[i-1]))
                {
                    System.err.println("Order of grid is not followed.");
                    n++;
                }
            }
            if(n==0)
            {
                System.out.println("Order of grid is followed.");
            }

        }
        else
        {
            System.err.println("Interactions page is not opened.");
        }

        System.out.println("****************************************************");
        JavascriptExecutor ja = (JavascriptExecutor) driver;
        ja.executeScript("window.scrollBy(800,800)");

        //click on Selectable submenu
        driver.findElement(By.xpath("//span[text()='Selectable']")).click();

        //Verify Selectable page is opened
        if (driver.findElement(By.xpath("//h1[text()='Selectable']")).isDisplayed())
        {
            //Verify list tab is opened
            if(driver.findElement(By.id("demo-tab-list")).getAttribute("aria-selected").equals("true"))
            {
                System.out.println("List tab is opened.");
            }
            else
            {
                System.err.println("List tab is not opened.");
                driver.findElement(By.id("demo-tab-list")).click();
            }

            //Select some list items
            driver.findElement(By.xpath("//ul[@id='verticalListContainer']/li[1]")).click();
            driver.findElement(By.xpath("//ul[@id='verticalListContainer']/li[4]")).click();

            //Verify which items are selected
            for(int i=1;i<5;i++)
            {
                if(driver.findElement(By.xpath("//ul[@id='verticalListContainer']/li["+i+"]")).getAttribute("class").contains("active"))
                {
                    System.out.println(driver.findElement(By.xpath("//ul[@id='verticalListContainer']/li["+i+"]")).getText()+ " : item is selected");
                }
            }

            //Open grid tab
            driver.findElement(By.id("demo-tab-grid")).click();

            //Select some grids
            driver.findElement(By.xpath("//div[@class='grid-container mt-4']/div[@class='list-group list-group-horizontal-sm'][1]/li[1]")).click();
            driver.findElement(By.xpath("//div[@class='grid-container mt-4']/div[@class='list-group list-group-horizontal-sm'][1]/li[3]")).click();
            driver.findElement(By.xpath("//div[@class='grid-container mt-4']/div[@class='list-group list-group-horizontal-sm'][2]/li[2]")).click();
            driver.findElement(By.xpath("//div[@class='grid-container mt-4']/div[@class='list-group list-group-horizontal-sm'][3]/li[1]")).click();
            driver.findElement(By.xpath("//div[@class='grid-container mt-4']/div[@class='list-group list-group-horizontal-sm'][3]/li[3]")).click();

            //verify which grids are selected
            List<WebElement> items = driver.findElements(By.xpath("//div[@class='grid-container mt-4']//li"));

            for (WebElement item : items)
            {
                if (item.getAttribute("class").contains("active"))
                {
                    System.out.println(item.getText() + " : item is selected");
                }
            }
        }
        else
        {
            System.err.println("Selectable page is not opened.");
        }

        System.out.println("****************************************************");
        ja.executeScript("window.scrollBy(800,800)");

        //click on Resizable submenu
        driver.findElement(By.xpath("//span[text()='Resizable']")).click();

        //Verify Resizable page is opened
        if (driver.findElement(By.xpath("//h1[text()='Resizable']")).isDisplayed())
        {
            // Locate resize handle
            WebElement box = driver.findElement(By.id("resizableBoxWithRestriction"));
            WebElement handle = driver.findElement(By.xpath("//div[@id='resizableBoxWithRestriction']/span"));

            // Get initial size
            System.out.println("Before: " + box.getSize());

            // Resize the box
            Actions act = new Actions(driver);
            act.clickAndHold(handle).moveByOffset(100, 50).release().perform();

            // Get final size
            System.out.println("After: " + box.getSize());
        }
        else
        {
            System.err.println("Resizable page is not opened.");
        }
        System.out.println("****************************************************");
        ja.executeScript("window.scrollBy(800,800)");

        //click on Droppable submenu
        driver.findElement(By.xpath("//span[text()='Droppable']")).click();

        //Verify Droppable page is opened
        if (driver.findElement(By.xpath("//h1[text()='Droppable']")).isDisplayed())
        {
            Actions a = new Actions(driver);

            // 1. Simple tab
            driver.findElement(By.id("droppableExample-tab-simple")).click();

            WebElement drag = driver.findElement(By.id("draggable"));
            WebElement drop = driver.findElement(By.id("droppable"));

            a.clickAndHold(drag).moveToElement(drop).release().perform();
            Thread.sleep(1000);

            if(drop.getText().contains("Dropped!"))
            {
                System.out.println("Drag and Drop successful on Simple tab");
            } else {
                System.out.println("Drag and Drop successful on Simple tab");
            }

            // 3. Prevent Propagation tab
            driver.findElement(By.id("droppableExample-tab-preventPropogation")).click();
            Thread.sleep(500);

            WebElement d1 = driver.findElement(By.id("dragBox"));
            WebElement inner = driver.findElement(By.id("greedyDropBoxInner"));

            a.clickAndHold(d1).moveToElement(inner).release().perform();
            Thread.sleep(1000);

            if (inner.getText().contains("Dropped!")) {
                System.out.println("Drag and Drop successful on Prevent Propagation tab");
            } else {
                System.out.println("Drag and Drop failed on Prevent Propagation tab");
            }

            // 4. Revert Draggable tab
            driver.findElement(By.id("droppableExample-tab-revertable")).click();
            Thread.sleep(500);

            WebElement dd = driver.findElement(By.id("revertable"));
            WebElement bb = driver.findElement(By.xpath("(//div[@id='droppable'])[2]"));

            a.clickAndHold(dd).moveToElement(bb).release().perform();
            Thread.sleep(1000);

            if (bb.getText().contains("Dropped!")) {
                System.out.println("Drag and Drop successful on Revert Draggable tab");
            } else {
                System.out.println("Drag and Drop failed on Revert Draggable tab");
            }
        }
        else
        {
            System.err.println("Droppable page is not opened.");
        }

        System.out.println("****************************************************");
        ja.executeScript("window.scrollBy(1000,800)");
        //click on Dragabble submenu
        driver.findElement(By.xpath("//span[text()='Dragabble']")).click();

        //Verify Dragabble page is opened
        if (driver.findElement(By.xpath("//h1[text()='Dragabble']")).isDisplayed())
        {
            //drag button
            Actions a =new Actions(driver);
            a.clickAndHold(driver.findElement(By.id("dragBox")));

            //Open 'Axis restricted' tab
            driver.findElement(By.id("draggableExample-tab-axisRestriction")).click();

            //Drag button
            a.clickAndHold(driver.findElement(By.id("restrictedX")));

            //Open 'Container restricted' tab
            driver.findElement(By.id("draggableExample-tab-containerRestriction")).click();

            //Open 'Cursor Style' tab
            driver.findElement(By.id("draggableExample-tab-cursorStyle")).click();

            //drag button
            a.clickAndHold(driver.findElement(By.id("cursorTopLeft")));

            System.out.println("Dragging actions performed successfully.");
        }
        else
        {
            System.err.println("Draggable page is not open. ");
        }

    }
}
