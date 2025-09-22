package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver driver;

    private By emailField = By.name("email");
    private By passwordField = By.name("password");
    private By loginButton = By.xpath("//button[text()='Login']");
    private By mensagemErro = By.xpath("//p[contains(text(),'Your email or password is incorrect!')]");
    private static final By USUARIO_LOGADO = By.xpath("//li/a[contains(.,'Logged in as')]");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPage preencherEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
        return this;
    }

    public LoginPage preencherSenha(String senha) {
        driver.findElement(passwordField).sendKeys(senha);
        return this;
    }

    public void clicarLogin() {
        driver.findElement(loginButton).click();
    }

    public String obterMensagemErro() {
        return driver.findElement(mensagemErro).getText();
    }

    public String obterUsuarioLogado(){
        return driver.findElement(USUARIO_LOGADO).getText();
    }
}
