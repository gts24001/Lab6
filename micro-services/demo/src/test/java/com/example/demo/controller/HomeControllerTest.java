package com.example.demo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.containsString;

@WebMvcTest(HomeController.class)
public class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testHomeEndpoint() throws Exception {
        mockMvc.perform(get("/"))
               .andExpect(status().isOk())
               .andExpect(content().string(containsString("Hello wonderful Spring Boot!")));
    }

    @Test
    public void testOtherEndpoint() throws Exception {
        mockMvc.perform(get("/other-endpoint"))
               .andExpect(status().isOk())
               .andExpect(content().string(containsString("Hello wonderful from OTHER-ENDPOINT!")));
    }

    @Test
    public void testSayHiBack() throws Exception {
        String testData = "Hello Test";
        mockMvc.perform(post("/say-hi-back")
               .contentType(MediaType.APPLICATION_JSON)
               .param("data", testData))
               .andExpect(status().isCreated())
               .andExpect(content().string(containsString("Echo: " + testData)));
    }

    @Test
    public void testSayHiBackDefaultValue() throws Exception {
        mockMvc.perform(post("/say-hi-back")
               .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isCreated())
               .andExpect(content().string(containsString("Echo: Enjoy your day!")));
    }

    @Test
    public void testValidEmail() throws Exception {
        mockMvc.perform(post("/email-address-valid")
               .contentType(MediaType.APPLICATION_JSON)
               .param("email", "test@example.com"))
               .andExpect(status().isOk())
               .andExpect(content().string("Valid Email Address\n"));
    }

    @Test
    public void testInvalidEmail() throws Exception {
        mockMvc.perform(post("/email-address-valid")
               .contentType(MediaType.APPLICATION_JSON)
               .param("email", "invalid-email"))
               .andExpect(status().isOk())
               .andExpect(content().string("Invalid Email Address\n"));
    }

    @Test
    public void testVeryWeakPassword() throws Exception {
        mockMvc.perform(post("/check-password-strength")
               .contentType(MediaType.APPLICATION_JSON)
               .param("password", "weak"))
               .andExpect(status().isOk())
               .andExpect(content().string("Very Weak\n"));
    }

    @Test
    public void testStrongPassword() throws Exception {
        mockMvc.perform(post("/check-password-strength")
               .contentType(MediaType.APPLICATION_JSON)
               .param("password", "MyStr0ng@Pass"))
               .andExpect(status().isOk())
               .andExpect(content().string("Very Strong\n"));
    }

    @Test
    public void testMediumPassword() throws Exception {
        mockMvc.perform(post("/check-password-strength")
               .contentType(MediaType.APPLICATION_JSON)
               .param("password", "Password123"))
               .andExpect(status().isOk())
               .andExpect(content().string("Medium\n"));
    }
}