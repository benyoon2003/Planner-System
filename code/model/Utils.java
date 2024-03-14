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

  public static User readXML(String path, List<User> database) {
    try {
      DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
      Document xmlDoc = builder.parse(new File(path));
      xmlDoc.getDocumentElement().normalize();

      Node scheduleNode = xmlDoc.getElementsByTagName("schedule").item(0);
      String userName = scheduleNode.getAttributes().getNamedItem("id").getNodeValue();

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

  private static Event createEvent(Element eventElement, List<User> database) {
    String eventName = getTextContent(eventElement, Tag.name);

    Element timeElement = (Element) eventElement.getElementsByTagName("time").item(0);
    String startDay = getTextContent(timeElement, Tag.startDay);
    int startTime = Integer.parseInt(getTextContent(timeElement, Tag.start));
    String endDay = getTextContent(timeElement, Tag.endDay);
    int endTime = Integer.parseInt(getTextContent(timeElement, Tag.end));

    Element locationElement = (Element) eventElement.getElementsByTagName("location").item(0);
    boolean online = Boolean.parseBoolean(getTextContent(locationElement, Tag.online));
    String place = getTextContent(locationElement, Tag.place);

    NodeList usersNodeList = eventElement.getElementsByTagName("uid");
    List<User> invitees = new ArrayList<>();
    for (int i = 0; i < usersNodeList.getLength(); i++) {
      String userName = usersNodeList.item(i).getTextContent();
      try {
        User user = findUser(userName, database);
        invitees.add(user);
      }
      catch (IllegalArgumentException ignored) {
        // TODO: this catch block is hit when we are finding the host, which does not exist
        // in the database yet or an invalid user in the users tag
      }
    }
    return new Event(eventName, place, online, Day.valueOf(startDay),
            startTime, Day.valueOf(endDay), endTime, invitees);
  }

  private static String getTextContent(Element element, Tag tag) {
    return element.getElementsByTagName(tag.toString()).item(0).getTextContent();
  }

  public static User findUser(String userName, List<User> database) {
    for (User user : database) {
      if (user.uid.equals(userName)) {
        return user;
      }
    }
    throw new IllegalArgumentException("User not found");
  }

}
