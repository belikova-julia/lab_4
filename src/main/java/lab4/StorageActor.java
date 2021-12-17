package lab4;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StorageActor extends AbstractActor {
    private final Map<String, ArrayList<String>> storage = new HashMap<>();

    private void sendResults(GetMessage msg) {
        sender().tell(
                new AllResultsMessage(msg.getPackageId(), storage.get(msg.getPackageId())),
                getContext().parent()
        );
    }

    private void save(ResultMessage msg) {
        ArrayList<String> results = storage.get(msg.getPackageId());
        if (results != null) {
            results.add(msg.getResult());
        } else {
            results = new ArrayList<>();
            results.add(msg.getResult());
            storage.put(msg.getPackageId(), results);
        }
    }

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(GetMessage.class, this::sendResults)
                .match(ResultMessage.class, this::save)
                .build();
    }
}
