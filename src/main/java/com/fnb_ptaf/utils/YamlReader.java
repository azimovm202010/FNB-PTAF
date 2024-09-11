package com.fnb_ptaf.utils;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class YamlReader {
    // Static map to hold the loaded YAML data
    private static Map<String, Object> data = new HashMap<>();

    // Static block to load YAML files from the specified folder
    static {
        // Create a new Yaml instance
        Yaml yaml = new Yaml();
        // Specify the folder path where the YAML files are located
        String folderPath = "elements";

        try (Stream<Path> paths = Files.walk(Paths.get(YamlReader.class.getClassLoader().getResource(folderPath).toURI()))) {
            // Filter and process each file in the folder
            paths
                    .filter(Files::isRegularFile)  // Only process regular files
                    .filter(path -> path.toString().endsWith(".yml"))  // Only process YAML files
                    .forEach(path -> {
                        try (InputStream inputStream = Files.newInputStream(path)) {
                            // Load the data from the YAML file
                            Map<String, Object> fileData = yaml.load(inputStream);
                            // Merge the loaded data into the main data map
                            mergeData(data, fileData);
                        } catch (IOException e) {
                            e.printStackTrace();  // Print the stack trace in case of an error
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();  // Print the stack trace in case of an error
        }
    }

    /**
     * Merges new data into the base map.
     *
     * @param base The base map to merge into
     * @param newData The new data to merge
     */
    private static void mergeData(Map<String, Object> base, Map<String, Object> newData) {
        // Iterate over the entries in the new data map
        for (Map.Entry<String, Object> entry : newData.entrySet()) {
            // If the base map already contains the key, merge the data
            if (base.containsKey(entry.getKey())) {
                Object baseValue = base.get(entry.getKey());
                Object newValue = entry.getValue();
                // If both values are maps, merge them recursively
                if (baseValue instanceof Map && newValue instanceof Map) {
                    mergeData((Map<String, Object>) baseValue, (Map<String, Object>) newValue);
                } else {
                    // Otherwise, replace the value in the base map
                    base.put(entry.getKey(), newValue);
                }
            } else {
                // If the base map does not contain the key, add the new entry
                base.put(entry.getKey(), entry.getValue());
            }
        }
    }

    /**
     * Retrieves a value from the loaded YAML data based on a dot-separated key.
     *
     * @param key The dot-separated key to look up
     * @return The value associated with the key
     */
    public static Object get(String key) {
        // Split the key into parts
        String[] keys = key.split("\\.");
        Map<String, Object> value = data;
        // Traverse the map using the key parts
        for (int i = 0; i < keys.length - 1; i++) {
            value = (Map<String, Object>) value.get(keys[i]);
        }
        // Return the final value
        return value.get(keys[keys.length - 1]);
    }
}
