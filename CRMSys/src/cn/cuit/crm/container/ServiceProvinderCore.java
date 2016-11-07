package cn.cuit.crm.container;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * <P>Desc: </P>
 * @author CUITLLB
 * @version 1.0
 */

/* 说明：服提供核心类, 该类的注意目的是加载beans.xml文件，创建Spring容器 ，这样就不要在web.xml文件中加载了，
 * 所以在服务器启动时就不会创建Spring容器，而是在第一次主动使用下面的类的时候才会加载beans.xml,所以启动是比较快的，
 * 而第一次使用时会比较慢，因为下面对Spring容器的创建
 */
public class ServiceProvinderCore {
	/* 注意这里的修饰符 */
	protected ApplicationContext ctx;

	/* 根据配置文件进行封装，根据配置文件创建容器 */
	public void load(String filename) {
		ctx = new ClassPathXmlApplicationContext(filename);
	}
}
