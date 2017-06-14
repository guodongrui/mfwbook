package org.mfwbook.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.mfwbook.dao.UserRepository;
import org.mfwbook.model.User;
import org.mfwbook.recommend.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RecommendController {

	@Autowired
	HttpServletRequest httpServletRequest;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RecommendService recommendService;
	
	@RequestMapping("/recommend")
	public String bookRecommend(Model model){
		String username = httpServletRequest.getRemoteUser();
        User user = userRepository.findByName(username);
        model.addAttribute("recommendBooks", recommendService.booksRecommend(user));
        
		return "recommend";
	}
}
