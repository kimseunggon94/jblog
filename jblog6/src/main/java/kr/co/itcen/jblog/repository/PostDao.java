package kr.co.itcen.jblog.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.itcen.jblog.vo.PostVo;

@Repository
public class PostDao {
	@Autowired
	private SqlSession sqlSession;
	
	public List<PostVo> getList(Long no) {
		List<PostVo> list = sqlSession.selectList("post.getList",no);
		return list;
	}

	public PostVo get(Long postNo, Long categoryno) {
		Map<String, Long>map = new HashMap<String, Long>();
		map.put("no",postNo);
		map.put("category_no",categoryno);
		PostVo vo = sqlSession.selectOne("post.getpost",map);
		return vo;
	}

	public boolean insertpost(PostVo vo) {
		int count =sqlSession.insert("post.insertpost",vo);
		return count==1;
	}

	public boolean delpost(Long no) {
		int count = sqlSession.delete("post.delpost",no);
		return count==1;
	}

	
}
