package ch.fhnw.webec.coffeecatalogue.repository;

import ch.fhnw.webec.coffeecatalogue.model.Bean;
import ch.fhnw.webec.coffeecatalogue.model.Roaster;
import ch.fhnw.webec.coffeecatalogue.model.Roasting;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ActiveProfiles("test")
@DataJpaTest
public class BeanRepositoryIntegrationTest {
    @Autowired
    private BeansRepository beansRepository;

    @Autowired
    private RoasterRepository roasterRepository;

    @Autowired
    private RoastingRepository roastingRepository;

    @Test
    public void testFindAll() {
        var beans = this.beansRepository.findAll();
        var firstBean = beans.get(0);
        var firstBeanRoastingsNames = firstBean.getRoastings().stream().map(Roasting::getName).toList();

        assertEquals(3, beans.size());
        assertEquals("Test bean 1", firstBean.getName());
        assertEquals("Test description 1", firstBean.getDescription());
        assertEquals("https://www.blasercafe.ch/Blasercafe_lilla_e_rose_250g.png?itok=uXhX-Z4E", firstBean.getImagePath());
        assertEquals("Test roaster 1", firstBean.getRoaster().getName());
        assertTrue(firstBeanRoastingsNames.contains("Test roasting 1"));
        assertTrue(firstBeanRoastingsNames.contains("Test roasting 2"));
    }

    @Test
    public void testSaveBean() {
        Roaster roaster = this.roasterRepository.findById(2L).orElseThrow();
        var bean = new Bean("new Bean", "https://imagelink.ch/png", "new description", roaster );
        var roasting1 = this.roastingRepository.findById(1L).orElseThrow();
        var roasting3 = this.roastingRepository.findById(3L).orElseThrow();

        bean.setRoastings(Set.of(roasting1,roasting3));

        assertEquals(3, this.beansRepository.findAll().size());

        var savedBean = this.beansRepository.save(bean);

        assertEquals(4, this.beansRepository.findAll().size());
        assertEquals("new Bean", savedBean.getName());
        assertEquals("new description", savedBean.getDescription());
        assertEquals(2, savedBean.getRoastings().size());
        assertTrue(savedBean.getRoastings().contains(roasting1));
        assertTrue(savedBean.getRoastings().contains(roasting3));
        assertFalse(savedBean.getFavorite());
    }

    @Test
    public void testUpdateBean() {
        var bean = this.beansRepository.findById(1L).orElseThrow();
        var roasting = this.roastingRepository.findById(1L).orElseThrow();

        assertEquals("Test bean 1", bean.getName());

        bean.setName("Edit test bean");
        var savedBean = this.beansRepository.save(bean);

        assertEquals(3, this.beansRepository.findAll().size());
        assertEquals("Edit test bean", savedBean.getName());
        assertEquals("Test description 1", savedBean.getDescription());
        assertTrue(savedBean.getRoastings().contains(roasting));
    }

    @Test
    public void testDeleteBean() {
        var bean = this.beansRepository.findById(1L).orElseThrow();

        assertEquals(3, this.beansRepository.findAll().size());

        this.beansRepository.delete(bean);

        assertEquals(2, this.beansRepository.findAll().size());
    }
}