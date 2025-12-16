package com.example.demo.global.exceptions.responses;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

    int status;

    String message;

    LocalDateTime timestamp;

    String path;

    List<SubError> subErrors;

    @Builder(
            builderClassName = "ErrorResponseBuilderDefault",
            builderMethodName = "builderDefault")
    public ErrorResponse(int status, String message,
                         LocalDateTime timestamp,
                         String path) {
        this.status = status;
        this.message = message;
        this.timestamp = timestamp;
        this.path = path;
    }

    @Builder(
            builderClassName = "ErrorResponseBuilderForForm",
            builderMethodName = "builderFields")
    public ErrorResponse(int status, String message,
                         LocalDateTime timestamp,
                         String path,
                         List<SubError> subErrors) {
        this(status,message,timestamp,path);
        this.subErrors = subErrors;
    }

}
