package com.tuimm.leaarningpath;

import com.tuimm.leaarningpath.common.RandomIdGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.UUID;

class AdvanceChallengeTest {
    private File getResourceAsFile(String fileName) {
        try {
            URL resource = getClass().getClassLoader().getResource(fileName);
            if (resource == null) {
                throw new IllegalArgumentException("file not found!");
            } else {
                return new File(resource.toURI());
            }
        }
        catch (Exception e) {
            throw new UnsupportedOperationException(e);
        }
    }

    private FileInputStream getResourceAsInputStream(String fileName)
    {
        try {
            return new FileInputStream(getResourceAsFile(fileName));
        } catch (FileNotFoundException e) {
            throw new UnsupportedOperationException(e);
        }
    }

    private String inputStreamToText(InputStream inputStream) {
        Scanner s = new Scanner(inputStream).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

    @Test
    void application_providesExpectedOutput() {
        InputStream inputs = getResourceAsInputStream("inputs.txt");
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        String utf8 = StandardCharsets.UTF_8.name();
        RandomIdGenerator randomIdGenerator = new TestIdGenerator();
        try (PrintStream printStream = new PrintStream(outputStream, true, utf8)) {
            AdvanceChallenge.ServiceLocator.getInstance().setRandomIdGeneratorSupplier(() -> randomIdGenerator);
            Assertions.assertEquals(System.in, AdvanceChallenge.ServiceLocator.getInstance().getInputStreamSupplier().get());
            AdvanceChallenge.ServiceLocator.getInstance().setInputStreamSupplier(() -> inputs);
            Assertions.assertEquals(System.out, AdvanceChallenge.ServiceLocator.getInstance().getPrintStreamSupplier().get());
            AdvanceChallenge.ServiceLocator.getInstance().setPrintStreamSupplier(() -> printStream);
            AdvanceChallenge.main();
            String actualOutput = outputStream.toString().replaceAll("\\r\\n?","\n");
            String expectedOutput = inputStreamToText(getResourceAsInputStream("expectedOutput.txt")).replaceAll("\\r\\n?","\n");
            Assertions.assertEquals(expectedOutput, actualOutput);
        }
        catch (Exception e)
        {
            throw new UnsupportedOperationException(e);
        }
    }
    private static class TestIdGenerator implements RandomIdGenerator {
        private static int cnt = 1;
        @Override
        public UUID generateRandomId() {
            return UUID.fromString(String.format("d0e82fb9-c4e5-4d20-9522-b09e8a857b0%d", cnt++));
        }
    }
}
