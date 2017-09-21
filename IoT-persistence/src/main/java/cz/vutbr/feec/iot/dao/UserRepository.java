package cz.vutbr.feec.iot.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import com.mysema.query.types.Predicate;

import cz.vutbr.feec.iot.entity.UserEntity;


// ,QueryDslPredicateExecutor<UserEntity>, QuerydslBinderCustomizer<UserEntity>

/**
 * @author Pavel Šeda
 *
 */
public interface UserRepository
    extends JpaRepository<UserEntity, Long>, QueryDslPredicateExecutor<UserEntity> {

  /**
   * Find specific user by his email
   * 
   * @param email email to search in String format
   * @return return specific user by his email
   */
  public UserEntity findByEmail(String email);

  /**
   * Find all users with possibility to manage number of returned users with PageAble
   * 
   * @param pageRequest usefull to separate user pages
   * 
   * @return return all users pageated. It means I could return e.g. first 10 users
   */
  @Override
  public Page<UserEntity> findAll(Pageable pageRequest);

  /**
   * Find all users with possibility to manage number of returned users with PageAble
   * 
   * @param pageRequest usefull to separate user pages
   * @param predicate is used to filter data with params search
   * 
   * @return return all users pageated. It means I could return e.g. first 10 users
   */
  @Override
  public Page<UserEntity> findAll(Predicate predicate, Pageable pageRequest);

}

