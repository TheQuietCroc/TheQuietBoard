package com.TheQuietCroc.TheQuietBoard;

import com.TheQuietCroc.TheQuietBoard.singletons.SparkServer;

import java.io.IOException;

public class Main {
    public static void main(String []args) {
        try {
            SparkServer.init();
        } catch (IOException e) {
            // Cannot read OAuth Client ID
            return;
        }
    }
}
