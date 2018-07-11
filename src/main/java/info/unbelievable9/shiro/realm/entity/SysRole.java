package info.unbelievable9.shiro.realm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * Created on : 2018/7/11
 * Author     : Unbelievable9
 **/
@Setter
@Getter
@NoArgsConstructor
@ToString
public class SysRole implements Serializable {

    private Long id;

    private String role;

    private String description;

    private Boolean available = Boolean.FALSE;

    public SysRole(String role, String description, Boolean available) {
        this.role = role;
        this.description = description;
        this.available = available;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        SysRole sysRole = (SysRole) obj;

        return id != null ? id.equals(sysRole.id) : sysRole.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
