Feature: Contact Us Form Submission

  Scenario: 01 - Submit a message via the Contact Us form
    Given I am on the Contact Us page
    When I fill in the form with valid details
    And I submit the form
    Then I should see a success message