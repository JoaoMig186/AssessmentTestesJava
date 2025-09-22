import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginInvalidoTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://automationexercise.com");
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void deveMostrarErroAoLogarComEmailInvalido() {
        driver.findElement(By.linkText("Signup / Login")).click();

        driver.findElement(By.name("email")).sendKeys("emailInvalido@test.com");
        driver.findElement(By.name("password")).sendKeys("senhaInvalida");
        driver.findElement(By.xpath("//button[text()='Login']")).click();

        WebElement mensagemErro = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//p[contains(text(),'Your email or password is incorrect!')]")
                )
        );

        assertThat(mensagemErro.getText())
                .isEqualTo("Your email or password is incorrect!");
    }
}
