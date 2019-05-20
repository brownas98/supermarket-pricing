# supermarket-pricing
This is an application to implement a Supermarket Pricing Model that includes price per item, price per weight and multiple item discount.

This app requires Java 8 and Maven 3.

1. To build the application navigate to the root project directory where you should find a pom.xml file.

2. Then run the following command 
```mvn clean install```
This will build the application and run the tests.

3. Once this has finished you should see output ending like
```
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  3.242 s
[INFO] Finished at: 2019-05-20T09:03:27+01:00
[INFO] ------------------------------------------------------------------------
```

4. Move one directory down into the target directory where there should now be a file like supermarket-pricing-0.0.1-SNAPSHOT.jar .

5. Run the application usinf the following command

```java -jar supermarket-pricing-0.0.1-SNAPSHOT.jar```

And you should get this output which describes the example basket contents, sub total, discount total and grand total.
Supermarket Pricing Example

Price List
Beans 0.50
Coke 0.70
Oranges @ 1.99 per kg

Discount List
Beans 3 for 2
Coke 2 for £1

Quantity List
Beans X 3
Coke X 2
Oranges X 200 grams

Sub Total 3.30
Discount Total -0.90
Grand Total 2.40


