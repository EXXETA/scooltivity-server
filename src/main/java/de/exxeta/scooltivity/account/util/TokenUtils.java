package de.exxeta.scooltivity.account.util;

import io.dropwizard.jackson.Jackson;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.ParseException;
import java.util.Base64;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;

public class TokenUtils {

  private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

  public static String generateToken(Object payload, String sharedSecretString) throws IOException, GeneralSecurityException, JOSEException {
    // Create RSA-signer with the private key
    JWSSigner signer = new MACSigner(sharedSecretString);

    // Prepare JWS object with simple string as payload
    JWSObject jwsObject = new JWSObject(new JWSHeader(JWSAlgorithm.HS512), new Payload(MAPPER.writeValueAsString(payload)));

    // Compute the RSA signature
    jwsObject.sign(signer);

    // Serialize to string
    return jwsObject.serialize();
  }

  public static boolean verifyToken(String token, String sharedSecretString) throws IOException, GeneralSecurityException, JOSEException,
      ParseException {

    JWSVerifier verifier = new MACVerifier(sharedSecretString);
    JWSObject jwsObject = JWSObject.parse(token);
    return jwsObject.verify(verifier);
  }

  public static <T> T getTokenPayload(String token, Class<T> t) throws IOException, JsonMappingException, IOException {
    return MAPPER.readValue(Base64.getUrlDecoder().decode(token.split("\\.")[1]), t);
  }
}
