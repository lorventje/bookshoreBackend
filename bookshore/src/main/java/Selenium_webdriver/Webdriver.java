package Selenium_webdriver;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

public class Webdriver {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.gecko.driver", "C:/Users/remmi/OneDrive/Bureaublad/Semester_4/Fun4_Individual_part/Testing/geckodriver.exe");
        //WebDriver obj = new FirefoxDriver();
        //obj.manage().window().maximize();
        //obj.get("http://localhost:8080/");

        if (testAh()) {
            System.out.println("Test Wordpress Login: Passed");
        } else {
            System.out.println("Test Wordpress Login: Failed");

        }
/*

        //Search books:
        obj.findElement(By.name("search")).clear();
        obj.findElement(By.name("search")).sendKeys("a");
        TimeUnit.SECONDS.sleep(3);
        obj.findElement(By.id("SearchButton")).click();
        obj.findElement(By.id("SearchButton")).click();



        TimeUnit.SECONDS.sleep(3);

        //Request book details:
        obj.findElement(By.xpath("//a[@href='/detail/15']")).click();

        TimeUnit.SECONDS.sleep(3);

        //Download e-book
        obj.findElement(By.name("download")).click();

        TimeUnit.SECONDS.sleep(3);

        //Request reviews
        obj.findElement(By.name("reviewss")).click();

        TimeUnit.SECONDS.sleep(3);

        //Write review
        obj.findElement(By.name("writereview")).click();
        obj.findElement(By.name("name")).sendKeys("Karel");
        obj.findElement(By.name("email")).sendKeys("karel@hotmail.com");
        obj.findElement(By.name("review")).sendKeys("Het is een leuk boek om te lezen.");
        TimeUnit.SECONDS.sleep(3);
        obj.findElement(By.name("create")).click();

        TimeUnit.SECONDS.sleep(3);

        //Previous page
        obj.findElement(By.name("back")).click();





        //obj.findElement(By.name("q")).clear();
        //obj.findElement(By.name("gbqfq")).sendKeys("Google");
        //obj.findElement(By.id("gbqfb")).click();

        //String result = obj.findElement(By.name("q")).getAttribute("value");
        //System.out.println(" The Result is " + result);
*/
    }
    private static boolean testAh() throws InterruptedException {
        WebDriver obj = new FirefoxDriver();
        obj.manage().window().maximize();
        obj.get("http://localhost:8080/");
        obj.findElement(By.name("search")).clear();
        obj.findElement(By.name("search")).sendKeys("a");
        TimeUnit.SECONDS.sleep(3);
        obj.findElement(By.id("SearchButton")).click();
        obj.findElement(By.id("SearchButton")).click();
        String URL = obj.getCurrentUrl();

        Assert.assertEquals(URL, "http://localhost:8080/search-results" );
       return true;
    }
}
