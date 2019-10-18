package kr.co.itcen.jblog.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.itcen.jblog.service.BlogService;
import kr.co.itcen.jblog.service.CategoryService;
import kr.co.itcen.jblog.service.PostService;
import kr.co.itcen.jblog.service.UserService;
import kr.co.itcen.jblog.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private BlogService blogService;

	@Autowired 
	private CategoryService categoryService;
	
	
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String join(@ModelAttribute UserVo vo) {
		return "user/join";
	}
	
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(@ModelAttribute @Valid UserVo vo, BindingResult result, Model model) {
		//model("userVo", vo);	@ModelAttribute가 하는 행동
		
		if(result.hasErrors()) {
			model.addAllAttributes(result.getModel());			//Map으로 받는것도 가능
			return "user/join";
		}
		userService.join(vo);
		blogService.insert(vo.getId());
		categoryService.default_insert(vo.getId());
		
		return "redirect:/user/joinsuccess";
	}
	
	@RequestMapping(value="/joinsuccess", method=RequestMethod.GET)
	public String joinsuccess() {
		return "user/joinsuccess";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login(@ModelAttribute UserVo vo) {
		return "user/login";
	}
}
