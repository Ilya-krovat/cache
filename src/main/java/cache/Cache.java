package cache;

public interface Cache<V>
{
  /*
  V - тип объектов, которые будут храниться в кэше
  Наследники данного интерфейса получают в конструктор обьект класса Options
  Для создания кэша со своими настройками достаточно переопределить getter'ы, выдающие нужные значения
  lifeTime - время существованя обьекта в кэше
  check period - интервал, между проверками
  cacheCapacity - допустимое количество объектов в кэше
   У данного интерфейса 4 реализации:
    MemoryCache: при переполнении кэша удаляются наиболее станые элементы, а так же существующие дольше, чем lifeTIme
    LFUCache: LFU подсчитывает как часто используется элемент. Те элементы, обращения к которым происходят реже всего, вытесняются в первую очередь.
    LRUCache: в первую очередь, вытесняется неиспользованный дольше всех
    MRUCache: в отличие от LRU, в первую очередь вытесняется последний использованный элемент
  */

  /**
  Метод put использется для сохранения обьекта в кэш с его ключом
  */
  void put(String key, V value);

  /**
  Метод возвращает количество объектов в кэше
  */
  Integer size();

  /**
  Медот get используется для получения обьекта по ключу
  */
  V get(String key);
}
