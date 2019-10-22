package model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.xml.bind.DatatypeConverter;

/**
 * Utility class.
 */
class Util {

  /**
   * Hashes input as MD5.
   * @param input The input string.
   * @return The hashed string.
   */
  static String hash(String input) {
    String hash;

    try {
      MessageDigest md = MessageDigest.getInstance("MD5");
      md.update(input.getBytes());
      byte[] digest = md.digest();
      hash = DatatypeConverter.printHexBinary(digest).toUpperCase();
    } catch (NoSuchAlgorithmException e) {
      hash = input;
    }

    return hash;
  }
}
