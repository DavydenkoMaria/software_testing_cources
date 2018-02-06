package ru.les.dav.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by saakovamr on 06.02.18.
 */
public class MyFirstTests {

   @Test
   public void testDistanceY(){
      Point first = new Point(1,1);
      Point second = new Point(1,2);
      Assert.assertEquals(first.distance(second),1.0);
   }
   @Test
   public void testDistanceX(){
      Point first = new Point(1,1);
      Point second = new Point(4,1);
      Assert.assertEquals(first.distance(second),3.0);
   }
   @Test
   public void testDistanceXY(){
      Point first = new Point(1,-2);
      Point second = new Point(5,1);
      Assert.assertEquals(first.distance(second),5.0);
   }
   @Test
   public void testDistance(){
      Point first = new Point(1,-2);
      Point second = new Point(5,1);
      Assert.assertEquals(first.distance(second), second.distance(first));
   }

}
