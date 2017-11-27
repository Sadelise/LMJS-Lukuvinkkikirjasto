/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lukuvinkkikirjasto;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.util.ArrayList;
import java.util.List;
import lukuvinkkikirjasto.dao.BasicTipDao;
import lukuvinkkikirjasto.io.StubIO;
import static org.junit.Assert.assertTrue;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 *
 * @author lmantyla
 */
public class SeleniumStepdefs {

    WebDriver driver = new ChromeDriver();
    String baseUrl = "http://localhost:8080";

    @Given("^the page \"([^\"]*)\" has been selected$")
    public void page_selected(String page) throws Throwable {
        driver.get(baseUrl + "/" + page);
        //WebElement element = driver.findElement(By.linkText("login"));
        //element.click();
    }

    @When("^title \"([^\"]*)\" and author \"([^\"]*)\" are entered into correct fields$")
    public void title_and_author_are_entered_into_fields(String title, String author) throws Throwable {
        WebElement element = driver.findElement(By.name("title"));
        element.sendKeys(title);
        element = driver.findElement(By.name("author"));
        element.sendKeys(author);
        driver.findElement(By.name("submit")).submit();
    }

    @Then("^the page will contain \"([^\"]*)\"$")
    public void page_will_contain(String pageContent) throws Throwable {
        assertTrue(driver.getPageSource().contains(pageContent));
    }

    @After
    public void tearDown() {
        driver.quit();
    }

}
