Feature: Being able to login
Scenario: Login
  Login with some username

    Given a list of users:
      | name    | password |
      | pepe    | pepe12   |
      | luis    | siul     |
      | mari    | mmm      |
    When I login with name "luis" and password "siul"
    Then I receive a welcome message
