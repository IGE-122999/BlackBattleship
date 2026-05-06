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

public class MainPageTest {
    MainPage mainPage = new MainPage();

    @BeforeAll
    public static void setUpAll() {
        Configuration.browserSize = "1280x800";
        SelenideLogger.addListener("allure", new AllureSelenide());
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
            Duration duration = null;
            acceptButton
                    .shouldBe(visible, duration.ofSeconds(5))
                    .click();

            // opcional mas recomendado: garantir que o banner desapareceu
            acceptButton.should(disappear, duration.ofSeconds(5));
        }
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
    public void toolsMenu() {
        mainPage.toolsMenu.click();

        $("div[data-test='main-submenu']").shouldBe(visible);
    }

    @Test
    public void navigationToAllTools() {
        mainPage.seeDeveloperToolsButton.click();
        mainPage.findYourToolsButton.click();

        $("#products-page").shouldBe(visible);

        assertEquals("All Developer Tools and Products by JetBrains", title());
    }
}
