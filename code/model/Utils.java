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
        System.out.print("HERE FOR SOME REASON");
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

  /**
   * Reads the specific tutorial.xml file, assuming it's right next to the program,
   * and prints useful information from the file.
   * For IntelliJ, that is the project's folder.
   */
  public static void readXML(User user) {
    try {
      DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
      Document xmlDoc = builder.parse(new File(user.uid));
      xmlDoc.getDocumentElement().normalize();

      Node tutorialsNode = xmlDoc.getElementsByTagName("tutorials").item(0);
      //This result isn't as nice...
      System.out.println("Investigating the textContent straight from the outermost element:");
      System.out.println(tutorialsNode.getTextContent());

      //So let's dig deeper into the other elements!
      NodeList nodeList = tutorialsNode.getChildNodes();
      for (int item = 0; item < nodeList.getLength(); item++) {
        Node current = nodeList.item(item);
        //We need to search for Elements (actual tags) and there
        //is only one: the tutorial tag
        if (current.getNodeType() == Node.ELEMENT_NODE) {
          Element elem = (Element) current;
          //Print out the attributes of this element
          System.out.println("Investigating the attributes:");
          System.out.println(elem.getTagName() + " : " + elem.getAttribute("tutId") + " " + elem.getAttribute("type"));

          //Print out the text that exists inside of this element: it doesn't look pretty...
          //and it erases the other elements
          System.out.println("Investigating the text content inside this element:");
          System.out.println(elem.getTagName() + " : " + elem.getTextContent());

          //... so let's dig even deeper!
          NodeList elemChildren = elem.getChildNodes();
          for (int childIdx = 0; childIdx < elemChildren.getLength(); childIdx++) {
            Node childNode = elemChildren.item(childIdx);
            if (childNode.getNodeType() == Node.ELEMENT_NODE) {
              Element child = (Element) childNode;
              //Now we're getting something more meaningful from each element!
              System.out.println("Investigating the text content inside the innermost tags");
              System.out.println(child.getTagName() + " : " + child.getTextContent());
            }
          }
        }
      }
    } catch (ParserConfigurationException ex) {
      throw new IllegalStateException("Error in creating the builder");
    } catch (IOException ioEx) {
      throw new IllegalStateException("Error in opening the file");
    } catch (SAXException saxEx) {
      throw new IllegalStateException("Error in parsing the file");
    }
  }
}
