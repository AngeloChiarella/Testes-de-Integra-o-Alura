package br.com.alura.leilao.login;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import br.com.alura.leilao.PageObject;
import br.com.alura.leilao.leiloes.LeiloesPage;

public class LoginPage extends PageObject {

	private static final String URL_LOGIN = "http://localhost:8080/login";
	private static final String URL_LEILAO = "http://localhost:8080/leiloes/2";
	private static final String URL_LOGIN_ERR = "http://localhost:8080/login?error";

	public LoginPage() {
		super(null);
		this.browser.navigate().to(URL_LOGIN);
	}

	public void preencheFormularioLogin(String username, String password) {
		browser.findElement(By.id("username")).sendKeys(username);
		browser.findElement(By.id("password")).sendKeys(password);
	}

	public LeiloesPage efetuaLogin() {
		browser.findElement(By.id("login-form")).submit();
		return new LeiloesPage(browser);
	}

	public boolean isUrlLogin() {
		return browser.getCurrentUrl().equals(URL_LOGIN);
	}

	public boolean isUrlLoginError() {
		return browser.getCurrentUrl().equals(URL_LOGIN_ERR);
	}

	public Object getNomeUsuarioLogado() {
		try {
			return browser.findElement(By.id("usuario-logado")).getText();
		} catch (NoSuchElementException e) {
			return null;
		}
	}

	public void navegaPagLances() {
		browser.navigate().to(URL_LEILAO);
	}

	public boolean contemTexto(String texto) {
		return browser.getPageSource().contains(texto);
	}

}
