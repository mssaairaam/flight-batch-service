# flight-batch-service
Creates and maintains Flight Batch details.

Setup
-----
1. Clone the repository.
2. Setup the following tables in Microsoft SQL Server.
  a. [FlightNotificationEngine].[dbo].[USER]
  b. [FlightNotificationEngine].[dbo].[T001_AWB_DTL]
3. Build the .war file by running the pom.xml.
4. Run the application and hit the URL : http:localhost:8083/flight-batch-service
5. Login with existing user name ("username1") and password ("password1")
