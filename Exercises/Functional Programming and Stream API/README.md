# Exercises — Functional Programming and Stream API

## Starter Data

### Person Class Definition

```java
public class Person {
    private String name;
    private int age;
    private String city;
    private boolean active;

    public Person(String name, int age, String city, boolean active) {
        this.name = name;
        this.age = age;
        this.city = city;
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name=" + name + 
                ", age=" + age +
                ", city=" + city +
                ", active=" + active +
                '}';
    }
}
```

### Sample Data

```java
List<Person> people = List.of(
        new Person("Amina", 22, "Stockholm", true),
        new Person("Erik", 17, "Uppsala", true),
        new Person("Noah", 34, "Stockholm", false),
        new Person("Sara", 29, "Gothenburg", true),
        new Person("Lina", 41, "Malmö", false),
        new Person("Omar", 19, "Stockholm", true)
);
```

---

## Part 1 — Functional Programming (No Streams)

### Core Tasks

1. Create a functional interface `PersonRule` with one method that takes a `Person` and returns a `boolean`.
2. Write rules (lambdas) for: active, adult (age 18 or above), and lives in Stockholm.
3. Write a `filterPeople` method that uses `PersonRule`.
4. Create `PersonAction` and write actions to print a name and to print: `Send email to <name>`.

### Optional Tasks (Extra Practice)

5. **Rule Composition**
    - Create rules: `isActive`, `isAdult`, `livesInStockholm`
    - Create combined rules:
        - active and adult
        - adult or lives in Stockholm
        - not active

6. **Reusable Processor**
    - Create a class `PersonProcessor` with:
        - `findPeople(list, rule)` returning a `List<Person>`
        - `applyToMatching(list, rule, action)` returning `void`

7. **Prebuilt Interfaces**
    - Replace `PersonRule` with `Predicate<Person>`
    - Replace `PersonAction` with `Consumer<Person>`

---

## Part 2 — Stream API

### Core Tasks

1. Filter all active people.
2. Map all names.
3. Count adults.
4. Sort by age.
5. Find first active person in Stockholm.

### Optional Tasks (Extra Practice)

6. **Multiple Filters**
    - Active adults
    - Active people in Stockholm
    - Inactive people older than 30

7. **Distinct and Sorting**
    - Get a sorted list of unique cities.
    - Get a sorted list of unique first letters of names.

8. **Mapping and Formatting**
    - Create a list of strings like: `Name (age) - city`
    - Create a list of emails like: `name.toLowerCase() + "@example.com"`

---

## Reflection Questions

### Which solutions were easier to read: loop-based or stream-based?  
**Stream-based solutions are generally much easier to read** because they focus on **what** you want to do (declarative programming) rather than **how** to do it step-by-step (imperative programming). 

* **The Problem with Loops:** Loop-based code requires reading through boilerplate mechanics—initializing storage lists, managing loop indexes or iterators, and embedding conditional nesting (`if` statements inside `for` blocks). Your brain has to mentally track data state changes at each step.
* **The Benefit of Streams:** Stream code reads like a human-language sentence flowing from top to bottom. It isolates operations cleanly into individual, chainable steps (`.filter()`, `.map()`, `.sorted()`), making it highly scannable.

### Which operations felt like transformations and which felt like side effects?  
* **Transformations (Pure Operations):** These operations take data in, apply a formula, and return entirely new data or collections without changing the original data structure. They are deterministic and clean.
  * **`.filter()`**: Modifies the stream shape by discarding elements that don't match criteria.
  * **`.map()`**: Changes data types or shapes (e.g., turning a `Person` object into a `String` email layout).
  * **`.sorted()`**: Generates an ordered arrangement of the items.
* **Side Effects (State/External Changes):** These actions interact with things outside the immediate scope of the stream pipeline or modify internal states.
  * **`System.out.println()`**: Sending data out to an external console terminal.
  * **`forEach(...)`**: Iterating exclusively to print text, trigger a database save, or write to an external file.
### Where did `Predicate`, `Function`, and `Consumer` fit naturally?  
Java’s core functional interfaces align with standard stream operations based on their structural input/output signatures:

| Functional Interface | Signature | Natural Stream Home | Example from Code |
| :--- | :--- | :--- | :--- |
| **`Predicate<T>`** | Takes one input, returns a `boolean` | **`.filter()`** | `p -> p.getAge() >= 18` |
| **`Function<T, R>`** | Takes one input, transforms it, returns a result | **`.map()`** | `p -> p.getName().toLowerCase() + "@example.com"` |
| **`Consumer<T>`** | Takes one input, processes it, returns nothing (`void`) | **`.forEach()`** | `System.out::println` |