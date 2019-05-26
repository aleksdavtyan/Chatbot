package com.aca.chatbot;

import com.aca.chatbot.calculator.Calculator;
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
        boolean chatting = true;
        String message;
        commandLineUserInterface.output("Hi, I'm a Chat bot.\n--------------------------------" +
                "\n|Instruction| Type 'q' to quit.\nType your message or app name to start.\n--------------------------------");
        while (chatting) {
            message = commandLineUserInterface.readStr();
            if (!message.equals("q")) {
                switch (message) {
                    case "Calculator":
                        Calculator.getCalculatorInstance().startCalculator();
                        break;
                    case "TicTacToe":

                        break;
                    default:
                        break;
                }
                String respond = RepositoryTools.getInstance().searchRespond(message, CHATBOT_REPO_PATH);
                if (respond != null) {
                    commandLineUserInterface.output(respond);
                }
            } else {
                chatting = false;
            }
        }
    }
}
