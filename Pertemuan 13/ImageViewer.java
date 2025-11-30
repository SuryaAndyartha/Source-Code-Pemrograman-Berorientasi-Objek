import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class ImageViewer extends JFrame {
    private JLabel imageLabel;
    private JButton openButton;

    public ImageViewer() {
        setTitle("Simple Image Viewer");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        imageLabel = new JLabel("", JLabel.CENTER);
        add(imageLabel, BorderLayout.CENTER);

        openButton = new JButton("Open Image");
        add(openButton, BorderLayout.SOUTH);

        openButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                int result = chooser.showOpenDialog(null);

                if(result == JFileChooser.APPROVE_OPTION) {
                    File file = chooser.getSelectedFile();
                    ImageIcon icon = new ImageIcon(file.getAbsolutePath());

                    // Resize image to fit the window
                    Image img = icon.getImage();
                    Image scaled = img.getScaledInstance(550, 400, Image.SCALE_SMOOTH);

                    imageLabel.setIcon(new ImageIcon(scaled));
                }
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new ImageViewer();
    }
}
