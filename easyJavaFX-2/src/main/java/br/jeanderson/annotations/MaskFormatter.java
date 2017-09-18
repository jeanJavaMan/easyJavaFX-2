package br.jeanderson.annotations;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import br.jeanderson.enums.MaskType;

@Retention(RUNTIME)
@Target(ElementType.FIELD)
public @interface MaskFormatter {
	public MaskType type();
	public boolean showMask() default true;
}
