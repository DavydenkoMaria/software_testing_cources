package ru.les.dav.mantis.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.les.dav.mantis.model.Issue;
import ru.les.dav.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

/**
 * Created by saakovamr on 10.04.18.
 */
public class SoapTests extends TestBase{

   @Test
   public void testGetProjects() throws MalformedURLException, ServiceException, RemoteException {
      skipIfNotFixed(0000001);
      Set<Project> projects = app.soap().getProjects();
      System.out.println(projects.size());
      for (Project project : projects) {
         System.out.println(project.getName());
      }
   }

   @Test
   public void testCreateIssue() throws RemoteException, ServiceException, MalformedURLException {
      Set<Project> projects = app.soap().getProjects();
      Issue issue = new Issue().withSummary("Test issue")
              .withDescription("Test issue description").withProject(projects.iterator().next());
      Issue created = app.soap().addIssue(issue);
      Assert.assertEquals(issue.getSummary(), created.getSummary());
   }
}
