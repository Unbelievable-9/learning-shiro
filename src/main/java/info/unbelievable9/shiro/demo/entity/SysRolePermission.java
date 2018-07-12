package info.unbelievable9.shiro.demo.entity;

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
public class SysRolePermission implements Serializable {

    private Long role_id;

    private Long permission_id;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        SysRolePermission sysRolePermission = (SysRolePermission) obj;

        return ((role_id != null ? role_id.equals(sysRolePermission.role_id) : sysRolePermission.role_id == null) &&
                (permission_id != null ? permission_id.equals(sysRolePermission.permission_id) : sysRolePermission.permission_id == null));
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
