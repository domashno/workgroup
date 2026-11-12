package se.lexicon;

public class SubscriptionRules {

    // Rule 1: Active subscribers
    public static SubscriberFilter activeSubscriber = s->s.isActive();

    //Rule 2: Expiring Subscription
    //Matches subscribers with 0 or 1 month remaining.
    public static SubscriberFilter expiringSubscription = s -> s.getMonthsRemaining() == 0
                    || s.getMonthsRemaining() == 1;

    //Rule 3: Active and Expiring Subscriber
    //Matches subscribers who are active and whose subscription is about to expire.
    public static SubscriberFilter activeAndExpiring =
            s -> activeSubscriber.matches(s) && expiringSubscription.matches(s);

    // Rule 4: Subscriber by Plan
    //Matches subscribers based on their subscription plan (FREE, BASIC, or PRO).
    public static SubscriberFilter freePlan =
            s -> s.getPlan() == Plan.FREE;

    public static SubscriberFilter basicPlan =
            s -> s.getPlan() == Plan.BASIC;

    public static SubscriberFilter proPlan =
            s -> s.getPlan() == Plan.PRO;

    //Rule 5: Paying Subscriber
    //Matches subscribers with a paid plan (BASIC or PRO).

    public static SubscriberFilter payingSubscriber = s -> basicPlan.matches(s) || proPlan.matches(s);

    //To avoid repetition we can write rule 4 and 5 like this.But i follow the approach which we have learned
    /* Rule 4
    public static SubscriberFilter byPlan(Plan plan) {
        return s -> s.getPlan() == plan;
    }

     Rule 5
    //public static final SubscriberFilter payingSubscribers = s -> byPlan(Plan.BASIC).matches(s)
             || byPlan(Plan.PRO).matches(s);
    */


    //Rule 6:Extend Subscription
    //Increases the remaining subscription period for a subscriber.
    // Example: increase by 1 month
    public static SubscriberAction extendSubscription = s -> s.setMonthsRemaining(s.getMonthsRemaining() + 1);

    // Rule 7: Deactivate Subscriber
    //Marks a subscriber as inactive.
    public static SubscriberAction deactivateSubscriber =
            s -> s.setActive(false);





}
