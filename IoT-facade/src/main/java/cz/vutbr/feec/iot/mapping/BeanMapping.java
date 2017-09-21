package cz.vutbr.feec.iot.mapping;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.domain.Page;

/**
 * @author Pavel Šeda (441048)
 *
 */
public interface BeanMapping {

  <T> List<T> mapTo(Collection<?> objects, Class<T> mapToClass);

  <T> Set<T> mapToSet(Collection<?> objects, Class<T> mapToClass);

  <T> T mapTo(Object u, Class<T> mapToClass);

  <T> Optional<T> mapToOptional(Object u, Class<T> mapToClass);

}
