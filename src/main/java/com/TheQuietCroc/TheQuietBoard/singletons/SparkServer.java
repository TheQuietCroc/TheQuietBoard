package com.TheQuietCroc.TheQuietBoard.singletons;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static spark.Spark.redirect;
import static spark.Spark.staticFileLocation;

/**
 * @author smarino
 */

public abstract class SparkServer {

    public static void init() throws IOException {
        final Properties mavenProps = new Properties();
        final InputStream in = new FileInputStream("dev.properties");

        mavenProps.load(in);

        final String OAUTH_CLIENT_ID = mavenProps.getProperty("oauth_id");

        staticFileLocation("/public");

        final StringBuilder streamerOauthPerms = new StringBuilder();
        streamerOauthPerms.append("channel:update:self");
        streamerOauthPerms.append("%20");
        streamerOauthPerms.append("channel:details:self");
        streamerOauthPerms.append("%20");
        streamerOauthPerms.append("interactive:robot:self");
        streamerOauthPerms.append("%20");
        streamerOauthPerms.append("interactive:manage:self");

        redirect.get("/streamer",
                "https://mixer.com/oauth/authorize" +
                        "?response_type=token" +
                        "&redirect_uri=http://localhost:4567/index.html" +
                        "&scope=" +
                        streamerOauthPerms.toString() +
                        "&client_id=" +
                        OAUTH_CLIENT_ID);

        final StringBuilder botOauthPerms = new StringBuilder();
        botOauthPerms.append("chat:connect");
        botOauthPerms.append("%20");
        botOauthPerms.append("chat:chat");

        redirect.get("/bot",
                "https://mixer.com/oauth/authorize" +
                        "?response_type=token" +
                        "&redirect_uri=http://localhost:4567/index.html" +
                        "&scope="
                        + botOauthPerms.toString() +
                        "&client_id=" +
                        OAUTH_CLIENT_ID);
    }
}
