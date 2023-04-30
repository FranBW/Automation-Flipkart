package Test;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

public class Cart_Functionality {


    public static void main(String[] args) {
        //Here goes the path where the ChromeDriver is, in this case it is inside AUTOMATION ASSIGNMENT.
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\miler\\Downloads\\AUTOMATION ASSIGNMENT\\AUTOMATION\\src\\main\\resources\\drivers\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--Incognito");

        WebDriver driver = new ChromeDriver(options);
        driver.get("https://www.flipkart.com/");

        WebElement CloseLoginButton = driver.findElement(By.xpath("/html/body/div[2]/div/div/button"));
        CloseLoginButton.click();

        WebElement searchBox = driver.findElement(By.xpath("//input[@title='Search for products, brands and more']"));
        searchBox.sendKeys("shoes");


        WebElement searchButton = driver.findElement(By.xpath("//button[@type='submit']"));
        try {
            Thread.sleep(2000); // wait 2 seconds
        } catch (InterruptedException e) {
            // handle exception here
        }
        searchButton.click();


        WebElement elemento = null;
        Duration espera = Duration.ofSeconds(5);

        try {
            elemento = new FluentWait<WebDriver>(driver)
                    .withTimeout(espera)
                    .pollingEvery(Duration.ofMillis(500))
                    .ignoring(NoSuchElementException.class)
                    .until(d -> d.findElement(By.xpath("//*[@id='container']/div/div[3]/div[1]/div[2]/div[1]/div/div/div[2]/div[3]")));
        } finally {

        }

        if (elemento != null) {
            WebElement sortButton = driver.findElement(By.xpath("//*[@id='container']/div/div[3]/div[1]/div[2]/div[1]/div/div/div[2]/div[3]"));

            try {
                Thread.sleep(2000); // wait 2 seconds
            } catch (InterruptedException e) {
                // handle exception here
            }
            sortButton.click();
        }

        WebElement sortOptionDiv = driver.findElement(By.xpath("//*[@id='container']/div/div[3]/div[1]/div[2]/div[1]/div/div/div[2]/div[3]"));
        sortOptionDiv.click();

        WebElement sortOption = driver.findElement(By.xpath("//*[@id='container']/div/div[3]/div[1]/div[2]/div[1]/div/div/div[2]/div[3]"));

        try {
            Thread.sleep(2000); // wait 2 seconds
        } catch (InterruptedException e) {
            // handle exception here
        }
        sortOption.click();


//Click on the second and third available products and add them to the cart
        WebElement secondProduct = driver.findElement(By.xpath("(//a[contains(@title, 'Shoe')])[2]"));
        secondProduct.click();
// Change the window control to the new window
        String parentWindow = driver.getWindowHandle();
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
// Perform actions in the new window
        WebElement addToCartButton1 = driver.findElement(By.xpath("//*[@id='container']/div/div[3]/div[1]/div[1]/div[2]/div/ul/li[1]/button"));
        try {
            Thread.sleep(5000); // wait 5 seconds
        } catch (InterruptedException e) {
            // handle exception here
        }
        addToCartButton1.click();
        try {
            Thread.sleep(2000); // wait 2 seconds
        } catch (InterruptedException e) {
            // handle exception here
        }
// Close the new window and return to main window control
        driver.close();
        driver.switchTo().window(parentWindow);

        WebElement thirdProduct = driver.findElement(By.xpath("(//a[contains(@title, 'Shoe')])[3]"));
        thirdProduct.click();
// Change window control to new window
        String parentWindow1 = driver.getWindowHandle();
        for (String winHandle1 : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle1);
        }
// Perform actions in the new window
        WebElement addToCartButton2 = driver.findElement(By.xpath("//*[@id='container']/div/div[3]/div[1]/div[1]/div[2]/div/ul/li[1]/button"));
        try {
            Thread.sleep(5000); // wait 5 seconds
        } catch (InterruptedException e) {
            // handle exception here
        }
        addToCartButton2.click();
        try {
            Thread.sleep(2000); // wait 2 seconds
        } catch (InterruptedException e) {
            // handle exception here
        }
// Close the new window and return to main window control
        driver.close();
        driver.switchTo().window(parentWindow1);


        // Go to the cart and validate the correct products and the total sum
        WebElement cartLink = driver.findElement(By.xpath("//*[@id='container']/div/div[1]/div[1]/div[2]/div[6]/div/div/a"));
        try {
            Thread.sleep(2000); // wait 2 seconds
        } catch (InterruptedException e) {
            // handle exception here
        }
        cartLink.click();
        try {
            Thread.sleep(2000); // wait 2 seconds
        } catch (InterruptedException e) {
            // handle exception here
        }
        WebElement firstProductTitle = driver.findElement(By.xpath("//*[@id='container']/div/div[2]/div/div/div[1]/div/div[3]/div/div[1]/div[1]/div[1]/a"));
        WebElement secondProductTitle = driver.findElement(By.xpath("//*[@id='container']/div/div[2]/div/div/div[1]/div/div[4]/div/div[1]/div[1]/div[1]/a"));
        WebElement firstProductPrice = driver.findElement(By.xpath("//*[@id='container']/div/div[2]/div/div/div[1]/div/div[3]/div/div[1]/div[1]//*[@class='_2-ut7f _1WpvJ7']"));
        WebElement secondProductPrice = driver.findElement(By.xpath("//*[@id='container']/div/div[2]/div/div/div[1]/div/div[4]/div/div[1]/div[1]//*[@class='_2-ut7f _1WpvJ7']"));
        WebElement totalSum = driver.findElement(By.xpath("//*[@id='container']/div/div[2]/div/div/div[2]/div[1]/div/div/div/div[4]/div/div[2]/span/div/div/div[2]/span"));

        if (firstProductTitle.getText().contains("Shoe") && secondProductTitle.getText().contains("Shoe")) {
            System.out.println("Both products added to cart successfully");
        } else {
            System.out.println("Error: Products not added to cart");
        }

        double expectedTotal = Double.parseDouble(firstProductPrice.getText().replaceAll("[^0-9.]+", ""))
                + Double.parseDouble(secondProductPrice.getText().replaceAll("[^0-9.]+", ""));
        double actualTotal = Double.parseDouble(totalSum.getText().replaceAll("[^0-9.]+", ""));

        if (expectedTotal == actualTotal) {
            System.out.println("Total Sum is correct");
        } else {
            System.out.println("Error: Total sum is incorrect");
        }

    }
}
