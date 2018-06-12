package com.base.utilslibrary.bean;

import java.util.List;

/**
 * @author Admin
 * @time 2017/4/7 9:44
 * @des ${TODO}
 */

public class LoginResult {
    public BasicResult result;//基础结果
    public String vc_code;//验证码
    public String ub_id;//用户的ID
    public String ub_phone;//用户的电话
    public UserDetail user_detail;//用户信息
    public List<String> file_ids;//图片信息
}
