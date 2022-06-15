package by.silina.beautysalon.util;

public class PageCounter {
    
    private PageCounter() {
    }

    public static int getNumberOfPages(long numberOfRecords, int recordsPerPage) {
        return (int) Math.ceil(numberOfRecords * 1.0 / recordsPerPage);
    }
}
