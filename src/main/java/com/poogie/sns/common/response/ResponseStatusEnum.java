package com.poogie.sns.common.response;

public enum ResponseStatusEnum {
    OK(200, "OK"),
    BAD_REQUEST(400, "BAD_REQUEST");

    int code;
    String codeDescription;

    ResponseStatusEnum(int code, String codeDescription) {
        this.code = code;
        this.codeDescription = codeDescription;
    }
}
