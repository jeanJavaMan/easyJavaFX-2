package br.jeanderson.annotations;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javafx.stage.StageStyle;

@Retention(RUNTIME)
@Target(TYPE)
public @interface DefineConfiguration {
	public String url_fxml() default "/br/jeanderson/view/DefaultView.fxml";
	public String title() default "Minha Janela";
	public String url_icon() default "/br/jeanderson/img/javafx-logo.png";
	public boolean fullscreen() default false;
	public boolean resizable() default true;
	public boolean maximized() default false;
	public StageStyle stageStyle() default StageStyle.DECORATED;
}
