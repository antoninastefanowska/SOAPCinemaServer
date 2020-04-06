package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Role")
@XmlType(name = "Role")
@XmlAccessorType(XmlAccessType.NONE)
public class Role {
	private String name;
	private String surname;
	private Person actor;
	
	public Role() { }
	
	public Role(String name, String surname, Person actor) {
		this.name = name;
		this.surname = surname;
		this.actor = actor;
	}

	@XmlElement(name = "Name")
	public String getName() {
		return name;
	}

	@XmlElement(name = "Surname")
	public String getSurname() {
		return surname;
	}

	@XmlElement(name = "Actor")
	public Person getActor() {
		return actor;
	}
}
