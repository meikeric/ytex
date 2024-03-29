drop table if exists v_i2b2_fword_lookup;

create table v_i2b2_fword_lookup (
  cui char(8) not null,
  fword varchar(100) not null,
  fstem varchar(100) not null,
  tok_str varchar(250) not null,
  stem_str varchar(250) not null
 ) engine=myisam, comment 'umls lookup table, created from umls_aui_fword and mrconso' ;

insert into v_i2b2_fword_lookup
select mrc.cui, c.fword, c.fstem, c.tok_str, c.stem_str
from umls_aui_fword c
inner join @UMLS_SCHEMA@.MRCONSO mrc on c.aui = mrc.aui
where  mrc.SAB in ('RXNORM', 'SNOMEDCT', 'SRC', 'MTH', 'MSH', 'CSP', 'MEDLINEPLUS', 'MEDCIN')
and exists
(
	select *
	from @UMLS_SCHEMA@.MRSTY sty
	where mrc.cui = sty.cui
	and sty.tui in
	(
	'T017' /* Anatomical Structure */,
	'T021','T022','T023','T024','T025','T026','T029','T030','T031',
	'T059','T060','T061',
	'T019','T020','T037','T046','T047','T048','T049','T050','T190','T191',
	'T033','T034','T040','T041','T042','T043','T044','T045','T046','T056','T057','T184','T121'
	)
)
;
-- perhaps add 
-- T119 Lipid
-- T109	Organic Chemical
-- T123	Biologically Active Substance

create index idx_fword on v_i2b2_fword_lookup (fword);
create index idx_cui on v_i2b2_fword_lookup (cui);
