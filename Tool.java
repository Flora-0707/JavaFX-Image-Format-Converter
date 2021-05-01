
package sample;
//Super class(tool)
//derived classes(CameraTool, SizeTool, LocationTool)
public class Tool {
    protected String imagePath;

    public Tool(String imagePath) {
        this.imagePath = imagePath;
    }
}
