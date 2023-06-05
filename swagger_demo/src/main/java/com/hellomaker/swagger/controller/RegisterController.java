package com.hellomaker.swagger.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RestController;

@Api
@RestController
public class RegisterController {


    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "string", name = "name", value = "", required = true)
    })
    @ApiOperation(value = "", notes = "")
    Object register(String name) {

        return null;
    }

}
