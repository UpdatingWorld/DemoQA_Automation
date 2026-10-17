import com.google.j2objc.annotations.Weak;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Forms_Page {

    public static void main(String[] args) throws InterruptedException {
        ChromeDriver driver = new ChromeDriver();
        driver.get("https://demoqa.com/");

        driver.manage().window().maximize();

        //click on Forms module
        driver.findElement(By.xpath("(//div[@class='card mt-4 top-card'])[2]")).click();

        Thread.sleep(2000);

        //click on practice form submenu
        driver.findElement(By.xpath("//span[text()='Practice Form']")).click();

        Thread.sleep(2000);
        if(driver.findElement(By.xpath("//h1[text()='Practice Form']")).isDisplayed())
        {
            //add details
            driver.findElement(By.id("firstName")).sendKeys("Abhi");
            driver.findElement(By.id("lastName")).sendKeys("Rane");
            driver.findElement(By.id("userEmail")).sendKeys("abhi@gmail.com");

            //select male radio button
            driver.findElement(By.xpath("//input[@value='Male']")).click();

            //Add moobile number
            driver.findElement(By.id("userNumber")).sendKeys("8483876563");

            //add date of birth
            driver.findElement(By.id("dateOfBirthInput")).click();

            WebElement month = driver.findElement(By.className("react-datepicker__month-select"));
            Select dataMonth =new Select(month);
            dataMonth.selectByVisibleText("May");

            WebElement year = driver.findElement(By.className("react-datepicker__year-select"));
            Select year1 =new Select(year);
            year1.selectByVisibleText("2001");

            driver.findElement(By.xpath("(//div[text()='30'])[2]")).click();
            driver.findElement(By.id("dateOfBirthInput")).click();

            //select hobbies
            driver.findElement(By.id("hobbies-checkbox-1")).click();
            driver.findElement(By.id("hobbies-checkbox-3")).click();

            //uploadPicture
            WebElement upload = driver.findElement(By.id("uploadPicture"));
            upload.sendKeys("C:\\Users\\Neha\\Downloads\\sampleFile.jpeg");

            //Add current address
            driver.findElement(By.id("currentAddress")).sendKeys("Pune, Maharashtra.");

            //Add state
            WebElement state = driver.findElement(By.id("react-select-3-input"));
            state.sendKeys("Rajasthan");
            state.sendKeys(Keys.ENTER);

            //Add city
            WebElement city =driver.findElement(By.id("react-select-4-input"));
            city.sendKeys("Jaipur");
            city.sendKeys(Keys.ENTER);

            //click on submit button
            driver.findElement(By.id("submit")).click();

            //Verify data is submit properly
            if(driver.findElement(By.id("example-modal-sizes-title-lg")).isDisplayed())
            {
                int m=0;
                String data[] ={"Abhi Rane","abhi@gmail.com","Male","8483876563","30 May,2001"," ","Sports, Music","sampleFile.jpeg","Pune, Maharashtra.","Rajasthan jaipur"};
                for(int i =1; i<=9; i++)
                {
                    if(i!=6 && !driver.findElement(By.xpath("//tbody//tr["+ i +"]//td[2]")).getText().equals(data[i-1]))
                    {
                        m=1;
                        System.out.println(" Student data "+ data[i-1] +" is not matching.");
                    }
                }

                if (m==0)
                {
                    System.out.println("All data are stored successfully.");
                }

                //close form
                driver.findElement(By.id("closeLargeModal")).click();
                driver.navigate().refresh();
            }
            else
            {
                System.out.println("Form is not submitted properly");
            }
        }
        else
        {
            System.err.println("'Practice form' page is not opened.");
        }




    }
}
