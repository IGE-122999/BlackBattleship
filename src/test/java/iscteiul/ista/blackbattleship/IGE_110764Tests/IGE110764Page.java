package iscteiul.ista.blackbattleship.IGE_110764Tests;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

/**
 * Page Object Class para os testes do aluno 110764.
 */
public class IGE110764Page {

    public void openBattleshipPage() {
        open("https://papergames.io/en/battleship");
        sleep(5000);
    }

    public void closePopupIfPresent() {
        SelenideElement rejectButton = $x("//*[contains(text(),'not consent')]");

        if (rejectButton.exists()) {
            rejectButton
                    .shouldBe(visible, Duration.ofSeconds(5))
                    .click();

            rejectButton.should(disappear, Duration.ofSeconds(5));
        }
    }

    private void clickElementContainingText(String text) {
        Boolean clicked = executeJavaScript(
                "const wanted = arguments[0].toLowerCase();" +
                        "const elements = Array.from(document.querySelectorAll('button, a, span, div'));" +
                        "for (const el of elements) {" +
                        "  const label = (el.innerText || el.textContent || '').trim().toLowerCase();" +
                        "  const rect = el.getBoundingClientRect();" +
                        "  const visible = rect.width > 0 && rect.height > 0;" +
                        "  if (visible && label.includes(wanted)) {" +
                        "    const clickable = el.closest('button, a') || el;" +
                        "    clickable.click();" +
                        "    return true;" +
                        "  }" +
                        "}" +
                        "return false;",
                text
        );

        if (!Boolean.TRUE.equals(clicked)) {
            throw new AssertionError("Não foi encontrado elemento visível com o texto: " + text);
        }
    }

    public void fillUsernameIfPresent(String username) {
        Boolean filled = executeJavaScript(
                "const username = arguments[0];" +
                        "const inputs = Array.from(document.querySelectorAll(\"input[formcontrolname='username'], input[name='username'], input[type='text']\"));" +
                        "for (const input of inputs) {" +
                        "  const rect = input.getBoundingClientRect();" +
                        "  const visible = rect.width > 0 && rect.height > 0;" +
                        "  if (visible) {" +
                        "    input.focus();" +
                        "    input.value = username;" +
                        "    input.dispatchEvent(new Event('input', { bubbles: true }));" +
                        "    input.dispatchEvent(new Event('change', { bubbles: true }));" +
                        "    return true;" +
                        "  }" +
                        "}" +
                        "return false;",
                username
        );

        if (Boolean.TRUE.equals(filled)) {
            sleep(1000);
            clickContinueIfPresent();
        }
    }

    public void clickContinueIfPresent() {
        Boolean clicked = executeJavaScript(
                "const elements = Array.from(document.querySelectorAll('button, span'));" +
                        "for (const el of elements) {" +
                        "  const label = (el.innerText || el.textContent || '').trim().toLowerCase();" +
                        "  const rect = el.getBoundingClientRect();" +
                        "  const visible = rect.width > 0 && rect.height > 0;" +
                        "  if (visible && label.includes('continue')) {" +
                        "    const clickable = el.closest('button') || el;" +
                        "    clickable.click();" +
                        "    return true;" +
                        "  }" +
                        "}" +
                        "return false;"
        );

        if (Boolean.TRUE.equals(clicked)) {
            sleep(1000);
        }
    }

    public void chooseBattleshipServerIfPresent() {
        SelenideElement select = $("#mat-select-serverApp0");

        if (select.exists()) {
            select.shouldBe(visible, Duration.ofSeconds(10)).click();

            sleep(1000);

            clickElementContainingText("Battleship");

            sleep(1000);
        }
    }

    public void startRobotGame() {
        sleep(3000);

        clickElementContainingText("robot");

        sleep(3000);

        fillUsernameIfPresent("Dinis110764");

        sleep(2000);

        chooseBattleshipServerIfPresent();

        sleep(1000);

        clickContinueIfPresent();

        sleep(7000);
    }

    public void createFriendGame() {
        sleep(3000);

        clickElementContainingText("friend");

        sleep(3000);

        fillUsernameIfPresent("Dinis110764");

        sleep(2000);

        chooseBattleshipServerIfPresent();

        sleep(1000);

        clickContinueIfPresent();

        sleep(5000);
    }

    public String getInvitationLink() {
        SelenideElement invitationLink = $x("//app-copy-text//span")
                .shouldBe(visible, Duration.ofSeconds(20));

        return invitationLink.getText();
    }

    public void openInvitationLink(String link) {
        open(link);
        closePopupIfPresent();
        sleep(3000);
    }

    public void shouldShowPage() {
        $("body").shouldBe(visible, Duration.ofSeconds(15));
    }

    public void shouldShowGameArea() {
        $x("//*[contains(text(),'Your boats') or contains(text(),'boats') or contains(text(),'Boats')]")
                .shouldBe(visible, Duration.ofSeconds(30));
    }

    public ElementsCollection getBoardCells() {
        return $$("td[class*='cell-']");
    }

    public ElementsCollection getAvailableCells() {
        return $$("td[class*='cell-']").filterBy(cssClass("null"));
    }

    public void shouldHave10x10Grid() {
        getBoardCells()
                .shouldHave(sizeGreaterThanOrEqual(100), Duration.ofSeconds(30));
    }

    public void shouldShowPlayerFleet() {
        $x("//*[contains(text(),'Your boats')]")
                .shouldBe(visible, Duration.ofSeconds(30));

        $$("img, svg, td[class*='cell-']")
                .shouldHave(sizeGreaterThan(0), Duration.ofSeconds(30));
    }

    public SelenideElement shootAvailableCell() {
        ElementsCollection availableCells = getAvailableCells()
                .shouldHave(sizeGreaterThan(0), Duration.ofSeconds(30));

        SelenideElement cell = availableCells.first();
        cell.shouldBe(visible).click();

        return cell;
    }

    public void shouldShowShotResult(SelenideElement cell) {
        cell.$("svg.hit, svg.no-hit")
                .should(exist, Duration.ofSeconds(10));
    }
}