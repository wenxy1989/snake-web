/**
 * book util *
 */
package com.snake.chinese.utils;

import com.base.util.text.AfterReadLineListener;
import com.base.util.text.TextReader;
import com.base.util.text.TextReaderBuilder;
import com.google.common.cache.*;
import com.snake.book.ChineseTextUtils;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainLogicInstants {

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

    private TextReaderBuilder builder;

    private MainLogicInstants() {
        builder = TextReaderBuilder.newBuilder();
    }

    public static MainLogicInstants getInstant() {
        return new MainLogicInstants();
    }

    private void printlnBytes(String line) {
        Pattern pattern = Pattern.compile(englishReg);
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }

    public String getFileEncodeType(File file) {
        String result = "UTF-8";
        if (null != file && file.exists() && file.isFile()) {
            byte[] b = new byte[3];
            if (!file.exists()) {
                return result;
            }
            FileInputStream ios = null;
            try {
                ios = new FileInputStream(file);
                ios.read(b);
                if (b[0] == -17 && b[1] == -69 && b[2] == -65) {
                    result = "UTF-8";
                } else {
                    result = "UTF-8";
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (ios != null) ios.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        return result;
    }

    public List<String> getChineseParagraphList(File sourceFile) throws IOException {
        final List<String> paragraphList = new ArrayList<String>();
        final List<String> tempList = new ArrayList<String>();
        TextReader reader = builder.afterReadLineListener(new AfterReadLineListener() {
            @Override
            public void readLine(String line) {
                line = line.replaceAll("\\s+", "").replaceAll(englishReg, "");
                ChineseTextUtils.getParagraphList(line, tempList);
                if (tempList.size() > 0) {
                    for (String each : tempList) {
                        if (StringUtils.isNotBlank(each)) {
                            paragraphList.add(each);
                        }
                    }
                    tempList.clear();
                }
            }
        }).build(sourceFile);
        reader.read();
        return paragraphList;
    }

    public Set<String> getPhraseSet(File file) throws IOException {
        final Set<String> set = new HashSet<String>();
        final List<String> tempList = new ArrayList<String>();
        TextReader reader = builder.afterReadLineListener(new AfterReadLineListener() {
            @Override
            public void readLine(String line) {
                line = line.replaceAll("\\s+", "").replaceAll(englishReg, "");
                ChineseTextUtils.getParagraphList(line, tempList);
                if (tempList.size() > 0) {
                    for (String each : tempList) {
                        if (StringUtils.isNotBlank(each)) {
                            List<String> phraseList = ChineseTextUtils.getPhraseList(each, tempList);
                            for (String phrase : phraseList) {
                                set.add(phrase);
                            }
                        }
                    }
                    tempList.clear();
                }
            }
        }).build(file);
        reader.read();
        return set;
    }

    public Map<String, Integer> getChineseCountMap(File file) throws IOException {
        final Map<String, Integer> map = new HashMap<String, Integer>();
        final List<String> paragraphList = new ArrayList<String>();
        final List<String> phraseList = new ArrayList<String>();
        final List<String> wordList = new ArrayList<String>();
        TextReader reader = builder.afterReadLineListener(new AfterReadLineListener() {
            @Override
            public void readLine(String line) {
                line = line.replaceAll("\\s+", "").replaceAll(englishReg, "");
                ChineseTextUtils.getParagraphList(line, paragraphList);
                if (paragraphList.size() > 0) {
                    for (String paragraph : paragraphList) {
                        if (StringUtils.isNotBlank(paragraph)) {
                            ChineseTextUtils.getPhraseList(paragraph, phraseList);
                            if (paragraphList.size() > 0) {
                                for (String phrase : phraseList) {
                                    ChineseTextUtils.getChineseWordList(phrase, wordList);
                                    if (wordList.size() > 0) {
                                        for (String word : wordList) {
                                            if (word.length() > 50) {
                                                System.out.println("length > 50 : " + word);
                                            } else if (map.get(word) == null) {
                                                map.put(word, 1);
                                            } else {
                                                Integer count = 1 + map.get(word);
                                                map.put(word, (count++));
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }).build(file);
        paragraphList.clear();
        phraseList.clear();
        wordList.clear();
        reader.read();
        return map;
    }

    public Cache<String, Integer> getChineseCountCache(File file) throws IOException {
        final Cache<String, Integer> cache = CacheBuilder.newBuilder()
                .concurrencyLevel(8)
                .expireAfterAccess(30, TimeUnit.SECONDS)
                .initialCapacity(1000)
                .maximumSize(1000000000l)
//                .recordStats()
                /*.removalListener(new RemovalListener<Object, Object>() {
                    @Override
                    public void onRemoval(RemovalNotification<Object, Object> notification) {
                        System.out.println("remove key : " + notification.getKey());
                    }
                })*/
                .build();
                /*.build(new CacheLoader<String, Integer>() {
                    @Override
                    public Integer load(String s) throws Exception {
                        return null;
                    }
                });*/
        final List<String> paragraphList = new ArrayList<String>();
        final List<String> phraseList = new ArrayList<String>();
        final List<String> wordList = new ArrayList<String>();
        TextReader reader = builder.afterReadLineListener(new AfterReadLineListener() {
            @Override
            public void readLine(String line){
                line = line.replaceAll("\\s+", "").replaceAll(englishReg, "");
                ChineseTextUtils.getParagraphList(line, paragraphList);
                Callable<Integer> value = new Callable<Integer>() {
                    @Override
                    public Integer call() throws Exception {
                        return 0;
                    }
                };
                if (paragraphList.size() > 0) {
                    for (String paragraph : paragraphList) {
                        if (StringUtils.isNotBlank(paragraph)) {
                            ChineseTextUtils.getPhraseList(paragraph, phraseList);
                            if (paragraphList.size() > 0) {
                                for (String phrase : phraseList) {
                                    ChineseTextUtils.getChineseWordList(phrase, wordList);
                                    if (wordList.size() > 0) {
                                        for (String word : wordList) {
                                            if (word.length() > 50) {
                                                System.out.println("length > 50 : " + word);
                                            } else try {
                                                if (cache.get(word,value) == 0) {
                                                    cache.put(word, 1);
                                                } else {
                                                    Integer count = 1 + cache.get(word,value);
                                                    cache.put(word, (count++));
                                                }
                                            } catch (ExecutionException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }).build(file);
        paragraphList.clear();
        phraseList.clear();
        wordList.clear();
        reader.read();
        return cache;
    }

}