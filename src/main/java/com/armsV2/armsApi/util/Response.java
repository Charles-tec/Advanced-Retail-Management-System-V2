package com.armsV2.armsApi.util;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
public class Response {
    private int statusCode;
    private String message;
    private Object data;
    private List<?> dataList;
    private Page<?> pagedList;
}
