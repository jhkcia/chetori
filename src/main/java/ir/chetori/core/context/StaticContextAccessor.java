package ir.chetori.core.context;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class StaticContextAccessor {
	private static ApplicationContext ac;

	public static void initializeContext() {
		if (ac == null)
			ac = new FileSystemXmlApplicationContext("src/main/webapp/WEB-INF/application-context-spring.xml");
	}

	public static <T> T getBean(Class<T> clazz) {
		return ac.getBean(clazz);
	}

	public static void printBeans() {
		for(String a:ac.getBeanDefinitionNames())
			System.out.println(a);
	}
}