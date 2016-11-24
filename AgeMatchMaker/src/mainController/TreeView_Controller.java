package mainController;

import database.Load_Database;
import javafx.event.EventHandler;
import javafx.scene.control.CheckBoxTreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.CheckBoxTreeCell;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;

public class TreeView_Controller {


	private static CheckBoxTreeItem<Object> spieler_Root_aktiv, spieler_Root_Inaktiv, spieler_MainRoot;
	private static CheckBoxTreeItem<Object> karten_Root_Landkarte, karten_Root_Wasserkarte, karten_Root_Funkarten, karten_Root_AusKarten, karten_MainRoot;
	private static CheckBoxTreeItem<Object> voelker_Root_NonPaladin, voelker_Root_Paladin, voelker_MainRoot;


	public TreeView_Controller(TreeView<Object> treeview_Players, TreeView<Object> treeview_Maps, TreeView<Object> treeview_Nations){

				setDragDrop(treeview_Players);
				setDragDrop(treeview_Maps);
				setDragDrop(treeview_Nations);

				//Root für die Spieler in der Treeview
				spieler_MainRoot		= new CheckBoxTreeItem<Object>("Spieler");
				spieler_Root_aktiv		= new CheckBoxTreeItem<Object>("Spieler(aktiv)");
				spieler_Root_Inaktiv	= new CheckBoxTreeItem<Object>("Spieler(inaktiv)");

				//Root für die Karten in der Treeview
				karten_MainRoot			= new CheckBoxTreeItem<Object>("Karten");
				karten_Root_Landkarte	= new CheckBoxTreeItem<Object>("Landkarten");
				karten_Root_Wasserkarte	= new CheckBoxTreeItem<Object>("Wasserkarten");
				karten_Root_Funkarten	= new CheckBoxTreeItem<Object>("Fun-Maps");
				karten_Root_AusKarten	= new CheckBoxTreeItem<Object>("Aussortiert");

				//Root für die Karten in der Treeview
				voelker_MainRoot		= new CheckBoxTreeItem<Object>("Völker");
				voelker_Root_Paladin	= new CheckBoxTreeItem<Object>("Paladin");
				voelker_Root_NonPaladin	= new CheckBoxTreeItem<Object>("NonPaladin");

				//Damit die Roots ausgeklappt angezeigt werden
				spieler_MainRoot.setExpanded(true);
				spieler_Root_aktiv.setExpanded(true);
				karten_MainRoot.setExpanded(true);
				voelker_MainRoot.setExpanded(true);


				fillPlayer();
				fillMap();
				fillNation();

				//Damit die gängisten Einträge direkt angeklickt sind
				karten_Root_Landkarte.setSelected(true);
				karten_Root_Wasserkarte.setSelected(true);
				voelker_Root_Paladin.setSelected(true);
				voelker_Root_NonPaladin.setSelected(true);

				spieler_MainRoot.getChildren().addAll(spieler_Root_aktiv,spieler_Root_Inaktiv);
				karten_MainRoot.getChildren().addAll(karten_Root_Landkarte, karten_Root_Wasserkarte, karten_Root_Funkarten, karten_Root_AusKarten);
				voelker_MainRoot.getChildren().addAll(voelker_Root_Paladin,voelker_Root_NonPaladin);

				//TreeCell sind für die Checkboxes im Treeview
				treeview_Players.setCellFactory(CheckBoxTreeCell.forTreeView());
				treeview_Maps.setCellFactory(CheckBoxTreeCell.forTreeView());
				treeview_Nations.setCellFactory(CheckBoxTreeCell.forTreeView());

				treeview_Players.setRoot(spieler_MainRoot);
				treeview_Maps.setRoot(karten_MainRoot);
				treeview_Nations.setRoot(voelker_MainRoot);

	}

	private void fillPlayer(){

		Load_Database dbPlayer = new Load_Database();
		for(int i=0;i<dbPlayer.getLoadPlayers().size();i++){

			if(dbPlayer.getLoadPlayers().get(i).isAktiv()==true){
				CheckBoxTreeItem<Object> box = new CheckBoxTreeItem<Object>(dbPlayer.getLoadPlayers().get(i));
				spieler_Root_aktiv.getChildren().add(box);
			}else{
				CheckBoxTreeItem<Object> box = new CheckBoxTreeItem<Object>(dbPlayer.getLoadPlayers().get(i));
				spieler_Root_Inaktiv.getChildren().add(box);
			}
		}
	}

	private void fillMap(){

		Load_Database dbMaps = new Load_Database();
		for(int i=0;i<dbMaps.getLoadMaps().size();i++){
			//System.out.println(dbMaps.getLoadMaps().get(i).getKategorie().length());
			if(dbMaps.getLoadMaps().get(i).getKategorie().equals("Land")){
				CheckBoxTreeItem<Object> box = new CheckBoxTreeItem<Object>(dbMaps.getLoadMaps().get(i));
				karten_Root_Landkarte.getChildren().add(box);
			}else if(dbMaps.getLoadMaps().get(i).getKategorie().equals("Wasser")){
				CheckBoxTreeItem<Object> box = new CheckBoxTreeItem<Object>(dbMaps.getLoadMaps().get(i));
				karten_Root_Wasserkarte.getChildren().add(box);
			}else if(dbMaps.getLoadMaps().get(i).getKategorie().equals("Fun")){
				CheckBoxTreeItem<Object> box = new CheckBoxTreeItem<Object>(dbMaps.getLoadMaps().get(i));
				karten_Root_Funkarten.getChildren().add(box);
			}else{
				CheckBoxTreeItem<Object> box = new CheckBoxTreeItem<Object>(dbMaps.getLoadMaps().get(i));
				karten_Root_AusKarten.getChildren().add(box);
			}
		}
	}

	private void fillNation(){

		Load_Database dbNations = new Load_Database();
		for(int i=0;i<dbNations.getLoadNations().size();i++){

			if(dbNations.getLoadNations().get(i).isPala()==true){
				CheckBoxTreeItem<Object> box = new CheckBoxTreeItem<Object>(dbNations.getLoadNations().get(i));
				voelker_Root_Paladin.getChildren().add(box);
			}else{
				CheckBoxTreeItem<Object> box = new CheckBoxTreeItem<Object>(dbNations.getLoadNations().get(i));
				voelker_Root_NonPaladin.getChildren().add(box);
			}
		}
	}

	private void setDragDrop(TreeView<Object> treeview){
		treeview.setOnDragDetected(new EventHandler<MouseEvent>() {
		    public void handle(MouseEvent event) {

		    	Dragboard db = treeview.startDragAndDrop(TransferMode.ANY);

		        ClipboardContent content = new ClipboardContent();

		        content.putString(treeview.getSelectionModel().getSelectedItem().getValue().toString());
				db.setContent(content);
		        event.consume();
		    }
		});
	}

	public static CheckBoxTreeItem<Object> getSpieler_Root_aktiv() {
		return spieler_Root_aktiv;
	}

	public static CheckBoxTreeItem<Object> getSpieler_Root_Inaktiv() {
		return spieler_Root_Inaktiv;
	}

	public static CheckBoxTreeItem<Object> getKarten_Root_Landkarte() {
		return karten_Root_Landkarte;
	}

	public static CheckBoxTreeItem<Object> getKarten_Root_Wasserkarte() {
		return karten_Root_Wasserkarte;
	}

	public static CheckBoxTreeItem<Object> getKarten_Root_Funkarten() {
		return karten_Root_Funkarten;
	}

	public static CheckBoxTreeItem<Object> getKarten_Root_AusKarten() {
		return karten_Root_AusKarten;
	}

	public static CheckBoxTreeItem<Object> getVoelker_Root_NonPaladin() {
		return voelker_Root_NonPaladin;
	}

	public static CheckBoxTreeItem<Object> getVoelker_Root_Paladin() {
		return voelker_Root_Paladin;
	}
}
