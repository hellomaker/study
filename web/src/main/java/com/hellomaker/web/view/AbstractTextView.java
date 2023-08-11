package com.hellomaker.web.view;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 *
 * @describe: 抽象文本view父类
 *
 * @author  xianzhikun
 * @date    2023/8/10 22:21
 */
public abstract class AbstractTextView implements TextView {

    @Override
    public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/plain");
        response.getWriter().print(content());
    }

    /**
     *
     * @describe: 重写toString方法，值为content内容
     *
     * @param
     * @return  {{@link String}}
     * @author  xianzhikun
     * @date    2023/8/10 22:28
     */
    @Override
    public String toString() {
        return content();
    }
}
