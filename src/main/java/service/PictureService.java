package service;

import dao.PictureDao;
import domain.Picture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Usage: Service for Picture
 * Description: PictureService
 * Created by Administrator on 2016/2/14.
 */
@Service
public class PictureService {

	@Autowired
	private PictureDao pictureDao;

	//保存图片
	public Picture insert( Picture picture ){
		return pictureDao.insert(picture);
	}

	//分页 获取 图片列表
	public List<Picture> findByName(String name,Integer pageId,Integer offset){
		return pictureDao.findByName(name, pageId, offset);
	}


}
