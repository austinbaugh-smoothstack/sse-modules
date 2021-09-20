package lms.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Collectors;

class Table {
    
    private int[] columnWidths;
    private String[] columnHeaders;
    private List<String[]> values = Collections.emptyList();
    
    Table(final int[] columnWidths, final String[] columnHeaders) {
        this.columnWidths = columnWidths;
        this.columnHeaders = columnHeaders;
    }
    
    /**
     * Constructs a List<String[]> from a List<Object[]>, applying a max width for each table value based on its column width
     * @param values Objects to be displayed in the table
     */
    void setValues(final List<Object[]> values) {
        this.values = values.stream().map(rowValues -> {
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
    }

    /**
     * Prints the entire table with dashes making borders and a header
     */
    @Override
    public String toString() {
        // Repeating dashes equal to the total width of the table
        final String border = "-".repeat(IntStream.of(columnWidths).sum() + columnWidths.length - 1) + System.lineSeparator();
        // Specifies the column widths in the format used for each row (i.e. "%5s %20s %15s")
        final String rowFormat = Arrays.stream(columnWidths)
            .boxed()
            .map(width -> "%" + String.valueOf(width) + "s")
            .collect(Collectors.joining(" ", "", " %n"));
        // Table
        return border
            + String.format(rowFormat, (Object[]) columnHeaders)
            + border
            + values.stream().map(row -> String.format(rowFormat, (Object[]) row)).collect(Collectors.joining())
            + border;
    }
}
