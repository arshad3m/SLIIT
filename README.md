# SLIIT

Before running the test suite, please modify the excel data in the following manner.
(Because the excel available in GitHub is already exceuted and the data available in the excel are inserted in Staging db)
You can find the excel file here: src/test/resources/excel/testdata.xlsx

Steps to modify data:

1) In 'add_new_locations' sheet, change 'code' column values. Keep the prefix 'LOC'
2) In 'add_new_centers' sheet, change 'code' column values. Keep the prefix. 
3) In 'add_new_centers' sheet, the 'location' column values should be matching the Active location values in 'add_new_location' sheet.
4) In 'edit_centers' sheet, change 'new code' column values. keep the prefix. Changing the name is optional.
5) Similarly, change column values for 'add_new_department' and 'edit_department' sheets.

