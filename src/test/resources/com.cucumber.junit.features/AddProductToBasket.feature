Feature: The user is able to add product to the basket and proceeds to the checkout page

  @full_test
  Scenario: Proceed to checkout as guest user
    Given the user opens Book Depository website
    When the user enters "Camilla, Duchess of Cornwall" into the search input
    And the user clicks on Search button
    Then Search results page with URL "https://www.bookdepository.com/search?searchTerm=Camilla%2C+Duchess+of+Cornwall&search=Find+book" is displayed
    When the user clicks Add to basket button for product with name Camilla, Duchess of Cornwall
    And the user clicks on Basket-Checkout button in basket modal
    Then Basket page opens with correct values
      |field   |value   |
      |Total   |21,09 € |
      |Subtotal|21,09 € |
    When the user clicks Checkout button on Basket page
    And Checkout page opens with correct values
      |field   |value |
      |Total   |21,09 €|
      |Subtotal|21,09 €|
    And the user set "test@user.com" as e-mail address