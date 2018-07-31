package info.unbelievable9.shiro.common;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.session.Session;

import java.io.*;

/**
 * Created on : 2018/7/31
 * Author     : Unbelievable9
 **/
public class SerializableUtil {

    public static String serialize(Session session) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(session);

            return Base64.encodeToString(byteArrayOutputStream.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("Serialize Session Failed.", e);
        }
    }

    public static Session deserialize(String sessionString) {
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(Base64.decode(sessionString));
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);

            return (Session)objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Deserialize Session Failed.", e);
        }
    }
}
