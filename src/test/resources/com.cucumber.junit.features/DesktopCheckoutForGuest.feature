@Regression
Feature: Desktop Checkout for Guest User
  As a customer
  I want to be able proceed to checkout
  So that I can specify my delivery and payment details and place the order

  Scenario: Proceed to checkout, final review and place order as guest user
    Given I am an anonymous customer with clear cookies
    And I open the "https://www.bookdepository.com/"
    And I search for "Thinking in java"
    And I am redirected to a "Search results"
    And Search results contain the following products
      | Thinking in Java     |
      | Think Java           |
      | Thinking Java Part I |
    And I apply the following search filters
      | Price range  | 30 € +         |
      | Availability | In Stock (5)   |
      | Language     | English (17)   |
      | Format       | Paperback (22) |
    And Search results contain only the following products
      | Thinking in Java                                                     |
      | Think Java                                                           |
      | Thinking Recursively - A 20th Anniversary Edition with Java (WSE)    |
      | Think Data Structures : Algorithms and Information Retrieval in Java |
    When I click 'Pre-order' button for product with name "Thinking in Java"
    And I select Basket Checkout in basket pop-up
    Then I am redirected to the "Basket page"
    And Basket order summary is as following:
      | Delivery cost | Total   |
      | FREE          | 83,18 €|
    When I click 'Checkout' button on 'Basket' page
    Then I am redirected to the "Checkout" page
    When I click 'Buy now' button
    Then the following validation error messages are displayed on 'Delivery Address' form:
      | Form field name | validation error message                                  |
      | Email address   | Please enter your Email address (for order confirmation)  |
      | Full name       | Please enter your Full name                               |
      | Address line 1  | Please enter your Address line 1                          |
      | Town/City       | Please enter your Town/City                               |
      | Postcode/ZIP    | Please enter your postcode/ZIP or write 'No Postcode'     |
    And the following validation error messages are displayed on 'Payment' form:
      | Please enter your card number, Please enter your card's expiration date, Please enter your CVV |
    And Checkout order summary is as following:
      | Sub-total | Delivery  | VAT    | Total   |
      | 83,18 €   | FREE      | 0,00 € | 83,18 € |
    And I checkout as a new customer with email "test@user.com"
    When I fill delivery address information manually:
      | Full name  | Delivery country | Address line 1   | Address line 2   | Town/City | County/State | Postcode |
      | Roman      | Andorra          | Moja adressa 1   | Moja adressa 2   | Stryi     | mij state    | 00000    |
    Then there is no validation error messages displayed on 'Delivery Address' form
    When I enter my card details
      | Card Type    | Visa             |
      | Name On Card | KartkaRomana     |
      | cardNumber   | 1111111111111111 |
      | Expiry Year  | 2024             |
      | Expiry Month | 05               |
      | Cvv          | 000              |