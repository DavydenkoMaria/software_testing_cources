package ru.les.dav.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.sun.xml.internal.ws.api.ha.StickyFeature;
import jdk.nashorn.internal.parser.JSONParser;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.testng.SkipException;
import org.testng.annotations.Test;
import sun.font.CoreMetrics;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import static org.testng.Assert.assertEquals;

/**
 * Created by saakovamr on 10.04.18.
 */
public class RestTests {

   @Test
   public void testCreateIssue() throws IOException {
      skipIfNotFixed(1321);
      Set<Issue> oldIssues = getIssues();
      System.out.println("NotIgnored");
      Issue newIssue = new Issue().withSubject("Test issue").withDescription("Test description");
      int issueId = createIssue(newIssue);
      Set<Issue> newIssues = getIssues();
      oldIssues.add(newIssue.withId(issueId));
      assertEquals(newIssues, oldIssues);
   }

   private Set<Issue> getIssues() throws IOException {
      String json = getExecutor().execute(Request.Get("http://demo.bugify.com/api/issues.json"))
              .returnContent().asString();
      JsonElement parsed = new JsonParser().parse(json);
      JsonElement issues = parsed.getAsJsonObject().get("issues");
      return new Gson().fromJson(issues, new TypeToken<Set<Issue>>(){}.getType());
   }

   private int createIssue(Issue newIssue) throws IOException {
      String json = getExecutor().execute(Request.Post("http://demo.bugify.com/api/issues.json")
              .bodyForm(new BasicNameValuePair("subject", newIssue.getSubject()),
                      new BasicNameValuePair("description", newIssue.getDescription())))
              .returnContent().asString();
      JsonElement parsed = new JsonParser().parse(json);
      return parsed.getAsJsonObject().get("issue_id").getAsInt();
   }

   public Executor getExecutor() {
      return Executor.newInstance().auth("28accbe43ea112d9feb328d2c00b3eed", "");
   }

   public void skipIfNotFixed(int issueId) throws IOException {
      if (isIssueOpen(issueId)) {
         throw new SkipException("Ignored because of issue " + issueId);
      }
   }

   private boolean isIssueOpen(int issueId) throws IOException {
      String link = "http://demo.bugify.com/api/issues/" + issueId + ".json";
      String json = getExecutor().execute(Request.Get(link))
              .returnContent().asString();
      JsonElement parsed = new JsonParser().parse(json);
      String check = parsed.getAsJsonObject().get("issues").getAsJsonArray().get(0).getAsJsonObject().get("state_name").getAsString();
      System.out.println(check);
      if (check.equals("Open")){
         return true;
      } else if (check.equals("In Progress")){
         return true;
      }
      return false;
   }
}
