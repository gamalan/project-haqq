/**
 * 
 */
package slab.haqq.lib;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jqurantree.orthography.Chapter;
import org.jqurantree.orthography.Document;

import android.content.Context;
import android.util.Log;
import slab.haqq.lib.adapter.model.Sura;

/**
 * @author rasxen
 * 
 */
public final class GlobalController {
	public final static int PREPARING_CODE = 0x00;
	public final static int PARSING_XML_CODE = 0x01;
	public final static int BULDING_MODEL_CODE = 0x02;
	public final static int FINISHING_CODE = 0x03;

	public final static String PREPARING = "Preparing...";
	public final static String PARSING_XML = "Parsing XML";
	public final static String BUILDING_MODEL = "Adding Data to Model";
	public final static String FINISHING = "Put Finishing Touch";

	// Splash screen timer
	public static int SPLASH_TIME_OUT = 3000;
	// SuraList
	public static List<Sura> SuraList = new ArrayList<Sura>();
	// SuraMap
	public static Map<String, Sura> SuraMap = new HashMap<String, Sura>();
	// Init Code
	private static int initCode = PREPARING_CODE;
	// Init Message
	private static String initTag = PREPARING;
	private static String initMessage = "";

	public static void init(Context context) {
		if (initCode != FINISHING_CODE) {
			boolean oneTime = false;
			// init xml
			System.setProperty("org.xml.sax.driver",
					"org.xmlpull.v1.sax2.Driver");
			initTag = PARSING_XML;
			initMessage = "Parsing Qur'an Properties";
			initCode = PARSING_XML_CODE;
			new QuranPropertiesReader(context);
			Log.v("init",
					String.valueOf(QuranPropertiesReader.sProperties.size()));
			initMessage = "Parsing Qur'an Data From Tanzil";
			for (Chapter ch : Document.getChapters()) {
				if (oneTime == false) {
					initTag = BUILDING_MODEL;
					initCode = BULDING_MODEL_CODE;
					oneTime = true;
				}
				Log.v("init",
						QuranPropertiesReader.sProperties.get(
								ch.getChapterNumber() - 1).getTname());
				initMessage = "Adding "
						+ QuranPropertiesReader.sProperties.get(
								ch.getChapterNumber() - 1).getTname();
				AddSura(new Sura(String.valueOf(ch.getChapterNumber()),
						ch.getChapterNumber(),
						QuranPropertiesReader.sProperties.get(
								ch.getChapterNumber() - 1).getTname(), ch
								.getName().toUnicode(), ch.getVerseCount()));
			}
			initTag = FINISHING;
			initMessage = "";
			initCode = FINISHING_CODE;
		}
	}

	private static void AddSura(Sura item) {
		SuraList.add(item);
		SuraMap.put(item.getId(), item);
	}

	/**
	 * @return the initCode
	 */
	public static int getInitCode() {
		return initCode;
	}

	/**
	 * @return the initTag
	 */
	public static String getInitTag() {
		return initTag;
	}

	/**
	 * @return the initMessage
	 */
	public static String getInitMessage() {
		return initMessage;
	}

}
