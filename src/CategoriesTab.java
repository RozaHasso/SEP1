import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.table.DefaultTableModel;

public class CategoriesTab extends JPanel
{
   private JPanel northPanel;
   ArrayList<String> categories = new ArrayList<String>();

   private JTable table;
   private DefaultTableModel tableModel;
   private EventList allEvents;

   private String[] columnNames;
   private Object[][] data;

   public CategoriesTab()
   {
      allEvents = new EventList();
      EventFileAdapter eventAdapter = new EventFileAdapter("events.bin");
      allEvents = eventAdapter.getAllEvents();

      // Get all categories to a list
      for (int i = 0; i < allEvents.size(); i++)
      {
         if (!categories.contains(allEvents.get(i).getCategory()))
            categories.add(allEvents.get(i).getCategory());
      }

      String[] categoriesArray = categories.toArray(new String[0]);

      JComboBox comboBox = new JComboBox(categoriesArray);

      northPanel = new JPanel();

      columnNames = new String[] { "Name", "Address", "Phone number", "E-mail",
            "Payment Year", "Membership Date" };

      DefaultTableModel tableModel = new DefaultTableModel(data, columnNames)
      {
         @Override
         public boolean isCellEditable(int row, int column)
         {
            return false;
         }
      };

      table = new JTable(tableModel);
      JScrollPane center = new JScrollPane(table);
      table.getTableHeader().setReorderingAllowed(false);
      
      northPanel.add(new JLabel("Please select a category to see which members are interested in that category: "));
      northPanel.add(comboBox);
      setLayout(new BorderLayout());
      add(northPanel, BorderLayout.NORTH);
      add(center, BorderLayout.CENTER);

      comboBox.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
            ArrayList<Participant> interestedMembers = new ArrayList<Participant>();
            

            for (int i = 0; i < allEvents.size(); i++)
            {
               for (int j = 0; j < allEvents.get(i).getParticipants()
                     .size(); j++)
               {
                  // if category equals selected and if participant is member
                  // and if member is not on the list yet
                  if (allEvents.get(i).getParticipants().get(j).getIsMember()
                        && !interestedMembers.contains(
                              allEvents.get(i).getParticipants().get(j))
                        && comboBox.getSelectedItem()
                              .equals(allEvents.get(i).getCategory()))
                  {
                     interestedMembers
                           .add(allEvents.get(i).getParticipants().get(j));
                  }
               }
            }

            tableModel.setRowCount(0); // Remove all rows
            for (int k = 0; k < interestedMembers.size(); k++)
            {
               tableModel.addRow(new Object[] {
                     interestedMembers.get(k).getName(),
                     interestedMembers.get(k).getAddress(),
                     interestedMembers.get(k).getPhoneNumber(),
                     interestedMembers.get(k).getEmail(),
                     interestedMembers.get(k).getPaymentYear(),
                     interestedMembers.get(k).getmembershipDate().toString() });
            }

         }
      });
   }

}
