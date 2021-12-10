Feature: As a QA Engineer, I want to check if the search functionality is working as expected.

	
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
    |"kwdwwdwpdpwdkdkopw"                              |false   |
    
