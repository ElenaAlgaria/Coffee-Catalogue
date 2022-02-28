package ch.fhnw.webec.coffeecatalogue.e2e.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

public class AbstractPage {
        protected final WebDriver webDriver;
        protected final int port;

        public AbstractPage(WebDriver webDriver, int port) {
            this.webDriver = webDriver;
            this.port = port;

            PageFactory.initElements(webDriver, this);
        }

        public void doSearch(String search) {
            this.webDriver.navigate().to(this.getUriBuilder().queryParam("search", search).build().toString());
        }

        public AddOrEditPage goToAddBeanPage() {
            this.webDriver.navigate().to(this.getUriBuilder().path("/bean/add").build().toString());

            return new AddOrEditPage(this.webDriver, this.port);
        }

        public AddOrEditPage goToEditBeanPage(long beanId) {
            this.webDriver.navigate().to(this.getUriBuilder().path("/bean/" + beanId + "/edit").build().toString());

            return new AddOrEditPage(this.webDriver, this.port);
        }

        public IndexPage goToIndexPage() {
            this.webDriver.navigate().to(this.getUriBuilder().path("/").build().toString());

            return new IndexPage(this.webDriver, this.port);
        }

        public ShowBeanPage goToShowBeanPage(long beanId) {
            this.webDriver.navigate().to(this.getUriBuilder().path("/bean/" + beanId).build().toString());

            return new ShowBeanPage(this.webDriver, this.port);
        }

        public RosterPage goRoasterPage() {
            this.webDriver.navigate().to(this.getUriBuilder().path("/bean/roaster").build().toString());

            return new RosterPage(this.webDriver, this.port);
        }

        public UriBuilder getUriBuilder() {
            return UriComponentsBuilder.fromUriString("http://localhost:%d/".formatted(this.port));
        }
}
