package com.snake.book.dao;

import com.base.dao.MybatisBasicDao;
import com.snake.book.dao.IBookDao;
import com.snake.book.model.Book;
import org.springframework.stereotype.Repository;

/**
 * Created by wen on 2015/5/1.
 */
@Repository("bookDao")
public class BookDao extends MybatisBasicDao<Book> implements IBookDao {

    public BookDao() {
        super(Book.class);
    }
}
