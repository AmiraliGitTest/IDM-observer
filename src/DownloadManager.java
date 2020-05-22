import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class DownloadManager extends JFrame implements Observer {

    public static JFrame frame;
    public static JPanel panel;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;

    DownloadManager() {
        gui();
    }

    public void gui() {
        frame = new JFrame();
        frame.setBounds(200, 200, 730, 489);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setVisible(true);

        textField = new JTextField();
        textField.setBounds(128, 28, 386, 30);
        frame.getContentPane().add(textField);
        textField.setColumns(200);

        JLabel urllabel = new JLabel("URL");
        urllabel.setBounds(65, 31, 46, 14);
        frame.getContentPane().add(urllabel);

        JLabel filepath = new JLabel("FilePath");
        filepath.setBounds(65, 68, 46, 14);
        frame.getContentPane().add(filepath);

        textField_1 = new JTextField();
        textField_1.setBounds(128, 65, 386, 30);
        frame.getContentPane().add(textField_1);
        textField_1.setColumns(200);

        // "btnStart" starts Download thread.
        JButton btnClear = new JButton("Clear");
        btnClear.setBounds(312, 387, 89, 23);
        frame.getContentPane().add(btnClear);

        JButton btnResume = new JButton("Resume");
        btnResume.setBounds(312, 420, 89, 23);
        frame.getContentPane().add(btnResume);

        JButton btnSubmit = new JButton("Start");
        btnSubmit.setBounds(65, 387, 89, 23);
        frame.getContentPane().add(btnSubmit);

        JButton btnpause = new JButton("Pause");
        btnpause.setBounds(65, 420, 89, 23);
        frame.getContentPane().add(btnpause);

        Download dl = new Download();
        dl.setStatus(1);

        btnSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (textField.getText().isEmpty() || (textField_1.getText().isEmpty()))
                    JOptionPane.showMessageDialog(null, "Data Missing");
                else {
                    try {
                        dl.setLink(textField.getText());
                        dl.setOfilePath(textField_1.getText());
                        dl.setStatus(0);
                        dl.download();

                        JOptionPane.showMessageDialog(null, "DownLoad Started");
                    } catch (Exception e) {
                        System.out.println(e);
                    }

                }
            }
        });

        btnpause.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (dl.getStatus() == 0) {
                        dl.setStatus(1);
                        dl.pause();
                    }
                } catch (Exception E) {
                    System.out.println(E);
                }
            }
        });

        btnpause.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (dl.getStatus() == 1) {
                        dl.setStatus(0);
                    }
                } catch (Exception E) {
                    System.out.println(E);
                }
            }
        });

        btnClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textField_1.setText(null);
                textField.setText(null);

            }
        });


    }

    @Override
    public void update(Observable o, Object arg) {
        Download observable = new Download();

    }
}


