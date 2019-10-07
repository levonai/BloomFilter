/*** 
* HashFunction
* HashFunction class implement hash function and retrun k hash values 
* Using java MediaDigest library for the hash functions
* using the digest call results to create k hash values 
***/

package galtest;

import java.math.BigInteger; 
import java.security.MessageDigest; 
import java.security.NoSuchAlgorithmException;

public class HashFunction {

    private static int mapSize;
    private static int kHashValues;
    private static String hashName;

    HashFunction (int m, int k, String hashType) {
        mapSize = m;
        kHashValues = k;
        hashName = hashType;
    }

    public int[] hash(String input) 
    { 
        try { 
            // digest() method returns the hasked value of the input string 
            MessageDigest messageDigest = MessageDigest.getInstance(hashName);
            byte[] message = messageDigest.digest(input.getBytes());  
            // convert the byte array into BigInt
            BigInteger bitIntMessage = new BigInteger(1, message); 

            int[] results = new int[kHashValues];
        
            // generate k hash values
            // k hash value is (message mod mapSize) and message = meggase / mapSize
            BigInteger temp = bitIntMessage;
            for (int i = 0; i < kHashValues; i++) {
                results[i] = temp.mod(BigInteger.valueOf(mapSize)).intValue();
                temp = temp.divide(BigInteger.valueOf(mapSize));
                if (temp.compareTo(BigInteger.ZERO) == 0) {
                    temp = bitIntMessage.divide(BigInteger.valueOf(2));
                }
            }
            return results;
        }  
        // message digest error 
        catch (NoSuchAlgorithmException e) { 
            throw new RuntimeException(e); 
        } 
    } 
}

