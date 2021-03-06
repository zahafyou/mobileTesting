package appium_Package;

import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.nativekey.PressesKey;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

//import io.appium.java_client.AppiumDriver;
import org.testng.annotations.BeforeMethod;

import static org.junit.Assert.assertTrue;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class AppiumTest {
	AppiumDriver<MobileElement> driver = null;  
	
	@Test
	 public void f() {
		
	 }
	 
	 
	 @BeforeMethod
	 public void BeforeMethod() throws MalformedURLException {
		//Set the Desired Capabilities
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("deviceName", "Tecno J8");
		caps.setCapability("udid", "0153801730301908"); //Give Device ID of your mobile phone
		caps.setCapability("platformName", "Android");
		caps.setCapability("platformVersion", "5.1");
		caps.setCapability("appPackage", "com.obertys.fadel.customercorner");
		caps.setCapability("appActivity", "com.obertys.fadel.customercorner.Vues.OuvertureCompte.Activities.SplashScreenActivityUI");
		caps.setCapability("noReset", "true");
		
		//Instantiate Appium Driver
		try {
			driver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), caps);
			
		} catch (MalformedURLException e) {
				System.out.println(e.getMessage());
			}
			
		//Added 5 seconds wait so that the app loads completely before starting with element identification
		try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}		
		 }
	 
	 
	 @Test
	 public void login() {
		 System.out.println("Driver is Launched");
			//Find  element UserName using ID property and click on it
			driver.findElement(By.id("com.obertys.fadel.customercorner:id/login_field")).click();				
			//Find UserName input element using ID property and clear it
			driver.findElement(By.id("com.obertys.fadel.customercorner:id/login_field")).clear();				
			//UserName input element and set value agentterrain02
			driver.findElement(By.id("com.obertys.fadel.customercorner:id/login_field")).sendKeys("agentterrain02");
			System.out.println("Page de connexion ");
			
			System.out.println("Login : agentterrain02");
			// Find Password input using Id property and click on it
			driver.findElement(By.id("com.obertys.fadel.customercorner:id/password_field")).click();				
			// Clear PassWord Input
			driver.findElement(By.id("com.obertys.fadel.customercorner:id/password_field")).clear();
			
			// Send PassWord
			driver.findElement(By.id("com.obertys.fadel.customercorner:id/password_field")).sendKeys("Test@123");
			System.out.println("Mot de passe : Test@123");
			((PressesKey) driver).pressKey(new KeyEvent(AndroidKey.BACK));
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			
			// Find Element Button using ID property and click on it
			if(driver.findElement(By.id("com.obertys.fadel.customercorner:id/btn_connexion")).isDisplayed()) {
				System.out.println("Button de connexion affich� successfully");
			}
			driver.findElement(By.id("com.obertys.fadel.customercorner:id/btn_connexion")).click();
			System.out.println("Click sur le bouton de connexion");
	 }
	 
	 @Test(invocationCount = 100)
	 public void createAccount() throws InterruptedException {
		 		
		// Page Home
		WebDriverWait wait = new WebDriverWait(driver, 30);				
			
		//  Hamburger Item Ouverture Compte
		MobileElement Menu = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.ImageButton[@content-desc='Open navigation drawer']")));
		Menu.click();
		// DropDown Menu Ouverture de Compte
		MobileElement DropDownMenu = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.obertys.fadel.customercorner:id/expandableListView")));
		DropDownMenu.click();
		System.out.println("Click sur le DropDown Menu");
		
		// Find all Elements in the list
		List<MobileElement> expandedListItem = driver.findElements(By.id("com.obertys.fadel.customercorner:id/expandedListItem"));
		System.out.println("**********************************************");
		System.out.println("*** Listes du DropDown Menu Ouverture Compte ***");
		for (MobileElement listItem : expandedListItem) {
			System.out.println(listItem.getText());
		}
		
		//MobileElement listClassName = driver.findElement(By.className("android.widget.TextView"));
		// Select 'Nouveau' pour Ouverture d'un nouveau compte
		if(expandedListItem.get(0) != null) {
			assertTrue("Affichage de la liste du DropDown Menu", expandedListItem.get(0).isDisplayed());
			expandedListItem.get(0).click();
			System.out.println("**********************************************");
			System.out.println("Click sur 'Nouveau' pour la cr�ation de compte");
		}
		
		// Find Element Image using ID property and see if it's visible			
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		MobileElement BtnOpenAccount = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.obertys.fadel.customercorner:id/cardView")));
		assertTrue(BtnOpenAccount.isDisplayed());				
		// Click sur Item "Ouverture de Compte"
		BtnOpenAccount.click();
		
		// V�rifier si le Titre == "Type de Compte"
		MobileElement TitleView = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("android.widget.TextView")));
		assertTrue(TitleView.getText().startsWith("Type"));
		System.out.println("Affichage du titre de la page : " + TitleView.getText());
		
		// List des Card View Suivi d'un Slide
		List<MobileElement> cardView = driver.findElementsById("com.obertys.fadel.customercorner:id/cardView");
		for (MobileElement typeCompte : cardView) {
			System.out.println("Type de Compte Bancaire disponible :" + typeCompte.findElementById("com.obertys.fadel.customercorner:id/titleTextView").getText());
		}
		
		// Swipe left to right and right to left
		System.out.println("*** D�filement des Types de Comptes***");
		@SuppressWarnings("rawtypes")
		TouchAction rightToLeft = new TouchAction(driver);			
		@SuppressWarnings("rawtypes")
		TouchAction leftToRight = new TouchAction(driver);
		rightToLeft.press(PointOption.point(500, 650)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1300))).moveTo(PointOption.point(60, 650)).release().perform();
		System.out.println("==> de la droite vers la gauche");
		
		
		leftToRight.press(PointOption.point(200, 650)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1500))).moveTo(PointOption.point(620, 650)).release().perform();			
		System.out.println("==> de la gauche vers la droite");
		
		
		MobileElement epargneCard = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.view.View/android.view.View/android.widget.FrameLayout/android.view.View/android.support.v4.view.ViewPager/android.widget.FrameLayout[1]"));
		epargneCard.click();
		System.out.println("Click sur View Card 'Compte Epargne'");

 
 
	 
	 System.out.println("*********************************************");
		// V�rifier si le Titre == "Type de Compte"
		MobileElement TitleViewCreateClient = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("android.widget.TextView")));
		assertTrue(TitleViewCreateClient.getText().startsWith("Cr�ation"));
		System.out.println("Affichage du titre de la page : " + TitleViewCreateClient.getText());
		
		// Click Pi�ce d'identit� RECTO *
		List<MobileElement> Cni = driver.findElementsByClassName("android.widget.Button");
		System.out.println("*** Liste des faces � utiliser pour la Carte d'Identit� National ***");
		for (MobileElement faceCNI : Cni) {
			System.out.println("==> : " + faceCNI.getText());
		}
		
		Cni.get(0).click();

	    MobileElement CniItems = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout");

	    
		if(CniItems.isDisplayed()) {
			//System.out.println("S�lection du face : " + Cni.get(0).getText());
			//Cni.get(0).click();
			MobileElement btnGalerieScanRecto = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.Button[2]"));
			btnGalerieScanRecto.click();
			
			Thread.sleep(3000);
			@SuppressWarnings("rawtypes")
			TouchAction FolderPhotoRecto = new TouchAction(driver);
			FolderPhotoRecto.tap(PointOption.point(120, 550)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(2000))).perform();
			
			@SuppressWarnings("rawtypes")
			TouchAction SelectPhotoRecto = new TouchAction(driver);
			SelectPhotoRecto.tap(PointOption.point(120, 250)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).perform();
			MobileElement trimSava = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.android.gallery3d:id/trim_save")));
			trimSava.click();
			// choix de l'emplacement and Once or Always
			Thread.sleep(3000);
		    
		    Thread.sleep(5000);
		    @SuppressWarnings("rawtypes")
			TouchAction BottomToTop = new TouchAction(driver);
		    // click sur le bouton Galerie et Scan
		    BottomToTop.press(PointOption.point(350, 1000)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(350, 580)).release().perform();			
			System.out.println("==> Swipe Vertical : bottom to top");
			
			Thread.sleep(3000);
		    MobileElement BtnScanGallery = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.view.View/android.view.View/android.widget.FrameLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout[1]/android.widget.Button"));
		    			    
		    BtnScanGallery.click();
		    
		    Thread.sleep(3000);
		    
		    // select button Scan et gallery
		    MobileElement Scan = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.Button[2]");
		    Scan.click();
		    
		    Thread.sleep(5000);
		    @SuppressWarnings("rawtypes")
			TouchAction FolderPhotoVerso = new TouchAction(driver);
			FolderPhotoVerso.tap(PointOption.point(120, 550)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).perform();
			System.out.println("Click sur le dossier photo");
			@SuppressWarnings("rawtypes")
			TouchAction SelectPhotoVerso = new TouchAction(driver);
			SelectPhotoVerso.tap(PointOption.point(120, 250)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).perform();
			System.out.println("Selection photo grace � ses coordonn�es X - Y");
		    
			Thread.sleep(3000);
			MobileElement trimSava2 = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.android.gallery3d:id/trim_save")));
			trimSava2.click();
		    Thread.sleep(3000);
		    System.out.println("Enregistrement de la photo Verso");
		    
		    Thread.sleep(5000);
		    @SuppressWarnings("rawtypes")
			TouchAction Bottom = new TouchAction(driver);
		    // click sur le bouton Galerie et Scan
		    Bottom.press(PointOption.point(350, 1000)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1500))).moveTo(PointOption.point(350, 400)).release().perform();
		    Thread.sleep(1000);
		    Bottom.press(PointOption.point(350, 1000)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1500))).moveTo(PointOption.point(350, 400)).release().perform();
		    Thread.sleep(1000);
		    Bottom.press(PointOption.point(350, 1000)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1500))).moveTo(PointOption.point(350, 400)).release().perform();
			System.out.println("==> Swipe Vertical : Go to bottom");
		    
		    MobileElement btnSuivant = driver.findElement(By.id("com.obertys.fadel.customercorner:id/suivant"));
		    btnSuivant.click();
		    System.out.println("click sur le button Suivant");

 
 
	 /********************** Inscrition done ***********************/
	 /**************************************************************/
	 
	    
	    System.out.println("V�rification du titre de la page");
	    MobileElement TitleViewCompteSerenity = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("android.widget.TextView")));
		assertTrue(TitleViewCompteSerenity.getText().startsWith("Compte"));
		System.out.println("Affichage du titre de la page qui est : " + TitleViewCompteSerenity.getText());
		
		List<MobileElement> relativeLayout = driver.findElements(By.className("android.widget.RelativeLayout"));
		System.out.println("*********************************************");
		System.out.println("***** Affichage des Sections Disponible *****");
		for(MobileElement section : relativeLayout) {
			MobileElement sectionBtn = section.findElement(By.className("android.widget.Button"));
			System.out.println("Section : " + sectionBtn.getText());
		}
		
		// Premi�re Section
		relativeLayout.get(0).click();
		
		// Civilit�
		MobileElement civilite = driver.findElement(By.id("android:id/text1"));
		civilite.click();
		System.out.println("Click sur Civilit�");
		
		List<MobileElement> ListCivilite = driver.findElements(By.className("android.widget.CheckedTextView"));
		System.out.println("*** Liste Civilit� ***");
		for(MobileElement listCiv : ListCivilite) {
			System.out.println("==> : " + listCiv.getText());
		}
	    
		// Choix de la Civilit�
		ListCivilite.get(1).click();
		System.out.println("Choix : ==> " + ListCivilite.get(1).getText());
		
		Thread.sleep(3000);
	    Bottom.press(PointOption.point(350, 1000)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1500))).moveTo(PointOption.point(350, 400)).release().perform();
	   				   
	    MobileElement sexe = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.view.View/android.view.View/android.widget.FrameLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.Spinner/android.widget.TextView"));
	    sexe.click();
	    
	    // Liste sexe M ou F
	    List<MobileElement> ListSexe = driver.findElements(By.className("android.widget.CheckedTextView"));
	    for(MobileElement typeSexe : ListSexe) {
	    	System.out.println("**** Sexe : " + typeSexe.getText() + " ****");
	    }
	    
	    // click sur le sexe f�minin
	    Thread.sleep(2000);
	    ListSexe.get(1).click();
	    System.out.println("Choix du Sexe : " + ListSexe.get(1).getText());
	    
	    Thread.sleep(3000);
	    Bottom.press(PointOption.point(350, 1000)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1500))).moveTo(PointOption.point(350, 400)).release().perform();
   		
	    // Statuts
	    MobileElement status = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("android:id/text1")));
	    status.click();
	    
	    Thread.sleep(3000);
	    // Liste des pi�ces d'identit�
	    List<MobileElement> listStatuts = driver.findElements(By.className("android.widget.CheckedTextView"));
	    System.out.println("*** Liste des Pi�ces d'identit� � renseigner ***");
	    for(MobileElement infos : listStatuts) {
	    	System.out.println("*** : " + infos.getText());				    	
	    }
	    
	    listStatuts.get(0).click();
	    System.out.println("Choix : " + listStatuts.get(0).getText());
	    
	    MobileElement numberCni = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.view.View/android.view.View/android.widget.FrameLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.FrameLayout/android.widget.EditText")));
	    numberCni.clear();
	    numberCni.sendKeys("09 2828 635 222");
	    System.out.println("Num�ro de la pi�ce d'identit� : " + numberCni.getText());
	    
	    MobileElement lieuDelivrance = (MobileElement) driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.view.View/android.view.View/android.widget.FrameLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.LinearLayout[3]/android.widget.FrameLayout/android.widget.EditText"));
	    lieuDelivrance.clear();
	    lieuDelivrance.sendKeys("Dakar");
	    System.out.println("Lieu de D�livrance : " + lieuDelivrance.getText());
	    
	    // D�livr� Par
	    MobileElement delivreBy = (MobileElement) driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.view.View/android.view.View/android.widget.FrameLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.LinearLayout[5]/android.widget.FrameLayout/android.widget.EditText"));
	    delivreBy.clear();
	    delivreBy.sendKeys("Police de Dakar");  
	    System.out.println("Papier d�livr� par : " + delivreBy.getText());
	    
	    Thread.sleep(3000);
	    Bottom.press(PointOption.point(350, 1000)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1500))).moveTo(PointOption.point(350, 400)).release().perform();
	    
	    // Situation Familiale
	    MobileElement situationFamiliale = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("android:id/text1")));
	    situationFamiliale.click();
	    
	    List<MobileElement> listSituation = driver.findElements(By.className("android.widget.CheckedTextView"));
	    System.out.println("Affichage de la liste : Situation Familiale");
	    for(MobileElement maSituation : listSituation) {
	    	System.out.println("==> : " + maSituation.getText());
	    }
	    
	    // Choix C�libataire
	    listSituation.get(0).click();
	    System.out.println("Choix : " + listSituation.get(0).getText());
	    
	    // T�l Fixe
	    MobileElement callMobile = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.view.View/android.view.View/android.widget.FrameLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.LinearLayout[5]/android.widget.FrameLayout/android.widget.EditText"));
	    callMobile.clear();
	    callMobile.sendKeys("33 450 20 30");
	    
	    Thread.sleep(3000);
	    Bottom.press(PointOption.point(350, 1000)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1500))).moveTo(PointOption.point(350, 400)).release().perform();
   		
	    // Pays de R�sidence
	    MobileElement paysResidence = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.view.View/android.view.View/android.widget.FrameLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.RelativeLayout[1]/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.Spinner[1]/android.widget.TextView")));
	    paysResidence.click();
	    // scroll List des pays
	    MobileElement paysTest = (MobileElement) driver.findElement(
	    		MobileBy.AndroidUIAutomator(
	    				"new UiScrollable(new UiSelector()).scrollIntoView("
	    				+ "new UiSelector().text(\"S�n�gal\"));"));
	    System.out.println(paysTest.getLocation());
	    paysTest.click();
	    System.out.println("Choix du Pays : " + paysTest.getText());
	    
	    
	    // Niveau d'�ducation
	    MobileElement education = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.view.View/android.view.View/android.widget.FrameLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.RelativeLayout[1]/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.Spinner[2]/android.widget.TextView")));
	    education.click();
	    
	    List<MobileElement> listEducation = driver.findElements(By.className("android.widget.CheckedTextView"));
	    System.out.println("Affichage de la liste : Situation Familiale");
	    for(MobileElement edu : listEducation) {
	    	System.out.println("==> : " + edu.getText());
	    }
	    
	    // Choix Universit�
	    listEducation.get(3).click();
	    System.out.println("Choix : " + listEducation.get(3).getText());
	    
	    MobileElement cooperative = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.view.View/android.view.View/android.widget.FrameLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.RelativeLayout[1]/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.Spinner[3]/android.widget.TextView"));
	    cooperative.click();
	    
	    List<MobileElement> listCooperative = driver.findElements(By.className("android.widget.CheckedTextView"));
	    System.out.println("Affichage de la liste : Situation Familiale");
	    for(MobileElement coop : listCooperative) {
	    	System.out.println("==> : " + coop.getText());
	    }
	    listCooperative.get(1).click();
	    System.out.println("Choix : " + listCooperative.get(1).getText());
	    
	    // D�pli Engagement				    
	    MobileElement engagements = (MobileElement) driver.findElement(By.className("android.widget.Button"));
	    engagements.click();
	    
	    Thread.sleep(3000);
	    Bottom.press(PointOption.point(350, 1000)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1500))).moveTo(PointOption.point(350, 400)).release().perform();
	    
	    				    
	    // Parcourir les textes
	    List<MobileElement> engagementsTxt = driver.findElements(By.className("android.widget.TextView"));
	    // Parcourir les checkBox
	    List<MobileElement> engagementsCheckBox = driver.findElements(By.className("android.widget.CheckBox"));
	    
	    System.out.println("*** Les types de contrats disponible ***");
	    for(MobileElement engagementtxt : engagementsTxt) {
	    	System.out.println("==> : " + engagementtxt.getText());
	    }				    
	    
	    //String firstCheckBox = engagementsCheckBox.get(1).getAttribute("checked");
	    System.out.println("Veuillez accepter le contrat *** ==> " + engagementsTxt.get(4).getText());
	    if(engagementsCheckBox.get(0).isDisplayed()) {
	    	
	    	engagementsCheckBox.get(0).click();
	    }
	    
	    Thread.sleep(3000);
	    Bottom.press(PointOption.point(350, 1000)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1500))).moveTo(PointOption.point(350, 400)).release().perform();
	    Bottom.press(PointOption.point(350, 1000)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1500))).moveTo(PointOption.point(350, 400)).release().perform();
	    Bottom.press(PointOption.point(350, 1000)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1500))).moveTo(PointOption.point(350, 400)).release().perform();
	    Bottom.press(PointOption.point(350, 1000)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1500))).moveTo(PointOption.point(350, 400)).release().perform();
	    Bottom.press(PointOption.point(350, 1000)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1500))).moveTo(PointOption.point(350, 400)).release().perform();
	    
	    Thread.sleep(2000);
	    
	    MobileElement accepter = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.Button[1]")));
	    
	    System.out.println("Choix du Contrat : " + accepter.getText());
	    accepter.click();
	    
	    Thread.sleep(3000);
	    
	    System.out.println("Veuillez accepter le contrat *** ==> " + engagementsTxt.get(5).getText());
	    engagementsCheckBox.get(1).click();
	    Thread.sleep(3000);
	    Bottom.press(PointOption.point(350, 1000)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1500))).moveTo(PointOption.point(350, 400)).release().perform();
	    Bottom.press(PointOption.point(350, 1000)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1500))).moveTo(PointOption.point(350, 400)).release().perform();			   		
	    Thread.sleep(2000);				    
	    MobileElement accepter2 = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.Button[1]")));

//		    List<MobileElement> btnletter = driver.findElements(By.className("android.widget.Button"));
	    accepter2.click();
	    //letterContract.click();
	    System.out.println("Choix du Contrat : " + accepter2.getText());
	    
	    
	    Thread.sleep(5000);
	    System.out.println("Veuillez accepter le contrat *** ==> " + engagementsTxt.get(6).getText());
	    engagementsCheckBox.get(2).click();
	
	    Thread.sleep(3000);
	    Bottom.press(PointOption.point(350, 1000)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1500))).moveTo(PointOption.point(350, 400)).release().perform();
	    Bottom.press(PointOption.point(350, 1000)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1500))).moveTo(PointOption.point(350, 400)).release().perform();			    
	    Thread.sleep(2000);
	    MobileElement accepter3 = (MobileElement) driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.Button[1]"));
	    accepter3.click();
	    System.out.println("Choix du Contrat : " + accepter3.getText());				    
	    				    
	    Bottom.press(PointOption.point(350, 1000)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1500))).moveTo(PointOption.point(350, 400)).release().perform();
	    Thread.sleep(5000);
	    
	    MobileElement conditionsGeneralesTab = (MobileElement) driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.view.View/android.view.View/android.widget.FrameLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.RelativeLayout[2]/android.widget.Button"));
	    conditionsGeneralesTab.click();				    
	    
	    Thread.sleep(2000);
	    MobileElement cg = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.view.View/android.view.View/android.widget.FrameLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.RelativeLayout[2]/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.CheckBox"));
	    cg.click();
	    
	    Thread.sleep(5000);
	    // Accepter les conditions g�n�rales
	    List<MobileElement> acceptCG = driver.findElements(By.className("android.widget.Button"));
	    acceptCG.get(0).click();
	    System.out.println("Accepter les Conditions G�n�rales");
	    
	    // Cliquer sur le bouton Signature
	    MobileElement btnSign = (MobileElement) driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.view.View/android.view.View/android.widget.FrameLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.RelativeLayout[2]/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.Button")); 
	    btnSign.click();
	    try {
			// Signature dans le Canvas
	    	WebElement CanvaSign = (MobileElement) wait.until(ExpectedConditions.elementToBeClickable(By.className("android.view.View")));
	    	
	    	Actions actionProvider = new Actions(driver);
	    	
	    	actionProvider.clickAndHold(CanvaSign).moveByOffset(20, 150).build().perform();
	    	actionProvider.clickAndHold(CanvaSign).moveByOffset(20, 300).build().perform();
	    	actionProvider.release();
	    	System.out.println("Signature du Contrat");
	    	// Valider la signature
	    	MobileElement signValidate = (MobileElement) driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.Button[1]"));
	    	signValidate.click();
	    	System.out.println("Choix de la signature : " + signValidate.getText());
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
     
     // Performs release event
     
     
     Thread.sleep(3000);
	    Bottom.press(PointOption.point(350, 1000)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1500))).moveTo(PointOption.point(350, 400)).release().perform();
	    Bottom.press(PointOption.point(350, 1000)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1500))).moveTo(PointOption.point(350, 400)).release().perform();
	    
	    MobileElement pieceJointe = (MobileElement) driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.view.View/android.view.View/android.widget.FrameLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.RelativeLayout[2]/android.widget.Button"));
	    pieceJointe.click();
	    
	    Thread.sleep(3000);
	    Bottom.press(PointOption.point(350, 1000)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1500))).moveTo(PointOption.point(350, 400)).release().perform();
	    Bottom.press(PointOption.point(350, 1000)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1500))).moveTo(PointOption.point(350, 400)).release().perform();
	    Bottom.press(PointOption.point(350, 1000)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1500))).moveTo(PointOption.point(350, 400)).release().perform();
	    
	    MobileElement photo = (MobileElement) driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.view.View/android.view.View/android.widget.FrameLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.Button"));
	    photo.click();
	    
	    Thread.sleep(3000);
	    MobileElement btnGalery = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.Button[2]")));
	    btnGalery.click();
	    
	    Thread.sleep(5000);
	    @SuppressWarnings("rawtypes")
		TouchAction FolderPhoto = new TouchAction(driver);
		FolderPhoto.tap(PointOption.point(120, 550)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).perform();
		System.out.println("Click sur le dossier photo");
		@SuppressWarnings("rawtypes")
		TouchAction SelectPhoto = new TouchAction(driver);
		SelectPhoto.tap(PointOption.point(120, 250)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).perform();
		
		Thread.sleep(3000);
		
		MobileElement trimSava3 = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.android.gallery3d:id/trim_save")));
		trimSava3.click();
	    System.out.println("Enregistrement de la photo");
	    
	    Thread.sleep(3000);
	    Bottom.press(PointOption.point(350, 1000)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1500))).moveTo(PointOption.point(350, 400)).release().perform();
	    Bottom.press(PointOption.point(350, 1000)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1500))).moveTo(PointOption.point(350, 400)).release().perform();
	    Thread.sleep(3000);
	    MobileElement saveAndSend = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.view.View/android.view.View/android.widget.FrameLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.Button[2]"));
	    saveAndSend.click();
	    System.out.println("Enregistrer et Enregistrer");
	    
	    @SuppressWarnings("rawtypes")
		TouchAction endInscription = new TouchAction(driver);
	    endInscription.tap(PointOption.point(300, 800)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).perform();
		
		}
	 } 
	 @AfterClass
	 public void afteClass() {
	 }
}
