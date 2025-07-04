package Day20;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v135.page.model.Screenshot;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;


public class ScreenShotMethodsInSelenium {
	
	/**
	 * There are three methods to take screenshot of file output type methods
	 *  1.FILE
	 *  2.BASE64
	 *  3.BYTE
	 *  
	 *it some methods to take screenshot webElements.
	 *take full screenshot. 
	 *stop overrider.
	 * @param args
	 * @throws IOException 
	 */

	public static void takeScreenShotFile(WebDriver driver, String fileName) throws IOException {
		File Ofile= new File("./image/"+fileName+".png");
		TakesScreenshot Oshot=(TakesScreenshot)driver;
		File screenshotAs = Oshot.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshotAs, Ofile);
	}
	public static void takeScreenShotBase64(WebDriver driver,String fileName) throws IOException {
		File Ofile=new File("./image/"+fileName+".png");
		TakesScreenshot Oshot=(TakesScreenshot)driver;
		String screenshotAs = Oshot.getScreenshotAs(OutputType.BASE64);
		File file = OutputType.FILE.convertFromBase64Png(screenshotAs);
		FileUtils.copyFile(file, Ofile);
	}
	public static void takeScreenShotByte(WebDriver driver,String fileName) throws IOException {
		File ofile=new File("./image/"+fileName+".png");
		TakesScreenshot Oshot=(TakesScreenshot)driver;
		byte[] screenshotAs = Oshot.getScreenshotAs(OutputType.BYTES);
		File Bytes = OutputType.FILE.convertFromPngBytes(screenshotAs);
		FileUtils.copyFile(Bytes, ofile);	
	}
	//This methods is used to take screenshot for particular elements
	public static void takeScreenShotWebElements(WebElement elements,String fileName) throws IOException {
		File ofile=new File("./image/"+fileName+".png");
		TakesScreenshot Oshot=(TakesScreenshot)elements;
		File screenshotAs = Oshot.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshotAs, ofile);
	}
	//This methods used for to stop override image
	public static String takeScreenShotAsFileWithDynamicFileName(WebDriver driver,String fileName) throws IOException {
		String filePath=System.getProperty("user.dir")+"/image/"+fileName+System.currentTimeMillis()+".png";
		System.out.println("The File location: "+filePath);
		File ofile=new File(filePath);
		TakesScreenshot Oshot=(TakesScreenshot)driver;
		File screenshotAs = Oshot.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshotAs, ofile);
		return filePath;
		
	}
	public static void takeFullScreenShot(WebDriver driver,String fileName) throws IOException {
		ru.yandex.qatools.ashot.Screenshot Oshot=new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
		String filePath=System.getProperty("user.dir")+"/image/"+fileName+System.currentTimeMillis()+".png";
		File oDes = new File(filePath);
		ImageIO.write(Oshot.getImage(), "png", oDes);
	}

}
