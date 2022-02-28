package ch.fhnw.webec.coffeecatalogue.e2e.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class AddOrEditPage extends AbstractPage {
    @FindBy(css = "#bean [type=\"submit\"]")
    private WebElement submitButtonElement;

    @FindBy(css = "#bean-roastings")
    private WebElement roastingsInputElement;

    @FindBy(css = "#bean-roaster")
    private WebElement roasterInputElement;

    @FindBy(css = "#bean-description")
    private WebElement descriptionInputElement;

    @FindBy(css = "#bean-imagePath")
    private WebElement imagePathInputElement;

    @FindBy(css = "#bean-name")
    private WebElement beanNameElement;

    public AddOrEditPage(WebDriver webDriver, int port) {
        super(webDriver, port);
    }

    public AbstractPage addBean(String new_bean, String imagePath, String new_description, String roaster, List<String> roastings) {
       this.setBeanName(new_bean);
       this.setImagePath(imagePath);
       this.setDescription(new_description);
       this.setRoaster(roaster);
       this.setRoastings(roastings);

       return this.submitForm();
    }

    private void setRoastings(List<String> roastings) {
        var roastingSelect = new Select(this.roastingsInputElement);

        roastingSelect.deselectAll();

        for (var roasting : roastings ){
            roastingSelect.selectByValue(roasting);
        }
    }

    private void setRoaster(String roaster) {
        var roasterSelect = new Select(this.roasterInputElement);

        roasterSelect.selectByValue(roaster);
    }

    private void setDescription(String new_description) {
        this.descriptionInputElement.clear();
        this.descriptionInputElement.sendKeys(new_description);
    }

    private void setImagePath(String imagePath) {
        this.imagePathInputElement.clear();
        this.imagePathInputElement.sendKeys(imagePath);
    }

    public void setBeanName(String new_bean) {
        this.beanNameElement.clear();
        this.beanNameElement.sendKeys(new_bean);
    }

    public String getRoaster() {
        return new Select(this.roasterInputElement).getFirstSelectedOption().getText();
    }

    public List<String> getRoastings(){
        return new Select(this.roastingsInputElement)
            .getAllSelectedOptions()
            .stream()
            .map(WebElement::getText)
            .toList();
    }

    public String getDescriptionInputElement() {
        return this.descriptionInputElement.getText();
    }

    public String getImagePathInputElement() {
        return this.imagePathInputElement.getText();
    }

    public String getBeanNameElement() {
        return this.beanNameElement.getText();
    }

    public AbstractPage submitForm() {
        this.submitButtonElement.click();

        if (this.webDriver.getCurrentUrl().contains("/bean/add")) {
            return this;
        } else {
            return new ShowBeanPage(this.webDriver, this.port);
        }
    }

    public List<String> getFieldErrors(String fieldName) {
        return this.webDriver.findElements(
            By.cssSelector(".form__field-" + fieldName + " .form__error")
        ).stream().map(WebElement::getText).toList();
    }
}



