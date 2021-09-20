package lms.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Collectors;

class Table {
    
    private int[] columnWidths;
    private String[] columnHeaders;
    
    Table(final int[] columnWidths, final String[] columnHeaders) {
        this.columnWidths = columnWidths;
        this.columnHeaders = columnHeaders;
    }
    
    /**
     * Prints the entire table with dashes making borders and a header
     * @param tableValues Constructs a List<String[]> from a List<Object[]>, applying a max width for each table value based on its column width
     */
    void print(final List<Object[]> values) {
        final List<String[]> tableValues = values.stream().map(rowValues -> {
            String[] row = new String[rowValues.length];
            int columnIndex = 0;
            for(final Object value : Arrays.asList(rowValues)) {
                // Applies max value string width based on the corresponding column width
                final int originalLen = value.toString().length();
                final int newLen = originalLen > columnWidths[columnIndex] ? columnWidths[columnIndex] : originalLen;
                row[columnIndex] = value.toString().substring(0, newLen);
                columnIndex++;
            }
            return row;
        }).collect(Collectors.toList());
        // Repeating dashes equal to the total width of the table
        final String border = "-".repeat(IntStream.of(columnWidths).sum() + columnWidths.length - 1);
        // Specifies the column widths in the format used for each row (i.e. "%5s %20s %15s")
        final String rowFormat = Arrays.stream(columnWidths)
            .boxed()
            .map(width -> "%" + String.valueOf(width) + "s")
            .collect(Collectors.joining(" ", "", " %n"));
        // Table
        System.out.println(border);
        System.out.print(String.format(rowFormat, (Object[]) columnHeaders));
        System.out.println(border);
        if(tableValues.isEmpty()) {
            System.out.println("(no records)");
        } else {
            tableValues.stream().forEach(row -> System.out.format(rowFormat, (Object[]) row));
        }
        System.out.println(border);
    }
}
