package org.mfwbook.recommend;

import java.util.List;

import org.mfwbook.model.Book;
import org.mfwbook.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecommendService {
	
	@Autowired
	RecommendStrategy<Book> booksRecommendSys;
	
	public List<Book> booksRecommend(User user){
		return booksRecommendSys.recommend(user);
	}
	
	
	@Autowired
	RecommendStrategy<User> friendsRecommendSys;
	
	public List<User> friendsRecommend(User user){
		return friendsRecommendSys.recommend(user);
	}
}
