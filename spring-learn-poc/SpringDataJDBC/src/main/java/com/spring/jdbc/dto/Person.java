package com.spring.jdbc.dto;

import java.time.LocalDate;
import java.time.Period;

import org.springframework.data.annotation.AccessType;
import org.springframework.data.annotation.AccessType.Type;
import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Person {

	private final @Id Long id;
	private final String firstname, lastname;
	private final LocalDate birthday;
	private final int age;

	private String comment;
	private @AccessType(Type.PROPERTY) String remarks;

	static Person of(String firstname, String lastname, LocalDate birthday) {

		return new Person(null, firstname, lastname, birthday, Period.between(birthday, LocalDate.now()).getYears());
	}

	Person(Long id, String firstname, String lastname, LocalDate birthday, int age) {

		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.birthday = birthday;
		this.age = age;
	}

	public Person withId(Long id) {
		return new Person(id, this.firstname, this.lastname, this.birthday, this.age);
	}

	void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
