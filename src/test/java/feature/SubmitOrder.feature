Feature: Purchase the order from E commerce site

  Background:
    Given I am landed to the site

  Scenario Outline: User submits a new order successfully
    Given The user is on the login page with username <username> and password <password>
    When The user logs in with valid credentials
    And The user adds a product <productName> to the cart
    And The user proceeds to the checkout page
    And The user enters shipping and payment details
    And The user submits the order
    Then The order confirmation should be displayed as <Message>

    Examples:
      | username        | password  | productName            | Message
      | nima1@gmail.com | Nima@5056 | ADIDAS ORIGINAL        | THANKYOU FOR THE ORDER.
      | nima2@gmail.com | Nima@5057 | ZARA COAT 3ZARA COAT 3 | THANKYOU FOR THE ORDER.
