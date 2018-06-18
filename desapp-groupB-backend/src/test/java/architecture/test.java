package architecture;

import static org.junit.Assert.assertNotNull;
import static org.reflections.ReflectionUtils.getAllMethods;
import static org.reflections.ReflectionUtils.withModifier;
import static org.reflections.ReflectionUtils.withPrefix;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Set;

import org.junit.Test;
import org.reflections.Reflections;

import com.google.common.base.Predicates;

public class test {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void testAllClasesInPackageServicesAreTransactional() {
		System.out.println("hjadfjhjdhaljfh---ajdhkajsdh");	
		Reflections reflections = new Reflections("webService.vehiclecorcern");
		
		Set<Class<? extends Object>> allClasses = reflections.getSubTypesOf(Object.class);
		System.out.println("hjadfjhjdhaljfh.-----daksjdkajsdlkasjdlkasjdlaksjdlaks");
		System.out.println(allClasses);	
		for (Class myClass : allClasses) {
			Set<Method> allMethods = getAllMethods(myClass, withModifier(Modifier.PUBLIC),
					Predicates.and(Predicates.not(withPrefix("get")), Predicates.not(withPrefix("set"))));
			System.out.println("hjadfjhjdhaljfh");	
			System.out.println(allMethods);
			
			this.assertAllMethodsAreTransactional(allMethods);
		}
	}

	private void assertAllMethodsAreTransactional(Set<Method> allMethods) {
		for (Method method : allMethods) {
			assertNotNull(method.getAnnotation(org.springframework.transaction.annotation.Transactional.class));
		}
	}

}
