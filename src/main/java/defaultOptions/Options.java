package defaultOptions;

public class Options
{

  private static final Integer CACHE_CAPACITY = 100;
  private static final Integer DATA_LIFETIME = 100000;
  private static final Integer CHECK_PERIOD = 100;

  public Integer getLifeTime()
  {
    return DATA_LIFETIME;
  }

  public Integer gerCheckPeriod()
  {
    return CHECK_PERIOD;
  }

  public Integer getCacheCapacity()
  {
    return CACHE_CAPACITY;
  }
}
