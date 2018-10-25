package com.meiguo.common.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.meiguo.common.domain.LogDO;
import com.meiguo.common.domain.PageDO;
import com.meiguo.common.utils.Query;
@Service
public interface LogService {
	void save(LogDO logDO);
	PageDO<LogDO> queryList(Query query);
	int remove(Long id);
	int batchRemove(Long[] ids);
}
