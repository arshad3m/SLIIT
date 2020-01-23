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


/*Deleting intake tables  */
delete from  intakeProgramSpecializationEvaluationGroup where intakeEvaluationGroupId in (select id from intakeEvaluationGroup where
name like 'Auto_%') ;
delete from intakeProgramSpecializationCenter where intakeProgramSpecializationId in 
(select id from intakeProgramSpecialization where intakeId in (select id from intake where code like 'Auto_%' )) ;
Delete from intakeProgramSpecialization where programId in(select id from program where name like 'Auto_%');
delete from intakeEvaluationGroup where intakeId in (select id from intake where code like 'Auto_%') ;
delete from intake where code like 'Auto_%' ;

/*delete Program definition Subject table*/
delete from programDefinitionSubject where programDefinitionId in 
(select id from programDefinition where  code like 'Auto_%' ) or
programDefinitionSemesterId in
(select id from programDefinitionSemester where  name like 'Auto_%' ) or
programDefinitionYearId in
(select id from programDefinitionYear where  name like 'Auto_%' )
;
/*delete Program definition Semester Entry Criteria table*/
delete from programDefinitionSemesterEntryCriteria where programDefinitionSemesterId in
(select id from  programDefinitionSemester where name like 'Auto_%') ;
delete from programDefinitionSemester where name like 'Auto_%' ;

/*delete Program definition Semester table*/
delete from programDefinitionSemester where programDefinitionYearId in
(select id from programDefinitionYear where programDefinitionId
in
(select id from programDefinition where code like 'Auto_%' ));
delete from programDefinitionSemester where programDefinitionYearId in
(select id from programDefinitionYear where name like 'Auto_%' );
delete from programDefinitionSemester where programDefinitionId in
(select id from programDefinition where code like 'Auto_%' );

/*delete Program definition Year Entry Criteria table*/
delete from programDefinitionYearEntryCriteria  where programDefinitionEntryCriterionId in
 (select id from programDefinitionEntryCriterion where programDefinitionId in 
(select id from programDefinition where code like 'Auto_%' ));

delete from programDefinitionYearEntryCriteria  where programDefinitionEntryCriterionId in
 (select id from programDefinitionEntryCriterion where entryCriterionId in 
(select id from entryCriterion where code like 'Auto_%' ));

delete from programDefinitionYearEntryCriteria  where programDefinitionYearId in
 (select id from programDefinitionYear where learningOutcomeId in 
(select id from learningOutcome where code like 'Auto_%' ));

/*delete Program definition Year table*/
delete from programDefinitionYear where programDefinitionId in 
(select id from programDefinition where code like 'Auto_%' );
delete from programDefinitionYear where learningOutcomeId in 
(select id from learningOutcome where code like 'Auto_%' );

/*delete Program definition Entry criterion Subject result table*/
delete from programDefinitionEntryCriterionSubjectResult where programDefinitionEntryCriterionId in 
(select id from programDefinitionEntryCriterion where programDefinitionId in 
(select id from programDefinition where code like 'Auto_%') );
delete from programDefinitionEntryCriterionSubjectResult where programDefinitionEntryCriterionId in 
(select id from programDefinitionEntryCriterion where entryCriterionId in 
(select id from entryCriterion where code like 'Auto_%') );

delete from programDefinitionEntryCriterionSubjectResult where entryCriterionResultId in
(Select id from entryCriterionResult where entryCriterionId in
(select id from entryCriterion where code like 'Auto_%')) ;

delete from programDefinitionEntryCriterionSubjectResult where entryCriterionResultId in
(select id from entryCriterionOutcome where name like 'Auto_%');

/*delete  Program definition Entry criterion special requirement table*/
delete FROM  programDefinitionEntryCriterionSpecialRequirement where programDefinitionEntryCriterionId in 
(select id from programDefinitionEntryCriterion where entryCriterionId in
(select id from entryCriterion where code like 'Auto_%' ));
delete FROM  programDefinitionEntryCriterionSpecialRequirement where programDefinitionEntryCriterionId in 
(select id from programDefinitionEntryCriterion where programDefinitionId in
(select id from programDefinition where code like 'Auto_%' ));
Delete FROM  programDefinitionEntryCriterionSpecialRequirement where programDefinitionEntryCriterionId in 
(select id from programDefinitionEntryCriterion where programDefinitionEntryCriterionId in 
(select id from programDefinition where code like 'Auto_%'));

/*delete  Program definition Entry criterion table*/
Delete from programDefinitionEntryCriterion where programDefinitionId in 
(select id from programDefinition where code like 'Auto_%') ;
Delete from programDefinitionEntryCriterion where entryCriterionId in 
(select id from entryCriterion where code like 'Auto_%') ;

delete from programDefinition where code like 'Auto_%' ;

/********* Program Definitions Deleted **********/

/*Deleting from Grades table */
delete from gradeMark
where gradeId in (select id from grade where code like 'Auto_%');

delete from grade
where code like 'Auto_%';

/*Deleting from program table */
delete from specializationCenter
where specialization in (select id from specialization where (programId in (select id from program where code like 'Auto_%') 
or departmentId in (select id from department where code like 'Auto_%')) )
or center in (select id from center where code like 'Auto_%');

delete from specialization
where name like 'Auto_%' 
or programId in (select id from program where code like 'Auto_%' or (awardingInstituteId in (select id from awardingInstitute where code like 'Auto_%')
or facultyId in (select id from faculty where code like 'Auto_%'))) 
or departmentId in (select id from department where code like 'Auto_%');

delete from program
where code like 'Auto_%' or awardingInstituteId in (select id from awardingInstitute where code like 'Auto_%')
or facultyId in (select id from faculty where code like 'Auto_%');

/*Deleting from  learning outcomes table */
delete from learningOutcome
where code like 'Auto_%';

/*Deleting from Entry Criterion table*/
delete from entryCriterionResult
where (entryCriterionId in (select id from entryCriterion where code like 'Auto_%') AND Id <>0);

delete from entryCriterionOutcome
where (entryCriterionId in (select id from entryCriterion where code like 'Auto_%') AND id <>0);

delete from entryCriterion
where code like 'Auto_%';

/*Deleting from  qualification outcomes table  */
delete from qualificationTypeOutcome
where (qualificationTypeId in (select id from qualificationType where code like 'Auto_%') AND Id <>0);

delete from qualificationType
where code like 'Auto_%';

/* Deleting from  Assessment criterion table */
Delete from subAssessment
where assessmentCriterionId in (Select ID from assessmentCriterion where code like 'Auto_%') and id<>0;

Delete from assessmentCriterion
where code like 'Auto_%';

/* Deleting from  subjects table */
delete from session
where code like 'Auto_%' or subjectId in (select id from subject where code like 'Auto_%'
or subject.departmentId in (select id from department where code like 'Auto_%'))
;
 
delete from mappedSubject
where mappedSubject in (select id from subject where code like 'Auto_%');

delete from mappedSubject
where mainSubject in (select id from subject where code like 'Auto_%');

delete from subject
where code like 'Auto_%' 
or departmentId in (select id from department where code like 'Auto_%')
;

/* Deleting from  program type */
delete from programType
where code like 'Auto_%';

delete from academicYear
where description like 'Auto_%' or academicYear.awardingInstituteId in (select id from awardingInstitute where code like 'Auto_%')
or academicYear.facultyId in (select id from faculty where code like 'Auto_%') AND Id <>0;

delete from documentType
where code like 'Auto_%';

/* Delete Classrooms */
delete from classroomFaculty 
where (classroomFaculty.classroomId in (select id from classroom where code like 'Auto_%') AND classroomFaculty.classroomId <>0);

delete from classroomFaculty 
where (classroomFaculty.facultyId in (select id from faculty where code like 'Auto_%') AND classroomFaculty.facultyId <>0);

delete from classroom
where code like 'Auto_%' or centerId in (Select id from center where code like 'Auto_%')	;

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
where code like 'Auto_%' or locationId in (select id from location where code like 'Auto_%' );

delete from location
where code like 'Auto_%';