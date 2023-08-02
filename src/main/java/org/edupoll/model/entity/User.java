package org.edupoll.model.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	@Id
	String id;

	String pass;
	String nick;
	Date joinDate;
	String authority;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userDetailIdx") // User Entity 의 필드
	UserDetail userDetail; // 이 컬럼을 찾는 객체는 UserDetail (id를 기준으로 찾음)

	@OneToMany(mappedBy = "manager", fetch = FetchType.LAZY)
	List<Moim> moims;

	public User() {
		super();
	}

	public User(String id, String pass, String nick, String authority) {
		super();
		this.id = id;
		this.pass = pass;
		this.nick = nick;
		this.authority = authority;
		this.joinDate = new Date();
	}

	public List<Moim> getMoims() {
		return moims;
	}
	
	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public void setMoims(List<Moim> moims) {
		this.moims = moims;
	}

	// setter / getter 만 추가
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public UserDetail getUserDetail() {
		return userDetail;
	}

	public void setUserDetail(UserDetail userDetail) {
		this.userDetail = userDetail;
	}

	// 객체 insert 전 할 작업
	@PrePersist
	public void doPrePersist() {
		System.out.println("doPrePersist..");
		joinDate = new Date();
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", pass=" + pass + ", nick=" + nick + ", joinDate=" + joinDate + ", authority=" + authority
				+ ", userDetail=" + userDetail + ", moims=" + moims + "]";
	}
}

/*
 * 
 * id varchar2(90) primary key, pass varchar2(90) not null, nick varchar2(90)
 * not null, join_date date default sysdate, user_detail_id varchar2(900)
 */
