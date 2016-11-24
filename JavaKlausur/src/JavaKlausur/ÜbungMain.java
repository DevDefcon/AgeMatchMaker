package JavaKlausur;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ÜbungMain extends JFrame{

	public static void main(String[] args) throws MyException {
		
		new ÜbungMain();
//		int zaehler = 7;
//		int nenner = 0;
//		int ergebnis;
//		
//		try{
//			ergebnis = zaehler/nenner;
//			
//			System.out.println(ergebnis);
//		}
//		catch(ArithmeticException e){
//			e.printStackTrace();
//		}
		
//		Zugriff zu = new Zugriff(5);
//		zu.parseInt("5");
		
		
//		int array[] = {1,2,3,4,5,0,7}; 
//		//System.out.println(prüfArray(array));
//		try{
//			berechnerMittelwert(array);
//		}catch(Exception e){
//			e.printStackTrace();
//		}
		
//		int wert = 7;
//		
//		Zugriff a = new Zugriff(4);
//		Zugriff b = new Zugriff(5);
//		b = a;
//		b = null;
//		
//		System.out.println(a.getZahl());

		//Aufgabe 05.07. Seite 145
//		int eingabe;
//		Scanner scanner = new Scanner(System.in);
//		
//		System.out.println("Eingabe eines Int Werts!");
//		eingabe = scanner.nextInt();
//		
//		int fakultät =1;
//		System.out.println("n-Fakultät von "+eingabe);
//		for(int i=1;i<=eingabe;i++){
//			System.out.println(i+"*"+fakultät+" = "+i*fakultät);
//			fakultät = fakultät*i;
//			
//		}
//		System.out.println(fakultät);
//		scanner.close();
		
		
		//Aufgabe 05.08. Seite 145
		
//		String kompletterName[][] 	= new String[5][2];
//		Scanner scanner = new Scanner(System.in);
//		
//		for(int i=0;i<5;i++){
//			System.out.println("Eingabe des "+i+". Namens");
//			String puffer =scanner.nextLine();
//			String array[] = puffer.split(" ");
//			kompletterName[i][0] = array[0];
//			kompletterName[i][1] = array[1];
//			System.out.println(kompletterName[i][0]);
//			System.out.println(kompletterName[i][1]);
//			//System.out.println(namen[1]);
//		}
//		
//		//ForEach Schleife iteriert durch ein 2D Array
//		// Erst den Datentypen 1D Array angeben : dann das 2D Array kompletter Name
//		for(String namenIn[] : kompletterName){
//			// Datentyp String element angeben, weil das 2D Array ein String ist.
//			// Dann das 1D Array namenIN angeben.
//			for(String element : namenIn){
//				System.out.println(element);
//			}
//		}
//		scanner.close();
		
//		Scanner scanner = new Scanner(System.in);
//		String namen[] 	= new String[5];
//		for(int i=0;i<5;i++){
//			System.out.println("Eingabe des Namens");
//			namen[i] = scanner.nextLine();
//		}
//		
//		for(String element : namen){
//			System.out.println(element);
//		}
//		scanner.close();
		
		//Aufgabe 06.06. Seite 166
		//Zu beachten, die lokale Variable in der Methode von der Klasse Zugriff überschreibt die Instanzvariable zahl in der selbigen Klasse.
//		Zugriff handler = new Zugriff();
//		System.out.println(handler.zugriffZahl());
	}
	
	public ÜbungMain(){
		setLayout(null);
		JPanel pane = new JPanel();
		JPanel pane2 = new JPanel();
		JPanel paneMain = new JPanel();
		JButton button = new JButton("OK");
		JButton but2 = new JButton("Abbrechen");
		JButton but4 = new JButton("uuu");
		
		setLocationRelativeTo(null);
		pane.setLayout(new FlowLayout());
		pane.add(button);
		pane.add(but2);	
		pane2.setLayout(new BorderLayout());
		pane2.add(but4, BorderLayout.SOUTH);
		
		paneMain.add(pane);
		paneMain.add(pane2);
		add(paneMain);
		
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	private static int prüfArray(int array[]){
		int gZahl = array[0];
		
		for(int i=1;i<array.length;i++){
			if(gZahl<array[i]){
				gZahl = array[i];
			}
		}
		
		return gZahl;
	}
	
	/** Ich bin esfsfsfsin Kommentar und erlaeutere die Klasse DocuTest1 
	 * @throws MyException */
	public static double berechnerMittelwert(int werte[]) throws MyException{
		
		int summe = 0;
		
		for(int i=0; i<werte.length;i++){
			if(werte[i]==0){
				throw new MyException("Null nicht erlaubt");
			}
			summe = summe + werte[i];
		}
		
		return (double) summe/werte.length;
	}

}
