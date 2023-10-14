import com.Application;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

    @ExtendWith( SpringExtension.class)
    @AutoConfigureMockMvc
    @SpringBootTest(classes = Application.class)
    public class AccountApplicationTest {

        @Autowired
        private MockMvc mockMvc;


        @Test
        public void addsNewAccount() throws Exception {
            String newAccount = "{\"id\":\"pangbo\",\"password\":\"123456\"}";
            mockMvc.perform(MockMvcRequestBuilders.post("/api/account")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(newAccount)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andReturn();
        }

        @Test
        public void getsSingleAccount() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.get("/api/account?id=pangbo")
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andReturn();
        }

        @Test
        public void deleteSingleAccount() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.delete("/api/account/0")
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andReturn();
        }

//        @Test
//        public void returnsNotFoundForInvalidSingleRide() throws Exception {
//            mockMvc.perform(MockMvcRequestBuilders.get("/api/account/1")
//                            .accept(MediaType.APPLICATION_JSON))
//                    .andExpect(status().isNotFound())
//                    .andReturn();
//        }


    }

