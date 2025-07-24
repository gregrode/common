package com.gregrode.common.util;

import static com.gregrode.common.util.Util.verify;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The <code>FileUtility</code> class is used to read and write to/from a given
 * file.
 *
 * @author Gregroy Dennis
 *
 */
public class FileUtility {
	private final Path filePath;

	/**
	 * Full constructor
	 *
	 * @param filePath The file to write to
	 * @throws IOException
	 */
	public FileUtility(final Path filePath) throws IOException {
		this.filePath = verify(filePath, "No file specified.");
		File file = filePath.toFile();
		if (!file.exists()) {
			file.getParentFile().mkdirs();
			file.createNewFile();
		}
	}

	public File getFile() {
		return filePath.toFile();
	}

	/**
	 * Read the contents and return the content of the file as a string.
	 *
	 * @return String
	 * @throws IOException
	 */
	public String readLines() throws IOException {
		return readLineAsList().stream().collect(Collectors.joining(String.format("%n")));
	}

	/**
	 * Read the contents and return the content of the file as a list of string.
	 * 
	 * @return List
	 * @throws IOException
	 */
	public List<String> readLineAsList() throws IOException {
		return Files.readAllLines(this.filePath);
	}

	/**
	 * Write the given data to the given file without appending a new line
	 *
	 * @param line The data to write
	 * @throws IOException
	 */
	public void write(final String line) throws IOException {
		write(line.getBytes());
	}

	/**
	 * Write the given data to the given file and append a new line
	 *
	 * @param lines The data to write
	 * @throws IOException
	 */
	public void writeln(final String... lines) throws IOException {
		for (final String line : lines) {
			write(line);
			newLine();
		}
	}

	/**
	 * Write the given data to the given file and append a new line
	 *
	 * @param lines The data to write
	 * @throws IOException
	 */
	public void write(byte[] lines) throws IOException {
		Files.write(filePath, lines, StandardOpenOption.WRITE, StandardOpenOption.APPEND);
	}

	/**
	 * Write a new line to the end of the file.
	 * 
	 * @throws IOException
	 */
	public void newLine() throws IOException {
		write(String.format("%n"));
	}

	/**
	 * Truncate the file.
	 * 
	 * @throws IOException
	 */
	public void truncate() throws IOException {
		Files.write(filePath, "".getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
	}

	/**
	 * @param lines the lines to rewrite
	 * @throws IOException
	 */
	public void rewrite(final List<String> lines) throws IOException {
		truncate();
		for (final String line : lines) {
			writeln(line);
		}
	}

	/**
	 * @param lines the lines to rewrite.
	 * @throws IOException
	 */
	public void rewrite(final String... lines) throws IOException {
		rewrite(List.of(lines));
	}

	/**
	 * @param paths the relative path
	 * @return the full path based on the operating system
	 */
	public static Path getPath(final String... paths) {
		return FileSystems.getDefault().getPath(getRootPath().toString(), paths);
	}

	/**
	 * @return get the root path based on the operating system
	 */
	public static Path getRootPath() {
		if (isWindows()) {
			return FileSystems.getDefault().getPath(System.getenv("SystemDrive"), File.separator);
		}
		return FileSystems.getDefault().getPath(System.getProperty("user.home"));
	}

	/**
	 * @return a boolean that indicates if the operating system is MircoSoft Windows
	 *         or not.
	 */
	public static boolean isWindows() {
		return System.getProperty("os.name").toLowerCase().contains("win");
	}

}
