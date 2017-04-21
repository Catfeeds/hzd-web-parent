package com.hzcf.platform.api.common;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * UserResponseBuilder
 *
 * @author gavin miao
 * @date 2016/12/2
 */
public class UserResponseBuilder {
    private HttpStatus status = HttpStatus.OK;
    private Object content;
    private HttpHeaders headers = new HttpHeaders();

    private UserResponseBuilder() {

    }

    public static UserResponseBuilder instance() {
        return new UserResponseBuilder();
    }

    public UserResponseBuilder status(HttpStatus status) {
        this.status = status;
        return this;
    }

    public UserResponseBuilder body(JsonNode object) {
        if(object != null){
            content = object;
        } else {
            content = null;
        }
        return this;
    }

    public UserResponseBuilder body(Object object) {
        if(object != null){
            content = UserJsonParseFactory.getUserParser().toJson(object);
        } else {
            content = null;
        }
        return this;
    }

    public UserResponseBuilder header(String headerName, String headerValue) {
        headers.set(headerName, headerValue);
        return this;
    }

    public ResponseEntity<Object> build() {
        return new ResponseEntity<Object>(content, headers, status);
    }

    public UserResponseBuilder setContent(JsonNode content) {
        this.content = content;
        return this;
    }
}
