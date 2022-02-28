package ch.fhnw.webec.coffeecatalogue.controller;

import ch.fhnw.webec.coffeecatalogue.repository.RoasterRepository;
import com.mitchellbosecke.pebble.boot.autoconfigure.PebbleAutoConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import(PebbleAutoConfiguration.class)
@WebMvcTest(RoasterController.class)
public class RoasterControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RoasterRepository roasterRepository;

    @Test
    public void testShowRoasterPage() throws Exception{
        this.mockMvc.perform(get("/bean/roaster"))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("Our favorite roasters")));
    }
}
