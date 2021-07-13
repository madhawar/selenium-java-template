package Pages;

import Utils.Log;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class MyAccountLogin {
    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;

    @FindBy(xpath = "//*[@id='password']")
    WebElement Password;

    @FindBy(xpath = "//*[@id='password1']")
    WebElement NewPassword;

    @FindBy(xpath = "//label[@for='readAgreement']")
    WebElement ReadAgreement;

    @FindBy(xpath = "//*[@id='registerBtn']")
    WebElement Register;

    @FindBy(xpath = "//*[@id='signInBtn']")
    WebElement SignIn;

    private final static String EXTRA_MISSING_PET = "Missing Pet Cover";

    private final static String ticksFeline = "//button[@title='Ticks (feline)']";

    public MyAccountLogin(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 30);
        js = ((JavascriptExecutor) driver);
    }

    public void example_page_factory(String password) {
        Log.info("It works!");
        NewPassword.sendKeys(password);
        ReadAgreement.click();
        Register.click();
    }

    public void example_js() {
        js.executeScript ("document.getElementsByClassName('btn btn-primary')[0].click();");
        js.executeScript ("document.getElementById('noVisited').click();");
        js.executeScript ("document.getElementById('submit').click();");
    }

    public void example_wait() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ticksFeline)));
        driver.findElement(By.xpath(ticksFeline)).click();
    }

    public void example_assert(String microchipped) {
        boolean missing_pet_cover;

        switch (microchipped) {
            case "yes" :
                try {
                    missing_pet_cover = driver.findElement(By.cssSelector("body")).getText().contains(EXTRA_MISSING_PET);
                } catch (NoSuchElementException e){
                    missing_pet_cover = false;
                }
                Assert.assertTrue(missing_pet_cover);
                break;
            case "no" :
                try {
                    missing_pet_cover = driver.findElement(By.cssSelector("body")).getText().contains(EXTRA_MISSING_PET);
                } catch (NoSuchElementException e){
                    missing_pet_cover = false;
                }
                Assert.assertFalse(missing_pet_cover);
                break;
        }
    }

}
