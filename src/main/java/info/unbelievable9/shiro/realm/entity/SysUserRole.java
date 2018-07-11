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
public class SysUserRole implements Serializable {

    private Long user_id;

    private Long role_id;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        SysUserRole sysUserRole = (SysUserRole) obj;

        return (user_id != null ? user_id.equals(sysUserRole.user_id) : sysUserRole.user_id == null) &&
                (role_id != null ? role_id.equals(sysUserRole.role_id) : sysUserRole.role_id == null);
    }

    @Override
    public int hashCode() {
        // Why 31? -> https://stackoverflow.com/questions/299304/why-does-javas-hashcode-in-string-use-31-as-a-multiplier
        int result = user_id != null ? user_id.hashCode() : 0;
        result = result * 31 + (role_id != null ? role_id.hashCode() : 0);

        return result;
    }
}
