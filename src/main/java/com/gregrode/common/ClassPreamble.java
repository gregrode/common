package com.gregrode.common;

import java.lang.annotation.Documented;

@Documented
public @interface ClassPreamble {
	String author() default "Gregroy Dennis";

	String lastModified() default "N/A";

	String lastModifiedBy() default "N/A";

	String copyright() default "&copy; Gregroy Dennis 2013";
}