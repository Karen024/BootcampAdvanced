import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.net.Socket;
import java.util.Iterator;
import java.util.List;

public class ServerService extends Thread {

    private final Socket client;
    private Server server;
    private String login;
    private OutputStream outputStream;

    public ServerService(Server server, Socket client) {
        this.client = client;
        this.server = server;
    }

    @Override
    public void run() {
        try {
            clientHandling();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void clientHandling() throws IOException {
        InputStream inputStream = client.getInputStream();
        this.outputStream = client.getOutputStream();
        String line;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        while ((line = bufferedReader.readLine()) != null) {
            String[] tokens = StringUtils.split(line);
            if (tokens != null && tokens.length > 0) {
                String cmd = tokens[0];
                if ("logoff".equalsIgnoreCase(cmd) || "quit".equalsIgnoreCase(cmd)) {
                    handleLogOff();
                    break;
                } else if ("login".equalsIgnoreCase(cmd)) {
                    handleLogin(outputStream, tokens);
                } else if ("message".equalsIgnoreCase(cmd)) {
                    String[] tokensWithText = StringUtils.split(line,null,3);
                    handleMessage(tokensWithText);
                } else {
                    outputStream.write("unknown command\n".getBytes());
                }
            }
        }
        client.close();
    }

    private void handleMessage(String[] tokens) throws IOException {
        String sendTo = tokens[1];
        String text = tokens[2];
        List<ServerService> users = server.getListOfServices();
        for (ServerService user : users) {
            if (sendTo.equalsIgnoreCase(user.getLogin())) {
                String outputMessage = "message " + login + " " + text + "\n";
                user.send(outputMessage);
            }
        }
    }

    private void handleLogOff() throws IOException {
        server.removeLoggedOffFromList(this);
        List<ServerService> list = server.getListOfServices();
        list.forEach(serverService -> {
            try {
                if (!login.equals(serverService.getLogin())) {
                    serverService.send("offline" + login + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        client.close();
    }

    private void handleLogin(OutputStream outputStream, String[] tokens) throws IOException {
        if (tokens.length == 3) {
            String login = tokens[1];
            String password = tokens[2];
            if ((login.equals("guest") && password.equals("guest")) || (login.equals("jim") && password.equals("jim"))) {
                outputStream.write("ok you are logged in\n".getBytes());
                this.login = login;
                System.out.println("User " + login + " logged in successfully\n");
                List<ServerService> list = server.getListOfServices();

                //send current user all other logins
                list.forEach(serverService -> {
                    try {
                        if (!login.equals(serverService.getLogin())) {
                            if (serverService.getLogin() != null) {
                                send("online" + serverService.getLogin() + "\n");
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                //send other logins current user login
                list.forEach(serverService -> {
                    try {
                        if (!login.equals(serverService.getLogin())) {
                            serverService.send("online" + login + "\n");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            } else {
                outputStream.write("access denied\n".getBytes());
            }
        }
    }

    private void send(String message) throws IOException {
        if (login != null) {
            outputStream.write(message.getBytes());
        }
    }

    public String getLogin() {
        return login;
    }
}
