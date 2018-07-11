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
@Getter
@Setter
@NoArgsConstructor
@ToString
public class SysPermission implements Serializable {

    private Long id;

    private String permission;

    private String description;

    private Boolean available = Boolean.FALSE;

    public SysPermission(String permission, String description, Boolean available) {
        this.permission = permission;
        this.description = description;
        this.available = available;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        SysPermission sysPermission = (SysPermission) obj;

        return id != null ? id.equals(sysPermission.id) : sysPermission.id == null;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
