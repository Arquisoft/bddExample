# language: en
Feature: User management

Scenario: Create first user
Given there are no users
When I create a user "Pepe" with password "Pepe12"
Then The number of users is 1
