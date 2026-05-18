package iscteiul.ista.blackbattleship;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Testes simples da página JetBrains gerada no projeto-piloto.
 */
public class MainPageTest {

    MainPage mainPage = new MainPage();

    @BeforeAll
    public static void setUpAll() {
        Configuration.browserSize = "1280x800";
        Configuration.timeout = 10000;
    }

    @BeforeEach
    public void setUp() {
        open("https://www.jetbrains.com/");
    }

    @Test
    public void shouldOpenJetBrainsHomePage() {
        mainPage.pageBody.shouldBe(visible);
        assertTrue(Selenide.title().contains("JetBrains"));
    }

    @Test
    public void shouldContainJetBrainsText() {
        mainPage.pageBody.shouldHave(text("JetBrains"));
    }

    @AfterEach
    public void tearDown() {
        closeWebDriver();
    }
}