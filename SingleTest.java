package com.browserstack;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v85.network.Network;
import org.openqa.selenium.devtools.v85.network.model.ConnectionType;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Optional;

public class SingleTest extends BrowserStackTestNGTest {

    @Test
    public void test() throws Exception {
       ChromeDriver driver = new ChromeDriver();

        DevTools devTools = driver.getDevTools();
        devTools.createSession();
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
        devTools.send(Network.emulateNetworkConditions(
                false,
                100,
                2000,
                50000,
                Optional.of(ConnectionType.CELLULAR3G)
        ));
        driver.get("https://www.demosite.com");

        Assert.assertTrue( driver.findElement(By.cssSelector("[data-testid='skeleton']")).isDisplayed(), "Loading skeleton is present");
    }
}
