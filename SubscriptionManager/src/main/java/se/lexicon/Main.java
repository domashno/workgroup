package se.lexicon;

import java.util.List;

public class Main {

	public static void main(String[] args) {

		SubscriberDAO dao = new SubscriberDAO();
		SubscriberProcessor processor = new SubscriberProcessor();

		dao.save(new Subscriber(1, "alice@example.com", Plan.PRO, true, 6));
		dao.save(new Subscriber(2, "bob@example.com", Plan.BASIC, true, 1));
		dao.save(new Subscriber(3, "carol@example.com", Plan.FREE, false, 0));
		dao.save(new Subscriber(4, "dave@example.com", Plan.BASIC, true, 0));
		dao.save(new Subscriber(5, "eve@example.com", Plan.PRO, false, 1));

		List<Subscriber> subscriberList = dao.findAll();

		System.out.println("ALL SUBSCRIBERS:");
		subscriberList.forEach(System.out::println);

		System.out.println("\nACTIVE SUBSCRIBERS:");
		subscriberList.stream().filter(Subscriber::isActive).forEach(System.out::println);

		System.out.println("\nEXPIRING SUBSCRIPTIONS:");
		subscriberList.stream().filter(s -> s.getMonthsRemaining() <= 1).forEach(System.out::println);

		System.out.println("\nACTIVE AND EXPIRING SUBSCRIBERS:");
		subscriberList.stream().filter(s -> s.isActive() && s.getMonthsRemaining() <= 1).forEach(System.out::println);

		System.out.println("\nPRO SUBSCRIBERS:");
		subscriberList.stream().filter(s -> s.getPlan() == Plan.PRO).forEach(System.out::println);

		System.out.println("\nPAYING SUBSCRIBERS:");
		subscriberList.stream().filter(s -> s.getPlan() != Plan.FREE).forEach(System.out::println);


        /* FIND SUBSCRIBERS DEMONSTRATION */
		System.out.println("\n========== FIND SUBSCRIBERS DEMONSTRATION ==========");

		//Find all active subscribers
		System.out.println("\nACTIVE SUBSCRIBERS:");
		processor.findSubscribers(subscriberList, SubscriptionRules.activeSubscriber)
				 .forEach(System.out::println);


		//Find expiring subscriptions
		System.out.println("\nEXPIRING SUBSCRIPTIONS:");
		processor.findSubscribers(subscriberList, SubscriptionRules.expiringSubscription)
				 .forEach(System.out::println);


       //Find active and expiring subscribers
		System.out.println("\nACTIVE AND EXPIRING SUBSCRIBERS:");
		processor.findSubscribers(subscriberList, SubscriptionRules.activeAndExpiring)
				 .forEach(System.out::println);


       //Find subscribers with FREE plan
		System.out.println("\nFREE PLAN SUBSCRIBERS:");
		processor.findSubscribers(subscriberList, SubscriptionRules.freePlan)
				 .forEach(System.out::println);


        //Find subscribers with BASIC plan
		System.out.println("\nBASIC PLAN SUBSCRIBERS:");
		processor.findSubscribers(subscriberList, SubscriptionRules.basicPlan)
				 .forEach(System.out::println);


        //Find subscribers with PRO plan
		System.out.println("\nPRO PLAN SUBSCRIBERS:");
		processor.findSubscribers(subscriberList, SubscriptionRules.proPlan)
				 .forEach(System.out::println);


        //Find paying subscribers (BASIC or PRO)
		System.out.println("\nPAYING SUBSCRIBERS:");
		processor.findSubscribers(subscriberList, SubscriptionRules.payingSubscriber)
				 .forEach(System.out::println);


		//Extend subscriptions of active and expiring subscribers
		processor.applyToMatching(
				subscriberList,
				SubscriptionRules.activeAndExpiring,
				SubscriptionRules.extendSubscription
		);

		subscriberList.forEach(System.out::println);


       //Deactivate all FREE plan subscribers
		System.out.println("\nDEACTIVATING FREE PLAN SUBSCRIBERS:");

		processor.applyToMatching(
				subscriberList,
				SubscriptionRules.freePlan,
				SubscriptionRules.deactivateSubscriber
		);

		subscriberList.forEach(System.out::println);
	}

}

