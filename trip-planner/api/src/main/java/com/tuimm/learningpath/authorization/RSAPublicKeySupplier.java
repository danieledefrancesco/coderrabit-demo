package com.tuimm.learningpath.authorization;

import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.function.Supplier;

@Component
public class RSAPublicKeySupplier implements Supplier<RSAPublicKey> {
    private final String publicKeyString;

    public RSAPublicKeySupplier(@Value("${security.jwt.public_key}") String publicKeyString) {
        this.publicKeyString = publicKeyString;
    }

    @Override
    public RSAPublicKey get() {
        try(
                Reader reader = new StringReader(publicKeyString);
                PemReader pemReader = new PemReader(reader)
        ) {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PemObject pemObject = pemReader.readPemObject();
            byte[] keyContentAsBytesFromBC = pemObject.getContent();
            return (RSAPublicKey) keyFactory.generatePublic(new X509EncodedKeySpec(keyContentAsBytesFromBC));
        } catch (NoSuchAlgorithmException | InvalidKeySpecException | IOException e) {
            throw new UnsupportedOperationException(e);
        }
    }
}
