package org.yilmzmesut.readingapp.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateDeserializer extends JsonDeserializer<Date> {

    private static final SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");

    @Override
    public Date deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException {
        Date date = null;
        try {
            date = formatter.parse(p.getText());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}