import com.Application;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest(classes = Application.class, properties = {"spring.config.location=classpath:application.yml"})
class MetahumanServiceImplIntegrationTest {

    @Resource
    private MetahumanServiceImpl metahumanService;

//    @Test
//    void testCreateMetahuman() {
//        MetahumanInfo metahumanInfo = new MetahumanInfo();
//        metahumanInfo.setGender("Male");
//        metahumanInfo.setName("John Doe");
//        metahumanInfo.setStatus("online");
//
//        boolean result = metahumanService.createMetahuman(metahumanD);
//        assertTrue(result);
//    }

//    @Test
//    void testDeleteMetahuman() {
//        Long mid = 2L;  // Assume there is a metahuman with id 1
//        boolean result = metahumanService.deleteMetahuman(mid);
//        assertTrue(result);
//    }

//    @Test
//    void testUpdateMetahuman() {
//        MetahumanInfo metahumanInfo = new MetahumanInfo();
//        metahumanInfo.setGender("Female");
//        metahumanInfo.setName("Jane Doe");
//        metahumanInfo.setStatus("offline");
//
//        Long mid = 3L;  // Assume there is a metahuman with id 1
//        boolean result = metahumanService.updateMetahuman(mid, metahumanInfo);
//        assertTrue(result);
//    }

//    @Test
//    void testFindMetahumanByCondition() {
//        MetahumanInfo metahumanInfo = new MetahumanInfo();
//        metahumanInfo.setGender("Male");
//
//        List<MetahumanDetailVo> result = metahumanService.findMetahumanBycondition(metahumanInfo);
//        assertNotNull(result);
//        // Print the result to the console
//        System.out.println(result);
//    }


    // ... Other test methods for update, delete, and findMetahumanBycondition
}


