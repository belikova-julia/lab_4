package lab4;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.http.javadsl.Http;
import akka.routing.RouterActor;
import akka.stream.ActorMaterializer;

public class TestApp {
    private static final String SYSTEM_NAME = "testing";

    public static void main(String[] args) throws Exception {
        ActorSystem system = ActorSystem.create(SYSTEM_NAME);
        ActorRef routerActor = system.actorOf(Props.create(RouterActor.class), "router");
        final Http http = Http.get(system);
        final ActorMaterializer materializer = ActorMaterializer.create(system);
        HttpParse instance = new HttpParse(routerActor);
    }
}
