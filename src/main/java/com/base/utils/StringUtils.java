package com.base.utils;

public class StringUtils {

    public static final String COLUMN_SPLIT_SYMBOL = "_";
    public static final String STRING_BLANK = "";

	public static boolean isEmpty(String str){
		return null == str || "".equals(str.trim());
	}
	
	public static boolean notEmpty(String str){
		return null != str && !"".equals(str.trim());
	}
	
	public static String columnToAttribute(String columnName){
        String attribute = null;
		String[] words = columnName.split(COLUMN_SPLIT_SYMBOL);
        if(words.length > 1) {
            StringBuffer sb = new StringBuffer(words[0].toLowerCase());
            for (int i = 1; i < words.length; i++) {
                String str = words[i];
                sb.append(getFirstLarge(str));
            }
            attribute = sb.toString();
        }else{
            attribute = columnName.replace(COLUMN_SPLIT_SYMBOL,STRING_BLANK).toLowerCase();
        }
		return attribute;
	}
	
	public static String attributeToColumn(String attributeName){
		String columnName = null;
		if(attributeName.indexOf(COLUMN_SPLIT_SYMBOL) > -1){
			columnName = attributeName.toLowerCase();
		}else{
			columnName = attributeName.toLowerCase() + COLUMN_SPLIT_SYMBOL;
		}
		return columnName;
	}

	public static CharSequence getFirstLarge(String str) {
		String large = "module";
		if(notEmpty(str)){
			large = str.substring(0, 1).toUpperCase()+str.substring(1);
		}
		return large;
	}

	public static CharSequence getFirstLittle(String str) {
		String little = "module";
		if(notEmpty(str)){
			little = str.substring(0, 1).toLowerCase()+str.substring(1);
		}
		return little;
	}

}
