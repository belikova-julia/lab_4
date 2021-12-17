package lab4;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class TesterActor extends AbstractActor {
    private String test(TestMessage msg) {
        String result;
        try {
            ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
            engine.eval(msg.getJsScript());
            Invocable invocable = (Invocable) engine;
            result = invocable.invokeFunction(msg.getFunctionName(), msg.getParams().toArray()).toString();
        } catch (Exception e) {

        }
        if (result.equals(msg.getExpectedResult()))
            return String.format("%s - SUCCESS, result - %s", msg.getTestName(), result);
        else
            return String.format("%s - FAIL, result - %s, expected - %s",
                    msg.getTestName(), result, msg.getExpectedResult());
    }
    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(TestMessage.class, )
                .build();
    }
}
