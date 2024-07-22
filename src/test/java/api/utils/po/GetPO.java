package api.utils.po;

import api.utils.builders.RequestBuilder;
import io.restassured.response.Response;

public class GetPO {
    private RequestBuilder requestBuilder;

    public GetPO() {
        requestBuilder = new RequestBuilder();
    }

    public Response getPosts(String endpoint) {
        return requestBuilder.get(endpoint, null, null, null, null);
    }
}
