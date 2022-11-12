package io.github.t45k.spring_trial.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import javax.servlet.http.Cookie;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
public class TestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void test() throws Exception {
        final MvcResult mvcResult = mockMvc.perform(get("/test")).andExpect(content().string("0")).andReturn();
        final String sessionCookieValue = mvcResult.getResponse().getCookie("SESSION").getValue();
        mockMvc.perform(get("/test").cookie(new Cookie("SESSION", sessionCookieValue))).andExpect(content().string("1"));
        mockMvc.perform(get("/test").cookie(new Cookie("SESSION", sessionCookieValue))).andExpect(content().string("2"));
    }
}
