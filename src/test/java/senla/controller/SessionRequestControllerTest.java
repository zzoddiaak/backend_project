package senla.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import senla.config.*;
import senla.dto.review.ReviewDTOToEntity;
import senla.dto.sessionrequest.SessionRequestDTOToEntity;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class}, loader = AnnotationConfigWebContextLoader.class)
@WebAppConfiguration
@Transactional

public class SessionRequestControllerTest {
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
        mockMvc.perform(get("/api/v1/reviews"))
                .andExpect(status().isOk());
    }

    @Test
    public void findByIdTest() throws Exception {
        mockMvc.perform(get("/api/v1/sessions-requests/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void saveTest() throws Exception {
        SessionRequestDTOToEntity dto = SessionRequestDTOToEntity.builder()
                .requestDate(LocalDate.parse("2022-04-11"))
                .build();

        mockMvc.perform(post("/api/v1/sessions-requests")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk());
    }

    @Test
    public void updateTest() throws Exception {
        SessionRequestDTOToEntity dto = new SessionRequestDTOToEntity();
        dto.setRequestDate(LocalDate.parse("2022-04-11"));


        mockMvc.perform(put("/api/v1/sessions-requests/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteByIdTest() throws Exception {
        mockMvc.perform(delete("/api/v1/sessions-requests/1"))
                .andExpect(status().isOk());
    }
}

