package cz.vutbr.feec.iot.entity;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * @author Pavel Šeda
 *
 */
@Entity
@Table(name = "\"roles\"")
public class RoleEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_role", unique = true, nullable = false)
  private Long id;

  @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private Set<UserEntity> users = new HashSet<>(0);

  @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private Set<PermissionEntity> permissions = new HashSet<>(0);


  @Column(name = "role", nullable = false, unique = true, length = 45)
  private String role;

  public RoleEntity() {}

  public RoleEntity(String role) {
    this.role = role;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Set<UserEntity> getUsers() {
    return Collections.unmodifiableSet(users);
  }

  public void setUsers(Set<UserEntity> user) {
    this.users = user;
  }

  public void addUser(UserEntity user) {
    this.users.add(user);
  }

  public Set<PermissionEntity> getPermissions() {
    return permissions;
  }

  public void setPermissions(Set<PermissionEntity> permissions) {
    this.permissions = permissions;
  }

  public void addPermission(PermissionEntity permission) {
    this.permissions.add(permission);
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  @Override
  public int hashCode() {
    return 31 * 1 + ((role == null) ? 0 : role.hashCode());
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (!(obj instanceof RoleEntity))
      return false;
    RoleEntity other = (RoleEntity) obj;
    if (role == null) {
      if (other.role != null)
        return false;
    } else if (!role.equals(other.role))
      return false;
    if (permissions == null) {
      if (other.permissions != null)
        return false;
    } else if (!permissions.equals(other.permissions))
      return false;
    return true;
  }
}
