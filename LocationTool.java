
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

public class LocationTool extends Tool implements ImageTool {


    public LocationTool(String path) {
        super(path);;
    }
//Get the information of GPS of image
    @Override
    public String getInfomation() throws JpegProcessingException, IOException {
        String location = "";
        File jpegFile = new File(imagePath);
        Metadata metadata = JpegMetadataReader.readMetadata(jpegFile);

        Iterable <Directory> dis = metadata.getDirectories();
        
        //Get the information of image location by checking keywords "Latitude" and "Longitude"
        for (Directory directory : metadata.getDirectories()) {
            for (Tag tag : directory.getTags()) {
                if(tag.getTagName().contains("PS Latitude")&&!tag.getTagName().contains("Ref")){
                    location += tag.getDescription()+",";
                }
                if(tag.getTagName().contains("PS Latitude")&&tag.getTagName().contains("Ref")){
                    location += tag.getDescription();
                }

                if(tag.getTagName().contains("PS Longitude")&&!tag.getTagName().contains("Ref")){
                    location += tag.getDescription();
                }
                if(tag.getTagName().contains("PS Longitude")&&tag.getTagName().contains("Ref")){
                    location += tag.getDescription();
                }


            }
        }
        return location;
    }
    //Test
    public static void main(String[] args) throws JpegProcessingException, IOException {
        LocationTool locationTool = new LocationTool("C:\\Users\\ValleyFalcon\\Pictures\\IMG_20210425_154835.jpg");
        System.out.println(locationTool.getInfomation());
    }

}
