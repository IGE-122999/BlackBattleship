package iscteiul.ista.blackbattleship.IGE_123011Tests;

import com.codeborne.selenide.Configuration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Configuration.timeout;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Tests {
    BattleShipMainPage mainPage = new BattleShipMainPage();

    @BeforeAll
    public static void setUpAll(){
        Configuration.browserSize = "1280x800";
        Configuration.pageLoadTimeout = 120000;
        Configuration.pageLoadStrategy = "eager";
        timeout = 15000;
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    public void setUp(){
        open("https://papergames.io/en/battleship");
        sleep(10000);
        closePopUp();
        sleep(1000);
    }
    public void closePopUp(){
        SelenideElement rejectButton = $x("//*[contains(text(),'not consent')]");

        if (rejectButton.exists()) {
            rejectButton
                    .shouldBe(visible, Duration.ofSeconds(5))
                    .click();
            rejectButton.should(disappear, Duration.ofSeconds(5));
        }
    }

    public void login(){
        $("[formcontrolname='username']").sendKeys("user1");

        sleep(1000);

        $x("//button[contains(text(), 'Continue')]")
                .shouldBe(visible)
                .click();

    }
    @Test
    @DisplayName("Criar sala privada")
    public void playwithFriend() {
        sleep(3000);

        $x("//span[contains(text(), 'Play with a friend')]")
                .shouldBe(visible)
                .click();

        sleep(2000);

        login();


        sleep(5000);

        SelenideElement qrCode = $x("//qrCode//canvas");
        qrCode.shouldBe(visible);

        SelenideElement actualLink   = $x("//app-copy-text//span");
        actualLink.shouldBe(visible).click();
        String generatedLink = actualLink.getText();
        sleep(2000);
    }
    @Test
    @DisplayName("Teste Jogar Online")
    public void testJogarOnline() {
        sleep(3000);
        $x("//span[contains(text(), 'Play online')]").shouldBe(visible).click();
        sleep(2000);
        login();
        sleep(5000);
    }

    @Test
    @DisplayName("Abortar Jogo")
    public void abortarJogo() {
        sleep(2000);
        testJogarOnline();
        $$("button")
                .findBy(exactText("Abort game"))
                .shouldBe(visible, Duration.ofSeconds(30))
                .click();
        sleep(2000);
        $("footer").shouldBe(visible);
        sleep(2000);
        $("footer").$$("button").findBy(exactText("Abort game")).shouldBe(visible).click();
        sleep(2000);
    }

}
