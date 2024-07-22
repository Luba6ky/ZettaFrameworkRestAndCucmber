package api.utils.po;

import api.utils.builders.RequestBuilder;
import io.restassured.response.Response;

public class DeletePO {
    private RequestBuilder requestBuilder;

    public DeletePO() {
        requestBuilder = new RequestBuilder();
    }

    public Response deletePost(String endpoint) {
        return requestBuilder.delete(endpoint, null, null, null, null);
    }
}
