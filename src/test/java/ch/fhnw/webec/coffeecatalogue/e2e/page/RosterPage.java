package ch.fhnw.webec.coffeecatalogue.e2e.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class RosterPage extends AbstractPage{
    @FindBy(css = "h2")
    private List<WebElement> roasterNames;

    @FindBy(css = ".roaster__image")
    private List<WebElement> roasterImages;

    @FindBy(css = ".description__txt")
    private List<WebElement> roasterDescription;

    public RosterPage(WebDriver webDriver, int port) {
        super(webDriver, port);
    }

    public List<String> getRoasterName() {
        return this.roasterNames.stream().map(WebElement::getText).toList();
    }

    public List<String> getRoasterImage() {
        return this.roasterImages.stream().map(
            roasterImage -> roasterImage.findElement(By.cssSelector("#roaster-image")).getAttribute("src")).toList();
    }

    public List<String>  getRoasterDescription() {
        return this.roasterDescription.stream().map(WebElement::getText).toList();
    }
}
