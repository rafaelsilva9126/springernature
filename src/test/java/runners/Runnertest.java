package runners;

import org.junit.runner.RunWith;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;

@RunWith(Cucumber.class)
@CucumberOptions(
		 features = "src/test/resources/featurefiles",
		 glue={"stepdefinitions"},
		 snippets = SnippetType.CAMELCASE,
		 dryRun = false,
		 tags = "@test1",
		 plugin = {"pretty", "html:target/report.html", "json:target/report.json"}
		 )
public class Runnertest {

	
}
