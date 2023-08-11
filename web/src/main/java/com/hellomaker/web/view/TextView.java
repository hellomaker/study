package com.hellomaker.web.view;

import org.springframework.web.servlet.View;

/**
 *
 * @describe: 文本内容接口
 *
 * @author  xianzhikun
 * @date    2023/8/10 23:43
 */
public interface TextView extends View {

    /**
     *
     * @describe: 抽象方法，返回view的文本实体
     *
     * @param
     * @return  {{@link String}}
     * @author  xianzhikun
     * @date    2023/8/10 22:24
     */
    String content();
}
