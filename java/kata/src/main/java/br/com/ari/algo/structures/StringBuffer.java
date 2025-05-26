package br.com.ari.algo.structures;

import java.util.regex.Pattern;

public class StringBuffer implements CharSequence {

    private final ArrayList<CharSequence> buffer;
    private int length;

    public StringBuffer() {
        this.buffer = new ArrayList<>();
        this.length = 0;
    }

    public StringBuffer(CharSequence chr) {
        this();
        this.append(chr);
    }

    public StringBuffer append(CharSequence str) {
        if (str != null && !str.isEmpty()) {
            this.buffer.add(str);
            this.length += str.length();
        }

        return this;
    }

    @Override
    public int length() {
        return this.length;
    }

    @Override
    public char charAt(int index) {
        if (isEmpty()) {
            throw new IllegalStateException("StringBuffer is empty");
        }

        if (index < 0 || index > this.length) {
            throw new IndexOutOfBoundsException("Index not in string length");
        }

        if (index == this.length - 1) {
            var last = this.buffer.getLast();
            return last.charAt(last.length() - 1);
        }

        int charIndex = 0;

        for (CharSequence chr : this.buffer) {
            if (index > chr.length() + charIndex - 1) {
                charIndex += chr.length();
                continue;
            }

            for (int i = 0; i < chr.length(); i++) {
                if (charIndex == index) {
                    return chr.charAt(i);
                }

                charIndex++;
            }
        }

        return 0;
    }

    @Override
    public boolean isEmpty() {
        return this.length == 0;
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        if (start < 0 || start > length - 1 || end > length || end < 1 || end < start) {
            throw new IllegalArgumentException();
        }

        int index = 0;
        int charIndex = 0;
        char[] result = new char[end - start];

        for (int i = 0; i < this.buffer.size() && charIndex < end; i++) {
            CharSequence chr = this.buffer.get(i);

            // Find the first element in the buffer that corresponds to the start index
            if (start > chr.length() + charIndex - 1) {
                charIndex += chr.length();
                continue;
            }

            for (int j = 0; j < chr.length() && charIndex < end; j++, charIndex++) {
                if (charIndex >= start) {
                    result[index++] = chr.charAt(j);
                }
            }
        }

        return new String(result);
    }

    public StringBuffer trim() {
        if (isEmpty()) {
            throw new IllegalStateException("StringBuffer is empty");
        }

        if (this.buffer.size() == 1) {
            trim(0, "^\\s+|\\s+$");
        }
        else {
            trim(0, "^\\s+");
            trim(this.buffer.size() - 1, "\\s+$");
        }

        return this;
    }

    private void trim(int index, String pattern) {
        if (index < 0 || index >= this.buffer.size()) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }

        var element = this.buffer.get(index);
        var trimmed = trimByRegex(element, pattern);

        this.buffer.set(index, trimmed);
        this.length = this.length - element.length() + trimmed.length();
    }

    private CharSequence trimByRegex(CharSequence element, String pattern) {
        var p = Pattern.compile(pattern);
        return p.matcher(element).replaceAll("");
    }

    public String toString() {
        int index = 0;
        char[] result = new char[this.length];

        for (CharSequence chr : this.buffer) {
            for (int i = 0; i < chr.length(); i++) {
                result[index++] = chr.charAt(i);
            }
        }

        return new String(result);
    }

}
