# Group Report – Subscription Manager

## Group Discussion and Project Planning

During our group discussion, we discussed how to organise the project, divide the responsibilities, and make sure that all classes work together correctly.

One important idea discussed during brainstorming was to separate the business rules from the `Main` class. Initially, we considered defining the rules directly inside `Main`, but after discussing the project structure, we agreed that creating a separate `SubscriptionRules` class would be a better solution. This makes the code more organised, reusable, and easier to maintain.

Another important discussion was about the subscriber data. We were initially unsure whether the subscriber list would be provided or whether we had to create it ourselves. After discussing this with the group, we understood that we needed to create our own sample subscribers. We decided to use different subscription plans, account statuses, and remaining subscription months to demonstrate how the application works.

## Individual Responsibilities and Contributions

Each group member was assigned a specific responsibility based on the project requirements.

### Soraya – `SubscriberProcessor.java`

Soraya is responsible for implementing the processing logic in the `SubscriberProcessor` class. Her main tasks are implementing the `findSubscribers()` method, which filters subscribers based on specific conditions, and the `applyToMatching()` method, which performs actions on subscribers that match a given filter. This part is important because it connects the functional interfaces with the business logic and allows the application to process subscribers in a reusable way.

### Haleema – `SubscriptionRules.java`

Haleema is responsible for implementing the business rules using lambda expressions. Her work includes defining conditions for finding active subscribers, expiring subscriptions, subscribers by plan, and paying subscribers. She is also responsible for defining actions such as extending subscriptions and deactivating accounts. Keeping these rules in a separate class helps make the application easier to understand and maintain.

### Ezhilarasi – `SubscriberDAO.java`

Ezhilarasi is responsible for the data access functionality. Her tasks include implementing methods for saving subscribers, retrieving all subscribers, and finding individual subscribers by their ID. This class provides the functionality needed to manage and access subscriber information.

### Ervin – `Subscriber.java` and `SubscriberProcessorTest.java`

Ervin is responsible for implementing `Subscriber.java`, which represents an individual subscriber and stores their information, including ID, email address, subscription plan, active status, and remaining subscription months.

In addition to the subscriber class, Ervin also worked on `SubscriberProcessorTest.java`, writing JUnit 5 tests for the application. These tests check different scenarios, such as displaying active subscribers, finding expiring subscriptions, identifying active and expiring subscribers, and extending subscriptions for paying subscribers.

While writing the tests, Ervin demonstrated his work to the group and explained the code as he developed it. This gave the other members an opportunity to follow the implementation, understand how the tests work, and learn how to verify the functionality of the application.

### Christopher – `SubscriberFilter.java`, `SubscriberAction.java`, `Plan.java` and `Main.java`

Christopher is responsible for the functional interfaces and the main application setup.

His tasks include implementing `SubscriberFilter.java`, which defines the conditions used to check whether a subscriber matches a specific rule, and `SubscriberAction.java`, which defines the operations that can be performed on subscribers.

He is also responsible for `Plan.java`, which defines the available subscription plans: `FREE`, `BASIC`, and `PRO`.

Finally, Christopher is responsible for `Main.java`, where sample subscriber objects are created, added to a list, and used to demonstrate how the application works.

## Collaboration and Teamwork

Although each member has an individual responsibility, the project requires cooperation because the different classes depend on each other.

For example, `SubscriberProcessor` uses the `SubscriberFilter` and `SubscriberAction` interfaces to filter subscribers and perform actions. Similarly, `SubscriptionRules` provides the conditions and actions that can be passed to the processor. The `Main` class brings these components together by providing sample subscribers and demonstrating their functionality.

During our discussions, we shared ideas about how the classes should interact and how to avoid putting too much responsibility in one class. We also clarified that the sample subscriber data needed to be created by ourselves. These discussions helped us understand the project requirements and how our individual contributions fit into the overall application.

We will also need to communicate when integrating our code, check that the methods and interfaces have matching definitions, and help each other identify and solve programming errors.

## Evaluation and Learning

Working on this project gives us an opportunity to practise object-oriented programming and functional programming together. We can learn from each other's approaches and understand how different parts of a Java application work together.

By dividing the work into separate responsibilities, each member can focus on a specific part while still contributing to the final result. The discussions about separating business rules, using lambda expressions, and creating sample data have helped us make better decisions about the project structure.

Overall, our goal is to combine our individual contributions into one working application. We will test the functionality, review each other's code, and make improvements together to ensure that the final project meets the assignment requirements.

---

[workgroup/SubscriptionManager at main · domashno/workgroup](/SubscriptionManager)
