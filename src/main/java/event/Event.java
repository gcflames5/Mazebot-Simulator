package event;

import java.util.ArrayList;

public class Event {

    private static ArrayList<Listener> listeners = new ArrayList<Listener>();

    /**
     * Add a listener to the active listener pool
     *
     * @param listener Class that contains methods with @EventHandler tags
     */
    public static void addListener(Listener listener){
        listeners.add(listener);
    }

    /**
     * Remove a listener from the active listener pool
     *
     * @param listener
     */
    public static void removeListener(Listener listener){
        listeners.remove(listener);
    }

    /**
     * Get active listeners
     *
     * @return ArrayList<Listener> list of all registered listeners
     */
    public static ArrayList<Listener> getListeners(){
        return Event.listeners;
    }

    /**
     * Sends the event to all active listeners that handle that specific event type
     *
     * @param e
     */
    public static void callEvent(Event e){
        EventCaller.call(listeners, e);
    }

}