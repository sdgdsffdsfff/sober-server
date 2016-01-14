package com.atschx.adnetwork.domain.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 广告类别
 */
@Entity
@Table(name = "offer_category")
public class OfferCategory implements Serializable {

	private static final long serialVersionUID = 4239986398023471934L;
	private Long id;
	private String name;
	private Integer level;
	private OfferCategory parent;
	private Set<OfferCategory> children = new HashSet<OfferCategory>(0);

	public OfferCategory() {
	}

	public OfferCategory(String name, Integer level) {
		super();
		this.name = name;
		this.level = level;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id")
	public OfferCategory getParent() {
		return parent;
	}

	public void setParent(OfferCategory parent) {
		this.parent = parent;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "parent")
	public Set<OfferCategory> getChildren() {
		return children;
	}

	public void setChildren(Set<OfferCategory> children) {
		this.children = children;
	}

}
