package az.com.dodo.deploy.model;

import java.util.List;
import java.util.Map;

/**
 * Created by ubuntu on 6/20/17.
 */
public class Modules {
    private String ip;
    private String port;

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    private List<ModulesInfo> info;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public List<ModulesInfo> getInfo() {
        return info;
    }

    public void setInfo(List<ModulesInfo> info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "Modules{" +
                "ip='" + ip + '\'' +
                ", port='" + port + '\'' +
                ", info=" + info +
                '}';
    }
}
