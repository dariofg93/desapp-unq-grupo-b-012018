package architecture;

import static org.junit.Assert.assertNotNull;

import static org.reflections.ReflectionUtils.*;


import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Set;

import javax.ws.rs.Path;
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
			Set<Method> allMethods = getAllMethods(myClass, withAnnotation(Path.class),
					Predicates.and(Predicates.not(withPrefix("get")), Predicates.not(withPrefix("set"))));

			System.out.println(allMethods);
			
			for (Method method : allMethods) {
				assertNotNull(method.getReturnType().equals(Response.class));
			}
		}
	}
}
