package pageobjects;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.core.env.ConfigurableEnvironment;

import constants.SearchProperties;
import framework.BasePage;
import framework.TestStatusContainer;

public class SearchPageObjects extends BasePage {

	private SearchProperties searchPropertiesObj;
	private ConfigurableEnvironment searchProperties;
	private TestStatusContainer testStatusContainer;

	public SearchPageObjects() {
		this.searchPropertiesObj = new SearchProperties();
		this.searchProperties = searchPropertiesObj.loadProperties();
		this.testStatusContainer = new TestStatusContainer();
	}

	public void getStatusContainer(String name) {
		testStatusContainer.addString("PARAM$string", name);
	}

	public void acceptCookie() throws InterruptedException {

		getElementById(searchProperties.getProperty("search.id.acceptCookie")).click();
	}

	public ConfigurableEnvironment getsearchProperties() {
		return searchProperties;
	}

	public void typeAWord(String string) throws InterruptedException {
		getElementById((searchProperties.getProperty("search.id.searchTextBox"))).sendKeys(string);
		getElementById((searchProperties.getProperty("search.id.searchBtn"))).click();

	}

	public void verifySearchDetails(boolean b1) {
		String quantityOfResult = getElementByXpath((searchProperties.getProperty("search.xpath.quantityOfResults")))
				.getText();
		int i = Integer.parseInt(quantityOfResult);
		if (i > 0) {
			boolean contentTypes = getElementByXpath((searchProperties.getProperty("search.xpath.contentTypeFacet")))
					.isDisplayed();
			boolean discipline = getElementByXpath((searchProperties.getProperty("search.xpath.disciplineFacet")))
					.isDisplayed();
			boolean subdiscipline = getElementByXpath((searchProperties.getProperty("search.xpath.subdisciplineFacet")))
					.isDisplayed();
			boolean language = getElementByXpath((searchProperties.getProperty("search.xpath.languageFacet")))
					.isDisplayed();
			Assert.assertEquals(contentTypes, b1);
			Assert.assertEquals(discipline, b1);
			Assert.assertEquals(subdiscipline, b1);
			Assert.assertEquals(language, b1);
		} else {
			noResult();
			Assert.assertFalse(b1);
		}

	}

	public static String removeFirstandLast(String str) {

		str = str.substring(1, str.length() - 1);

		return str;
	}

	public void validateTypedWord() {
		String wordTyped = getElementByXpath((searchProperties.getProperty("search.xpath.wordTyped"))).getText();
		String finalwordTyped = removeFirstandLast(wordTyped);

		Assert.assertEquals(finalwordTyped, (testStatusContainer.getString("PARAM$searchedWord")));
	}

	public void pagination() {

		String quantityOfResult = getElementByXpath((searchProperties.getProperty("search.xpath.quantityOfResults")))
				.getText();
		int finalQuantityOfResult = Integer.parseInt(quantityOfResult);
		List<WebElement> results = new ArrayList<WebElement>();
		if (finalQuantityOfResult > 0 && finalQuantityOfResult > 20) {
			String lastPage = getElementByXpath((searchProperties.getProperty("search.xpath.lastPage"))).getText();
			int finaLastPage = Integer.parseInt(lastPage);

			for (int i = 1; i <= finaLastPage; i++) {
				results.addAll(getElementsByXpath("//*[@id='results-list']/li"));
				if (i != finaLastPage) {
					getElementByXpath((searchProperties.getProperty("search.xpath.nextPage"))).click();
				}

			}

		} else {
			if (finalQuantityOfResult > 0) {
				results.addAll(getElementsByXpath("//*[@id='results-list']/li"));
			}

		}
		if (finalQuantityOfResult != 0) {
			int numberOfRecords = results.size();
			Assert.assertEquals(finalQuantityOfResult, numberOfRecords);
		}
		else {
			noResult();
		}
	}
	
	public void noResult() {
		String noResult = getElementByXpath((searchProperties.getProperty("search.xpath.noResult"))).getText();
		Assert.assertEquals(noResult, "Sorry");
	}

}
