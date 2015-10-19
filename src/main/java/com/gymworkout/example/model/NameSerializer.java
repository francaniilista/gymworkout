package com.gymworkout.example.model;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

import java.io.IOException;

/**
 * Created by pfranca on 9/29/2015.
 */
public class NameSerializer extends JsonSerializer<Name> {

    @Override
    public void serialize(Name name, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeFieldName("option");
        jsonGenerator.writeNumber(name.getOption());
        jsonGenerator.writeFieldName("label");
        jsonGenerator.writeString(name.getName());
        jsonGenerator.writeEndObject();
    }
}
