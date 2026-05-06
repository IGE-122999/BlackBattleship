package iscteiul.ista.blackbattleship;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

// page_url = https://www.jetbrains.com/
public class MainPage {
    public SelenideElement seeDeveloperToolsButton = $x("//*[@data-test-marker='Products']");
    public SelenideElement findYourToolsButton = $x("//*[@data-test='suggestion-link']");
    public SelenideElement toolsMenu = $x("//div[@data-test='main-menu-item' and @data-test-marker = 'Products']");
    public SelenideElement searchButton = $("[data-test='site-header-search-action']");
}
