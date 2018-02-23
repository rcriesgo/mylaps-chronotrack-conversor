package es.bitten;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class Main {

	public static void main(String[] args) throws IOException {

		if (args.length < 3) {
			System.out.println("Faltan parámetros. Debes indicar: ");
			System.out.println("ficheroMylaps PUNTO ficheroResultado");

			System.exit(0);
		}

		String mylapsFile = args[0];
		String pointName = args[1];
		String resultFile = args[2];

		int contador = 1;

		Reader reader = Files.newBufferedReader(Paths.get(mylapsFile));
		CSVReader csvReader = new CSVReader(reader, ' ');

		Writer writer = Files.newBufferedWriter(Paths.get(resultFile), StandardCharsets.ISO_8859_1,
				StandardOpenOption.CREATE);

		CSVWriter csvWriter = new CSVWriter(writer, CSVWriter.DEFAULT_SEPARATOR, CSVWriter.NO_QUOTE_CHARACTER,
				CSVWriter.DEFAULT_ESCAPE_CHARACTER, "\r\n");

		// Reading Records One by One in a String array
		String[] nextRecord;
		while ((nextRecord = csvReader.readNext()) != null) {
			System.out.println("chip : " + nextRecord[0]);

			csvWriter.writeNext(new String[] { "CT01_13", String.valueOf(contador), pointName, nextRecord[0],
					nextRecord[1], "1", "111976", "7" });

			contador++;
		}
		
		csvReader.close();
		csvWriter.close();

	}
}