package com.lhm.qubaManage.util;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.lhm.qubaManage.bean.Result;
import com.lhm.qubaManage.bean.ResultCodeEnum;

/**  
 * 发送邮件工具类
 * @package: com.lhm.qubaManage.util
 * @author: liu huangming
 * @date: 2019年12月26日 下午7:12:54 
 */
@Service
public class SendEmailUtil {

	@Autowired
    JavaMailSender mailSender;
	
	private Logger logger = LoggerFactory.getLogger(SendEmailUtil.class);
	private static final String EMAIL = "13687091847@163.com";
	private static final String TOPIC = "去吧管理平台-验证码";
	/**
	 * 发送邮件
	 * @package: com.lhm.qubaManage.util
	 * @param from 发送方
	 * @param to 接受方
	 * @param content 内容
	 * @param topic 主题
	 * @author: liu huangming
	 * @date: 2019年12月26日 下午7:27:05
	 */
	public Boolean SendEmail(String from,String to,String content,String topic) {
		try {
			final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
            final MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
            message.setFrom(from);
            message.setTo(to);
            message.setSubject(topic);
            // true是指,这是html文件
            message.setText(setHtml(content),true);
			mailSender.send(mimeMessage);
			return true;
		} catch (Exception e) {
			logger.error("发送邮件失败:{}",e);
			return false;
		}
	}
	/**
	 * 发送验证码
	 * @package: com.lhm.qubaManage.util
	 * @param to
	 * @param code
	 * @author: liu huangming
	 * @date: 2019年12月26日 下午7:29:58
	 */
	@SuppressWarnings("rawtypes")
	public Result sendCodeEmail(String to,String code) {
		if (this.SendEmail(EMAIL,to, code,TOPIC)) {
			return new Result<>(true);
		}else {
			return new Result<>(false,ResultCodeEnum.SEND_CODE_FAIL);
		}
	}
	public String setHtml(String content) {
		String html = "<div style=\"padding: 0px 0px 0px 20px;box-sizing: border-box;color: #333333;font-family: \"microsoft yahei\";font-size: 14px\">" +
	               "<div style=\"margin-top: 30px;\">您的验证码为：<em style=\"font-style: normal;font-weight: 600;\">"+content+"</em></div>" +
	               "<div style=\"margin-top: 35px;\">5分钟内输入有效,请尽快输入,谢谢！</div>" +
	               "</div>";
		return html;
	}
}
