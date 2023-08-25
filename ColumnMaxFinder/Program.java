import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;
import java.util.Scanner;

public class Program {

	private static Scanner keyboardScanner = new Scanner (System.in);

	public static void main (String[] args){

		switch(args.length){
			
			case 0:
				System.out.print("Para poder continuar, por favor introduzca la ruta al CSV: ");
				String path = keyboardScanner.nextLine();
				getMax(path);
			break;

			default: 
				for (String f : args){
					getMax(f);
				}

		}
		
	}

	private static void getMax(String path){

		File db = new File(path);
		System.out.println("\nColumnas disponibles en el archivo \"" + db.getAbsolutePath() + "\": ");

		try(Stream<String> lines = Files.lines(Path.of(db.getAbsolutePath())); Scanner fileScanner = new Scanner(db)){

			int columnID;
			int result;
			
			String[] columns = fileScanner.nextLine().split(",");
			IntStream.range(0,columns.length)
				.mapToObj(i -> "["+(i+1)+"] " + columns[i])
				.forEach(System.out::println);

			System.out.print("Escoja una columna: ");
			columnID = Integer.parseInt(keyboardScanner.nextLine()) - 1;
			
			result = lines.mapToInt(s -> s.split(",")[columnID].length()).max().getAsInt();

			System.out.println("El ancho máximo de columna es: " + result);
			
		}catch(Exception e){
			System.out.println("\n[ERROR] El archivo \"" + db.getAbsolutePath() + "\" no es un archivo de valores separados por comas.");
		}

	}

}