package iscteiul.ista.blackbattleship;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.*;

public class MainPageTest {

    MainPage mainPage = new MainPage();

    @BeforeAll
    public static void setUpAll() {
        Configuration.browserSize = "1280x800";
        Configuration.timeout = 8000;
    }

    @BeforeEach
    public void setUp() {
        open("https://www.jetbrains.com/");
        closePopup();
    }

    @Test
    public void shouldOpenJetBrainsHomePage() {
        mainPage.pageBody.shouldBe(visible);
        assertTrue(Selenide.title().contains("JetBrains"));
    }

    @Test
    public void shouldDisplayLogo() {
        mainPage.logo.shouldBe(visible);
    }

    @Test
    public void shouldOpenSearch() {
        mainPage.searchButton.shouldBe(visible).click();
        $("body").shouldHave(text("Search"));
    }

    @Test
    public void shouldSearchForSelenium() {
        mainPage.searchButton.shouldBe(visible).click();
        actions().sendKeys("Selenium").sendKeys("\n").perform();
        mainPage.pageBody.shouldHave(text("Selenium"));
    }
}
