@userLogin
Feature: User Login
  As a user with valid credentials
  I want to be able to login to demo app
  So that I can see dashboard

  Background: Pre-requisite
    Given User navigates to App
    Then User should be on Login Page

  @validUserLogin
  Scenario: Valid User Login
    Given User Login using credentials "admin" and "admin"
    Then User should be logged in successful

  @invalidUserLogin
  Scenario: Invalid User Login
    Given User Login using credentials "admin" and "admin"
    Then User should be be logged in successfully

