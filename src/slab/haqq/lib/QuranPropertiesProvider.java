/**
 * 
 */
package slab.haqq.lib;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import android.content.Context;

/**
 * @author rasxen
 * <p><h1>QuranPropertiesProvider</h1></p>
 * <p>a provider of quran properties</p>
 */
public class QuranPropertiesProvider {
	private static String RES_PATH = "xml/quran-properties-en.xml";
	public static List<SuraProperties> sProperties = new ArrayList<SuraProperties>();

	/**
	 * A constructor for QuranPropertiesProvider
	 * @param context
	 */
	public QuranPropertiesProvider(Context context) {
		try {
			sProperties.clear();
			XMLReader reader = XMLReaderFactory.createXMLReader();
			reader.setContentHandler(new SuraPropertiesHandler());

			InputStream stream = context.getAssets().open(RES_PATH);
			InputSource source = new InputSource(stream);

			reader.parse(source);
			stream.close();

		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @author rasxen
	 * a sax event handler when reading quran properties xml
	 */
	public class SuraPropertiesHandler extends DefaultHandler {
		private static final String SURA_ELEMENT = "sura";

		@Override
		public void startElement(String uri, String localName, String qName,
				Attributes attributes) throws SAXException {
			// TODO Auto-generated method stub
			if (qName.equals(SURA_ELEMENT)) {
				QuranPropertiesProvider.sProperties.add(new SuraProperties(
						Integer.parseInt(attributes.getValue("index")),
						attributes.getValue("name"), attributes
								.getValue("tname")));
			}
		}
	}

	/**
	 * @author rasxen
	 *	TODO : Documentation
	 */
	public static class SuraProperties {
		private int id;
		private String name;
		private String tname;

		/**
		 * TODO : Documentation
		 * @param id
		 * @param name
		 * @param tname
		 */
		public SuraProperties(int id, String name, String tname) {
			this.id = id;
			this.name = name;
			this.tname = tname;
		}

		/**
		 * @return the id
		 */
		public int getId() {
			return id;
		}

		/**
		 * @param id
		 *            the id to set
		 */
		public void setId(int id) {
			this.id = id;
		}

		/**
		 * @return the name
		 */
		public String getName() {
			return name;
		}

		/**
		 * @param name
		 *            the name to set
		 */
		public void setName(String name) {
			this.name = name;
		}

		/**
		 * @return the tname
		 */
		public String getTname() {
			return tname;
		}

		/**
		 * @param tname
		 *            the tname to set
		 */
		public void setTname(String tname) {
			this.tname = tname;
		}
	}
}
