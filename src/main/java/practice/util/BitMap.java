package practice.util;

import lombok.Getter;

/**
 * long 8字节 64位
 *
 * @author Jay Yang
 * @date 2023/2/9
 */
public class BitMap {

    @Getter
    private final long size;
    private final long[] table;

    public BitMap(long size) {
        this.size = size;
        int a = (int) (size >>> 6); // 除以64 （1long = 8byte = 64bit）
        long b = size & 63; // 取余64
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
        return (int) (index >>> 6);
    }

    private int bitIndex(long index) {
        // index & 63 相当于 index % 64
        return (int) (index & 63);
    }

    private void rangeCheck(long index) {
        if (index >= size)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }
}
