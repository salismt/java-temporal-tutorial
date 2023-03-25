package org.example.greeting.activity;

public class GreetingActivityImpl implements GreetingActivity {
    @Override
    public String composeGreeting(String greet, String name) {
        return String.format("%s %s hehe", greet, name);
    }
}
