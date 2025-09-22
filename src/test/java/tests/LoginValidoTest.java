package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginValidoTest {
    private WebDriver driver;

    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://automationexercise.com/login");
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void deveLogarComCredenciaisValidas() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.preencherEmail("seuEmailValido@teste.com")
                .preencherSenha("suaSenhaValida")
                .clicarLogin();

        String mensagemUsuario = loginPage.obterUsuarioLogado();
        assertThat(mensagemUsuario).contains("Logged in as");
    }
}
