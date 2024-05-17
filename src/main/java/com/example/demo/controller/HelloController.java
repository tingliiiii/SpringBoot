package com.example.demo.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hello")
public class HelloController {

	@GetMapping
	public String hello(Model model) {
		
		// 將要給 jsp 渲染的資料放在 model 中
		model.addAttribute("name", "tingli");
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss E");
		Date date = new Date();
		String time = simpleDateFormat.format(date);
		model.addAttribute("time", time);
		
		return "hello"; // 自動指向 /WEB-INF/view/hello.jsp
	}
}
