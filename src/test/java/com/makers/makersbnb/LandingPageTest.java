package com.makers.makersbnb;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

class LandingPageTest {
    static Playwright playwright;
    static Browser browser;
    BrowserContext context;
    Page page;

    @BeforeAll
    static void launchBrowser() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch();
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

    @Test
    public void usersAreWelcomedToTheApp() {
        page.navigate("localhost:8080");
        Locator pageH1 = page.locator("H1");
        assertThat(pageH1).containsText("Welcome to MakersBnB!");
        Locator pageH3 = page.locator("H3");
        assertThat(pageH3).containsText("spaces are waiting to be discovered");
    }

    @Test
    public void usersCanSeeContactEmailAddress() {
        page.navigate("localhost:8080/contactus");
        Locator pageBody = page.locator("body");
        assertThat(pageBody).containsText("email@website.com");
    }

    @Test
    public void usersCanSeeTeamList() {
        page.navigate("localhost:8080/team");
        Locator pageBody = page.locator("body");
        assertThat(pageBody).containsText("Toby");
    }

    @Test
    public void usersCanSeeTsAndCs() {
        page.navigate("localhost:8080/termsandconditions");
        Locator pageBody = page.locator("body");
        assertThat(pageBody).containsText("Coming soon! In the meantime, please behave yourselves.");
    }
}
