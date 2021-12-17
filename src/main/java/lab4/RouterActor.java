package lab4;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.japi.pf.ReceiveBuilder;
import akka.routing.Router;

public class RouterActor extends AbstractActor {
    private Router router;
    private ActorRef storage;

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(GetMessage.class, msg ->)
    }
}
