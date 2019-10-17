package kr.co.itcen.jblog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.co.itcen.jblog.security.Auth;
import kr.co.itcen.jblog.service.BlogService;
import kr.co.itcen.jblog.service.CategoryService;
import kr.co.itcen.jblog.service.PostService;
import kr.co.itcen.jblog.vo.BlogVo;
import kr.co.itcen.jblog.vo.CategoryVo;
import kr.co.itcen.jblog.vo.PostVo;

@Auth
@Controller
@RequestMapping("/{userId:(?!assets).*}/admin")
public class AdminController {
	
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private PostService postService;
	
	@RequestMapping({"","/basic"})
	public String blogmanagement(@PathVariable String userId ,Model model){
		BlogVo vo =	blogService.get(userId);
		
		model.addAttribute("blogvo", vo);
		return "blog/blog-admin-basic";
		
	}
	
	@RequestMapping(value="/basic",method = RequestMethod.POST )
	public String blogmanagement(
			@RequestParam(value="logo-file",required=false) MultipartFile multipartFile,
			@PathVariable String userId, 
			BlogVo vo){
		vo.setId(userId);
		blogService.update(multipartFile,vo);
		return "redirect:/"+userId;	
	}
	
	
	@RequestMapping("/category")
	public String categorymanagement(@PathVariable String userId, Model model) {
		List<CategoryVo> categorylist = categoryService.getcategorylist(userId);
		model.addAttribute("categorylist",categorylist);
		BlogVo vo =	blogService.get(userId);
		model.addAttribute("blogvo", vo);
		return "blog/blog-admin-category";
		
	}
	
	
	@RequestMapping("/write")
	public String  write(@PathVariable String userId, Model model) {
		List<CategoryVo> categoryvo = categoryService.getList(userId);
		model.addAttribute("categoryvo",categoryvo);
		BlogVo vo =	blogService.get(userId);
		
		model.addAttribute("blogvo", vo);
		return "blog/blog-admin-write";
	}
	
	
	@RequestMapping(value="/write",method = RequestMethod.POST)
	public String write(@PathVariable String userId , PostVo vo , @RequestParam("category") Long no) {
		
		vo.setCategory_no(no);
		postService.insertpost(vo);
		
		return "redirect:/"+userId;
	}
	
	
}
