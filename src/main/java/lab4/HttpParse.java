package lab4;

import akka.actor.ActorRef;
import akka.http.javadsl.marshallers.jackson.Jackson;
import akka.http.javadsl.server.Route;
import akka.pattern.Patterns;
import scala.concurrent.Future;
import static akka.http.javadsl.server.Directives.*;

public class HttpParse {
    private final ActorRef router;
    private static final String SUCCESS_MSG = "";

    public HttpParse(ActorRef routerActor) {
        this.router = routerActor;
    }

    public Route createRoute() {
        return route(
                get(() -> parameter("packageID", (ID) -> {
                    Future<Object> future = Patterns.ask(router, );
                    return completeOKWithFuture(future, Jackson.marshaller());
                })),
                post(() -> entity(Jackson.unmarshaller(), msg -> {
                    router.tell(msg, ActorRef.noSender());
                    return complete(SUCCESS_MSG);
                }))
        );
    }
}
