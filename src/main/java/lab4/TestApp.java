package lab4;

import akka.actor.ActorSystem;

public class TestApp {
    private static final String SYSTEM_NAME = "testing";

    public static void main(String[] args) throws Exception {
        ActorSystem system = ActorSystem.create(SYSTEM_NAME);
        
    }
}
