package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private WebDriver driver;

    private By signupLoginLink = By.linkText("Signup / Login");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void abrirPagina() {
        driver.get("https://automationexercise.com");
    }

    public LoginPage clicarSignupLogin() {
        driver.findElement(signupLoginLink).click();
        return new LoginPage(driver);
    }
}
