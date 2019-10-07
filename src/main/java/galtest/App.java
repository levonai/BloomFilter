package galtest;

import java.io.*;

public final class App {
    private App() {
    }

    // test the BloomFilter
    public static void main(String[] args) {
        
        int m = 3000000;
        int k = 7;
        String h = "MD5";
        System.out.println ("Bloom Filter: m = " + m + " k = " + " h = " + h);
        testBloomFilter(m, k , h);

        m = 1500000;
        k = 3;
        System.out.println ("Bloom Filter: m = " + m + " k = " + " h = " + h);
        testBloomFilter(m, k , h);
    }

    private static void testBloomFilter(int m, int k, String h) {
        // creates a bloom filter, given the bitmap size, number of hash functions and hash function to use
        BloomFilter wFilter = new BloomFilter(m, k, h);
        
        int errorCount = 0;
        int wordCount = 0;

        long startTime = System.currentTimeMillis();
        try {
            // open the word list text file
            BufferedReader br = new BufferedReader(new FileReader("wordlist.txt"));
            String strLine;
            // get the next word, check the filter for false positive
            // then add the word to the filter
            while ((strLine = br.readLine()) != null) {
                if (wFilter.getWord(strLine) == true) {
                    errorCount++;
                }
                wFilter.addWord(strLine);
                wordCount++;
            }
            System.out.println ("total count " + wordCount + " false positive: " + errorCount + " (" + (double)errorCount/wordCount + ")");
            //Close the input buffer
            br.close();
        } catch(Exception e) { 
            System.out.println(e);
        }
        long stopTime = System.currentTimeMillis();
        elapseTime(startTime, stopTime);
    }

    private static void elapseTime(long start, long stop) {
        long elapsedTime = stop - start;
        System.out.println("exec time (millisec): " + elapsedTime);
    }
}
