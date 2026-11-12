

package se.lexicon;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SubscriberDao {

    private final List<Subscriber> subscribers = new ArrayList<>();

    // Save a subscriber
    public void save(Subscriber subscriber) {
        // Update if ID already exists, otherwise add new
        findById(subscriber.getId()).ifPresentOrElse(
                existing -> {
                    int index = subscribers.indexOf(existing);
                    subscribers.set(index, subscriber);
                },
                () -> subscribers.add(subscriber)
        );
    }

    // Get all subscribers
    public List<Subscriber> findAll() {
        return new ArrayList<>(subscribers);
    }

    // Find subscriber by ID
    public Optional<Subscriber> findById(int id) {
        return subscribers.stream()
                .filter(s -> s.getId() == id)
                .findFirst();
    }
}
