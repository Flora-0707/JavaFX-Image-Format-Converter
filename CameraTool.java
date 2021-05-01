
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

//Get information of the camera
public class CameraTool extends sample.Tool implements sample.ImageTool {


    public CameraTool(String path) {
        super(path);;
    }

    @Override
    public String getInfomation() throws JpegProcessingException, IOException {
        String camera = "";
        File jpegFile = new File(imagePath);
        Metadata metadata = JpegMetadataReader.readMetadata(jpegFile);

        Iterable <Directory> dis = metadata.getDirectories();

//Check data keywords of images
//If data contains keyword "make" and doesn't contain "makernote", make this data as model(camera)
        
        for (Directory directory : metadata.getDirectories()) {
            for (Tag tag : directory.getTags()) {
                if(tag.getTagName().contains("Make")&&!tag.getTagName().contains("Makernote")){
                    camera = tag.getDescription();
                }


            }
        }
        return camera;
    }
    //Test
    public static void main(String[] args) throws JpegProcessingException, IOException {
        CameraTool cameraTool = new CameraTool("C:\\Users\\ValleyFalcon\\Pictures\\IMG_20210425_154835.jpg");
        System.out.println(cameraTool.getInfomation());
        cameraTool.getInfomation();
    }
}
