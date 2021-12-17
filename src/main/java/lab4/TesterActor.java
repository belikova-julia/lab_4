package lab4;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

public class TesterActor extends AbstractActor {
    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match()
                .build();
    }
}
