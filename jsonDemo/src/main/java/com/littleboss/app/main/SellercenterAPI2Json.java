package com.littleboss.app.main;

import com.littleboss.app.jsonUtil.JsonUtil;
import com.littleboss.app.lazada.beans.getProducts.*;
import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/*
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
*/
//import org.json.*;


public class SellercenterAPI2Json {
//  private static final String ScApiHost = "http://api.sellercenter.net/"; 

    private static final String ScApiHost = "https://sellercenter-api.lazada.com.my/";
    private static final String HASH_ALGORITHM = "HmacSHA256";
    private static final String CHAR_UTF_8 = "UTF-8";
    private static final String CHAR_ASCII = "ASCII";

    public static void main(String[] args) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("UserID", "156038530@qq.com");
        params.put("Timestamp", getCurrentTimestamp());
        params.put("Version", "1.0");
        params.put("Action", "GetProducts");
        params.put("UpdatedAfter", "2016-04-14T10:35:11+0800");
        params.put("Format", "JSON");
        // params.put("Format", "XML");

        final String apiKey = "963c5f36247dfe4ec38a766bb0fbc4777cc20e15";
        final String XML = "";
        // final String XML = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?
        //   ><Request><Product><SellerSku>4105382173aaee4</SellerSku><Price>12</Price></Product></Request>";
        final String out = getSellercenterApiResponse(params, apiKey, XML); // provide XML as an empty string
        System.out.println(out); // print out the XML response
//        try {
////         JsonUtil.jsonStr2Obj(out,GetProducts.class,SuccessResponse.class,Body.class,XMLGregorianCalendarImpl.class,Head.class, Images.class, Product.class, ProductData.class, Products.class,List.class,String.class,BigInteger.class, BigDecimal.class);//,List.class
//            //, Body.class, Head.class, Images.class, Product.class, ProductData.class, Products.class,List.class,String.class,BigInteger.class, BigDecimal.class
////            System.out.println(sr.getHead().getRequestAction());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        digestJson(out);
    }


//    public static void digestJson(String input) {
//        try {
////            String str = "{\"data\":{\"birth_day\":7,\"birth_month\":6},\"errcode\":0,\"msg\":\"ok\",\"ret\":0}";
//
////            ObjectMapper mapper = new ObjectMapper();
////            JsonNode root = mapper.readTree(input);
////
////            JsonNode data = root.path("SuccessResponse");
////
////            JsonNode head = data.path("Head");
////            JsonNode requestAction = head.path("RequestAction");
////
////            String rqa = requestAction.asText();
//
////            System.out.println(rqa);
//
////            JsonNode birth_month = data.path("birth_month");
////            System.out.println(birth_month.asInt());
////
////            JsonNode msg = root.path("msg");
////            System.out.println(msg.textValue());
////        } catch (IOException e) {
////        }
//    }

    /**
     * calculates the signature and sends the request
     *
     * @param params Map - request parameters
     * @param apiKey String - user's API Key
     * @param XML    String - Request Body
     */
    public static String getSellercenterApiResponse(Map<String, String> params, String apiKey, String XML) {
        System.out.println("getSellercenterApiResponse");
        String queryString = "";
        String Output = "";
        HttpURLConnection connection = null;
        URL url = null;
        Map<String, String> sortedParams = new TreeMap<String, String>(params);
        queryString = toQueryString(sortedParams);
        final String signature = hmacDigest(queryString, apiKey, HASH_ALGORITHM);
        queryString = queryString.concat("&Signature=".concat(signature));
        final String request = ScApiHost.concat("?".concat(queryString));
//	final String request = "http://sellercenter-api.lazada.com.my/?Action=GetProducts&Format=JSON&Timestamp=2016-05-08T01%3A14%3A00%2B0800&UpdatedAfter=2015-11-14T10%3A35%3A11%2B0800&UserID=156038530%40qq.com&Version=1.0&Signature=cc84c47f71fba22c275a498e91161c820a3ea3056896495f2a9cafb1e79b0edf";
        System.out.println(request);

        String result = "";

        BufferedReader in = null;
        try {
            //   url = new URL("https://sellercenter-api.lazada.com.my/?Action=GetProducts&Format=JSON&Timestamp=2016-05-09T10%3A09%3A29%2B0800&UpdatedAfter=2016-04-14T10%3A35%3A11%2B0800&UserID=156038530%40qq.com&Version=1.0&Signature=245bae1720e30159651e6ba3452133529073803fc2d30774d5d53694f8c95ee9");
            url = new URL(request);
            connection = (HttpURLConnection) url.openConnection();
            //  connection.setDoOutput(true);
            // connection.setDoInput(true);
            //   connection.setInstanceFollowRedirects(false);
            // connection.setRequestMethod("POST");
            connection.setRequestMethod("GET");
            //    connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            //   connection.setRequestProperty("charset", CHAR_UTF_8);
            //    connection.setUseCaches(false);
   /*   if (!XML.equals("")) {
        connection.setRequestProperty("Content-Length", "" + Integer.toString(XML.getBytes().length));
        DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
        wr.writeBytes(XML);
        wr.flush();
        wr.close();
      }*/


            String line;
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            while ((line = reader.readLine()) != null) {
                Output += line + "\n";
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return Output;
    }

    /**
     * generates hash key
     *
     * @param msg
     * @param keyString
     * @param algo
     * @return string
     */
    private static String hmacDigest(String msg, String keyString, String algo) {
        String digest = null;
        try {
            SecretKeySpec key = new SecretKeySpec((keyString).getBytes(CHAR_UTF_8), algo);
            Mac mac = Mac.getInstance(algo);
            mac.init(key);
            final byte[] bytes = mac.doFinal(msg.getBytes(CHAR_ASCII));
            StringBuffer hash = new StringBuffer();
            for (int i = 0; i < bytes.length; i++) {
                String hex = Integer.toHexString(0xFF & bytes[i]);
                if (hex.length() == 1) {
                    hash.append('0');
                }
                hash.append(hex);
            }
            digest = hash.toString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return digest;
    }

    /**
     * build querystring out of params map
     *
     * @param data map of params
     * @return string
     * @throws UnsupportedEncodingException
     */
    private static String toQueryString(Map<String, String> data) {
        String queryString = "";
        try {
            StringBuffer params = new StringBuffer();
            for (Map.Entry<String, String> pair : data.entrySet()) {
                params.append(URLEncoder.encode((String) pair.getKey(), CHAR_UTF_8) + "=");
                params.append(URLEncoder.encode((String) pair.getValue(), CHAR_UTF_8) + "&");
            }
            if (params.length() > 0) {
                params.deleteCharAt(params.length() - 1);
            }
            queryString = params.toString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return queryString;
    }

    /**
     * returns the current timestamp
     *
     * @return current timestamp in ISO 8601 format
     */
    private static String getCurrentTimestamp() {
        final TimeZone tz = TimeZone.getTimeZone("UTC");
        final DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mmZ");
        df.setTimeZone(tz);
        final String nowAsISO = df.format(new Date());
        return nowAsISO;
    }
}