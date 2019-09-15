package defaultOptions;

public class Options
{
  private static final Integer DATA_LIFETIME = 1000;
  private static final Integer CHECK_PERIOD = 100;

  public Integer getLifeTime()
  {
    return DATA_LIFETIME;
  }

  public Integer gerCheckPeriod()
  {
    return CHECK_PERIOD;
  }
}
