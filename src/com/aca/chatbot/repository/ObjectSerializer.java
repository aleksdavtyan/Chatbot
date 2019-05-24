package com.aca.chatbot.repository;

import java.io.*;
import java.util.Base64;

public class ObjectSerializer {

    private static final ObjectSerializer objectSerializer = new ObjectSerializer();

    private ObjectSerializer() {
    }

    public static ObjectSerializer getInstance() {
        return objectSerializer;
    }

    public static String objectToString(Serializable obj) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(obj);
        oos.close();
        return Base64.getEncoder().encodeToString(baos.toByteArray());
    }

    public static Object ObjectFromString(String str) throws IOException, ClassNotFoundException {
        byte[] data = Base64.getDecoder().decode(str);
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data));
        Object obj = ois.readObject();
        ois.close();
        return obj;
    }
}

