package be.pxl;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public class TestTask4 {

    private static Playwright playwright;
    private static Browser browser;
    private final String URL = "";

    private BrowserContext context;
    private Page page;

    @AfterAll
    static void closeBrowser(){
        playwright.close();
    }

//    @BeforeAll
//    static void
}
