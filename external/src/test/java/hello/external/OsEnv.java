package hello.external;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class OsEnv {

    public static void main(String[] args){
        Map<String, String> getenv = System.getenv();
        for(String key : getenv.keySet()){
            System.out.println(key);
            System.out.println(getenv.get(key));
        }
    }
}
