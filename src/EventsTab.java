import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class EventsTab extends JPanel
{
   private int selectedIndex = -1;

   private JPanel eventsNorthPanel;
   private JLabel eventsTitleLabel;
   private JTextField eventsTitleField;
   private JLabel eventsTypeLabel;
   private JTextField eventsTypeField;
   private JLabel eventsDateLabel;
   private JTextField eventsDateField;
   private JLabel eventsTimeLabel;
   private JTextField eventsTimeField;
   private JLabel eventsDurationLabel;
   private JTextField eventsDurationField;
   private JLabel eventsCategoryLabel;
   private JTextField eventsCategoryField;
   private JLabel eventsPlacesLabel;
   private JTextField eventsPlacesField;
   private JLabel eventsFinalizedLabel;
   private JCheckBox eventsFinalizedCheckBox;

   private JPanel eventsSearchPanel;
   private TitledBorder searchEventsTitle;
   private JTextField searchEventsField;

   private JPanel eventsEditPanel;
   private TitledBorder eventsEditTitle;
   private JButton createEventsButton;
   private JButton updateEventsButton;
   private JButton deleteEventsButton;

   private JPanel eventsEastPanel; // East Panel
   private JPanel eventsDescriptionPanel;
   private JTextArea eventsDescriptionField;
   private JPanel eventsListsPanel;
   private JPanel eventParticipantsListPanel;
   private JList eventParticipantsList;
   private DefaultListModel<String> eventParticipantsListModel;
   private JButton addParticipantButton;
   private JButton removeParticipantButton;
   private JPanel eventLecturersListPanel;
   private JList eventLecturersList;
   private DefaultListModel<String> eventLecturersListModel;
   private JButton addLecturerButton;
   private JButton removeLecturerButton;
   private JPanel eventSponsorsListPanel;
   private JList eventSponsorsList;
   private DefaultListModel eventSponsorsListModel;
   private JButton addSponsorButton;
   private JButton removeSponsorButton;
   private JButton showSponsorsButton;
   private JPanel statisticsPanel;
   private JLabel statisticsLabel;
   private String statisticsText = "<html><b>Statistics</b><br><br><br><br></html>"; // Statistics

   private JScrollPane eventsScrollPane;
   private JTable eventsTable;
   private DefaultTableModel eventsTableModel;

   private Object[][] eventsData;
   private String[] eventsColumnNames;

   private ListSelectionModel listSelectionModel;

   private EventList allEvents;
   private LecturerList allLecturers;
   private SponsorList allSponsors;
   private ParticipantList allParticipants;

   public EventsTab(EventList allEvents, LecturerList allLecturers,
         ParticipantList allParticipants, SponsorList allSponsors)
   {
      // Load lists
      this.allEvents = allEvents;
      this.allLecturers = allLecturers;
      this.allSponsors = allSponsors;
      this.allParticipants = allParticipants;

      eventsColumnNames = new String[] { "Title", "Type", "Date", "Duration (hours)",
            "Category", "Places", "Finalized" };// columns name of sponsor

      eventsTableModel = new DefaultTableModel(eventsData, eventsColumnNames)
      {
         @Override
         public boolean isCellEditable(int row, int column)
         {
            return false;
         }
      };

      for (int i = 0; i < allEvents.size(); i++)
      {
         eventsTableModel.addRow(new Object[] { allEvents.get(i).getTitle(),
               allEvents.get(i).getType(), allEvents.get(i).getStartDate(),
               allEvents.get(i).getDuration(), allEvents.get(i).getCategory(),
               allEvents.get(i).getPlaces(),
               allEvents.get(i).getIsFinalized() });
      }

      eventsNorthPanel = new JPanel(new BorderLayout());
      eventsEditPanel = new JPanel();
      eventsEditTitle = new TitledBorder("Edit");
      eventsEditPanel.setBorder(eventsEditTitle);
      eventsTitleLabel = new JLabel("Title:");
      eventsTitleField = new JTextField(10);
      eventsTypeLabel = new JLabel("Type:");
      eventsTypeField = new JTextField(10);
      eventsDateLabel = new JLabel("Date:");
      eventsDateField = new JTextField(8);
      eventsTimeLabel = new JLabel("Time:");
      eventsTimeField = new JTextField(4);
      eventsDurationLabel = new JLabel("Duration:");
      eventsDurationField = new JTextField(5);
      eventsCategoryLabel = new JLabel("Category:");
      eventsCategoryField = new JTextField(7);
      eventsPlacesLabel = new JLabel("Places:");
      eventsPlacesField = new JTextField(4);
      eventsFinalizedLabel = new JLabel("Finalized:");
      eventsFinalizedCheckBox = new JCheckBox();
      eventsSearchPanel = new JPanel();
      searchEventsTitle = new TitledBorder("Search");
      searchEventsField = new JTextField(30);
      createEventsButton = new JButton("Create");
      updateEventsButton = new JButton("Update");
      deleteEventsButton = new JButton("Delete");

      eventsEastPanel = new JPanel(new BorderLayout());
      eventsDescriptionPanel = new JPanel();
      eventsDescriptionField = new JTextArea(5, 40);
      eventsDescriptionField.setLineWrap(true);
      eventsListsPanel = new JPanel(new BorderLayout());
      eventParticipantsListPanel = new JPanel();
      eventParticipantsListPanel.setLayout(
            new BoxLayout(eventParticipantsListPanel, BoxLayout.PAGE_AXIS));
      eventParticipantsListModel = new DefaultListModel();
      eventParticipantsList = new JList(eventParticipantsListModel);

      eventParticipantsList.setAlignmentX(CENTER_ALIGNMENT);
      eventParticipantsList
            .setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      addParticipantButton = new JButton("Add");
      addParticipantButton.setAlignmentX(CENTER_ALIGNMENT);
      removeParticipantButton = new JButton("Remove");
      removeParticipantButton.setAlignmentX(CENTER_ALIGNMENT);
      eventLecturersListPanel = new JPanel();
      eventLecturersListPanel.setLayout(
            new BoxLayout(eventLecturersListPanel, BoxLayout.PAGE_AXIS));
      eventLecturersListModel = new DefaultListModel();
      eventLecturersList = new JList(eventLecturersListModel);
      eventLecturersList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      addLecturerButton = new JButton("Add");
      addLecturerButton.setAlignmentX(CENTER_ALIGNMENT);
      removeLecturerButton = new JButton("Remove");
      removeLecturerButton.setAlignmentX(CENTER_ALIGNMENT);
      eventSponsorsListPanel = new JPanel();
      eventSponsorsListPanel.setLayout(
            new BoxLayout(eventSponsorsListPanel, BoxLayout.PAGE_AXIS));
      eventSponsorsListModel = new DefaultListModel();
      eventSponsorsList = new JList(eventSponsorsListModel);
      eventSponsorsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      addSponsorButton = new JButton("Add");
      addSponsorButton.setAlignmentX(CENTER_ALIGNMENT);
      removeSponsorButton = new JButton("Remove");
      removeSponsorButton.setAlignmentX(CENTER_ALIGNMENT);
      statisticsPanel = new JPanel();
      statisticsLabel = new JLabel(statisticsText);

      // Add lecturer button
      addLecturerButton.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
            if (selectedIndex != -1)
            {
               JFrame createLecturerFrame = new JFrame(
                     "Please select lecturer");
               JButton addButton = new JButton("Add");
               JPanel createLecturerPanel = new JPanel(new BorderLayout());
               DefaultListModel<String> lecturerListModel = new DefaultListModel<String>();
               JList lecturerList = new JList(lecturerListModel);
               JScrollPane scrollPane = new JScrollPane(lecturerList);
               lecturerList
                     .setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

               if (!allLecturers.isEmpty())
               {
                  for (int i = 0; i < allLecturers.size(); i++)
                  {
                     lecturerListModel.addElement(
                           allLecturers.get(i).getName() + ", Phone number: "
                                 + allLecturers.get(i).getPhoneNumber());
                  }
               }

               createLecturerFrame.setLocationRelativeTo(null);
               createLecturerFrame.setVisible(true);
               createLecturerFrame.setSize(400, 200);
               createLecturerFrame.setResizable(false);

               createLecturerPanel.add(scrollPane, BorderLayout.CENTER);
               createLecturerPanel.add(addButton, BorderLayout.SOUTH);
               createLecturerFrame.add(createLecturerPanel);

               addButton.addActionListener(new ActionListener()
               {
                  public void actionPerformed(ActionEvent e)
                  {

                     eventLecturersListModel.addElement(allLecturers
                           .get(lecturerList.getSelectedIndex()).getName());
                     allEvents.get(selectedIndex).getLecturers().add(
                           allLecturers.get(lecturerList.getSelectedIndex()));
                     createLecturerFrame.dispose();

                  }
               });
            }
            else
            {
               JOptionPane.showMessageDialog(null, "Please select event.",
                     "Attention!", JOptionPane.ERROR_MESSAGE);
            }
         }
      });
      // Add lecturer button END

      // Remove lecturer button
      removeLecturerButton.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
            if (selectedIndex != -1)
            {
               if (eventLecturersList.getSelectedIndex() != -1)
               {
                  int option = JOptionPane.showConfirmDialog(null,
                        "Are you sure?", "Confirm message",
                        JOptionPane.YES_NO_OPTION);
                  if (option == 0)
                  {
                     int selectedLecturer = eventLecturersList
                           .getSelectedIndex();
                     eventLecturersListModel.remove(selectedLecturer);
                     allEvents.get(selectedIndex).getLecturers()
                           .remove(selectedLecturer);
                  }
               }
            }
            else
            {
               JOptionPane.showMessageDialog(null, "Please select event.",
                     "Attention!", JOptionPane.ERROR_MESSAGE);
            }
         }
      });
      // Remove lecturer button END

      // Add participant button
      addParticipantButton.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
            if (selectedIndex != -1)
            {
               JFrame createParticipantFrame = new JFrame(
                     "Please select participant");
               JButton addButton = new JButton("Add");
               JPanel createParticipantPanel = new JPanel(new BorderLayout());
               DefaultListModel<String> participantListModel = new DefaultListModel<String>();
               JList participantList = new JList(participantListModel);
               JScrollPane scrollPane = new JScrollPane(participantList);
               participantList
                     .setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

               if (!allParticipants.isEmpty())
               {
                  for (int i = 0; i < allParticipants.size(); i++)
                  {
                     participantListModel.addElement(
                           allParticipants.get(i).getName() + ", Phone number: "
                                 + allParticipants.get(i).getPhoneNumber());
                  }
               }

               createParticipantFrame.setLocationRelativeTo(null);
               createParticipantFrame.setVisible(true);
               createParticipantFrame.setSize(400, 200);
               createParticipantFrame.setResizable(false);

               createParticipantPanel.add(scrollPane, BorderLayout.CENTER);
               createParticipantPanel.add(addButton, BorderLayout.SOUTH);
               createParticipantFrame.add(createParticipantPanel);

               addButton.addActionListener(new ActionListener()
               {
                  public void actionPerformed(ActionEvent e)
                  {
                     eventParticipantsListModel.addElement(allParticipants
                           .get(participantList.getSelectedIndex()).getName()
                           + ", "
                           + allParticipants
                                 .get(participantList.getSelectedIndex())
                                 .getEmail());
                     allEvents.get(selectedIndex).getParticipants()
                           .add(allParticipants
                                 .get(participantList.getSelectedIndex()));
                     createParticipantFrame.dispose();
                  }
               });
            }
            else
            {
               JOptionPane.showMessageDialog(null, "Please select event.",
                     "Attention!", JOptionPane.ERROR_MESSAGE);
            }
         }
      });
      // Add participant button END

      // Remove participant button
      removeParticipantButton.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
            if (selectedIndex != -1)
            {
               if (eventParticipantsList.getSelectedIndex() != -1)
               {
                  int option = JOptionPane.showConfirmDialog(null,
                        "Are you sure?", "Confirm message",
                        JOptionPane.YES_NO_OPTION);
                  if (option == 0)
                  {
                     int selectedParticipant = eventParticipantsList
                           .getSelectedIndex();
                     eventParticipantsListModel.remove(selectedParticipant);
                     allEvents.get(selectedIndex).getParticipants()
                           .remove(selectedParticipant);
                  }
               }
            }
            else
            {
               JOptionPane.showMessageDialog(null, "Please select event.",
                     "Attention!", JOptionPane.ERROR_MESSAGE);
            }
         }
      });
      // Remove participant button END

      // Add sponsor button
      addSponsorButton.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
            if (selectedIndex != -1)
            {
               JFrame createSponsorFrame = new JFrame("Please select sponsor");
               JButton addButton = new JButton("Add");
               JPanel createSponsorPanel = new JPanel(new BorderLayout());
               DefaultListModel<String> sponsorListModel = new DefaultListModel<String>();
               JList sponsorList = new JList(sponsorListModel);
               JScrollPane scrollPane = new JScrollPane(sponsorList);
               sponsorList
                     .setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

               if (!allSponsors.isEmpty())
               {
                  for (int i = 0; i < allSponsors.size(); i++)
                  {
                     sponsorListModel.addElement(
                           allSponsors.get(i).getName() + ", Phone number: "
                                 + allSponsors.get(i).getPhoneNumber());
                  }
               }

               createSponsorFrame.setLocationRelativeTo(null);
               createSponsorFrame.setVisible(true);
               createSponsorFrame.setSize(400, 200);
               createSponsorFrame.setResizable(false);

               createSponsorPanel.add(scrollPane, BorderLayout.CENTER);
               createSponsorPanel.add(addButton, BorderLayout.SOUTH);
               createSponsorFrame.add(createSponsorPanel);

               addButton.addActionListener(new ActionListener()
               {
                  public void actionPerformed(ActionEvent e)
                  {
                     eventSponsorsListModel.addElement(allSponsors
                           .get(sponsorList.getSelectedIndex()).getName() + ", "
                           + allSponsors.get(sponsorList.getSelectedIndex())
                                 .getPhoneNumber());
                     allEvents.get(selectedIndex).getSponsors().add(
                           allSponsors.get(sponsorList.getSelectedIndex()));
                     createSponsorFrame.dispose();
                  }
               });
            }
            else
            {
               JOptionPane.showMessageDialog(null, "Please select event.",
                     "Attention!", JOptionPane.ERROR_MESSAGE);
            }
         }
      });
      // Add sponsor button END

      // Remove sponsor button
      removeSponsorButton.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
            if (selectedIndex != -1)
            {
               if (eventSponsorsList.getSelectedIndex() != -1)
               {
                  int option = JOptionPane.showConfirmDialog(null,
                        "Are you sure?", "Confirm message",
                        JOptionPane.YES_NO_OPTION);
                  if (option == 0)
                  {
                     int selectedSponsor = eventSponsorsList.getSelectedIndex();
                     eventSponsorsListModel.remove(selectedSponsor);
                     allEvents.get(selectedIndex).getSponsors()
                           .remove(selectedSponsor);
                  }
               }
            }
            else
            {
               JOptionPane.showMessageDialog(null, "Please select event.",
                     "Attention!", JOptionPane.ERROR_MESSAGE);
            }
         }
      });
      // Remove sponsor button END

      eventsTable = new JTable(eventsTableModel);
      eventsTable.setFillsViewportHeight(true);
      eventsTable.getTableHeader().setReorderingAllowed(false);
      eventsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      eventsTable.setModel(eventsTableModel);

      listSelectionModel = eventsTable.getSelectionModel();
      listSelectionModel
            .addListSelectionListener(new MyListSelectionListener());
      eventsTable.setSelectionModel(listSelectionModel);
      eventsEastPanel.add(eventsDescriptionPanel, BorderLayout.NORTH);
      JScrollPane descriptionScroll = new JScrollPane(eventsDescriptionField);
      eventsDescriptionPanel.add(descriptionScroll);
      eventsDescriptionPanel.setBorder(new TitledBorder("Description"));
      eventsEastPanel.add(eventsListsPanel, BorderLayout.CENTER);
      eventsListsPanel.add(eventLecturersListPanel, BorderLayout.WEST);
      eventsListsPanel.add(eventParticipantsListPanel, BorderLayout.CENTER);
      eventsListsPanel.add(eventSponsorsListPanel, BorderLayout.EAST);
      JScrollPane participantsListScroll = new JScrollPane(
            eventParticipantsList); // Participants List
      participantsListScroll.setPreferredSize(new Dimension(70, 100));
      eventParticipantsListPanel.add(participantsListScroll);
      eventParticipantsListPanel.setBorder(new TitledBorder("Participants"));
      eventParticipantsListPanel.add(addParticipantButton);
      eventParticipantsListPanel.add(removeParticipantButton);

      eventsEastPanel.add(statisticsPanel, BorderLayout.SOUTH);
      statisticsPanel.add(statisticsLabel);

      JScrollPane lecturersListScroll = new JScrollPane(eventLecturersList); // Lecturers
                                                                             // List
      lecturersListScroll.setPreferredSize(new Dimension(70, 100));
      eventLecturersListPanel.add(lecturersListScroll);
      eventLecturersListPanel.setBorder(new TitledBorder("Lecturers"));
      eventLecturersListPanel.add(addLecturerButton);
      eventLecturersListPanel.add(removeLecturerButton);

      JScrollPane sponsorsListScroll = new JScrollPane(eventSponsorsList); // Sponsors
                                                                           // List
      sponsorsListScroll.setPreferredSize(new Dimension(70, 100));
      eventSponsorsListPanel.add(sponsorsListScroll);
      eventSponsorsListPanel.setBorder(new TitledBorder("Sponsors"));
      eventSponsorsListPanel.add(addSponsorButton);
      eventSponsorsListPanel.add(removeSponsorButton);

      eventsEditPanel.add(eventsTitleLabel);
      eventsEditPanel.add(eventsTitleField);
      eventsEditPanel.add(eventsTypeLabel);
      eventsEditPanel.add(eventsTypeField);
      eventsEditPanel.add(eventsDateLabel);
      eventsEditPanel.add(eventsDateField);
      eventsEditPanel.add(eventsTimeLabel);
      eventsEditPanel.add(eventsTimeField);
      eventsEditPanel.add(eventsDurationLabel);
      eventsEditPanel.add(eventsDurationField);
      eventsEditPanel.add(eventsCategoryLabel);
      eventsEditPanel.add(eventsCategoryField);
      eventsEditPanel.add(eventsPlacesLabel);
      eventsEditPanel.add(eventsPlacesField);
      eventsEditPanel.add(eventsFinalizedLabel);
      eventsEditPanel.add(eventsFinalizedCheckBox);
      eventsEditPanel.add(createEventsButton);
      eventsEditPanel.add(updateEventsButton);
      eventsEditPanel.add(deleteEventsButton);

      eventsSearchPanel.setBorder(searchEventsTitle);
      eventsSearchPanel.add(searchEventsField);

      eventsScrollPane = new JScrollPane(eventsTable);

      eventsNorthPanel.add(eventsEditPanel, BorderLayout.WEST);
      eventsNorthPanel.add(eventsSearchPanel, BorderLayout.EAST);
      setLayout(new BorderLayout());
      add(eventsEastPanel, BorderLayout.EAST);
      add(eventsNorthPanel, BorderLayout.NORTH);
      add(eventsScrollPane, BorderLayout.CENTER);

      // Search

      DefaultTableModel table = (DefaultTableModel) eventsTable.getModel();

      TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(
            table);
      eventsTable.setRowSorter(tr);
      tr.setSortable(2, false);
      tr.setSortable(3, false);

      searchEventsField.addKeyListener(new KeyAdapter()
      {
         public void keyReleased(KeyEvent e)
         {
            String search = searchEventsField.getText().toLowerCase();
            tr.setRowFilter(RowFilter.regexFilter("(?i)" + search));
         }
      }

      );
      // Search END

      // Create
      createEventsButton.addActionListener(new ActionListener()
      {
         @Override
         public void actionPerformed(ActionEvent e)
         {
            if (!(eventsTitleField.getText().equals("")
                  || eventsTypeField.getText().equals("")
                  || eventsDateField.getText().equals("")
                  || eventsTimeField.getText().equals("")
                  || eventsDurationField.getText().equals("")
                  || eventsCategoryField.getText().equals("")
                  || eventsPlacesField.getText().equals("")
                  || eventsDescriptionField.getText().equals("")))
            {
               int option = JOptionPane.showConfirmDialog(null,
                     "Create new event?\nTitle: " + eventsTitleField.getText()
                           + "\nType: " + eventsTypeField.getText() + "\nDate: "
                           + eventsDateField.getText() + "\nTime: "
                           + eventsTimeField.getText() + "\nDuration: "
                           + eventsDurationField.getText() + "\nPlaces: "
                           + eventsPlacesField.getText() + "\nCategory: "
                           + eventsCategoryField.getText() + "\nFinalized: "
                           + eventsFinalizedCheckBox.isSelected(),
                     "Confirm message", JOptionPane.YES_NO_OPTION);
               if (option == 0)
               {
                  String dateStrings[] = eventsDateField.getText().split("/");
                  String timeStrings[] = eventsTimeField.getText().split(":");

                  int day = Integer.parseInt(dateStrings[0]);
                  int month = Integer.parseInt(dateStrings[1]);
                  int year = Integer.parseInt(dateStrings[2]);

                  int hour = Integer.parseInt(timeStrings[0]);
                  int minute = Integer.parseInt(timeStrings[1]);

                  // add row to the model
                  eventsTableModel.addRow(new Object[] {
                        eventsTitleField.getText(), eventsTypeField.getText(),
                        eventsDateField.getText() + " "
                              + eventsTimeField.getText(),
                        eventsDurationField.getText(),
                        eventsCategoryField.getText(),
                        eventsPlacesField.getText(),
                        eventsFinalizedCheckBox.isSelected() });

                  Event newEvent = new Event(eventsTitleField.getText(),
                        eventsTypeField.getText(),
                        Double.parseDouble(eventsDurationField.getText()),
                        eventsCategoryField.getText(),
                        Integer.parseInt(eventsPlacesField.getText()),
                        eventsFinalizedCheckBox.isSelected(),
                        eventsDescriptionField.getText(),
                        new MyDate(day, month, year, hour, minute));

                  allEvents.add(newEvent);
               }
            }
            else
            {
               JOptionPane.showMessageDialog(null, "Input every field",
                     "Attention!", JOptionPane.ERROR_MESSAGE);
            }
         }
      });
      // Create END

      // Delete
      deleteEventsButton.addActionListener(new ActionListener()
      {
         @Override
         public void actionPerformed(ActionEvent e)
         {
            // i = the index of the selected row
            int i = eventsTable.getSelectedRow();
            if (i >= 0)
            {
               int opcion = JOptionPane.showConfirmDialog(null, "Are you sure?",
                     "Confirm message", JOptionPane.YES_NO_OPTION);

               if (opcion == 0)
               {
                  tr.setRowFilter(null);
                  allEvents.remove(selectedIndex);
                  eventsTableModel.removeRow(selectedIndex);
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
      updateEventsButton.addActionListener(new ActionListener()
      {
         @Override
         public void actionPerformed(ActionEvent e)
         {
            if (selectedIndex > -1)
            {
               if (!(eventsTitleField.getText().equals("")
                     || eventsTypeField.getText().equals("")
                     || eventsDateField.getText().equals("")
                     || eventsTimeField.getText().equals("")
                     || eventsDurationField.getText().equals("")
                     || eventsCategoryField.getText().equals("")
                     || eventsPlacesField.getText().equals("")
                     || eventsDescriptionField.getText().equals("")))
               {

                  int option = JOptionPane.showConfirmDialog(null,
                        "Update event data?", "Confirm message",
                        JOptionPane.YES_NO_OPTION);
                  if (option == 0)
                  {

                     String dateStrings[] = eventsDateField.getText()
                           .split("/");
                     String timeStrings[] = eventsTimeField.getText()
                           .split(":");

                     int day = Integer.parseInt(dateStrings[0]);
                     int month = Integer.parseInt(dateStrings[1]);
                     int year = Integer.parseInt(dateStrings[2]);

                     int hour = Integer.parseInt(timeStrings[0]);
                     int minute = Integer.parseInt(timeStrings[1]);

                     allEvents.get(selectedIndex)
                           .setTitle(eventsTitleField.getText());
                     eventsTableModel.setValueAt(eventsTitleField.getText(),
                           selectedIndex, 0);

                     allEvents.get(selectedIndex)
                           .setType(eventsTypeField.getText());
                     eventsTableModel.setValueAt(eventsTypeField.getText(),
                           selectedIndex, 1);

                     allEvents.get(selectedIndex).setStartDate(
                           new MyDate(day, month, year, hour, minute));
                     eventsTableModel
                           .setValueAt(
                                 eventsDateField.getText() + "  "
                                       + eventsTimeField.getText(),
                                 selectedIndex, 2);

                     allEvents.get(selectedIndex).setDuration(
                           Double.parseDouble(eventsDurationField.getText()));
                     eventsTableModel.setValueAt(eventsDurationField.getText(),
                           selectedIndex, 3);

                     allEvents.get(selectedIndex)
                           .setCategory(eventsCategoryField.getText());
                     eventsTableModel.setValueAt(eventsCategoryField.getText(),
                           selectedIndex, 4);

                     allEvents.get(selectedIndex).setPlaces(
                           Integer.parseInt(eventsPlacesField.getText()));
                     eventsTableModel.setValueAt(eventsPlacesField.getText(),
                           selectedIndex, 5);

                     allEvents.get(selectedIndex).setIsFinalizes(
                           eventsFinalizedCheckBox.isSelected());
                     eventsTableModel.setValueAt(
                           eventsFinalizedCheckBox.isSelected(), selectedIndex,
                           6);

                     allEvents.get(selectedIndex)
                           .setDescription(eventsDescriptionField.getText());

                  }
               }
               else
                  JOptionPane.showMessageDialog(null,
                        "Insert data in every field!", "Error Message",
                        JOptionPane.ERROR_MESSAGE);
            }
            else
            {
               JOptionPane.showMessageDialog(null, "Select a row first of all!",
                     "Error Message", JOptionPane.ERROR_MESSAGE);
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
            if (eventsTable.getSelectedRow() != -1)
            {
               selectedIndex = eventsTable
                     .convertRowIndexToModel(eventsTable.getSelectedRow());
               System.out.println("Current row: " + selectedIndex);

               int day = allEvents.get(selectedIndex).getStartDate().getDay();
               int month = allEvents.get(selectedIndex).getStartDate()
                     .getMonth();
               int year = allEvents.get(selectedIndex).getStartDate().getYear();

               int hour = allEvents.get(selectedIndex).getStartDate().getHour();
               int minute = allEvents.get(selectedIndex).getStartDate()
                     .getMinute();

               // Always show info from the selected object in the fields
               eventsTitleField
                     .setText(allEvents.get(selectedIndex).getTitle());
               eventsTypeField.setText(allEvents.get(selectedIndex).getType());
               eventsDateField.setText(((day < 10) ? "0" + day : day) + "/"
                     + ((month < 10) ? "0" + month : month) + "/" + year);
               eventsTimeField.setText(((hour < 10) ? "0" + hour : hour) + ":"
                     + ((minute < 10) ? "0" + minute : minute));
               eventsDurationField.setText(Double
                     .toString(allEvents.get(selectedIndex).getDuration()));
               eventsCategoryField
                     .setText(allEvents.get(selectedIndex).getCategory());
               eventsPlacesField.setText(Integer
                     .toString(allEvents.get(selectedIndex).getPlaces()));
               eventsFinalizedCheckBox.setSelected(
                     allEvents.get(selectedIndex).getIsFinalized());
               eventsDescriptionField
                     .setText(allEvents.get(selectedIndex).getDescription());

               // Update Statistics
               statisticsText = "<html><b>Statistics</b><br>Total places: "
                     + allEvents.get(selectedIndex).getPlaces() + "<br>"
                     + "Free places: "
                     + allEvents.get(selectedIndex).getFreeSpacesLeft() + "<br>"
                     + "Amount of members: "
                     + allEvents.get(selectedIndex).getNumberOfMembers()
                     + "</html>";
               statisticsLabel.setText(statisticsText);
               // Update Statistics END

               // Update Lecturers List
               eventLecturersListModel.removeAllElements();
               for (int i = 0; i < allEvents.get(selectedIndex).getLecturers()
                     .size(); i++)
               {
                  eventLecturersListModel.addElement(allEvents
                        .get(selectedIndex).getLecturers().get(i).getName());
               }
               // Update Lecturers List END

               // Update Participants List
               eventParticipantsListModel.removeAllElements();
               for (int i = 0; i < allEvents.get(selectedIndex)
                     .getParticipants().size(); i++)
               {
                  eventParticipantsListModel.addElement(allEvents
                        .get(selectedIndex).getParticipants().get(i).getName()
                        + ", " + allEvents.get(selectedIndex).getParticipants()
                              .get(i).getEmail());
               }
               // Update Participants List END

               // Update Sponsors List
               eventSponsorsListModel.removeAllElements();
               for (int i = 0; i < allEvents.get(selectedIndex).getSponsors()
                     .size(); i++)
               {
                  eventSponsorsListModel.addElement(allEvents.get(selectedIndex)
                        .getSponsors().get(i).getName());
               }
               // Update Sponsors List END
               
               // Restrict adding participants to a non-finalized event
               if (eventsFinalizedCheckBox.isSelected())
               {
                  addParticipantButton.setEnabled(true);
               }
               else
               {
                  addParticipantButton.setEnabled(false);
               }

            }
            else
            {
               // Clear GUI when nothing is selected
               eventsTitleField.setText("");
               eventsTypeField.setText("");
               eventsDateField.setText("");
               eventsTimeField.setText("");
               eventsDurationField.setText("");
               eventsCategoryField.setText("");
               eventsPlacesField.setText("");
               eventsFinalizedCheckBox.setSelected(false);
               eventsDescriptionField.setText("");

               eventLecturersListModel.removeAllElements();
               eventParticipantsListModel.removeAllElements();
               eventSponsorsListModel.removeAllElements();
            }

         }
      }

   }

}
