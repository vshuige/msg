package org.fb.msg;

import java.util.Map;

import javax.annotation.Resource;

import org.fb.msg.service.IMsgService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * @author wh
 *
 */
@Controller
public class MsgController {
	
//	private static Logger logger = LoggerFactory.getLogger(MsgController.class);

	@Resource
	private IMsgService msgService;

	@RequestMapping("/sendMsg")
	@ResponseBody
	public Map<String, Object> sendMsg(String mobile, String type,
			String proNo){
		return msgService.sendMsg(mobile, type, proNo);
	}
	
	@RequestMapping("/validateMsg")
	@ResponseBody
	public boolean validateMsg(String mobile, String type, String proNo,
			String verCode) {
		return msgService.validateMsg(mobile, type, proNo, verCode);
	}
}



