package com.atschx.adnetwork.domain.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "sober_role")
public class Role implements Serializable {

	private static final long serialVersionUID = 7572081951087586464L;

	private Long id;
	private String code;
	private Set<User> users = new HashSet<User>(0);

	public Role() {
		this(null);
	}

	public Role(String code) {
		super();
		this.code = code;
	}

	@Id
	@GeneratedValue(generator = "sober_id_gen")
	@GenericGenerator(name = "sober_id_gen", strategy = "enhanced-table", parameters = {
			@Parameter(name = "table_name", value = "id_generator"),
			@Parameter(name = "value_column_name", value = "next"),
			@Parameter(name = "segment_column_name", value = "segment_name"),
			@Parameter(name = "segment_value", value = "sober_role_seq"),
			@Parameter(name = "initial_value", value = "1000"), @Parameter(name = "increment_size", value = "10"),
			@Parameter(name = "optimizer", value = "pooled-lo") })
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "code", nullable = false)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

//	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	@ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, fetch = FetchType.LAZY)
	@JoinTable(name = "sober_user_role", joinColumns = {
			@JoinColumn(name = "role_id", nullable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "user_id", nullable = false) })
	@JsonIgnore
	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

}
