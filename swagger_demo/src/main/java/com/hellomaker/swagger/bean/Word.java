package com.hellomaker.swagger.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="单词信息", description="需要计算的单词")
public class Word {

    @ApiModelProperty(value = "名称")
    private String name;


    @ApiModelProperty(value = "值")
    private double value;

}
