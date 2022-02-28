package ch.fhnw.webec.coffeecatalogue.model;

import org.junit.jupiter.api.Test;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RoasterUnitTest {
    //EXTERNAL: Copied from Booklist
    private Validator createValidator() {
        LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
        localValidatorFactoryBean.afterPropertiesSet();

        return localValidatorFactoryBean;
    }

    @Test
    public void testValidationIsEmpty() {
        var validator = createValidator();
        Roaster roaster = new Roaster();
        roaster.setName("Test Roaster");
        roaster.setRoasterUrl("Test URL");

        Set<ConstraintViolation<Roaster>> violations = validator.validate(roaster);

        assertFalse(violations.isEmpty());
    }

    @Test
    public void testValidationDescription() {
        var validator = createValidator();
        Roaster roaster = new Roaster();
        roaster.setName("Test Roaster");
        roaster.setRoasterUrl("https://www.blasertrading.ch");

        Set<ConstraintViolation<Roaster>> violations = validator.validate(roaster);

        for (var violation : violations) {
            assertEquals("please enter a description", violation.getMessage());
        }
    }

    @Test
    public void testValidationURL() {
        var validator = createValidator();
        Roaster roaster = new Roaster();
        roaster.setName("Test Roaster");
        roaster.setDescription("Test description");

        Set<ConstraintViolation<Roaster>> violations = validator.validate(roaster);

        for (var violation : violations) {
            assertEquals("please enter a valid URL", violation.getMessage());
        }
    }

    @Test
    public void testValidationName() {
        var validator = createValidator();
        Roaster roaster = new Roaster();
        roaster.setDescription("Test description");
        roaster.setRoasterUrl("https://www.blasertrading.ch");

        Set<ConstraintViolation<Roaster>> violations = validator.validate(roaster);

        for (var violation : violations) {
            assertEquals("please enter a name", violation.getMessage());
        }
    }

    @Test
    public void testValidValidation() {
        var validator = createValidator();
        Roaster roaster = new Roaster();
        Bean bean = new Bean();
        roaster.setName("Test Roaster");
        roaster.setImage_path("/images/coffee/blasercafeag.jpg");
        roaster.setRoasterUrl("https://www.blasertrading.ch");
        roaster.setDescription("Test description of the roaster");
        roaster.setBeansList(Set.of(bean));

        Set<ConstraintViolation<Roaster>> violations = validator.validate(roaster);

        assertTrue(violations.isEmpty());
    }
}
