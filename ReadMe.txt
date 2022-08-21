
please check out and  run 'mvn clean install' to build and generate the unit test report
This will generate the test report in the 'target/site/jcoco' folder

Adding new discount.
 - Implement IDiscount interface and add it to the IDiscountHandler which can be obtained from the CheckoutHandler#getDiscountHandler()