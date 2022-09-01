package com.example.F1Standings2022.Standings.dao;

import com.example.F1Standings2022.Standings.entity.ConstructorTeam;
import com.example.F1Standings2022.Standings.entity.Driver;
import com.example.F1Standings2022.Standings.service.DriverService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.client.RestTemplate;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.valueOf;

public class ConstructorTeamRowMapper implements RowMapper<ConstructorTeam> {

    private static List<Driver> getDriversList()
    {
        final String driversURL = "http://ergast.com/api/f1/2022/11/driverStandings.json";

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(driversURL, String.class);

        JSONObject obj = new JSONObject(result);
        JSONArray driverStandings = obj.getJSONObject("MRData")
                .getJSONObject("StandingsTable")
                .getJSONArray("StandingsLists")
                .getJSONObject(0)
                .getJSONArray("DriverStandings");

        /*JSONObject max = driverStandings.getJSONObject(0); max is first object; current top driver as of 7/24
        String maxName = max.getJSONObject("Driver").get("driverId").toString();
        System.out.println(maxName);*/

        List<Driver> drivers = new ArrayList<>();

        for(int i=0; i<driverStandings.length(); i++) {

            String driverFirstName = driverStandings.getJSONObject(i).getJSONObject("Driver").get("givenName").toString();
            String driverLastName = driverStandings.getJSONObject(i).getJSONObject("Driver").get("familyName").toString();

            int driverNumber = valueOf(driverStandings.getJSONObject(i).getJSONObject("Driver").get("permanentNumber").toString());
            String driverName = driverFirstName + " " + driverLastName;
            String driverConstructor = driverStandings.getJSONObject(i).getJSONArray("Constructors").getJSONObject(0).get("name").toString();
            int driverPoints = valueOf(driverStandings.getJSONObject(i).get("points").toString());

            Driver curr = new Driver(driverNumber, driverName, driverConstructor, driverPoints);
            drivers.add(curr);
            //System.out.println(curr.toString() + "added");
        }
        //System.out.println(drivers);
        return drivers;
    }

    @Override
    public ConstructorTeam mapRow(ResultSet rs, int rowNumber) throws SQLException {

        List<Driver> driversList = getDriversList();

        String drivers = rs.getString("drivers");
        String[] driverNameArray = drivers.split("\\s+");

        List<String> driversNamesList = new ArrayList<>();

        List<Driver> teamDrivers = new ArrayList<>();

        int i = 0;
        while (i < driverNameArray.length) {
            StringBuffer currDriverName = new StringBuffer();
            currDriverName.append(driverNameArray[i] + " ");
            i++;
            currDriverName.append(driverNameArray[i]);
            i++;
            driversNamesList.add(currDriverName.toString());
        }

        for (String s : driversNamesList) {
            for (int idx=0; idx<driversList.size(); idx++) {
                if (driversList.get(idx).getName().equals(s)){
                    teamDrivers.add(driversList.get(idx));
                    break;
                }
            }
        }

        ConstructorTeam constructorTeam = new ConstructorTeam(rs.getString("name"), teamDrivers,
                rs.getInt("points"), rs.getInt("driver_points_difference"));

        return constructorTeam;
    }

}
