package steps;

import api.utils.config.PropertyReader;
import api.utils.database.DBConnection;
import api.utils.database.models.ExampleModel;
import api.utils.parser.JsonParser;
import api.utils.po.PostPO;
import api.utils.requestDto.PostRequestDto;
import api.utils.responseDto.PostResponseDto;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import io.restassured.response.Response;

import java.util.Properties;

public class PostApiTestSteps {

    private PostPO postPO = new PostPO();
    private String endpoint;
    private PostRequestDto postData;
    private PostResponseDto response;
    private final ExampleModel exampleModel = new ExampleModel();


    @Given("I have the POST endpoint for posts")
    public void i_have_the_post_endpoint_for_posts() {
        JsonParser.getInstance().loadJsonData("src/test/resources/drivers/TestData.json");
        endpoint=JsonParser.getInstance().getValue("postsEndpoint");    }

    @Given("I have the post data")
    public void i_have_the_post_data() {
        postData = new PostRequestDto();
        postData.setTitle("foo");
        postData.setBody("bar");
        postData.setUserId(1);
    }

    @When("I send a POST request to the endpoint with the post data")
    public void i_send_a_post_request_to_the_endpoint_with_the_post_data() {
        response = postPO.createPost(endpoint, postData);
    }

    @Then("the response status code for POST should be {int}")
    public void the_response_status_code_for_post_should_be(int statusCode) {
        Assertions.assertEquals(statusCode, 201); // Статус кодът за успешна POST заявка е 201
    }

    @Then("the response should contain the post data")
    public void the_response_should_contain_the_post_data() {
        Assertions.assertEquals(postData.getTitle(), response.getTitle());
        Assertions.assertEquals(postData.getBody(), response.getBody());
        Assertions.assertEquals(postData.getUserId(), response.getUserId());
    }
    @Then("the post data should be saved correctly in the database")
    public void the_post_data_should_be_saved_correctly_in_the_database() {
        Properties properties = new PropertyReader().getProperties();

        DBConnection dbConnection = new DBConnection(properties);

        ExampleModel exampleModel = new ExampleModel();

        try {
            exampleModel.verifyUser(response.getUserId(), "testName", "testname@mail.com", "12343456");
        } catch (Exception e) {
            e.printStackTrace();
            Assertions.fail("Database verification failed.");
        }
    }
}
