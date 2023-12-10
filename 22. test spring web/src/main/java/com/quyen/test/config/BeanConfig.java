package com.quyen.test.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.gson.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Configuration
public class BeanConfig {
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_USING_DEFAULT_VALUE);
        mapper.enable(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL);
        mapper.enable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        JavaTimeModule module = new JavaTimeModule();
//        module.addSerializer(LOCAL_DATETIME_SERIALIZER);
        mapper.registerModule(module);
        return mapper;
    }

    @Bean
    public Gson gson() {
        return new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new JsonSerializer<LocalDateTime>() {
                    @Override
                    public JsonElement serialize(LocalDateTime dateTime, Type type, JsonSerializationContext jsonSerializationContext) {
                        return new JsonPrimitive(dateTime.format(DateTimeFormatter.ISO_DATE_TIME)); //'yyyy-MM-ddTHH:mm:ss.SSSZ'
                    }
                })
                .registerTypeAdapter(LocalDate.class, new JsonDeserializer<>() {

                    @Override
                    public Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext)
                            throws JsonParseException {
                        return LocalDateTime.parse(jsonElement.getAsJsonPrimitive().getAsString(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                    }
                })
                .create();
    }


}
