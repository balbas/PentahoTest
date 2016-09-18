package jmb.pentahotest.backend.controller;

import java.text.Format;
import java.text.DateFormat;
import java.text.DecimalFormat;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * Use a formatter to format the cell Object
 * 
 */
public class FormatRenderer extends DefaultTableCellRenderer {
    private Format formatter;

    /*
     *   Use the specified formatter to format the Object
     */
    public FormatRenderer(Format formatter) {
        this.formatter = formatter;
    }

    @Override
    public void setValue(Object value) {
        //  Format the Object before setting its value in the renderer
        try {
            formatter = new DecimalFormat("#0.00");
            if (value != null) value = formatter.format(value);
        } catch(IllegalArgumentException ex) {}

        super.setValue(value);
    }

    /*
     *  Use the default date/time formatter for the default locale
     */
    public static FormatRenderer getDateTimeRenderer() {
        return new FormatRenderer(DateFormat.getDateTimeInstance());
    }

    /*
     *  Use the default time formatter for the default locale
     */
    public static FormatRenderer getTimeRenderer() {
        return new FormatRenderer(DateFormat.getTimeInstance());
    }
}
