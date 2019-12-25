package com.lhm.qubaManage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**  
 * @package: com.lhm.qubaManage.controller
 * @author: liu huangming
 * @date: 2019年12月19日 下午5:48:19 
 */
@Controller
public class UserController{
	
    @RequestMapping(value = BaseControllerUrl.LOGIN_HTM)
    public String login() {
    	return "login";
    }
    
}
