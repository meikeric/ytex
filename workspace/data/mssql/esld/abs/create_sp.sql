/*
 * stored procedure called from the access database to create a 
 * abs_radiology record out of an abs_radiology_review record
 */
drop procedure esld.rad_review_to_abs;
go

create procedure esld.rad_review_to_abs 
	@studyid int,
	@doc_uid int,
	@abs_radiology_id int OUT
AS
-- insert the record
insert into esld.abs_radiology (studyid, uid, uid_reviewed, procedure_type, procedure_date)
select r.studyid, r.uid, 1 uid_reviewed, t.rad_procedure_name, l.doc_date
from esld.abs_radiology_review r
inner join esld.ref_rad_procedure_type t on r.rad_procedure_type_id = t.rad_procedure_type_id
inner join esld.v_document_current l on l.study_id = r.studyid and l.uid = r.uid and l.document_type_name = 'RADIOLOGY'
left join esld.abs_radiology ar on r.studyid = ar.studyid and r.uid = ar.uid
where r.rad_procedure_type_id <> 0
and ar.id is null
and r.studyid = @studyid
and r.[uid] = @doc_uid
;
-- return the id of the inserted record.  assume the combo studyid + uid is unique
set @abs_radiology_id = (select top 1 id from esld.abs_radiology where studyid = @studyid and [uid] = @doc_uid);
GO
