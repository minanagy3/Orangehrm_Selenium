package com.example.orangehrmtest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AdminPage {
    WebDriver driver;
    By adminTab = By.id("menu_admin_viewAdminModule");
    By addUserButton = By.id("btnAdd");
    By userCountText = By.id("recordsFound");
    By usernameField = By.id("systemUser_userName");
    By saveButton = By.id("btnSave");
    By searchButton = By.id("searchBtn");
    By deleteButton = By.id("btnDelete");

    public AdminPage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateToAdminTab() {
        driver.findElement(adminTab).click();
    }

    public boolean isAdminTabVisible() {
        return driver.findElement(adminTab).isDisplayed();
    }

    public int getRecordCount() {
        return Integer.parseInt(driver.findElement(userCountText).getText());
    }

    public void addUser(String username, String role, String status, String password) {
        driver.findElement(addUserButton).click();
        driver.findElement(usernameField).sendKeys(username);
        driver.findElement(saveButton).click();
    }

    public void searchUser(String username) {
        driver.findElement(usernameField).sendKeys(username);
        driver.findElement(searchButton).click();
    }

    public void deleteUser() {
        driver.findElement(deleteButton).click();
    }
}
