package ch.fhnw.webec.coffeecatalogue.e2e.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class IndexPage extends AbstractPage {
    @FindBy(css = ".index__heading")
    private List<WebElement> coffeeItemHeadingElements;

    @FindBy(css = ".header__title")
    private List<WebElement> indexHeader;

    @FindBy(css = ".description__txt")
    private List<WebElement> indexDescription;

    @FindBy(css = ".coffee__imageContainer")
    private List<WebElement> coffeeItemImg;

    public IndexPage(WebDriver webDriver, int port) {
        super(webDriver, port);
    }

    public List<String> getBeansNames() {
        return this.coffeeItemHeadingElements.stream().map(WebElement::getText).toList();
    }
}
