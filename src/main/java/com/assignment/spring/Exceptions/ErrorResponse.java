package com.assignment.spring.Exceptions;

import com.assignment.spring.entity.Response;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class ErrorResponse implements Response {
    private String remoteServerResponse;
}
