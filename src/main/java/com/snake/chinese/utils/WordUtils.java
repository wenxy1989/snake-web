package com.snake.chinese.utils;

import com.snake.chinese.model.Element;
import com.snake.chinese.model.relation.ElementType;
import com.snake.chinese.model.relation.StructureElement;
import com.snake.chinese.model.Type;
import com.snake.chinese.model.Word;
import com.snake.chinese.model.relation.WordType;

import java.util.*;

/**
 * Created by wen on 2015/8/11.
 */
public class WordUtils {

    private static Map<String,Long> wordIdMap = null;
    private static Map<Long,String> idWordMap = null;
    private static Map<Long,List<Long>> wordTypesMap = null;
    private static Map<Long,String> idElementMap = null;
    private static Map<Long,List<Long>> elementTypesMap = null;
    private static Map<Long,String> idTypeMap = null;
    private static Map<Long,List<Long>> structureElementsMap = null;

    public static List<Long> getWordTypeIds(Long wordId){
        List<Long> typeIds = null;
        if(null != wordTypesMap) {
            typeIds = wordTypesMap.get(wordId);
        }
        return typeIds;
    }

    public static Word getWord(String value) {
        Word result = null;
        if(null != wordIdMap){
            Long wordId = wordIdMap.get(value);
            result = new Word(wordId,value);
        }
        return result;
    }

    public static List<Long> getTypeIds(Long elementId){
        List<Long> list = null;
        if(null != elementTypesMap){
            list = elementTypesMap.get(elementId);
        }
        return list;
    }

    public static boolean isWord(String value){
        boolean result = false;
        if(null != wordIdMap){
            result = null != wordIdMap.get(value);
        }
        return result;
    }

    public static Type getType(Long typeId) {
        Type result = null;
        if(null != idTypeMap) {
            String name = idTypeMap.get(typeId);
            result = new Type(typeId,name);
        }
        return result;
    }

    public static Word getWord(Long wordId) {
        Word result = null;
        if(null != idWordMap){
            String value = idWordMap.get(wordId);
            result = new Word(wordId,value);
        }
        return result;
    }

    public static Element getElement(Long elementId) {
        Element result = null;
        if(null != idElementMap){
            String name = idElementMap.get(elementId);
            result = new Element(elementId,name);
        }
        return result;
    }

    public static Set<Long> getStructureIdSet(){
        Set<Long> result = null;
        if(null != structureElementsMap){
            result = structureElementsMap.keySet();
        }
        return result;
    }

    public static List<Long> getElementIds(Long structureId) {
        List<Long> result = null;
        if(null != structureElementsMap){
            result = structureElementsMap.get(structureId);
        }
        return result;
    }

    public static void addType(Long id,String value){
        if(null == idTypeMap){
            idTypeMap = new HashMap<Long, String>();
        }
        idTypeMap.put(id,value);
    }

    public static void addWord(Long id,String value){
        if(null == wordIdMap){
            wordIdMap = new HashMap<String, Long>();
        }
        if(null == idWordMap){
            idWordMap = new HashMap<Long,String>();
        }
        wordIdMap.put(value,id);
        idWordMap.put(id, value);
    }

    public static void addElement(Long id,String value){
        if(null == idElementMap){
            idElementMap = new HashMap<Long, String>();
        }
        idElementMap.put(id,value);
    }

    public static void addWordType(Long wordId,Long typeId){
        if(null == wordTypesMap){
            wordTypesMap = new HashMap<Long,List<Long>>();
        }
        List<Long> typeIds = wordTypesMap.get(wordId);
        if(null == typeIds){
            typeIds = new ArrayList<Long>();
            wordTypesMap.put(wordId,typeIds);
        }
        typeIds.add(typeId);
    }

    public static void addElementType(Long elementId,Long typeId){
        if(null == elementTypesMap){
            elementTypesMap = new HashMap<Long,List<Long>>();
        }
        List<Long> typeIds = elementTypesMap.get(elementId);
        if(null == typeIds){
            typeIds = new ArrayList<Long>();
            elementTypesMap.put(elementId,typeIds);
        }
        typeIds.add(typeId);
    }

    public static void addStructureElement(Long structureId,Long elementId){
        if(null == structureElementsMap){
            structureElementsMap = new HashMap<Long, List<Long>>();
        }
        List<Long> elementIds = structureElementsMap.get(structureId);
        if(null == elementIds){
            elementIds = new ArrayList<Long>();
            structureElementsMap.put(structureId,elementIds);
        }
        elementIds.add(elementId);
    }

    public static void addType(Type type){
        if(null != type){
            addType(type.getId(), type.getName());
        }
    }

    public static void addWord(Word word){
        if(null != word) {
            addWord(word.getId(), word.getWord());
        }
    }

    public static void addElement(Element element){
        if(null != element) {
            addElement(element.getId(), element.getName());
        }
    }

    public static void addWordType(WordType wordType){
        if(null != wordType){
            addWordType(wordType.getWordId(), wordType.getTypeId());
        }
    }

    public static void addElementType(ElementType elementType){
        if(null != elementType) {
            addElementType(elementType.getElementId(), elementType.getTypeId());
        }
    }

    public static void addWordType(Word word,Type type){
        if(null != word && null != type){
            addWordType(word.getId(), type.getId());
        }
    }

    public static void addElementType(Element element,Type type){
        if(null != element && null != type){
            addElementType(element.getId(), type.getId());
        }
    }

    public static void addStructureElement(StructureElement structureElement){
        if(null != structureElement){
            addStructureElement(structureElement.getStructureId(), structureElement.getElementId());
        }
    }

    public static void setWordList(List list) {
        if(null != list && list.size() > 0){
            for(Object obj:list){
                Word word = (Word) obj;
                addWord(word);
            }
        }
    }

    public static void setTypeList(List list){
        if(null != list && list.size() > 0){
            for(Object obj:list){
                Type type = (Type) obj;
                addType(type);
            }
        }
    }

    public static void setElementList(List list) {
        if(null != list && list.size() > 0){
            for(Object obj:list){
                Element element = (Element) obj;
                addElement(element);
            }
        }
    }

    public static void setWordTypeList(List list) {
        if(null != list && list.size() > 0){
            for(Object obj:list){
                WordType wordType = (WordType) obj;
                addWordType(wordType);
            }

        }
    }

    public static void setElementTypeList(List list) {
        if(null != list && list.size() > 0){
            for(Object obj:list){
                ElementType word = (ElementType) obj;
                addElementType(word);
            }
        }
    }

    public static void setStructureElementList(List list) {
        if(null != list && list.size() > 0){
            for(Object obj:list){
                StructureElement structureElement = (StructureElement) obj;
                addStructureElement(structureElement);
            }
        }
    }

    public static void removeWord(Word word){
        if(null != idWordMap){
            idWordMap.remove(word.getId());
        }
        if(null != wordIdMap){
            wordIdMap.remove(word.getWord());
        }
    }

}
