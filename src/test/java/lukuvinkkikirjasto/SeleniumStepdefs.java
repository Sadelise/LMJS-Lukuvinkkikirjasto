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
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import lukuvinkkikirjasto.dao.BasicTipDao;
import lukuvinkkikirjasto.io.StubIO;
import static org.junit.Assert.assertTrue;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 *
 * @author lmantyla
 */
public class SeleniumStepdefs {

    WebDriver driver;
    String baseUrl = "http://localhost:8080";

    public SeleniumStepdefs() {
        if (System.getenv("TRAVIS") == null) {
            driver = new ChromeDriver();
        } else {
            File file;
            if (System.getProperty("os.name").matches("Mac OS X")) {
                file = new File("lib/macgeckodriver");
            } else {
                file = new File("lib/geckodriver");
            }
            String absolutePath = file.getAbsolutePath();
            System.setProperty("webdriver.gecko.driver", absolutePath);

            this.driver = new FirefoxDriver();
        }
    }

    @Given("^the page \"([^\"]*)\" has been selected$")
    public void page_selected(String page) throws Throwable {
        driver.get(baseUrl + "/" + page);
    }

    @Given("^a book by title \"([^\"]*)\" and author \"([^\"]*)\" and description \"([^\"]*)\" exists$")
    public void page_selected(String title, String author, String description) throws Throwable {
        add_book(title, author, description, "");
        Thread.sleep(3000);
    }

    @When("^title \"([^\"]*)\" and author \"([^\"]*)\" are entered into correct fields$")
    public void title_and_author_are_entered_into_fields(String title, String author) throws Throwable {
        add_book(title, author, "", "");
    }

    @When("^only title \"([^\"]*)\" is entered into the correct field$")
    public void only_title_entered_into_fields(String title) throws Throwable {
        add_book(title, "", "", "");
    }

    @When("^only author \"([^\"]*)\" is entered into the correct field$")
    public void only_author_entered_into_fields(String author) throws Throwable {
        add_book("", author, "", "");
    }

    @When("^title \"([^\"]*)\" and author \"([^\"]*)\" and description \"([^\"]*)\" "
            + "and ISBN \"([^\"]*)\" are entered into correct fields$")
    public void only_title_entered_into_fields(String title, String author, String description, String ISBN) throws Throwable {
        add_book(title, author, description, ISBN);
    }

    @When("^the delete button is clicked$")
    public void the_delete_button_is_clicked() throws Throwable {
        Thread.sleep(1000);
        WebElement element = driver.findElement(By.name("delete"));
        element.click();
    }

    @Then("^the confirmation prompt is accepted$")
    public void the_prompt_is_accepted() throws Throwable {
        Thread.sleep(1000);
        driver.switchTo().alert().accept();
    }

    @Then("^the confirmation prompt is not accepted$")
    public void the_prompt_is_not_accepted() throws Throwable {
        Thread.sleep(1000);
        driver.switchTo().alert().dismiss();
    }

    @Then("^the page will contain the message \"([^\"]*)\"$")
    public void page_will_contain_message(String pageContent) throws Throwable {
        //driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Thread.sleep(1000);
        assertTrue(driver.getPageSource().contains(pageContent));
        driver.get(baseUrl + "/books");
    }

    @Then("^the page will contain \"([^\"]*)\"$")
    public void page_will_contain(String pageContent) throws Throwable {
        //driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Thread.sleep(1000);
        assertTrue(driver.getPageSource().contains(pageContent));
    }

    @When("^the book \"([^\"]*)\" is selected$")
    public void the_book_is_selected(String book) throws Throwable {
        Thread.sleep(1000);
        WebElement element = driver.findElement(By.linkText(book));
        element.click();
    }

    @When("^the description \"([^\"]*)\" is entered$")
    public void the_description_is_entered(String desc) throws Throwable {
        Thread.sleep(1000);
        WebElement element = driver.findElement(By.name("description"));
        element.sendKeys(desc);
        element = driver.findElement(By.name("save"));
        element.submit();
    }

    @When("^the book is marked read$")
    public void the_book_is_marked_read() throws Throwable {
        Thread.sleep(1000);
        WebElement element = driver.findElement(By.name("read"));
        element.click();
        element = driver.findElement(By.name("save"));
        element.submit();
    }

    @When("^keyword \"([^\"]*)\" has been submitted$")
    public void search_feature_finds_content(String keyword) throws Throwable {
        WebElement element = driver.findElement(By.name("keyword"));
        element.sendKeys(keyword);
        driver.findElement(By.name("search")).submit();
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    private void add_book(String title, String author, String description, String ISBN) {
        WebElement element = driver.findElement(By.name("title"));
        element.sendKeys(title);
        element = driver.findElement(By.name("author"));
        element.sendKeys(author);
        element = driver.findElement(By.name("description"));
        element.sendKeys(description);
        element = driver.findElement(By.name("ISBN"));
        element.sendKeys(ISBN);

        driver.findElement(By.name("sendtip")).submit();
    }
}
