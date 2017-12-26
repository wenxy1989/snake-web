package com.base.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Properties;

public class MysqlUtils {

	private static String url = "jdbc:mysql://localhost:3306/";

	public static Connection getConnection(String url, String username, String password) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Properties pro = new Properties();
		pro.setProperty("charSet", "GB2312");
		return DriverManager.getConnection(url, username, password);
	}

	public static ResultSet getResultSet(Connection conn, String sql) {
		ResultSet rs = null;
		try {
			rs = conn.createStatement().executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public static void main(String args[]) throws ClassNotFoundException, SQLException{
		String database = "base";
		Connection conn = MysqlUtils.getConnection(MysqlUtils.url, "root", "live123456");
		String packageName = "com.model";
		String tableSql = "SELECT * FROM information_schema.TABLES WHERE TABLE_SCHEMA='"+database+"'";
		ResultSet rs = MysqlUtils.getResultSet(conn, tableSql);
		while(rs.next()){
			String tableName = rs.getString("TABLE_NAME");
			String columnSql = "SELECT * FROM information_schema.COLUMNS WHERE TABLE_SCHEMA='"+database+"' AND TABLE_NAME='"+tableName+"'";
			ResultSet columns = MysqlUtils.getResultSet(conn, columnSql);
			while(columns.next()){
				Column column = new Column();
				column.setTableSchema(database);
				column.setTableName(tableName);
				column.setColumnName(columns.getString("COLUMN_NAME"));
				column.setDataType(columns.getString("DATA_TYPE"));
				String columnType = columns.getString("COLUMN_TYPE");
				int startOffset = columnType.indexOf("(") + 1, endOffset = columnType.indexOf(")");
				if(startOffset > -1 && endOffset > -1){
					System.out.println("columnType is : " + columnType);
					String length = columnType.substring(startOffset, endOffset);
					System.out.println("length is : " + length);
					column.setDataLength(new Integer(length));
				}
				column.setColumnComment(columns.getString("COLUMN_COMMENT"));
				
			}
		}
		
	}

}

class Column{
	private String tableSchema;//database name
	private String tableName;//table name
	private String columnName;//columns name
	private Boolean isNullable;//is null or not
	private String dataType;//data type
//	private String columnType;//column type
	private Integer dataLength;//column max length
	private String columnComment;//column comment
	public String getTableSchema() {
		return tableSchema;
	}
	public void setTableSchema(String tableSchema) {
		this.tableSchema = tableSchema;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public Boolean getIsNullable() {
		return isNullable;
	}
	public void setIsNullable(Boolean isNullable) {
		this.isNullable = isNullable;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	/*public String getColumnType() {
		return columnType;
	}
	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}*/
	public Integer getDataLength() {
		return dataLength;
	}
	public void setDataLength(Integer dataLength) {
		this.dataLength = dataLength;
	}
	public String getColumnComment() {
		return columnComment;
	}
	public void setColumnComment(String columnComment) {
		this.columnComment = columnComment;
	}
}
