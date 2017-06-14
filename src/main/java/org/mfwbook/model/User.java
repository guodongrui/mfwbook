package org.mfwbook.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.mfwbook.recommend.Recommendable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


@Entity
public class User implements UserDetails,Recommendable{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue
    private Long id;
    private String name;
    private String password;
    
    @ManyToMany(cascade = CascadeType.REFRESH,fetch=FetchType.EAGER)
    private List<SysRole> roles;

//    @JsonIgnore
    @ManyToMany(/*mappedBy="users",*/
    		cascade=CascadeType.PERSIST,fetch=FetchType.EAGER)
    @JoinTable(name="user_prefer_books",
    		joinColumns={@JoinColumn(name = "USER_ID", referencedColumnName = "ID")},
    		inverseJoinColumns = {@JoinColumn(name = "BOOK_ID", referencedColumnName = "BOOKID")
    })
    private Set<Book> prefer_books = new HashSet<Book>();
    
    
    @ManyToMany(cascade=CascadeType.PERSIST,fetch=FetchType.EAGER)
    @JoinTable(name="user_reading_books",
    		joinColumns={@JoinColumn(name = "USER_ID", referencedColumnName = "ID")},
    		inverseJoinColumns = {@JoinColumn(name = "BOOK_ID", referencedColumnName = "BOOKID")
    })
    private Set<Book> reading_books = new HashSet<Book>();
    
    
    @ManyToMany(cascade=CascadeType.PERSIST,fetch=FetchType.EAGER)
    @JoinTable(name="user_have_read_books",
    		joinColumns={@JoinColumn(name = "USER_ID", referencedColumnName = "ID")},
    		inverseJoinColumns = {@JoinColumn(name = "BOOK_ID", referencedColumnName = "BOOKID")
    })
    private Set<Book> have_read_books = new HashSet<Book>();
    
    
    @OneToMany(mappedBy="user",cascade=CascadeType.PERSIST,fetch=FetchType.EAGER)
    private Set<BookComment> bookComments;

    public User() {
        super();
    }

    public User(String name, String password) {
        super();
        this.name = name;
        this.password = password;
    }
    
    

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    

	public List<SysRole> getRoles() {
		return roles;
	}

	public void setRoles(List<SysRole> roles) {
		this.roles = roles;
	}

	


	public Set<Book> getPrefer_books() {
		return prefer_books;
	}

	public void setPrefer_books(Set<Book> prefer_books) {
		this.prefer_books = prefer_books;
	}

	public Set<Book> getReading_books() {
		return reading_books;
	}

	public void setReading_books(Set<Book> reading_books) {
		this.reading_books = reading_books;
	}

	public Set<Book> getHave_read_books() {
		return have_read_books;
	}

	public void setHave_read_books(Set<Book> have_read_books) {
		this.have_read_books = have_read_books;
	}

	

	public Set<BookComment> getBookComments() {
		return bookComments;
	}

	public void setBookComments(Set<BookComment> bookComments) {
		this.bookComments = bookComments;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
		List<SysRole> roles = this.getRoles();
		for(SysRole sysRole:roles){
			authorities.add(new SimpleGrantedAuthority(sysRole.getName()));
		}
		return authorities;
	}

	@Override
	public String getUsername() {
		
		return name;
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

}
