package com.web.${app.code}.config;

import java.util.ArrayList;
import java.util.List;

public interface Page<T> {

    int getOffset();

    int getLimit();

    void addCondition(String condition);

    void setOrder(String order);

    String getWhere();

    String getOrder();

    void setParam(T param);

    T getParam();

    int getTotal();

    List<T> getContent();

    Page build(int total, List<T> list);

    /**
     * @param number min 1
     * @param size min 10
     * @return
     */
    public static Page newInstance(int number, int size) {
        size = size < 10 ? 10 : size;
        return new Pagination((number < 1 ? 0 : number - 1) * size, size);
    }

    class Pagination<T> implements Page<T> {

        private List<String> conditionList;
        private T param;
        private String order;
        private int offset;
        private int limit;
        private int total;
        private List<T> content;

        public Pagination(int offset, int limit) {
            this.offset = offset;
            this.limit = limit;
        }

        public void setOffset(int offset) {
            this.offset = offset;
        }

        public void setLimit(int limit) {
            this.limit = limit;
        }

        @Override
        public Page<T> build(int total, List<T> list) {
            this.total = total;
            this.content = list;
            return this;
        }

        @Override
        public int getOffset() {
            return this.offset;
        }

        @Override
        public int getLimit() {
            return this.limit;
        }

        @Override
        public void addCondition(String condition) {
            if (null == this.conditionList) {
                this.conditionList = new ArrayList<>();
            }
            this.conditionList.add(condition);
        }

        @Override
        public String getWhere() {
            if (null != this.conditionList) {
                int length = this.conditionList.size();
                if (length > 0) {
                    StringBuffer sb = new StringBuffer(this.conditionList.get(0));
                    if (length > 1) {
                        for (int i = 1; i < length; i++) {
                            sb.append(" and ");
                            sb.append(this.conditionList.get(i));
                        }
                    }
                    return sb.toString();
                }
            }
            return null;
        }

        @Override
        public void setOrder(String order) {
            this.order = order;
        }

        @Override
        public String getOrder() {
            return this.order;
        }

        @Override
        public void setParam(T param) {
            this.param = param;
        }

        @Override
        public T getParam() {
            return this.param;
        }

        @Override
        public int getTotal() {
            return this.total;
        }

        @Override
        public List<T> getContent() {
            return this.content;
        }
    }

}
