package iscteiul.ista.blackbattleship;

import static com.codeborne.selenide.Selenide.*;
import com.codeborne.selenide.SelenideElement;

/**
 * Page Object Model, usando Selenide.
 * Esta classe contém os localizadores e ações sobre a página.
 */
public class SelenideBattery {

    private final String url = "https://papergames.io/en/battleship";

    private final SelenideElement playVsRobotBtn = $(".w-100:nth-child(2) .btn .flex-grow-1");
    private final SelenideElement gridCell = $(".hover");

    /**
     * Abre a página do jogo
     */
    public void openPage() {
        open(url);
    }

    /**
     * Clica no botão "Play vs Robot"
     */
    public void clickPlayVsRobot() {
        playVsRobotBtn.click();
    }

    /**
     * Seleciona uma coordenada no tabuleiro
     */
    public void selectCoordinate() {
        gridCell.click();
    }

    /**
     * Executa duas jogadas
     */
    public void playTwoMoves() {
        selectCoordinate();
        selectCoordinate();
    }
}
