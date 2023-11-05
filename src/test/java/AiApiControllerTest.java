import com.Application;
import com.client.entitiy.ChatRequest;
import com.client.service.LanguageModelService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sys.service.impl.MetahumanServiceImpl;
import com.sys.vo.MetahumanDetailVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@WebMvcTest(AiApiController.class)
@SpringBootTest(classes = Application.class, properties = {"spring.config.location=classpath:application.yml"})
@AutoConfigureMockMvc
public class AiApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MetahumanServiceImpl metahumanService;

    @MockBean
    private LanguageModelService languageModelService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void getCompletion_ReturnsMessage_WhenMetahumanExists() throws Exception {
        // 假设这是从服务返回的MetaHuman详细信息
        MetahumanDetailVo metahumanDetailVo = new MetahumanDetailVo();
        metahumanDetailVo.setName("John Doe");
        metahumanDetailVo.setGender("male");
        metahumanDetailVo.setDescription("Some description");
        metahumanDetailVo.setCreateTime(LocalDateTime.parse("2023-01-01T00:00:00Z", DateTimeFormatter.ISO_DATE_TIME));
        metahumanDetailVo.setUpdateTime(LocalDateTime.parse("2023-01-01T00:00:00Z", DateTimeFormatter.ISO_DATE_TIME));

        // 假设这是从语言模型服务返回的响应
        String expectedResponse = "Some response from language model";

        ChatRequest chatRequest = new ChatRequest();
        chatRequest.setMetahumanId(1L);
        chatRequest.setSessionId("session-123");
        chatRequest.setUserMessage("Hello, what's your name?");

        // 配置 Mocks
        given(metahumanService.findMetahumanDetailVoById(1L)).willReturn(metahumanDetailVo);
        given(languageModelService.sendMessage(anyList())).willReturn(ResponseEntity.ok(expectedResponse));

        // 执行并验证结果
        mockMvc.perform(post("/api/completion")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(chatRequest)))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedResponse));
    }
}
