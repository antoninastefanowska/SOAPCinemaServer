package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Seat")
@XmlType(name = "Seat")
@XmlAccessorType(XmlAccessType.NONE)
public class Seat {
	private int row;
	private int column;
	
	public Seat() { }
	
	public Seat(int row, int column) {
		this.row = row;
		this.column = column;
	}

	@XmlElement(name = "Row")
	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	@XmlElement(name = "Column")
	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}
}
