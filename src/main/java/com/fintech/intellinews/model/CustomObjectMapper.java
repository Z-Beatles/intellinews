package com.fintech.intellinews.model;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.web.util.HtmlUtils;

import java.io.IOException;

/**
 * @author waynechu
 * Created 2017-12-05 11:59
 */
public class CustomObjectMapper extends ObjectMapper {

    public CustomObjectMapper() {
        SimpleModule module = new SimpleModule("HTML XSS Serializer",
                new Version(1, 0, 0, "FINAL", "cn.waynechu", "ep-jsonmodule"));
        module.addSerializer(new JsonHtmlXssSerializer());
        this.registerModule(module);
    }

    class JsonHtmlXssSerializer extends JsonSerializer<String> {
        JsonHtmlXssSerializer() {
            super();
        }

        @Override
        public Class<String> handledType() {
            return String.class;
        }

        @Override
        public void serialize(String value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
                throws IOException {
            if (value != null) {
                String encodedValue = HtmlUtils.htmlEscape(value);
                jsonGenerator.writeString(encodedValue);
            }
        }
    }
}
