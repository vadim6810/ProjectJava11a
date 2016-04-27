package com.domain.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Client {
	@Id
	String email;
	String password;
	String name;
	String phone;
	String avatar;
	@OneToMany(mappedBy = "client")
	Set<TenderRequest> tenders = new HashSet<TenderRequest>();
	@ManyToMany
	Set<ServiceStation> scoredStations = new HashSet<ServiceStation>();

	public Client() {
		// TODO Auto-generated constructor stub
	}

	public Client(String email, String password, String name, String phone) {
		this.email = email;
		this.password = password;
		this.name = name;
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Set<TenderRequest> getTenders() {
		return tenders;
	}

	public void setTenders(Set<TenderRequest> tenders) {
		this.tenders = tenders;
	}

	public Set<ServiceStation> getScoredStations() {
		return scoredStations;
	}

	public void setScoredStations(Set<ServiceStation> scoredStations) {
		this.scoredStations = scoredStations;
	}

	public boolean addScoredStation(ServiceStation station) {
		return scoredStations.add(station);
	}

	public boolean addTender(TenderRequest tender) {
		return tenders.add(tender);
	}

	public boolean removeTender(TenderRequest tender) {
		return tenders.remove(tender);
	}

	@Override
	public String toString() {
		return "Client [email=" + email + ", name=" + name + ", phone=" + phone + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
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
		Client other = (Client) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}

}
