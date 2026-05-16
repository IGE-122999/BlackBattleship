package iscteiul.ista.blackbattleship.IGE_110764Tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Configuration.timeout;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class Tests {

    IGE110764Page page = new IGE110764Page();

    /**
     * Configuração inicial do browser e do Allure.
     */
    @BeforeAll
    public static void setUpAll() {
        Configuration.browserSize = "1280x800";
        Configuration.pageLoadTimeout = 120000;
        Configuration.pageLoadStrategy = "eager";
        timeout = 15000;
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    /**
     * Abre a página Battleship antes de cada teste.
     */
    @BeforeEach
    public void setUp() {
        page.openBattleshipPage();
        page.closePopupIfPresent();
    }

    /**
     * Fecha o browser depois de cada teste.
     */
    @AfterEach
    public void tearDown() {
        closeWebDriver();
    }

    /**
     * US06 - Entrar por convite.
     */
    @Test
    @DisplayName("US06 - Entrar por convite")
    public void enterByInvitationLink() {
        page.createFriendGame();

        String invitationLink = page.getInvitationLink();

        assertTrue(
                invitationLink.contains("papergames.io"),
                "O link de convite não parece ser válido."
        );

        page.openInvitationLink(invitationLink);
        page.shouldShowPage();
    }

    /**
     * US08 - Grelha de jogo 10x10.
     */
    @Test
    @DisplayName("US08 - Grelha de jogo 10x10")
    public void shouldShow10x10Grid() {
        page.startRobotGame();
        page.shouldShowGameArea();
        page.shouldHave10x10Grid();
    }
}
