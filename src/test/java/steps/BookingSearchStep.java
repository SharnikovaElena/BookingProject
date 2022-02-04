package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


import java.util.List;

import static org.testng.Assert.assertEquals;

public class BookingSearchStep {

    private static final String BOOKING_URL = "https://www.booking.com/searchresults.en-gb.html";
    private static final By SEARCH_FIELD = By.xpath("//input[@type='search']");
    private static final By SEARCH_BUTTON = By.xpath("//*[@class='sb-searchbox__button ']");
    private String nameHotel;
    WebDriver driver;


    @Before
    public void init() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Given("The name of the hotel {string}")
    public void searchKeywordIsString(String keyword) {
        nameHotel = keyword;
    }

    @When("User does search")
    public void search() {
        driver.get(BOOKING_URL);
        driver.findElement(SEARCH_FIELD).click();
        driver.findElement(SEARCH_FIELD).sendKeys(nameHotel);
        driver.findElement(SEARCH_BUTTON).click();
    }

    @Then("Hotel {string} on the first page")
    public void assertSearchResult(String nameHotel) {
        final List<WebElement> resultsLinks = driver.findElements(By.xpath("//div[@data-testid='title']"));
        assertEquals(resultsLinks.get(0).getText(), nameHotel, "Hotel name does not match");
    }

    @And("Hotel rating {string}")
    public void hotelRating(String ratingHotel) {
        final List<WebElement> resultsLinks = driver.findElements(By.xpath("//div[@data-testid='review-score']/div[contains(text(),*)]"));
        assertEquals(resultsLinks.get(0).getText(), ratingHotel, "Hotel rating does not match");
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}


