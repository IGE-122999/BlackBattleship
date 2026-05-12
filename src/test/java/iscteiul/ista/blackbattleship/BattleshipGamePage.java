package iscteiul.ista.blackbattleship;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class BattleshipGamePage {

    public SelenideElement opponentBoard() {
        return $("div.opponent").$("table.table-board");
    }

    public SelenideElement attackHeader() {
        return $("div.opponent .header.attack");
    }

    public SelenideElement yourBoatsText() {
        return $x("//*[contains(text(),'Your boats')]");
    }

    public SelenideElement resignButton() {
        return $x("//*[contains(text(),'Resign')]");
    }

    public SelenideElement body() {
        return $("body");
    }

    public SelenideElement cell(int x, int y) {
        return opponentBoard().$("td.cell-" + x + "-" + y);
    }
}