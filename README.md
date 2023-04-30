# Automation-Flipkart
A test class was created that validates the following test cases: 1) Validate the Sort functionality for the displayed products. 2) Validate the Add to Cart functionality.
To accomplish this task, it was written in a Java language with a JUnit framework using Selenium WebDriver and it takes about 18 hours to complete.
The script uses ChromeDriver to automate the Google Chrome browser. The WebDriver library is used to find web elements by their XPath and interact with them.

Scripts work independently and each one must be run individually. The validations are shown by console.

This code performs web scraping of the Flipkart website to get the prices of the shoes and sort them in descending order. These are the steps performed by the code:

The Chrome browser is launched and the website 'https://www.flipkart.com/' is opened.

An automation was implemented to close the login window and thus only have to start the Script, each step is commented within the code.

A FluentWait object is used among other methods to wait for the button to become visible on the page.

A loop is executed to iterate through all the pages and the necessary data of the products of each page is obtained.

The results are printed on the console to see that the validation is correct.

A Maven build tool was used

The dependencies found in the POM.XML were used:

Selenium WebDriver
chrome controller
Note: The code can be adapted to scrape prices for any other product by modifying the search query in step 3.
