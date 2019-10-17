package kr.co.itcen.jblog.security;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD}) //어디다가 붙일 수 있는지 지정
@Retention(RetentionPolicy.RUNTIME) 
public @interface Auth {
	
}