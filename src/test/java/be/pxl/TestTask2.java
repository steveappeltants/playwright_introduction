package be.pxl;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


class TestTask2 {

    // To run this command before writing the test:
    // mvn exec:java -e -D exec.mainClass=com...
    // Remove tempy <scope>test</scope> in the dependencies under GroupId: com.microsoft.playwright

    // Shared between all tests in this class.
    private static Playwright playwright;
    private static Browser browser;
    private String URL = "http://localhost:5001/app/catalog.html";

    // New instance for each test method.
    private BrowserContext context;
    private Page page;

    @AfterAll
    static void closeBrowser() {
        playwright.close();
    }

    @BeforeAll
    static void launchBrowser() {
        playwright = Playwright.create();
        BrowserType.LaunchOptions options = new BrowserType.LaunchOptions().setHeadless(false);
        browser = playwright.chromium().launch(options);
    }

    @Test
    void eventListCount() {
        try {
            context = browser.newContext();
            page = context.newPage();
            page.navigate(URL);
            page.getByTestId("filter-text").click();
            page.getByTestId("filter-text").fill("moon");
            page.getByTestId("filter-button").click();

            // Print inner text of the html td (table data)
            page.locator(".event-name").all().forEach(
                    l -> System.out.println(l.innerText()));

            // Count the number of appearances an inner text occurs
            int count = page.locator(".event-name").all().size();
            System.out.println("Number of events on the page: " + count);

            // Simplified screenshot handling
            String screenshotFolder = "screenshots";
            Files.createDirectories(Paths.get(screenshotFolder));  // Creates the folder if it doesn't exist
            Path screenshotFilePath = Paths.get(screenshotFolder, "catalog_page.png");

            // Save the screenshot
            page.screenshot(new Page.ScreenshotOptions().setPath(screenshotFilePath));
            System.out.println("Screenshot saved at: " + screenshotFilePath.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    void closeContext() {
        context.close();
    }
}
