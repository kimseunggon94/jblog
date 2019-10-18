package kr.co.itcen.jblog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kr.co.itcen.jblog.dto.JSONResult;
import kr.co.itcen.jblog.service.CategoryService;
import kr.co.itcen.jblog.service.PostService;
import kr.co.itcen.jblog.vo.CategoryVo;


@RestController("categoryApiController")
@RequestMapping("/api/{userId:(?!assets).*}")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private PostService postService;
	
	//category 추가하기 
	@PostMapping("/addcategory")
	 public JSONResult addCategory(@PathVariable String userId,CategoryVo categoryvo ) {
		 categoryvo.setBlog_id(userId);

		 // database insert - select last_insert_id() <- no
		 categoryService.addcategory(categoryvo);
		 categoryvo.setCount(0L);
		 return JSONResult.success(categoryvo);
	 }
	//category 제거 하기 
	@RequestMapping(value = "/removecategory", method = RequestMethod.POST)
	@ResponseBody
	public JSONResult delcategory(CategoryVo vo) {
		postService.delpost(vo.getNo());
		Boolean exist=categoryService.delcategory(vo.getNo());
		return JSONResult.success(exist);
				
		
	}
}