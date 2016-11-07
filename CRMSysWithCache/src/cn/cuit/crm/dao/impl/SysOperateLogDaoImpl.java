package cn.cuit.crm.dao.impl;

import org.springframework.stereotype.Repository;

import cn.cuit.crm.dao.ISysOperateLogDao;
import cn.cuit.crm.domain.SysOperateLog;

/**
 * 
 * <P>Desc: </P>
 * @author CUITLLB
 * @version 1.0
 */

/* 说明：  */
@Repository(ISysOperateLogDao.SERVICE_NAME)
public class SysOperateLogDaoImpl extends CommonDaoImpl<SysOperateLog> implements ISysOperateLogDao {

}
