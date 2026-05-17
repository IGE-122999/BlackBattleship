package iscteiul.ista.blackbattleship;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.util.Random;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class BattleShipMainPage {

    private final SelenideElement battleshipButton =
            $x("//*[text()='Battleship']");

    private final SelenideElement robotButton =
            $x("//*[contains(text(),'robot')]");

    private final SelenideElement usernameInput =
            $("[formcontrolname='username']");

    private final SelenideElement continueButton =
            $x("//button[text()='Continue']");

    private final SelenideElement opponentBoard =
            $("div.opponent").$("table.table-board");

    private final SelenideElement leaderboardButton =
            $x("//*[contains(text(),'Leaderboard')]");

    public void openPage() {
        open("https://papergames.io/en/");
        sleep(3000);

        battleshipButton.shouldBe(visible).click();
    }

    public void acceptCookies() {
        sleep(3000);

        SelenideElement rejectCookies =
                $x("//*[contains(text(),'not consent')]");

        if (rejectCookies.exists()) {
            rejectCookies.shouldBe(visible).click();
        }
    }

    public void startRobotGame() {
        robotButton.shouldBe(visible).click();

        sleep(2000);

        if (usernameInput.exists()) {
            usernameInput.shouldBe(visible).setValue("cabrito");

            continueButton.shouldBe(enabled).click();

            sleep(2000);
        }

        if ($x("//*[contains(text(),'Battleship')]").exists()) {

            $x("//*[contains(text(),'Battleship')]")
                    .shouldBe(visible)
                    .click();

            sleep(1000);

            $x("//button[text()='Continue']")
                    .shouldBe(enabled)
                    .click();
        }

        sleep(5000);
    }

    public void verifyGameStarted() {
        $x("//*[contains(text(),'Your boats')]")
                .shouldBe(visible);
    }

    public void waitForPlayerTurn() {

        for (int i = 0; i < 30; i++) {

            if ($("div.opponent .header.attack").exists()) {
                return;
            }

            sleep(1000);
        }
    }

    public void shootRandomCell() {

        waitForPlayerTurn();

        ElementsCollection cells = opponentBoard
                .$$("td[class*='cell-']");

        Random random = new Random();

        for (SelenideElement cell : cells) {

            if (cell.$("svg").exists()) {
                continue;
            }

            cell.scrollTo().click();

            sleep(1000);

            return;
        }
    }

    public void verifyShotResult() {

        opponentBoard
                .$$("svg.hit, svg.no-hit")
                .first()
                .should(exist);
    }

    public void playUntilGameEnds() {

        for (int i = 0; i < 150; i++) {

            if (
                    $x("//*[text()='YOU LOST!']").exists()
                            || $x("//*[text()='YOU WON!']").exists()
                            || $x("//*[contains(text(),'won by')]").exists()
            ) {
                return;
            }

            if ($("div.opponent .header.attack").exists()) {

                shootRandomCell();
            }

            sleep(500);
        }
    }

    public void verifyGameEnded() {

        $x("//*[text()='YOU LOST!' or text()='YOU WON!']")
                .shouldBe(visible);
    }

    public void openLeaderboard() {

        $x("//*[contains(text(),'Leaderboard')]")
                .shouldBe(visible)
                .click();
    }

    public void verifyLeaderboardVisible() {

        $x("//*[contains(text(),'Leaderboard')]")
                .shouldBe(visible);
    }

    public static class GamesMainPage {

        SelenideElement battleshipBtn = $x("//*[contains(text(),'Battleship')]");
        SelenideElement getOpponentBoard = $("div.opponent").$("table.table-board");
        SelenideElement profileBtn = $x("//button[@class='mat-mdc-menu-trigger mdc-button mat-mdc-button mat-unthemed mat-mdc-button-base' and " +
                "@aria-haspopup='menu']");
    }
}