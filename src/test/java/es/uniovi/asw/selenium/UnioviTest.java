package es.uniovi.asw.selenium;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class UnioviTest {
  private WebDriver driver;
  private String baseUrl;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new HtmlUnitDriver();
    baseUrl = "http://www.uniovi.es/";
    driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
  }

  @Test
  public void testUnioviTest3() throws Exception {
    driver.get(baseUrl + "/");
    driver.findElement(By.id("cabecera_keywords")).clear();
    driver.findElement(By.id("cabecera_keywords")).sendKeys("rector");
    driver.findElement(By.id("cabecera_search")).click();
    try {
      assertEquals("Rector", driver.findElement(By.cssSelector("li.alt > div.resultado_contenido > span.titulo > span.highlight")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }


}
