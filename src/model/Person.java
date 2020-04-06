package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Person")
@XmlType(name = "Person")
@XmlAccessorType(XmlAccessType.NONE)
public class Person {
	private static Counter indexCounter = new Counter();
	
	private int id;
	private String name;
	private String surname;
	
	public Person() { }
	
	public Person(String name, String surname) {
		this.name = name;
		this.surname = surname;
	}

	@XmlElement(name = "Name")
	public String getName() {
		return name;
	}

	@XmlElement(name = "Surname")
	public String getSurname() {
		return surname;
	}

	@XmlElement(name = "ID")
	public int getId() {
		return id;
	}
	
	public int generateId() {
		int id = indexCounter.nextValue();
		this.id = id;
		return id;
	}
}
