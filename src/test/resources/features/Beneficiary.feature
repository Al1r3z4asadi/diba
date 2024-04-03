Feature: Test Beneficiary

  Scenario: Create a new beneficiary
    Given a request to create a new beneficiary
    When the createOne command is dispatched and if it is unique in the system
    Then the beneficiary should be created successfully and get store in both eventstoreDB and mongoDB