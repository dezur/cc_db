package com.codecool.database;

import java.sql.*;

public class RadioCharts {

    private final String url;
    private final String user;
    private final String password;

    public RadioCharts(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public String getMostPlayedSong() {
        String SQL = "SELECT song FROM music_broadcast GROUP BY song ORDER BY SUM(times_aired) DESC, artist ASC";
        return createConnection(SQL);
    }

    public String getMostActiveArtist() {
        String SQL = "SELECT artist, COUNT(DISTINCT song) as songs FROM music_broadcast GROUP BY artist ORDER BY songs DESC";
        return createConnection(SQL);
    }

    private String createConnection(String query) {
        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement statement = conn.createStatement();
            ResultSet res = statement.executeQuery(query);
            res.next();
            return res.getString(1);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return "";
    }
}
