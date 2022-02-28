package ch.fhnw.webec.coffeecatalogue.e2e.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class ShowBeanPage extends AbstractPage {

    @FindBy(css = ".bean__image")
    private WebElement showBeanImage;

    @FindBy(css = ".beans-detail__title")
    private WebElement showBeanName;

    @FindBy(css = ".description__txt")
    private WebElement showBeanDescription;

    @FindBy(css = ".roasting-list__item")
    private List<WebElement> roastingsE;

    @FindBy(css = ".roaster__name")
    private WebElement roaster;

    @FindBy(css = "[value=\"Delete\"]")
    private WebElement deleteBtn;

    @FindBy(css = ".show__favorite button")
    private WebElement favButtonElement;

    @FindBy(css = ".show__favorite .card__icon")
    private WebElement showBeanFavorite;

    public ShowBeanPage(WebDriver webDriver, int port) {
        super(webDriver, port);
    }

    public String getHeading() {
        return this.showBeanName.getText();
    }

    public String getDescription() {
        return this.showBeanDescription.getText();
    }

    public List<String> getRoastingNames() {
        return this.roastingsE.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public String getImagePath() {
        return this.showBeanImage.getAttribute("src");
    }

    public String getRoaster() {
        return this.roaster.getText();
    }

    public boolean getIsFavorite() {
        return this.showBeanFavorite.isDisplayed();
    }

    public boolean addAsFavorite() {
        this.favButtonElement.click();

        return this.getIsFavorite();
    }

    public AbstractPage deleteBean() {
        this.deleteBtn.click();
        this.webDriver.switchTo().alert().accept();
        if (this.webDriver.getCurrentUrl().contains("/")) {
            return this;
        } else {
            return new IndexPage(this.webDriver, this.port);
        }
    }
}
