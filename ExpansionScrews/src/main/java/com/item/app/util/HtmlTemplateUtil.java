package com.item.app.util;

import com.item.app.model.DbDocument;
import com.item.app.model.TableDocument;

import java.util.List;

/**
 * 生成html文件模版工具类
 * 主要针对的是其他数据库
 * @author zcm
 */
public class HtmlTemplateUtil {

    private static String getHead(){
        return "<html lang=\"zh\">\n" +
                "    <head>\n" +
                "        <title>数据库设计文档</title>\n" +
                "        <style type='text/css'>\n" +
                "            body {padding-bottom: 50px}\n" +
                "            body, td {font-family: verdana, fantasy;font-size: 12px;line-height: 150%}\n" +
                "            table {width: 100%;background-color: #ccc;margin: 5px 0}\n" +
                "            td {background-color: #fff;padding: 3px 3px 3px 10px}\n" +
                "            thead td {text-align: center;font-weight: bold;background-color: #eee}\n" +
                "            a:link, a:visited, a:active {color: #015fb6;text-decoration: none}\n" +
                "            a:hover {color: #e33e06}\n" +
                "        </style>\n" +
                "    </head>\n" +
                "    <body style='text-align:center;'>\n" +
                "        <div style='width:800px; margin:20px auto; text-align:left;'>\n" +
                "            <a name='index'></a>";
    }

    private static String getTitle(String title,String dbName,String version,String desc){
        return String.format("<h2 style='text-align:center; line-height:50px;'>%s</h2>\n" +
                "            <div>\n" +
                "                <b>数据库名：%s</b><br><b>文档版本：%s</b>\n" +
                "                <br>\n" +
                "                <b>文档描述：%s</b>\n" +
                "                <br>\n" +
                "            </div>",title,dbName,version,desc);
    }

    private static String getDbTable(List<String> dbName){
        String db = "<table cellspacing='1'>\n" +
                "                <thead>\n" +
                "                    <tr>\n" +
                "                        <td style='width:40px; '>序号</td>\n" +
                "                        <td>表名</td>\n" +
                "                        <td>说明</td>\n" +
                "                    </tr>\n" +
                "                </thead>";

        String[] table = {""};
        dbName.forEach(name->{
            String col = String.format("<tr>\n" +
                    "                    <td style='text-align:center;'>1</td>\n" +
                    "                    <td><a href='#%s'>%s</a></td>\n" +
                    "                    <td></td>\n" +
                    "                </tr>",name,name);
            table[0] += col;
        });

        String dbEnd = "</table>";

        return db + table[0] + dbEnd;
    }

    private static String getContentList(List<TableDocument> list){

        String[] content = {""};

        list.forEach(table -> {

            content[0] += String.format("<a name='dict_catalog'></a>\n" +
                    "            <div style='margin-top:30px;'>\n" +
                    "                <a href='#%s' style='float:right; margin-top:6px;'>返回目录</a>\n" +
                    "                <b>表名：%s</b>\n" +
                    "            </div>\n" +
                    "            <div>说明：%s</div>\n" +
                    "            <div>数据列：</div>\n" +
                    "            <table cellspacing='1'>\n" +
                    "                <thead>\n" +
                    "                    <tr>\n" +
                    "                        <td style='width:40px; '>序号</td>\n" +
                    "                        <td>名称</td><td>数据类型</td>\n" +
                    "                        <td>长度</td><td>小数位</td>\n" +
                    "                        <td>允许空值</td>\n" +
                    "                        <td>主键</td>\n" +
                    "                        <td>默认值</td>\n" +
                    "                        <td>说明</td>\n" +
                    "                    </tr>\n" +
                    "                </thead>\n" +
                    "                <tr>\n" +
                    "                    <td style='text-align:center;'>%s</td>\n" +
                    "                    <td>%s</td>\n" +
                    "                    <td align='center'>%s</td>\n" +
                    "                    <td align='center'>%s</td>\n" +
                    "                    <td align='center'>%s</td>\n" +
                    "                    <td align='center'>%s</td>\n" +
                    "                    <td align='center'>%s</td>\n" +
                    "                    <td align='center'>%s</td>\n" +
                    "                    <td align='center'>%s</td>\n" +
                    "                </tr>\n" +
                    "            </table>",table.getName(),table.getName(),table.getDesc(),
                    table.getNum(),table.getColumnName(),table.getType(),table.getLength(),
                    table.getDot(),table.getNullStr(),table.getKey(),table.getDefaultValue(),table.getComment());

        });

        return content[0];
    }

    private static String getFooter(){
        return "</div>\n" +
                "        <footer>\n" +
                "        </footer>\n" +
                "    </body>\n" +
                "</html>";
    }


    public static String getDbHtmlStr(DbDocument dbDocument){
        String head = getHead();
        //String title,String dbName,String version,String desc
        String db = getTitle(dbDocument.getTitle(),dbDocument.getDbName(),dbDocument.getVersion(),dbDocument.getDesc());
        String title = getDbTable(dbDocument.getDbNameList());
        String table = getContentList(dbDocument.getTableDocumentList());
        String footer = getFooter();
        return head + db + title + table + footer;
    }

}
