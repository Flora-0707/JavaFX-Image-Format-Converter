package sample;

import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.imaging.jpeg.JpegProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller {

	private static final FileChooser FILE_CHOOSER = new FileChooser();

	private static final ObservableList<String> IMAGE_EXTENSIONS = FXCollections.observableArrayList("bmp", "png", "jpg", "jpeg");

	@FXML
	private ImageView imageView;

	@FXML
	private Button bntOpen;

	@FXML
	private Label labelFilename;

	@FXML
	private Button btnSave;

	@FXML
	private ComboBox<String> imageFormat;

	private BufferedImage bufferedImage;

	private String imageName;

	@FXML
	private Label size;


	@FXML
	private Label camera;

	@FXML
	private Label loc;
//Initialize the FileChooser
	@FXML
	public void initialize() throws Exception {
		FILE_CHOOSER.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image files",
				"*.bmp", "*.BMP", "*.png", "*.PNG", "*.jpg", "*.JPG", "*.JPEG"));
		imageFormat.setItems(IMAGE_EXTENSIONS);

	}
//Show the thumbnail
	private void showImage() {
		if (bufferedImage != null) {
			imageView.setImage(null);
			imageView.setImage(SwingFXUtils.toFXImage(bufferedImage, null));
		}
	}

	//Open picture and get informations of size, location and camera
	@FXML
	void onClickBtnOpen(ActionEvent event) throws JpegProcessingException, IOException {
		ImageFactory imageFactory = new ImageFactory();
		File file = FILE_CHOOSER.showOpenDialog(bntOpen.getScene().getWindow());
		if (file != null) {
			String filename = file.getName();
			SizeTool sizeTool = (SizeTool)imageFactory.getTool("size",file.getAbsolutePath());
			size.setText(sizeTool.getInfomation());
			CameraTool cameraTool = (CameraTool)imageFactory.getTool("camera",file.getAbsolutePath());
			camera.setText(cameraTool.getInfomation());
			LocationTool locationTool = (LocationTool)imageFactory.getTool("location",file.getAbsolutePath());
			loc.setText(locationTool.getInfomation());
			labelFilename.setText(filename);
			imageName = filename.substring(0, filename.lastIndexOf('.'));
			try {
				bufferedImage = ImageIO.read(file);
				showImage();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// Convert the input image from original format to the goal(selected by user) format
	@FXML
	void onClickBtnSave(ActionEvent event) {
		if (bufferedImage != null) {
			FileChooser fileSaver = new FileChooser();
			String extension = imageFormat.getValue();
			fileSaver.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image files", "*." + extension));
			fileSaver.setInitialFileName(imageName + "." + extension);
			File file = fileSaver.showSaveDialog(btnSave.getScene().getWindow());
			if (file != null) {
				try {
					ImageIO.write(bufferedImage, extension, file);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
