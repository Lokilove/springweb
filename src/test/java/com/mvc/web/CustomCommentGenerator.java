package com.mvc.web;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.InnerClass;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.internal.DefaultCommentGenerator;

import java.util.Properties;

/**
 * 日本語コメントを自動生成する CommentGenerator
 */
public class CustomCommentGenerator extends DefaultCommentGenerator {

    private boolean addRemarkComments = false;
    private boolean suppressAllComments = false;

    private Properties properties;

    public CustomCommentGenerator() {
        super();
        properties = new Properties();
    }

    @Override
    public void addConfigurationProperties(Properties properties) {
        this.properties.putAll(properties);
    }

//    @Override
//    public void addConfigurationProperties(java.util.Properties properties) {
//        super.addConfigurationProperties(properties);
//        String addRemark = properties.getProperty("addRemarkComments");
//        if ("true".equalsIgnoreCase(addRemark)) {
//            this.addRemarkComments = true;
//        }
//    }

    /**
     * クラスコメント（Mapper インターフェース用）
     */
    @Override
    public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable) {
        if (!suppressAllComments) {
            String domainObjectName = introspectedTable.getFullyQualifiedTable().getDomainObjectName();
            String remarks = introspectedTable.getRemarks(); // DBのコメント（なければ空）

            innerClass.addJavaDocLine("/**");
            innerClass.addJavaDocLine(" * " + (remarks.isEmpty() ? domainObjectName : remarks) + "用 Mapper");
            innerClass.addJavaDocLine(" * テーブル: " + introspectedTable.getFullyQualifiedTable());
            innerClass.addJavaDocLine(" */");
        }
    }

    /**
     * メソッドコメント（Mapperの各メソッド）
     */
    @Override
    public void addGeneralMethodComment(Method method, IntrospectedTable introspectedTable) {
        String remarks = introspectedTable.getRemarks();
        String tableName = introspectedTable.getFullyQualifiedTable().getIntrospectedTableName();
        String classComment = "";
        if (remarks == null || remarks.isEmpty()) {
            classComment = tableName + " に対する " + method.getName() + " メソッド";
        } else {
            classComment = remarks + "（" + tableName + "） に対する " + method.getName() + " メソッド";
        }

        method.addJavaDocLine("/**");
        method.addJavaDocLine(" * " + classComment);
        method.addJavaDocLine(" */");
    }

    /**
     * フィールドコメント（Modelクラスのフィールド）
     */
    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable,
                                IntrospectedColumn introspectedColumn) {
        String remarks = introspectedColumn.getRemarks();
        if (remarks != null && !remarks.isEmpty()) {
            field.addJavaDocLine("/** " + remarks + " */");
        }
    }

    /**
     * Getter/Setter にもコメント付与したい場合
     */
    @Override
    public void addGetterComment(Method method, IntrospectedTable introspectedTable,
                                 IntrospectedColumn introspectedColumn) {
        if (!addRemarkComments && introspectedColumn.getRemarks() != null
                && !introspectedColumn.getRemarks().isEmpty()) {
            method.addJavaDocLine("/**");
            method.addJavaDocLine(" * " + introspectedColumn.getRemarks() + "取得");
            method.addJavaDocLine(" */");
        }
    }

    @Override
    public void addSetterComment(Method method, IntrospectedTable introspectedTable,
                                 IntrospectedColumn introspectedColumn) {
        if (!addRemarkComments && introspectedColumn.getRemarks() != null
                && !introspectedColumn.getRemarks().isEmpty()) {
            method.addJavaDocLine("/**");
            method.addJavaDocLine(" * " + introspectedColumn.getRemarks() + "設定");
            method.addJavaDocLine(" */");
        }
    }
}
