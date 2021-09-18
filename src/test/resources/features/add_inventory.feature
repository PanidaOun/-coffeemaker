Feature: Test about add inventory in coffee maker
  Scenario: Add a coffee in coffee maker
    Given Initial number of ingredients
    When Add 2 coffee to amount of ingredients
    Then The total of coffee will have 17

  Scenario: Add a milk in coffee maker
    Given Initial number of ingredients
    When Add 5 milk to amount of ingredients
    Then The total of milk will have 20

  Scenario: Add a chocolate in coffee maker
    Given Initial number of ingredients
    When Add 7 chocolate to amount of ingredients
    Then The total of chocolate will have 22

  Scenario: Add a sugar in coffee maker
    Given Initial number of ingredients
    When Add 6 sugar to amount of ingredients
    Then The total of sugar will have 21