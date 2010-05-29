package ytex.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import ytex.model.DocumentSearchResult;

public class DocumentSearchDaoImpl implements DocumentSearchDao {
	static final Log log = LogFactory.getLog(DocumentSearchDaoImpl.class);
	SimpleJdbcTemplate jdbcTemplate;
	private DataSource dataSource;
	Properties searchProperties;
	String query;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new SimpleJdbcTemplate(dataSource);
	}

	public DataSource getDataSource() {
		return this.dataSource;
	}

	public void setSearchProperties(Properties searchProperties) {
		this.searchProperties = searchProperties;
		this.query = searchProperties.getProperty("retrieveDocumentByCUI");
	}

	public Properties getSearchProperties() {
		return searchProperties;
	}

	public static class DocumentSearchResultMapper implements
			RowMapper<DocumentSearchResult> {

		@Override
		public DocumentSearchResult mapRow(ResultSet rs, int rowNum)
				throws SQLException {
			DocumentSearchResult result = new DocumentSearchResult();
			result.setCuiText(rs.getString("cui_text"));
			result.setDocumentDate(rs.getDate("doc_date"));
			result.setDocumentID(rs.getInt("document_id"));
			result.setDocumentTitle(rs.getString("doc_title"));
			result.setDocumentTypeName(rs.getString("document_type_name"));
			result.setSentenceText(rs.getString("sentence_text"));
			return result;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.va.vacs.esld.dao.DocumentSearchDao#searchByCui(java.lang.String)
	 */
	public List<DocumentSearchResult> searchByCui(String code) {
		Map<String, Object> mapArgs = new HashMap<String, Object>(1);
		mapArgs.put("code", code);
		return this.jdbcTemplate.query(query, new DocumentSearchResultMapper(),
				mapArgs);
	}

	/**
	 * Extended search
	 * 
	 * @param code
	 *            concept CUI or code. this is the only required argument
	 * @param documentTypeName
	 *            document type name. (in VACS @see DocumentType)
	 * @param dateFrom
	 *            document date greater than or equal to this
	 * @param dateTo
	 *            document date less than or equal to this
	 * @param patientId
	 *            patient id (study id in VACS)
	 * @param negationStatus
	 *            true - only affirmed terms. false - only negated terms.
	 * @return list of results matching query
	 */
	public List<DocumentSearchResult> extendedSearch(String code,
			String documentTypeName, Date dateFrom, Date dateTo,
			Integer patientId, Boolean negationStatus) {
		StringBuilder queryBuilder = new StringBuilder(query);
		Map<String, Object> mapArgs = new HashMap<String, Object>(1);
		mapArgs.put("code", code);
		if (documentTypeName != null) {
			queryBuilder.append("\n").append(
					searchProperties
							.getProperty("retrieveDocumentDocTypeClause"));
			mapArgs.put("document_type_name", documentTypeName);
		}
		if (dateFrom != null) {
			queryBuilder.append("\n").append(
					searchProperties
							.getProperty("retrieveDocumentFromDateClause"));
			mapArgs.put("from_doc_date", dateFrom);
		}
		if (dateTo != null) {
			queryBuilder.append("\n").append(
					searchProperties
							.getProperty("retrieveDocumentToDateClause"));
			mapArgs.put("to_doc_date", dateTo);
		}
		if (patientId != null) {
			queryBuilder.append("\n").append(
					searchProperties
							.getProperty("retrieveDocumentPatientIDClause"));
			mapArgs.put("study_id", patientId);
		}
		if (negationStatus != null) {
			queryBuilder.append("\n").append(
					searchProperties.getProperty("retrieveDocumentNegClause"));
			mapArgs.put("certainty", negationStatus ? 0 : -1);
		}
		String extendedSearchQuery = queryBuilder.toString();
		if (log.isDebugEnabled()) {
			log.debug("executing query, query=" + extendedSearchQuery
					+ ", args=" + mapArgs);
		}
		return this.jdbcTemplate.query(extendedSearchQuery,
				new DocumentSearchResultMapper(), mapArgs);
	}

	/**
	 * perform full text search
	 * @param searchTerm
	 * @return list of maps for each record.  map keys correspond to search query headings.  map (i.e. query) must contain DOCUMENT_ID (integer) and NOTE (string) fields.
	 */
	public List<Map<String, Object>> fullTextSearch(String searchTerm) {
		return this.jdbcTemplate.queryForList(this.searchProperties
				.getProperty("retrieveDocumentFullText"), searchTerm);
	}

	/**
	 * retrieve note for specified document id, retrieved via full text search
	 * @param documentId
	 * @return note text.
	 */
	public String getFullTextSearchDocument(int documentId) {
		return this.jdbcTemplate.queryForObject(this.searchProperties
				.getProperty("retrieveFullTextSearchDocument"), String.class,
				documentId);
	}
}
