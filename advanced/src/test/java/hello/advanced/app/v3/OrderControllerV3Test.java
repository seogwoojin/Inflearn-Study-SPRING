package hello.advanced.app.v3;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest
class OrderControllerV3Test {
    @Autowired private MockMvc mockMvc;

    @Test
    public void a1(){
        mockMvc.perform(
                requestBuilder
        )
    }

}