package org.wikidata.wdtk.examples;

import java.io.IOException;

import org.wikidata.wdtk.dumpfiles.DumpProcessingController;
import org.wikidata.wdtk.dumpfiles.EntityTimerProcessor;
import org.wikidata.wdtk.dumpfiles.MwLocalDumpFile;

/**
 * This class illustrates how to process local dumpfiles. It uses
 * {@link EntityTimerProcessor} to process a dump.
 * 
 * @author Markus Damm
 *
 */

public class LocalDumpFileProcessor {

	/**
	 * Directory of the dump that should be processed
	 */
	private final static String DUMP_DIRECTORY = "C:/Users/Markus/workspace/wdtk-parent/wdtk-examples/dumpfiles/wikidatawiki/json-20150713/20150713.json.gz";

	// private final static DumpContentType DUMP_CONTENT_TYPE =
	// DumpContentType.JSON;

	// private static EntityDocumentProcessor entityDocumentProcessor = new
	// GenderRatioProcessor();

	public static void main(String[] args) throws IOException {
		ExampleHelpers.configureLogging();
		LocalDumpFileProcessor.printDocumentation();

		DumpProcessingController dumpProcessingController = new DumpProcessingController(
				"wikidatawiki");
		dumpProcessingController.setOfflineMode(true);
		// dumpProcessingController.registerEntityDocumentProcessor(
		// entityDocumentProcessor, null, true);
		EntityTimerProcessor entityTimerProcessor = new EntityTimerProcessor(
				0);
		dumpProcessingController.registerEntityDocumentProcessor(
				entityTimerProcessor, null, true);

		System.out.println(DUMP_DIRECTORY);
		MwLocalDumpFile mwDumpFile = new MwLocalDumpFile(DUMP_DIRECTORY);
		mwDumpFile.prepareDumpFile();
		dumpProcessingController.processDump(mwDumpFile);

		entityTimerProcessor.close();
	}

	/**
	 * Prints some basic documentation about this program.
	 */
	public static void printDocumentation() {
		System.out.println("********************************************************************");
		System.out.println("*** Wikidata Toolkit: ProcessLocalDumpFile");
		System.out.println("*** ");
		System.out.println("*** This program should illustrate how to process local dumps.");
		System.out.println("*** It uses an EntityTimerProcesses which counts processed items");
		System.out.println("*** and elapsed time.");
		System.out.println("*** See source code for further details.");
		System.out.println("********************************************************************");
	}
}