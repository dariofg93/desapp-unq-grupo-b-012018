package architecture;

import static org.junit.Assert.assertNotNull;
import static org.reflections.ReflectionUtils.getAllMethods;
import static org.reflections.ReflectionUtils.withModifier;
import static org.reflections.ReflectionUtils.withPrefix;


import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Set;

import javax.ws.rs.core.Response;

import org.junit.Test;
import org.reflections.Reflections;

import com.google.common.base.Predicates;

import webService.vehiclecorcern.AbstractRest;
import webService.vehiclecorcern.UserRest;

public class ArchitectureTest {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void testAllClasesInPackagageRestServiceREturnTypeReponseClass() {
		UserRest user = new UserRest();
		Class clase = user.getClass();
		Package paquete = clase.getPackage();
		
		Reflections reflections = new Reflections(paquete.getName());
		
		Set<Class<? extends AbstractRest>> allClasses = reflections.getSubTypesOf(AbstractRest.class);
		System.out.println(allClasses);
		for (Class myClass : allClasses) {
			Set<Method> allMethods = getAllMethods(myClass, withModifier(Modifier.PUBLIC),
					Predicates.and(Predicates.not(withPrefix("get")), Predicates.not(withPrefix("set"))));

			System.out.println(allMethods);
			
			this.assertAllMethodsAreTransactional(allMethods);
		}
	}

	private void assertAllMethodsAreTransactional(Set<Method> allMethods) {
		for (Method method : allMethods) {
			System.out.println("________");
			System.out.println(method.getName());
			
			System.out.println(method.getReturnType());
			assertNotNull(method.getReturnType().equals(Response.class));
		}
	}

}
