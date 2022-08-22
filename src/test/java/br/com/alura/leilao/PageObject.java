package br.com.alura.leilao;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class PageObject {

	protected WebDriver browser;

	public PageObject(WebDriver browser) {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		if (browser == null) {
			this.browser = new ChromeDriver();
		} else {
			this.browser = browser;
		}
		//Esperar um tempo determinado
		this.browser.manage().timeouts()
			.implicitlyWait(5, TimeUnit.SECONDS)
			.pageLoadTimeout(10, TimeUnit.SECONDS)
			;
	}
	
	public void fechar() {
		this.browser.quit();
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