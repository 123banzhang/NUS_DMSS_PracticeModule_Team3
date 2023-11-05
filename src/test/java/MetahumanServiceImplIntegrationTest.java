import com.Application;
import com.sys.mapper.MetahumanMapper;
import com.sys.mapper.VoiceMapper;
import com.sys.service.impl.MetahumanServiceImpl;
import com.sys.vo.MetahumanDetailVo;
import com.sys.vo.MetahumanInfo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = Application.class, properties = {"spring.config.location=classpath:application.yml"})
@Transactional
class MetahumanServiceImplIntegrationTest {


    @Resource
    private MetahumanServiceImpl metahumanService;

    @Resource
    private MetahumanMapper metahumanMapper;

    @Resource
    private VoiceMapper voiceMapper;

    @Test
    void testFindMetahumanByCondition() {
        // Create test data
        MetahumanInfo metahumanInfo = new MetahumanInfo();
        metahumanInfo.setGender("Male");

        // Call method and validate result
        List<MetahumanDetailVo> result = metahumanService.findMetahumanByCondition(metahumanInfo);
        assertNotNull(result);
    }

    @Test
    void testCreateMetahuman() {
        MetahumanDetailVo metahumanDetail = new MetahumanDetailVo();
        metahumanDetail.setGender("Male");
        metahumanDetail.setName("John Doe");
        metahumanDetail.setStatus("online");
        metahumanDetail.setPitch(1.2F);
        boolean result = metahumanService.createMetahuman(metahumanDetail);
        assertTrue(result);
    }

    @Test
    void testUpdateMetahuman() {
        // Assume a Metahuman with ID 1 exists
        Long mid = 12L;
        MetahumanDetailVo metahumanDetail = new MetahumanDetailVo();
        metahumanDetail.setGender("Female");
        metahumanDetail.setName("Jane Doe");
        metahumanDetail.setStatus("offline");
        metahumanDetail.setPitch(1.2F);
        boolean result = metahumanService.updateMetahuman(mid, metahumanDetail);
        assertTrue(result);
    }

    @Test
    void testDeleteMetahuman() {
        // Assume a Metahuman with ID 1 exists
        Long mid = 12L;

        boolean result = metahumanService.deleteMetahuman(mid);
        assertTrue(result);
    }

    @Test
    void testFindMetahumanDetailVoById() {
        // Assume a Metahuman with ID 1 exists
        Long mid = 12L;

        MetahumanDetailVo result = metahumanService.findMetahumanDetailVoById(mid);
        assertNotNull(result);
    }
}

