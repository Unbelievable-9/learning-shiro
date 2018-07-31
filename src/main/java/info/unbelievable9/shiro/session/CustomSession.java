package info.unbelievable9.shiro.session;

import lombok.Getter;
import lombok.Setter;
import org.apache.shiro.session.mgt.SimpleSession;

/**
 * Created on : 2018/7/31
 * Author     : Unbelievable9
 **/
public class CustomSession extends SimpleSession {

    public static enum CustomSessionStatus {
        ONLINE("在线"),
        LURKING("隐身"),
        KICKED("踢出");

        private final String info;

        private CustomSessionStatus(String info) {
            this.info = info;
        }

        public String getInfo() {
            return info;
        }
    }


    private static final long serialVersionUID = 2487271701037477459L;

    private static final int USER_AGENT_BIT_MASK;
    private static final int STATUS_BIT_MASK;

    private static int bitIndexCounter = 8;

    @Setter
    @Getter
    private String userAgent;

    @Getter
    @Setter
    private CustomSessionStatus status = CustomSessionStatus.ONLINE;

    static {
        USER_AGENT_BIT_MASK = 1 << bitIndexCounter++;
        STATUS_BIT_MASK = 1 << bitIndexCounter++;
    }

    public CustomSession() {
        super();
    }

    public CustomSession(String host) {
        super(host);
    }
}
