package be.pxl;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;


class TestTask3 {

    private static Playwright playwright;
    private static Browser browser;
    private String URL = "http://localhost:5001/app/catalog.html";

    private BrowserContext context;
    private Page page;

    @AfterAll
    static void closeBrowser() {
        playwright.close();
    }

    @BeforeAll
    static void lauchBrowser() {
        playwright = Playwright.create();
        BrowserType.LaunchOptions options = new BrowserType.LaunchOptions().setHeadless(true);
        browser = playwright.chromium().launch(options);
    }

    @Test
    void basketQuantity() {
        try {
            context = browser.newContext();
            page = context.newPage();
            page.navigate(URL);

            // Act
            page.getByTestId("purchase-button-1").click();
            page.getByTestId("quantity").selectOption("3");
            page.getByTestId("place-order").click();
            page.getByTestId("quantity-dropdown").selectOption("4");
            page.getByTestId("update-button").click();
            // Arrange
            String ticketCountValue = page.locator("#ticket-count").innerText();  // Correct the selector
            // Assert
            assertEquals(ticketCountValue, "4");
            System.out.println("Updated basket ticket value is: " + ticketCountValue);

        } catch (Exception e) {
            Assertions.fail("Test failed due to unexpected exception: " + e.getMessage());
        }
    }

    @AfterEach
    void closeContext() {
        context.close();
    }
}













