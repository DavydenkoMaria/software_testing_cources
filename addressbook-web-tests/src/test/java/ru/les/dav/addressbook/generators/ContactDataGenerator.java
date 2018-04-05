package ru.les.dav.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.thoughtworks.xstream.XStream;
import ru.les.dav.addressbook.model.ContactShortData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by saakovamr on 05.04.18.
 */
public class ContactDataGenerator {

   @Parameter(names = "-c", description = "Group count")
   public int count;

   @Parameter(names = "-f", description = "Target file")
   public String file;

   @Parameter(names = "-d", description = "Data format")
   public String format;

   public static void main(String[] args) throws IOException {
      ContactDataGenerator generator = new ContactDataGenerator();
      JCommander jCommander = new JCommander(generator);
      try {
         jCommander.parse(args);
      } catch (ParameterException ex){
         jCommander.usage();
         return;
      }
      generator.run();
   }
   void run() throws IOException {
      List<ContactShortData> contacts = generateContacts(count);
      if (format.equals("csv")) {
         save(contacts, new File(file));
      } else if (format.equals("xml")){
         saveAsXml(contacts, new File(file));
      } else {
         System.out.println("Unrecognized format" + format);
      }
   }

   private void save (List<ContactShortData> contacts, File file) throws IOException {
      try(Writer writer = new FileWriter(file)) {
         for (ContactShortData contact : contacts) {
            writer.write(String.format("%s;%s;%s;%s;%s\n", contact.getFirstName(), contact.getLastName()
                    , contact.getAddress(), contact.getEmail(), contact.getMobileNumber()));
         }
      }
   }
   private void saveAsXml(List<ContactShortData> contacts, File file) throws IOException {
      XStream xstream = new XStream();
      xstream.processAnnotations(ContactShortData.class);
      String xml = xstream.toXML(contacts);
      try(Writer writer = new FileWriter(file)) {
         writer.write(xml);
      }
   }

   private List<ContactShortData> generateContacts(int count) {
      List<ContactShortData> contacts = new ArrayList<ContactShortData>();
      for (int i = 0; i<count; i++){
         contacts.add(new ContactShortData().withFirstName(String.format("name %s", i))
                 .withLastName(String.format("surname %s", i)).withAddress(String.format("address %s", i))
                 .withEmail(String.format("email %s", i)).withMobileNumber(String.format("number %s", i)));
      }
      return contacts;
   }
}
