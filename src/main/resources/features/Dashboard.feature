@dashboard
Feature: Dashboard
  As a logged in user on demo app
  I want to be able to see a table on user's dashboard
  So that I can see all currencies and their rates.

  Background: Pre-requisite
    Given User navigates to App
    When User Login using credentials "admin" and "admin"
    Then User should be logged in successful

  Scenario: Checking all currencies are displayed
    Given User is on dashboard
    When Table is displayed
    Then Table should display all currencies

  Scenario Outline: Checking currency and rate
    Given User is on dashboard
    When Currency "<Currency>" is displayed
    Then  The Currency "<Currency>" and rate should be correct
    Examples: Currencies
      | Currency |
      | AUD      |
      | EUR      |

