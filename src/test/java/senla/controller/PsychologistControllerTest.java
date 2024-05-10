package senla.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import senla.config.*;
import senla.dto.order.OrderDTOToEntity;
import senla.dto.payment.PaymentDTOToEntity;
import senla.dto.psychologist.PsychologistDTOToEntity;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class}, loader = AnnotationConfigWebContextLoader.class)
@WebAppConfiguration
@Transactional

public class PsychologistControllerTest {
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void findAllTest() throws Exception {
        mockMvc.perform(get("/api/v1/psychologists"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "testUser", roles = {"CLIENT"}, password = "sdsdsd")

    public void findByIdTest() throws Exception {
        mockMvc.perform(get("/api/v1/psychologists/1"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "testUser", roles = {"CLIENT"}, password = "sdsdsd")
    public void saveTest() throws Exception {
        PsychologistDTOToEntity dto = PsychologistDTOToEntity.builder()
                .experience(7)
                .build();

        mockMvc.perform(post("/api/v1/psychologists")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "testUser", roles = {"CLIENT"}, password = "sdsdsd")
    public void updateTest() throws Exception {
        PsychologistDTOToEntity dto = new PsychologistDTOToEntity();
        dto.setExperience(6);

        mockMvc.perform(put("/api/v1/psychologists/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "testUser", roles = {"CLIENT"}, password = "sdsdsd")
    public void deleteByIdTest() throws Exception {
        mockMvc.perform(delete("/api/v1/psychologists/1"))
                .andExpect(status().isOk());
    }
}

