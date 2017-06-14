package org.mfwbook.recommend;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.mfwbook.dao.BookRepository;
import org.mfwbook.model.Book;
import org.mfwbook.model.User;
import org.mfwbook.statistic.UserPreferenceStatistic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BooksRecommendBySys implements RecommendStrategy<Book>{
	
	private static int MaxRecommendations = 5;
	
	
	@Autowired
	BookRepository bookRepository;
	

	@Override
	public List<Book> recommend(User user) {

		
		UserPreferenceStatistic userPreferenceStatistic =
				new UserPreferenceStatistic(user);
	
		List<Map.Entry<String, Integer>> userPreferBookTypes1 =
				userPreferenceStatistic.getSortBookTypesStatistic();
		List<Map.Entry<String, Integer>> userPreferAuthors1 =
				userPreferenceStatistic.getSortAuthorsStatistic();
		
		List<Book> booksInDB = bookRepository.findAll();
		
		Map<Book, Integer> bookAndWeight = new HashMap<>();
		for(Book book:booksInDB){
			if(isAlreadyHadBook(user, book))continue;
			if(bookAndWeight.get(book)==null) bookAndWeight.put(book, 0);
			for(Map.Entry<String, Integer> authorAndWeight:userPreferAuthors1){
				if(book.getAuthor().contains(authorAndWeight.getKey())){
					Integer weight = bookAndWeight.get(book);
					bookAndWeight.put(book, weight+authorAndWeight.getValue());
				}
			}
			for(Map.Entry<String, Integer> typeAndWeight : userPreferBookTypes1){
				if(book.getBookTypes().contains(typeAndWeight.getKey())){
					Integer weight = bookAndWeight.get(book);
					bookAndWeight.put(book, weight+typeAndWeight.getValue());
				}
			}
			
		}
		
		
		List<Map.Entry<Book, Integer>> bookEntrys = new ArrayList<>(bookAndWeight.entrySet());
		Collections.sort(bookEntrys,new Comparator<Map.Entry<Book, Integer>>() {

			@Override
			public int compare(Entry<Book, Integer> o1, Entry<Book, Integer> o2) {
				return o2.getValue()-o1.getValue();
			}
			
		});
		
		List<Book> recommendBooks = new ArrayList<>();
		for(int i=0;i<MaxRecommendations&&i<bookEntrys.size();i++){
			recommendBooks.add(bookEntrys.get(i).getKey());
		}
		
		return recommendBooks;
	}
	
	
	private boolean isAlreadyHadBook(User user,Book book){
		if(isContainTheBook(user.getPrefer_books(), book))return true;
		if(isContainTheBook(user.getReading_books(), book))return true;
		if(isContainTheBook(user.getHave_read_books(), book))return true;
		return false;
	}
	
	private boolean isContainTheBook(Set<Book> books,Book book){
		for(Book b:books){
			if(b.getBookId().equals(book.getBookId()))
				return true;
		}
		return false;
	}
	
}
