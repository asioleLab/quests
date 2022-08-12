package org.chat.server;

import org.chat.server.utils.TestClient;
import org.junit.jupiter.api.*;

import java.net.ServerSocket;


/**
 * ChatServer Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>ago 11, 2022</pre>
 */
public class ChatServerTest {

    private static ChatServer chatServer;

    @BeforeAll
//    public void before() throws Exception {
    static void initEach() {
        chatServer = new ChatServer();
    }


    @Nested
    class StartTest {
        @Test
        public void testStopped() throws Exception {
            if (chatServer.isStarted())
                Assertions.assertDoesNotThrow(() -> chatServer.stop(),
                        "Failed to stop");
            Assertions.assertFalse(chatServer.isStarted());
        }

//        @Test
//        public void testStarting() throws Exception {
//            if(!chatServer.isStarted())
//
//            chatServer.stop();
//        }

        @Test
        public void testStarted() throws Exception {
            if (!chatServer.isStarted())
                Assertions.assertDoesNotThrow(() -> chatServer.start(),
                        "Failed to start");
            Assertions.assertTrue(chatServer.isStarted());
        }
    }

    @Nested
    class ClientTest {
        @Test
        public void testConnection() throws Exception {
            if (!chatServer.isStarted())
                Assertions.assertDoesNotThrow(() -> chatServer.start(),
                        "Failed to start");
            TestClient testClient = new TestClient();
            Assertions.assertDoesNotThrow(() -> testClient.startConnection("127.0.0.1", 10000),
                    "Failed to start");
            Assertions.assertTrue(testClient.isConnected());

        }


    }


}
