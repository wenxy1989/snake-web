package com.snake.mysql.model;

public class Column {

    private String name;
    private Boolean isNullable;
    private String dataType;
    private Long characterLength;
    private Integer numberPrecision;
    private Integer numberScale;
    private Boolean autoIncrement;
    private String comment;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getNullable() {
        return isNullable;
    }

    public void setNullable(Boolean nullable) {
        isNullable = nullable;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public Long getCharacterLength() {
        return characterLength;
    }

    public void setCharacterLength(Long characterLength) {
        this.characterLength = characterLength;
    }

    public Integer getNumberPrecision() {
        return numberPrecision;
    }

    public void setNumberPrecision(Integer numberPrecision) {
        this.numberPrecision = numberPrecision;
    }

    public Integer getNumberScale() {
        return numberScale;
    }

    public void setNumberScale(Integer numberScale) {
        this.numberScale = numberScale;
    }

    public Boolean getAutoIncrement() {
        return autoIncrement;
    }

    public void setAutoIncrement(Boolean autoIncrement) {
        this.autoIncrement = autoIncrement;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
