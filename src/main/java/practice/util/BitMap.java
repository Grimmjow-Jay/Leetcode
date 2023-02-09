package practice.util;

/**
 * long 8字节 64位
 *
 * @author Jay Yang
 * @date 2023/2/9
 */
public class BitMap {

    private final long size;
    private final long[] table;

    public BitMap(long size) {
        this.size = size;
        int a = (int) (size >>> 6); // 除以 2^6 （64，1long = 8byte = 64bit）
        long b = size % 64;
        if (b > 0) {
            table = new long[a + 1];
        } else {
            table = new long[a];
        }
    }

    public void set(long index, boolean value) {
        rangeCheck(index);

        int tableIndex = tableIndex(index);
        int bitIndex = bitIndex(index);

        if (value) {
            table[tableIndex] = table[tableIndex] | (Long.MIN_VALUE >>> bitIndex);
        } else {
            table[tableIndex] = table[tableIndex] & ~(Long.MIN_VALUE >>> bitIndex);
        }
    }

    public boolean get(long index) {
        rangeCheck(index);

        int tableIndex = tableIndex(index);
        int bitIndex = bitIndex(index);

        long x = Long.MIN_VALUE >>> bitIndex;

        return (table[tableIndex] & x) == x;
    }

    private int tableIndex(long index) {
        return (int) ((index + 1) >>> 6);
    }

    private int bitIndex(long index) {
        return (int) (index % 64);
    }

    private void rangeCheck(long index) {
        if (index >= size)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }
}
