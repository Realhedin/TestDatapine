package com.datapine.domain;

import javax.persistence.*;

@Entity
@Table(name = "users")
@NamedQueries({
        @NamedQuery(name = "User.getByEmail", query = "SELECT u FROM User u WHERE u.email=?1"),
        @NamedQuery(name = "User.findAllOrderById", query = "SELECT u FROM User u ORDER BY u.id")
})
public class User {

	@Id
	@GeneratedValue
	private Long id;

    @Column(name = "email", nullable = false, unique = true)
	private String email;

    @Column(name = "password", nullable = false)
	private String password;

//    private String role;

	public User(final String email) {
		this.email = email;
	}

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

//    public String getRole() {
//        return role;
//    }
//
//    public void setRole(String role) {
//        this.role = role;
//    }
}
