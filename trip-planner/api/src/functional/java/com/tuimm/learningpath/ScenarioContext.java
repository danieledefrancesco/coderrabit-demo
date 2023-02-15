package com.tuimm.learningpath;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;

@Component
@Scope(SCOPE_CUCUMBER_GLUE)
public class ScenarioContext {
    private final Map<Class<?>, Object> context = new HashMap<>();

    public void cleanUp() {
        context.clear();
    }

    public <T> void set(Class<T> type, T data) {
        context.remove(type);
        context.put(type, data);
    }

    public <T> T get(Class<T> type) {
        if (!context.containsKey(type)) return null;
        return (T) context.get(type);
    }

    public Driver getDriver() {
        return get(Driver.class);
    }

}
