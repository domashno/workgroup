import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import se.lexicon.Plan;
import se.lexicon.Subscriber;
import se.lexicon.SubscriberDao;
import se.lexicon.SubscriberFilter;
import se.lexicon.SubscriberProcessor;
import se.lexicon.SubscriptionRules;

public class SubscriberProcessorTest {
	private SubscriberDao dao;
	private SubscriberProcessor processor;
	@BeforeEach
	void setUp() {
		dao = new SubscriberDao();
		processor = new SubscriberProcessor();

		// Seed test data
		dao.save(new Subscriber(1, "alice@example.com", Plan.PRO, true, 5));     // Active, Paying, Not expiring
		dao.save(new Subscriber(2, "bob@example.com", Plan.BASIC, true, 1));     // Active, Paying, Expiring
		dao.save(new Subscriber(3, "charlie@example.com", Plan.FREE, true, 0));  // Active, Free, Expired (0 months)
		dao.save(new Subscriber(4, "diana@example.com", Plan.PRO, false, 0));    // Inactive, Paying, Expired
		dao.save(new Subscriber(5, "eve@example.com", Plan.FREE, true, 3));      // Active, Free, Not expiring
	}
	@Test
	void scenario1_showActiveSubscribers() {
		List<Subscriber> active = processor.findSubscribers(dao.findAll(), SubscriptionRules.activeSubscriber);
		assertEquals(4, active.size());
		assertTrue(active.stream().allMatch(Subscriber::isActive));
	}
	@Test
	void scenario2_showExpiringSubscriptions() {
		List<Subscriber> expiring = processor.findSubscribers(dao.findAll(), SubscriptionRules.expiringSubscription);
		assertEquals(3, expiring.size()); // Bob (1), Charlie (0), Diana (0)
	}
	@Test
	void scenario3_showActiveAndExpiringSubscribers() {
		List<Subscriber> activeAndExpiring = processor.findSubscribers(
				dao.findAll(),
				SubscriptionRules.activeAndExpiring);

		assertEquals(2, activeAndExpiring.size());
		assertTrue(activeAndExpiring.stream().noneMatch(s -> s.getId() == 4));
	}
	@Test
	void scenario4_extendSubscriptionsForPayingSubscribers() {
		// Combine rules: Active AND Paying AND Expiring
		SubscriberFilter targetFilter = s -> SubscriptionRules.activeAndExpiring.matches(s)
				&& SubscriptionRules.payingSubscriber.matches(s);

		List<Subscriber> updated = processor.applyToMatching(
				dao.findAll(),
				targetFilter,
				SubscriptionRules.extendSubscription(3)
		);

		assertEquals(1, updated.size()); // Only Bob qualifies
		assertEquals(2, updated.get(0).getId());
		assertEquals(4, updated.get(0).getMonthsRemaining()); // 1 + 3 = 4
	}
	@Test
	void scenario5_deactivateExpiredFreeSubscribers() {
		// Combine rules: FREE plan AND 0 months remaining
		SubscriberFilter expiredFreeFilter = s -> SubscriptionRules.freePlan.matches(s)
				&& s.getMonthsRemaining() == 0;

		List<Subscriber> deactivated = processor.applyToMatching(
				dao.findAll(),
				expiredFreeFilter,
				SubscriptionRules.deactivateSubscriber
		);

		assertEquals(1, deactivated.size());
		assertEquals(3, deactivated.get(0).getId());
		assertFalse(deactivated.get(0).isActive());
	}
	@Test
	void scenario6_filterSubscribersByPlan() {
		List<Subscriber> proSubscribers = processor.findSubscribers(
				dao.findAll(),
				SubscriptionRules.proPlan
		);

		assertEquals(2, proSubscribers.size());
		assertTrue(proSubscribers.stream().allMatch(s -> s.getPlan() == Plan.PRO));
	}
	@Test
	void extraScenario_daoFindByIdAndUpdate() {
		assertTrue(dao.findById(99).isEmpty());

		Subscriber bob = dao.findById(2).orElseThrow();
		bob.setPlan(Plan.PRO);
		dao.save(bob);

		assertEquals(Plan.PRO, dao.findById(2).orElseThrow().getPlan());
		assertEquals(5, dao.findAll().size()); // Ensure save updated rather than duplicated
	}
}
