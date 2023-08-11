package com.hellomaker.web.common;

/**
 *
 * @describe: 常量
 *
 * @author  xianzhikun
 * @date    2023/8/11 13:50
 */
public class ViewConst {

    //成功
    public static final Integer OK_STATUS = 2000;


    //参数错误
    public static final Integer PARAM_ERROR_STATUS = 4000;
    //token错误或未登录
    public static final Integer UNAUTHORIZED_STATUS = 4001;
    //没有结果的返回
    public static final Integer NO_RESULT_STATUS = 4002;
    //没有访问权限
    public static final Integer FORBIDDEN_STATUS = 4003;
    //密码初始化后未修改
    public static final Integer PASSWORD_NEED_RESET_STATUS = 4004;


    //服务器内部错误
    public static final Integer INNER_ERROR_STATUS = 5000;

    public static final String CODE = "code";

    public static final String MESSAGE = "message";

    public static final String DATA = "data";

    public static final String USERS = "users";

    public static final String DEPTS = "depts";

    public static final String GROUPS = "groups";
}
