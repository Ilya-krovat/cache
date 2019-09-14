package defaultOptions;

public class DefaultOptions
{
  private static final Integer MEMORY_CAPACITY = 1000;
  private static final Integer DATA_LIFETIME = 1000;
  private static final Integer CHECK_PERIOD = 100;

  public Integer getMemory()
  {
    return MEMORY_CAPACITY;
  }

  public Integer getLifeTime()
  {
    return DATA_LIFETIME;
  }

  public Integer gerCheckPeriod()
  {
    return CHECK_PERIOD;
  }
}
