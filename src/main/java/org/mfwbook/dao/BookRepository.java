package org.mfwbook.dao;

import java.util.List;
import java.util.Set;

import org.mfwbook.model.Book;
import org.mfwbook.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface BookRepository extends JpaRepository<Book, String> {
    
    List<Book> findByBookId(String bookId);
    List<Book> findByAuthorLike(String author);
    List<Book> findByBookNameLike(String bookName);
    List<Book> findByBookTypesLike(String bookTypes);
    Book findByBookName(String bookName);
    
//    void deleteByBookId(String bookId);
    
//    @Query("select p from Person p where p.name= :name and p.address= :address")
//    Person withNameAndAddressQuery(@Param("name")String name, @Param("address")String address);
//    
//    Person withNameAndAddressNamedQuery(String name, String address);
}
