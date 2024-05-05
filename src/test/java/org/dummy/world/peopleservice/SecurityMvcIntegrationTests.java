package org.dummy.world.peopleservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SecurityMvcIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void citiesUnauthenticated_returnsUnauthorized() throws Exception {
        // Given

        // When
        mockMvc.perform(MockMvcRequestBuilders.get("/cities"))
            .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(roles = "GUEST")
    void citiesWrongRole_returnsForbidden() throws Exception {
        // Given

        // When
        mockMvc.perform(MockMvcRequestBuilders.get("/cities"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "READ")
    void citiesCorrectRole_returnsOk() throws Exception {
        // Given

        // When
        mockMvc.perform(MockMvcRequestBuilders.get("/cities"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "jef")
    void citiesUnknownUser_returnsForbidden() throws Exception {
        // Given

        // When
        mockMvc.perform(MockMvcRequestBuilders.get("/cities"))
                .andExpect(status().isForbidden());
    }

}
