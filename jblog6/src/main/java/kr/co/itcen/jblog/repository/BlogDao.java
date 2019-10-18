package kr.co.itcen.jblog.repository;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import kr.co.itcen.jblog.exception.FileuploadException;
import kr.co.itcen.jblog.vo.BlogVo;

@Repository
public class BlogDao {
	@Autowired
	private SqlSession sqlSession;
	

	private static final String SAVE_PATH = "/uploads";
	private static final String URL_PREFIX ="/images";
	
	
	public boolean insert(String id) {
		Map<String, String > map = new HashMap<String, String>();
		map.put("id", id);
		map.put("title", id+"의 블로그입니다.");
		map.put("logo", "/assets/images/spring-logo.jpg");
		int count = sqlSession.insert("blog.insert", map);
		return count==1;
	}

	public  BlogVo get(String id) {
		BlogVo reasult = sqlSession.selectOne("blog.getId",id);
		return reasult;	
	}

	public boolean updateBlog(MultipartFile multipartFile, BlogVo vo) {
		String url = "";
		if(multipartFile == null) {
			return false;
		}
		
		String originalFilename = multipartFile.getOriginalFilename();
		String saveFileName = generateSaveFilename(originalFilename.substring(originalFilename.lastIndexOf('.')+1));
		long fileSize = multipartFile.getSize();
		
		try {
			byte[] fileData = multipartFile.getBytes();
			OutputStream os =new FileOutputStream(SAVE_PATH +"/"+ saveFileName);
			os.write(fileData);
			os.close();
			//파일 저장/upload
			url = URL_PREFIX+"/" + saveFileName;
		} catch (IOException e) {
			throw new FileuploadException();
		}
		vo.setLogo(url);
		
		int count = sqlSession.update("blog.update", vo);
		return count==1;	
		
	}
	
	private String generateSaveFilename(String extName) {
		String filename = "";
		
		Calendar calendar = Calendar.getInstance();
		filename += calendar.get(Calendar.YEAR);
		filename += calendar.get(Calendar.MONTH);
		filename += calendar.get(Calendar.DATE);
		filename += calendar.get(Calendar.HOUR);
		filename += calendar.get(Calendar.MINUTE);
		filename += calendar.get(Calendar.SECOND);
		filename += calendar.get(Calendar.MILLISECOND);
		filename += ("." + extName);
		
		return filename;
	}

}
