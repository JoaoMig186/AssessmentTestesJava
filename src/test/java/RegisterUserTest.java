import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class RegisterUserTest {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        try {
            driver.get("https://exemplo.com/register");
            driver.manage().window().maximize();

            driver.findElement(By.id("username")).sendKeys("JoaoMiguelTeste");
            driver.findElement(By.id("email")).sendKeys("joaomiguelteste@teste.com");
            driver.findElement(By.id("password")).sendKeys("123456");
            driver.findElement(By.id("confirmPassword")).sendKeys("123456");

            driver.findElement(By.id("registerButton")).click();

            WebElement successMessage = driver.findElement(By.id("successMessage"));
            if (successMessage.isDisplayed()) {
                System.out.println("Cadastro realizado com sucesso!");
            } else {
                System.out.println("Erro no cadastro!");
            }
        } finally {
            driver.quit();
        }
    }
}