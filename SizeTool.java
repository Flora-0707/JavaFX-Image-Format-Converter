
package sample;

import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.imaging.jpeg.JpegProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SizeTool extends Tool implements ImageTool {


    public SizeTool(String path) {
        super(path);
    }

    @Override
    public String getInfomation() throws JpegProcessingException, IOException {
        String size = "";
        File jpegFile = new File(super.imagePath);
        Metadata metadata = JpegMetadataReader.readMetadata(jpegFile);

        Iterable <Directory> dis = metadata.getDirectories();

        //Get the size of image by image width and image height
        for (Directory directory : metadata.getDirectories()) {
            for (Tag tag : directory.getTags()) {
                if(tag.getTagName().contains(" Image Width")){
                    System.out.println(tag.getDescription());
                    String regEx="[^0-9]";
                    Pattern p = Pattern.compile(regEx);
                    Matcher m = p.matcher(tag.getDescription());
                    size+=m.replaceAll("").trim()+"x";
//                    System.out.println( m.replaceAll("").trim());

                }
                if(tag.getTagName().contains(" Image Height")){
                    System.out.println(tag.getDescription());
                    String regEx="[^0-9]";
                    Pattern p = Pattern.compile(regEx);
                    Matcher m = p.matcher(tag.getDescription());
                    size+=m.replaceAll("").trim()+" pixels";
//                    System.out.println( m.replaceAll("").trim());

                }


            }
        }
        return size;
    }
//test
    public static void main(String[] args) throws JpegProcessingException, IOException {
    SizeTool sizeTool = new SizeTool("C:\\Users\\ValleyFalcon\\Pictures\\IMG_20210425_154835.jpg");
        System.out.println(sizeTool.getInfomation());
    }
}
