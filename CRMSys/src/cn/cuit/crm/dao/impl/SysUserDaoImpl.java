package cn.cuit.crm.dao.impl;


import org.springframework.stereotype.Repository;

import cn.cuit.crm.dao.ISysUserDao;
import cn.cuit.crm.domain.SysUser;

/**
 * 
 * <P>Desc: </P>
 * @author CUITLLB
 * @version 1.0
 */

/* 说明：  */
@Repository(ISysUserDao.SERVICE_NAME)
public class SysUserDaoImpl extends CommonDaoImpl<SysUser> implements ISysUserDao {


}
