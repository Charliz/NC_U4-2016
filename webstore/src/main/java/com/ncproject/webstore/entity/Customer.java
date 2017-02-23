package com.ncproject.webstore.entity;

public class Customer {
	private int id;
	private String login;
	private String password;
	private String email;
	private String name;
	private String address;
	private String payment;

	public Customer(String login, String password, String email, String name, String address, String payment) {
		this.login = login;
		this.password = password;
		this.email = email;
		this.name = name;
		this.address = address;
		this.payment = payment;
	}



	public int getId() {
		return id;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	@Override
	public String toString() {
		return "Customer{" +
				"id=" + id +
				", login='" + login + '\'' +
				", password='" + password + '\'' +
				", email='" + email + '\'' +
				", name='" + name + '\'' +
				", address='" + address + '\'' +
				", payment='" + payment + '\'' +
				'}';
	}
}
