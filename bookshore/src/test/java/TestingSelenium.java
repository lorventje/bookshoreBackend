import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class TestingSelenium {

    WebDriver obj;

    @Before
    public void before(){
        System.setProperty("webdriver.gecko.driver", "C:/Users/remmi/OneDrive/Bureaublad/Semester_4/Fun4_Individual_part/Testing/geckodriver.exe");
        obj = new FirefoxDriver();
        obj.manage().window().maximize();
        obj.get("http://localhost:8080/");
    }

    @Test
    public void searchBooks() throws InterruptedException {
        obj.findElement(By.name("search")).clear();
        obj.findElement(By.name("search")).sendKeys("a");
        TimeUnit.SECONDS.sleep(3);
        obj.findElement(By.id("SearchButton")).click();
        obj.findElement(By.id("SearchButton")).click();
        String URL = obj.getCurrentUrl();
        Assert.assertEquals(URL, "http://localhost:8080/search-results" );
    }

    @Test
    public void requestBookDetails() throws InterruptedException {
        searchBooks();
        TimeUnit.SECONDS.sleep(3);
        obj.findElement(By.xpath("//a[@href='/detail/15']")).click();
        String URL = obj.getCurrentUrl();
        Assert.assertEquals(URL, "http://localhost:8080/detail/15");
    }


    @Test
    public void downloadEbook() throws InterruptedException {
        requestBookDetails();
        TimeUnit.SECONDS.sleep(3);
        obj.findElement(By.name("download")).click();
        //Alert alert = obj.switchTo().alert();
        //System.out.println(alert.getText()); //Print Alert popup
        //alert.accept(); //Close Alert popup
    }


    @Test
    public void requestReviews() throws InterruptedException {
        requestBookDetails();
        TimeUnit.SECONDS.sleep(3);
        obj.findElement(By.name("reviewss")).click();
        String URL = obj.getCurrentUrl();
        Assert.assertEquals(URL, "http://localhost:8080/reviews/15");
    }

    @Test
    public void writeReview() throws InterruptedException {
        requestReviews();
        TimeUnit.SECONDS.sleep(3);
        obj.findElement(By.name("writereview")).click();
        obj.findElement(By.name("name")).sendKeys("Karel");
        obj.findElement(By.name("email")).sendKeys("karel@hotmail.com");
        obj.findElement(By.name("review")).sendKeys("Het is een leuk boek om te lezen.");
        TimeUnit.SECONDS.sleep(3);
        obj.findElement(By.name("create")).click();
    }


    @Test
    public void previousPage() throws InterruptedException {
        requestReviews();
        TimeUnit.SECONDS.sleep(3);
        obj.findElement(By.name("back")).click();
        String URL = obj.getCurrentUrl();
        Assert.assertEquals(URL, "http://localhost:8080/detail/15");
    }

}
