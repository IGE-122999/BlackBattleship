package iscteiul.ista.blackbattleship;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;
import static org.junit.jupiter.api.Assertions.*;

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
        acceptCookiesIfPresent();
    }

    public void acceptCookiesIfPresent() {
        SelenideElement acceptButton =
                $x("//button[contains(.,'Accept') or contains(.,'Agree')]");

        if (acceptButton.exists()) {
            acceptButton
                    .shouldBe(visible, Duration.ofSeconds(5))
                    .click();

            acceptButton.should(disappear, Duration.ofSeconds(5));
        }
    }

    @Test
    public void shouldOpenJetBrainsHomePage() {
        mainPage.pageBody.shouldBe(visible);
        assertTrue(Selenide.title().contains("JetBrains"));
    }

    @Test
    public void search() throws InterruptedException {
        // abrir search
        mainPage.searchButton.shouldBe(visible).shouldBe(clickable).click();

        // escrever no input correto
        $("input[data-test='input__inner']")
                .shouldBe(visible)
                .setValue("Selenium")
                .pressEnter();
        $("main")
                .shouldBe(visible);
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