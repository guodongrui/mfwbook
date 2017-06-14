package org.mfwbook.statistic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.mfwbook.model.Book;
import org.mfwbook.model.User;


public class UserPreferenceStatistic {
	
	private User user;
	private Map<String, Integer> user_prefer_books_types;
	private Map<String, Integer> user_prefer_authors;
	
	public UserPreferenceStatistic(User user){
		this.user = user;
		analyseUserPreference();
	}

//	private void statistic
	
	public void analyseUserPreference(){
		user_prefer_books_types = new HashMap<>();
		user_prefer_authors = new HashMap<>();
		Set<Book> user_books = new HashSet<>();
		user_books.addAll(user.getPrefer_books());
		user_books.addAll(user.getReading_books());
		user_books.addAll(user.getHave_read_books());
		
		for(Book book:user_books){
			String[] bookTypes = book.getBookTypes().split("[,，]");
			for(String bookType: bookTypes){
				Integer bookTypeCount = user_prefer_books_types.get(bookType);
				user_prefer_books_types.put(bookType, bookTypeCount==null?1:++bookTypeCount);
			}
			
			
			String[] bookAuthors = book.getAuthor().split("[,，]");
			for(String author:bookAuthors){
				Integer authorCount = user_prefer_authors.get(author);
				user_prefer_authors.put(author, authorCount==null?1:++authorCount);
				
			}
		}
	}
	
	public Map<String, Integer> getBookTypesStatistic(){
		return user_prefer_books_types;
	}
	
	
	public List<Map.Entry<String, Integer>> getSortBookTypesStatistic(){
		List<Map.Entry<String, Integer>> sort_book_types =
				new ArrayList<>(user_prefer_books_types.entrySet());
		Collections.sort(sort_book_types, new PreferenceComparator());
		
		return sort_book_types;
	}
	
	public List<String> getBookTypesAsSortList(){
		List<String> sortList = new ArrayList<>();
		List<Map.Entry<String, Integer>> sort_book_types = getSortBookTypesStatistic();
		for(Map.Entry<String, Integer> entry:sort_book_types){
			sortList.add(entry.getKey());
		}
		return sortList;
	}
	
	
	
	public Map<String, Integer> getAuthorsStatistic(){
		return user_prefer_authors;
	}
	
	public List<Map.Entry<String, Integer>> getSortAuthorsStatistic(){
		List<Map.Entry<String, Integer>> sort_authors = 
				new ArrayList<>(user_prefer_authors.entrySet());
		Collections.sort(sort_authors, new PreferenceComparator());
		return sort_authors;
	}
	
	public List<String> getAuthorsAsSortList(){
		List<String> sortList = new ArrayList<>();
		List<Map.Entry<String, Integer>> sort_authors = getSortAuthorsStatistic();
		for(Map.Entry<String, Integer> entry:sort_authors){
			sortList.add(entry.getKey());
		}
		return sortList;
	}
	
	
	private class PreferenceComparator implements Comparator<Map.Entry<String, Integer>>{

		@Override
		public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
			return o1.getValue()-o2.getValue();
		}
		
	}
}
