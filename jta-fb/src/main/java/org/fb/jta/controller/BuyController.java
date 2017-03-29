package org.fb.jta.controller;

import java.util.HashMap;
import java.util.Map;

import org.fb.jta.services.IBuyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @datetime 2017-3-8 下午10:20:12
 * @author wh
 **/
@Controller
public class BuyController {
	@Autowired
	private IBuyService buyService;

	@RequestMapping("/moredb")
	@ResponseBody
	public Map<String, Object> allController(){
		Map<String, Object> result = new HashMap<String, Object>();
		System.out.println("begin...");
		buyService.buy(100, 1, 100);
		System.out.println("end...");
		return result;
	}
	
}
