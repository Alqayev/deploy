package az.com.dodo.deploy.model;

/**
 * Created by ubuntu on 6/20/17.
 */
public class ModulesInfo {
    private String modulName;
    private String username;
    private String password;
    private String pcpassword;
    private String catalinaHome;
    private String path;
    private String usergroup;
    private String multipletomcat;



    public ModulesInfo() {

    }

    public String getPcpassword() {
        return pcpassword;
    }

    public void setPcpassword(String pcpassword) {
        this.pcpassword = pcpassword;
    }

    public String getCatalinaHome() {
        return catalinaHome;
    }

    public void setCatalinaHome(String catalinaHome) {
        this.catalinaHome = catalinaHome;
    }

    public String getUsergroup() {
        return usergroup;
    }

    public void setUsergroup(String usergroup) {
        this.usergroup = usergroup;
    }

    public String getMultipletomcat() {
        return multipletomcat;
    }

    public void setMultipletomcat(String multipletomcat) {
        this.multipletomcat = multipletomcat;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getModulName() {
        return modulName;
    }

    public void setModulName(String modulName) {
        this.modulName = modulName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "ModulesInfo{" +
                "modulName='" + modulName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", pcpassword='" + pcpassword + '\'' +
                ", catalinaHome='" + catalinaHome + '\'' +
                ", path='" + path + '\'' +
                ", usergroup='" + usergroup + '\'' +
                ", multipletomcat='" + multipletomcat + '\'' +
                '}';
    }
}
