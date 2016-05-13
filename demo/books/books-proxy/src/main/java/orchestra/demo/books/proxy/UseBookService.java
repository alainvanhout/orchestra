package orchestra.demo.books.proxy;

import ochestra.proxying.EnableProxies;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(UseBookConfiguration.class)
@EnableProxies
public @interface UseBookService {
}
