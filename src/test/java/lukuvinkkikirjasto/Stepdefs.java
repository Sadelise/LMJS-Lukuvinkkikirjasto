package lukuvinkkikirjasto;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.util.ArrayList;
import java.util.List;
import lukuvinkkikirjasto.dao.BasicTipDao;
import lukuvinkkikirjasto.io.StubIO;
import static org.junit.Assert.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Stepdefs {

    List<String> inputLines = new ArrayList<>();
    StubIO io;
    BasicTipDao tipDao;
    
    @Given("^command add is selected$")
    public void command_add_selected() throws Throwable {
        inputLines.add("add");
    }

    @When("^title \"([^\"]*)\" and author \"([^\"]*)\" are entered$")
    public void title_and_author_are_entered(String title, String author) throws Throwable {
        inputLines.add(title);
        inputLines.add(author);
    }

    @Then("^system will respond with \"([^\"]*)\"$")
    public void system_will_respond_with(String expectedOutput) throws Throwable {
        runProgram();

        assertTrue(io.getOutput().contains(expectedOutput));
    }

    @Then("^system response will contain \"([^\"]*)\"$")
    public void system_response_will_contain(String expectedOutput) throws Throwable {
        runProgram();

        List<String> output = io.getOutput();
        boolean stringContained = false;
        for (String string : output) {
            if (string.contains(expectedOutput)) {
                stringContained = true;
            }
        }

        assertTrue(stringContained);
    }

    @Given("^a book has been added$")
    public void a_book_has_been_added() throws Throwable {
        inputLines.add("add");
        inputLines.add("title");
        inputLines.add("author");

    }

    @Given("^a book titled \"([^\"]*)\" by \"([^\"]*)\" has been added$")
    public void specific_book_has_been_added(String title, String author) throws Throwable {
        inputLines.add("add");
        inputLines.add(title);
        inputLines.add(author);
    }

    @Given("^command edit has been selected$")
    public void command_edit_has_been_selected() throws Throwable {
        inputLines.add("edit");
    }

    @And("^command view has been selected$")
    public void command_view_has_been_selected() throws Throwable {
        inputLines.add("view");

    }

    @When("^when user inputs \"([^\"]*)\"$")
    public void when_user_inputs(String input) throws Throwable {
        inputLines.add(input);

    }

    @And("^gives the correct book number of (\\d+)$")
    public void gives_the_correct_book_number_Of(int num) throws Throwable {
        inputLines.add("" + num);
    }

    @And("^command remove has been selected$")
    public void command_remove_has_been_selected() throws Throwable {
        inputLines.add("remove");
    }

    @When("^user gives the book number (\\d+)$")
    public void user_gives_the_book_number(int num) throws Throwable {
        inputLines.add("" + num);
    }

    @When("^when user gives the number (\\d+)$")
    public void when_user_gives_the_number(int num) throws Throwable {
        inputLines.add("" + num);
    }

    @And("^when user enters \"([^\"]*)\"$")
    public void when_user_enters(String input) throws Throwable {
        inputLines.add(input);
        runProgram();
    }

    private void runProgram() {
        inputLines.add("quit");
        inputLines.add("quit");
        io = new StubIO(inputLines);
        tipDao = new BasicTipDao();
        Main.run(io, tipDao);
    }
}
