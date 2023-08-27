import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.IntStream;
import java.util.Scanner;

public class Program {

	private static Scanner keyboardScanner = new Scanner (System.in);

	public static void main (String[] args){

		start(args);
		
	}

	private static void start(String[] stack){

		switch(stack.length){
			
			case 0:
				System.out.print("Para poder continuar, por favor introduzca la ruta al CSV: ");
				String path = keyboardScanner.nextLine();
				start(new String[]{path});
			break;

			default: 
				for (String f : stack){

					File db = new File(f);
					System.out.println("\nColumnas disponibles en el archivo \"" + db.getAbsolutePath() + "\": ");
					
					try {

						String[] csample = getColumnSample(db);

						IntStream.range(0,csample.length)
							.mapToObj(i -> "["+(i+1)+"] " + csample[i])
							.forEach(System.out::println);

						System.out.print("Escoja una columna (pueden ser varias separadas por un espacio): ");
						String input[] = keyboardScanner.nextLine().split(" ");
						System.out.println();
						
						for (String i : input){
							System.out.println("La longuitud maxima en la columna " + i  + " es igual a " + getMaxLen(db.toPath(), Integer.parseInt(i)-1));
						}

						System.out.println();
						
					} catch (Exception e) {
						System.out.println("\n[ERROR] El archivo \"" + db.getAbsolutePath() + "\" no es un archivo de valores separados por comas.");
					}
				}

		}

	}

	private static String[] getColumnSample (File db) throws IOException{
		Scanner fScanner = new Scanner(db);
		String[] cSample = fScanner.nextLine().split(",");
		fScanner.close();
		return cSample;
	}

	private static int getMaxLen(Path path , int cID) throws IOException{

		return Files.lines(path).mapToInt(s -> s.split(",")[cID].length()).max().getAsInt();

	}

}