package iscteiul.ista.blackbattleship;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

/**
 * Page Object Model para todos os cenários associados à User Story 2.
 *
 * Esta classe agrega todos os elementos e operações usados nos testes
 * presentes no ficheiro TestSuite_123025.side.
 *
 * Inclui:
 *  - Abertura da página
 *  - Interação com o botão "Play vs robot"
 *  - Seleção de coordenadas no tabuleiro
 *  - Acesso ao menu e ao perfil do utilizador
 *
 * Todos os seletores foram extraídos diretamente do ficheiro .side.
 */
public class UserStory2 {

    // -----------------------------
    // Elementos principais da página
    // -----------------------------

    /** Botão "Play vs robot" */
    private final SelenideElement playVsRobotBtn =
            $("css=.w-100:nth-child(2) > .btn .flex-grow-1");

    /** Célula atualmente em hover (usada pelo Selenium IDE) */
    private final SelenideElement hoverCell =
            $("css=.hover");

    /** Botão do menu (ícone no canto superior direito) */
    private final SelenideElement menuButton =
            $("css=.cdk-focused > .mat-mdc-button-touch-target");

    /** Botão com o nome do utilizador */
    private final SelenideElement userCreditButton =
            $("css=.credit");

    /** Botão "My account" */
    private final SelenideElement myAccountBtn =
            $("linkText=My account");

    /** Botão "My profile" */
    private final SelenideElement myProfileBtn =
            $("css=.cursor-pointer > .mat-mdc-menu-item-text > span");


    // -----------------------------
    // Métodos de interação
    // -----------------------------

    /**
     * Abre a página principal do Battleship.
     */
    public void openPage() {
        open("https://papergames.io/en/battleship");
    }

    /**
     * Clica no botão "Play vs robot".
     */
    public void clickPlayVsRobot() {
        playVsRobotBtn.click();
    }

    /**
     * Seleciona uma célula do tabuleiro.
     */
    public void selectCell() {
        hoverCell.click();
    }

    /**
     * Seleciona múltiplas células do tabuleiro.
     *
     * @param count número de células a selecionar
     */
    public void selectCells(int count) {
        for (int i = 0; i < count; i++) {
            selectCell();
        }
    }

    /**
     * Abre o menu do utilizador.
     */
    public void openMenu() {
        menuButton.click();
    }

    /**
     * Abre o submenu do utilizador (onde aparece o nome).
     */
    public void openUserCredit() {
        userCreditButton.click();
    }

    /**
     * Abre a página "My account".
     */
    public void openMyAccount() {
        myAccountBtn.click();
    }

    /**
     * Abre a página "My profile".
     */
    public void openMyProfile() {
        myProfileBtn.click();
    }

    /**
     * Fecha o browser.
     */
    public void closeBrowser() {
        closeWebDriver();
    }
}

