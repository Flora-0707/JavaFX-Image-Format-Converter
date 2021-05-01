package sample;

import com.drew.imaging.jpeg.JpegProcessingException;

import java.io.IOException;
//Create an interface
public interface ImageTool {
    String getInfomation() throws JpegProcessingException, IOException;
}
