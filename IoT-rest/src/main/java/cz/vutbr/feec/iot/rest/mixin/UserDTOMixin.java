package cz.vutbr.feec.iot.rest.mixin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Pavel Šeda
 *
 */
@JsonIgnoreProperties({"passwordHash","enabled"})
public class UserDTOMixin {

}
