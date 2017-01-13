package com.fans.javassist.test;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.jsoup.select.Elements;
import org.jsoup.select.Evaluator;

/**
 * Created by ${fanchunshuai} on 2016-12-6.
 *
 * @version: V1.0
 * @Desc:
 * @Copyright (c) 2016 58到家-版权所有
 */
public class JsoupTest {
    public static void main(String[] args) {
        String htmlStr = "<p>这是下拉框选择：<select name=\"carlist\" form=\"carform\">\n" +
                "   <option value=\"volvo\">Volvo</option>\n" +
                "   <option value=\"saab\">Saab</option>\n" +
                "   <option value=\"opel\">Opel</option>\n" +
                "   <option value=\"audi\">Audi</option>\n" +
                "</select> ，这是单选框选择：<input type='radio' name='user3' checked='true' value='two'    onclick='getValue()'>Two<input type='radio' name='user3' value='four' >Four,这是复选框选择：我喜欢自行车:<input type=\"checkbox\" name=\"Bike\" > <br />\n" +
                "我喜欢汽车：<input type=\"checkbox\" name=\"Car\"></p>";
      //  Document doc = Jsoup.parse(htmlStr);
       // Element outerHtml = doc.select("select").first().text("Volvo");
       // doc.select("input").first().text("Volvo");
        //doc.select("select").remove();
       // System.out.println(outerHtml.html());
       // System.out.println(doc.text());
        String textStr = "<p>这是文本框1:<input type='text' name='user6' id='user6ID' />,这是文本框2:<input type='text' name='user7' id='user7ID' />,这是文本框3:<input type='text' name='user8' id='user8ID' /></p>";

        Document document = Jsoup.parse(textStr,"UTF-8");

        document.getElementById("user6ID").text("valueUser:user6");
        document.getElementById("user7ID").text("valueUser:user7");
        document.getElementById("user8ID").text("valueUser:user8");
        //document.select("input").get(4).text("valueUser:user7");

        String htmlStr1 = document.html();

        //设置html文本格式
        Document document1 = Jsoup.parse(htmlStr1,"UTF-8");
        document1.head().append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");

        System.out.println(document1.html());
    }
}
