package ie.gmit.sw;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author Blaine Burke
 * @version 1.0
 */
public class Parser implements Runnable {

	private Database db = null;
	private String file;
	private int k;

	public Parser(String dataLocation, int k) {
		this.file = dataLocation;
		this.k = k;
	}

	public void setDb(Database db) {
		this.db = db;
	}

	public void run() {

		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			String line = null;

			while ((line = br.readLine()) != null) {
				String[] record = line.trim().split("@");
				if (record.length != 2)
					continue;
				parse(record[0], record[1]);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void parse(String text, String lang, int... ks) {
		Language language = Language.valueOf(lang);
		for (int i = 0; i <= text.length() - k; i++) {
			CharSequence kmer = text.substring(i, i + k);
			db.add(kmer, language);
		}
	}

	public void analyseQuery(String file) throws IOException {
		int kmer = 0;
		int frequency = 1;
		String queryFile;

		Map<Integer, LanguageEntry> query = new HashMap<>();

		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

		while ((queryFile = br.readLine()) != null) {
			// break query into kmers , make map out of it
			for (int i = 0; i <= queryFile.length() - k; i++) {
				CharSequence lang = queryFile.substring(i, i + k);
				kmer = lang.hashCode();

				if (query.containsKey(kmer)) {
					frequency += query.get(kmer).getFrequency();
				}

				LanguageEntry l = new LanguageEntry(kmer, frequency);
				query.put(kmer, l);
			}
		}

		System.out.println("the frequency is " + frequency);
		System.out.println("the Kmer is " + kmer);
		Language language = db.getLanguage(query);
		System.out.println("The entered language is " + language);
		pressAnyKeyToContinue();

	}

	private void pressAnyKeyToContinue() {
		System.out.println("Press Enter key to continue...");
		try {
			System.in.read();
		} catch (Exception e) {
		}
	}
}
