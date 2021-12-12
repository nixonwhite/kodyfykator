import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import ua.org.gurt.kodyfykator.KodyfykatorApplication;
import ua.org.gurt.kodyfykator.controller.MyController;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = KodyfykatorApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
public class KodyfykatorTests {

    @Autowired
    public MyController controller;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void contextLoads() {
        assertThat(controller).isNotNull();
    }

    @Test
    public void testControllerver() {
        assertThat(this.testRestTemplate.getForObject("http://localhost:8080/version", String.class)).contains("v1");
    }

    @Test
    public void testController() {
        assertThat(this.testRestTemplate.postForEntity("http://localhost:8080/find/", "test", String.class)).isNotNull();
    }
}
