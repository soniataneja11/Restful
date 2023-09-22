Feature:Automation Rest Api on restful-api-dev

  Scenario: Create Return and Delete the new product
    Given I create a new product from CreateProduct.json
    When I perform POST on objects resource
    Then response status code is 200
    And Id field should not be null
    And response body should contain value of Apple MacBook Pro 16 for key name
    And response body should contain value of 2019 for key data.year
    # Delete
    And I perform DELETE on objects resource
    Then response status code is 200

  Scenario Outline: verify status code and response time and validate Schema for Get all
    When I perform GET on objects resource
    Then response status code is <statusCode>
    And  Verify response time <ResponseTime>
    And response matches the schema from GetAllSchema.json
     Examples:
      | statusCode | ResponseTime |
      | 200        | 1500         |

  Scenario:  verify status code for invalid Resource
    When I perform GET on object resource
    Then response status code is 404











