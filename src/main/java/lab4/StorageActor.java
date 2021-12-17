package lab4;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StorageActor extends AbstractActor {
    private final Map<String, ArrayList<String>> storage = new HashMap<>();

    private void sendResult(GetMessage msg) {
        sender().tell();
    }

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(GetMessage.class, )
                .match(ResultMessage.class, )
                .build();
    }
}
