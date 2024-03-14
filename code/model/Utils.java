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
 * A utilities class that allows writing to a XML file and reading from an XML file.
 */
public final class Utils {

  /**
   * Writes the schedule of the given user to an XML file and saves it within the project folder.
   * @param user a User
   */
  public static void writeToFile(User user) {
    try {
      DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
      Document schedule = builder.newDocument();
      Element scheduleID = schedule.createElement("schedule");
      scheduleID.setAttribute("id", user.uid);

      // Traverses events in the user's schedule.
      for (Event e : user.schedule) {
        writeEvent(schedule, scheduleID, e);
      }
      schedule.appendChild(scheduleID);
      saveDomToFile(schedule, user.uid);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Structures the events of the XML file.
   * @param schedule a Document
   * @param scheduleID an Element
   * @param e an Event
   */
  private static void writeEvent(Document schedule, Element scheduleID, Event e) {
    Element event = schedule.createElement("event");
    Element name = schedule.createElement("name");
    name.appendChild(schedule.createTextNode(e.getName()));
    event.appendChild(name);

    // Structures time section of event
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

    // Structures location section of event
    Element location = schedule.createElement("location");
    Element online = schedule.createElement("online");
    online.appendChild(schedule.createTextNode(String.format("%b", e.getOnline())));
    Element place = schedule.createElement("place");
    place.appendChild(schedule.createTextNode(e.getLocation()));
    location.appendChild(online);
    location.appendChild(place);
    event.appendChild(location);

    // Structures users section of event
    Element users = schedule.createElement("users");
    for (User u : e.getInvitedUsers()) {
      Element uid = schedule.createElement("uid");
      uid.appendChild(schedule.createTextNode(u.uid));
      users.appendChild(uid);
    }
    event.appendChild(users);
    scheduleID.appendChild(event);
  }

  /**
   * Saves the given document to a file with the given file name.
   * @param document a Document
   * @param fileName a String
   * @throws Exception a TransformerConfiguration Exception or TransformerException
   */
  private static void saveDomToFile(Document document, String fileName)
          throws Exception {
    DOMSource dom = new DOMSource(document);
    Transformer transformer = TransformerFactory.newInstance()
            .newTransformer();
    StreamResult result = new StreamResult(new File(fileName));
    transformer.transform(dom, result);
  }

  /**
   * Reads a XML file from the project folder with the given path and uses the given
   * database to retrieve the list of invitees.
   * @param path a String
   * @param database a List of User
   * @return a User
   */
  public static User readXML(String path, List<User> database) {
    try {
      DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
      Document xmlDoc = builder.parse(new File(path));
      xmlDoc.getDocumentElement().normalize();

      Node scheduleNode = xmlDoc.getElementsByTagName("schedule").item(0);
      String userName = scheduleNode.getAttributes().getNamedItem("id").getNodeValue();

      // Traverses events in the XML file and adds each event to the schedule
      List<Event> schedule = new ArrayList<>();
      if(scheduleNode.getNodeType() == Node.ELEMENT_NODE) {
        NodeList eventNodeList = scheduleNode.getChildNodes();
        for (int i = 0; i < eventNodeList.getLength(); i++) {
          Node eventNode = eventNodeList.item(i);
          if (eventNode.getNodeType() == Node.ELEMENT_NODE) {
            Event event = createEvent((Element) eventNode, database);
            schedule.add(event);
          }
        }
      }
      return new User(userName, schedule);
    } catch (ParserConfigurationException | SAXException | IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * Extracts fields of an Event from the given event Element and returns an Event.
   * @param eventElement an Element
   * @param database a List of User
   * @return an Event
   */
  private static Event createEvent(Element eventElement, List<User> database) {
    // Gets event name
    String eventName = getTextContent(eventElement, Tag.name);

    // Gets start day, start time, end day, end time from time section of Event element
    Element timeElement = (Element) eventElement.getElementsByTagName("time").item(0);
    String startDay = getTextContent(timeElement, Tag.startDay);
    int startTime = Integer.parseInt(getTextContent(timeElement, Tag.start));
    String endDay = getTextContent(timeElement, Tag.endDay);
    int endTime = Integer.parseInt(getTextContent(timeElement, Tag.end));

    // Gets the online and place from the location section of the Event element
    Element locationElement = (Element) eventElement.getElementsByTagName("location").item(0);
    boolean online = Boolean.parseBoolean(getTextContent(locationElement, Tag.online));
    String place = getTextContent(locationElement, Tag.place);

    // Gets the list of invitees from the users section of the Event element
    NodeList usersNodeList = eventElement.getElementsByTagName("uid");
    List<User> invitees = new ArrayList<>();
    for (int i = 0; i < usersNodeList.getLength(); i++) {
      String userName = usersNodeList.item(i).getTextContent();
      try {
        User user = findUser(userName, database);
        invitees.add(user);
      }
      catch (IllegalArgumentException ignored) {
        // The uploaded XML file belongs to a user that does not yet exist in the database
        if (i > 0) {
          throw new IllegalArgumentException("A user in the list of invitees that is not the host" +
                  "does not exist in the database.");
        }
      }
    }
    return new Event(eventName, place, online, Day.valueOf(startDay),
            startTime, Day.valueOf(endDay), endTime, invitees);
  }

  /**
   * Gets the text content of the element of the XML file with the given tag.
   * @param element an Element
   * @param tag a Tag
   * @return a String
   */
  private static String getTextContent(Element element, Tag tag) {
    return element.getElementsByTagName(tag.toString()).item(0).getTextContent();
  }

  /**
   * Gets the User with the given usernamen in the given database.
   * @param userName a String
   * @param database a List of User
   * @return a User
   */
  public static User findUser(String userName, List<User> database) {
    for (User user : database) {
      if (user.uid.equals(userName)) {
        return user;
      }
    }
    throw new IllegalArgumentException("User not found");
  }

}
