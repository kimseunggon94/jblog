package kr.co.itcen.jblog.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.co.itcen.jblog.repository.BlogDao;
import kr.co.itcen.jblog.vo.BlogVo;


@Service
public class BlogService {
	@Autowired
	private BlogDao blogDao;

	
	public void insert(String id) {
		blogDao.insert(id);
	}
	
	public BlogVo get(String userId) {
		return blogDao.get(userId);
	}

	public void update(MultipartFile multipartFile, BlogVo vo) {
		blogDao.updateBlog(multipartFile, vo);
	}
	
}
