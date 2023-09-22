import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"demo.stepsdefinations","demo.setup"},
        plugin = {"pretty"},
        dryRun = false,
        monochrome = true
)


public class SerenityRunner {
}
