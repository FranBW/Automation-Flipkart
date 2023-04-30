package Test;


import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.chrome.ChromeOptions;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



public class Sort_Functionality {



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

        // Get page limit from data source

        // find the element that contains the maximum number of pages and get its text
        WebElement pageNumElement = driver.findElement(By.xpath("//*[@id='container']/div/div[3]/div[1]/div[2]/div[12]/div/div/span[1]"));
        String pageNumText = pageNumElement.getText();

        // extract the maximum number of pages from the obtained text string
        int maxPageNum = Integer.parseInt(pageNumText.replaceAll("^Page\\s+(\\d+)\\s+of\\s+(\\d+)$", "$2"));
        System.out.println("Number of pages " + maxPageNum);

        int Valid = 3;


        List<Float> productsPrices = new ArrayList<Float>();

// Read prices on the first page
        List<WebElement> products = driver.findElements(By.xpath("//*[@class='_1AtVbE col-12-12']//*[contains(@class, '_1fQZEK')]//*[@class='_30jeq3']"));
        for (WebElement product : products) {
            String texto = product.getText().replaceAll("[^0-9.]", ""); // Remove all characters other than numbers and periods
            float price = Float.parseFloat(texto);
            productsPrices.add(price);
        }

        for (int i = 2; i <= Valid; i++) {
            WebElement nextButton = driver.findElement(By.xpath("//*[@id='container']/div/div[3]/div[1]/div[2]/div[12]/div/div/nav//*[@class='_1LKTO3']//span"));
            Duration espera0 = Duration.ofSeconds(100);
            WebDriverWait wait = new WebDriverWait(driver, espera0);
            wait.until(ExpectedConditions.visibilityOf(nextButton));
            wait.until(ExpectedConditions.elementToBeClickable(nextButton));
            try {
                Thread.sleep(5000); // Additional wait of 500ms
            } catch (InterruptedException e) {
                System.out.println("'Next' button did not become clickable in time");
            }
            nextButton.click();

            // Wait for the page to fully load
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='_2kHMtA']")));

            // Read current page prices
            products = driver.findElements(By.xpath("//*[@id='container']/div/div[3]//div[1]/div[2]//*[@class='_1AtVbE col-12-12']//*[@class='_13oc-S']//*[@class='_2B099V']//*[@class='_25b18c']//*[@class='_30jeq3']"));
            for (WebElement product : products) {
                String texto = product.getText().replaceAll("[^0-9.]", ""); // Remove all characters other than numbers and periods
                float price = Float.parseFloat(texto);
                productsPrices.add(price);
            }
        }
        driver.navigate().back();


// Sort the price list in ascending order
        List<Float> sortedPrices = new ArrayList<Float>(productsPrices);
        Collections.sort(sortedPrices);
        for (Float price : sortedPrices) {
            System.out.println(price);
        }

        // Validate that the price list ordered
        if(productsPrices.equals(sortedPrices)) {
            System.out.println("The prices are in ascending order.");
        } else {
            System.out.println("Prices are NOT in ascending order.");
        }
        Assert.assertTrue(productsPrices.equals(sortedPrices));

    }
}
