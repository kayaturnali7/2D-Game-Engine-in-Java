package engine.event;

import java.util.HashMap;

public class SignalManager {

    private static final HashMap<String, GameEvent> signals = new HashMap<>();

    public static void addSignal(String name, GameEvent event){
        signals.put(name, event);
    }



}
