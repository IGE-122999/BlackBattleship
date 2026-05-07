package iscteiul.ista.blackbattleship;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
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
        closePopup();
    }

    @Test
    public void search() {
        mainPage.searchButton.click();

        $("[data-test-id='search-input']").sendKeys("Selenium");
        $("button[data-test='full-search-button']").pressEnter();

        $("input[data-test-id='search-input']").shouldHave(attribute("value", "Selenium"));
    }

    @Test
    public void productsMenu() {
        mainPage.productsMenu.click();

        $("div[data-test='main-submenu']").shouldBe(visible);
    }

    @Test
    public void navigationToAllTools() {
        mainPage.seeProductsButton.click();
        mainPage.findYourToolsButton.click();

        $("#products-page").shouldBe(visible);

        assertEquals("All Developer Tools and Products by JetBrains", Selenide.title());
    }

    void closePopup() {
        SelenideElement rejectButton = $x("//button[contains(.,'Deny')]");

        if (rejectButton.exists()) {
            rejectButton
                    .shouldBe(visible, Duration.ofSeconds(5))
                    .click();
            rejectButton.should(disappear, Duration.ofSeconds(5));
        }
    }
}
