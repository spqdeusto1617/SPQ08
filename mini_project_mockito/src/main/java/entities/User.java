package entities;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;
import java.io.Serializable;


@PersistenceCapable(detachable = "true")
public class User implements Serializable {

	@PrimaryKey
	private String username;

	private String password;

	private int age;

	private int income;

	public User(String username, String password, int age, int income) {
		this.username = username;
		this.password = password;
		this.age = age;
		this.income = income;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getIncome() {
		return income;
	}

	public void setIncome(int income) {
		this.income = income;
	}
}
