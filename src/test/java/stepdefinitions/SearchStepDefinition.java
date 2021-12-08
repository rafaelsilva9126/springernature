package stepdefinitions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.env.ConfigurableEnvironment;

import constants.SearchProperties;
import framework.TestStatusContainer;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import pageobjects.SearchPageObjects;

public class SearchStepDefinition {

	private TestStatusContainer testStatusContainer;
	private SearchPageObjects searchPageObjects;
	private SearchProperties searchPropertiesObj;
	private ConfigurableEnvironment searchProperties;

	public SearchStepDefinition() {
		this.searchPageObjects = new SearchPageObjects();
		this.testStatusContainer = new TestStatusContainer();
		this.searchPropertiesObj = new SearchProperties();
		this.searchProperties = searchPropertiesObj.loadProperties();
	}

	
	@Given("I entered on {string} page")
	public void iEnteredOnPage(String string) throws InterruptedException {

		this.searchPageObjects.getUrlDriver((searchProperties.getProperty("search.url.springPage")));
		this.searchPageObjects.acceptCookie();
	}

	@Given("I type a (.*)$")
	public void iTypeAWord(String string) throws InterruptedException {
		searchPageObjects.typeAWord(string);
		testStatusContainer.addString("PARAM$searchedWord", string);
	}
	
	@Then("the word typed is displayed on the top of page")
	public void theWordTypedIsDisplayedOnTheTopOfPage() {
		searchPageObjects.validateTypedWord();
	}
	
	@Then("The amount of result to be displayed")
	public void theAmountOfResultToBeDisplayed() {
		searchPageObjects.pagination();
	}

	
	@Then("the refined search must {string} details about the searched word.")
	public void theRefinedSearchMustDetailsAboutTheSearchedWord(String bool) {
		boolean b1=Boolean.parseBoolean(bool);  
		searchPageObjects.verifySearchDetails(b1);
	}
		
	@After
	public void closeDriver() {
		this.searchPageObjects.quitDriver();
	}
}
