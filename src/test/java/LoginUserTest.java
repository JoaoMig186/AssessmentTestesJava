import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginUserTest {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        String currentUrl = driver.getCurrentUrl();

        try {
            driver.get("https://exemplo.com/login");
            driver.manage().window().maximize();

            driver.findElement(By.id("email")).sendKeys("joaomiguelteste@teste.com");
            driver.findElement(By.id("password")).sendKeys("123456");
            driver.findElement(By.id("loginButton")).click();
            WebElement dashboard = driver.findElement(By.id("dashboard"));

            if (currentUrl.contains("/dashboard")) {
                System.out.println("Login realizado com sucesso!");
            } else {
                System.out.println("Erro no login!");
            }

        } finally {
            driver.quit();
        }
    }
}
