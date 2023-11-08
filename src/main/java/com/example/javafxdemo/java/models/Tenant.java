package com.example.javafxdemo.java.models;

public class Tenant{
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String email;

	public Tenant(String firstName, String lastName, String phoneNumber, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null || this.getClass() != obj.getClass())
            return false;
        Tenant tenant = (Tenant)obj;
        return this.email.equals(tenant.email)
		&& this.firstName.equals(tenant.firstName) && this.lastName.equals(tenant.lastName)
		&& this.phoneNumber.equals(tenant.phoneNumber);

    }
	@Override
	public String toString() {
		return "'" + firstName + '\'' +
				", '" + lastName + '\'' +
				", '" + phoneNumber + '\'' +
				", " + email + '\'';
	}
}
