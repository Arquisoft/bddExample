package es.uniovi.asw;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.ApplicationContext;

@RunWith(SpringRunner.class)
@WebMvcTest(Application.class)
// @IntegrationTest({ "server.port=0" })
public class MainControllerTest {

  @Autowired
	private ApplicationContext context;
  
  @Autowired
	private MockMvc mvc;
  
  @Before
  public void setUp() throws Exception {
    mvc = MockMvcBuilders.webAppContextSetup(context).build();
  }

  @Test
  public void testLanding() throws Exception {
    mvc.perform(get("/")).andExpect(status().isOk()).andExpect(content().string(containsString("Home")));
  }

  @Test
  public void testSort() throws Exception {
    mvc.perform(get("/sort")).andExpect(status().isOk()).andExpect(content().string(containsString("sorted")));
  }

  @Test
  public void testSearchOK() throws Exception {
    mvc.perform(get("/search?name=pepe")).andExpect(status().isOk()).andExpect(content().string(containsString("pepe")));
  }
}