package main.model;

public class CompanyDistributor {
	protected int id;
	protected String dist_name;
	protected String dist_city;
	protected String dist_pincode;
	protected String dist_username;
	protected String dist_password;
	
	

	public CompanyDistributor(int id, String dist_name, String dist_city, String dist_pincode, String dist_username,
			String dist_password) {
		super();
		this.id = id;
		this.dist_name = dist_name;
		this.dist_city = dist_city;
		this.dist_pincode = dist_pincode;
		this.dist_username = dist_username;
		this.dist_password = dist_password;
	}

	
	public CompanyDistributor(String dist_name, String dist_city, String dist_pincode, String dist_username,
			String dist_password) {
		super();
		this.dist_name = dist_name;
		this.dist_city = dist_city;
		this.dist_pincode = dist_pincode;
		this.dist_username = dist_username;
		this.dist_password = dist_password;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDist_name() {
		return dist_name;
	}

	public void setDist_name(String dist_name) {
		this.dist_name = dist_name;
	}

	public String getDist_city() {
		return dist_city;
	}

	public void setDist_city(String dist_city) {
		this.dist_city = dist_city;
	}

	public String getDist_pincode() {
		return dist_pincode;
	}

	public void setDist_pincode(String dist_pincode) {
		this.dist_pincode = dist_pincode;
	}

	public String getDist_username() {
		return dist_username;
	}

	public void setDist_username(String dist_username) {
		this.dist_username = dist_username;
	}

	public String getDist_password() {
		return dist_password;
	}

	public void setDist_password(String dist_password) {
		this.dist_password = dist_password;
	}	
}