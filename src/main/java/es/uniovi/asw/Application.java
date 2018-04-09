package es.uniovi.asw;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.prometheus.client.spring.boot.EnablePrometheusEndpoint;
import io.prometheus.client.spring.boot.EnableSpringBootMetricsCollector;

@EnableAutoConfiguration
@SpringBootApplication
@EnablePrometheusEndpoint
@EnableSpringBootMetricsCollector
public class Application {

  public static void main(String[] args) {
    SpringApplication app = new SpringApplication(Application.class);
    app.setBannerMode(Banner.Mode.OFF);
    app.run(args);
  }
}