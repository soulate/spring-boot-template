package com.template.sample.controller;

import com.template.common.util.BindingResultHelper;
import com.template.sample.service.SimpleService;
import com.template.sample.vo.SampleValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/internal/test")
public class SampleContoller {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SimpleService simpleService;

    @GetMapping("/list")
    public ResponseEntity list(@Valid SampleValidator sampleValidator, BindingResult bindingResult){

        logger.error("list >>>>>>> " + sampleValidator);

        if (bindingResult.hasErrors()) {
            // 바인딩 오류 처리
            //BindingResultHelper.invalidParameterException(bindingResult);
        }

        //Custom Validation
        sampleValidator.validate();

        return simpleService.getList();
    }

    @GetMapping("/test/list")
    public ResponseEntity testList(@RequestParam String test){

        logger.error("test >>>>>>> " + test);

        return simpleService.getList();
    }

    @RequestMapping("/list2")
    public ResponseEntity list2(){

        return simpleService.getList2();
    }

    @PostMapping("/insert")
    public ResponseEntity insert(@RequestBody Map<String,Object> map){

        logger.error("insert >>>>>>> " + map.toString());

        return simpleService.setData();
    }

    @PostMapping("/test/insert")
    public ResponseEntity testInsert(@RequestBody Map<String,Object> map){

        logger.error("test >>>>>>> " + map.toString());

        return simpleService.setData();
    }
}
