package ru.aao.eventservice.common.util;

import ru.aao.eventservice.common.exception.ApplicationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class MessageUtils {
    public static String getStringFromInputStream(InputStream is) {

        StringBuilder sb = new StringBuilder();

        String line;
        try(BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException e) {
            throw new ApplicationException("Couldn't read from Input Stream : " + e.getLocalizedMessage());
        }
        return sb.toString();

    }
}
