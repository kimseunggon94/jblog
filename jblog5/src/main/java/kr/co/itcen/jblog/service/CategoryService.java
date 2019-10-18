package kr.co.itcen.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.itcen.jblog.repository.CategoryDao;
import kr.co.itcen.jblog.vo.CategoryVo;

@Service
public class CategoryService {
	@Autowired
	private CategoryDao categoryDao;
	
	public List<CategoryVo> getList(String id) {
		return categoryDao.getList(id);
	}

	public void default_insert(String id) {
		categoryDao.default_insert(id);
	}

	public List<CategoryVo> getcategorylist(String id) {
		List<CategoryVo> list =categoryDao.getcategorylist(id);
		return list;
	}

	public int addcategory(CategoryVo categoryvo) {
		return categoryDao.addcategory(categoryvo);
	}
	public Boolean delcategory(Long categoryno) {
		return categoryDao.delcategory(categoryno);
		
	}

}
