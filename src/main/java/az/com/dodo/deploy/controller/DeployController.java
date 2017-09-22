package az.com.dodo.deploy.controller;

import az.com.dodo.deploy.model.Modules;
import az.com.dodo.deploy.model.ModulesInfo;
import az.com.dodo.deploy.utilities.GenericServant;
import az.com.dodo.deploy.utilities.Servant;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.controlsfx.control.CheckComboBox;
import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

@SuppressWarnings({"restriction", "unused"})
public class DeployController implements Initializable {

    static Logger logger= LogManager.getLogger(DeployController.class);
    private static String userPassword;

    private  List<File> warList;

    private final static String resourcePath = "/scripts/ubuntu/deploy.sh";
    private String uploadWar = "scripts/ubuntu/uploadwar.sh";

    @FXML
    protected ComboBox<String> modulesName;

    @FXML
    protected TextField passwordTxt;

    @FXML
    protected CheckBox hasPassword;

    @FXML
    protected CheckComboBox<String> checkMultipleModul;

    @FXML
    protected TextArea logArea;

    private PrintStream ps ;

    public class Console extends OutputStream {
        private TextArea console;

        public Console(TextArea console) {
            this.console = console;
        }

        public void appendText(String valueOf) {
            Platform.runLater(() -> console.appendText(valueOf));
        }

        public void write(int b) throws IOException {
            appendText(String.valueOf((char)b));
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
             ps=new PrintStream(new Console(logArea));
             System.setOut(ps);
             System.setErr(ps);
//            logArea.
            /* load modul name combo start */
            URL customUrl= Servant.getUrl(DeployController.class,"config/modules.yml");
            Modules modules= GenericServant.readFromYML(customUrl,Modules.class);
            Servant.checkComboBox(this.checkMultipleModul,modules.getInfo()
                    .stream()
                    .map(x->x.getModulName())
                    .collect(Collectors.toList()));
            /* load modul name combo end */
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @FXML
    private void uploadWarFile(ActionEvent event) throws Exception {
        FileChooser war=new FileChooser();
        war.setTitle("Upload war");
        FileChooser.ExtensionFilter extensionFilters=new FileChooser.ExtensionFilter("FileChooser extensions ","*.war","*.xlsx","*.txt");
        war.getExtensionFilters().add(extensionFilters);
        warList =war.showOpenMultipleDialog(new Stage());
    }
    @FXML
    private void exampleHandle(ActionEvent event) throws Exception {

        String path = getClass().getResource(resourcePath).getPath();
        if (path.contains("!78")){
            String jarPath = path.substring(5);
            String extractJarPath = jarPath.substring(0, jarPath.indexOf("!"));
            String folderPath = extractJarPath.substring(0, extractJarPath.lastIndexOf("/"));
            String runPath = folderPath + "/deploy/" + resourcePath;
            String isExtract = folderPath + "/deploy";
            File file = new File(isExtract);
            if (file.exists()) {
                System.out.println("*** File exists ***");
                Process removeFolder = Runtime.getRuntime().exec("echo afd1854 | sudo -S rm -rf " + isExtract);
                removeFolder.waitFor();
                BufferedReader reader = new BufferedReader(new InputStreamReader(removeFolder.getInputStream()));
                String line = null;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            }
            String unzip = "unzip " + extractJarPath + " -d deploy";
            String changeWritePermission = "chmod -w -R " + folderPath + "/deploy";
            Process procUnzip = Runtime.getRuntime().exec(unzip);
            procUnzip.waitFor();
            Process procChPermission = Runtime.getRuntime().exec(changeWritePermission);
            procChPermission.waitFor();
            Process procRun = Runtime.getRuntime().exec("bash " + runPath + " adjust");
            BufferedReader reader = new BufferedReader(new InputStreamReader(procRun.getInputStream()));
            String line = null;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } else {
            String osPassword=passwordTxt.getText();

            ObservableList<String>selectedModul=checkMultipleModul.getCheckModel().getCheckedItems();
            List<String> selectedVal=selectedModul.stream().collect(Collectors.toList());
            URL customUrl= Servant.getUrl(DeployController.class,"config/modules.yml");
            String deploy= Servant.getUrl(DeployController.class,uploadWar).getPath();
            Modules modules= GenericServant.readFromYML(customUrl,Modules.class);
            logger.info("***  Script file "+deploy+" ***");

            if (warList.size()==selectedVal.size())//mutleq secilmish modulla war file sayi eyni olmalidir
            for (File deployFile: warList){
                for (ModulesInfo info:modules.getInfo()){
                    String modulName=info.getModulName();
                    String pcPass=info.getPcpassword();
                    String pass=info.getPassword();
                    String userName=info.getUsername();
                    String ip=modules.getIp();
                    String port=modules.getPort();
                    String fileName=deployFile.getName();
                    String catalinaHome=info.getCatalinaHome();
                    String warLocation=deployFile.getAbsolutePath();
                    String folderName=deployFile.getName().substring(0,deployFile.getName().indexOf('.'));
                    if (selectedVal.contains(modulName)){
                        Process process=Runtime.getRuntime().exec(deploy
                                +" "+pcPass+""
                                +" "+userName+""
                                +" "+pass+""
                                +" "+ip+""
                                +" "+port+""
                                +" "+fileName+""
                                +" "+catalinaHome+""
                                +" "+warLocation+""
                                +" "+folderName+"");
                        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                        String line = null;
                        while ((line = reader.readLine()) != null) {
                            System.out.println(line);
                        }
                    }
                }
            }
        }
    }

    public void controlPasswordTxt(ActionEvent actionEvent) {
        if (!hasPassword.isSelected()){
            passwordTxt.setDisable(true);
        }else{
            passwordTxt.setDisable(false);
        }
    }

}
