package cn.cuit.crm.dao;

import cn.cuit.crm.dao.ICommonDao;
import cn.cuit.crm.domain.City;

/**
 * 
 * <P>Desc: </P>
 * @author CUITLLB
 * @version 1.0
 */

/* 说明：  */
public interface ICityDao extends ICommonDao<City> {

	public final static String SERVICE_NAME = "cn.cuit.crm.dao.impl.CityDaoImpl";
}
