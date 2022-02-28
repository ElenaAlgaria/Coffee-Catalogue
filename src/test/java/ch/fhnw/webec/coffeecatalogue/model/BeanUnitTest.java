package ch.fhnw.webec.coffeecatalogue.model;

import org.junit.jupiter.api.Test;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BeanUnitTest {
    //EXTERNAL: Copied from Booklist
    private Validator createValidator() {
        LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
        localValidatorFactoryBean.afterPropertiesSet();

        return localValidatorFactoryBean;
    }

    @Test
    public void testValidationIsEmpty() {
        var validator = createValidator();
        Bean bean = new Bean();
        bean.setName("Test Bean");
        bean.setFavorite(true);

        Set<ConstraintViolation<Bean>> violations = validator.validate(bean);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void testValidValidation() {
        var validator = createValidator();
        Bean bean = new Bean();
        bean.setName("Test Bean");
        bean.setFavorite(true);
        bean.setRoaster(new Roaster());
        Roasting roasting = new Roasting();
        Set<Roasting> set = new HashSet<>();
        set.add(roasting);
        bean.setRoastings(set);

        Set<ConstraintViolation<Bean>> violations = validator.validate(bean);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void testValidationRoaster() {
        var validator = createValidator();
        Bean bean = new Bean();
        Roasting roasting = new Roasting();
        Set<Roasting> set = new HashSet<>();
        set.add(roasting);
        bean.setName("Test Bean");
        bean.setFavorite(true);
        bean.setRoastings(set);

        Set<ConstraintViolation<Bean>> violations = validator.validate(bean);

        for (var violation : violations) {
            assertEquals("please choose a roaster", violation.getMessage());
        }
    }

    @Test
    public void testValidationRoasting() {
        var validator = createValidator();
        Bean bean = new Bean();
        bean.setName("Test Bean");
        bean.setRoaster(new Roaster());
        bean.setFavorite(true);

        Set<ConstraintViolation<Bean>> violations = validator.validate(bean);

        for (var violation : violations) {
            assertEquals("at least one has to be chosen", violation.getMessage());
        }
    }

    @Test
    public void testValidationName() {
        var validator = createValidator();
        Bean bean = new Bean();
        bean.setRoaster(new Roaster());
        bean.setFavorite(true);
        Roasting roasting = new Roasting();
        Set<Roasting> set = new HashSet<>();
        set.add(roasting);
        bean.setRoastings(set);

        Set<ConstraintViolation<Bean>> violations = validator.validate(bean);

        for (var violation : violations) {
            assertEquals("not valid input - please enter the name", violation.getMessage());
        }
    }
}


