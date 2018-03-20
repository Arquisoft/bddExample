package es.uniovi.asw.steps;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import es.uniovi.asw.listeners.MessageListener;
import es.uniovi.asw.producers.KafkaProducer;
import io.reactivex.Observable;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertTrue;


@RunWith(SpringRunner.class)
@TestPropertySource(properties = "spring.kafka.bootstrap-servers=${spring.embedded.kafka.brokers}")
@EmbeddedKafka
public class KafkaSteps {

  @Autowired
  private KafkaProducer kafkaProducer;

  @Autowired
  private MessageListener messageListener;

  private Observable<String> observable;
  
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
