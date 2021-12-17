package lab4;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.japi.pf.ReceiveBuilder;
import akka.routing.Routee;
import akka.routing.Router;

import java.util.ArrayList;
import java.util.List;

public class RouterActor extends AbstractActor {
    private Router router;
    private ActorRef storage;

    private static final String STORAGE_NAME = "storage";
    private static final int NUM_WORKERS = 5;

    public RouterActor() {
        storage= getContext().actorOf(Props.create(StorageActor.class), STORAGE_NAME);
        getContext().watch(storage);

        List<Routee> routees = new ArrayList<Routee>();
        for (int i = 0; i < NUM_WORKERS; i++) {
            ActorRef 
        }
    }

    public void test(PackageData data) {
        for (TestData t : data.getTests()) {
            router.route(
                    new TestMessage(
                            data.getPackageId(),
                            data.getFunctionName(),
                            data.getJsScript(),
                            t.getTestName(),
                            t.getExpectedResult(),
                            t.getParams()
                            ), storage);
        }
    }

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(GetMessage.class, msg -> storage.tell(msg, sender()))
                .match(PackageData.class, this::test)
                .build();
    }
}
