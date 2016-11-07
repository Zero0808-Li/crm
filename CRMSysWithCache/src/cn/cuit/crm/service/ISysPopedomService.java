package cn.cuit.crm.service;

import java.util.List;

import cn.cuit.crm.domain.SysPopedom;

/**
 * 
 * <P>Desc: </P>
 * @author CUITLLB
 * @version 1.0
 */

/* 说明：  */
public interface ISysPopedomService {
	
	public final static String SERVICE_NAME = "cn.cuit.crm.service.impl.SysPopedomServiceImpl";

	/**
	 * 获取系统操作功能的所有数据
	 * @return
	 */
	public abstract List<SysPopedom> findAllSysPopedoms();

}
