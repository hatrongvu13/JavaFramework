package com.tdt.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import java.util.List;

public final class StringUtil {

    public static void main(String args[]) {
//        String text = "This - text ! has \\ /allot # of -- % special % characters";
//        text = text.replaceAll("[^a-zA-Z0-9]", "");
//        System.out.println(text);
//        String html = "This is @#@%^$&& &lt; bold";
//        html = html.replaceAll("[^a-zA-Z0-9\\s+]", "");
//        System.out.println(html);


//        String text = "<p class=\"MsoNormal\"><span style=\"font-size: x-small; font-family: Arial;\"><span style=\"font-size: 10pt; font-family: Arial;\">Dear colleagues,</span></span></p><p class=\"MsoNormal\"><span style=\"font-size: x-small; font-family: Arial;\"><span style=\"font-size: 10pt; font-family: Arial;\"></span></span></p><p class=\"MsoNormal\"><strong><span style=\"text-decoration: underline;\"><span style=\"font-size: x-small; font-family: Arial;\"><span style=\"font-weight: bold; font-size: 10pt; font-family: Arial;\">Reception Services</span></span></span></strong><span style=\"font-size: x-small; font-family: Arial;\"><span style=\"font-size: 10pt; font-family: Arial;\"> </span></span></p><p class=\"MsoNormal\"><span style=\"font-size: x-small; font-family: Arial;\"><span style=\"font-size: 10pt; font-family: Arial;\">The reception desk is the central point of contact whereby our clients reach us.</span></span><span style=\"font-size: x-small; font-family: Arial;\"><span style=\"font-size: 10pt; font-family: Arial;\">It is important for the receptionist to be able to channel the calls to the desired </span></span><span style=\"font-size: x-small; font-family: Arial;\"><span style=\"font-size: 10pt; font-family: Arial;\">contact as soon as possible.  We therefore ask that the following be adhered to, </span></span><span style=\"font-size: x-small; font-family: Arial;\"><span style=\"font-size: 10pt; font-family: Arial;\">to avoid any bottleneck in the call process for our clients to reach the right contact.</span></span><span style=\"font-size: x-small; font-family: Arial;\"><span style=\"font-size: 10pt; font-family: Arial;\"> </span></span></p><p class=\"MsoNormal\"><span style=\"font-size: x-small; font-family: Arial;\"><span style=\"font-size: 10pt; font-family: Arial;\">1.  Do not call the receptionist to look out for your clients in level 6, please attend to your clients personally.<br /></span></span><span style=\"font-size: x-small; font-family: Arial;\"><span style=\"font-size: 10pt; font-family: Arial;\">2.  Do not use the phone in reception counter to call any of internal staff.<br /></span></span><span style=\"font-size: x-small; font-family: Arial;\"><span style=\"font-size: 10pt; font-family: Arial;\">3.  Do not ask the receptionist to do errands, her main focus is serving the clients through the phone system </span></span><span style=\"font-size: x-small; font-family: Arial;\"><span style=\"font-size: 10pt; font-family: Arial;\">or receiving them over the counter.</span></span></p>";
//        text = text.replaceAll("<br />", "\n");
//
//
//        System.out.println(text);

//
//        String input = "<a href=\"http://www.google.com\">Link to Google</a>";
//        Document doc = Jsoup.parse(input, "UTF-8");
//        Elements links = doc.select("a[href]");
//        while (links.hasNext()) {
//            Element link = iterator.next();
//            Element bold = doc.createElement("b").appendText(link.text());
//            link.replaceWith(bold);
//        }
    }

    public static String html2text(String html) {
        return Jsoup.parse(html).text();
    }
}
