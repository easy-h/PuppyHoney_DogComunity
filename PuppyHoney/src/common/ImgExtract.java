package common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ImgExtract {


   public String imgExtract(String img) {
      

        Pattern pattern = Pattern.compile("<img[^>]*src=[\"']?([^>\"']+)[\"']?[^>]*>"); //img �±� src ���� ����ǥ����

        Matcher matcher = pattern.matcher(img);
        String src="";
        
         
        while(matcher.find()){

           src=matcher.group(1);
        }
        return src;

	}

}



