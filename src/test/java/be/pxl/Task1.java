package be.pxl;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;


public class Task1 {

    @Test
    public void differentBrowsers() {
        try (Playwright pw = Playwright.create()) { // guarantee the browser and pages will be closed in the end
            BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions().setHeadless(true);
            Browser browser = pw.chromium().launch(launchOptions);
            Page page = browser.newPage();
            page.navigate("http://localhost:5001/app/catalog.html");
            page.locator(".event-name").all().forEach(
                    l -> System.out.println(l.innerText())
            );
        }
    }
}
