package GUITools;

import java.awt.*;
import javax.swing.*;
import java.util.Calendar;

public class DigitalClock extends JFrame {
    public static final int TWELVE_HOUR = 12;
    public static final int TWENTY_FOUR_HOUR = 24;

    private int format = TWENTY_FOUR_HOUR;
    private boolean displaySeconds = true;

    private JLabel clockLabel;

    public DigitalClock() {
        // Set up the JFrame
        setTitle("Digital Clock");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Set up the clock label
        clockLabel = new JLabel("", JLabel.CENTER);
        clockLabel.setFont(new Font("Arial", Font.BOLD, 30));
        add(clockLabel, BorderLayout.CENTER);

        // Start the clock
        Timer timer = new Timer(1000, e -> updateClock());
        timer.start();

        setVisible(true);
    }

    // Get the current time
    public String timeNow() {
        StringBuilder time = new StringBuilder();
        String amPmFormat = "";
        Calendar now = Calendar.getInstance();

        int hrs = now.get(Calendar.HOUR_OF_DAY);
        int min = now.get(Calendar.MINUTE);
        int sec = now.get(Calendar.SECOND);

        if (this.getFormat() == TWELVE_HOUR) {
            if (hrs > 12) {
                hrs -= 12;
                amPmFormat = " PM";
            } else if (hrs == 0) {
                hrs = 12; // Midnight case
                amPmFormat = " AM";
            } else {
                amPmFormat = " AM";
            }
            time.append(hrs).append(":").append(zero(min));
        } else {
            time.append(zero(hrs)).append(":").append(zero(min));
        }

        if (this.displaySeconds) {
            time.append(":").append(zero(sec));
        }

        return time.append(amPmFormat).toString();
    }

    public String zero(int num) {
        return (num < 10) ? ("0" + num) : ("" + num); // Add leading zero if needed
    }

    @Override
    public String toString() {
        return timeNow();
    }

    public int getFormat() {
        return format;
    }

    public void setFormat(int format) {
        this.format = format;
    }

    private void updateClock() {
        clockLabel.setText(timeNow());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(DigitalClock::new);
    }
}
