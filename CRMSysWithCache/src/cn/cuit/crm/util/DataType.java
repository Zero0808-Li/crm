package cn.cuit.crm.util;

import org.apache.commons.lang.StringUtils;

/**
 * <P>Desc: </P>
 * @author CUITLLB
 * @version 1.0
 */

/* 说明：数据类型转化工具类 */
public class DataType {

	/**
	 * @该方法完成的功能是将字符串数组转化为整形数组
	 * @param sids
	 * @return
	 */
	public static Integer[] converterStringArray2IntegerArray(String[] sids) {
		if (sids != null && sids.length > 0) {
			Integer[] ids = new Integer[sids.length];
			for (int i = 0; i < sids.length; i++) {
				if (StringUtils.isNotBlank(sids[i])) {
					ids[i] = Integer.parseInt(sids[i]);
				}
			}
			return ids;
		}
		return null;
	}

	/**
	 * 利用给定的流水位数生成第一个流水号
	 * 	例如：流水位是 3 对应的第一个流水号是001
	 * 	例如：流水位是 4 对应的第一个流水号是0001
	 * @param glideBit（流水位数）
	 * @return
	 */
	public static String genFirstGlideNumber(Integer glideBit) {
		if (glideBit == null || glideBit < 3) {
			//默认流水位数最少是3位数
			glideBit = 3;
		}
		
		String glideNumber = "";
		for (int i = 0; i < glideBit - 1; i++) {
			glideNumber = glideNumber + "0";
		}
		glideNumber = glideNumber + "1";
		
		return glideNumber;
	}

	/* 测试第一个流水号生成 和给定流水号生成下一个流水号*/
	public static void main(String[] args) {
		System.out.println(genFirstGlideNumber(3));
		System.out.println(genNextGlideNumber("005"));
	}

	/**
	 * 根据当前的流水号生成下一个流水号
	 * 	例如：当前的流水号是001，下一个就为002
	 * 	例如：当前的流水号是0001，下一个就为0002
	 * 	例如：当前的流水号是005，下一个就为006
	 * @param currentGlideBitNumber
	 * @return
	 * 算法：（此算法目前还有漏洞，就是处理全部为9的情况）因为我们对流水位的位数有控制，貌似不会有问题
	 * 例如：
	 * 	1：当前为String---0005   
	 *  2：在字符串前面+1，再转为整型---得到整型的 10005
	 *  3：在整型10005的基础上计算下个，整型的 10005 + 1 ---得到整型的 10006
	 * 	4：将整型的 10006变为字符串     ---得到String 10006
	 * 	5：将String 10006之前加的1截取去  ---得到String 0006
	 * 	
	 */
	public static String genNextGlideNumber(String currentGlideBitNumber) {
		if (StringUtils.isBlank(currentGlideBitNumber)) {
			throw new RuntimeException("不能计算下一个流水号");
		}
		
		currentGlideBitNumber = "1" + currentGlideBitNumber;
		Integer iCurrentGlideBitNumber = Integer.parseInt(currentGlideBitNumber);
		iCurrentGlideBitNumber++;
		currentGlideBitNumber = iCurrentGlideBitNumber + "";
		currentGlideBitNumber = currentGlideBitNumber.substring(1);
		return currentGlideBitNumber;
	}
	
}
