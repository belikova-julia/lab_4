package lab4;

import akka.actor.*;
import akka.japi.pf.DeciderBuilder;
import akka.japi.pf.ReceiveBuilder;
import akka.routing.ActorRefRoutee;
import akka.routing.RandomRoutingLogic;
import akka.routing.Routee;
import akka.routing.Router;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static akka.actor.SupervisorStrategy.restart;

public class RouterActor extends AbstractActor {
    private final Router router;
    private final ActorRef storage;

    private static final String STORAGE_NAME = "storage";
    private static final int NUM_WORKERS = 5;
    private static final int MAX_RETRIES = 10;
    private static final Duration DURATION = Duration.ofMinutes(1);

    private static SupervisorStrategy strategy = new OneForOneStrategy(
            MAX_RETRIES, DURATION, DeciderBuilder.matchAny(o -> restart()).build());

    public RouterActor() {
        storage = getContext().actorOf(Props.create(StorageActor.class), STORAGE_NAME);
        getContext().watch(storage);
        List<Routee> routees = new ArrayList<>();
        for (int i = 0; i < NUM_WORKERS; i++) {
            ActorRef tester = getContext().actorOf(Props.create(TesterActor.class));
            getContext().watch(tester);
            routees.add(new ActorRefRoutee(tester));
        }
        router = new Router(new RandomRoutingLogic(), routees);
    }

    public SupervisorStrategy supervisorStrategy() {
        return strategy;
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
