package com.hellomaker.web.controller;

import com.hellomaker.web.view.TextView;
import com.hellomaker.web.view.impl.RestViewBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("hello")
public class HelloController {

    @GetMapping("get")
    TextView get() {


        return RestViewBuilder.builder().message("hello").data("hello world").encrypt().build();
    }

}
