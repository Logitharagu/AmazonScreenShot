package Day20;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.jspecify.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;

public class TakeFullScreenShotInAmazon {
	public static WebDriver driver;
	public static int  Sellector=1;

	public static void main(String[] args) throws IOException {
		BrowserSellector();
		BrowserSetting();
		Navigation();
		GetInformation();
		SearchBox("ponniyin selvan", "Books");
		PageFoundResult();
		Exist();

	}
	public static void BrowserSellector() {
		switch (Sellector) {
		case 1:
			System.out.println("User Select Chrome Browser");
			driver=new ChromeDriver();
			break;
		case 2:
			System.out.println("User Select Edge Browser");
			driver=new EdgeDriver();
			break;
		default:
			System.out.println("User Select Unknow Value So Open Chrome Browser");
			driver=new ChromeDriver();
			break;
		}
		
	}
   public static void BrowserSetting() {
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().pageLoadTimeout(Duration.ofMinutes(40));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		}
   public static void Navigation() {
			driver.navigate().to("https://www.amazon.in/");
			driver.navigate().refresh();
			driver.navigate().back();
			driver.navigate().forward();
		}
   public static void GetInformation() {
			String Url=driver.getCurrentUrl();
			System.out.println("The current URL is: "+Url);
			@Nullable
			String title = driver.getTitle();
			System.out.println("The Current page title: "+title);
			String handle = driver.getWindowHandle();
			System.out.println("The window handle: "+handle);
		}
   public static void SearchBox(String ProductName,String ProCategories) {
	   WebElement SearchText, Categories,Botton;
	   SearchText=driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
	   SearchText.sendKeys(ProductName);
	   
	   Categories=driver.findElement(By.xpath("//select[@id='searchDropdownBox']"));
	   Select Objsel=new Select(Categories);
	   Objsel.selectByVisibleText(ProCategories);
	   
	   Botton=driver.findElement(By.xpath("//input[@id='nav-search-submit-button']"));
	   Botton.click();
   }
   public static void PageFoundResult() throws IOException {
	   WebElement Oresult;
	   Oresult=driver.findElement(By.xpath("(//div[@class='sg-col-inner'][1]//h2)[1]"));
	   //to get the result value as using text
	   String ResultText=Oresult.getText();
	   System.out.println("After Regex: "+ResultText);
	   //next we check the value is greater than zero.you can't directly use because of those are string
	   String Result = ResultText.replaceAll("[^0-9]", "");
	   //still it has string to converted integer
	   int int1 = Integer.parseInt(Result);
	  // System.out.println("Extracted Number: " + Result);
	   if (int1>0) {
		System.out.println("Text is avaliable");
		SearchResult();
		ScreenShotMethodsInSelenium.takeFullScreenShot(driver, "Full_shot");//TAKE SCREENSHORT
	} else {
		System.out.println("Text is not avaliable");
	}
	   
   }
   public static void SearchResult() {
	  
	   /*
	    * Step1: find the overall grouping element
	    *   --->path of over all grouping element://span[@class='rush-component s-latency-cf-section']
	    * Step2:and the find single grouping elements
	    *   --->path of single grouping elements: //span[@class='rush-component s-latency-cf-section']//div[@role='listitem'][1]
	    * step3: after that you select what to want to print in program
	    *  --->path of print in single elements: //span[@class='rush-component s-latency-cf-section']//div[@role='listitem'][1]//div[@data-cy='title-recipe']//h2
	    */
	   List<WebElement> elements = driver.findElements(By.xpath("//span[@class='rush-component s-latency-cf-section']//div[@role='listitem']"));
	   for (WebElement Element : elements) {
		   String text = Element.findElement(By.xpath(".//div[@data-cy='title-recipe']//h2")).getText();
		System.out.println(text);
	}
	   
   }
   public static void Exist() {
	   driver.quit();
   }
}
