package model;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import model.Event;

/**
 * An example code file to show how to write to files
 * and read XML files using the built in XML parser.
 * <p>
 * The writing example is simple since not much is needed
 * for it. The reading example shows not only how to read
 * XML and extract information, but also WHY we need to dive
 * really deep: text content at higher levels requires
 * more parsing than it's worth.
 * <p>
 * Written by Lucia A. Nunez, using the tutorial.xml file and based
 * on the tutorial written by Baeldung
 * Source: https://www.baeldung.com/java-xerces-dom-parsing
 * <p>
 * Do NOT simply copy-paste this code into your projects. It's
 * useless in its current form to you. Instead, figure out what it
 * is doing and how, lookup any related Javadocs, and finally write your
 * own wherever you need it.
 */
public class Utils {
  /**
   * Creates an XML file in the directory where this code is run
   * For IntelliJ, that is the project's folder.
   * <p>
   * SIDE-EFFECT: Calling this method twice will OVERWRITE the file.
   * If you want to add to an existing file, use append instead.
   */


  public static void writeToFile(User user) {
    try {
      DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
      Document schedule = builder.newDocument();
      Element scheduleID = schedule.createElement("schedule");
      scheduleID.setAttribute("id", user.uid);
      for (Event e : user.schedule) {
        Element event = schedule.createElement("event");
        Element name = schedule.createElement("name");
        name.appendChild(schedule.createTextNode(e.getName()));
        event.appendChild(name);

        Element time = schedule.createElement("time");
        Element startDay = schedule.createElement("start-day");
        startDay.appendChild(schedule.createTextNode(e.getStartDay().toString()));
        Element start = schedule.createElement("start");
        start.appendChild(schedule.createTextNode(String.format("%d", e.getStartTime())));
        Element endDay = schedule.createElement("end-day");
        endDay.appendChild(schedule.createTextNode(e.getEndDay().toString()));
        Element end = schedule.createElement("end");
        end.appendChild(schedule.createTextNode(String.format("%d", e.getEndTime())));
        time.appendChild(startDay);
        time.appendChild(start);
        time.appendChild(endDay);
        time.appendChild(end);
        event.appendChild(time);

        Element location = schedule.createElement("location");
        Element online = schedule.createElement("online");
        online.appendChild(schedule.createTextNode(String.format("%b", e.getOnline())));
        Element place = schedule.createElement("place");
        place.appendChild(schedule.createTextNode(e.getLocation()));
        location.appendChild(online);
        location.appendChild(place);
        event.appendChild(location);

        Element users = schedule.createElement("users");
        for (User u : e.getInvitedUsers()) {
          Element uid = schedule.createElement("uid");
          uid.appendChild(schedule.createTextNode(u.uid));
          users.appendChild(uid);
        }
        event.appendChild(users);
        scheduleID.appendChild(event);
      }
      schedule.appendChild(scheduleID);
      saveDomToFile(schedule, user.uid);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private static void saveDomToFile(Document document, String fileName)
          throws Exception {

    DOMSource dom = new DOMSource(document);
    Transformer transformer = TransformerFactory.newInstance()
            .newTransformer();

    StreamResult result = new StreamResult(new File(fileName));
    transformer.transform(dom, result);
  }

  // TODO: Implement read from XML

  /**
   * Reads the specific tutorial.xml file, assuming it's right next to the program,
   * and prints useful information from the file.
   * For IntelliJ, that is the project's folder.
   */
  public static User readXML(String path, List<User> database) {
    try {
      DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
      Document xmlDoc = builder.parse(new File(path));
      xmlDoc.getDocumentElement().normalize();

      // Gets the username from XML
      Node userNameNode = xmlDoc.getElementsByTagName("users").item(0);
      String userName = userNameNode.getTextContent();

      // Gets the schedule from XML
      Node scheduleNode = xmlDoc.getElementsByTagName("schedule").item(0);

      // User's schedule
      List<Event> schedule = new ArrayList<>();

      // Traverses events in the schedule
      NodeList eventsNodeList = scheduleNode.getChildNodes();
      Node event;
      for (int index = 0; index < eventsNodeList.getLength(); index++) {
        event = eventsNodeList.item(index);
        if (event.getNodeType() == Node.ELEMENT_NODE) {
          createSchedule(schedule, event, database);
        }
      }
      User user = new User(userName, schedule);
      return user;

    } catch (ParserConfigurationException ex) {
      throw new IllegalStateException("Error in creating the builder");
    } catch (IOException ioEx) {
      throw new IllegalStateException("Error in opening the file");
    } catch (SAXException saxEx) {
      throw new IllegalStateException("Error in parsing the file");
    }

  }

  public static void createSchedule(List<Event> schedule, Node event, List<User> database) {
    NodeList eventsChildrenNodeList = event.getChildNodes();

    // Gets name of the event
    //TODO: This is null for some reason
    String eventName = eventsChildrenNodeList.item(0).getTextContent();
    System.out.print(eventName);

    // Gets event's time children
    Node eventsTimeNode = eventsChildrenNodeList.item(2);
    NodeList eventsTimeChildrenNodeList = eventsTimeNode.getChildNodes();
    Day startDay = Day.valueOf(eventsTimeChildrenNodeList.item(0).getTextContent());
    int startTime = Integer.parseInt(eventsTimeChildrenNodeList.item(1).getTextContent());
    Day endDay = Day.valueOf(eventsTimeChildrenNodeList.item(2).getTextContent());
    int endTime = Integer.parseInt(eventsTimeChildrenNodeList.item(3).getTextContent());

    // Gets event's location children
    Node eventsLocationNode = eventsChildrenNodeList.item(2);
    NodeList eventsLocationChildrenNodeList = eventsLocationNode.getChildNodes();
    boolean online = Boolean.parseBoolean(eventsLocationChildrenNodeList.item(0).getTextContent());
    String location = eventsLocationChildrenNodeList.item(1).getTextContent();

    // Gets event's users children
    Node eventsUsersNode = eventsChildrenNodeList.item(3);
    NodeList eventsUsersChildrenNodeList = eventsUsersNode.getChildNodes();
    List<String> listOfUsernames = new ArrayList<>();
    for (int users = 0; users < eventsUsersChildrenNodeList.getLength(); users++) {
      listOfUsernames.add(eventsUsersChildrenNodeList.item(users).getTextContent());
    }

    List<User> listOfInvitees = matchUsernameToUser(listOfUsernames, database);
    Event e = new Event(eventName, location, online, startDay,
            startTime, endDay, endTime, listOfInvitees);
    schedule.add(e);
  }

  public static List<User> matchUsernameToUser(List<String> listOfUsernames, List<User> database) {
    List<User> listOfInvitees = new ArrayList<>();
    for (String username : listOfUsernames) {
      for (User user : database) {
        if (username.equals(user.uid)) {
          listOfInvitees.add(user);
        }
      }
    }
    return listOfInvitees;
  }


}
