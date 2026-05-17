package iscteiul.ista.blackbattleship.IGE_110764Tests;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

/**
 * Contém os localizadores e operações usadas nos testes de aceitação
 * sobre o jogo Battleship do PaperGames.
 */
public class IGE110764Page {

    /**
     * Abre diretamente a página do jogo Battleship.
     */
    public void openBattleshipPage() {
        open("https://papergames.io/en/battleship");
    }

    /**
     * Fecha o pop-up de consentimento, caso apareça.
     */
    public void closePopupIfPresent() {
        SelenideElement rejectButton = $x("//*[contains(text(),'not consent')]");

        if (rejectButton.exists()) {
            rejectButton
                    .shouldBe(visible, Duration.ofSeconds(5))
                    .click();

            rejectButton.should(disappear, Duration.ofSeconds(5));
        }
    }

    /**
     * Preenche o nome de convidado, caso o campo apareça.
     *
     * @param username nome do jogador
     */
    public void fillUsernameIfPresent(String username) {
        SelenideElement usernameInput = $("[formcontrolname='username']");

        if (usernameInput.exists()) {
            usernameInput
                    .shouldBe(visible, Duration.ofSeconds(10))
                    .setValue(username);

            clickContinueIfPresent();
        }
    }

    /**
     * Clica no botão Continue, caso exista.
     */
    public void clickContinueIfPresent() {
        SelenideElement continueButton =
                $x("//button[contains(text(), 'Continue') or .//span[contains(text(), 'Continue')]]");

        if (continueButton.exists()) {
            continueButton
                    .shouldBe(visible, Duration.ofSeconds(10))
                    .click();
        }
    }

    /**
     * Inicia um jogo contra robot/IA.
     */
    public void startRobotGame() {
        $x("//*[contains(text(), 'robot') or contains(text(), 'Robot')]")
                .shouldBe(visible, Duration.ofSeconds(15))
                .click();

        sleep(2000);

        fillUsernameIfPresent("Dinis110764");

        chooseBattleshipServerIfPresent();

        clickContinueIfPresent();

        sleep(4000);
    }

    /**
     * Escolhe o servidor/modo Battleship, caso seja pedido pela interface.
     */
    public void chooseBattleshipServerIfPresent() {
        SelenideElement select = $x("//*[@id='mat-select-serverApp0']");

        if (select.exists()) {
            select
                    .shouldBe(visible, Duration.ofSeconds(10))
                    .click();

            $x("//div[contains(text(),'Battleship')]")
                    .shouldBe(visible, Duration.ofSeconds(10))
                    .click();
        }
    }

    /**
     * Cria uma sala para jogar com um amigo.
     */
    public void createFriendGame() {
        $x("//span[contains(text(), 'Play with a friend')]")
                .shouldBe(visible, Duration.ofSeconds(15))
                .click();

        sleep(2000);

        fillUsernameIfPresent("Dinis110764");

        sleep(5000);
    }

    /**
     * Devolve o link de convite gerado pela aplicação.
     *
     * @return link de convite
     */
    public String getInvitationLink() {
        SelenideElement invitationLink = $x("//app-copy-text//span")
                .shouldBe(visible, Duration.ofSeconds(20));

        return invitationLink.getText();
    }

    /**
     * Abre um link de convite.
     *
     * @param link link gerado pela sala privada
     */
    public void openInvitationLink(String link) {
        open(link);
        closePopupIfPresent();
        sleep(3000);
    }

    /**
     * Verifica se a página/jogo está visível.
     */
    public void shouldShowPage() {
        $("body").shouldBe(visible, Duration.ofSeconds(15));
    }

    /**
     * Verifica se a zona de jogo foi carregada.
     */
    public void shouldShowGameArea() {
        $x("//*[contains(text(),'Your boats') or contains(text(),'boats') or contains(text(),'Boats')]")
                .shouldBe(visible, Duration.ofSeconds(30));
    }

    /**
     * Devolve todas as células do tabuleiro.
     *
     * @return coleção de células
     */
    public ElementsCollection getBoardCells() {
        return $$("td[class*='cell-']");
    }

    /**
     * Devolve células ainda disponíveis para disparo.
     *
     * @return coleção de células disponíveis
     */
    public ElementsCollection getAvailableCells() {
        return $$("td[class*='cell-']").filterBy(cssClass("null"));
    }

    /**
     * Verifica se existe uma grelha com pelo menos 100 células,
     * correspondente a uma grelha 10x10.
     */
    public void shouldHave10x10Grid() {
        getBoardCells()
                .shouldHave(sizeGreaterThanOrEqual(100), Duration.ofSeconds(30));
    }

    /**
     * Verifica se a frota do jogador está visível.
     */
    public void shouldShowPlayerFleet() {
        $x("//*[contains(text(),'Your boats')]")
                .shouldBe(visible, Duration.ofSeconds(30));

        $$("img, svg, td[class*='cell-']")
                .shouldHave(sizeGreaterThan(0), Duration.ofSeconds(30));
    }

    /**
     * Dispara numa célula disponível.
     *
     * @return célula onde foi feito o disparo
     */
    public SelenideElement shootAvailableCell() {
        ElementsCollection availableCells = getAvailableCells()
                .shouldHave(sizeGreaterThan(0), Duration.ofSeconds(30));

        SelenideElement cell = availableCells.first();
        cell.shouldBe(visible).click();

        return cell;
    }

    /**
     * Verifica se o disparo teve resultado visual: acerto ou falha.
     *
     * @param cell célula onde foi feito o disparo
     */
    public void shouldShowShotResult(SelenideElement cell) {
        cell.$("svg.hit, svg.no-hit")
                .should(exist, Duration.ofSeconds(10));
    }
}
