package gustavoakira.sendemail.sendemail;

import org.junit.Test;

public class AppTest {
	@Test
	public void emailTest() {
		try {
			SentEmail sentEmail = new SentEmail("akirauekita2002@gmail.com", "Gustavo akira", "Testando email com java", "Esse texto é a descrição do email");
			sentEmail.sent(false);
			Thread.sleep(5000);// waiting to kill junit test
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	@Test
	public void emailTestwithHtml() {
		StringBuilder builderHtml = new StringBuilder();
		builderHtml.append("<h1>Akira</h1>");
		try {
			SentEmail sentEmail = new SentEmail("akirauekita2002@gmail.com", "Gustavo akira", "Testando email com java", builderHtml.toString());
			sentEmail.sent(true);
			Thread.sleep(5000);// waiting to kill junit test
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	@Test
	public void emailTestwithAnex() {
		StringBuilder builderHtml = new StringBuilder();
		builderHtml.append("<h1>Akira</h1>");
		try {
			SentEmail sentEmail = new SentEmail("akirauekita2002@gmail.com", "Gustavo akira", "Testando email com java", builderHtml.toString());
			sentEmail.sentwhitAnexed(true);
			Thread.sleep(5000);// waiting to kill junit test
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
}
