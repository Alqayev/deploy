import az.com.dodo.deploy.controller.DeployController;
import az.com.dodo.deploy.model.Modules;
import az.com.dodo.deploy.model.ModulesInfo;
import az.com.dodo.deploy.utilities.Servant;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ubuntu on 6/20/17.
 */
public class MainClass {
    public static void main(String[] args) throws IOException {
        System.out.println(Servant.getUrl(DeployController.class,"config/modules.yml"));
        ObjectMapper obj=new ObjectMapper(new YAMLFactory());
        Modules modules=obj.readValue(MainClass.class.getClassLoader().getResource("config/modules.yml"),Modules.class);

        modules.getInfo().forEach(x-> System.out.println(x.getModulName()));
//        Modules modules=new Modules();
//        modules.setIp("10");
//        ModulesInfo info=new ModulesInfo("cabin","ad", "asdasa");
//List<ModulesInfo> list=new ArrayList<>();
//list.add(info);
//modules.setInfo(list);
//        System.out.println(obj.writeValueAsString(modules));;

        //        modules.getModules().forEach((k,v)->{
//            System.out.println(k+"---"+v);
//        });
        //        List<String> list=Arrays.asList(modules.getModules());
//        list.forEach(System.out::println);
    }
}
