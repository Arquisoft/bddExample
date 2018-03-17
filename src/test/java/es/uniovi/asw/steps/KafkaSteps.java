package es.uniovi.asw.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import es.uniovi.asw.Application;
import es.uniovi.asw.controller.Main;
import es.uniovi.asw.listeners.MessageListener;
import es.uniovi.asw.producers.KafkaProducer;
import io.reactivex.Observable;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@ContextConfiguration(classes=Application.class, loader=SpringApplicationContextLoader.class)
@IntegrationTest
@WebAppConfiguration
public class KafkaSteps {

  @Autowired
  private KafkaProducer kafkaProducer;

  @Autowired
  private MessageListener messageListener;

  private Observable<String> observable;
  
  private static final Logger LOG = LoggerFactory.getLogger(Main.class);

  @When("^the producer sends the message \"([^\"]*)\" to the topic \"([^\"]*)\"$")
  public void the_producer_sends_the_message_to_the_topic(String message, String topic) throws Throwable {
    observable = messageListener.getObservable();
    kafkaProducer.send(topic, message);
  }

  @Then("^the consumer receives the message \"([^\"]*)\" from the topic \"([^\"]*)\"$")
  public void the_consumer_receives_the_message_from_the_topic(String message, String topic) throws Throwable {
    String receivedMessage = observable.blockingFirst();
    System.out.println("Message received: " + receivedMessage);
    assertTrue(receivedMessage.equals(message));
  }

}
