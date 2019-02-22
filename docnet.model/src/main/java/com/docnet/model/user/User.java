package com.docnet.model.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="user")
@NamedQueries({
    @NamedQuery(name = "User.findById", query = "SELECT u FROM User u WHERE u.id = :id and u.status = 1 order by u.lastName"),
    @NamedQuery(name = "User.findByMobAndOtp", query = "SELECT u FROM User u WHERE u.phone = :phone and u.otp = :otp"),
    @NamedQuery(name = "User.findByFirstName", query = "SELECT u FROM User u WHERE u.firstName = :firstName and u.status = 1 order by u.lastName"),
    @NamedQuery(name = "User.findByLastName", query = "SELECT u FROM User u WHERE u.lastName = :lastName and u.status = 1"),
    @NamedQuery(name = "User.findByLogin", query = "SELECT u FROM User u WHERE u.login = :login and u.status = 1 order by u.lastName"),
    @NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password and u.status = 1 order by u.lastName"),
    @NamedQuery(name = "User.findByAddress", query = "SELECT u FROM User u WHERE u.address = :address and u.status = 1 order by u.lastName"),
    @NamedQuery(name = "User.findOtpForMobileNo", query = "SELECT u.otp FROM User u WHERE u.login = :login"),
    @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email"),
    @NamedQuery(name = "User.findPassword", query = "SELECT u.password FROM User u WHERE u.login = :login and u.status = 1 order by u.lastName"),
    @NamedQuery(name = "User.findByPhone", query = "SELECT u FROM User u WHERE u.phone = :phone order by u.lastName")})
public class User {

	@Id
    @GeneratedValue
    @Column(name="id", unique=true, nullable=false)
	private long id;
	@Column(name="first_name")
	private String firstName;
	@Column(name="last_name")
	private String lastName;
	@Column(name="specialization")
	private String specialization;
	@Column(name="login")
	private String login;
	@Column(name="password")
	@JsonIgnore
	private String password;
	@Column(name="location")
	private String location;
	@Column(name="address")
	private String address;
	@Column(name="email")
	private String email;
	@Column(name="phone")
	private String phone;
	@Column(name="status")
	private int status;
	@Column(name="create_date")
	private String dateCreated;
	@Column(name="update_date")
	private String dateUpdated;
    @Column(name= "gcm_id")
    private String gcmId;
    @Column(name="otp")
    private int otp;
	public String getGcmId() {
		return gcmId;
	}
	public void setGcmId(String gcmId) {
		this.gcmId = gcmId;
	}
	public int getOtp() {
		return otp;
	}
	public void setOtp(int otp) {
		this.otp = otp;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	public String getSpecialization() {
		return specialization;
	}
	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	@JsonIgnore
	public String getPassword() {
		return password;
	}
	@JsonProperty
	public void setPassword(String password) {
		this.password = password;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}
	public String getDateUpdated() {
		return dateUpdated;
	}
	public void setDateUpdated(String dateUpdated) {
		this.dateUpdated = dateUpdated;
	}
}
