
package se.lexicon;

import java.util.List;
import java.util.stream.Collectors;

public class SubscriberProcessor {

    // Method 1: Find subscribers that match a filter
    public List<Subscriber> findSubscribers(
            List<Subscriber> list,
            SubscriberFilter filter) {

        return list.stream()
                .filter(filter::matches)
                .collect(Collectors.toList());
    }

    // Method 2: Apply an action to matching subscribers
    public List<Subscriber> applyToMatching(
            List<Subscriber> list,
            SubscriberFilter filter,
            SubscriberAction action) {

        List<Subscriber> matchingSubscribers =
                findSubscribers(list, filter);

        matchingSubscribers.forEach(action::run);

        return matchingSubscribers;
    }
}
