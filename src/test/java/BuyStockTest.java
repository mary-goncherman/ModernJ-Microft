import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/features",
        glue = "src/test/java/glue",
        dryRun = false,
        strict = false,
        snippets = SnippetType.UNDERSCORE
)
public class BuyStockTest {
}
