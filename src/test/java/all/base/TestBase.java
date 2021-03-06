package all.base;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class TestBase {
    protected WebDriver driver;
    protected WebDriverWait wait;
    public String initialWindow;

    public Set<String> getAllWindows() {
        return driver.getWindowHandles();
    }

    public void switchToFirstWindow() {
        var newWindows = getAllWindows().stream().filter(i -> !i.equals(initialWindow)).collect(Collectors.toSet());
        driver.switchTo().window(newWindows.stream().findFirst().get());
    }

    public void switchToWindow(String windowId) {
        driver.switchTo().window(windowId);
    }

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        var options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("incognito");
        options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.IGNORE);
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        driver = new ChromeDriver(options);
        initialWindow = driver.getWindowHandle();
        wait = new WebDriverWait(driver, 10);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterEach
    public void tearDown() throws IOException {
        try {
            takeScreenshot();
        } catch (UnhandledAlertException alertException) {
            Alert alert = driver.switchTo().alert();
            alert.accept();
            takeScreenshot();
        }
        driver.quit();
    }

    private void takeScreenshot() throws IOException {
        var sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(sourceFile, new File("Screenshots/screenShots"));
    }
}