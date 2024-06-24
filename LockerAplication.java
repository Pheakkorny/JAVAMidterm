import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LockerApplication extends JFrame {
    private String savedPassword = null;
    private JTextField passwordField;
    private JLabel statusLabel;

    public LockerApplication() {
        setTitle("Locker Application");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        passwordField = new JTextField();
        passwordField.setEditable(false);
        passwordField.setHorizontalAlignment(JTextField.CENTER);
        
        statusLabel = new JLabel("Enter your passcode", SwingConstants.CENTER);
        
        JPanel buttonPanel = new JPanel(new GridLayout(4, 3));
        
        for (int i = 1; i <= 9; i++) {
            String num = String.valueOf(i);
            JButton button = new JButton(num);
            button.addActionListener(new NumberButtonAction());
            buttonPanel.add(button);
        }

        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(new ClearButtonAction());
        buttonPanel.add(clearButton);
        
        JButton zeroButton = new JButton("0");
        zeroButton.addActionListener(new NumberButtonAction());
        buttonPanel.add(zeroButton);
        
        JButton enterButton = new JButton("Enter");
        enterButton.addActionListener(new EnterButtonAction());
        buttonPanel.add(enterButton);

        add(passwordField, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(statusLabel, BorderLayout.SOUTH);
    }

    private class NumberButtonAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String input = e.getActionCommand();
            passwordField.setText(passwordField.getText() + input);
        }
    }

    private class ClearButtonAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            passwordField.setText("");
        }
    }

    private class EnterButtonAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String enteredPassword = passwordField.getText();
            
            if (savedPassword == null) {
                savedPassword = enteredPassword;
                statusLabel.setText("Password Set");
            } else {
                if (enteredPassword.equals(savedPassword)) {
                    statusLabel.setText("Correct Password");
                } else {
                    statusLabel.setText("Incorrect Password");
                }
            }
            
            passwordField.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LockerApplication app = new LockerApplication();
            app.setVisible(true);
        });
    }
}
