package engine.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class EventBus {

    private static final Map<Class<? extends GameEvent>, List<Consumer<GameEvent>>> subscribers = new HashMap<>();

    @SuppressWarnings("unchecked")
    public static <T extends GameEvent> void subscribe(Class<T> eventClass, Consumer<T> action){
        subscribers.putIfAbsent(eventClass, new ArrayList<>());
        Consumer<GameEvent> genericAction = (Consumer<GameEvent>) action;
        subscribers.get(eventClass).add(genericAction);
    }

    public static void add(GameEvent event){
        Class<? extends GameEvent> eventClass = event.getClass();

        List<Consumer<GameEvent>> actions = subscribers.get(eventClass);

        if (actions != null){
            for (Consumer<GameEvent> action : actions){
                action.accept(event);
            }
        }
    }
}
