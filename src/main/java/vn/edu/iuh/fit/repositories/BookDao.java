package vn.edu.iuh.fit.repositories;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.data.redis.core.RedisTemplate;import org.springframework.stereotype.Repository;import vn.edu.iuh.fit.model.Book;import java.util.List;@Repositorypublic class BookDao {    public static final String HASH_KEY = "Book";    @Autowired    private RedisTemplate template;    public Book save(Book book){        template.opsForHash().put(HASH_KEY,book.getId(),book);        return book;    }    public List<Book> findAll(){        return template.opsForHash().values(HASH_KEY);    }    public Book findBookById(long id){        System.out.println("called findProductById() from DB");        return (Book) template.opsForHash().get(HASH_KEY,id);    }    public String deleteProduct(int id){        template.opsForHash().delete(HASH_KEY,id);        return "product removed !!";    }}