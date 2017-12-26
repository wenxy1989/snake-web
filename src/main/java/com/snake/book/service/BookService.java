package com.snake.book.service;

import com.base.exception.DaoException;
import com.base.exception.ServiceException;
import com.base.service.BasicService;
import com.base.util.DateTimeUtils;
import com.snake.book.dao.IBookDao;
import com.snake.book.dao.IParagraphDao;
import com.snake.book.model.Book;
import com.snake.book.model.Paragraph;
import com.snake.chinese.utils.MainLogicInstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wen on 2015/5/1.
 */
@Service
@Repository("bookService")
public class BookService extends BasicService<Book> implements IBookService {

    @Autowired
    @Qualifier("bookDao")
    private IBookDao bookDao;
    @Autowired
    @Qualifier("paragraphDao")
    private IParagraphDao paragraphDao;

    @Override
    public IBookDao getDao() {
        return bookDao;
    }

    private boolean saveBookParagraphList(Long bookId,File file){
        boolean result = false;
        try {
            List<String> list = MainLogicInstants.getInstant().getChineseParagraphList(file);
            if (null != list && list.size() > 0) {
                String now = DateTimeUtils.getNowDateTime();
                List<Paragraph> paragraphs = new ArrayList<Paragraph>();
                for (String value : list) {
                    Paragraph paragraph = new Paragraph(value);
                    paragraph.setBookId(bookId);
                    paragraph.setCreatedTime(now);
                    paragraphs.add(paragraph);
                }
                paragraphDao.batchInsert(paragraphs);
                result = true;
            }
        }catch (DaoException e){
            logger.error("save book paragraph happened service exception",e);
            result = false;
        } catch (IOException e) {
            logger.error("save book paragraph happened io exception",e);
            result = false;
        }
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor ={Throwable.class })
    public void create(Book object) throws ServiceException {
        File file = new File(object.getFile());
        if(null != file && file.isFile()) {
            super.create(object);
            saveBookParagraphList(object.getId(), file);
        }
    }


    /*@Override
    public void update(Book object) throws ServiceException {
        File file = new File(object.getFile());
        if(null != file && file.isFile()) {
            super.create(object);
            saveBookParagraphList(object.getId(), file);
        }
    }*/

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor ={Throwable.class })
    public void delete(Object id) throws ServiceException {
        try {
            paragraphDao.deleteByBookId((Long)id);
            super.delete(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
