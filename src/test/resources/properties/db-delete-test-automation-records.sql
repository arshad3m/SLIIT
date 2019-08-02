/* 
 * This script is used to delete all the records that are populated while running the automation tests. 
 * All the entries populated during the automation should be prefixed with "Auto_"
 * 
 * NOTE: It is important to maintain the order of deletion due to the relationship of the tables.
 * 
 * Author : IndikaR
 * Created Date : 07/11/2019
 *
 * Modified Date : <date>
 * Modified By : <name>
 *
 */
 
delete from sims.awardingInstitute
where code like 'Auto_%';

delete from sims.centerFaculty
where centerFaculty.centerId in (select id from sims.center where code like 'Auto_%');

delete from sims.centerFaculty
where centerFaculty.facultyId in (select id from sims.faculty where code like 'Auto_%');

delete from sims.center
where code like 'Auto_%';

delete from sims.location
where code like 'Auto_%';

delete from sims.department
where code like 'Auto_%';

delete from sims.faculty
where code like 'Auto_%';

delete from sims.documentType
where code like 'Auto_%';