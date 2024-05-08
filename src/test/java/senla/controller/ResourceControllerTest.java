package senla.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import senla.config.AppConfig;
import senla.dto.security.AuthRequest;
import senla.dto.security.AuthResponse;
import senla.servise.AuthService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class}, loader = AnnotationConfigWebContextLoader.class)
@WebAppConfiguration
@Transactional
public class ResourceControllerTest {

    private MockMvc mockMvc;

    @Mock
    private AuthService authService;

    @InjectMocks
    private ResourceController resourceController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(resourceController).build();
    }

    @Test
    public void testLoginEndpoint() throws Exception {
        AuthRequest authRequest = new AuthRequest("username", "password");
        AuthResponse authResponse = new AuthResponse("token");

        when(authService.authenticate(authRequest)).thenReturn(authResponse);

        mockMvc.perform(post("/api/v1/resource-controller/login")
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(authRequest)))
                .andExpect(status().isOk());
    }

    @Test
    public void testRegisterEndpoint() throws Exception {
        AuthRequest authRequest = new AuthRequest("username", "password");
        AuthResponse authResponse = new AuthResponse("token");

        when(authService.reg(authRequest)).thenReturn(authResponse);

        mockMvc.perform(post("/api/v1/resource-controller/reg")
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(authRequest)))
                .andExpect(status().isOk());
    }
}
