package com.template.sample.vo;

import com.template.common.util.InvalidParameterException;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class SampleValidator {

    @Size(min=2, max=30, message = "{field.required.size.2~30}")
    private String name;

    @NotNull(message = "{field.required.msg}")
    @Size(min=1 )
    private String msg;

    /*
    @NotNull(message = "날짜를 입력해주세요.")
    @DateTimeFormat(pattern = "yyyyMMdd")
    private LocalDate startDate;
    */

    public void validate(){
        String msg = String.format("%s : '%s'" , "커스텀 ","오류");
        throw new InvalidParameterException(msg);
    }

}
