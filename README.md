# SLIIT Automated Regression Test Suite

Steps to run the test:
----------------------
1) Install mvn
2) Clone the project
3) Go to root of hte project directory
4) Open command prompt and execute: mvn test
5) Find the execution results in: target/surefire-reports/html/extent.html#!

PS: If report contents are not showing properly,
a) Type 'about:config' in address bar and search for property called 'security.csp.enable'.
b) Set above property ('security.csp.enable') value to false.
c) Refresh page.
