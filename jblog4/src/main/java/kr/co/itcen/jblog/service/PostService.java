package kr.co.itcen.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.itcen.jblog.repository.PostDao;
import kr.co.itcen.jblog.vo.PostVo;

@Service
public class PostService {
	@Autowired
	private PostDao postDao;
	
	
	public List<PostVo> getList(Long categoryno) {
		return postDao.getList(categoryno);
	}

	public PostVo getPost(Long postno, Long categoryno) {
		return postDao.get(postno, categoryno);
	}

	public boolean insertpost(PostVo vo) {
		return postDao.insertpost(vo);
		
	}

	public boolean delpost(Long no) {
		return postDao.delpost(no);
	}

}
