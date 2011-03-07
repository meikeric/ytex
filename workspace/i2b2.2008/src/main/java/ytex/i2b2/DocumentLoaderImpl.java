package ytex.i2b2;

import java.io.File;

import javax.sql.DataSource;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.ParserAdapter;

import ytex.kernel.KernelContextHolder;

public class DocumentLoaderImpl {

	private SimpleJdbcTemplate jdbcTemplate;
	
	public DocumentLoaderImpl() {
		DataSource ds = KernelContextHolder.getApplicationContext().getBean(DataSource.class);
		jdbcTemplate = new SimpleJdbcTemplate(ds); 
	}
		
	protected void insertPatientRecord(int docId, String docText, String documentSet) {
		jdbcTemplate.update("insert into i2b2_2008_doc (docId, docText, documentSet) values (?,?,?)", docId, docText, documentSet);
	}
	
	protected void insertAnno(String source, String disease, int docId, String judgement) {
		jdbcTemplate.update("insert into i2b2_2008_anno (source, disease, docId, judgement) values (?,?,?,?)", source, disease, docId, judgement);
	}

	/**
	 * parse doc annotations, insert into db
	 * @author vijay
	 *
	 */
	public class AnnoHandler extends DefaultHandler {
		
		private String source;
		private String disease;
		public void startElement(String namespace, String localName,
				String qName, Attributes atts) {
			String currTag = localName.toLowerCase();
			if (currTag.equals("diseases")) {
				source = atts.getValue("source");
			} else if (currTag.equals("disease")) {
				disease = atts.getValue("name");
			} else if (currTag.equals("doc")) {
				int docId = Integer.parseInt(atts.getValue("id"));
				String judgement = atts.getValue("judgment");
				insertAnno(source, disease, docId, judgement);
			} 
		}
	}

	/**
	 * parse docs, insert into db
	 * @author vijay
	 *
	 */
	public class PatientRecordsHandler extends DefaultHandler {
		private String currTag;
		private StringBuilder cdata;
		private String documentSet;
		
		private int docId;

		public PatientRecordsHandler(String documentSet) {
			init();
			this.documentSet = documentSet;
		}

		public void init() {
			currTag = "";
		}

		public void startElement(String namespace, String localName,
				String qName, Attributes atts) {
			currTag = localName.toLowerCase();
			if (currTag.equals("doc")) {
				docId = Integer.parseInt(atts.getValue("id"));
				cdata = null;
			} if (currTag.equals("text")) {
				cdata = new StringBuilder();
			}
		}

		public void characters(char[] ch, int start, int length) {
			if(cdata != null)
				cdata.append(new String(ch, start, length));
		}

		public void endElement(String uri, String localName, String qName) {
			if (localName.equalsIgnoreCase("text")) {
				insertPatientRecord(docId, cdata.toString(), documentSet);
				cdata = null;
			}
		}
	}

	/* (non-Javadoc)
	 * @see ytex.cmc.DocumentLoader#process(java.lang.String, java.lang.String)
	 */
	public void process(String urlString, String documentSet) throws Exception {
		System.out.println("Processing URL " + urlString);
		SAXParserFactory spf = SAXParserFactory.newInstance();
		SAXParser sp = spf.newSAXParser();
		ParserAdapter pa = new ParserAdapter(sp.getParser());
		pa.setContentHandler(new PatientRecordsHandler(documentSet));
		pa.parse(urlString);
	}
	
	public void processAnno(String urlString) throws Exception {
		System.out.println("Processing URL " + urlString);
		SAXParserFactory spf = SAXParserFactory.newInstance();
		SAXParser sp = spf.newSAXParser();
		ParserAdapter pa = new ParserAdapter(sp.getParser());
		pa.setContentHandler(new AnnoHandler());
		pa.parse(urlString);
	}
	
	public static void main(String args[]) throws Exception {
		DocumentLoaderImpl l = new DocumentLoaderImpl();
//		l.process(args[0]+ File.separator + "obesity_patient_records_training.xml", "train");
//		l.process(args[0]+ File.separator + "obesity_patient_records_training2.xml", "train");
//		l.process(args[0]+ File.separator + "obesity_patient_records_test.xml", "test");
		l.processAnno(args[0]+ File.separator + "obesity_standoff_annotations_training.xml");
		l.processAnno(args[0]+ File.separator + "obesity_standoff_annotations_test.xml");
	}

}
