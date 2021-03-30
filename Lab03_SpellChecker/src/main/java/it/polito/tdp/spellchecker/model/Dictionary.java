package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Dictionary {
	
	Set<String> setDictionary = new HashSet<>();
	LinkedList<RichWord> elenco = new LinkedList<RichWord>();
	
	public void loadDictionary(String language) {
		
		if(language.equals("English")) {
			try {
				FileReader fr = new FileReader("src/main/resources/English.txt");
				BufferedReader br = new BufferedReader(fr);
				String word;
				while((word = br.readLine())!=null) {
					setDictionary.add(word);
				}
				br.close();
				fr.close();
			}catch(IOException e) {
				System.out.println("ERRORE nella lettura da file");
			}
		}
		else if(language.equals("Italian")) {
			try {
				FileReader fr = new FileReader("src/main/resources/Italian.txt");
				BufferedReader br = new BufferedReader(fr);
				String word;
				while((word = br.readLine())!=null) {
					setDictionary.add(word);
				}
				br.close();
				fr.close();
			}catch(IOException e) {
				System.out.println("ERRORE nella lettura da file");
			}

		}
	}

	public List<RichWord> spellCheckTest(List<String> inputTextList ){
		
		
		for(String s : inputTextList) {
			RichWord w;
			if(setDictionary.contains(s)) {
				w = new RichWord(s, true);
				elenco.add(w);
			}
			else
				w= new RichWord(s, false);
				elenco.add(w);
		}
		
		return elenco;
	}
	
	public String elencoParoleErrate() {
		
		String s = "";
		
		for(RichWord w : this.elenco) {
			if(w.isCorretta()==false)
				s=s+w.getParola()+"\n";
		}
		
		
		return s;
	}
	
	public int numeroParoleErrate() {
		
		int cont = 0;
		for(RichWord w : elenco) {
			if(w.isCorretta()==false)
				cont++;
		}
		
		return cont;
	}
	
	public void resetDictionary() {
		elenco.clear();
		setDictionary.clear();
	}
}
