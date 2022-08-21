package cucumber.options;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/java/features",plugin={"json:target/jsonReports/cucumber-report.json", "pretty", "html:target/html-report/cucumber-html-reports.html"}, glue={"stepDefinitions"})
public class TestRunner {

}
