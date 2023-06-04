Feature: Validate the Amazon Search Functionality

  @Sanity
  Scenario: Search product using hard coded data
    Given User should navigate to Ebay page
    When User should enter the product and select the category
    And User click on search buttom
    Then Validate the search result

  Scenario: Search product using data table values by map
    Given User should navigate to Ebay page
    When User should enter the product and select the category from below list
      | productName | productCategory |
      | Iphone      | Electronics     |
      | Love        | Gift Cards      |
    And User click on search buttom

  @Smoke
  Scenario Outline: Search product using produt name and category given from feature
    Given User should navigate to Ebay page
    When User should enter the product <productName> and select the category <productCategory> from feature
    And User click on search buttom
    Then Validate the search result

    Examples: 
      | productName | productCategory |
      | Iphone      | Electronics     |
      | Love        | Gift Cards      |
