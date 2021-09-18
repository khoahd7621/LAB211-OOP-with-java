package security;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author KhoaHD.7621
 */
public class MD5Encryptor {
    
    public static String encrypt(String srcText) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String enrText;
        
        MessageDigest msd = MessageDigest.getInstance("MD5");
        byte[] srcTextBytes = srcText.getBytes("UTF-8");
        byte[] enrTextBytes = msd.digest(srcTextBytes);
        
        BigInteger bigInt = new BigInteger(1, enrTextBytes);
        enrText = bigInt.toString(16);
        
        return enrText;
    }
    
    public static String convertToMD5(String input) throws Exception {
    String md5 = null;
    if (null == input)
        return null;
    try {
        // Create MessageDigest object for MD5
        MessageDigest digest = MessageDigest.getInstance("MD5");
        // Update input string in message digest
        digest.update(input.getBytes(), 0, input.length());
        // Converts message digest value in base 16 (hex)
        md5 = new BigInteger(1, digest.digest()).toString(16);
    } catch (NoSuchAlgorithmException e) {

        throw e;
    }
    return md5;
}
    
}
