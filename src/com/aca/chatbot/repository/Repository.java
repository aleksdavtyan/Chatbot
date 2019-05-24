package com.aca.chatbot.repository;

import java.io.IOException;
import java.io.Serializable;

public abstract class Repository<Key extends Serializable, V extends Serializable> {

    public abstract void put(String message, String respond, String fileName) throws IOException;

    public abstract Object get(String key, String fileName) throws IOException, ClassNotFoundException;
}
