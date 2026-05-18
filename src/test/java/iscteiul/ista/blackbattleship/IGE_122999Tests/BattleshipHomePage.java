package iscteiul.ista.blackbattleship.IGE_122999Tests;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

/**
 * Page Object da página Battleship.
 */
public class BattleshipHomePage {

    public SelenideElement playWithRobotButton =
            $x("//button[contains(., 'robot') or .//span[contains(., 'robot')]]");

    public SelenideElement playWithFriendButton =
            $x("//button[contains(., 'friend') or .//span[contains(., 'friend')]]");
}