package ch.fhnw.webec.coffeecatalogue.controller;

import ch.fhnw.webec.coffeecatalogue.model.Bean;
import ch.fhnw.webec.coffeecatalogue.model.Roaster;
import ch.fhnw.webec.coffeecatalogue.repository.BeansRepository;
import ch.fhnw.webec.coffeecatalogue.repository.RoasterRepository;
import ch.fhnw.webec.coffeecatalogue.repository.RoastingRepository;
import com.mitchellbosecke.pebble.boot.autoconfigure.PebbleAutoConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import(PebbleAutoConfiguration.class)
@WebMvcTest(BeansController.class)
public class BeanControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BeansRepository beansRepository;

    @MockBean
    private RoasterRepository roasterRepository;

    @MockBean
    private RoastingRepository roastingRepository;

    final Roaster roaster = new Roaster("Roaster 1", "/image/test.jpg","description", "https://www.roaster.ch");

    @Test
    public void testIndex() throws Exception {
        this.mockMvc.perform(get("/"))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("Coffee Catalogue")));
    }

    @Test
    public void testAddAsFavoriteIndex() throws Exception{
        var beanId = 1L;
        var bean = new Bean("new Bean", "https://imagelink.ch/png", "new description", roaster );
        var fav= "index";

        when(this.beansRepository.findById(beanId)).thenReturn(Optional.of(bean));

        this.mockMvc.perform(post("/bean/{id}/fav/{fav}", beanId, fav))
            .andExpect(status().is3xxRedirection());

        verify(this.beansRepository, times(1)).findById(beanId);
        verify(this.beansRepository, times(1)).save(bean);
    }

    @Test
    public void testAddAsFavoriteShow() throws Exception{
        var beanId = 1L;
        var bean = new Bean("new Bean", "https://imagelink.ch/png", "new description", roaster );
        var fav= "show";

        when(this.beansRepository.findById(beanId)).thenReturn(Optional.of(bean));

        this.mockMvc.perform(post("/bean/{id}/fav/{fav}", beanId, fav))
            .andExpect(status().is3xxRedirection());

        verify(this.beansRepository, times(1)).findById(beanId);
        verify(this.beansRepository, times(1)).save(bean);
    }

    @Test
    public void testIndexWithBeans() throws Exception {
        when(this.beansRepository.findBySearch("")).thenReturn(Arrays.asList(
            new Bean("new Bean", "https://imagelink.ch/png", "new description", roaster ),
            new Bean("new Bean 2", "https://imagelink.ch/png", "new description 2", roaster )
        ));

        this.mockMvc.perform(get("/"))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("new Bean")))
            .andExpect(content().string(containsString("new Bean 2")));
    }

    @Test
    public void testSearch() throws Exception {
        var search = "my test search";

        this.mockMvc.perform(get("/?search={search}", search))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString(search)));

        verify(this.beansRepository, times(1)).findBySearch(search);
        verify(this.beansRepository, never()).findAll();
    }

    @Test
    public void testShowBean() throws Exception {

        var beanId = 1L;
        var bean = new Bean("new Bean", "https://imagelink.ch/png", "new description", roaster );

        when(this.beansRepository.findById(beanId)).thenReturn(Optional.of(bean));

        this.mockMvc.perform(get("/bean/{id}/", beanId))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("new Bean")))
            .andExpect(content().string(containsString("new description")));

        verify(this.beansRepository, times(1)).findById(beanId);
    }

    @Test
    public void testDeleteBean() throws Exception {
        var beanId = 1L;
        var bean = new Bean("new Bean", "https://imagelink.ch/png", "new description", roaster );

        when(this.beansRepository.findById(beanId)).thenReturn(Optional.of(bean));

        this.mockMvc.perform(post("/bean/{id}/delete", beanId))
            .andExpect(status().is3xxRedirection());

        verify(this.beansRepository, times(1)).findById(beanId);
        verify(this.beansRepository, times(1)).delete(bean);
    }
}
