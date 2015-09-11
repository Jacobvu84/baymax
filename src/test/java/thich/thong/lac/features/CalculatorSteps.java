package thich.thong.lac.features;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import thich.thong.lac.pages.Baymax;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.notNullValue;

public class CalculatorSteps {

	@Given("^open calcutor application$")
	public void open_calcutor_application(){
		Baymax.click("calculator/window.png");
		Baymax.click("calculator/Calculator.png");
	}

	@When("^press \"([^\"]*)\" button$")
	public void press_button(String buttonValue) {
		Baymax.click("calculator/"+buttonValue+".png");
	}
	
	@Then("^the result is \"([^\"]*)\"$")
	public void the_result_is(String result) {
		assertThat(Baymax.exists("calculator/"+result+".png"),notNullValue());
	}
	
	@Then("^the result is not \"([^\"]*)\"$")
	public void the_result_is_not(String result) {
		assertThat(Baymax.exists("calculator/"+result+".png"),nullValue());
	}


}
