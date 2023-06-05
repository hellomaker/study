package com.hellomaker.swagger.controller;

import com.hellomaker.swagger.bean.Word;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@Api
@RestController
public class HelloController {

    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "string", name = "word", value = "", required = true)
    })
    @ApiOperation(value = "欢迎进入", notes = "根据输入的词打印欢迎", httpMethod = "GET")
    @GetMapping("hello")
    @ApiResponse(code = 200, message = "成功")
    Word hello(@ApiParam(value = "输入词",required = true, example = "word") @RequestParam String word) {
        Word wordBean = new Word();
        wordBean.setName(word);
        wordBean.setValue(new Random().nextDouble());
        return wordBean;
    }


}
