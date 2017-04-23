package org.javacream.training.java.experts.jpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CATS")
public class Cat {

	private String name;
	
	private double weight;
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public String getName() {
		return name;
	}
	public Cat(String name, double weight) {
		super();
		this.name = name;
		this.weight = weight;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cat other = (Cat) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Cat [name=" + name + ", weight=" + weight + ", catId=" + catId
				+ "]";
	}
	
	public void annoy(){
		
	}
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private long catId;
	
	@SuppressWarnings("unused")
	private Cat(){}
}
