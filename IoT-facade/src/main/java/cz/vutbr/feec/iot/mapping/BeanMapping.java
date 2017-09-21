package cz.vutbr.feec.iot.mapping;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;

/**
 * @author Pavel Šeda (441048)
 *
 */
public interface BeanMapping {

  public <T> List<T> mapTo(Collection<?> objects, Class<T> mapToClass);

  public <T> Set<T> mapToSet(Collection<?> objects, Class<T> mapToClass);

  public <T> T mapTo(Object u, Class<T> mapToClass);
  
}
