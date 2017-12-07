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
import java.io.File;
import static org.junit.Assert.assertTrue;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

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

    @After
    public void tearDown() throws Throwable {
        Thread.sleep(1000);

        while (driver.getPageSource().contains("hiddenDelete")) {
            try {
                WebElement element = driver.findElement(By.name("hiddenDelete"));
                element.submit();
                Thread.sleep(1000);
            } catch (NoSuchElementException ex) {
            }
        }

        driver.quit();
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

    @When("^a book titled \"([^\"]*)\" by \"([^\"]*)\" has been added and marked read$")
    public void book_added_and_marked_read(String title, String author) throws Throwable {
        add_book(title, author, "", "");
        Thread.sleep(1000);
        WebElement element = driver.findElement(By.linkText("muokkaa"));
        element.click();
        element = driver.findElement(By.name("read"));
        element.click();
        element = driver.findElement(By.name("save"));
        element.submit();
        Thread.sleep(1000);
    }

    @Then("^a book titled \"([^\"]*)\" is found on list \"([^\"]*)\"$")
    public void a_book_titled_is_found_on_list(String title, String listName) throws Throwable {
        Thread.sleep(1000);
        WebElement element = driver.findElement(By.id(listName));
        System.out.println("ELEMENT\n " + element.getText() + "\nLOOKING FOR " + title);
        assertTrue(element.getText().contains(title));
    }

    @When("^the tip type \"([^\"]*)\" has been selected in the dropdown menu$")
    public void type_selection_selected(String choice) {
        WebElement element = driver.findElement(By.id("optionsList"));
        Select menu = new Select(element);
        menu.selectByVisibleText(choice);
    }

    @When("^title \"([^\"]*)\" and author \"([^\"]*)\" are entered into correct fields$")
    public void title_and_author_are_entered_into_fields(String title, String author) throws Throwable {
        add_book(title, author, "", "");
    }

    @When("^title \"([^\"]*)\" and URL \"([^\"]*)\" are entered into the video-adding form")
    public void title_and_URL_are_entered_into_fields(String title, String URL) throws Throwable {
        add_youtubeVideo(title, URL, "", "");
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
        WebElement element = driver.findElement(By.linkText("muokkaa"));
        element.click();
    }

    @When("^the video \"([^\"]*)\" is selected$")
    public void the_video_is_selected(String video) throws Throwable {
        Thread.sleep(1000);
        WebElement element = driver.findElement(By.linkText("muokkaa"));
        element.click();
    }

    @When("^the description \"([^\"]*)\" is entered$")
    public void the_description_is_entered(String desc) throws Throwable {
        Thread.sleep(1000);
        WebElement element = driver.findElement(By.name("description"));
        element.sendKeys(desc);
        /*element = driver.findElement(By.name("save"));
         element.submit();*/
    }

    @When("^the tag \"([^\"]*)\" is entered$")
    public void the_tag_is_entered(String tag) throws Throwable {
        Thread.sleep(1000);
        WebElement element = driver.findElement(By.name("tagString"));
        element.sendKeys(tag);
    }

    @When("^save is clicked$")
    public void save_is_clicked() throws Throwable {
        Thread.sleep(1000);
        WebElement element = driver.findElement(By.name("save"));
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

    private void add_youtubeVideo(String title, String URL, String description, String uploader) {
        WebElement element = driver.findElement(By.id("youtubeTitle"));
        element.sendKeys(title);
        element = driver.findElement(By.name("link"));
        element.sendKeys(URL);
        element = driver.findElement(By.id("youtubeDesc"));
        element.sendKeys(description);
        element = driver.findElement(By.name("uploader"));
        element.sendKeys(uploader);

        driver.findElement(By.name("sendYouTubetip")).submit();

    }
}
