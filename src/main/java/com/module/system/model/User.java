package com.module.system.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.module.novel.model.Character;
import org.springframework.security.core.userdetails.UserDetails;

import com.base.common.model.AbstractModel;

public class User extends AbstractModel implements UserDetails {

	/**
	 */
	private static final long serialVersionUID = 8342304011564627512L;
	
	private String loginName;
	private String password;
	private String email;
	private String mobile;
	private Collection<Role> roles;
	
	private com.module.novel.model.Character currentCharacter;
	private Character enemy;
	private List<Character> characters;

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public Character getCurrentCharacter() {
		return currentCharacter;
	}

	public void setCurrentCharacter(
			Character currentCharacter) {
		this.currentCharacter = currentCharacter;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public List<Character> getCharacters() {
		return characters;
	}

	public void setCharacters(List<Character> characters) {
		this.characters = characters;
	}

	public Character getEnemy() {
		return enemy;
	}

	public void setEnemy(Character enemy) {
		this.enemy = enemy;
	}

	@Override
	public Collection<Role> getAuthorities() {
		return this.roles;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.getName();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Serializable getObjectId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUniqueIdName() {
		return "user";
	}
}
