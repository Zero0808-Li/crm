package cn.cuit.crm.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.cuit.crm.dao.ISysPopedomDao;
import cn.cuit.crm.domain.SysPopedom;
import cn.cuit.crm.service.ISysPopedomService;

/**
 * 
 * <P>Desc: </P>
 * @author CUITLLB
 * @version 1.0
 */

/* 说明： */
@Transactional(readOnly = true)
@Service(value = ISysPopedomService.SERVICE_NAME)
public class SysPopedomServiceImpl implements ISysPopedomService {

	@Resource(name = ISysPopedomDao.SERVICE_NAME)
	private ISysPopedomDao sysPopedomDao;

	@Override
	public List<SysPopedom> findAllSysPopedoms() {
		/* 排序 */
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("o.sort", "asc");
		
		return sysPopedomDao.findObjectsByConditionWithNoPage(orderby);
	}

}
