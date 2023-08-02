package org.edupoll.model.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "follows")
public class Follow {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	Integer Id;
	
	@ManyToOne
	@JoinColumn(name = "ownerId")
	User owner;
	
	@ManyToOne
	@JoinColumn(name = "targetId")
	User target;
	
	Date created;
	
	@Override
	public String toString() {
		return "Follow [Id=" + Id + ", owner=" + owner + ", target=" + target + ", created=" + created + "]";
	}

	public Follow() {
		super();
	}

	public Follow(User owner, User target) {
		super();
		this.owner = owner;
		this.target = target;
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public User getTarget() {
		return target;
	}

	public void setTarget(User target) {
		this.target = target;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}
	
}
