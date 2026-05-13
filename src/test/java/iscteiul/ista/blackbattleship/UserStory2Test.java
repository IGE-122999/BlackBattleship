package iscteiul.ista.blackbattleship;

import org.junit.jupiter.api.Test;

/**
 * Classe de testes JUnit que executa os cenários associados à User Story 2.
 *
 * Cada teste corresponde a um dos cenários presentes no ficheiro
 * TestSuite_123025.side, mas agora usando o Page Object UserStory2.
 */
public class UserStory2Test {

    @Test
    public void testUS01_US29() {
        UserStory2 page = new UserStory2();

        page.openPage();
        page.clickPlayVsRobot();
        page.closeBrowser();
    }

    @Test
    public void testUS14() {
        UserStory2 page = new UserStory2();

        page.openPage();
        page.clickPlayVsRobot();
        page.selectCells(2); // duas coordenadas
        page.closeBrowser();
    }

    @Test
    public void testUS17() {
        UserStory2 page = new UserStory2();

        page.openPage();
        page.clickPlayVsRobot();
        page.selectCells(4); // quatro coordenadas
        page.closeBrowser();
    }

    @Test
    public void testUS26() {
        UserStory2 page = new UserStory2();

        page.openPage();
        page.openMenu();
        page.openUserCredit();
        page.openMyAccount();
        page.openMyProfile();
        page.closeBrowser();
    }
}

