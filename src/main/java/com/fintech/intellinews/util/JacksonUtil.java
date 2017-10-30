package com.fintech.intellinews.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.NullNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fintech.intellinews.AppException;
import com.fintech.intellinews.Constant;
import org.apache.commons.lang3.ArrayUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author waynechu
 * Created 2017-10-18 16:22
 */
public class JacksonUtil {

    private JacksonUtil() {
    }

    public static final JsonSerializer<AppException> JSON_SERIALIZER_APPEXCEPTION = new JsonSerializer<AppException>() {
        @Override
        public Class<AppException> handledType() {
            return AppException.class;
        }

        @Override
        public void serialize(AppException data, JsonGenerator jgen, SerializerProvider provider) throws IOException {
            jgen.writeString(data.getClass().getName());
        }
    };

    public static <T> T toJavaObjectFromString(ObjectMapper objectMapper, String str, Class<T> clazz) {
        if (str == null) {
            return null;
        }
        try {
            return objectMapper.readValue(str, clazz);
        } catch (IOException e) {
            throw new AppException(Constant.ERRORCODE_CONVERT_STR2JAVAERROR, "字符串转换Java对象失败,源[" + str + "]", e);
        }
    }

    public static <T> List<T> toJavaListFromString(ObjectMapper objectMapper, String str, Class<T> clazz) {
        if (str == null) {
            return Collections.emptyList();
        }
        try {
            JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, clazz);
            return objectMapper.readValue(objectMapper.getFactory().createParser(str), javaType);
        } catch (IOException e) {
            throw new AppException(Constant.ERRORCODE_CONVERT_STR2JAVAERROR, "字符串转换Java列表失败,源[" + str + "]", e);
        }
    }

    public static String toStringFromJavaObject(ObjectMapper objectMapper, Object value) {
        if (value == null) {
            return null;
        }
        try {
            return objectMapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            throw new AppException(Constant.ERRORCODE_CONVERT_JAVA2STRERROR, "Java对象转换字符串失败,源[" + value + "]", e);
        }
    }

    public static String toStringFromJsonNode(ObjectMapper objectMapper, JsonNode value) {
        if (value == null) {
            return null;
        }
        try {
            return objectMapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            throw new AppException(Constant.ERRORCODE_CONVERT_JSON2STRERROR, "Json对象转换字符串失败,源[" + value + "]", e);
        }
    }

    public static ObjectNode toObjectNodeFromString(ObjectMapper objectMapper, String str) {
        if (str == null) {
            return null;
        }
        try {
            return objectMapper.readValue(str, ObjectNode.class);
        } catch (IOException e) {
            throw new AppException(Constant.ERRORCODE_CONVERT_STR2OBJECTNODEERROR, "字符串转换ObjectNode格式失败,源[" + str +
                    "]", e);
        }
    }

    public static ArrayNode toArrayNodeFromString(ObjectMapper objectMapper, String str) {
        if (str == null) {
            return null;
        }
        try {
            return objectMapper.readValue(str, ArrayNode.class);
        } catch (IOException e) {
            throw new AppException(Constant.ERRORCODE_CONVERT_STR2ARRAYNODEERROR, "字符串转换ArrayNode格式失败,源[" + str +
                    "]", e);
        }
    }

    public static <T> T toJavaObjectFromObjectNode(ObjectMapper objectMapper, ObjectNode value, Class<T> clazz) {
        if (value == null) {
            return null;
        }
        try {
            return objectMapper.readValue(objectMapper.treeAsTokens(value), clazz);
        } catch (IOException e) {
            throw new AppException(Constant.ERRORCODE_CONVERT_JSON2JAVAERROR, "Json格式转换Java对象失败,源[" + value + "]", e);
        }
    }

    public static <T> List<T> toJavaListFromArrayNode(ObjectMapper objectMapper, ArrayNode value, Class<T> clazz) {
        if (value == null) {
            return Collections.emptyList();
        }
        try {
            JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, clazz);
            return objectMapper.readValue(objectMapper.treeAsTokens(value), javaType);
        } catch (IOException e) {
            throw new AppException(Constant.ERRORCODE_CONVERT_JSON2JAVAERROR, "Json数组转换Java列表失败,源[" + value + "]", e);
        }
    }

    public static ObjectNode toObjectNodeFromJavaObject(ObjectMapper objectMapper, Object value) {
        if (value == null) {
            return null;
        }
        try {
            return objectMapper.valueToTree(value);
        } catch (IllegalArgumentException e) {
            throw new AppException(Constant.ERRORCODE_CONVERT_JAVA2JSONERROR, "Java格式转换Json对象失败,源[" + value + "]", e);
        }
    }

    public static Long getChildAsLong(ObjectNode node, String... fields) {
        if (fields.length > 1) {
            return getChildAsLong(getChildAsObjectNode(node, ArrayUtils.subarray(fields, 0, fields.length - 1)),
                    fields[fields.length - 1]);
        }
        return (node != null && node.has(fields[0])) ? node.get(fields[0]).longValue() : null;
    }

    public static String getChildAsString(ObjectNode node, String... fields) {
        if (fields.length > 1) {
            return getChildAsString(getChildAsObjectNode(node, ArrayUtils.subarray(fields, 0, fields.length - 1)),
                    fields[fields.length - 1]);
        }
        return (node != null && node.has(fields[0])) ? node.get(fields[0]).textValue() : null;
    }

    public static Boolean getChildAsBoolean(ObjectNode node, String... fields) {
        if (fields.length > 1) {
            return getChildAsBoolean(getChildAsObjectNode(node, ArrayUtils.subarray(fields, 0, fields.length - 1)),
                    fields[fields.length - 1]);
        }
        return (node != null && node.has(fields[0])) ? node.get(fields[0]).booleanValue() : null;
    }

    public static Integer getChildAsInteger(ObjectNode node, String... fields) {
        if (fields.length > 1) {
            return getChildAsInteger(getChildAsObjectNode(node, ArrayUtils.subarray(fields, 0, fields.length - 1)),
                    fields[fields.length - 1]);
        }
        return (node != null && node.has(fields[0])) ? node.get(fields[0]).intValue() : null;
    }

    public static String[] getChildAsStringArray(ObjectNode node, String... fields) {
        if (fields.length > 1) {
            return getChildAsStringArray(getChildAsObjectNode(node, ArrayUtils.subarray(fields, 0, fields.length - 1)
            ), fields[fields.length - 1]);
        }
        List<String> list = new ArrayList<>();
        if (node != null && node.has(fields[0])) {
            ArrayNode arr = node.withArray(fields[0]);
            arr.forEach(x -> list.add(x.textValue()));
        }
        return list.toArray(new String[list.size()]);
    }

    public static ArrayNode getChildAsArrayNode(ObjectNode node, String... fields) {
        if (fields.length > 1) {
            return getChildAsArrayNode(getChildAsObjectNode(node, ArrayUtils.subarray(fields, 0, fields.length - 1)),
                    fields[fields.length - 1]);
        }
        return (node != null && node.has(fields[0])) ? (ArrayNode) node.get(fields[0]) : null;
    }

    public static ObjectNode getChildAsObjectNode(ObjectNode node, String... fields) {
        for (String fieldName : fields) {
            if (node != null && node.has(fieldName)) {
                Object child = node.get(fieldName);
                if (child == null || child instanceof NullNode) {
                    return null;
                } else {
                    node = (ObjectNode) child;
                }
            } else {
                return null;
            }
        }
        return node;
    }
}
