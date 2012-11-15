package net.petrusha.homebudget.persistence.gae.jdo.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.Order;
import javax.jdo.annotations.Extension;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;



@PersistenceCapable
public class UserProfileEntity implements Serializable {
	private static final long serialVersionUID = -8785565883383024287L;
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String id;
	
	@Persistent
	private String name; 
	
	@Persistent
	private String userId;
	
	@Persistent(defaultFetchGroup = "true")
	@Order(extensions = @Extension(vendorName="datanucleus",key="list-ordering", value="order asc"))
	private List<AccountEntity> accounts = new ArrayList<AccountEntity>();
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<AccountEntity> getAccounts() {
		if (accounts == null) {
			accounts = new ArrayList<AccountEntity>();
		}
		return accounts;
	}

	public void addAccount(AccountEntity account) {
		if (this.accounts == null) {
			this.accounts = new ArrayList<AccountEntity>();
		}
		this.accounts.add(account);
	}
	
	public void removeAccountById(String accountId) {
		for (AccountEntity account: accounts) {
			if (account.getId() != null && account.getId().equals(accountId)) {
				accounts.remove(account);
			}
		}
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
		UserProfileEntity other = (UserProfileEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	
	
}
