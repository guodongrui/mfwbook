package org.mfwbook.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.mfwbook.recommend.Recommendable;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
// @NamedQuery(name="Person.withNameAndAddressNamedQuery",
// query="select p from Person p where p.name=?1 and address=?2")
public class Book implements Recommendable{

	@Id
	private String bookId;
	private String bookName;
	private String author;
	private String publisher;
	private String bookTypes;
	private Integer readTimes;
	private Integer searchTimes;

	@JsonIgnore 
	@ManyToMany(mappedBy="prefer_books",cascade = CascadeType.PERSIST,
			fetch=FetchType.EAGER)
/*	@JoinTable(name = "user_books", 
			joinColumns = {@JoinColumn(name = "BOOK_ID", referencedColumnName = "BOOKID") },
			inverseJoinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "ID") })*/
	private Set<User> prefer_users;
	
	
	@JsonIgnore 
	@ManyToMany(mappedBy="reading_books",cascade = CascadeType.PERSIST,
			fetch=FetchType.EAGER)
/*	@JoinTable(name = "user_books", 
			joinColumns = {@JoinColumn(name = "BOOK_ID", referencedColumnName = "BOOKID") },
			inverseJoinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "ID") })*/
	private Set<User> reading_users;
	
	@JsonIgnore 
	@ManyToMany(mappedBy="have_read_books",cascade = CascadeType.PERSIST,
			fetch=FetchType.EAGER)
/*	@JoinTable(name = "user_books", 
			joinColumns = {@JoinColumn(name = "BOOK_ID", referencedColumnName = "BOOKID") },
			inverseJoinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "ID") })*/
	private Set<User> have_read_users;

	public Book() {}
	
	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getBookTypes() {
		return bookTypes;
	}

	public void setBookTypes(String bookTypes) {
		this.bookTypes = bookTypes;
	}

	

	public Integer getReadTimes() {
		return readTimes;
	}

	public void setReadTimes(Integer readTimes) {
		this.readTimes = readTimes;
	}

	public Integer getSearchTimes() {
		return searchTimes;
	}

	public void setSearchTimes(Integer searchTimes) {
		this.searchTimes = searchTimes;
	}

	public Set<User> getPrefer_users() {
		return prefer_users;
	}

	public void setPrefer_users(Set<User> prefer_users) {
		this.prefer_users = prefer_users;
	}

	public Set<User> getReading_users() {
		return reading_users;
	}

	public void setReading_users(Set<User> reading_users) {
		this.reading_users = reading_users;
	}

	public Set<User> getHave_read_users() {
		return have_read_users;
	}

	public void setHave_read_users(Set<User> have_read_users) {
		this.have_read_users = have_read_users;
	}

	public int getPopularity(){
		return prefer_users.size()+reading_users.size()+have_read_users.size();
	}

}
