package com.yumeng.libbaseProject.listener;

/**
 * Created by YoKey on 16/10/9.
 */
public interface IndexableEntity {

    String getFieldIndexBy();

    void setFieldIndexBy(String indexField);

    void setFieldPinyinIndexBy(String pinyin);
}