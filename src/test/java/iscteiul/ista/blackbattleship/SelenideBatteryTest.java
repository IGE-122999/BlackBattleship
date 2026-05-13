package iscteiul.ista.blackbattleship;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;

/**
 * Teste da UserStory2 usando Selenide.
 */
public class SelenideBatteryTest {

    private SelenideBattery page;

    /**
     * Setup antes de cada teste
     */
    @BeforeEach
    public void setUp() {
        page = new SelenideBattery();
    }

    /**
     * Teste principal
     */
    @Test
    public void test() {
        page.openPage();
        page.clickPlayVsRobot();
        page.playTwoMoves();
    }

    /**
     * Cleanup após o teste
     */
    @AfterEach
    public void tearDown() {
        closeWebDriver();
    }
}