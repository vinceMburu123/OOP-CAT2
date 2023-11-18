import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Cat2 extends JFrame {

    private JTextField idField, nameField, addressField, contactField;
    private JRadioButton maleRadioButton, femaleRadioButton;
    private ButtonGroup genderGroup;
    private JSpinner dateOfBirthSpinner;
    private JButton submitButton, resetButton;

    public Cat2() {
        setTitle("registration form");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        addComponents(panel);
        add(panel);

        setVisible(true);
    }

    private void addComponents(JPanel panel) {
        panel.setLayout(new GridLayout(8, 2, 5, 5));

        JLabel idLabel = new JLabel("ID:");
        idField = new JTextField();
        panel.add(idLabel);
        panel.add(idField);

        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField();
        panel.add(nameLabel);
        panel.add(nameField);

        JLabel genderLabel = new JLabel("Gender:");
        maleRadioButton = new JRadioButton("Male");
        femaleRadioButton = new JRadioButton("Female");
        genderGroup = new ButtonGroup();
        genderGroup.add(maleRadioButton);
        genderGroup.add(femaleRadioButton);
        panel.add(genderLabel);
        panel.add(maleRadioButton);
        panel.add(new JLabel()); // Empty cell
        panel.add(femaleRadioButton);

        JLabel dobLabel = new JLabel("Date of Birth:");
        dateOfBirthSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateOfBirthSpinner, "dd/MM/yyyy");
        dateOfBirthSpinner.setEditor(dateEditor);
        panel.add(dobLabel);
        panel.add(dateOfBirthSpinner);

        JLabel addressLabel = new JLabel("Address:");
        addressField = new JTextField();
        panel.add(addressLabel);
        panel.add(addressField);

        JLabel contactLabel = new JLabel("Contact:");
        contactField = new JTextField();
        panel.add(contactLabel);
        panel.add(contactField);

        submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Load the MySQL JDBC driver
                    Class.forName("com.mysql.cj.jdbc.Driver");

                    // Establish the connection
                    //Connection connection = DriverManager.getConnection("jdbc:mysql://mysql-connector-java-5.1.23-bin.jar/cat2","cat" );

                    // You can now use 'connection' to perform database operations
                    // For example, you can call a method to insert data into the database
                    //submitForm(connection);

                    // Close the connection when done
                    //connection.close();
                } catch (Exception ex) {
                  //  ex.printStackTrace();
                    //JOptionPane.showMessageDialog(java.this, "Error submitting the form: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        resetButton = new JButton("Reset");
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetForm();
            }
        });

        panel.add(submitButton);
        panel.add(resetButton);
    }

    private void submitForm(Connection connection) {
        try {
            String id = idField.getText();
            String name = nameField.getText();
            String gender = maleRadioButton.isSelected() ? "Male" : "Female";
            Date dob = (Date) dateOfBirthSpinner.getValue();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String dobString = dateFormat.format(dob);
            String address = addressField.getText();
            String contact = contactField.getText();

            // Create the SQL query
            String sql = "INSERT INTO your_table_name (id, name, gender, dob, address, contact) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, gender);
            preparedStatement.setString(4, dobString);
            preparedStatement.setString(5, address);
            preparedStatement.setString(6, contact);

            preparedStatement.executeUpdate();

            preparedStatement.close();

            JOptionPane.showMessageDialog(this, "Form submitted successfully!");
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error submitting the form: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void resetForm() {
        idField.setText("");
        nameField.setText("");
        genderGroup.clearSelection();
        dateOfBirthSpinner.setValue(new Date());
        addressField.setText("");
        contactField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Cat2();
            }
        });
    }
}