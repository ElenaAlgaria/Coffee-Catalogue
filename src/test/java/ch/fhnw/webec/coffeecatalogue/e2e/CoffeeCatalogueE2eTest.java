package ch.fhnw.webec.coffeecatalogue.e2e;

import ch.fhnw.webec.coffeecatalogue.e2e.page.AddOrEditPage;
import ch.fhnw.webec.coffeecatalogue.e2e.page.IndexPage;
import ch.fhnw.webec.coffeecatalogue.e2e.page.ShowBeanPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;


@Import(WebDriverConfiguration.class)
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CoffeeCatalogueE2eTest {

    @LocalServerPort
    private int port;
    @Autowired
    private WebDriver webDriver;
    private IndexPage indexPage;

    @BeforeEach
    public void setUp() {
        this.indexPage = new IndexPage(this.webDriver, this.port);
    }

    @Test
    public void testSearchBeanNames() {
        this.indexPage.goToIndexPage();

        assertEquals(3, this.indexPage.getBeansNames().size());

        this.indexPage.doSearch("Test bean 1");

        assertEquals(1, this.indexPage.getBeansNames().size());
        assertTrue(this.indexPage.getBeansNames().contains("Test bean 1"));
    }

    @Test
    public void testShowBean() {
        var showBeanPage = this.indexPage.goToShowBeanPage(1L);

        assertEquals("Test bean 1", showBeanPage.getHeading());
        assertEquals("Test description 1", showBeanPage.getDescription());
        assertTrue(showBeanPage.getRoastingNames().contains("test roasting 1"));
        assertTrue(showBeanPage.getRoastingNames().contains("test roasting 2"));
    }

    @Test
    @DirtiesContext
    public void testAddBean() {
        var addBeanPage = this.indexPage.goToAddBeanPage();
        var imagePath = "https://images.squarespace-cdn.com/incaelporvenirCATURRA.png?format=2500w";

        var abstractPage =
            addBeanPage.addBean("new Bean", imagePath, "new description", "1",
                List.of("1"));

        if (abstractPage instanceof ShowBeanPage showBeanPage) {
            assertEquals("new Bean", showBeanPage.getHeading());
            assertEquals("new description", showBeanPage.getDescription());
            assertEquals(imagePath, showBeanPage.getImagePath());
            assertEquals("Test roaster 1", showBeanPage.getRoaster());
            assertTrue(showBeanPage.getRoastingNames().contains("test roasting 1"));
        } else {
            fail();
        }
    }

    @Test
    public void testAddBeanInvalid() {
        var addBeanPage = this.indexPage.goToAddBeanPage();
        var abstractPage = addBeanPage.addBean("", "/hallo/bild","","1", List.of("1"));

        if (abstractPage instanceof AddOrEditPage) {
            assertEquals( 1, addBeanPage.getFieldErrors("bean-name").size());
            assertEquals( 1, addBeanPage.getFieldErrors("bean-imagePath").size());
            assertTrue(addBeanPage.getFieldErrors("bean-name").contains("not valid input - please enter the name"));
            assertTrue(addBeanPage.getFieldErrors("bean-imagePath").contains("has to be a full URL to an Image! Or use current default image."));
        } else {
            fail();
        }
    }

    @Test
    @DirtiesContext
    public void testEditBean() {
        var showBeanPage = this.indexPage.goToShowBeanPage(1L);

        assertEquals(showBeanPage.getHeading(), "Test bean 1");

        var editBeanPage = showBeanPage.goToEditBeanPage(1L);

        editBeanPage.setBeanName("Updated Test bean 1");
        var abstractPage = editBeanPage.submitForm();

        if (abstractPage instanceof ShowBeanPage showBeanPageEdited) {
            assertEquals("Updated Test bean 1", showBeanPageEdited.getHeading());
        } else {
            fail();
        }
    }

    @Test
    @DirtiesContext
    public void testDeleteBean() {
        if (this.webDriver instanceof HtmlUnitDriver) {
            return; // Confirm dialog not supported for HtmlUnitDriver
        }

        var indexPage = this.indexPage.goToIndexPage();

        assertTrue(indexPage.getBeansNames().contains("Test bean 1"));

        var showBeanPage = indexPage.goToShowBeanPage(1L);

        assertEquals("Test bean 1", showBeanPage.getHeading());

        var abstractPage = showBeanPage.deleteBean();

        if (abstractPage instanceof IndexPage indexPageEdited) {
            assertFalse(indexPageEdited.getBeansNames().contains("Test bean 1"));
        } else {
            fail();
        }
    }

    @AfterEach
    public void tearDown() {
        this.webDriver.quit();
    }
}
