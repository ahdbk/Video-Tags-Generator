package com.watson.Tags.Video;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

import org.jcodec.api.FrameGrab;
import org.jcodec.api.JCodecException;
import org.jcodec.common.io.NIOUtils;
import org.jcodec.common.model.Picture;
import org.jcodec.scale.AWTUtil;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifyImagesOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.VisualClassification;

public class TagsGenerator {

	public static int INTERVAL = 100;
	public static int MAX_FRAMES = 10;
	public static String VIDEO_PATH = "/Users/ahdbenkheder/Desktop/testvideo.mp4";
	public static String DIR_NAME = "saved";

	public static void main(String[] args) throws IOException, JCodecException, JSONException {

		File videoFile = new File(VIDEO_PATH);
		File saveDir = new File(DIR_NAME);
		saveDir.mkdir();

		int count = 0;
		int index = 10;

		FrameGrab grab = FrameGrab.createFrameGrab(NIOUtils.readableChannel(videoFile));
		Picture picture;
		grab.seekToSecondPrecise(10); // Begin video from sec 10

		System.out.println("Processing video :" + videoFile.getPath());

		// Generate frames from video
		while (null != (picture = grab.getNativeFrame())) 
		{
			BufferedImage bufferedImage = AWTUtil.toBufferedImage(picture);
			ImageIO.write(bufferedImage, "png", new File(saveDir.getName() + "/frame" + index + ".png"));
			index++;
			count++;
			grab.seekToSecondPrecise(index + INTERVAL);

			if (count == MAX_FRAMES)
				break;
		}

		System.out.println(count + "Frame generated");
		
		// get the list of generated frames
		File[] listOfFiles = saveDir.listFiles();

		// TODO Auto-generated method stub
		VisualRecognition service = new VisualRecognition(VisualRecognition.VERSION_DATE_2016_05_20);
		service.setEndPoint("https://gateway-a.watsonplatform.net/visual-recognition/api");
		// Here you replace "your_api_key_here" by the API Key you created in "Creating
		// a Watson Visual Recognition service instance and getting the API key"
		service.setApiKey("acc4516893bf5b2fea7b0d4c4942fed4a167e1a1");
		// Here you add the URL of your image. The image size should not exceed 2MB.

		ArrayList<VisualClassification> results = new ArrayList<VisualClassification>();
		ArrayList<String> tagsClass = new ArrayList<String>();

		for (File file2 : listOfFiles) {

			if (!file2.getPath().contains("png"))
				continue;

			ClassifyImagesOptions options = new ClassifyImagesOptions.Builder().images(file2).build();
			VisualClassification result = service.classify(options).execute();
			results.add(result);

			JSONObject obj = new JSONObject(result.toString());
			// Get an Array of all classes
			JSONArray arr = obj.getJSONArray("images")
								.getJSONObject(0)
								.getJSONArray("classifiers")
								.getJSONObject(0)
								.getJSONArray("classes");

			// Get the first class
			String bestTag = arr.getJSONObject(0)
								.getString("class");
			
			if (!tagsClass.contains(bestTag))
				tagsClass.add(bestTag);

		}
		
		System.out.println("Tags results for your video:");
		
		for (String string : tagsClass) {
			System.out.println(string + ",");

		}
		System.out.println("Done");
	}
}
