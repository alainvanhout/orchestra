package orchestra.instrument;

import orchestra.instrument.port.UseFreePort;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@UseFreePort
public @interface Instrument {
}
