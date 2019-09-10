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
use sims;
SET SQL_SAFE_UPDATES = 0;

delete from session
where code like 'Auto_%' or subjectId in (select id from subject where code like 'Auto_%'
or subject.awardingInstituteId in (select id from awardingInstitute where code like 'Auto_%')
or subject.facultyId in (select id from faculty where code like 'Auto_%') 
or subject.departmentId in (select id from department where code like 'Auto_%'))
;
 
delete from mappedSubject
where mappedSubject in (select id from subject where code like 'Auto_%');

delete from mappedSubject
where mainSubject in (select id from subject where code like 'Auto_%');

delete from subject
where code like 'Auto_%' 
or awardingInstituteId in (select id from awardingInstitute where code like 'Auto_%')
or facultyId in (select id from faculty where code like 'Auto_%') 
or departmentId in (select id from department where code like 'Auto_%')
;

delete from programType
where code like 'Auto_%';

delete from academicYear
where year like 'Auto_%' or academicYear.awardingInstituteId in (select id from awardingInstitute where code like 'Auto_%')
or academicYear.facultyId in (select id from faculty where code like 'Auto_%') AND Id <>0;

delete from documentType
where code like 'Auto_%';

delete from classroomFaculty 
where (classroomFaculty.classroomId in (select id from classroom where code like 'Auto_%') AND classroomFaculty.classroomId <>0);

delete from classroomFaculty 
where (classroomFaculty.facultyId in (select id from faculty where code like 'Auto_%') AND classroomFaculty.facultyId <>0);

delete from classroom
where code like 'Auto_%';

delete from awardingInstitute
where code like 'Auto_%';

delete from department
where code like 'Auto_%';

delete from centerFaculty 
where (centerFaculty.centerId in (select id from center where code like 'Auto_%') AND centerFaculty.centerId <>0);

delete from centerFaculty
where (centerFaculty.facultyId in (select id from faculty where code like 'Auto_%') AND centerFaculty.facultyId <> 0);

delete from faculty
where code like 'Auto_%';

delete from center
where code like 'Auto_%';

delete from location
where code like 'Auto_%';






