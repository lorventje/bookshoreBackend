package Selenium_webdriver;

//import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
//import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.support.ui.Select;

    public class exampleCode {
        private WebDriver driver;
        private String baseUrl;
        //private boolean acceptNextAlert = true;
        private StringBuffer verificationErrors = new StringBuffer();

        @Before
        public void setUp() throws Exception {
            System.setProperty("webdriver.gecko.driver", "C:/Users/remmi/OneDrive/Bureaublad/Semester_4/Fun4_Individual_part/Testing/geckodriver.exe");
            driver = new FirefoxDriver();
            baseUrl = "https://www.google.com/";
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        }

        @Test
        public void testGoogleSearch() throws Exception {
            driver.get(baseUrl + "/");
            driver.findElement(By.name("q")).clear();
            driver.findElement(By.name("q")).sendKeys("Google");
            driver.findElement(By.name("q")).click();
        }

        @After
        public void tearDown() throws Exception {
            driver.quit();
            String verificationErrorString = verificationErrors.toString();
            if (!"".equals(verificationErrorString)) {
                fail(verificationErrorString);
            }
        }


}
