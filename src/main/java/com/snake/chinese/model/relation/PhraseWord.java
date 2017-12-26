package com.snake.chinese.model.relation;

import com.snake.chinese.model.Phrase;
import com.snake.chinese.model.Word;

/**
 * 短语-构成词组关系
 * Created by wen on 2015/8/23.
 */
public class PhraseWord {

    private Long phraseId;
    private Long wordId;
    private Phrase phrase;
    private Word word;

    public PhraseWord(){}

    public PhraseWord(Long phraseId, Long wordId) {
        this.phraseId = phraseId;
        this.wordId = wordId;
    }

    public Long getPhraseId() {
        return phraseId;
    }

    public void setPhraseId(Long phraseId) {
        this.phraseId = phraseId;
    }

    public Long getWordId() {
        return wordId;
    }

    public void setWordId(Long wordId) {
        this.wordId = wordId;
    }

    public Phrase getPhrase() {
        return phrase;
    }

    public void setPhrase(Phrase phrase) {
        this.phrase = phrase;
    }

    public Word getWord() {
        return word;
    }

    public void setWord(Word word) {
        this.word = word;
    }
}
