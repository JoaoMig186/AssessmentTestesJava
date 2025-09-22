package tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.HomePage;
import pages.LoginPage;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginInvalidoTest {
    private WebDriver driver;
    private HomePage homePage;
    private LoginPage loginPage;

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        homePage = new HomePage(driver);
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void deveMostrarErroAoLogarComCredenciaisInvalidas() {
        homePage.abrirPagina();
        loginPage = homePage.clicarSignupLogin();

        loginPage.preencherEmail("email_invalido@test.com")
                .preencherSenha("senhaerrada")
                .clicarLogin();

        String mensagem = loginPage.obterMensagemErro();
        assertThat(mensagem).isEqualTo("Your email or password is incorrect!");
    }
}