insert into $(db_schema).ref_document_type (document_type_id, document_type_name)
values (0, 'PROGRESS_NOTE');

insert into $(db_schema).ref_document_type (document_type_id, document_type_name)
values (1, 'RADIOLOGY');

insert into $(db_schema).ref_document_type (document_type_id, document_type_name)
values (2, 'PATHOLOGY');

insert into $(db_schema).ref_uima_type values(100 , 'ytex.vacs.uima.types.DocumentDate', 'ytex.uima.mapper.DocumentDateAnnotationMapper' );
insert into $(db_schema).ref_uima_type values(101 , 'ytex.vacs.uima.types.DocumentTitle', 'ytex.uima.mapper.AnnotationMapper');
insert into $(db_schema).ref_uima_type values(102 , 'ytex.vacs.uima.types.DocumentKey', 'ytex.uima.mapper.DocumentKeyAnnotationMapper');

insert into $(db_schema).ref_segment_regex (segment_id, regex) values('CLINICAL_HISTORY', '\nClinical History\:\s*\r{0,1}\n');
insert into $(db_schema).ref_segment_regex (segment_id, regex) values('REPORT', '\nReport\:\s*\r{0,1}\n');
insert into $(db_schema).ref_segment_regex (segment_id, regex) values('IMPRESSION', '\nImpression\:\s*\r{0,1}\n');

