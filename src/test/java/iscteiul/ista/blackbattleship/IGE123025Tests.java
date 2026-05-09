package iscteiul.ista.blackbattleship;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.Random;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Configuration.timeout;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IGE123025Tests {

    GamesMainPage mainPage = new GamesMainPage();

    @BeforeAll
    public static void setUpAll() {
        Configuration.browserSize = "1280x800";
        timeout = 10000;  // espera de 10s
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    public void setUp() {
        open("https://papergames.io/en/");
        sleep(3000);
        closePopup();
        sleep(1000);
        mainPage.battleshipBtn.shouldBe(visible).click();
    }

    @Test
    public void playAsGuest() {
        sleep(3000);

        $x("//*[contains(text(), 'robot')]")
                .shouldBe(visible)
                .click();

        sleep(2000);

        $("[formcontrolname='username']").sendKeys("user1");

        sleep(1000);

        $x("//button[contains(text(), 'Continue')]")
                .shouldBe(visible)
                .click();

        sleep(5000);

        $x("//*[contains(text(), 'Your boats')]")
                .shouldBe(visible);
    }

    @Test
    public void sunkenShipDetection() {
        sleep(3000);

        $x("//*[contains(text(), 'robot')]")
                .shouldBe(visible)
                .click();

        sleep(2000);

        $x("//*[@id='mat-select-serverApp0']")
                .shouldBe(visible)
                .click();

        sleep(2000);

        $x("//div[contains(text(),'Battleship')]")
                .shouldBe(visible)
                .click();

        sleep(2000);

        $x("//*[contains(text(), 'Continue')]")
                .shouldBe(visible)
                .click();

        sleep(2000);

        boolean shipSunken = false;
        Random rand = new Random();

        for (int i = 0; i < 50; i++) {
            ElementsCollection availableCells = $$("td[class*='cell-']")
                    .filterBy(cssClass("null"));

            SelenideElement cell = availableCells.get(rand.nextInt(availableCells.size()));

            cell.click();

            sleep(500);

            if($("img.is-destroyed").exists()) {
                shipSunken = true;
                break;
            }
        }

        assertTrue(shipSunken, "Nenhum navio destruído");
    }

    void closePopup() {
        SelenideElement rejectButton = $x("//*[contains(text(),'not consent')]");

        if (rejectButton.exists()) {
            rejectButton
                    .shouldBe(visible, Duration.ofSeconds(5))
                    .click();
            rejectButton.should(disappear, Duration.ofSeconds(5));
        }
    }
}
