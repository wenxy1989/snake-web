/** book util **/
package com.snake.book;

import com.snake.ArrayTools;
import org.apache.commons.lang.StringUtils;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChineseTextUtils {

    private static final String httpReg = "^((https?|ftp|news):\\/\\/)?([a-z]([a-z0-9\\-]*[\\.。])+([a-z]{2}|aero|arpa|biz|com|coop|edu|gov|info|int|jobs|mil|museum|name|nato|net|org|pro|travel)|(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5]))(\\/[a-z0-9_\\-\\.~]+)*(\\/([a-z0-9_\\-\\.]*)(\\?[a-z0-9+_\\-\\.%=&]*)?)?(#[a-z][a-z0-9_]*)?$";
    private static final String simpleChineseReg = "^[\\u4E00-\\u9FA5]+$";
    private static final String englishReg = "([\\u0000-\\u002f]|[\\u003a-\\u0fff])+";
    private static Set<String> endSymbolList;
    private static Set<String> ignoreList;
    private static Map<String, String> coupleSymbolMap;

    static {

        coupleSymbolMap = new HashMap<String, String>();
        coupleSymbolMap.put("“", "”");
        coupleSymbolMap.put("\"", "\"");
        coupleSymbolMap.put("《", "》");
        coupleSymbolMap.put("<", ">");
        coupleSymbolMap.put("【", "】");
        coupleSymbolMap.put("[", "]");
        coupleSymbolMap.put("{", "}");
        coupleSymbolMap.put("（", "）");
        coupleSymbolMap.put("(", ")");

        endSymbolList = new HashSet<String>();
        endSymbolList.add("。");
        endSymbolList.add("？");
        endSymbolList.add("！");
        endSymbolList.add("；");
//        endSymbolList.add(".");
        endSymbolList.add("?");
        endSymbolList.add("!");
        endSymbolList.add(";");

        ignoreList = new HashSet<String>();
        ignoreList.addAll(endSymbolList);
        ignoreList.addAll(coupleSymbolMap.keySet());
        ignoreList.addAll(coupleSymbolMap.values());
        ignoreList.add("ī");
        ignoreList.add("？");
        ignoreList.add("！");
        ignoreList.add("；");
        ignoreList.add(".");
        ignoreList.add("?");
        ignoreList.add("!");
        ignoreList.add(";");
        ignoreList.add("—");
        ignoreList.add("…");
        ignoreList.add("◎");
    }

    public boolean allIsIn(Collection<String> collection, String words) {
        boolean result = true;
        for (int i = 0; i < words.length(); i++) {
            String word = String.valueOf(words.charAt(i));
            if (!ArrayTools.contains(ignoreList, word)) {
                result = false;
                break;
            }
        }
        return result;
    }

    public static List<String> getParagraphList(String line,List<String> list) {
        if(null != list) {
            list.clear();
            int startOffset = 0;
            String startChar = null;
            int status = 0;//read status
            for (int i = 0; i < line.length(); i++) {
                if (i == line.length() - 1) {//in the end
                    list.add(line.substring(startOffset));
                } else {//not end
                    String word = String.valueOf(line.charAt(i));
                    switch (status) {
                        case 1: {
                            if (coupleSymbolMap.get(startChar).equals(word)) {
                                status = 0;
                            }
                        }
                        break;
                        default: {
                            if (ArrayTools.contains(endSymbolList, word)) {//complete words
                                String words = line.substring(startOffset, i + 1);
                                startOffset = i + 1;
                                list.add(words);
                            } else if (ArrayTools.contains(coupleSymbolMap.keySet(), word)) {
                                startChar = word;
                                status = 1;
                            }
                        }
                    }
                }
            }
        }
        return list;
    }

    public static List<String> getPhraseList(String paragraph,List<String> list) {
        if(null != list) {
            list.clear();
            if (StringUtils.isNotEmpty(paragraph)) {
                if (paragraph.contains("，")) {
                    String[] phraseArray = paragraph.split("，");
                    for (String phrase : phraseArray) {
                        if (StringUtils.isNotEmpty(phrase)) {
                            list.add(phrase);
                        }
                    }
                } else if (paragraph.contains(",")) {
                    String[] phraseArray = paragraph.split(",");
                    for (String phrase : phraseArray) {
                        if (StringUtils.isNotEmpty(phrase)) {
                            list.add(phrase);
                        }
                    }
                } else {
                    list.add(paragraph);
                }
            }
        }
        return list;
    }

    private static final int chineseWordLength = 4;

    public static List<String> getChineseWordList(String phrase,List<String> list) {
        if(null != list) {
            list.clear();
            if (StringUtils.isNotBlank(phrase)) {
                for (int i = 0; i < phrase.length(); i++) {
                    for (int j = i; j <= (Math.min(i + chineseWordLength, phrase.length() - i)); j++) {
                        String word = phrase.substring(i, j);
                        if (word.matches(simpleChineseReg)) {
                            list.add(word);
                        }
                    }
                }
            }
        }
        return list;
    }

    public static List<String> getSingleChineseWordList(String phrase,List<String> list) {
        if(null != list) {
            list.clear();
            if (StringUtils.isNotBlank(phrase)) {
                for (int i = 0; i < phrase.length(); i++) {
                    String word = String.valueOf(phrase.charAt(i));
                    if (word.matches(simpleChineseReg)) {
                        list.add(word);
                    }
                }
            }
        }
        return list;
    }

    private void printlnBytes(String line) {
        Pattern pattern = Pattern.compile(englishReg);
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }

}