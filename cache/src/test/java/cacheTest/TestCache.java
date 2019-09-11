package cacheTest;

import cache.AbstractCache;
import junit.framework.TestCase;
import org.junit.Test;

public class TestCache extends TestCase
{
  public void testCache() throws Exception
  {
    AbstractCache cache = new AbstractCache();
    String testStr = "test";
    cache.put(testStr);
    assertNotNull(cache.get(testStr));
    Thread.sleep(200);
    assertNotNull(cache.get(testStr));
    Thread.sleep(1000);
    assertNull(cache.get(testStr));
  }

  @Override
  public void setUp() throws Exception
  {
    super.setUp();
  }

  @Override
  public void tearDown() throws Exception
  {
    super.tearDown();
  }
}
