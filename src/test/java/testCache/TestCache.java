package testCache;

import cache.AbstractCache;
import cache.Cache;
import cache.MemoryCache;
import defaultOptions.Options;
import junit.framework.TestCase;

import java.util.Map;
import java.util.TreeMap;

public class TestCache extends TestCase
{
  public void testCacheCreate()
  {
    AbstractCache cache = new MemoryCache(new Options());
    String testStr = "test";
    cache.put("123", testStr);
    assertNotNull(cache.get("123"));

    Integer testInt = 1234;
    cache.put("1234", testInt);
    assertNotNull(cache.get("1234"));
  }

  public void testCacheKeeping() throws Exception
  {
    AbstractCache cache = new MemoryCache(new Options());
    String testStr = "test";
    cache.put("123", testStr);

    Integer testInt = 1234;
    cache.put("1234", testInt);

    Thread.sleep(200);

    assertNotNull(cache.get("1234"));
    assertNotNull(cache.get("123"));
  }

  public void testCacheCleaning() throws Exception
  {
    Cache cache = new MemoryCache(new Options());

    String testStr = "test";
    cache.put("123", testStr);

    Integer testInt = 1234;
    cache.put("1234", testInt);

    Map<String, Integer> map = new TreeMap<>();
    map.put("1236", 1234);
    cache.put("map", map);

    Thread.sleep(2000);

    assertNull(cache.get("123"));
    assertNull(cache.get("1234"));
    assertNull(cache.get("map"));
  }

  public void testCapacity() throws Exception
  {
    Cache cache = new MemoryCache(new Options()
    {
      @Override
      public Integer getCacheCapacity()
      {
        return 3;
      }
    });

    String testStr1 = "test";
    cache.put("123", testStr1);

    Integer testInt = 1234;
    cache.put("1234", testInt);

    String testStr2 = "test";
    cache.put("1235", testStr2);

    String testStr3 = "test";
    cache.put("1236", testStr3);

    String testStr4 = "test";
    cache.put("12366", testStr4);

    Thread.sleep(200);
    assertEquals(Integer.valueOf(3), cache.size());
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
