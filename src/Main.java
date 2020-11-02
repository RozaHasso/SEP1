import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Main extends JFrame
{
   private JTabbedPane tabbedPane;
   private SponsorsTab sponsorsPanel;
   private EventsTab eventsPanel;
   private LecturersTab lecturersPanel;
   private ParticipantsTab participantsPanel;
   private CategoriesTab categoriesPanel;
   
   private EventList allEvents;
   private EventFileAdapter eventAdapter;
   private LecturerList allLecturers;
   private LecturerFileAdapter lecturerAdapter;
   private ParticipantList allParticipants;
   private ParticipantFileAdapter participantAdapter;
   private SponsorList allSponsors;
   private SponsorFileAdapter sponsorAdapter;
   

   public Main()
   {
      super("VIA Event Manager");

      allEvents = new EventList();
       eventAdapter = new EventFileAdapter("events.bin");
      allEvents = eventAdapter.getAllEvents(); // load data from file to
                                               // allEvents
      allLecturers = new LecturerList();
       lecturerAdapter = new LecturerFileAdapter(
            "lecturers.bin");
      allLecturers = lecturerAdapter.loadLecturers();

       allParticipants = new ParticipantList();
       participantAdapter = new ParticipantFileAdapter(
            "participants.bin");
      allParticipants = participantAdapter.loadParticipants();

       allSponsors = new SponsorList();
       sponsorAdapter = new SponsorFileAdapter(
            "sponsors.bin");
      allSponsors = sponsorAdapter.loadSponsors();

      tabbedPane = new JTabbedPane();
      
      sponsorsPanel = new SponsorsTab(allSponsors);
      eventsPanel = new EventsTab(allEvents, allLecturers, allParticipants, allSponsors);
      lecturersPanel = new LecturersTab(allLecturers);
      participantsPanel = new ParticipantsTab(allParticipants);
      categoriesPanel = new CategoriesTab();

      tabbedPane.addTab("Events", null, eventsPanel, "Manage events.");
      tabbedPane.addTab("Participants", null, participantsPanel,
            "Manage participants.");
      tabbedPane.addTab("Lecturers", null, lecturersPanel, "Manage Lecturers.");
      tabbedPane.addTab("Sponsors", null, sponsorsPanel, "Manage sponsors.");
      tabbedPane.addTab("Categories", null, categoriesPanel,
            "List members for each category.");

      tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

      add(tabbedPane);

      setVisible(true);
      setResizable(true);
      setMinimumSize(new Dimension(1366, 720)); // 1000*720
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setLocationRelativeTo(null);
      
      Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {

         public void run() {
            eventAdapter.saveEvents(allEvents);
            lecturerAdapter.saveLecturers(allLecturers);
            participantAdapter.saveParticipants(allParticipants);
            sponsorAdapter.saveSponsors(allSponsors);
         }
     }));
   }

   public static void main(String[] args)
   {
      try
      {
         UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
      }
      catch (UnsupportedLookAndFeelException e)
      {
      }
      catch (ClassNotFoundException e)
      {
      }
      catch (InstantiationException e)
      {
      }
      catch (IllegalAccessException e)
      {
      }

      Main gui = new Main();

      System.out.println(gui);
   }
}
