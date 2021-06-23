package com.hitex.evn.utils;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.util.Random;


/**
 * @author Chidq
 * @project yousim
 * @created 03/04/2021 - 10:58 AM
 */
public class StringUtils {

    public static String formatIsdnKit(String number){
        String isdn = "0" + number;
        return isdn;
    }

    public static String formatSerialKit(String number){
//        number = "89840810008725125633";
        String isdn =  number.substring(9,number.length()-1);
        return isdn;
    }

    public static String formatIsdnCallWs(String number){
        String isdn = "84" + number;
        return isdn;
    }



    public static String formatSerial(String serial){
        String number = serial.substring(7);
        return number;
    }

    public static String formatIsdnRequest(String number){
        String isdn = number;
        if(number.startsWith("0") ){
            isdn = number.replaceFirst("0","");
        } else if(number.startsWith("84")){
            isdn = number.replaceFirst("84","");
        }
        return isdn;
    }

    public static String formatPhoneRequest(String number){
        String phone = number;
        if(number.startsWith("84") ){
            phone = number.replaceFirst("84","0");
        } else if(number.startsWith("+84")){
            phone = number.replace("+84","0");
        }
        return phone;
    }

    public static String randOtp(int min, int max) {
        try {
            Random rn = new Random();
            int range = max - min + 1;
            int randomNum = min + rn.nextInt(range);
            return String.valueOf(randomNum);
        } catch (Exception e) {
            return "";
        }
    }

//    public static int getMinOtp() {
//        return (int) Math.pow(10, Constant.OTP_NUMBER - 1);
//    }
//
//    public static int getMaxOtp() {
//        return (int) (Math.pow(10, Constant.OTP_NUMBER) - 1);
//    }

    public static boolean checkIsdn(String number){
        if(number.startsWith("0") || number.startsWith("84") && number.length()==10){
            return true;
        }
        return false;
    }

    public static int countIsdn(String n) {
        int number = Integer.parseInt(n);
        int DEC_10 = 10;
        int total = 0;
        do {
            total = total + number % DEC_10;
            number = number / DEC_10;
        } while (number > 0);
        int result = total % DEC_10;
        return result;
    }

    private static Document convertStringToXMLDocument(String xmlString)
    {
        //Parser that produces DOM object trees from XML content
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        //API to obtain DOM Document instance
        DocumentBuilder builder = null;
        try
        {
            //Create DocumentBuilder with default configuration
            builder = factory.newDocumentBuilder();

            //Parse the content to Document object
            Document doc = builder.parse(new InputSource(new StringReader(xmlString)));
            return doc;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static int getPhysicType(String type){
        if (type.trim().equalsIgnoreCase("USIM")){
            return 1;
        } else  if (type.trim().equalsIgnoreCase("ESIM")){
            return 2;
        }else return 0;
    }

    public static String getTypeNameOrder(int type){
        switch (type){
            case 1 : return "Cá Nhân";
            case 2 : return "Đối Tác";
        }
        return null;
    }

}
