package com.template.sample.vo;

import com.template.common.util.InvalidParameterException;
import com.template.common.util.StaticMessageSource;
import lombok.Data;
import org.springframework.context.MessageSource;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class SampleValidator {

    @Size(min=2, max=30, message = "field.required.size.2~30")
    private String name;

    @NotNull(message = "field.required.msg")
    @Size(min=1 )
    private String msg;

    /*
    @NotNull(message = "날짜를 입력해주세요.")
    @DateTimeFormat(pattern = "yyyyMMdd")
    private LocalDate startDate;
    */

    public void validate(){
        //사용자 정의 오류.
        String msg = StaticMessageSource.getMessage("field.required.custom", null);
        throw new InvalidParameterException(msg);
    }

}
