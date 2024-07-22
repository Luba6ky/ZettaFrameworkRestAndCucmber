package api.utils.po;

import api.utils.builders.RequestBuilder;
import api.utils.requestDto.PostRequestDto;
import api.utils.requestDto.PutRequestDto;
import api.utils.responseDto.PostResponseDto;
import api.utils.responseDto.PutResponseDto;
import io.restassured.response.Response;
public class PutPO {
    private RequestBuilder requestBuilder;

    public PutPO() {
        requestBuilder = new RequestBuilder();
    }

    public PutResponseDto putPosts(String endpoint, PutRequestDto postData) {
        Response response = requestBuilder.put(endpoint, postData, null, null, null, null,null);
        return response.as(PutResponseDto.class);
    }
}

