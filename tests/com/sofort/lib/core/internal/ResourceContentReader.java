package com.sofort.lib.core.internal;

import java.util.NoSuchElementException;
import java.util.Scanner;

import static com.sofort.lib.core.Logger.log;

/**
 * Resource file reader.
 */
public class ResourceContentReader {

    private final String resource;


    /**
     * Instance of resource content reader.
     *
     * @param resource resource file name started with '/' the content will be read
     *                 from.
     */
    public ResourceContentReader(String resource) {
        this.resource = resource;
    }


    /**
     * Reads the content of the given file
     *
     * @return content of a file
     */
    public final String getContent() {
        Scanner scanner = null;
        try {
            log.info("Read content from resource: " + resource);
            scanner = new Scanner(ResourceContentReader.class.getResourceAsStream(resource));
            scanner.useDelimiter("\\Z");

            return scanner.next();

        } catch (NoSuchElementException e) {
            return "";

        } finally {
            if (scanner != null) {
                scanner.close();
            }
            log.info("Done reading content from resource: " + resource);
        }
    }

}
