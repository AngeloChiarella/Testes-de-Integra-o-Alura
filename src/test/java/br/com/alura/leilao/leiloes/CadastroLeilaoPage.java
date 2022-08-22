package br.com.alura.leilao.leiloes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import br.com.alura.leilao.PageObject;

public class CadastroLeilaoPage extends PageObject {

	private static final String URL_NOVO_LEILAO = "http://localhost:8080/leiloes/new";


	public CadastroLeilaoPage(WebDriver browser) {
		super(browser);
	}

	public LeiloesPage cadastrarLeilao(String nome, String valorInicial, String data) {
		this.browser.findElement(By.id("nome")).sendKeys(nome);
		this.browser.findElement(By.id("valorInicial")).sendKeys(valorInicial);
		this.browser.findElement(By.id("dataAbertura")).sendKeys(data);
		this.browser.findElement(By.id("button-submit")).click();

		return new LeiloesPage(browser);
	}

	public boolean isPagAtual() {
		return browser.getCurrentUrl().equals(URL_NOVO_LEILAO);
	}

	public boolean isMensagensValidacaoVisiveis() {
		return browser.getPageSource().contains("minimo 3 caracteres")
		&& browser.getPageSource().contains("deve ser um valor maior de 0.1")
		&& browser.getPageSource().contains("deve ser uma data no formato dd/MM/yyyy");
	}

}
