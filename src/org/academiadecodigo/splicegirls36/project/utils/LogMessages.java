package org.academiadecodigo.splicegirls36.project.utils;

public final class LogMessages {

    public static final String FAILED_CONNECTION = "Failed connection to server: ";
    public static final String BIND_FAILED = "Failed to bind to port ";
    public static final String UNKNOWN_HOST = "The address you specified does not resolve to a known computer.";
    public static final String CLIENT_CONNECTED = "Client successfully connected to";
    public static final String ACCEPTED_CONNECTION = "Accepted connection from";
    public static final String CLIENT_CONNECTION_TIMED_OUT = "Player connection reach time limit";
    public static final String LISTENING_CONNECTIONS = "Lobby Stage: Waiting for connections from players up to " + Constants.MIN_PLAYERS + " for " + Constants.MAX_TIME_PER_CLIENT * Constants.MAX_PLAYERS / 1000  + " seconds.";
    public static final String LC_EXTRA_TIME = "Lobby Stage: Extra Time";
    public static final String STARTING_GAME = "Starting Game";
    public static final String SENDING_QUESTIONS = "Sending questions to each player.";

}
