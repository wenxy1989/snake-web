package com.snake.chinese.utils;

import com.snake.ArrayTools;
import com.snake.chinese.model.Element;
import com.snake.chinese.model.Word;
import org.apache.commons.lang.StringUtils;

import java.util.*;

/**
 * Created by wen on 2015/8/10.
 */
public class PhraseUtils {

    /**
     * 从小到大
     * 1.逐字分析组织词组类型
     * 2.当不能组成词组时，从断开处再次分析下面的词组类型（这个时候要考虑词组的优先顺序，例如：他的[裤/子]）
     * 3.分析完所有的词组类型后，适配语句类型
     */

    /**
     * 从大到小
     * 1.找到第一个匹配的词组（主谓宾）
     * 2.尝试可能的句型
     * 3.正确则继续
     * 4.错误则从新划分句子成分
     */
    public static List<Word> getWordList(String phrase){
        List<Word> words = null;
        if(null != phrase && phrase.length() > 0) {
            words = new ArrayList<Word>();
            for (int startOffset = 0; startOffset < phrase.length(); ) {
                for (int i = phrase.length(); i > startOffset; i--) {
                    String value = phrase.substring(startOffset, i);
                    if (WordUtils.isWord(value)) {
                        words.add(WordUtils.getWord(value));
                        startOffset = i;
                        break;
                    }
                    if(i == startOffset+1){//can not find word in this
                        words.add(new Word(0l,value));
                        startOffset++;
                    }
                }
            }
        }
        return words;
    }

    public static Long understand(List<Word> words){
        Long result = null;
        boolean success = false;
        Set<Long> structureIdSet = WordUtils.getStructureIdSet();
        if(null != words && words.size() > 0 && null != structureIdSet && structureIdSet.size() > 0) {
            Iterator<Long> iterator = structureIdSet.iterator();
            while (iterator.hasNext()) {//获取语句格式（主谓宾）
                Long structureId = iterator.next();
                List<Long> elementIds = WordUtils.getElementIds(structureId);
                if(words.size() >= elementIds.size()) {
                    success = true;
                    for (int j = 0; j < elementIds.size(); j++) {//依次验证各个成分（主-(名词|代词)/谓-动词/宾-(名词|代词)）
                        Long elementId = elementIds.get(j);
                        Word word = words.get(j);
                        if (null != word) {
                            Long typeId = PhraseUtils.suitable(word.getId(), elementId);
                            success = success && null != typeId;
                            if(success){
                                word.setTypeId(typeId);
                            }
                        } else {
                            success = false;
                            break;
                        }
                    }
                }
                if(success){
                    result = structureId;
                    break;
                }
            }
        }
        return result;
    }

    public static Long suitable(Long wordId, Long elementId) {
        Long typeId = null;
//        boolean suitable = false;
        if(null != wordId && null != elementId) {
            List<Long> typeIds = WordUtils.getTypeIds(elementId);
            List<Long> wordTypeIds = WordUtils.getWordTypeIds(wordId);
            List<Long> union = ArrayTools.union(typeIds, wordTypeIds);
            if(null != union && union.size() > 0){
//                suitable = true;
                typeId = union.get(0);
            }
        }
        return typeId;
    }

    //todo
    private static List<Element> buildElementList(List<Word> words,List<Long> elementIds) {
        List<Element> elementList = null;
        if(null != elementIds && elementIds.size() > 0 && null != words && words.size() > 0) {
            elementList = new ArrayList<Element>();
            for (int i = 0; i < elementIds.size(); i++) {//依次验证各个成分（主-(名词|代词)/谓-动词/宾-(名词|代词)）
                Element element = WordUtils.getElement(elementIds.get(i));
                Word word = words.get(i);
                if (null != word && null != element) {
                    String value = element.getName();
                    Element phraseElement = new Element(word,value);
                    elementList.add(phraseElement);
                }
            }
        }
        return elementList;
    }

    private static void print(List<Word> words,List<Long> elementIds){
        if(null != elementIds && elementIds.size() > 0 && null != words && words.size() > 0){
            for (int i = 0; i < elementIds.size(); i++) {//依次验证各个成分（主-(名词|代词)/谓-动词/宾-(名词|代词)）
                String structureType = WordUtils.getElement(elementIds.get(i)).getName();
                Word word = words.get(i);
                if(null != word && null != structureType) {
                    String value = word.getWord();
                    Long typeId = word.getTypeId();
                    System.out.println(structureType + ":" + value + "(" + WordUtils.getType(typeId).getName() + ")");
                }
            }
        }
    }

    public static void main(String args[]) {
        String phrase1 = "二愣子睁大着双眼";
        List<Word> words = PhraseUtils.getWordList(phrase1);
        Long structureId = PhraseUtils.understand(words);
        if (null != structureId) {
            List<Long> elementIds = WordUtils.getElementIds(structureId);
            PhraseUtils.print(words, elementIds);
        }
    }

    public static List<String> getPhraseList(String paragraph) {
        //todo 按照标点强行分割，不符合逻辑
        List<String> set = null;
        if (StringUtils.isNotEmpty(paragraph)) {
            set = new ArrayList<String>();
            if (paragraph.contains("，")) {
                String[] phraseArray = paragraph.split("，");
                for (String phrase : phraseArray) {
                    if (StringUtils.isNotEmpty(phrase)) {
                        set.add(phrase);
                    }
                }
            } else if (paragraph.contains(",")) {
                String[] phraseArray = paragraph.split(",");
                for (String phrase : phraseArray) {
                    if (StringUtils.isNotEmpty(phrase)) {
                        set.add(phrase);
                    }
                }
            } else {
                set.add(paragraph);
            }
        }
        return set;
    }

}
