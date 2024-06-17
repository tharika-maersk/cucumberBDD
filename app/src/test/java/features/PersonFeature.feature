# language: en
Feature: Person testing Feature
# This file contains the different scenarios to test out person service
# for this service we don't need a db connection to store and find persons
# The managed persons need to be stored in a list in the memory

# First scenario to initialize the list of persons
Scenario: Initializing a given list of persons
    Given I have a static method which initialize a list of persons
    When I connect I can see the size of the initialized list
    Then I should see the size of the list is 3


# Create a new person with random entries
Scenario: Creating a new person
    Given The List of persons contains 3 persons already stored
    When I create a new person with random entries
    Then I get the ID of the new person and the list contains more than three persons

# Person Bulk creation
Scenario Outline: Bulk creation
    Given The List of persons contains 3 persons already stored
    When I create a new Person with "<FirstName>" and "<LastName>" and <Age>
    Then I get the ID of the new Person and list contains more than 3 persons

    Examples:
        | FirstName | LastName | Age |
        | John      | Doe      | 30  |
        | Jane      | Doe      | 25  |
        | Jack      | Doe      | 35  |


Scenario Outline: Update a person by ID
    Given The List of persons contains 3 persons already stored
    When I update a person data with <ID> and "<FirstName>" and "<LastName>" and <Age>
    Then I get the person updated

    Examples:
        | ID | FirstName | LastName | Age |
        | 1  | John      | Ding     | 31  |
        | 2  | Jane      | Doe      | 15  |
        | 3  | Jack      | Doe      | 39  |

Scenario: Delete a person by ID
    Given The List of persons contains 3 persons already stored
    When I delete a person with ID 1
    Then The given person is deleted and the list size is 2