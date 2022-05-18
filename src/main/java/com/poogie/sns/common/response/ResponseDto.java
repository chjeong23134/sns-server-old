package com.poogie.sns.common.response;

import lombok.Data;

@Data
public class ResponseDto {
    private ResponseStatusEnum status;
    private String message;
    private Object data;
}
