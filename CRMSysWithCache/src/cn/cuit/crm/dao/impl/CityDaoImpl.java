package cn.cuit.crm.dao.impl;

import org.springframework.stereotype.Repository;

import cn.cuit.crm.dao.ICityDao;
import cn.cuit.crm.domain.City;

/**
 * 
 * <P>Desc: </P>
 * @author CUITLLB
 * @version 1.0
 */

/* 说明：  */
@Repository(ICityDao.SERVICE_NAME)
public class CityDaoImpl extends CommonDaoImpl<City> implements ICityDao {

}
