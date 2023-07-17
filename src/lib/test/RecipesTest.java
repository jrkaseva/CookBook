package lib.test;
// Generated by ComTest BEGIN
import static org.junit.Assert.*;
import org.junit.*;
import lib.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2023.07.07 20:53:04 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class RecipesTest {



  // Generated by ComTest BEGIN
  /** testIsEmpty27 */
  @Test
  public void testIsEmpty27() {    // Recipes: 27
    Recipes r = new Recipes(); 
    assertEquals("From: Recipes line: 29", true, r.isEmpty()); 
    r.put(new Recipe()); assertEquals("From: Recipes line: 30", false, r.isEmpty()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testContains41 */
  @Test
  public void testContains41() {    // Recipes: 41
    Recipes r = new Recipes(); 
    assertEquals("From: Recipes line: 43", false, r.contains(1)); 
    r.put(new Recipe()); 
    assertEquals("From: Recipes line: 45", true, r.contains(2)); 
    assertEquals("From: Recipes line: 46", false, r.contains(2)); 
  } // Generated by ComTest END
}