package demo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.SimpleDateFormat;

/**
 * @author Eric
 * Date 2017/5/4 11:20
 * Desc
 */
public abstract class Base {
    static final Gson GSON;
    protected static final ObjectMapper OBJECT_MAPPER;

    static {
        GSON = new GsonBuilder().serializeNulls().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

        OBJECT_MAPPER = new ObjectMapper();
        OBJECT_MAPPER.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        // 设置输出时包含属性的风格
        OBJECT_MAPPER.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        // 禁止使用int代表Enum的order()來反序列化Enum,非常危險
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS, true);
        // 设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        OBJECT_MAPPER.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }
}
