package com.aca.chatbot.ui;

public abstract class UserInterface {
    /**
     * Send info to user.
     *
     * @param message the message
     * @return true if success.
     */
    public abstract boolean output(String message);

    /**
     * Get values from user.
     *
     * @return the user input line;
     */
    public abstract String readStr();

    /**
     * Get values from user.
     *
     * @return the user input number;
     */
    public abstract int readInt();
}
