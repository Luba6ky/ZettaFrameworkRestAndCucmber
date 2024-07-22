package steps;

import api.utils.parser.JsonParser;
import api.utils.po.PutPO;
import api.utils.requestDto.PutRequestDto;
import api.utils.responseDto.PutResponseDto;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;

public class PutApiTestSteps {

    private PutPO putPO = new PutPO();
    private PutResponseDto putResponseDto;
    private String endpoint;

    @Given("I have the PUT endpoint for posts")
    public void i_have_the_put_endpoint_for_posts() {
        JsonParser.getInstance().loadJsonData("src/test/resources/drivers/TestData.json");
        endpoint = JsonParser.getInstance().getValue("postsEndpoint");
    }

    @Given("I update the post with id {int} with title {string} and body {string} for user {int}")
    public void i_update_the_post_with_id_with_title_and_body_for_user(int id, String title, String body, int userId) {
        PutRequestDto postRequest = new PutRequestDto(id, title, body, userId);
        putResponseDto = putPO.putPosts(endpoint + "/" + id, postRequest);
    }

    @Then("the post should be updated with id {int} and title {string} and body {string} for user {int}")
    public void the_post_should_be_updated_with_id_and_title_and_body_for_user(int id, String title, String body, int userId) {
        Assertions.assertEquals(id, putResponseDto.getId(), "Post ID does not match");
        Assertions.assertEquals(title, putResponseDto.getTitle(), "Title does not match");
        Assertions.assertEquals(body, putResponseDto.getBody(), "Body does not match");
        Assertions.assertEquals(userId, putResponseDto.getUserId(), "User ID does not match");
    }
}
