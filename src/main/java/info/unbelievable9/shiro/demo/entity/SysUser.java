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
public class SysUser implements Serializable {

    private Long id;

    private String username;

    private String password;

    private String salt;

    private Boolean locked = Boolean.FALSE;

    public SysUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getCredentialSalt() {
        return username + salt;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        SysUser sysUser = (SysUser) obj;

        return id != null ? id.equals(sysUser.id) : sysUser.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
