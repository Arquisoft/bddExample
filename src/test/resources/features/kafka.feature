Feature: kafka message
  Scenario: client sends a message to kafka stream
    When the producer sends the message "Hello" to the topic "exampleTopic"
    Then the consumer receives the message "Hello" from the topic "exampleTopic"