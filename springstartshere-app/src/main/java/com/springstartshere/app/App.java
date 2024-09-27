package com.springstartshere.app;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.google.common.base.Supplier;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		var context = new AnnotationConfigApplicationContext(ProjectConfig.class);

//		Parrot x = new Parrot();
//		x.setName("Kiki");
//
//		Supplier<Parrot> parrotSupplier = () -> x;
//		context.registerBean("kiki", Parrot.class, parrotSupplier);
//
//		Parrot p = context.getBean(Parrot.class);
//		System.out.println(p.getName());
		
		//context.registerBean("parrot1", Parrot.class, parrotSupplier, bc -> bc.setPrimary(true));
		
		Person person = context.getBean(Person.class);
		
		Parrot parrot = context.getBean(Parrot.class);
		
		System.out.println("Person's name: " + person.getName());
		
		System.out.println("Parrot's name: " + parrot.getName());
		
		System.out.println("Person's parrot: " + person.getParrot());
	}
}
