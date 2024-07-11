//package SimpleClock;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;


public class SimpleClock extends JFrame implements Runnable {
    
        Calendar calendar;
        SimpleDateFormat timeFormat;
        SimpleDateFormat dayFormat;
        SimpleDateFormat dateFormat;

        SimpleDateFormat timeFormatMilitary;
        SimpleDateFormat timeFormatGMT;
        Boolean isClickedOnce = false;
    
        JLabel timeLabel;
        JLabel dayLabel;
        JLabel dateLabel;
        String time;
        String day;
        String date;

        SimpleClock() {
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setTitle("Digital Clock");
            this.setLayout(new FlowLayout());
            this.setSize(410, 230);
            this.setResizable(false);
    
            timeFormat = new SimpleDateFormat("hh:mm:ss a");

            timeFormatMilitary = new SimpleDateFormat("HH:mm:ss");
            timeFormatGMT = new SimpleDateFormat("HH:mm:ss z");
            timeFormatGMT.setTimeZone(TimeZone.getTimeZone("GMT"));

            dayFormat=new SimpleDateFormat("EEEE");
            dateFormat=new SimpleDateFormat("MMMMM dd, yyyy");
            timeLabel = new JLabel();
            timeLabel.setFont(new Font("SANS_SERIF", Font.PLAIN, 55));
            timeLabel.setBackground(Color.BLACK);
            timeLabel.setForeground(Color.WHITE);
            timeLabel.setOpaque(true);
            dayLabel=new JLabel();
            dayLabel.setFont(new Font("Ink Free",Font.BOLD,34));
    
            dateLabel=new JLabel();
            dateLabel.setFont(new Font("Ink Free",Font.BOLD,30));

            JButton militaryTime = new JButton("24HR");
            militaryTime.setPreferredSize(new Dimension(100, 30));
            militaryTime.setBackground(Color.black);
            militaryTime.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(!isClickedOnce){
                        timeFormat = timeFormatMilitary;
                    } else {
                        timeFormat = new SimpleDateFormat("hh:mm:ss a");
                    }
                    isClickedOnce = !isClickedOnce;
                }
            });

            JButton gmtTime = new JButton("GMT");
            gmtTime.setPreferredSize(new Dimension(100, 30));
            gmtTime.setBackground(Color.black);
            gmtTime.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(!isClickedOnce){
                        timeFormat = timeFormatGMT;
                    } else {
                        timeFormat = new SimpleDateFormat("hh:mm:ss a");
                    }
                    isClickedOnce = !isClickedOnce;
                }
            });

            this.add(gmtTime);
            this.add(militaryTime);

            this.add(timeLabel);
            this.add(dayLabel);
            this.add(dateLabel);
            this.setVisible(true);

            run();
        }

        public static void main (String[] args) {
            SimpleClock clock = new SimpleClock();
            Thread thread = new Thread(clock);
            thread.start();
        }

    @Override
    public void run() {
        do {
            time = timeFormat.format(Calendar.getInstance().getTime());
            timeLabel.setText(time);

            day = dayFormat.format(Calendar.getInstance().getTime());
            dayLabel.setText(day);

            date = dateFormat.format(Calendar.getInstance().getTime());
            dateLabel.setText(date);

            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.getStackTrace();
            }
        } while (true);
    }
}
