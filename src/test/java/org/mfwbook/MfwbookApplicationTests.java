package org.mfwbook;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mfwbook.dao.BookCommentRepository;
import org.mfwbook.dao.BookRepository;
import org.mfwbook.dao.UserRepository;
import org.mfwbook.model.Book;
import org.mfwbook.model.BookComment;
import org.mfwbook.model.User;
import org.mfwbook.recommend.BooksRecommendBySys;
import org.mfwbook.recommend.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MfwbookApplicationTests {

	@Autowired
	UserRepository userRepository;

	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	BookCommentRepository bookCommentRepository;
	
	@Autowired
	RecommendService recommendService;

	@Before
	public void InitBinder() {
/*		User user_z = new User("zhenxiongwu", "123");
		User user_g = new User("guodongrui", "234");

		Book book_spring_boot = new Book();
		book_spring_boot.setBookId("1");
		book_spring_boot.setBookName("spring boot");

		Book book_UML = new Book();
		book_UML.setBookId("2");
		book_UML.setBookName("UML");

		Set<Book> user_z_books = new HashSet<>();
		user_z_books.add(book_spring_boot);

//		user_z.setBooks(user_z_books);

		Set<Book> user_g_books = new HashSet<>();
		user_g_books.add(book_spring_boot);
		user_g_books.add(book_UML);
//		user_g.setBooks(user_g_books);

		userRepository.save(Arrays.asList(user_z, user_g));
//		bookRepository.save(Arrays.asList(book_spring_boot,book_UML));
*/
	}


	@After
	public void clearTable() {

		/*userRepository.deleteAll();
		bookRepository.deleteAll();*/

	}

	@Test
	public void contextLoads() {
		List<Book> books = bookRepository.findAll();
		assertNotNull(books);
		assertTrue(books.size() > 0);

		List<User> users = userRepository.findAll();
		assertNotNull(users);
		assertTrue(users.size() > 0);
		
	/*	User user_zhenxiongwu = userRepository.findByName("zhenxiongwu");
		BookComment bookComment = new BookComment(user_zhenxiongwu, books.get(1));
		bookComment.setContent("zhenxiongwu's first commentzhenxiongwu's first comment"
				+ "zhenxiongwu's first commentzhenxiongwu's first comment");
//		userRepository.save(user_zhenxiongwu);
		bookCommentRepository.save(bookComment);
		assertNotNull(user_zhenxiongwu.getBookComments());*/
//		assertTrue(user_zhenxiongwu.getBookComments()!=null);
		List<BookComment> comments = bookCommentRepository.findAll();
		assertTrue(comments.size()==2);
		User user1 = comments.get(0).getUser();
		User user2 = comments.get(1).getUser();
		User user_zhenxiongwu = userRepository.findByName("zhenxiongwu");
		User user_zhenxiongwu1 = userRepository.findByName("zhenxiongwu");
		assertNotNull(user1);
		assertNotNull(user2);
		assertEquals(user1, user2);
//		assertEquals(user_zhenxiongwu1, user_zhenxiongwu);
		
		user_zhenxiongwu.getReading_books().add(books.get(0));
		userRepository.save(user_zhenxiongwu);
		
		List<Book> books1 = bookRepository.findAll();
		Book book1=books1.get(0);
		assertTrue(book1.getReading_users().size()==1);
		
		user_zhenxiongwu = userRepository.findByName("zhenxiongwu");
	
		List<Book> recommendBooks = recommendService.booksRecommend(user_zhenxiongwu);
		assertNotNull(recommendBooks);
		
//		assertTrue(recommendBooks.size()==0);
		assertEquals(recommendBooks.size(), 5);
		Logger logger = Logger.getLogger("zhenxiongwu");
		for(Book book:recommendBooks)
			logger.info("\n推荐的书："+book.getBookName()+"书作者："+book.getAuthor()+"\t书类型："+book.getBookTypes());

	}

}
