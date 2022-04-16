package com.alishirmohammadi.librarymanagementsystem.repository;
import java.util.List;
import com.alishirmohammadi.librarymanagementsystem.entity.Book;
import com.alishirmohammadi.librarymanagementsystem.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookRepository extends JpaRepository<Book, Long> {

	@Query("SELECT b FROM Book b WHERE b.name LIKE %?1%" + " OR b.isbn LIKE %?1%" + " OR b.serialName LIKE %?1%")
	public List<Book> search(String keyword);

}
