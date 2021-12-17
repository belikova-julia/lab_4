package lab4;

import akka.actor.ActorRef;
import akka.http.javadsl.marshallers.jackson.Jackson;
import akka.http.javadsl.server.Route;
import akka.pattern.Patterns;
import akka.util.Timeout;
import scala.concurrent.Future;

import java.time.Duration;

import static akka.http.javadsl.server.Directives.*;

public class HttpParse {
    private final ActorRef router;
    private static final String SUCCESS_MSG = "";
    private final static Timeout TIMEOUT = Timeout.create(Duration.ofSeconds(5));

    public HttpParse(ActorRef routerActor) {
        this.router = routerActor;
    }

    public Route createRoute() {
        return route(
                get(() -> parameter("packageId", (Id) -> {
                    Future<Object> future = Patterns.ask(router, new GetMessage(Id), TIMEOUT);
                    return completeOKWithFuture(future, Jackson.marshaller());
                })),
                post(() -> entity(Jackson.unmarshaller(), msg -> {
                    router.tell(msg, ActorRef.noSender());
                    return complete(SUCCESS_MSG);
                }))
        );
    }
}
