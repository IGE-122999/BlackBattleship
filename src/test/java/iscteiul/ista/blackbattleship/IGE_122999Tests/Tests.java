package iscteiul.ista.blackbattleship.IGE_122999Tests;

import iscteiul.ista.blackbattleship.BattleShipMainPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class Tests {

    private BattleShipMainPage battleShipMainPage;

    @BeforeEach
    public void setUp() {

        battleShipMainPage = new BattleShipMainPage();

        battleShipMainPage.openPage();
    }

    @AfterEach
    public void tearDown() {

        closeWebDriver();
    }

    @Test
    public void shouldStartGameAgainstRobot() {

        battleShipMainPage.acceptCookies();

        battleShipMainPage.startRobotGame();

        battleShipMainPage.verifyGameStarted();
    }

    @Test
    public void shouldShowShotResult() {

        battleShipMainPage.acceptCookies();

        battleShipMainPage.startRobotGame();

        battleShipMainPage.shootRandomCell();

        battleShipMainPage.verifyShotResult();
    }

    @Test
    public void shouldShowEndGameCondition() {

        battleShipMainPage.acceptCookies();

        battleShipMainPage.startRobotGame();

        battleShipMainPage.playUntilGameEnds();

        battleShipMainPage.verifyGameEnded();
    }

    @Test
    public void shouldShowLeaderboard() {

        battleShipMainPage.acceptCookies();

        battleShipMainPage.openLeaderboard();

        battleShipMainPage.verifyLeaderboardVisible();
    }
}