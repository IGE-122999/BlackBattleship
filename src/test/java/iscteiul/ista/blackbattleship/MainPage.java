package iscteiul.ista.blackbattleship;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class MainPage {

    public SelenideElement logo = $("a[href='/']");
    public SelenideElement searchButton = $("[data-test='site-header-search-action']");
    public SelenideElement pageBody = $("body");
}