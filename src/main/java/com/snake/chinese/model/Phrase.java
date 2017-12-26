package com.snake.chinese.model;

import com.snake.chinese.utils.WordUtils;

import java.util.List;

/**
 * 短语
 * 例如：他要说的话
 * Created by wen on 2015/8/10.
 */
public class Phrase {

    private Long id;
    private Long bookId;
    private Long paragraphId;
    private String phrase;
    private String createdTime;
    private Long creatorId;
    private Long structureId;

    private char[] values;
    private List<Word> words;
    private List<Element> elements;

    public Phrase(){}

    public Phrase(String value){
        this.phrase = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhrase() {
        return phrase;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }

    public Long getParagraphId() {
        return paragraphId;
    }

    public void setParagraphId(Long paragraphId) {
        this.paragraphId = paragraphId;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public List<Word> getWords() {
        return words;
    }

    public void setWords(List<Word> words) {
        this.words = words;
    }

    public Long getStructureId() {
        return structureId;
    }

    public void setStructureId(Long structureId) {
        this.structureId = structureId;
    }

    public List<Element> getElements() {
        return elements;
    }

    public void setElements(List<Element> elements) {
        this.elements = elements;
    }

    public char[] getValues() {
        if(null != this.phrase){
            this.values = this.phrase.toCharArray();
        }
        return values;
    }

    public void setValues(char[] values) {
        this.values = values;
    }

    public String toString(){
        StringBuffer sb = new StringBuffer();
        if(null != elements && elements.size() > 0) {
            for (int i = 0; i < elements.size(); i++) {//依次验证各个成分（主-(名词|代词)/谓-动词/宾-(名词|代词)）
                Element element = elements.get(i);
                sb.append(element.getWord().getWord());
                sb.append(":");
                sb.append(element.getName());
                sb.append("(");
                sb.append(WordUtils.getType(element.getWord().getTypeId()).getName());
                sb.append(")");
            }
        }
        return sb.toString();
    }
}
