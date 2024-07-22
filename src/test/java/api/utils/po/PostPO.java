package api.utils.po;

import api.utils.builders.RequestBuilder;
import api.utils.requestDto.PostRequestDto;
import api.utils.responseDto.PostResponseDto;
import io.restassured.response.Response;

public class PostPO {
    private RequestBuilder requestBuilder;

    public PostPO() {
        requestBuilder = new RequestBuilder();
    }

    public PostResponseDto createPost(String endpoint, PostRequestDto postData) {
        Response response = requestBuilder.post(endpoint, postData, null, null, null, null);
        return response.as(PostResponseDto.class);
    }
}
