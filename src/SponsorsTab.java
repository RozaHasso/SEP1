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

public class SponsorsTab extends JPanel
{
   private int selectedIndex = -1;

   private JPanel sponsorsNorthPanel;

   private JPanel sponsorsSearchPanel;
   private JLabel sponsorsNameLabel;
   private JTextField sponsorsNameField;
   private JTextField searchSponsorsField;
   private JLabel sponsorsPhoneLabel;
   private JTextField sponsorsPhoneField;
   private JLabel sponsorsEmailLabel;
   private JTextField sponsorsEmailField;
   private JLabel sponsorsCategoryLabel;
   private JTextField sponsorsCategoryField;
   private TitledBorder searchSponsorsTitle;

   private JPanel sponsorsEditPanel;
   private TitledBorder sponsorsEditTitle;

   private JButton createSponsorsButton;
   private JButton editSponsorsButton;
   private JButton deleteSponsorsButton;
   private JScrollPane sponsorsScrollPane;
   private JTable sponsorsTable;
   private DefaultTableModel sponsorsTableModel;

   private Object[][] sponsorsData;
   private String[] sponsorsColumnNames;

   private ListSelectionModel listSelectionModel;

   private SponsorList allSponsors;

   public SponsorsTab(SponsorList allSponsors)
   {
      this.allSponsors = allSponsors;

      sponsorsColumnNames = new String[] { "Name", "Phone number", "E-mail",
            "Category" };// columns name of sponsor

      DefaultTableModel sponsorsTableModel = new DefaultTableModel(sponsorsData,
            sponsorsColumnNames)
      {
         @Override
         public boolean isCellEditable(int row, int column)
         {
            return false;
         }
      };

      for (int i = 0; i < allSponsors.size(); i++)
      {
         sponsorsTableModel.addRow(new Object[] { allSponsors.get(i).getName(),
               allSponsors.get(i).getPhoneNumber(),
               allSponsors.get(i).getEmail(),
               allSponsors.get(i).getCategory() });
      }

      sponsorsTable = new JTable(sponsorsTableModel);
      sponsorsScrollPane = new JScrollPane(sponsorsTable);

      sponsorsNorthPanel = new JPanel();

      sponsorsSearchPanel = new JPanel();
      sponsorsEditPanel = new JPanel();

      searchSponsorsField = new JTextField(30);

      sponsorsNameLabel = new JLabel("Name: ");
      sponsorsNameField = new JTextField(15);
      sponsorsPhoneLabel = new JLabel("Phone number: ");
      sponsorsPhoneField = new JTextField(10);
      sponsorsEmailLabel = new JLabel("E-mail: ");
      sponsorsEmailField = new JTextField(15);
      sponsorsCategoryLabel = new JLabel("Category: ");
      sponsorsCategoryField = new JTextField(10);

      createSponsorsButton = new JButton("Create");

      editSponsorsButton = new JButton("Update");
      deleteSponsorsButton = new JButton("Delete");
      sponsorsEditPanel.setBorder(new EmptyBorder(12, 0, 0, 0));

      sponsorsEditPanel.setBorder(new TitledBorder("Edit"));

      sponsorsEditPanel.add(sponsorsNameLabel);
      sponsorsEditPanel.add(sponsorsNameField);
      sponsorsEditPanel.add(sponsorsPhoneLabel);
      sponsorsEditPanel.add(sponsorsPhoneField);
      sponsorsEditPanel.add(sponsorsEmailLabel);
      sponsorsEditPanel.add(sponsorsEmailField);
      sponsorsEditPanel.add(sponsorsCategoryLabel);
      sponsorsEditPanel.add(sponsorsCategoryField);
      sponsorsEditPanel.add(createSponsorsButton);
      sponsorsEditPanel.add(deleteSponsorsButton);
      sponsorsEditPanel.add(editSponsorsButton);

      sponsorsTable.setFillsViewportHeight(true);
      sponsorsTable.getTableHeader().setReorderingAllowed(false);
      sponsorsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      sponsorsTable.setModel(sponsorsTableModel);

      listSelectionModel = sponsorsTable.getSelectionModel();
      listSelectionModel
            .addListSelectionListener(new MyListSelectionListener());
      sponsorsTable.setSelectionModel(listSelectionModel);

      searchSponsorsTitle = BorderFactory.createTitledBorder("Search");
      sponsorsSearchPanel.setBorder(searchSponsorsTitle);
      sponsorsSearchPanel.add(searchSponsorsField);

      // Search

      DefaultTableModel table = (DefaultTableModel) sponsorsTable.getModel();

      TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(
            table);
      sponsorsTable.setRowSorter(tr);
      tr.setSortable(1, false);
      tr.setSortable(2, false);

      searchSponsorsField.addKeyListener(new KeyAdapter()
      {
         public void keyReleased(KeyEvent e)
         {

            String search = searchSponsorsField.getText().toLowerCase();

            tr.setRowFilter(RowFilter.regexFilter("(?i)" + search));
         }
      }

      );
      // Search END

      sponsorsNorthPanel.setLayout(new BorderLayout());

      sponsorsScrollPane
            .setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

      sponsorsEditPanel.add(createSponsorsButton);
      sponsorsEditPanel.add(editSponsorsButton);
      sponsorsEditPanel.add(deleteSponsorsButton);

      sponsorsNorthPanel.add(sponsorsSearchPanel, BorderLayout.EAST);
      sponsorsNorthPanel.add(sponsorsEditPanel, BorderLayout.WEST);

      setLayout(new BorderLayout());
      add(sponsorsNorthPanel, BorderLayout.NORTH);
      add(sponsorsScrollPane, BorderLayout.CENTER);

      // button create row
      Object[] row = new Object[4];
      sponsorsTable.setModel(sponsorsTableModel);

      createSponsorsButton.addActionListener(new ActionListener()
      {

         @Override
         public void actionPerformed(ActionEvent e)
         {
            row[0] = sponsorsNameField.getText();
            row[1] = sponsorsPhoneField.getText();
            row[2] = sponsorsEmailField.getText();
            row[3] = sponsorsCategoryField.getText();

            if (!(sponsorsNameField.getText().equals("")
                  || sponsorsPhoneField.getText().equals("")
                  || sponsorsEmailField.getText().equals("")
                  || sponsorsCategoryField.getText().equals("")))
            {
               int opcion = JOptionPane.showConfirmDialog(null,
                     "Create new sponsor?\nName: " + sponsorsNameField.getText()
                           + "\nPhone number: " + sponsorsPhoneField.getText()
                           + "\nE-mail: " + sponsorsEmailField.getText()
                           + "\nCategory: " + sponsorsCategoryField.getText(),
                     "Confirm message", JOptionPane.YES_NO_OPTION);
               if (opcion == 0)
               {
                  // add row to the model
                  sponsorsTableModel.addRow(row);
                  allSponsors.add(new Sponsor(sponsorsNameField.getText(),
                        sponsorsPhoneField.getText(),
                        sponsorsEmailField.getText(),
                        sponsorsCategoryField.getText()));
               }
               else
               {
                  return;
               }

            }
            else
            {
               JOptionPane.showMessageDialog(null, "Input every field",
                     "Attention!", JOptionPane.ERROR_MESSAGE);
            }
         }
      });
      // the end of button create row

      // Delete
      deleteSponsorsButton.addActionListener(new ActionListener()
      {
         @Override
         public void actionPerformed(ActionEvent e)
         {
            // i = the index of the selected row
            int i = sponsorsTable.getSelectedRow();
            if (i >= 0)
            {
               int opcion = JOptionPane.showConfirmDialog(null, "Are you sure?",
                     "Confirm message", JOptionPane.YES_NO_OPTION);

               if (opcion == 0)
               {
                  tr.setRowFilter(null);
                  allSponsors.remove(selectedIndex);
                  sponsorsTableModel.removeRow(selectedIndex);
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
      editSponsorsButton.addActionListener(new ActionListener()
      {
         @Override
         public void actionPerformed(ActionEvent e)
         {
            if (selectedIndex > -1)
            {
               if (!(sponsorsNameField.getText().equals("")
                     || sponsorsPhoneField.getText().equals("")
                     || sponsorsEmailField.getText().equals("")
                     || sponsorsCategoryField.getText().equals("")))
               {
                  int option = JOptionPane.showConfirmDialog(null,
                        "Update sponsor data?\nName: "
                              + sponsorsNameField.getText() + "\nPhone number: "
                              + sponsorsPhoneField.getText() + "\nE-mail: "
                              + sponsorsEmailField.getText() + "\nCategory: "
                              + sponsorsCategoryField.getText(),
                        "Confirm message", JOptionPane.YES_NO_OPTION);
                  if (option == 0)
                  {
                     allSponsors.get(selectedIndex)
                           .setName(sponsorsNameField.getText());
                     sponsorsTableModel.setValueAt(sponsorsNameField.getText(),
                           selectedIndex, 0);
                     allSponsors.get(selectedIndex)
                           .setPhoneNumber(sponsorsPhoneField.getText());
                     sponsorsTableModel.setValueAt(sponsorsPhoneField.getText(),
                           selectedIndex, 1);
                     allSponsors.get(selectedIndex)
                           .setEmail(sponsorsEmailField.getText());
                     sponsorsTableModel.setValueAt(sponsorsEmailField.getText(),
                           selectedIndex, 2);
                     allSponsors.get(selectedIndex)
                           .setCategory(sponsorsCategoryField.getText());
                     sponsorsTableModel.setValueAt(
                           sponsorsCategoryField.getText(), selectedIndex, 3);
                  }
               }
               else
               {
                  JOptionPane.showMessageDialog(null, "Input every field",
                        "Attention!", JOptionPane.ERROR_MESSAGE);
               }

            }
            else
               JOptionPane.showMessageDialog(null, "No sponsor selected!",
                     "Attention!", JOptionPane.ERROR_MESSAGE);
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
            if (sponsorsTable.getSelectedRow() != -1)
            {
               selectedIndex = sponsorsTable
                     .convertRowIndexToModel(sponsorsTable.getSelectedRow());
               System.out.println("Current row: " + selectedIndex);

               // Always show info from the selected object in the fields
               sponsorsNameField
                     .setText(allSponsors.get(selectedIndex).getName());
               sponsorsPhoneField
                     .setText(allSponsors.get(selectedIndex).getPhoneNumber());
               sponsorsEmailField
                     .setText(allSponsors.get(selectedIndex).getEmail());
               sponsorsCategoryField
                     .setText(allSponsors.get(selectedIndex).getCategory());
            }
            else
            {
               sponsorsNameField.setText("");
               sponsorsPhoneField.setText("");
               sponsorsEmailField.setText("");
               sponsorsCategoryField.setText("");
            }

         }
      }

   }
}