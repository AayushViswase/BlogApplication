package com.Blog.Model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userId;
	@NotEmpty
	@Size(min = 4, message = "Username must be min 4 characters")
	private String name;
	@Email(message = "Email address is not valid!")
	private String email;
	@NotEmpty
	@Size(min = 4, max = 10, message = "Password must be  min  of 3 char and max 10 char !!")
	private String password;
	@NotEmpty
	private String about;
	@JsonBackReference
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Post> post = new ArrayList<>();

}
