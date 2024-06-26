package org.dummy.world.peopleservice.controller;

import org.dummy.world.peopleservice.config.security.SecurityConfig;
import org.dummy.world.peopleservice.model.City;
import org.dummy.world.peopleservice.service.AmericaService;
import org.dummy.world.peopleservice.service.EuropeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.togglz.core.manager.FeatureManager;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CityController.class)
@Import(SecurityConfig.class)
class CityControllerMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EuropeService europeService;

    @MockBean
    private AmericaService americaService;

    @MockBean
    private FeatureManager featureManager;


    @Test
    void noUserReturnsUnautherized() throws Exception {
        // Given

        // When
        mockMvc
                .perform(MockMvcRequestBuilders.get("/cities"))
                .andDo(print())
                .andExpect(status().isUnauthorized());

        // Then

    }

    @Test
    @WithMockUser(roles = "READ")
    void returnsCities() throws Exception {
        // Given
        when(americaService.findAll()).thenReturn(List.of(
                new City("Austin", 974_000L),
                new City("New York", 8_260_000L),
                new City("Washington", 671_000L)
        ));

        when(europeService.findAll()).thenReturn(List.of(
                new City("Brussels", 1_209_000L),
                new City("Dendermonde", 45_800L)
        ));

        // When
        mockMvc
                .perform(MockMvcRequestBuilders.get("/cities"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("""
                                                [
                                                {"name":"Brussels","amount":1209000},
                                                {"name":"Dendermonde","amount":45800},
                                                {"name":"Austin","amount":974000},
                                                {"name":"New York","amount":8260000},
                                                {"name":"Washington","amount":671000}]
                        """
                ))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        // Then

    }

    @Test
    @WithMockUser(username = "franky")
    void unkownUserReturns403() throws Exception {
        // Given

        // When
        mockMvc
                .perform(MockMvcRequestBuilders.get("/cities"))
                .andDo(print())
                .andExpect(status().isForbidden());

        // Then

    }

    @Test
    @WithMockUser(roles = {})
    void noRoleReturns403() throws Exception {
        // Given

        // When
        mockMvc
                .perform(MockMvcRequestBuilders.get("/cities"))
                .andDo(print())
                .andExpect(status().isForbidden());

        // Then

    }

}