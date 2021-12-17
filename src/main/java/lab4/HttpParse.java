package lab4;

import akka.actor.ActorRef;
import akka.http.javadsl.server.Route;

public class HttpParse {
    private final ActorRef router;

    public HttpParse(ActorRef routerActor) {
        this.router = routerActor;
    }

    public Route createRoute() {
        return route()
    }
}
