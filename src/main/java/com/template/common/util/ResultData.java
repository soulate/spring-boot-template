package com.template.common.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ResultData {

    private int code = HttpStatus.OK.value();
    private String msg = HttpStatus.OK.toString();

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object result = null;

    public ResultData(){

    }

    public ResultData(int code, String msg){
        this.code = code;
        this.msg = msg;
    }
}
