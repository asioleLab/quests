package org.chat.server;

import org.chat.server.utils.TestClient;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;


/**
 * ChatServer Tester.
 *
 * @author asiole
 * @version 1.0
 * @since <pre>ago 11, 2022</pre>
 */
public class ChatServerTest {

    private static ChatServer chatServer;

    @BeforeAll
    static void initChatServer() {
        chatServer = new ChatServer();
        Runnable r = new Runnable() {
            @Override
            public void run() {

                try {

                    if (!chatServer.isStarted()) {
                        Assertions.assertDoesNotThrow(() -> chatServer.start(),
                                "Failed to start");
                    }
                    chatServer.runMe();
                } catch (SocketException ex) {
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        new Thread(r).start();
    }

    @AfterAll
    static void quitChatServer() {
        if (chatServer.isStarted())
            chatServer.stop();
    }


    @Nested
    class StartTest {
        @Test
        public void testStopped() throws Exception {
            if (chatServer.isStarted()) {
                Assertions.assertDoesNotThrow(() -> chatServer.stop(),
                        "Failed to stop");
            }
            Assertions.assertFalse(chatServer.isStarted());
        }

        @Test
        public void testStarted() throws Exception {
            Assertions.assertTrue(chatServer.isStarted());
        }
    }

    @Nested
    class ClientTest {
        @Test
        public void testConnection() throws Exception {

            TestClient testClient = new TestClient();
            Assertions.assertDoesNotThrow(() -> testClient.startConnection("127.0.0.1", 10000),
                    "Failed to start");
            Assertions.assertTrue(testClient.isConnected());
            Assertions.assertEquals(1 ,chatServer.getAllUsers().size());

        }

        @Test
        public void testMessage() throws IOException, InterruptedException {
            TestClient client = new TestClient();

            if (chatServer.isStarted()) {
                client.startConnection("127.0.0.1", 10000);
                if (chatServer.getAllUsers().size() >= 0) {
                    List<ChatMessage> chatMessage = chatServer.getChatMessage();
                    Assertions.assertEquals(0, chatMessage.size());
                    String response = client.sendMessage("Test");
                    response = client.sendMessage("Hello, you're welcome");
                    Thread.sleep(1000); //latency
                    Assertions.assertEquals(1, chatMessage.size());
                    Assertions.assertEquals("Test", chatServer.getChatMessage().stream().map(el -> el.sender).findFirst().get());
                    Assertions.assertEquals("Hello, you're welcome", chatServer.getChatMessage().stream().map(el -> el.message).findFirst().get());

                }
            }
        }


    }


}
