package br.com.alura.leilao.login;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest {

	private WebDriver browser;

	private String loginUrl = "http://localhost:8080/login";
	private String loginUrlError = "http://localhost:8080/login?error";
	private String username = "fulano";
	private String password = "pass";
	private String msgErro = "Usuário e senha inválidos.";

	@BeforeAll
	public static void beforeAll() {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
	}

	@BeforeEach
	public void beforeEach() {
		this.browser = new ChromeDriver();
		this.browser.navigate().to(loginUrl);

	}

	@AfterEach
	public void afterEach() {
		this.browser.quit();
	}

	@Test
	public void efetuarLoginComDadosValidos() {

		browser.findElement(By.id("username")).sendKeys(username);
		browser.findElement(By.id("password")).sendKeys(password);
		browser.findElement(By.id("login-form")).submit();

		Assert.assertFalse(browser.getCurrentUrl().equals(loginUrl));
		Assert.assertEquals(username, browser.findElement(By.id("usuario-logado")).getText());
	}

	@Test
	public void naoLogarComDadosInvalidos() {

		browser.navigate().to(loginUrl);
		browser.findElement(By.id("username")).sendKeys("invalido");
		browser.findElement(By.id("password")).sendKeys("invalido");
		browser.findElement(By.id("login-form")).submit();

		Assert.assertTrue(browser.getCurrentUrl().equals(loginUrlError));
		Assert.assertTrue(msgErro, browser.getPageSource().contains(msgErro));
		Assert.assertThrows(NoSuchElementException.class, () -> browser.findElement(By.id("usuario-logado")));

	}

	@Test
	public void naoAcessarPagRestritaSemLogar() {
		this.browser.navigate().to("http://localhost:8080/login/2");
		
		Assert.assertTrue(browser.getCurrentUrl().equals("http://localhost:8080/login")); // verificar link
		Assert.assertFalse(browser.getPageSource().contains("Dados do Leilão")); // nao conter dadosdoleilao
	}
}

//.findElement - encontrar elemento no browser
//By - referenciar elemento html - n precisa estanciar - recomendado usar id
//.id - do imput "username" e "password"
//.sendKeys - escrever no campo input
//.submit - submeter um formulario
//.getText - recuperar o texto de um elemento
//.getPageSource - devolve string com todo codigo fonte da pag
//.assertThrows - verificar qual exception e chamada