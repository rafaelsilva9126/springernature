Feature: As a QA Engineer, I want to add end-to-end automated tests to the Coverage page, So that we
can confirm there are no problems with this page.

	
	Background:
	Given I entered on "main" page
    
  @test1 
  Scenario Outline: Search 
    Given I type a <name>
    Then the word typed is displayed on the top of page
    Then the refined search must "<display>" details about the searched word.
    And The amount of result to be displayed
    
    
    Examples:
    |name                                              |display|
    |"The Palgrave Handbook of African Women's Studies"|true   |
    
