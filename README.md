📇 Address Book GUI Application

A lightweight desktop Address Book application built using Java Swing and AWT. This project provides a clean graphical interface to view, add, edit, and delete contacts, with real-time synchronization between an in-memory ArrayList data model and a JTable view.

📌 Features

Interactive Table Display (JTable): View all saved contacts in structured columns (Name, Phone, Email).

Add Contacts: Modal dialog with input validation to prevent blank entries.

Edit Contacts: Select any existing contact to modify their details directly via a pre-filled dialog.

Delete Contacts: Select any contact row to remove it with an instant confirmation prompt.

Data Synchronization: Real-time two-way synchronization between an in-memory ArrayList<Contact> and DefaultTableModel.

Thread-Safe UI: Launched safely using Swing's Event Dispatch Thread (SwingUtilities.invokeLater).

🏗️ Project Architecture & Directory Structure

AddressBookApp/
├── src/
│   ├── Contact.java          # Model class (encapsulates Name, Phone, Email)
│   └── AddressBookGUI.java   # Main View & Controller class (JFrame & Listeners)
└── README.md                 # Project documentation


Class Responsibilities:

Contact.java:

Represents the individual contact entity.

Contains private attributes: name, phone, email.

Exposes getters and setters for data retrieval and modification.

AddressBookGUI.java:

Extends JFrame to create the application window.

Houses the JTable, DefaultTableModel, and action buttons (Add, Edit, Delete).

Maintains an ArrayList<Contact> to store the underlying dataset.

Handles events and input dialogs using JOptionPane.

⚙️ Prerequisites

Java Development Kit (JDK): Version 8 or higher (JDK 11 / 17 / 21 recommended).

IDE: Eclipse IDE for Java Developers (or any compatible IDE such as IntelliJ IDEA / VS Code).

🚀 Setup & Execution Guide (Eclipse IDE)

Step 1: Open Eclipse and Create a New Project

Open Eclipse IDE.

Go to File ➔ New ➔ Java Project.

In Project name, enter AddressBookApp.

Ensure the execution environment JRE is set to JavaSE-8 or higher.

Click Finish. (If prompted to create module-info.java, select Don't Create).

Step 2: Create Model Class (Contact.java)

In the Package Explorer, expand AddressBookApp.

Right-click on the src folder ➔ New ➔ Class.

Set Name to Contact.

Click Finish.

Paste the code from your Contact.java file and save (Ctrl + S).

Step 3: Create Controller & View Class (AddressBookGUI.java)

Right-click on the src folder ➔ New ➔ Class.

Set Name to AddressBookGUI.

Check the option: public static void main(String[] args).

Click Finish.

Paste the code from your AddressBookGUI.java file and save (Ctrl + S).

Step 4: Run the Application

Right-click on AddressBookGUI.java in the Package Explorer.

Select Run As ➔ Java Application.

The Address Book window will appear on your screen.

📖 How to Use the Application

Adding a Contact:

Click the Add Contact button at the bottom.

Fill in the Name, Phone, and Email fields.

Click OK. The new contact will appear in the table immediately.

Editing a Contact:

Click on the row in the table you want to modify.

Click Edit Contact.

Update the fields in the dialog box and click OK. The row and internal list will update.

Deleting a Contact:

Click on the row you wish to remove.

Click Delete Contact.

Confirm the deletion when prompted.

🛠️ Implementation Highlights (Swing Best Practices)

Layout Management: Uses BorderLayout for the main window (table in CENTER, buttons in SOUTH) and GridLayout inside input dialogs for clean alignment.

Event Dispatch Thread (EDT): Wrapped in SwingUtilities.invokeLater() to avoid concurrency and UI-freeze issues.

Input Validation: Protects against empty submissions before mutating model state.

Read-Only Cells: Directly disabled cell editing via isCellEditable(), routing all edits cleanly through the Edit dialog.