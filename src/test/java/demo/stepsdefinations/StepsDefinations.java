package demo.stepsdefinations;

import demo.stepsdefinations.actions.requestActions;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;


public class StepsDefinations {
    private Response response;

    public String productID;

    @Steps
    protected requestActions requestActions;

    @When("^I perform (.*) on (.*) resource$")
    public void iPerformRequest(String method, String resource) throws Exception {

        productID = ( productID == null) ? ""  : "/"+ productID;
        response= requestActions.performFullRequest(method,resource+productID);
    }

    @Then("Verify response time {int}")
    public void verify_response_time(Integer int1) {
        Assert.assertTrue(response.time() <= int1);
    }

    @Then("Id field should not be null")
    public void idFieldShouldNotBeNull() {
        productID = response.jsonPath().get("id").toString();
        Assert.assertNotNull(productID);
    }

    @Then("^response body should contain value of (.*) for key (.*)$")
    public void responseBodyShouldContainValueForKey(String value, String key) throws Throwable {
        requestActions.responseBodyShouldContainValueForKey(value,key);
    }

    @Given("I create a new product from (.*)$")
    public void iLoadBodyToJsonString(String resourceFile) throws Exception {
        requestActions.getRequestBodyFile(resourceFile);
    }


    @Then("response status code is {int}")
    public void responseStatusCodeIs(int statusCode) {
            requestActions.responseCodeShouldBe(statusCode);
    }

    @And("response matches the schema from (.*)$")
    public void validateSchema(String resourceFile) {
        requestActions.validateResponseSchema(resourceFile);
    }

    @Given("I create a new request")
    public void i_create_a_new_media_endpoint() {
        System.out.println("creating a new object");
    }
}
