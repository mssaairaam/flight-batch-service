# flight-batch-service
A Web application, which creates and maintains Flight Batch details.

Setup
-----
1. Clone the repository.
2. Setup the following tables in Microsoft SQL Server.

    a. <code> [FlightNotificationEngine].[dbo].[USER] </code>
  
    b. <code> [FlightNotificationEngine].[dbo].[T001_AWB_DTL] </code>
  
3. Build the <code>.war</code> file by running the pom.xml.
4. Run the application and hit the URL : <code>http:localhost:8083/flight-batch-service</code>
5. Login with existing user name ("username1") and password ("password1")
