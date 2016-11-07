package cn.cuit.crm.service;

import java.util.List;

import cn.cuit.crm.domain.SysMenu;

/**
 * <P>Desc: </P>
 * @author CUITLLB
 * @version 1.0
 */

/* 说明：  */
public interface ISysMenuService {
	
	public final static String SERVICE_NAME = "cn.cuit.crm.service.impl.SysMenuServiceImpl";

	/**
	 * @return
	 */
	public abstract List<SysMenu> findAllSysMenus();

	/**
	 * @return
	 */
	public abstract List<SysMenu> findAllSysMenusCache();

}
