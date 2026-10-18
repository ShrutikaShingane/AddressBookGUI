import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AddressBookGUI extends JFrame {

    // Step 4: ArrayList to store contacts
    private ArrayList<Contact> contactList = new ArrayList<>();

    // Step 1: JTable and Model
    private JTable table;
    private DefaultTableModel tableModel;

    public AddressBookGUI() {
        setTitle("Address Book");
        setSize(650, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Table setup
        String[] columnNames = {"Name", "Phone", "Email"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Table cells are read-only directly
            }
        };
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Buttons Panel setup
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton btnAdd = new JButton("Add Contact");
        JButton btnEdit = new JButton("Edit Contact");
        JButton btnDelete = new JButton("Delete Contact");

        buttonPanel.add(btnAdd);
        buttonPanel.add(btnEdit);
        buttonPanel.add(btnDelete);
        add(buttonPanel, BorderLayout.SOUTH);

        // --- Step 2: Add Dialog ---
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showContactDialog(null, -1);
            }
        });

        // --- Step 2: Edit Dialog ---
        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(AddressBookGUI.this, 
                        "Please select a contact to edit.", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                Contact selectedContact = contactList.get(selectedRow);
                showContactDialog(selectedContact, selectedRow);
            }
        });

        // --- Step 3 & 4: Delete Button (removes from model and ArrayList) ---
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(AddressBookGUI.this, 
                        "Please select a contact to delete.", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                int confirm = JOptionPane.showConfirmDialog(AddressBookGUI.this, 
                        "Are you sure you want to delete this contact?", "Confirm Delete", JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    contactList.remove(selectedRow);      // Sync ArrayList
                    tableModel.removeRow(selectedRow);    // Remove from table view
                }
            }
        });
    }

    // Dialog for Adding or Editing contacts
    private void showContactDialog(Contact contact, int rowIndex) {
        boolean isEdit = (contact != null);

        JTextField txtName = new JTextField(isEdit ? contact.getName() : "", 15);
        JTextField txtPhone = new JTextField(isEdit ? contact.getPhone() : "", 15);
        JTextField txtEmail = new JTextField(isEdit ? contact.getEmail() : "", 15);

        JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));
        panel.add(new JLabel("Name:"));
        panel.add(txtName);
        panel.add(new JLabel("Phone:"));
        panel.add(txtPhone);
        panel.add(new JLabel("Email:"));
        panel.add(txtEmail);

        String dialogTitle = isEdit ? "Edit Contact" : "Add Contact";
        int result = JOptionPane.showConfirmDialog(this, panel, dialogTitle, 
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String name = txtName.getText().trim();
            String phone = txtPhone.getText().trim();
            String email = txtEmail.getText().trim();

            if (name.isEmpty() || phone.isEmpty() || email.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (isEdit) {
                // Update contact in ArrayList and Table
                contact.setName(name);
                contact.setPhone(phone);
                contact.setEmail(email);

                tableModel.setValueAt(name, rowIndex, 0);
                tableModel.setValueAt(phone, rowIndex, 1);
                tableModel.setValueAt(email, rowIndex, 2);
            } else {
                // Create new contact, add to ArrayList and Table
                Contact newContact = new Contact(name, phone, email);
                contactList.add(newContact);
                tableModel.addRow(new Object[]{name, phone, email});
            }
        }
    }

    public static void main(String[] args) {
        // Run on Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AddressBookGUI().setVisible(true);
            }
        });
    }
}