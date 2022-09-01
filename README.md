# F1-Standings

Drivers table from ergast api - http://ergast.com/api/f1/2022/11/driverStandings.json
	- Name
	- Driver Number
	- Team
	- Points

Events table from ergast api - https://ergast.com/api/f1/2022/results/1.json
	- Name
	- Winner

Constructors table pulled from drivers table via the config - each driver is checked for their team which is added to the table, with the driver being added to the drivers list for the team in the table if the team has already been added.
	- Name
	- Drivers 
	- Points
	- Driver points performance differences
  
Config files for pulling from API, DAO layer for JDBC database interactions, Service layer interacting with the DAO layer, and Controller layer calls Service layer.

Each table is displayed in JSON data format + human readable text format.

