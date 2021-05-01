
package sample;

//Design pattern -> Factory pattern
//Create a Factory to generate object of concrete class based on given information
public class ImageFactory {

    public Tool getTool(String toolType,String imagePath){
        if(toolType == null){
            return null;
        }
        if(toolType.equalsIgnoreCase("size")){
            return new SizeTool(imagePath);
        } else if(toolType.equalsIgnoreCase("camera")){
            return new CameraTool(imagePath);
        } else if(toolType.equalsIgnoreCase("location")){
            return new LocationTool(imagePath);
        }
        return null;
    }
}
