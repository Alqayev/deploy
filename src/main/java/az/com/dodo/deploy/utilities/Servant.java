package az.com.dodo.deploy.utilities;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import org.controlsfx.control.CheckComboBox;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.List;

/**
 * Created by ubuntu on 6/20/17.
 */
public class Servant {
    public static URL getUrl(Class clazz,String resourcePath) throws FileNotFoundException {
        return GenericServant.getUrl(clazz,resourcePath);
    }
    public static void comboBox(ComboBox<String>combo,List<String> fields){
        combo.setItems(FXCollections.observableArrayList(fields));
    }
    public static void checkComboBox(CheckComboBox<String> checkComboBox, List<String> fields){
        checkComboBox.getItems().addAll(fields);
    }

}
