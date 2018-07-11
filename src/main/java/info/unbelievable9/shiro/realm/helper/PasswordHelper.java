package info.unbelievable9.shiro.realm.helper;

import info.unbelievable9.shiro.realm.entity.SysUser;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * Created on : 2018/7/11
 * Author     : Unbelievable9
 **/
public class PasswordHelper {

    private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();

    /**
     * Encrypt System User Password Using Provided Algorithm And Hash Iteration Count
     *
     * @param sysUser        System User Entity.
     * @param algorithmName  Algorithm Name. Eg. md5
     * @param hashIterations Hash Iteration Count. Eg. 2
     */
    public void encrypt(SysUser sysUser, String algorithmName, int hashIterations) {
        sysUser.setSalt(randomNumberGenerator.nextBytes().toHex());

        String saltedPassword = new SimpleHash(
                algorithmName,
                sysUser.getPassword(),
                ByteSource.Util.bytes(sysUser.getCredentialSalt()),
                hashIterations).toHex();

        sysUser.setPassword(saltedPassword);
    }
}
