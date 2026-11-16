package se.lexicon;

import java.lang.FunctionalInterface;
@FunctionalInterface
public interface PersonRule {
    boolean test(Person person);

}
