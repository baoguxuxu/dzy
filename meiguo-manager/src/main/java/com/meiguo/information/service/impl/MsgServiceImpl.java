package com.meiguo.information.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meiguo.information.dao.MsgDao;
import com.meiguo.information.dao.MsgUserDao;
import com.meiguo.information.domain.MsgDO;
import com.meiguo.information.domain.MsgUserDO;
import com.meiguo.information.service.MsgService;



@Service
public class MsgServiceImpl implements MsgService {
	
	@Autowired
	private MsgUserDao MsgUserDao;
	
	
	@Autowired
	private MsgDao msgDao;
	
	@Override
	public MsgDO get(Integer id){
		return msgDao.get(id);
	}
	
	@Override
	public List<MsgDO> list(Map<String, Object> map){
		return msgDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return msgDao.count(map);
	}
	
	@Override
	public int save(MsgDO msg){
//		int id = msgDao.msgId();
		int id=55;
		msg.setId(id);
		if(StringUtils.isNotBlank(msg.getForIds())){
			String[] ids = msg.getForIds().split(",");
			for (int i = 0; i < ids.length; i++) {
				MsgUserDO msgUser = new MsgUserDO(Long.parseLong(ids[i]),(long)id);
				MsgUserDao.save(msgUser);
			}
		}
		return msgDao.save(msg);
	}
	
	@Override
	public int update(MsgDO msg){
		return msgDao.update(msg);
	}
	
	@Override
	public int remove(Integer id){
		return msgDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return msgDao.batchRemove(ids);
	}
	
	@Override
	public List<Map<String, Object>> getUserPlotList(){
		return msgDao.getUserPlotList();
	}
}
