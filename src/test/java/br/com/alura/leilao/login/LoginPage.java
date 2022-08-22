package br.com.alura.leilao.login;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginPage {

	private WebDriver browser;

	private String loginUrl = "http://localhost:8080/login";
	private String leiloesUrl = "http://localhost:8080/leiloes/2";
	private String loginUrlError = "http://localhost:8080/login?error";

	public LoginPage() {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		this.browser = new ChromeDriver();
		this.browser.navigate().to(loginUrl);
	}

	public void fechar() {
		this.browser.quit();
	}

	public void preencheFormularioLogin(String username, String password) {
		browser.findElement(By.id("username")).sendKeys(username);
		browser.findElement(By.id("password")).sendKeys(password);
	}

	public void efetuaLogin() {
		browser.findElement(By.id("login-form")).submit();
	}

	public boolean isUrlLogin() {
		return browser.getCurrentUrl().equals(loginUrl);
	}

	public boolean isUrlLoginError() {
		return browser.getCurrentUrl().equals(loginUrlError);
	}

	public Object getNomeUsuarioLogado() {
		try {
			return browser.findElement(By.id("usuario-logado")).getText();
		} catch (NoSuchElementException e) {
			return null;
		}
	}

	public void navegaPagLances() {
		browser.navigate().to(leiloesUrl);
	}

	public boolean contemTexto(String texto) {
		return browser.getPageSource().contains(texto);
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