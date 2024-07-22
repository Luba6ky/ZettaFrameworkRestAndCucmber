package steps;

import api.base.ApiBaseTest;
import api.utils.parser.JsonParser;
import api.utils.po.GetPO;
import api.utils.responseDto.GetResponseDto;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import io.restassured.response.Response;

import java.util.Arrays;
import java.util.List;

public class GetApiTestSteps extends ApiBaseTest {

    private GetPO postPO = new GetPO();

    private String endpoint;
    private List<GetResponseDto> responsePosts;
    private Response response;

    @Given("I have the GET endpoint for posts")
    public void i_have_the_get_endpoint_for_posts() {
        JsonParser.getInstance().loadJsonData("src/test/resources/drivers/TestData.json");
        endpoint=JsonParser.getInstance().getValue("postsEndpoint");
    }

    @When("I send a GET request to the endpoint")
    public void i_send_a_get_request_to_the_endpoint() {
        response = postPO.getPosts(endpoint);
        responsePosts = Arrays.asList(response.as(GetResponseDto[].class));
    }

    @Then("the response status code for GET should be {int}")
    public void the_response_status_code_for_get_should_be(int statusCode) {
        Assertions.assertEquals(statusCode, response.getStatusCode());
    }

    @Then("the response should contain posts")
    public void the_response_should_contain_posts() {
        Assertions.assertFalse(responsePosts.isEmpty(), "The response should contain posts");
    }
}


