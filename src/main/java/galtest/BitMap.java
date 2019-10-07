/*** 
* BitMap
* Bitmap implementation using int array.
***/
package galtest;

public class BitMap {
    private int bits[] = null;
    private int mapSize;

    public BitMap(int size) {
        mapSize = size;
        bits = new int[(size + 31) >> 5];
        reset();
    }

    public boolean getBit(int pos) {
        if (validatePos(pos) == true) {
            return (bits[pos >> 5] & (0x1 << (pos & 31))) != 0;
        }
        return false;
    }

    public void setBit(int pos) {
        if (validatePos(pos) == true) {
            bits[pos >> 5] |= (0x1 << (pos & 31));
        }
    }

    public void reset() {
        for(int i=0; i < (mapSize >> 5); i++) {
            bits[i] = 0x0;
        }
    }

    private boolean validatePos(int pos) {
        if (pos >= mapSize || pos < 0) {
            System.out.println("Invalide position: " + pos);
            return false;
        }
        return true;
    }
}