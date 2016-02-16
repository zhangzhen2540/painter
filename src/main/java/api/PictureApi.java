package api;

import domain.Picture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import service.PictureService;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Usage: API for Picture
 * Description: PictureApi
 * Created by Administrator on 2016/2/14.
 */
@RestController
@RequestMapping(value="picture")
public class PictureApi {

	@Autowired
	private PictureService pictureService;

	//保存图片
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Map<String,Object> save(@RequestParam(value = "userId", required = true)Long userId,
	                               @RequestParam(value = "name") String name,
	                               @RequestParam("file") MultipartFile file,
	                               HttpServletRequest request){
		Map<String,Object> data = new HashMap<String, Object>();
		String path = "";
		// 判断文件是否为空
		if (!file.isEmpty()) {
			try {
				// 文件保存路径
				String filePath = request.getSession().getServletContext().getRealPath("/") + "upload/"
						+ file.getOriginalFilename();
				// 转存文件
				file.transferTo(new File(filePath));
				path = filePath;
			} catch (Exception e) {
				e.printStackTrace();
				data.put("pic",null);
				data.put("status",1);
				data.put("msg",e);
				return data;
			}
		}
		//init picture
		Picture picture = new Picture();
		picture.setAuthorId(userId);
		picture.setCreateTime(new Date());
		picture.setUpdateTime(new Date());
		//未填写名称则取创建时间为其名称 三元表达式 如果满足条件则执行?号后一条语句 否则执行：后语句
		picture.setName(name==null?picture.getCreateTime().toString():name);
		picture.setPath(path);
		pictureService.insert(picture);
		data.put("pic",picture);
		data.put("status",0);
		data.put("msg","success");
		return data;
	}

	//查询图片列表
	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Map<String,Object> list(@RequestParam(value = "name", required = false)String name,
	                               @RequestParam(value = "page", required = false)Integer pageId,
	                               @RequestParam(value = "size", required = false)Integer size){
		Map<String,Object> data = new HashMap<String, Object>();
		if(pageId==null||pageId<0){
			pageId=0;
		}
		if(size==null){
			size=20;
		}
//		if(name.trim().equals("")){
//			data.put("list",null);
//			data.put("status",1);
//			data.put("msg","name not null");
//			return data;
//		}
		List<Picture> result = pictureService.findByName(name,pageId,size);
		data.put("list",result);
		data.put("status",0);
		data.put("msg","success");
		return data;
	}
}
