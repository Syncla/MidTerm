package com.cisc181.exceptions;
import com.cisc181.core.Person;

public class PersonException extends Exception{
	private Person person;
	public PersonException(Person person){
		this.person = person;
	}
	public Person getPerson(){
		return this.person;
	}
}
