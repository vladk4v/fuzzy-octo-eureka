package company;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {

	private static StringBuilder out = new StringBuilder();

	private static Deque<File> dirs = new ArrayDeque<>();
	private static Deque<File> items = new ArrayDeque<>();


	private static void preparationToCreationDirectory(String path) {
		File someDir = new File(path);
		dirs.add(someDir);
	}

	private static void preparationToCreationFile(String path, String child) {
		File someItem = new File(path, child);
		items.add(someItem);
	}

	//создать папку
	private static void createDirectory(Deque<File> deq) {
		for (File f : deq) {
			if (f.mkdir()) {
				System.out.println(f + " directory was created");
				out.append(f + " directory was created \n");
			} else {
				System.out.println("Creating directory " + f + " was failed");
				out.append("Creating directory " + f + " was failed \n");
			}
		}
	}

	//создать файл
	private static void createFile(Deque<File> deq) {
		for (File f1 : deq) {
			try {
				if (f1.createNewFile()) {
					System.out.println(f1 + " file was created");
					out.append(f1 + " file was created \n");
				} else {
					System.out.println("Creating file " + f1 + " was failed");
					out.append("Creating file " + f1 + " was failed \n");
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	//записать в temp
	private static void fillTemp(String pathToTemp) {
		String log = out.toString();

		try (FileWriter writer = new FileWriter(
				pathToTemp, false)) {
			writer.write(log);
			writer.flush();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {


		preparationToCreationDirectory("C:\\Games\\src");
		preparationToCreationDirectory("C:\\Games\\res");
		preparationToCreationDirectory("C:\\Games\\savegames");
		preparationToCreationDirectory("C:\\Games\\temp");
		preparationToCreationDirectory("C:\\Games\\src\\main");
		preparationToCreationDirectory("C:\\Games\\src\\test");

		preparationToCreationFile("C:\\Games\\src\\main", "Main.java");
		preparationToCreationFile("C:\\Games\\src\\main", "Utils.java");

		preparationToCreationDirectory("C:\\Games\\res\\drawables");
		preparationToCreationDirectory("C:\\Games\\res\\vectors");
		preparationToCreationDirectory("C:\\Games\\res\\icons");

		preparationToCreationFile("C:\\Games\\temp", "temp.txt");

		createDirectory(dirs);
		createFile(items);
		fillTemp("C:\\Games\\temp\\temp.txt");

	}
}
