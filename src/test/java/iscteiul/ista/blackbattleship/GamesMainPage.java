package iscteiul.ista.blackbattleship;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class GamesMainPage {

    SelenideElement battleshipBtn = $x("//*[contains(text(),'Battleship')]");
    SelenideElement getOpponentBoard = $("div.opponent").$("table.table-board");
}
