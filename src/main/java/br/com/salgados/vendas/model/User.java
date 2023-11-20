package br.com.salgados.vendas.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "users")
@Entity
public class User implements UserDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	private String nome;
	private String email;
	private String cpjcnpj;
	private String login;
	private String password;
	private UserRole role;

	public User() {
	}

	public User(String nome, String email, String cpfcnpj, String login, String password, UserRole role) {
		this.nome = nome;
		this.email = email;
		this.cpjcnpj = cpfcnpj;
		this.login = login;
		this.password = password;
		this.role = role;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((cpjcnpj == null) ? 0 : cpjcnpj.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (cpjcnpj == null) {
			if (other.cpjcnpj != null)
				return false;
		} else if (!cpjcnpj.equals(other.cpjcnpj))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (role != other.role)
			return false;
		return true;
	}

	public String getCpjcnpj() {
		return cpjcnpj;
	}

	public void setCpjcnpj(String cpjcnpj) {
		this.cpjcnpj = cpjcnpj;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
		if (this.role == UserRole.ADMIN) {
			authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
			authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
			authorities.add(new SimpleGrantedAuthority("ROLE_EMPRESA"));
		}
		if (this.role == UserRole.EMPRESA) {
			authorities.add(new SimpleGrantedAuthority("ROLE_EMPRESA"));
		}

		if (this.role == UserRole.USER) {
			authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		}
		return authorities;
	}

	@Override
	public String getUsername() {
		return login;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
