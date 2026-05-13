package iscteiul.ista.blackbattleship;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class BattleshipHomePage {

    public SelenideElement battleshipButton() {
        return $x("//*[contains(text(),'Battleship')]");
    }

    public SelenideElement playVsRobotButton() {
        return $x("//*[contains(text(),'robot')]");
    }

    public SelenideElement nicknameInput() {
        return $("[formcontrolname='username']");
    }

    public SelenideElement gameSelect() {
        return $x("//*[@id='mat-select-serverApp0']");
    }

    public SelenideElement battleshipOption() {
        return $x("//div[contains(text(),'Battleship')]");
    }

    public SelenideElement continueButton() {
        return $x("//*[contains(text(), 'Continue')]");
    }

    public SelenideElement leaderboardContainer() {
        return $(".leaderboard-container");
    }

    public SelenideElement seeAllLeaderboardButton() {
        return $x("//a[contains(text(),'See all')]");
    }
}