package com.hcl.nxp.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Candidate {
	private int id;
	private String name;
	private Date date;
	private String gender;
	private List<Favourite> favouriteCollection;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public List<Favourite> getFavouriteCollection() {
		return favouriteCollection;
	}
	public void setFavouriteCollection(List<Favourite> favouriteCollection) {
		this.favouriteCollection = favouriteCollection;
	}
	@Override
	public String toString() {
		return "Candidate [id=" + id + ", name=" + name + ", date=" + date + ", gender=" + gender
				+ ", favouriteCollection=" + favouriteCollection + "]";
	}
	

	
}
