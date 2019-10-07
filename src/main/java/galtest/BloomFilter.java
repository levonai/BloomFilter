/***
* Bloom Filter
* Bloom filter is a probabilistic data structure that is used to test whether an element is a member of a set.
* It is a probabilistic data structure: it tells if the element either definitely not in the set or probably in the set.
*
* inputs:
*   n - data set size
*   m - bitmap size 
*   k - number of hash functions
*   p - false positive probablity
*
*   m = - nlogp / (log2)^2;
*   k = m/n log(2) ;
*
* ferwer than 10 bits per element are requeired for a 1% false positive probability -> m = 10*n
* optimal k = m/n log2 -> k = 7
***/

package galtest;

public class BloomFilter {

    HashFunction hash;
    BitMap map;
    int k;

    public BloomFilter(int bitmapSize, int numK, String hashType) {
        hash = new HashFunction(bitmapSize, numK, hashType);
        map = new BitMap(bitmapSize);
        k = numK;
    }

    public void addWord (String s) {
        int[] hashValues = hash.hash1(s);

        for (int i = 0; i < k; i++) {
            map.setBit(hashValues[i]);
        }
    }

    public boolean getWord (String s) {
        int[] hashValues = hash.hash1(s);

        boolean isValid = true;
        for (int i = 0; i < k; i++) {
            isValid &= map.getBit(hashValues[i]);
        }
        return isValid;
    }

    private static boolean isWord(String s) 
    { 
        return ((s != null) && (!s.equals("")) && (s.matches("^[a-zA-Z]*$"))); 
    } 
}