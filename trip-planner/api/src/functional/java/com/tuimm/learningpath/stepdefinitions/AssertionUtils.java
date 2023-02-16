package com.tuimm.learningpath.stepdefinitions;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Assertions;

import java.util.Map;

public class AssertionUtils {
    private AssertionUtils() {

    }

    public static void assertMapMatchesJson(Map<String, String> map, JsonNode node) {
        for (String key : map.keySet()) {
            Assertions.assertNotNull(getNode(node, key), String.format("Field %s is not present in the response", key));
            Assertions.assertEquals(map.get(key),
                    getNode(node, key).textValue(),
                    String.format("Check for field %s has failed", key));
        }
    }

    private static JsonNode getNode(JsonNode node, String key) {
        String[] splitKey = key.split("\\.");
        JsonNode resultNode = node;
        for (String k : splitKey) {
            resultNode = resultNode.get(k);
        }
        return resultNode;
    }
}
