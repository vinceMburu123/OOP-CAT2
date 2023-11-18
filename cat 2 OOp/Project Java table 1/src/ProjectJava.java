import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ProjectJava extends JFrame {

    private JTextField idField, nameField, addressField, contactField;
    private JRadioButton maleRadioButton, femaleRadioButton;
    private ButtonGroup genderGroup;
    private JSpinner dateOfBirthSpinner;
    private JButton submitButton, resetButton;

    public ProjectJava() {
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