import java.awt.BorderLayout;
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

public class LecturersTab extends JPanel
{
   private int selectedIndex = -1;

   private JPanel lecturersNorthPanel;
   
   private JTextField searchLecturersField;
   private TitledBorder searchLecturersTitle;
   
   private JPanel lecturersSearchPanel;
   private JLabel lecturersNameLabel;
   private JTextField lecturersNameField;
   private JLabel lecturersPhoneLabel;
   private JTextField lecturersPhoneField;
   private JLabel lecturersEmailLabel;
   private JTextField lecturersEmailField;
   private JLabel lecturersCategoryLabel;
   private JTextField lecturersCategoryField;
   

   private JPanel lecturersEditPanel;
   private TitledBorder lecturersEditTitle;

   private JButton createLecturersButton;
   private JButton editLecturersButton;
   private JButton deleteLecturersButton;
   private JScrollPane lecturersScrollPane;
   private JTable lecturersTable;
   private DefaultTableModel lecturersTableModel;

   private Object[][] lecturersData;
   private String[] lecturersColumnNames;

   private ListSelectionModel listSelectionModel;

   LecturerList allLecturers;

   public LecturersTab(LecturerList allLecturers)
   {
      // Load all lecturers
      this.allLecturers = allLecturers;

      
      lecturersColumnNames = new String[] { "Name", "Phone number", "E-mail","Category" };// columns name of lecturer

      DefaultTableModel lecturersTableModel = new DefaultTableModel(lecturersData, lecturersColumnNames)
      {
         @Override
         public boolean isCellEditable(int row, int column)
         {
            return false;
         }
      };

      for (int i = 0; i < allLecturers.size(); i++)
      {
         lecturersTableModel.addRow(new Object[] { allLecturers.get(i).getName(),
               allLecturers.get(i).getPhoneNumber(),
               allLecturers.get(i).getEmail(),
               allLecturers.get(i).getCategory() });
      }

      lecturersTable = new JTable(lecturersTableModel);
      lecturersScrollPane = new JScrollPane(lecturersTable);

      lecturersNorthPanel = new JPanel();

      lecturersSearchPanel = new JPanel();
      lecturersEditPanel = new JPanel();

      searchLecturersField = new JTextField(30);

      lecturersNameLabel = new JLabel("Name: ");
      lecturersNameField = new JTextField(15);
      lecturersPhoneLabel = new JLabel("Phone number: ");
      lecturersPhoneField = new JTextField(10);
      lecturersEmailLabel = new JLabel("E-mail: ");
      lecturersEmailField = new JTextField(15);
      lecturersCategoryLabel = new JLabel("Category: ");
      lecturersCategoryField = new JTextField(10);

      createLecturersButton = new JButton("Create");

      editLecturersButton = new JButton("Update");
      deleteLecturersButton = new JButton("Delete");
      lecturersEditPanel.setBorder(new EmptyBorder(12, 0, 0, 0));

      lecturersEditPanel.setBorder(new TitledBorder("Edit"));

      lecturersEditPanel.add(lecturersNameLabel);
      lecturersEditPanel.add(lecturersNameField);
      lecturersEditPanel.add(lecturersPhoneLabel);
      lecturersEditPanel.add(lecturersPhoneField);
      lecturersEditPanel.add(lecturersEmailLabel);
      lecturersEditPanel.add(lecturersEmailField);
      lecturersEditPanel.add(lecturersCategoryLabel);
      lecturersEditPanel.add(lecturersCategoryField);
      lecturersEditPanel.add(createLecturersButton);
      lecturersEditPanel.add(deleteLecturersButton);
      lecturersEditPanel.add(editLecturersButton);

      lecturersTable.setFillsViewportHeight(true);
      lecturersTable.getTableHeader().setReorderingAllowed(false);
      lecturersTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      lecturersTable.setModel(lecturersTableModel);

      listSelectionModel = lecturersTable.getSelectionModel();
      listSelectionModel.addListSelectionListener(new MyListSelectionListener());
      lecturersTable.setSelectionModel(listSelectionModel);

      searchLecturersTitle = BorderFactory.createTitledBorder("Search");
      lecturersSearchPanel.setBorder(searchLecturersTitle);
      lecturersSearchPanel.add(searchLecturersField);

      // Search

      DefaultTableModel table = (DefaultTableModel) lecturersTable.getModel();

      TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(table);
      lecturersTable.setRowSorter(tr);
      tr.setSortable(1, false);
      tr.setSortable(2, false);

      searchLecturersField.addKeyListener(new KeyAdapter()
      {
         public void keyReleased(KeyEvent e)
         {
            String search = searchLecturersField.getText().toLowerCase();
            tr.setRowFilter(RowFilter.regexFilter("(?i)" + search));
         }
      }

      );
      // Search END

      lecturersNorthPanel.setLayout(new BorderLayout());

      lecturersScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

      lecturersEditPanel.add(createLecturersButton);
      lecturersEditPanel.add(editLecturersButton);
      lecturersEditPanel.add(deleteLecturersButton);

      lecturersNorthPanel.add(lecturersSearchPanel, BorderLayout.EAST);
      lecturersNorthPanel.add(lecturersEditPanel, BorderLayout.WEST);

      setLayout(new BorderLayout());
      add(lecturersNorthPanel, BorderLayout.NORTH);
      add(lecturersScrollPane, BorderLayout.CENTER);

      // button create row
      Object[] row = new Object[4];
      lecturersTable.setModel(lecturersTableModel);

      createLecturersButton.addActionListener(new ActionListener()
      {
         @Override
         public void actionPerformed(ActionEvent e)
         {
            row[0] = lecturersNameField.getText();
            row[1] = lecturersPhoneField.getText();
            row[2] = lecturersEmailField.getText();
            row[3] = lecturersCategoryField.getText();

            if (!(lecturersNameField.getText().equals("")
                  || lecturersPhoneField.getText().equals("")
                  || lecturersEmailField.getText().equals("")
                  || lecturersCategoryField.getText().equals("")))
            {

               int opcion = JOptionPane.showConfirmDialog(null,
                     "Create new lecturer?\nName: " + lecturersNameField.getText()
                           + "\nPhone number: " + lecturersPhoneField.getText()
                           + "\nE-mail: " + lecturersEmailField.getText()
                           + "\nCategory: " + lecturersCategoryField.getText(),
                     "Confirm message", JOptionPane.YES_NO_OPTION);
               if (opcion == 0)
               {

                  // add row to the model
                  lecturersTableModel.addRow(row);
                  allLecturers.add(new Lecturer(lecturersNameField.getText(),
                        lecturersPhoneField.getText(),
                        lecturersEmailField.getText(),
                        lecturersCategoryField.getText()));
               }
               else
               {
                  return;
               }

            }
            else
            {
               JOptionPane.showMessageDialog(null, "Input every field", "Attention!", JOptionPane.ERROR_MESSAGE);
            }
         }
      });
      // the end of button create row

      // Delete
      deleteLecturersButton.addActionListener(new ActionListener()
      {
         @Override
         public void actionPerformed(ActionEvent e)
         {
            // i = the index of the selected row
            int i = lecturersTable.getSelectedRow();
            if (i >= 0)
            {

               int opcion = JOptionPane.showConfirmDialog(null, "Are you sure?",
                     "Confirm message", JOptionPane.YES_NO_OPTION);

               if (opcion == 0)
               {
                  ////////////////////////////////////////////////
                  tr.setRowFilter(null);
                  allLecturers.remove(selectedIndex);
                  lecturersTableModel.removeRow(selectedIndex);
               }
               else
               {
                  return;
               }
            }

            else
            {
               JOptionPane.showMessageDialog(null, "Please select a row!","Error Message", JOptionPane.ERROR_MESSAGE);
            }

         }
      });
      // Delete END

      // Update selected
      editLecturersButton.addActionListener(new ActionListener()
      {
         @Override
         public void actionPerformed(ActionEvent e)
         {
            if (selectedIndex > -1)
            {
               if (!(lecturersNameField.getText().equals("")
                     || lecturersPhoneField.getText().equals("")
                     || lecturersEmailField.getText().equals("")
                     || lecturersCategoryField.getText().equals("")))
               {
               
               allLecturers.get(selectedIndex).setName(lecturersNameField.getText());
               lecturersTableModel.setValueAt(lecturersNameField.getText(),selectedIndex,0);
               
               allLecturers.get(selectedIndex).setPhoneNumber(lecturersPhoneField.getText());
               lecturersTableModel.setValueAt(lecturersPhoneField.getText(),selectedIndex,1);
               
               allLecturers.get(selectedIndex).setEmail(lecturersEmailField.getText());
               lecturersTableModel.setValueAt(lecturersEmailField.getText(),selectedIndex,2);
               
               allLecturers.get(selectedIndex).setCategory(lecturersCategoryField.getText());
               lecturersTableModel.setValueAt(lecturersCategoryField.getText(),selectedIndex,3);
               
               }
               else
                  JOptionPane.showMessageDialog(null, "Insert data in every field!","Error Message", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
               JOptionPane.showMessageDialog(null, "Select a row first of all!","Error Message", JOptionPane.ERROR_MESSAGE);
            }
         }
      });
      // Update selected END
   }

   class MyListSelectionListener implements ListSelectionListener
   {
      @Override
      public void valueChanged(ListSelectionEvent arg0)
      {
         if (!arg0.getValueIsAdjusting()) // Prevent double invoke
         {
            if (lecturersTable.getSelectedRow() != -1)
            {
               selectedIndex = lecturersTable
                     .convertRowIndexToModel(lecturersTable.getSelectedRow());
               System.out.println("Current row: " + selectedIndex);

               // Always show info from the selected object in the fields
               lecturersNameField.setText(allLecturers.get(selectedIndex).getName());
               lecturersPhoneField.setText(allLecturers.get(selectedIndex).getPhoneNumber());
               lecturersEmailField.setText(allLecturers.get(selectedIndex).getEmail());
               lecturersCategoryField.setText(allLecturers.get(selectedIndex).getCategory());
            }
            else
            {
               lecturersNameField.setText("");
               lecturersPhoneField.setText("");
               lecturersEmailField.setText("");
               lecturersCategoryField.setText("");
            }

         }
      }

   }
}