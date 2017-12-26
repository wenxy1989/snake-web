package com.snake.mysql.model;


import com.base.util.StringTools;

public class ColumnType {
	
	private static final String[] javaType = new String[]{"Long","Integer","Double","Boolean","Float","Character","Byte","String"};
	private static final String[] mysqlType = new String[]{"BIGINT","INTEGER","DECIMAL","BOOLEAN","FLOAT","CHAR","TINYINT","VARCHAR"};
	
	/**
	 * 字段用途类型：主键、外键(外键对本表一对一和一对多)、类型翻译代码代码()
	 */
	public static final String[] attributeType = new String[]{"primary","onetoone","onetomany","foreign","translate"};
	
	public static String getMysqlByJava(String java){
		return mysqlType[getIndexFromJava(java)];
	}
	
	public static String getJavaByMysql(String mysql){
		return javaType[getIndexFromMysql(mysql)];
	}
	
	public static String getMysql(Integer index){
		return mysqlType[index];
	}
	
	public static String getJava(Integer index){
		return javaType[index];
	}
	
	public static Integer getIndexFromJava(String java){
		Integer index = -1;
		if(StringTools.isNotBlank(java)){
			for(int i=0;i<javaType.length;i++){
				if(javaType[i].toLowerCase().equals(java.toLowerCase())){
					index = i;
					break;
				}
			}
		}
		return index;
	}
	
	public static Integer getIndexFromMysql(String mysql){
		Integer index = -1;
		if(StringTools.isNotBlank(mysql)){
			for(int i=0;i<mysqlType.length;i++){
				if(mysqlType[i].equals(mysql.toUpperCase())){
					index = i;
					break;
				}
			}
		}
		return index;
	}

}
