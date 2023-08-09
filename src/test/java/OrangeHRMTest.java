package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.example.orangehrmtest.LoginPage;
import com.example.orangehrmtest.AdminPage;
import java.time.Duration;

public class OrangeHRMTest {
    WebDriver driver;
    LoginPage loginPage;
    AdminPage adminPage;
    int initialRecordCount;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://opensource-demo.orangehrmlive.com/");
        loginPage = new LoginPage(driver);
        adminPage = new AdminPage(driver);
    }

    @Test(priority = 1)
    public void loginTest() {
        loginPage.login("Admin", "admin123");
        Assert.assertTrue(adminPage.isAdminTabVisible(), "Login failed or Admin tab not found!");
    }

    @Test(priority = 2)
    public void addUserTest() {
        adminPage.navigateToAdminTab();
        initialRecordCount = adminPage.getRecordCount();
        adminPage.addUser("TestUser", "Admin", "Enabled", "TestPassword123");
        Assert.assertEquals(adminPage.getRecordCount(), initialRecordCount + 1, "User count did not increase!");
    }

    @Test(priority = 3)
    public void deleteUserTest() {
        adminPage.searchUser("TestUser");
        adminPage.deleteUser();
        Assert.assertEquals(adminPage.getRecordCount(), initialRecordCount, "User count did not decrease!");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
