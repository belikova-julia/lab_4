package lab4;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

public class TesterActor extends AbstractActor {
    private String test(TestMessage msg) {
        
    }
    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(TestMessage.class, )
                .build();
    }
}
