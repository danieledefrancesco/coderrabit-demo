package com.tuimm.learningpath;

import com.tuimm.learningpath.authorization.RSAPublicKeySupplier;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemReader;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import java.io.*;
import java.net.URL;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.function.Function;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/functional/resources/features",
        plugin = {"pretty", "html:target/cucumber/spring-challenge-specs.html"},
        dryRun = true,
        monochrome = true)
public class CucumberTest {

    @TestConfiguration
    public static class ServicesOverride {
        @Bean
        @Primary
        public TodayDateProvider todayDateProvider() {
            TodayDateProvider todayDateProvider = mock(TodayDateProvider.class);
            when(todayDateProvider.getTodayDate()).thenReturn(LocalDate.now());
            return todayDateProvider;
        }

        @Bean
        @Primary
        public RSAPublicKeySupplier rsaPublicKeySupplier() {
            return new RSAPublicKeySupplier(inputStreamToText(getResourceAsInputStream("keys/jwt_public.key")).replace("\r\n","\n"));
        }

        @Bean
        public Function<String, RSAPrivateKey> rsaPrivateKeySupplier() {
            java.security.Security.addProvider(
                    new org.bouncycastle.jce.provider.BouncyCastleProvider()
            );
            return (fileName) -> {
                String privateKey = inputStreamToText(getResourceAsInputStream(fileName)).replace("\r\n","\n");
                try(
                        Reader reader = new StringReader(privateKey);
                        PemReader pemReader = new PemReader(reader)
                ) {
                    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
                    PemObject pemObject = pemReader.readPemObject();
                    byte[] keyContentAsBytesFromBC = pemObject.getContent();
                    return (RSAPrivateKey) keyFactory.generatePrivate(new PKCS8EncodedKeySpec(keyContentAsBytesFromBC));
                } catch (NoSuchAlgorithmException | InvalidKeySpecException | IOException e) {
                    throw new UnsupportedOperationException(e);
                }
            };
        }

        private File getResourceAsFile(String fileName) {
            try {
                URL resource = getClass().getClassLoader().getResource(fileName);
                if (resource == null) {
                    throw new IllegalArgumentException("file not found!");
                } else {
                    return new File(resource.toURI());
                }
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }

        private FileInputStream getResourceAsInputStream(String fileName) {
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
    }
}
