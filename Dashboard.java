import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Dashboard extends JFrame {
    private JPanel DashboardPanel;
    private JLabel Admin;
    private JButton registerButton;
    private JButton loginButton;

    public Dashboard() {
        setTitle("Dashboard");
        setContentPane(DashboardPanel);
        setMinimumSize(new Dimension(500, 429));
        setSize(600, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegisterForm registerForm = new RegisterForm(Dashboard.this);
                User user = registerForm.user;
                if (user != null) {
                    JOptionPane.showMessageDialog(Dashboard.this,
                            "New User:" + user.name,
                            "Successful Registration",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });



                loginButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed (ActionEvent e){
            LoginForm loginForm = new LoginForm(Dashboard.this);
            User user = loginForm.user;
            if (user != null) {
                JOptionPane.showMessageDialog(Dashboard.this,
                        "Authentication successful!",
                        "You have logged in",
                        JOptionPane.INFORMATION_MESSAGE);
            }

        }
    });
                }

    public static void main(String[] args) {
        Dashboard form=new Dashboard();
    }
}


