package model;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Film")
@XmlType(name = "Film")
@XmlAccessorType(XmlAccessType.NONE)
public class Film {
	private static Counter indexCounter = new Counter();
	
	private int id;
	private String title;
	private Person director;
	private List<Role> roles;
	private String description;
	
	@XmlTransient
	private File cover;
	
	public Film() {
		this.roles = new ArrayList<>();
	}
	
	public Film(String title, Person director, String description) {
		this.title = title;
		this.director = director;
		this.description = description;
		this.roles = new ArrayList<>();
	}

	@XmlElement(name = "Title")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@XmlElement(name = "Director")
	public Person getDirector() {
		return director;
	}

	public void setDirector(Person director) {
		this.director = director;
	}

	@XmlElement(name = "Description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public void addRole(Role role) {
		roles.add(role);
	}

	@XmlElement(name = "Roles")
	public List<Role> getRoles() {
		return roles;
	}
	
	public void setCover(String filename) {
		cover = new File("./resources/img/" + filename);
	}
	
	@XmlElement(name = "Cover")
	public Image getCover() {
		try {
			return ImageIO.read(cover);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
