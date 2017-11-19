package lukuvinkkikirjasto;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import lukuvinkkikirjasto.io.StubIO;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty"})
public class MainTest {
}
