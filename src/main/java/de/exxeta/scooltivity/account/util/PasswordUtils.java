package de.exxeta.scooltivity.account.util;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class PasswordUtils {

  public static String createPasswordHash(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
    MessageDigest md = MessageDigest.getInstance("SHA-256");
    md.update(password.getBytes("UTF-8"));
    return Base64.getEncoder().encodeToString(md.digest());
  }

  public static String createPasswordHash(String password, String passwordSalt) throws GeneralSecurityException,
      UnsupportedEncodingException {
    return createPasswordHash(password + passwordSalt);
  }

}
