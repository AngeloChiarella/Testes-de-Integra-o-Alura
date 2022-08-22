package br.com.alura.leilao;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class HelloWorldSelenium {
	
	@Test // Abrir o navegador e entrar na localhost:8080
	public void hello() {
		System.setProperty("webdriver.chrome.driver","drivers/chromedriver.exe");
		WebDriver browser = new ChromeDriver();//Abrir o navegador
		browser.navigate().to("http://localhost:8080/leiloes");//navegar pelo browser
		browser.quit();//Fechar janela do navegador
	}

}
