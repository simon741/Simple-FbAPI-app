package sk.simon;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * Created by Simon on 12.11.2016.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class FbUserControllerTest {

    //Token expires after few hours
    private String accessToken = "EAAQx8CaKKZCoBAIY0UkTThZABVu3unZA78w8zACOyf5ZCxty5xZCyEqMURoSJKpZA4VH1QyhlMxhsUbGY3sjtimenibyMl29MBZC3TDyWZCLo9hkJslG0sVJT2aPCQnTTdmr2uAuQBflOgAB5CZAMW2EUXZBw6GvZAO6qHou7pwi4wrAAZDZD";
    private String userFbId = "10206248904662029";

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mvc;

    @Test
    public void retrieveAndSaveFbUserTest() throws Exception {
        FbUserAccessDetailsDTO fbUserAccessDetailsDTO = new FbUserAccessDetailsDTO();
        fbUserAccessDetailsDTO.setAccessToken(accessToken);
        fbUserAccessDetailsDTO.setUserId(userFbId);

        objectMapper.writeValueAsBytes(fbUserAccessDetailsDTO);
        mvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(fbUserAccessDetailsDTO)))
                .andExpect(status().isCreated());
    }
}
