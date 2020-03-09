package com.lhm.qubaManage.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**  
 * webSocket配置
 * @package: com.lhm.qubaManage.config
 * @author: liu huangming
 * @date: 2019年12月30日 上午9:23:17 
 */
public class WebSocketConfig {
	
	@Bean  
    public ServerEndpointExporter serverEndpointExporter() {  
        return new ServerEndpointExporter();  
    }  
}
