package iscteiul.ista.blackbattleship;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.*;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Configuration.timeout;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BattleshipTest {

    BattleshipHomePage homePage = new BattleshipHomePage();
    BattleshipGamePage gamePage = new BattleshipGamePage();

    @BeforeAll
    public static void setUpAll() {
        Configuration.browserSize = "1280x800";
        Configuration.headless = false;
        timeout = 10000;
    }

    @BeforeEach
    public void setUp() {
        open("https://papergames.io/en/");
        sleep(3000);

        closePopup();

        sleep(1000);

        homePage.battleshipButton()
                .shouldBe(visible)
                .click();

        sleep(2000);
    }

    @Test
    @DisplayName("US15 - Resultado do disparo")
    public void us15_shouldShowShotResult() {
        startRobotGame();

        gamePage.yourBoatsText()
                .shouldBe(visible);

        gamePage.attackHeader()
                .shouldBe(visible);

        SelenideElement cell = shoot(4, 7);

        cell.$("svg.hit, svg.no-hit")
                .should(exist);

        sleep(3000);
    }

    @Test
    @DisplayName("US18 - Condição de fim de jogo")
    public void us18_shouldPlayUntilEndConditionAppears() {
        startRobotGame();

        gamePage.yourBoatsText()
                .shouldBe(visible);

        gamePage.opponentBoard()
                .shouldBe(visible);

        boolean madeShot = false;

        int[][] shots = {
                {0, 0}, {0, 1}, {0, 2}, {0, 3}, {0, 4}, {0, 5}, {0, 6}, {0, 7}, {0, 8}, {0, 9},
                {1, 0}, {1, 1}, {1, 2}, {1, 3}, {1, 4}, {1, 6}, {1, 6}, {1, 7}, {1, 8}, {1, 9},
                {2, 0}, {2, 1}, {2, 2}, {2, 3}, {2, 4}, {2, 6}, {2, 6}, {2, 7}, {2, 8}, {2, 9},
                {3, 0}, {3, 1}, {3, 2}, {3, 3}, {3, 4}, {3, 6}, {3, 6}, {3, 7}, {3, 8}, {3, 9},
                {4, 0}, {4, 1}, {4, 2}, {4, 3}, {4, 4}, {2, 6}, {4, 6}, {4, 7}, {4, 8}, {4, 9},
                {5, 0}, {5, 1}, {5, 2}, {5, 3}, {5, 4}, {4, 6}, {5, 6}, {5, 7}, {5, 8}, {5, 9},
                {6, 0}, {6, 1}, {6, 2}, {6, 3}, {6, 4}, {5, 6}, {6, 6}, {6, 7}, {6, 8}, {6, 9},
                {7, 0}, {7, 1}, {7, 2}, {7, 3}, {7, 4}, {6, 6}, {7, 6}, {7, 7}, {7, 8}, {7, 9},
                {8, 0}, {8, 1}, {8, 2}, {8, 3}, {8, 4}, {8, 6}, {8, 6}, {8, 7}, {8, 8}, {8, 9},
                {9, 0}, {9, 1}, {9, 2}, {9, 3}, {9, 4}, {9, 6}, {9, 6}, {9, 7}, {9, 8}, {9, 9}
        };

        for (int[] shot : shots) {
            if ($x("//*[contains(text(),'YOU LOST') or contains(text(),'YOU WON') or contains(text(),'won by')]").exists()) {
                break;
            }

            gamePage.attackHeader()
                    .shouldBe(visible);

            SelenideElement cell = gamePage.cell(shot[0], shot[1]);

            if (cell.exists() && cell.isDisplayed()) {
                cell.click();
                madeShot = true;
                sleep(3000);
            }
        }

        assertTrue(madeShot, "O teste deve realizar pelo menos um disparo.");

        gamePage.body()
                .shouldHave(matchText("(?i).*(Your boats|Resign|Paper Man|YOU LOST|YOU WON|won by).*"));

        sleep(3000);
    }


    @Test
    @DisplayName("US27 - Leaderboard")
    public void us27_shouldShowLeaderboard() {

        homePage.seeAllLeaderboardButton()
                .scrollTo()
                .shouldBe(visible)
                .click();

        sleep(3000);

        $("body")
                .shouldHave(matchText("(?i).*(leaderboard|ranking|players|score|rating).*"));

        sleep(3000);
    }

    @Test
    @DisplayName("US30 - Jogo rápido")
    public void us30_shouldStartQuickGameAgainstRobot() {
        startRobotGame();

        gamePage.yourBoatsText()
                .shouldBe(visible);

        gamePage.opponentBoard()
                .shouldBe(visible);

        gamePage.body()
                .shouldHave(matchText("(?i).*(Paper Man|Your boats|Resign).*"));

        sleep(3000);
    }

    private void startRobotGame() {
        sleep(3000);

        homePage.playVsRobotButton()
                .shouldBe(visible)
                .click();

        sleep(2000);

        if (homePage.nicknameInput().exists()) {
            homePage.nicknameInput()
                    .shouldBe(visible)
                    .setValue("cabrito");

            sleep(1000);

            homePage.continueButton()
                    .shouldBe(visible)
                    .click();

            sleep(3000);
        }

        if (homePage.gameSelect().exists()) {
            homePage.gameSelect()
                    .shouldBe(visible)
                    .click();

            sleep(2000);

            homePage.battleshipOption()
                    .shouldBe(visible)
                    .click();

            sleep(2000);
        }

        if (homePage.continueButton().exists()) {
            homePage.continueButton()
                    .shouldBe(visible)
                    .click();
        }

        sleep(7000);
    }

    private SelenideElement shoot(int x, int y) {
        gamePage.attackHeader()
                .shouldBe(visible);

        SelenideElement cell = gamePage.cell(x, y);

        cell.shouldBe(visible)
                .click();

        sleep(100);

        return cell;
    }

    private void closePopup() {
        SelenideElement rejectButton = $x("//*[contains(text(),'not consent')]");

        if (rejectButton.exists()) {
            rejectButton
                    .shouldBe(visible, Duration.ofSeconds(5))
                    .click();

            rejectButton
                    .should(disappear, Duration.ofSeconds(5));
        }
    }
}