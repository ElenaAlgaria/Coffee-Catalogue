package ch.fhnw.webec.coffeecatalogue.controller;

import com.mitchellbosecke.pebble.boot.autoconfigure.PebbleAutoConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AboutController.class)
@Import(PebbleAutoConfiguration.class)
public class AboutControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testAboutPage() throws Exception {
        this.mockMvc.perform(get("/about"))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("About")));
    }
}
