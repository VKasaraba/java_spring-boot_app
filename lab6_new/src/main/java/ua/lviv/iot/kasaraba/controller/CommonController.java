package ua.lviv.iot.kasaraba.controller;

import java.util.List;

public interface CommonController <T, S> {
  List<T> createDTOs(Iterable<S> entities);

  T createDTO(S entity);
}
