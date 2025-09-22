import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class Clock{
    private JFrame frame;
    private JLabel label;
    private JLabel dateLabel;
    private ClockDisplay clock;
    private boolean clockRunning = false;
    private TimerThread timerThread;

    public Clock(){
        makeFrame();
        clock = new ClockDisplay();
    }

    public void start(){
        clockRunning = true;
        timerThread = new TimerThread(this);
        timerThread.start();
    }

    public void stop(){
        clockRunning = false;
    }

    public boolean isRunning(){
        return clockRunning;
    }

    public void step(){
        clock.timeTick();
        label.setText(clock.getTime());
        dateLabel.setText(clock.getDate());
    }

    private void showAbout(){
        JOptionPane.showMessageDialog(frame,
            "Tugas Mingguan" + 
            "Membuat Jam",
            "Tambahan dengan detik, tanggal, suhu",
            JOptionPane.INFORMATION_MESSAGE);
    }

    private void quit(){
        System.exit(0);
    }

    private void makeFrame(){
        frame = new JFrame("Clock");
        JPanel contentPane = (JPanel) frame.getContentPane();
        contentPane.setBorder(new EmptyBorder(1, 60, 1, 60));
        makeMenuBar(frame);
    
        contentPane.setLayout(new BorderLayout(12, 12));
    
        JPanel centerPanel = new JPanel(new BorderLayout());
    
        label = new JLabel("00:00:00", SwingConstants.CENTER);
        Font displayFont = label.getFont().deriveFont(72.0f);
        label.setFont(displayFont);
        centerPanel.add(label, BorderLayout.CENTER);
    
        dateLabel = new JLabel("dd/MM/yyyy             86°F", SwingConstants.CENTER);
        Font dateFont = dateLabel.getFont().deriveFont(24.0f);
        dateLabel.setFont(dateFont);
        centerPanel.add(dateLabel, BorderLayout.SOUTH);
    
        contentPane.add(centerPanel, BorderLayout.CENTER);
    
        JPanel toolbar = new JPanel();
        JButton startButton = new JButton("Start");
        startButton.addActionListener(e -> start());
        toolbar.add(startButton);
    
        contentPane.add(toolbar, BorderLayout.SOUTH);
    
        frame.pack();
    
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(d.width / 2 - frame.getWidth() / 2,
                          d.height / 2 - frame.getHeight() / 2);
        frame.setVisible(true);
    }


    private void makeMenuBar(JFrame frame){
        final int SHORTCUT_MASK = Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();

        JMenuBar menubar = new JMenuBar();
        frame.setJMenuBar(menubar);

        JMenu menu;
        JMenuItem item;

        menu = new JMenu("File");
        menubar.add(menu);

        item = new JMenuItem("About Clock...");
        item.addActionListener(e -> showAbout());
        menu.add(item);

        menu.addSeparator();

        item = new JMenuItem("Quit");
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, SHORTCUT_MASK));
        item.addActionListener(e -> quit());
        menu.add(item);
    }
}
