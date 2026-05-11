package iscteiul.ista.blackbattleship.IGE_123011Tests;
import com.codeborne.selenide.*;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$x;


public class BattleShipMainPage {
    SelenideElement playWithFriend = $x("//span[contains(text(), 'Play with a friend')]");
    SelenideElement title = $x("//*[contains(text(), 'Battleship')]");
}
