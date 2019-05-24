package com.aca.chatbot;

import com.aca.chatbot.repository.RepositoryTools;
import com.aca.chatbot.ui.CommandLineUserInterface;

public class ChatBot {

    CommandLineUserInterface commandLineUserInterface = new CommandLineUserInterface();
    private static final String CHATBOT_REPO_PATH = "src\\com\\aca\\chatbot\\filesystem\\BotDb.txt";
    private static ChatBot chatBotInstance = new ChatBot();

    private ChatBot() {
    }

    public static ChatBot getChatBotInstance() {
        return chatBotInstance;
    }

    public boolean add(String message, String respond) {
        return RepositoryTools.getInstance().add(message, respond);
    }


    public void startChat() {

        commandLineUserInterface.output("Hi, I'm a Chat bot. Please enter your message or order.");
        String message = commandLineUserInterface.readStr();
        String respond = RepositoryTools.getInstance().searchRespond(message, CHATBOT_REPO_PATH);
        if (respond != null) {
            commandLineUserInterface.output(respond);
        }

        //ChatBot.getChatBotInstance().add("Hi", "Hi");
    }
}
