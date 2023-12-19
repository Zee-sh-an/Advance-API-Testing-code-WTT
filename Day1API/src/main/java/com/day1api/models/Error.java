package com.day1api.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
public class Error {

    private final String message;

    private String code;

}
