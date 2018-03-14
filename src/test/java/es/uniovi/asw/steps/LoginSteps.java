package es.uniovi.asw.steps;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import es.uniovi.asw.controller.Main;

public class LoginSteps {
  
  private static final Logger LOG = LoggerFactory.getLogger(Main.class);

  @Given("^a list of users:$")
  public void a_list_of_users(List<User> users) throws Throwable {
    for (User u: users) {
      System.out.println("Inserting user..." + u.name + " - " + u.password);
    }
  }

  @When("^I login with name \"(.+)\" and password \"(.+)\"$")
  public void i_login_with_name_and_password(String name, String password) throws Throwable {
    System.out.println("Login with values..." + name + " - " + password);
  }

  @Then("^I receive a welcome message$")
  public void i_receive_a_welcome_message() throws Throwable {
    System.out.println("Wellcome value received");
  }
  
  public static class User {
    private String name;
    private String password;
  }
}
