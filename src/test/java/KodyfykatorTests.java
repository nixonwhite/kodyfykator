import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import ua.org.gurt.kodyfykator.KodyfykatorApplication;
import ua.org.gurt.kodyfykator.controller.SettleController;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = KodyfykatorApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
class KodyfykatorTests {

    @Autowired
    SettleController controller;

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    void contextLoads() {
        assertThat(controller).isNotNull();
    }

    @Test
    void testControllerver() {
        assertThat(this.testRestTemplate.getForObject("http://localhost:8080/version", String.class)).contains("1.1");
    }

    @Test
    void testController() {
        assertThat(this.testRestTemplate.postForEntity("http://localhost:8080/find/", "test", String.class)).isNotNull();
    }
}
