package cz.vutbr.feec.iot.dto.permission;

import java.util.ArrayList;
import java.util.List;

import cz.vutbr.feec.iot.dto.role.RoleDTO;
import io.swagger.annotations.ApiModel;

@ApiModel(value = "Permission DTO", description = "Permission DTO description")
public class PermissionDTO {

  private Long id;
  private String permission;
  private List<RoleDTO> roles = new ArrayList<>(0);

  public PermissionDTO() {}

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getPermission() {
    return permission;
  }

  public void setPermission(String permission) {
    this.permission = permission;
  }

  public List<RoleDTO> getRoles() {
    return roles;
  }

  public void setRoles(List<RoleDTO> roles) {
    this.roles = roles;
  }

  public void addRole(RoleDTO role) {
    this.roles.add(role);
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((permission == null) ? 0 : permission.hashCode());
    result = prime * result + ((roles == null) ? 0 : roles.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    PermissionDTO other = (PermissionDTO) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    if (permission == null) {
      if (other.permission != null)
        return false;
    } else if (!permission.equals(other.permission))
      return false;
    if (roles == null) {
      if (other.roles != null)
        return false;
    } else if (!roles.equals(other.roles))
      return false;
    return true;
  }


}
