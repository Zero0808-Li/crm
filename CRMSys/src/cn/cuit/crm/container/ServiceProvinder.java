package cn.cuit.crm.container;

import org.apache.commons.lang.StringUtils;

/**
 * <P>
 * Desc: 该类的主要作用是加载Spring的配置文件，创建Spring容器，和获取service bean
 * </P>
 * 
 * @author CUITLLB
 * @version 1.0
 */

/* 说明： 服务提供核类，通过静态方法来获取服务，而不直接new */
public class ServiceProvinder {
	private static ServiceProvinderCore sc;

	/* 类加载的时候就初始化Spring容器 */
	static {
		sc = new ServiceProvinderCore();
		sc.load("beans.xml");
	}

	/* 获取一个服务 ，相当于对getBean方法的一个封装，使用起来是比较方便的 */
	public static Object getService(String beanName) {
		if (StringUtils.isBlank(beanName)) {
			/* 请求的服务有问题，我们可以通过下面的异常看到效果，是比较清晰的的 */
			throw new RuntimeException("您要访问的服务名称不能为空");
		}

		Object bean = null;

		/* 如果Spring中包含beanName的bean,则获取该bean的实例对象 */
		if (sc.ctx.containsBean(beanName)) {
			bean = sc.ctx.getBean(beanName);
		}

		/* 如果获取出来的bean实例对象为空 */
		if (bean == null) {
			throw new RuntimeException("您要访问的服务名称[" + beanName + "]不存在");
		}

		return bean;
	}
}
