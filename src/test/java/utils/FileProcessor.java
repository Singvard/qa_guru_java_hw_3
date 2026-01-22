package utils;

import java.io.InputStream;

@FunctionalInterface
public interface FileProcessor {
    void process(InputStream fileStream) throws Exception;
}
