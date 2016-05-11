package com.littleboss.app.jsonUtil;



import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by PHPnew6 on 2016/5/11.
 */
public class JsonUtil {
    public static <T> T jsonStr2Obj(String jsonStr, Class<T> collectionClass) throws IOException {
        ObjectMapper mapper = new ObjectMapper().setVisibility(JsonMethod.FIELD, JsonAutoDetect.Visibility.ANY);
        return mapper.readValue(jsonStr,collectionClass);
    }
    public static <T> T jsonStr2Obj2(String jsonStr,Class<T> type){
        return null;
    }

    public static void  obj2JsonStream(Object obj, OutputStream os) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(os,obj);
    }

    public static void main(String[] args) {
        String json = "{\"address\":\"address\",\"name\":\"haha\",\"id\":1,\"email\":\"email\",\"birthday\":\"birthday\"}";
        try {
            AccountBean acc = jsonStr2Obj(json, AccountBean.class);
            System.out.println(acc.getAddress());
            OutputStream os=new ByteArrayOutputStream();
            obj2JsonStream(acc,os);
            System.out.println(os.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
