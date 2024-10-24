package be.pxl;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

class TemplateTest {

	private static Playwright playwright;
	private static Browser browser;
	private String URL = "http://localhost:5001/app/catalog.html";

	// New instance for each test method.
	private BrowserContext context;
	private Page page;

	@BeforeAll
	static void launchBrowser() {
		playwright = Playwright.create();
		BrowserType.LaunchOptions options = new BrowserType.LaunchOptions().setHeadless(false);
		browser = playwright.chromium().launch(options);
	}

	@AfterAll
	static void closeBrowser() {
		playwright.close();
	}

	@BeforeEach
	void createContextAndPage() {
		context = browser.newContext();
		page = context.newPage();
	}

	@AfterEach
	void closeContext() {
		context.close();
	}

}
