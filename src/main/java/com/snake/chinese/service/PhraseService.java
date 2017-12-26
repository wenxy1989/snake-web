package com.snake.chinese.service;

import com.base.exception.DaoException;
import com.base.exception.ServiceException;
import com.base.service.BasicService;
import com.snake.book.model.Paragraph;
import com.snake.chinese.utils.WordUtils;
import com.snake.chinese.dao.IPhraseDao;
import com.snake.chinese.model.Phrase;
import com.snake.chinese.model.relation.PhraseWord;
import com.snake.chinese.model.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wen on 2015/5/1.
 */
@Service
@Repository("phraseService")
public class PhraseService extends BasicService<Phrase> implements IPhraseService {

    @Autowired
    @Qualifier("phraseDao")
    private IPhraseDao phraseDao;

    @Override
    public IPhraseDao getDao() {
        return phraseDao;
    }

    public void batchInsert(List<Phrase> list) throws ServiceException {
        try{
            if (null != list && list.size() > 0){
                int count = list.size();
                int size = 100;
                int page = count/size;
                if(count%size > 0){
                    page ++;
                }
                for(int i=0;i<page;i++) {
                    int start = i*size;
                    int end = start+size-1;
                    if(end > count){
                        end = count;
                    }
                    List<Phrase> phrases = list.subList(start,end);
                    this.phraseDao.batchInsert(phrases);
                }
            }
        }catch (DaoException e){
            throw new ServiceException(e);
        }
    }

    public Integer getReadPageNo(Long userId, Long bookId) throws ServiceException {
        try {
            return this.phraseDao.getReadPageNo(userId,bookId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public void updateReadRecord(Long userId, Long bookId, Integer pageNo, String time) throws ServiceException {
        try {
            this.phraseDao.updateReadRecord(userId, bookId, pageNo, time);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public void createReadRecord(Long userId, Long bookId, String time) throws ServiceException{
        try {
            this.phraseDao.createReadRecord(userId, bookId, time);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public void deleteByBookId(Long id) throws ServiceException {
        try {
            this.phraseDao.deleteByBookId(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public void batchInsertPhraseWord(List<PhraseWord> list) throws ServiceException {
        try {
            this.phraseDao.batchInsertPhraseWord(list);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public List<PhraseWord> getPhraseWordListByPhraseId(Long phraseId) throws ServiceException {
        try {
            List<PhraseWord> phraseWords = this.phraseDao.getPhraseWordListByPhraseId(phraseId);
            if(null != phraseWords && phraseWords.size() > 0){
                for(PhraseWord phraseWord : phraseWords){
                    Long wordId = phraseWord.getWordId();
                    Word word = WordUtils.getWord(wordId);
                    phraseWord.setWord(word);
                }
            }
            return phraseWords;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Phrase> getListByBookId(Long bookId, int start, int count) throws ServiceException {
        try {
            return this.phraseDao.getListByBookId(bookId, start, count);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
