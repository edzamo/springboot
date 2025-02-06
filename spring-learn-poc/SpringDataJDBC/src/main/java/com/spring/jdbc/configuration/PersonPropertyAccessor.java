package com.spring.jdbc.configuration;

import java.lang.invoke.MethodHandle;

import org.springframework.data.mapping.PersistentProperty;
import org.springframework.data.mapping.PersistentPropertyAccessor;

import com.spring.jdbc.dto.Person;

public class PersonPropertyAccessor implements PersistentPropertyAccessor {

	private static final MethodHandle firstname = null;

	private Person person;

	@Override
	public void setProperty(PersistentProperty property, Object value) {

		String name = property.getName();
		

		if ("firstname".equals(name)) {
			try {
				firstname.invoke(person, (String) value);
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if ("id".equals(name)) {
			this.person = person.withId((Long) value);
		} else if ("lastname".equals(name)) {
		//	this.person.setLastname((String) value);
			
		}

	}

	@Override
	public Object getProperty(PersistentProperty property) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getBean() {
		// TODO Auto-generated method stub
		return null;
	}

}
