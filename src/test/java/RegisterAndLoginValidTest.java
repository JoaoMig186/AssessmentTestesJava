import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

public class RegisterAndLoginValidTest {

    private WebDriver driver;

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://automationexercise.com");
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void deveCadastrarENavegarComUsuarioValido() {
        driver.findElement(By.linkText("Signup / Login")).click();
        String nome = "João Miguel QA";
        String email = "joaomiguelqa" + System.currentTimeMillis() + "@teste.com";
        driver.findElement(By.name("name")).sendKeys(nome);
        driver.findElement(By.cssSelector("input[data-qa='signup-email']")).sendKeys(email);
        driver.findElement(By.cssSelector("button[data-qa='signup-button']")).click();

        driver.findElement(By.id("id_gender1")).click();
        driver.findElement(By.id("password")).sendKeys("123456");
        new Select(driver.findElement(By.id("days"))).selectByValue("20");
        new Select(driver.findElement(By.id("months"))).selectByValue("2");
        new Select(driver.findElement(By.id("years"))).selectByValue("2003");

        driver.findElement(By.id("first_name")).sendKeys("João Miguel QA");
        driver.findElement(By.id("last_name")).sendKeys("Tester");
        driver.findElement(By.id("company")).sendKeys("INFNET");
        driver.findElement(By.id("address1")).sendKeys("Rua Selenium 123");
        driver.findElement(By.id("address2")).sendKeys("Bloco QA");
        new Select(driver.findElement(By.id("country"))).selectByVisibleText("Canada");
        driver.findElement(By.id("state")).sendKeys("Ontario");
        driver.findElement(By.id("city")).sendKeys("Toronto");
        driver.findElement(By.id("zipcode")).sendKeys("12345");
        driver.findElement(By.id("mobile_number")).sendKeys("11999999999");

        driver.findElement(By.cssSelector("button[data-qa='create-account']")).click();

        WebElement accountCreatedMsg = driver.findElement(By.cssSelector("h2[data-qa='account-created']"));
        assertThat(accountCreatedMsg.getText()).isEqualTo("ACCOUNT CREATED!");

        driver.findElement(By.cssSelector("a[data-qa='continue-button']")).click();

        driver.findElement(By.linkText("Logout")).click();

        driver.findElement(By.cssSelector("input[data-qa='login-email']")).sendKeys(email);
        driver.findElement(By.cssSelector("input[data-qa='login-password']")).sendKeys("123456");
        driver.findElement(By.cssSelector("button[data-qa='login-button']")).click();

        WebElement loggedInAs = driver.findElement(By.xpath("//li/a[contains(.,'Logged in as')]"));
        assertThat(loggedInAs.getText()).contains("João Miguel QA");
    }
}
