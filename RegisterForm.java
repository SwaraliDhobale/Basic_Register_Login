import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
public class RegisterForm extends JDialog {
    private JTextField tfName;
    private JTextField tfEmail;
    private JTextField tfAddress;

    private JButton btnRegister;
    private JButton btnCancel;
    private JPanel registerPanel;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;


    public RegisterForm(JFrame parent) {
        setTitle("Create a new account");
        setContentPane(registerPanel);
        setMinimumSize(new Dimension(500, 474));
        setSize(600, 600);
        setModal(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        btnRegister.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                registerUser();
            }
        });
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        setVisible(true);
    }

    private void registerUser() {
        String name = tfName.getText();
        String email = tfEmail.getText();
        String address = tfAddress.getText();
        String password = String.valueOf(passwordField1.getPassword());
        String confirmPassword = String.valueOf(passwordField2.getPassword());

        if (name.isEmpty() || email.isEmpty() || address.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please fill all details",
                    "Try again", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this, "Confirm password does not match", "Try again", JOptionPane.ERROR_MESSAGE);
            return;
        }
        user = addUserToDatabase(name, email, address, password);
        if (user != null) {
            JOptionPane.showMessageDialog(RegisterForm.this,
                    " You are successfully registered!",
                    "Registration successful",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Failed to register", "Try again", JOptionPane.ERROR_MESSAGE);
        }
    }

    public User user;

    private User addUserToDatabase(String name, String email, String address, String password) {
        User user = null;
        final String DB_URL = "jdbc:mysql://localhost:";
        final String USERNAME = "";
        final String PASSWORD = "";

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            Statement stmt = conn.createStatement();
            String sql = "INSERT INTO users (name,email,address,password)" + "VALUES(?,?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, address);
            preparedStatement.setString(4, password);
            //insert row into table
            int addedRows = preparedStatement.executeUpdate();
            if (addedRows > 0) {
                user = new User();
                user.name = name;
                user.email = email;
                user.address = address;
                user.password = password;
            }
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }
    public static void main(String[] args) {

        RegisterForm form = new RegisterForm(null);
    }
}


