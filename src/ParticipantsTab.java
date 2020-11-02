import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.DefaultButtonModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JOptionPane;

public class ParticipantsTab extends JPanel
{
   private int selectedIndex = -1;

   private JPanel participantsNorthPanel;

   private JPanel participantsSearchPanel;
   private JLabel participantsNameLabel;
   private JTextField participantsNameField;
   private JTextField searchParticipantsField;
   private JLabel participantsPhoneLabel;
   private JTextField participantsPhoneField;
   private JLabel participantsEmailLabel;
   private JTextField participantsEmailField;
   private JLabel participantsAddressLabel;
   private JTextField participantsAddressField;
   private JLabel participantsIsMemberLabel;
   private JCheckBox participantsIsMemberField;
   private JLabel participantsPaymentYearLabel;
   private JTextField participantsPaymentYearField;
   private JLabel participantsMembershipDateLabel;
   private JTextField participantsMembershipDateField;
   private TitledBorder searchParticipantsTitle;

   private JPanel participantsEditPanel;
   private TitledBorder participantsEditTitle;

   private JButton createParticipantsButton;
   private JButton editParticipantsButton;
   private JButton deleteParticipantsButton;
   private JScrollPane participantsScrollPane;
   private JTable participantsTable;
   private DefaultTableModel participantsTableModel;

   private Object[][] participantsData;
   private String[] participantsColumnNames;

   private ListSelectionModel listSelectionModel;

   private ParticipantList allParticipants;

   public ParticipantsTab(ParticipantList allParticipants)
   {

      this.allParticipants = allParticipants;

      // allParticipants
      participantsColumnNames = new String[] { "Name", "Address",
            "Phone number", "E-mail", "Is Member", "Payment Year",
            "Membership Date" };// columns
                                // name
                                // of
                                // participant

      DefaultTableModel participantsTableModel = new DefaultTableModel(
            participantsData, participantsColumnNames)
      {
         @Override
         public boolean isCellEditable(int row, int column)
         {
            return false;
         }
      };

      for (int i = 0; i < allParticipants.size(); i++)
      {
         participantsTableModel
               .addRow(new Object[] { allParticipants.get(i).getName(),
                     allParticipants.get(i).getAddress(),
                     allParticipants.get(i).getPhoneNumber(),
                     allParticipants.get(i).getEmail(),
                     allParticipants.get(i).getIsMember(),
                     allParticipants.get(i).getPaymentYear(),
                     allParticipants.get(i).getmembershipDate() });
      }

      participantsTable = new JTable(participantsTableModel);
      participantsScrollPane = new JScrollPane(participantsTable);

      participantsNorthPanel = new JPanel();

      participantsSearchPanel = new JPanel(new BorderLayout());
      participantsEditPanel = new JPanel();

      searchParticipantsField = new JTextField(30);

      participantsNameLabel = new JLabel("Name:");
      participantsNameField = new JTextField(15);
      participantsPhoneLabel = new JLabel("Phone:");
      participantsPhoneField = new JTextField(10);
      participantsEmailLabel = new JLabel("Email:");
      participantsEmailField = new JTextField(15);
      participantsAddressLabel = new JLabel("Address:");
      participantsAddressField = new JTextField(15);
      participantsIsMemberLabel = new JLabel("Member:");
      participantsIsMemberField = new JCheckBox();
      participantsPaymentYearLabel = new JLabel("Payment Year:");
      participantsPaymentYearField = new JTextField(5);
      participantsMembershipDateLabel = new JLabel("Membership Date:");
      participantsMembershipDateField = new JTextField(10);

      createParticipantsButton = new JButton("Create");
      editParticipantsButton = new JButton("Update");
      deleteParticipantsButton = new JButton("Delete");
      participantsEditPanel.setBorder(new EmptyBorder(12, 0, 0, 0));

      participantsEditPanel.setBorder(new TitledBorder("Edit"));

      participantsEditPanel.add(participantsNameLabel);
      participantsEditPanel.add(participantsNameField);
      participantsEditPanel.add(participantsAddressLabel);
      participantsEditPanel.add(participantsAddressField);
      participantsEditPanel.add(participantsPhoneLabel);
      participantsEditPanel.add(participantsPhoneField);
      participantsEditPanel.add(participantsEmailLabel);
      participantsEditPanel.add(participantsEmailField);
      participantsEditPanel.add(participantsIsMemberLabel);
      participantsEditPanel.add(participantsIsMemberField);
      participantsEditPanel.add(participantsPaymentYearLabel);
      participantsEditPanel.add(participantsPaymentYearField);
      participantsEditPanel.add(participantsMembershipDateLabel);
      participantsEditPanel.add(participantsMembershipDateField);
      participantsEditPanel.add(createParticipantsButton);
      participantsEditPanel.add(deleteParticipantsButton);
      participantsEditPanel.add(editParticipantsButton);

      participantsTable.setFillsViewportHeight(true);
      participantsTable.getTableHeader().setReorderingAllowed(false);
      participantsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      participantsTable.setModel(participantsTableModel);

      listSelectionModel = participantsTable.getSelectionModel();
      listSelectionModel
            .addListSelectionListener(new MyListSelectionListener());
      participantsTable.setSelectionModel(listSelectionModel);

      searchParticipantsTitle = BorderFactory.createTitledBorder("Search");
      participantsSearchPanel.setBorder(searchParticipantsTitle);
      participantsSearchPanel.add(searchParticipantsField);

      // Search

      DefaultTableModel table = (DefaultTableModel) participantsTable
            .getModel();

      TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(
            table);
      participantsTable.setRowSorter(tr);

      tr.setSortable(2, false);
      tr.setSortable(3, false);
      tr.setSortable(6, false);

      searchParticipantsField.addKeyListener(new KeyAdapter()
      {
         public void keyReleased(KeyEvent e)
         {

            String search = searchParticipantsField.getText().toLowerCase();

            tr.setRowFilter(RowFilter.regexFilter("(?i)" + search));
         }
      }

      );
      // Search END

      participantsNorthPanel.setLayout(new BorderLayout());

      participantsScrollPane
            .setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

      participantsEditPanel.add(createParticipantsButton);
      participantsEditPanel.add(editParticipantsButton);
      participantsEditPanel.add(deleteParticipantsButton);

      participantsNorthPanel.add(participantsSearchPanel, BorderLayout.NORTH);
      participantsNorthPanel.add(participantsEditPanel, BorderLayout.SOUTH);

      setLayout(new BorderLayout());
      add(participantsNorthPanel, BorderLayout.NORTH);
      add(participantsScrollPane, BorderLayout.CENTER);

      // Create Participants
      participantsTable.setModel(participantsTableModel);

      createParticipantsButton.addActionListener(new ActionListener()
      {
         @Override
         public void actionPerformed(ActionEvent e)
         {
            if (participantsIsMemberField.isSelected()
                  && !(participantsNameField.getText().equals("")
                        || participantsAddressField.getText().equals("")
                        || participantsPhoneField.getText().equals("")
                        || participantsEmailField.getText().equals("")
                        || participantsPaymentYearField.getText().equals("")
                        || participantsMembershipDateField.getText()
                              .equals("")))
            {
               int opcion = JOptionPane.showConfirmDialog(null,
                     "Create new participant?\nName: "
                           + participantsNameField.getText() + "\nAddress: "
                           + participantsAddressField.getText() + "\nPhone: "
                           + participantsPhoneField.getText() + "\nE-mail: "
                           + participantsEmailField.getText() + "\nMember: "
                           + participantsIsMemberField.isSelected()
                           + "\nPaymentYear: "
                           + participantsPaymentYearField.getText()
                           + "\nMembershipDate: "
                           + participantsMembershipDateField.getText(),
                     "Confirm message", JOptionPane.YES_NO_OPTION);
               if (opcion == 0)
               {
                  String dateString = participantsMembershipDateField.getText();
                  String[] dayMonthYear = dateString.split("/");
                  int day = Integer.parseInt(dayMonthYear[0]);
                  int month = Integer.parseInt(dayMonthYear[1]);
                  int year = Integer.parseInt(dayMonthYear[2]);

                  // add row to the model
                  participantsTableModel
                        .addRow(new Object[] { participantsNameField.getText(),
                              participantsAddressField.getText(),
                              participantsPhoneField.getText(),
                              participantsEmailField.getText(),
                              participantsIsMemberField.isSelected(),
                              participantsPaymentYearField.getText(),
                              participantsMembershipDateField.getText() });

                  Participant newParticipant = new Participant(
                        participantsNameField.getText(),
                        participantsAddressField.getText(),
                        participantsPhoneField.getText(),
                        participantsEmailField.getText(),
                        participantsIsMemberField.isSelected(),
                        Integer
                              .parseInt(participantsPaymentYearField.getText()),
                        new MyDate(day, month, year));
                  allParticipants.add(newParticipant);
               }
            }
            else if (participantsIsMemberField.isSelected() == false
                  && !(participantsNameField.getText().equals("") // Create
                                                                  // participant,
                                                                  // not member
                        || participantsAddressField.getText().equals("")
                        || participantsPhoneField.getText().equals("")
                        || participantsEmailField.getText().equals("")))
            {
               int opcion = JOptionPane.showConfirmDialog(null,
                     "Create new participant?\nName: "
                           + participantsNameField.getText() + "\nAddress: "
                           + participantsAddressField.getText() + "\nPhone: "
                           + participantsPhoneField.getText() + "\nE-mail: "
                           + participantsEmailField.getText() + "\nMember: "
                           + participantsIsMemberField.isSelected(),
                     "Confirm message", JOptionPane.YES_NO_OPTION);
               if (opcion == 0)
               {
                  // add row to the model
                  participantsTableModel
                        .addRow(new Object[] { participantsNameField.getText(),
                              participantsAddressField.getText(),
                              participantsPhoneField.getText(),
                              participantsEmailField.getText(),
                              participantsIsMemberField.isSelected(), "", "" });

                  Participant newParticipant = new Participant(
                        participantsNameField.getText(),
                        participantsAddressField.getText(),
                        participantsPhoneField.getText(),
                        participantsEmailField.getText(),
                        participantsIsMemberField.isSelected(), 0, null);
                  allParticipants.add(newParticipant);
               }
            }
            else
            {
               JOptionPane.showMessageDialog(null, "Input every field",
                     "Attention!", JOptionPane.ERROR_MESSAGE);
            }
         }
      });
      // Create Participants END

      // Delete
      deleteParticipantsButton.addActionListener(new ActionListener()
      {
         @Override
         public void actionPerformed(ActionEvent e)
         {
            // i = the index of the selected row
            int i = participantsTable.getSelectedRow();
            if (i >= 0)
            {
               int opcion = JOptionPane.showConfirmDialog(null, "Are you sure?",
                     "Confirm message", JOptionPane.YES_NO_OPTION);

               if (opcion == 0)
               {
                  tr.setRowFilter(null);
                  allParticipants.remove(selectedIndex);
                  participantsTableModel.removeRow(selectedIndex);
               }
               else
               {
                  return;
               }
            }

            else
            {
               JOptionPane.showMessageDialog(null, "Please select a row.",
                     "Error Message", JOptionPane.ERROR_MESSAGE);
            }
         }
      });
      // Delete END

      // Update selected
      editParticipantsButton.addActionListener(new ActionListener()
      {
         @Override
         public void actionPerformed(ActionEvent e)
         {
            if (selectedIndex > -1)
            {
               if (participantsIsMemberField.isSelected()
                     && participantsPaymentYearField.getText().equals("")
                     && participantsMembershipDateField.getText().equals(""))
               {
                  JOptionPane.showMessageDialog(null,
                        "Please fill out all fields.", "Error Message",
                        JOptionPane.ERROR_MESSAGE);
               }
               else
               {
                  int option = JOptionPane.showConfirmDialog(null,
                        "Update participant data?", "Confirm message",
                        JOptionPane.YES_NO_OPTION);
                  if (option == 0)
                  {

                     allParticipants.get(selectedIndex)
                           .setName(participantsNameField.getText());
                     participantsTableModel.setValueAt(
                           participantsNameField.getText(), selectedIndex, 0);
                     allParticipants.get(selectedIndex)
                           .setAddress(participantsAddressField.getText());
                     participantsTableModel.setValueAt(
                           participantsAddressField.getText(), selectedIndex,
                           1);
                     allParticipants.get(selectedIndex)
                           .setPhoneNumber(participantsPhoneField.getText());
                     participantsTableModel.setValueAt(
                           participantsPhoneField.getText(), selectedIndex, 2);
                     allParticipants.get(selectedIndex)
                           .setEmail(participantsEmailField.getText());
                     participantsTableModel.setValueAt(
                           participantsEmailField.getText(), selectedIndex, 3);
                     participantsTableModel.setValueAt(
                           participantsIsMemberField.isSelected(),
                           selectedIndex, 4);
                     allParticipants.get(selectedIndex)
                           .setIsMember(participantsIsMemberField.isSelected());

                     if (participantsIsMemberField.isSelected())
                     {
                        String dateString = participantsMembershipDateField
                              .getText();
                        String[] dayMonthYear = dateString.split("/");
                        int day = Integer.parseInt(dayMonthYear[0]);
                        int month = Integer.parseInt(dayMonthYear[1]);
                        int year = Integer.parseInt(dayMonthYear[2]);

                        participantsTableModel.setValueAt(
                              participantsPaymentYearField.getText(),
                              selectedIndex, 5);
                        allParticipants.get(selectedIndex)
                              .setIsPaymentYear(Integer.parseInt(
                                    participantsPaymentYearField.getText()));
                        participantsTableModel.setValueAt(
                              participantsMembershipDateField.getText(),
                              selectedIndex, 6);
                        allParticipants.get(selectedIndex)
                              .setMembershipDate(new MyDate(day, month, year));
                     }
                     else
                     {
                        participantsTableModel.setValueAt(
                              participantsPaymentYearField.getText(),
                              selectedIndex, 5);
                        allParticipants.get(selectedIndex).setIsPaymentYear(0);
                        participantsTableModel.setValueAt(
                              participantsMembershipDateField.getText(),
                              selectedIndex, 6);
                        allParticipants.get(selectedIndex)
                              .setMembershipDate(null);
                     }
                  }
               }
            }
            else
            {
               JOptionPane.showMessageDialog(null,
                     "Please select a participant.", "Attention!",
                     JOptionPane.ERROR_MESSAGE);
            }
         }

      });
      // Update selected END

      // Disable member fields when checkbox false
      participantsMembershipDateField.setEnabled(false);
      participantsPaymentYearField.setEnabled(false);

      participantsIsMemberField.addActionListener(new ActionListener()
      {
         @Override
         public void actionPerformed(ActionEvent e)
         {
            if (participantsIsMemberField.isSelected())
            {
               participantsMembershipDateField.setEnabled(true);
               participantsPaymentYearField.setEnabled(true);
            }
            else
            {
               participantsMembershipDateField.setEnabled(false);
               participantsMembershipDateField.setText("");
               participantsPaymentYearField.setEnabled(false);
               participantsPaymentYearField.setText("");
            }

         }
      });

   }

   class MyListSelectionListener implements ListSelectionListener
   {
      @Override
      public void valueChanged(ListSelectionEvent arg0)
      {
         if (!arg0.getValueIsAdjusting()) // Prevent double invoke
         {
            if (participantsTable.getSelectedRow() != -1)
            {
               selectedIndex = participantsTable.convertRowIndexToModel(
                     participantsTable.getSelectedRow());
               System.out.println("Current row: " + selectedIndex);

               // Always show info from the selected object in the fields
               participantsNameField
                     .setText(allParticipants.get(selectedIndex).getName());
               participantsPhoneField.setText(
                     allParticipants.get(selectedIndex).getPhoneNumber());
               participantsEmailField
                     .setText(allParticipants.get(selectedIndex).getEmail());
               participantsAddressField
                     .setText(allParticipants.get(selectedIndex).getAddress());
               participantsIsMemberField.setSelected(
                     allParticipants.get(selectedIndex).getIsMember());
               if (allParticipants.get(selectedIndex).getIsMember())
               {
                  participantsPaymentYearField.setText(Integer.toString(
                        allParticipants.get(selectedIndex).getPaymentYear()));
                  participantsMembershipDateField.setText(allParticipants
                        .get(selectedIndex).getmembershipDate().toString());
               }
               else
               {
                  participantsPaymentYearField.setText("");
                  participantsMembershipDateField.setText("");
               }

               // disable fields
               if (participantsIsMemberField.isSelected())
               {
                  participantsMembershipDateField.setEnabled(true);
                  participantsPaymentYearField.setEnabled(true);
               }
               else
               {
                  participantsMembershipDateField.setEnabled(false);
                  participantsMembershipDateField.setText("");
                  participantsPaymentYearField.setEnabled(false);
                  participantsPaymentYearField.setText("");
               }
            }
            else
            {
               participantsNameField.setText("");
               participantsPhoneField.setText("");
               participantsEmailField.setText("");
               participantsAddressField.setText("");
               participantsIsMemberField.setSelected(false);
               participantsPaymentYearField.setText("");
               participantsMembershipDateField.setText("");
            }

         }
      }

   }
}