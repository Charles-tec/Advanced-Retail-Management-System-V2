package com.armsV2.armsApi.util;


import lombok.var;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

@Component
public class ResponseBuild<T> {
    public BiFunction<T, List<T>, Response> successResponse = (obj, objList) -> {
        var response = new Response();
        response.setData(obj);
        response.setDataList(objList);
        response.setMessage("Success");
        response.setStatusCode(200);
        return response;
    };

    public Function<Page<T>, Response> pagedResponse = (objList) -> {
        var response = new Response();
        response.setPagedList(objList);
        response.setMessage("Success");
        response.setStatusCode(200);
        return response;
    };
}
