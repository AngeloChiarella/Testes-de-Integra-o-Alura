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

	private LoginPage paginaDeLogin;

	@BeforeAll
	public static void beforeAll() {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
	}

	@BeforeEach
	public void beforeEach() {
		this.paginaDeLogin = new LoginPage();
	}

	@AfterEach
	public void afterEach() {
		this.paginaDeLogin.fechar();
	}

	@Test
	public void efetuarLoginComDadosValidos() {

		paginaDeLogin.preencheFormularioLogin("fulano", "pass");
		paginaDeLogin.efetuaLogin();
		Assert.assertFalse(paginaDeLogin.isUrlLogin());
		Assert.assertEquals("fulano", paginaDeLogin.getNomeUsuarioLogado());
	}

	@Test
	public void naoLogarComDadosInvalidos() {

		paginaDeLogin.preencheFormularioLogin("invalido", "invalido");
		paginaDeLogin.efetuaLogin();

		Assert.assertTrue(paginaDeLogin.isUrlLoginError());
		Assert.assertNull(paginaDeLogin.getNomeUsuarioLogado());
		Assert.assertTrue(paginaDeLogin.contemTexto("Usuário e senha inválidos."));

	}

	@Test
	public void naoAcessarPagRestritaSemLogar() {
		paginaDeLogin.navegaPagLances();

		Assert.assertTrue(paginaDeLogin.isUrlLogin());
		Assert.assertFalse(paginaDeLogin.contemTexto("Dados do Leilão")); // nao conter dadosdoleilao
	}
}