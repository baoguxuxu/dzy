package com.meiguo.goods.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meiguo.common.config.BootdoConfig;
import com.meiguo.goods.dao.OrderCommentImageDao;
import com.meiguo.goods.domain.OrderCommentImageDO;
import com.meiguo.goods.service.OrderCommentImageService;

import sun.misc.BASE64Decoder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;



@Service
public class OrderCommentImageServiceImpl implements OrderCommentImageService {
	@Autowired
	private OrderCommentImageDao orderCommentImageDao;
	@Autowired
	private BootdoConfig bootdoConfig;
	@Override
	public OrderCommentImageDO get(Long id){
		return orderCommentImageDao.get(id);
	}
	
	@Override
	public List<OrderCommentImageDO> list(Map<String, Object> map){
		return orderCommentImageDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return orderCommentImageDao.count(map);
	}
	
	@Override
	public int save(OrderCommentImageDO orderCommentImage){
		return orderCommentImageDao.save(orderCommentImage);
	}
	
	@Override
	public int update(OrderCommentImageDO orderCommentImage){
		return orderCommentImageDao.update(orderCommentImage);
	}
	
	@Override
	public int remove(Long id){
		return orderCommentImageDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return orderCommentImageDao.batchRemove(ids);
	}
	
	@Override
	public String uploadImg(String namespace, String imgStr, String fileType){
		String imgFileTempPath = "";
		try {
			String base64String = imgStr.substring(imgStr.indexOf(",")+1);
			// Base64解码
			BASE64Decoder decoder = new BASE64Decoder();
			byte[] b = decoder.decodeBuffer(base64String);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {// 调整异常数据
					b[i] += 256;
				}
			}
			String baseDir = bootdoConfig.getUploadPath()+"pingjia/";
			File file = new File(baseDir);
			if(!file.isDirectory()){
				file.mkdirs();
			}
			
			File file2 = new File(baseDir+ "/" + namespace + "/");
			if(!file2.isDirectory()){
				file2.mkdirs();
			}
			// 生成jpeg图片
			imgFileTempPath = "/" + namespace +"/"+ System.currentTimeMillis() + "." + fileType;//新生成的图片
			OutputStream out = new FileOutputStream(baseDir + imgFileTempPath);
			out.write(b);
			out.flush();
			out.close();
//			return staticsHost+imgFileTempPath;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return imgFileTempPath;	
	}
	
}
