package com.aca.chatbot.ui;

public class GraphicalUserInterface extends UserInterface {

    @Override
    public boolean output(String message) {
        return false;
    }

    @Override
    public String readStr() {
        return null;
    }

    @Override
    public int readInt() {
        return 0;
    }

}