package be.pxl;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.Test;

class FirstExample {
    @Test
    public void firstTest() {
        try (Playwright pw = Playwright.create()) {
            Browser browser = pw.chromium().launch();
            Page page = browser.newPage();
            page.navigate("https://www.lambdatest.com/");
            System.out.println("Title: " + page.title());
        }
    }
}
