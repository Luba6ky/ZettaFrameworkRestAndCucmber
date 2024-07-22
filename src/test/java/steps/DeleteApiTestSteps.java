package steps;

import api.utils.parser.JsonParser;
import api.utils.po.DeletePO;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;

public class DeleteApiTestSteps {

    private DeletePO deletePO = new DeletePO();
    private Response response;
    private String endpoint;

    @Given("I have the DELETE endpoint for posts")
    public void i_have_the_delete_endpoint_for_posts() {
        JsonParser.getInstance().loadJsonData("src/test/resources/drivers/TestData.json");
        endpoint = JsonParser.getInstance().getValue("deletePostEndpoint");
    }

    @Given("I delete the post with id {int}")
    public void i_delete_the_post_with_id(int id) {
        response = deletePO.deletePost(endpoint + "/" + id);
    }

    @Then("the post should be deleted successfully")
    public void the_post_should_be_deleted_successfully() {
        Assertions.assertEquals(200, response.getStatusCode(), "Expected status code is 200");
    }
}
