package ru.les.dav.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
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
      save(contacts, new File(file));
   }

   private void save (List<ContactShortData> contacts, File file) throws IOException {
      Writer writer = new FileWriter(file);
      for (ContactShortData contact : contacts) {
         writer.write(String.format("%s;%s;%s;%s;%s\n", contact.getFirstName(), contact.getLastName()
                 , contact.getAddress(), contact.getEmail(), contact.getMobileNumber()));
      }
      writer.close();
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
