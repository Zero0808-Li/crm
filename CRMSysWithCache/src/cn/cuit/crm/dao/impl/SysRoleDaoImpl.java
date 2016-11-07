package cn.cuit.crm.dao.impl;


import org.springframework.stereotype.Repository;

import cn.cuit.crm.dao.ISysRoleDao;
import cn.cuit.crm.domain.SysRole;

/**
 * <P>Desc: </P>
 * @author CUITLLB
 * @version 1.0
 */

/* 说明：  使用这个注解，将该对象注入业务层的名称*/
@Repository(ISysRoleDao.SERVICE_NAME)
public class SysRoleDaoImpl extends CommonDaoImpl<SysRole>implements ISysRoleDao {

}
