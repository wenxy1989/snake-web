package com.snake.chinese.model.relation;

import com.snake.chinese.model.Type;
import com.snake.chinese.model.Word;
import com.snake.resource.dao.MapObject;

/**
 * 词语-类型关联关系
 * Created by wen on 2015/8/23.
 */
public class WordType implements MapObject {

    private Long id;
    private Long wordId;
    private Long typeId;
    private Word word;
    private Type type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getWordId() {
        return wordId;
    }

    public void setWordId(Long wordId) {
        this.wordId = wordId;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public Word getWord() {
        return word;
    }

    public void setWord(Word word) {
        this.word = word;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
