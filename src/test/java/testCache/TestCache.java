package testCache;

import cache.*;
import defaultOptions.Options;
import junit.framework.TestCase;

import java.util.Map;
import java.util.TreeMap;

public class TestCache extends TestCase
{
  public void testCacheCreate()
  {
    Cache<Object> cache = new MemoryCache<>(new Options()
    {
      @Override
      public Integer getLifeTime()
      {
        return 1000;
      }
    });
    cache.put("123",  "Str1");
    assertNotNull(cache.get("123"));

    cache.put("1234", 435);
    assertNotNull(cache.get("1234"));
  }

  public void testCacheKeeping() throws Exception
  {
    Cache<Object> cache = new MemoryCache<>(new Options()
    {
      @Override
      public Integer getLifeTime()
      {
        return 1000;
      }
    });

    cache.put("123", "Str1");

    cache.put("1234", 532);

    Thread.sleep(200);

    assertNotNull(cache.get("1234"));
    assertNotNull(cache.get("123"));
  }

  public void testCacheCleaning() throws Exception
  {
    Cache<Object> cache = new MemoryCache<>(new Options()
    {
      @Override
      public Integer getLifeTime()
      {
        return 1000;
      }
    });
    cache.put("123", "testStr");

    cache.put("1234", 123654);

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
    Cache<Object> cache = new MemoryCache<>(new Options()
    {
      @Override
      public Integer getCacheCapacity()
      {
        return 3;
      }

      @Override
      public Integer getLifeTime()
      {
        return 1000;
      }
    });

    cache.put("123", "test");

    cache.put("1234", 445);

    cache.put("1235", "Str1");

    cache.put("1236", "Str2");

    cache.put("12366", "Str3");

    Thread.sleep(200);
    assertEquals(Integer.valueOf(3), cache.size());
  }

  public void testLRUCache() throws Exception
  {
    Cache<Object> cache = new LRUCache<>(new Options()
    {
      @Override
      public Integer getCacheCapacity()
      {
        return 3;
      }
    });

    cache.put("String", "Hello");
    cache.put("Int", 1999);
    cache.put("test", "krgj");

    Thread.sleep(500);

    cache.get("Int");
    cache.get("String");

    Thread.sleep(500);

    cache.put("NewString", "World");

    Thread.sleep(500);

    assertEquals(Integer.valueOf(3), cache.size());

    assertNotNull(cache.get("Int"));
    assertNotNull(cache.get("String"));
    assertNotNull(cache.get("NewString"));

    assertNull(cache.get("test"));
  }

  public void testMRUCache() throws Exception
  {
    Cache<Object> cache = new MRUCache<>(new Options()
    {
      @Override
      public Integer getCacheCapacity()
      {
        return 3;
      }
    });

    cache.put("String", "Hello");
    cache.put("Int", 1999);
    cache.put("test", "krgj");

    Thread.sleep(500);

    cache.get("Int");

    Thread.sleep(500);

    cache.get("String");

    cache.put("NewString", "World");

    Thread.sleep(500);

    assertEquals(Integer.valueOf(3), cache.size());

    assertNotNull(cache.get("Int"));
    assertNotNull(cache.get("test"));
    assertNotNull(cache.get("NewString"));

    assertNull(cache.get("String"));
  }

  public void testLFUCache() throws Exception
  {
    Cache<Object> cache = new LFUCache<>(new Options()
    {
      @Override
      public Integer getCacheCapacity()
      {
        return 3;
      }
    });

    cache.put("String", "Hello");

    cache.put("Int", 1999);
    cache.put("test", "krgj");

    Thread.sleep(500);

    cache.get("Int");
    cache.get("Int");

    cache.get("String");

    Thread.sleep(500);

    cache.put("NewString", "World");

    Thread.sleep(500);

    assertEquals(Integer.valueOf(3), cache.size());

    assertNotNull(cache.get("Int"));
    assertNotNull(cache.get("NewString"));
    assertNotNull(cache.get("String"));

    assertNull(cache.get("test"));
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
