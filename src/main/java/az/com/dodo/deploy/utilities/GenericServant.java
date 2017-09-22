package az.com.dodo.deploy.utilities;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;

/**
 * Created by ubuntu on 6/20/17.
 */
public class GenericServant {

    public  static <T> URL getUrl(Class<T> clazz,String resourcePath) throws FileNotFoundException {
        URL url=clazz.getClassLoader().getResource(resourcePath);
        Optional<URL> opt=Optional.ofNullable(url);
        opt.orElseThrow(FileNotFoundException::new);
        return url;
    }
    public static<T> T readFromYML(URL url,Class<T> clazz) throws IOException {
        ObjectMapper obj=new ObjectMapper(new YAMLFactory());
        T t=obj.readValue(url,clazz);
        return t;
    }
}
