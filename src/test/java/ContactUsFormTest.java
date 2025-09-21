import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ContactUsFormTest {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        try {
            driver.get("https://exemplo.com/contact");
            driver.manage().window().maximize();

            driver.findElement(By.id("name")).sendKeys("João Miguel de Souza Cruz Ramos dos Santos");
            driver.findElement(By.id("email")).sendKeys("joaomiguelteste@teste.com");
            driver.findElement(By.id("subject")).sendKeys("Problema no site");
            driver.findElement(By.id("message")).sendKeys("Olá, encontrei um problema na página de login.");

            driver.findElement(By.id("submitButton")).click();

            WebElement confirmationMessage = driver.findElement(By.id("confirmationMessage"));
            if (confirmationMessage.isDisplayed()) {
                System.out.println("Formulário enviado com sucesso!");
            } else {
                System.out.println("Erro ao enviar o formulário!");
            }
        } finally {
            driver.quit();
        }
    }
}
