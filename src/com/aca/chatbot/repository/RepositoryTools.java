package com.aca.chatbot.repository;

import com.aca.chatbot.ui.CommandLineUserInterface;

import java.io.*;
import java.util.Scanner;

public class RepositoryTools extends Repository {

    CommandLineUserInterface commandLineUserInterface = new CommandLineUserInterface();
    private static final String CHATBOT_REPO_PATH = "src\\com\\aca\\chatbot\\filesystem\\BotDb.txt";
    private static final String SEPARATOR = "-";
    private static final String NEW_LINE = "\r\n";

    private static RepositoryTools repositoryToolsInstance = new RepositoryTools();

    public static RepositoryTools getInstance() {
        return repositoryToolsInstance;
    }

    public static String getChatbotRepoPath() {
        return CHATBOT_REPO_PATH;
    }

    private RepositoryTools() {
    }

    @Override
    public void put(String message, String respond, String fileName) {
        try {
            FileWriter writer = new FileWriter(fileName, true); // append true is writing from next position
            writer.append(message + SEPARATOR + respond + NEW_LINE);
            System.out.println("Successfully wrote to the file.");
            writer.flush(); //method flushes the stream.
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object get(String key, String fileName) {
        try {
            File file = new File(fileName);
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] parts = data.split(SEPARATOR);
                if (key.equals(parts[0])) {
                    Object obj = ObjectSerializer.ObjectFromString(parts[1]);
                    return obj;
                }
            }
            myReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean containsKey(String message, String fileName) {
        try {
            File file = new File(fileName);
            Scanner reader = new Scanner(file);
            if (file.length() != 0) {
                while (reader.hasNextLine()) {
                    String data = reader.nextLine();
                    String[] parts = data.split(SEPARATOR);
                    if (message.equals(parts[0])) {
                        return true;
                    }
                }
            } else {
                commandLineUserInterface.output("The repository is empty.");
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


    public boolean add(String message, String respond) {
        if (!containsKey(message, CHATBOT_REPO_PATH)) {
            put(message, respond, CHATBOT_REPO_PATH);
            return true;
        }
        return false;
    }

//    public boolean ask(User user) {
//        if (containsKey(user.getUsername())) {
//            User userObj = (User) RepositoryTools.getInstance().get(user.getUsername(), USER_REPO_PATH);
//            if (user.getUsername().equals(userObj.getUsername()) && user.getPassword().equals(userObj.getPassword()))
//                return true;
//        }
//        return false;
//    }

    public String searchRespond(String message, String fileName) {
        if (containsKey(message, CHATBOT_REPO_PATH)) {
            try {
                File file = new File(fileName);
                Scanner myReader = new Scanner(file);
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    String[] parts = data.split(SEPARATOR);
                    if (message.equals(parts[0]))
                        return parts[1];
                }
                myReader.close();
            } catch (FileNotFoundException e) {
                commandLineUserInterface.output("An error occurred.");
                e.printStackTrace();
            }
        } else {
            commandLineUserInterface.output("Please enter the Respond:");
            String respond = commandLineUserInterface.readStr();
            add(message, respond);
        }
        return null;
    }

}
