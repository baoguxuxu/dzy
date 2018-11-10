package com.meiguo.goods.service;


import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.meiguo.goods.domain.OrderCommentImageDO;

import springfox.documentation.service.ResponseMessage;

/**
 * 评价图片表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-11-10 18:55:12
 */
public interface OrderCommentImageService {
	
	OrderCommentImageDO get(Long id);
	
	List<OrderCommentImageDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(OrderCommentImageDO orderCommentImage);
	
	int update(OrderCommentImageDO orderCommentImage);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
	//上传图片
	
	public String uploadImg(String mode, String namespace,
			String fileType);
	
	
	/*ResponseMessage deleteFile(@PathVariable("mode")String mode,
			@RequestParam("file") String file);

*/
}
