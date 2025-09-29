Feature: Get Held Items

  Scenario: Get Held Items
    Given Get name pokemon is "ditto"
    When The controller find pokemon
    Then Must return the help items