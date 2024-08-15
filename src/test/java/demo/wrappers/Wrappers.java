package demo.wrappers;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class Wrappers {

    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor jse;


    public Wrappers(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        //.driver = driver;
        jse = (JavascriptExecutor) driver;
    }

    public void openYoutube() {
        driver.get("https://www.youtube.com/");
    }

    public void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (Exception e) {

        }

    }

    public void clickOnSideBar(String title) {

        WebElement selectingMovies = wait.until(
                ExpectedConditions.
                        presenceOfElementLocated(
                                By.xpath("//yt-formatted-string[text()='"+title+"']")));
        selectingMovies.click();



    }

    public int getNumberFromText(String imp){
        StringBuilder rev = new StringBuilder();
        for (int i = 0; i < imp.length(); i++){

            if (Character.isDigit(imp.charAt(i)))
                rev.append(imp.charAt(i));
        }

        if (rev.length() > 0){
            return Integer.parseInt(rev.toString());
        }else
            return 0    ;
    }

    public void scrollToItem(String title){


        WebElement e = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='"+title+"']")));


        jse.executeScript("arguments[0].scrollIntoView();", e);

//        jse.executeScript("window.scrollBy(0,250)", "");
    }

    public void scrollToItem(WebElement element){
        jse.executeScript("arguments[0].scrollIntoView();", element);
    }

    public void searchYoutube(String searchKey){
        WebElement searchBox = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='search']")));
        searchBox.sendKeys(searchKey);
        searchBox.sendKeys(Keys.ENTER);
    }


    public List<WebElement> getVideoElements(){
        WebElement rootDiv = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//ytd-item-section-renderer[@class='style-scope ytd-section-list-renderer']")));

//       return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//ytd-video-renderer[@class='style-scope ytd-item-section-renderer']")));
        return driver.findElements(By.xpath("//ytd-video-renderer[@class='style-scope ytd-item-section-renderer']"));
    }

    public double getViewCount(WebElement view){

        String viewCount = view.getText().split(" ")[0];
        double sum = 0;
        if (viewCount.matches(".*[a-zA-Z]+.*")){
            double num = Double.parseDouble(viewCount.substring(0, viewCount.length()-1));
            char mul = viewCount.charAt(viewCount.length()-1);

            switch (mul){
                case 'B' :
                    sum = num * 100_000_000_0;
                    break;
                case 'M' :
                    sum = num * 100_000_0;
                    break;

                case 'K' :
                    sum = num * 100_0;
                    break;

            }
        }else{
            sum = Double.parseDouble(viewCount);
        }


        return sum;
    }

}
