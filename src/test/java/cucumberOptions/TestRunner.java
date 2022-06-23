package cucumberOptions;



import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

//@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/features", glue = {"stepDefinations"}, monochrome = true, plugin = {
		"pretty", 
		"html:target/cucumber-reports/cucumber.html", 
		"json:target/cucumber-reports/cucumber.json",
		 }, dryRun = false)
public class TestRunner extends AbstractTestNGCucumberTests{


}
