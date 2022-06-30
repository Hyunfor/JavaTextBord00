package utils;

import java.util.HashMap;
import java.util.Map;

public class UriParser {

    // members/join
    // members/modify?loginId=test
    // article/detail?id=1
    // 컨트롤러 종류/ 행위?파라미터

    private String URI; // /system/exit

    private String controllerCode;

    private String target; // 행위

    private boolean isValid = false;

    // key / value
    private Map<String, Object> parameter = new HashMap<>();

    public UriParser(String uri){
        this.URI = uri;
        parse();
    }

    private void parse() {
        // /system/exit

        if(!this.URI.startsWith("/")){
            this.isValid = false;
            return;
        }

        // "?"
        String[] uriSplite = this.URI.split("\\?", 2);
        // articles/detail?id=1&title=???&body=??
        // uriSplit = [atricles/detail, id=1]
        if (uriSplite.length == 2) {

            // id=1
            String paramBody = uriSplite[1];
            String[] splitParam = paramBody.split("=", 2);
            // splitParam = [id, 1]
            parameter.put(splitParam[0], splitParam[1]); // id: 1

        }

        // [ , system, exit]
        // /articles / detail
        // naver.com/members/join
        String[] uriBodySplit = uriSplite[0].split("/");

        if(uriBodySplit.length != 3){
            this.isValid = false;
            return;
        }

        // [, articles, detail]
        this.controllerCode = uriBodySplit[1];  //system
        this.target = uriBodySplit[2]; // exit

        isValid = true;

    }

    public String getControllerCode() {
        return controllerCode;
    }

    public String getTarget() {
        return target;
    }

    public Map<String, Object> getParameter() {
        return parameter;
    }

    public String getURI(){
        return URI;
    }

    public boolean isValid() {
        return isValid;
    }
}
