package com.sri.connectivity.wifi;

import java.net.ServerSocket;

import com.sri.mousepointer.actions.MouseXY;

public class MouseServerListener {

    private static final int PORT = 9001;

    public static void main(String[] args) throws Exception {
        System.out.println("Touch Screen Locator driver running.");
        new MouseXY();
        ServerSocket listener = new ServerSocket(PORT);
        try {
            while (true) {
                new MouseEventsDelegator(listener.accept()).start();
            }
        } finally {
            listener.close();
        }
    }
}