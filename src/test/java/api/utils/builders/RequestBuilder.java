package api.utils.builders;

import api.base.ApiBaseTest;
import api.utils.logging.Log;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class RequestBuilder extends ApiBaseTest {

    public Response post(String path, final Object inputEntity, Map<String, String> queryParams, Map<String, String> authMap, Map<String, String> requestHeaderParams, final ContentType parameterContentType) {
        Response response;
        RequestSpecification httpRequest = defaultRequest("POST");
        if (authMap != null) {
            httpRequest.auth().basic(authMap.get("username"), authMap.get("password"));
        }
        if (queryParams != null) {
            httpRequest.queryParams(queryParams);
        }
        if (inputEntity != null) {
            httpRequest.body(inputEntity);
        }

        if (requestHeaderParams != null) {
            requestHeaderParams.put("X-REQUESTED-WITH", java.util.UUID.randomUUID().toString());
            httpRequest.headers(requestHeaderParams);
        }

        response = httpRequest.contentType(parameterContentType != null ? parameterContentType : ContentType.JSON)
                .when().post(path).then().assertThat().log().all().extract().response();
        return response;
    }

    public Response get(final String path, Map<String, String> queryParams, final ContentType parameterContentType, Map<String, String> authMap, Map<String, String> requestHeaderParams) {
        RequestSpecification httpRequest = defaultRequest("GET");
        Response response;

        if (authMap != null) {
            httpRequest.auth().basic(authMap.get("username"), authMap.get("password"));
        }
        if (queryParams != null) {
            httpRequest.queryParams(queryParams);
        }

        if (requestHeaderParams != null) {
            requestHeaderParams.put("X-REQUESTED-WITH", java.util.UUID.randomUUID().toString());
            Log.info("X-REQUESTED-WITH: " + requestHeaderParams.get("X-REQUESTED-WITH"));
            httpRequest.headers(requestHeaderParams);
        }

        response = httpRequest.headers("Accept", ContentType.JSON)
                .contentType(parameterContentType != null ? parameterContentType : ContentType.JSON)
                .when().get(path).then().assertThat().log().body().extract().response();
        return response;
    }

    public Response put(final String path, final Object inputEntity, Map<String, String> queryParams, Map<String, String> authMap, Map<String, String> formParams, final ContentType parameterContentType, Map<String, String> requestHeaderParams) {
        RequestSpecification httpRequest = defaultRequest("PUT");
        Response response;

        if (authMap != null) {
            httpRequest.auth().basic(authMap.get("username"), authMap.get("password"));
        }
        if (formParams != null) {
            httpRequest.formParams(formParams);
        }
        if (queryParams != null) {
            httpRequest.queryParams(queryParams);
        }
        if (inputEntity != null) {
            httpRequest.body(inputEntity);
        }

        if (requestHeaderParams != null) {
            requestHeaderParams.put("X-REQUESTED-WITH", java.util.UUID.randomUUID().toString());
            Log.info("X-REQUESTED-WITH: " + requestHeaderParams.get("X-REQUESTED-WITH"));
            httpRequest.headers(requestHeaderParams);
        }

        response = httpRequest.headers("Accept", ContentType.JSON)
                .contentType(parameterContentType != null ? parameterContentType : ContentType.JSON)
                .when().put(path).then().assertThat().log().all().extract().response();
        return response;
    }

    public Response delete(String path, Map<String, String> queryParams, Map<String, String> authMap, Map<String, String> requestHeaderParams, final ContentType parameterContentType) {
        RequestSpecification httpRequest = defaultRequest("DELETE");
        Response response;

        if (authMap != null) {
            httpRequest.auth().basic(authMap.get("username"), authMap.get("password"));
        }

        if (queryParams != null) {
            httpRequest.queryParams(queryParams);
        }

        if (requestHeaderParams != null) {
            requestHeaderParams.put("X-REQUESTED-WITH", java.util.UUID.randomUUID().toString());
            Log.info("X-REQUESTED-WITH: " + requestHeaderParams.get("X-REQUESTED-WITH"));
            httpRequest.headers(requestHeaderParams);
        }

        response = httpRequest.headers("Accept", ContentType.JSON)
                .contentType(parameterContentType != null ? parameterContentType : ContentType.JSON)
                .when().delete(path).then().assertThat().log().all().extract().response();
        return response;
    }

    public RequestSpecification defaultRequest(String requestType) {
        if (requestType.equalsIgnoreCase("GET")) {
            return given().log().ifValidationFails();
        } else {
            return given().log().ifValidationFails().header("Content-type", "application/json; charset=UTF-8");
        }
    }
}
