package cz.vutbr.feec.iot.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import cz.vutbr.feec.iot.entity.PermissionEntity;
import cz.vutbr.feec.iot.entity.RoleEntity;
import cz.vutbr.feec.iot.entity.UserEntity;

/**
 * @author Pavel Šeda
 *
 */
public interface PermissionRepository
    extends JpaRepository<PermissionEntity, Long>, QueryDslPredicateExecutor<UserEntity> {

}
