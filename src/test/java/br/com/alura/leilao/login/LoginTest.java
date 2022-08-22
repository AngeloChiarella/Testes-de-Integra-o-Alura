package br.com.alura.leilao.login;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
		
		Assert.assertEquals("fulano", paginaDeLogin.getNomeUsuarioLogado());
		Assert.assertFalse(paginaDeLogin.isUrlLogin());
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
		Assert.assertFalse(paginaDeLogin.contemTexto("Dados do Leilão"));
	}
}