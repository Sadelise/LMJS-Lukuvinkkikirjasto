package lukuvinkkikirjasto;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.util.ArrayList;
import java.util.List;
import lukuvinkkikirjasto.dao.BasicTipDao;
import lukuvinkkikirjasto.io.StubIO;
import static org.junit.Assert.*;

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
        inputLines.add("quit");
        io = new StubIO(inputLines);
        tipDao = new BasicTipDao();
        Main.run(io, tipDao);
    }

    @Then("^system will respond with \"([^\"]*)\"$")
    public void system_will_respond_with(String expectedOutput) throws Throwable {
        assertTrue(io.getOutput().contains(expectedOutput));
    }

    @Given("^a book has been added$")
    public void a_book_has_been_added() throws Throwable {
        inputLines.add("add");
        inputLines.add("title");
        inputLines.add("author");

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
        inputLines.add(""+num);
        inputLines.add("quit");
        io = new StubIO(inputLines);
        tipDao = new BasicTipDao();
        Main.run(io, tipDao);
    }
}
