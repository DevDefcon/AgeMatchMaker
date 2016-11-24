package subController;

import java.awt.Desktop;
import java.io.File;

import mainController.Match_Controller;
import database.Load_Database;

public class Match_Controller_MapPreview extends Match_Controller{
	
	public void mapPreview(String chosenMap){
		
		Load_Database db = new Load_Database();
		String pictureFormat[] = {".jpg",".Bmp",".png"};
		
		for(int i=0;i<db.getLoadMaps().size();i++){
			
			for(int j=0;j<pictureFormat.length;j++){
				
				if(chosenMap.equals(db.getLoadMaps().get(i).toString())){
					Desktop desktop = Desktop.getDesktop();
					File jpg;
					try{
							jpg = new File("MapPreview/"+db.getLoadMaps().get(i).toString()+pictureFormat[j]);
							desktop.open(jpg);
					}
					catch(Exception oError){
					}
				}
			}
			
		}
	}
	
	
}
