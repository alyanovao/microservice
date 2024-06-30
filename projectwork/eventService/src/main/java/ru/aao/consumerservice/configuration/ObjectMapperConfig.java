package ru.aao.consumerservice.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.fasterxml.jackson.datatype.joda.cfg.JacksonJodaDateFormat;
import com.fasterxml.jackson.datatype.joda.deser.DateTimeDeserializer;
import com.fasterxml.jackson.datatype.joda.ser.DateTimeSerializer;
import org.joda.time.ReadableInstant;
import org.joda.time.format.DateTimeFormat;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ObjectMapperConfig {

    @Bean
    @Primary
    public ObjectMapper objectMapper() {
        JacksonJodaDateFormat jacksonJodaDateFormat = new JacksonJodaDateFormat(DateTimeFormat.forPattern("yyyy-mm-dd'T'HH:mm:ss.SSSZZ"));
        return JsonMapper
                .builder()
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
                .addModule(new JodaModule()
                    // If you do not want to customize the formatting, you can remove the next two lines
                    .addSerializer(new DateTimeSerializer(jacksonJodaDateFormat))
                    .addDeserializer(ReadableInstant.class,
                            new DateTimeDeserializer(ReadableInstant.class, jacksonJodaDateFormat))
                )
                .enable(SerializationFeature.INDENT_OUTPUT)
                .build();
    }
}
