-- drop 'operational' data
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'$(db_schema).[fracture_demo]') AND type in (N'U'))
	drop table $(db_schema).fracture_demo
;
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'$(db_schema).[document_class]') AND type in (N'U'))
	drop table $(db_schema).document_class
;
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'$(db_schema).[anno_contain]') AND type in (N'U'))
	drop table $(db_schema).anno_contain
;
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'$(db_schema).[anno_source_doc_info]') AND type in (N'U'))
	drop table $(db_schema).anno_source_doc_info
;
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'$(db_schema).[anno_num_token]') AND type in (N'U'))
	drop table $(db_schema).anno_num_token
;
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'$(db_schema).[anno_word_token]') AND type in (N'U'))
	drop table $(db_schema).anno_word_token
;
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'$(db_schema).[anno_base_token]') AND type in (N'U'))
	drop table $(db_schema).anno_base_token
;
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'$(db_schema).[anno_segment]') AND type in (N'U'))
	drop table $(db_schema).anno_segment
;
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'$(db_schema).[anno_umls_concept]') AND type in (N'U'))
	drop table $(db_schema).anno_umls_concept
;
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'$(db_schema).[anno_ontology_concept]') AND type in (N'U'))
	drop table $(db_schema).anno_ontology_concept
;
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'$(db_schema).[anno_named_entity]') AND type in (N'U'))
	drop table $(db_schema).anno_named_entity
;
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'$(db_schema).[anno_sentence]') AND type in (N'U'))
	drop table $(db_schema).anno_sentence
;
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'$(db_schema).[anno_date]') AND type in (N'U'))
	drop table $(db_schema).anno_date
;
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'$(db_schema).[anno_base]') AND type in (N'U'))
	drop table $(db_schema).anno_base
;
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'$(db_schema).[document]') AND type in (N'U'))
	drop table $(db_schema).document
;
