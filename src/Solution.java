import java.util.*;

class Solution {
    static Set<Character> left;


    public static class Book {
        String serialNumber;
        String bookTitle;

        @Override
        public boolean equals(Object o) {
            Book book = (Book) o;
            return (Objects.equals(((Book) o).serialNumber, this.serialNumber));
        }
    }
}