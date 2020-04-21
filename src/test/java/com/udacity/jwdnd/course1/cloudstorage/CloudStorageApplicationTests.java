package com.udacity.jwdnd.course1.cloudstorage;

import java.time.Duration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests {

	private static final String FIRST_NAME = "Firstname";
	private static final String LAST_NAME = "Lastname";
	private static final String USERNAME = "demo";
	private static final String PASSWORD = "password";
	@LocalServerPort
	private int port;

	private WebDriver driver;

	@BeforeAll
	static void beforeAll() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void beforeEach() {
		this.driver = new ChromeDriver();
	}

	@AfterEach
	public void afterEach() {
		if (this.driver != null) {
			driver.quit();
		}
	}

	@Test
	public void withoutSignup() throws InterruptedException {
		driver.get("http://localhost:" + this.port + "/home");
		Thread.sleep(1000);
		Assertions.assertEquals("Login", driver.getTitle());
	}

	@Test
	public void testCredential() throws InterruptedException {
		signup();
		login();

		WebElement credentialsTab = driver.findElement(By.id("nav-credentials-tab"));
		credentialsTab.click();
		Thread.sleep(500);

		WebElement showCredentialsModel = driver.findElement(By.id("show-credentials-modal"));
		showCredentialsModel.click();
		Thread.sleep(500);

		WebElement credentialUrl = driver.findElement(By.id("credential-url"));
		credentialUrl.sendKeys("abc.cc");

		WebElement credentialUsername = driver.findElement(By.id("credential-username"));
		credentialUsername.sendKeys("test");

		WebElement credentialPassword = driver.findElement(By.id("credential-password"));
		credentialPassword.sendKeys("password");

		WebElement credentialSubmit = driver.findElement(By.id("credential-submit"));
		credentialSubmit.click();
		Thread.sleep(1000);

		WebElement homeReturn = driver.findElement(By.id("return-home"));
		homeReturn.click();
		Thread.sleep(500);

		WebElement credentialsTab1 = driver.findElement(By.id("nav-credentials-tab"));
		credentialsTab1.click();
		Thread.sleep(500);

		WebElement savedNote = driver.findElement(By.cssSelector("td.credential-url-saved"));
		Assertions.assertEquals("abc.cc", savedNote.getText());
		Thread.sleep(500);

		WebElement editNote = driver.findElement(By.cssSelector("button.credential-edit"));
		editNote.click();
		Thread.sleep(500);

		WebElement credentialUrl1 = driver.findElement(By.id("credential-url"));
		credentialUrl1.sendKeys(".dd");
		WebElement saveCredential = driver.findElement(By.id("credential-submit"));
		saveCredential.click();
		Thread.sleep(1000);


		WebElement homeReturn1 = driver.findElement(By.id("return-home"));
		homeReturn1.click();
		Thread.sleep(1000);

		WebElement notesTab2 = driver.findElement(By.id("nav-credentials-tab"));
		notesTab2.click();
		Thread.sleep(500);

		WebElement savedCredential	 = driver.findElement(By.cssSelector("td.credential-url-saved"));
		Assertions.assertEquals("abc.cc.dd", savedCredential.getText());

		WebElement deleteCredential = driver.findElement(By.cssSelector("a.credential-delete"));
		deleteCredential.click();
		Thread.sleep(1000);

		WebElement homeReturn2 = driver.findElement(By.id("return-home"));
		homeReturn2.click();
		Thread.sleep(1000);

		WebElement credentialTab3 = driver.findElement(By.id("nav-credentials-tab"));
		credentialTab3.click();
		Thread.sleep(500);

		boolean ifCredentialPresent = driver.findElements(By.cssSelector("td.credential-url-saved")).size()==0;
		Assertions.assertEquals(true, ifCredentialPresent);

	}

	@Test
	public void userLoginToLogout() throws InterruptedException {
		signup();
		login();

		Assertions.assertEquals("Home", driver.getTitle());

		WebElement element = driver.findElement(By.id("logout"));
		element.click();
		Thread.sleep(1000);

		Assertions.assertEquals("Login", driver.getTitle());

		driver.get("http://localhost:" + this.port + "/home");
		Thread.sleep(1000);
		Assertions.assertEquals("Login", driver.getTitle());
	}

	@Test
	public void testNotes() throws InterruptedException {
		signup();
		login();

		WebElement notesTab = driver.findElement(By.id("nav-notes-tab"));
		notesTab.click();
		Thread.sleep(500);

		WebElement showNoteModel = driver.findElement(By.id("show-note-modal"));
		showNoteModel.click();
		Thread.sleep(500);

		WebElement noteTitle = driver.findElement(By.id("note-title"));
		noteTitle.sendKeys("title");

		WebElement noteDescription = driver.findElement(By.id("note-description"));
		noteDescription.sendKeys("description");

		WebElement saveNote = driver.findElement(By.id("note-submit"));
		saveNote.click();
		Thread.sleep(1000);

		WebElement homeReturn = driver.findElement(By.id("return-home"));
		homeReturn.click();
		Thread.sleep(500);

		WebElement notesTab1 = driver.findElement(By.id("nav-notes-tab"));
		notesTab1.click();
		Thread.sleep(500);

		WebElement savedNote = driver.findElement(By.cssSelector("td.note-title-row"));
		Assertions.assertEquals("title", savedNote.getText());
		Thread.sleep(500);

		WebElement editNote = driver.findElement(By.cssSelector("button.note-edit"));
		editNote.click();
		Thread.sleep(500);

		WebElement noteTitle1 = driver.findElement(By.id("note-title"));
		noteTitle1.sendKeys("edit");
		WebElement saveNote1 = driver.findElement(By.id("note-submit"));
		saveNote1.click();
		Thread.sleep(1000);


		WebElement homeReturn1 = driver.findElement(By.id("return-home"));
		homeReturn1.click();
		Thread.sleep(1000);

		WebElement notesTab2 = driver.findElement(By.id("nav-notes-tab"));
		notesTab2.click();
		Thread.sleep(500);

		WebElement savedNote1	 = driver.findElement(By.cssSelector("td.note-title-row"));
		Assertions.assertEquals("titleedit", savedNote1.getText());

		WebElement deleteNote = driver.findElement(By.cssSelector("a.note-delete"));
		deleteNote.click();
		Thread.sleep(1000);

		WebElement homeReturn2 = driver.findElement(By.id("return-home"));
		homeReturn2.click();
		Thread.sleep(1000);

		WebElement notesTab3 = driver.findElement(By.id("nav-notes-tab"));
		notesTab3.click();
		Thread.sleep(500);

		boolean ifNotePresent = driver.findElements(By.cssSelector("td.note-title-row")).size()==0;
		Assertions.assertEquals(true, ifNotePresent);
	}

	private void login() throws InterruptedException {
		driver.get("http://localhost:" + this.port + "/login");
		Thread.sleep(1000);

		WebElement element = driver.findElement(By.id("inputUsername"));
		element.sendKeys(USERNAME);

		element = driver.findElement(By.id("inputPassword"));
		element.sendKeys(PASSWORD);

		element = driver.findElement(By.id("login"));
		element.click();
		Thread.sleep(1000);
	}

	private void signup() throws InterruptedException {
		driver.get("http://localhost:" + this.port + "/signup");
		Thread.sleep(1000);

		WebElement element = driver.findElement(By.id("inputFirstName"));
		element.sendKeys(FIRST_NAME);

		element = driver.findElement(By.id("inputLastName"));
		element.sendKeys(LAST_NAME);

		element = driver.findElement(By.id("inputUsername"));
		element.sendKeys(USERNAME);

		element = driver.findElement(By.id("inputPassword"));
		element.sendKeys(PASSWORD);

		element = driver.findElement(By.id("signup"));
		element.click();
		Thread.sleep(1000);
	}

}