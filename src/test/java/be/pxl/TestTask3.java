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

            // Arrange
            String eventName = page.locator(".event-name").innerText();
            String dropdownValue = page.getByTestId("quantity-dropdown").selectOption("3").getFirst();
            String basketAmountOfTickets = page.locator("#ticket-count").innerText();
            String eventTotal = page.locator(".event-total").innerText();

            // Assert
            assertEquals(eventName, "Alexander Lemtov Live");
            assertEquals(dropdownValue, "3");
            assertEquals(basketAmountOfTickets, "3");
            assertEquals(eventTotal, "$195.00");

            // Log Terminal
            System.out.printf("%-40s %s%n", "Purchase details - event name:", basketAmountOfTickets);
            System.out.printf("%-40s %s%n", "Purchase details - dropdown value:", basketAmountOfTickets);
            System.out.printf("%-40s %s%n", "Purchase order - tickets in basket:", basketAmountOfTickets);
            System.out.printf("%-40s %s%n", "Purchase order - total event amount:", eventTotal);

        } catch (Exception e) {
            Assertions.fail("Test failed due to unexpected exception: " + e.getMessage());
        }
    }

    @AfterEach
    void closeContext() {
        context.close();
    }
}













