package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        Music music = new Music(2, "Song Name", "Artist Name", "2023-01-01", "03:30", "Pop");
        int userId = 2;
        Playlist playlist = new Playlist(music.getId(), music.getName(), music.getArtist(),
                music.getReleaseDate(), music.getTime(), music.getGenre(), userId);
        playlist.addToPlaylist();
        List<Song> songsInPlaylist = Playlist.getSongsInPlaylist(userId);
        for (Song song : songsInPlaylist) {
            System.out.println("Title: " + song.getName() + " | Artist: " + song.getArtist() + " | Genre: " + song.getGenre());
        }
    }
}

class DB {
    private static Connection connection = null;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection("jdbc:postgresql://localhost/music", "postgres", "E.Akylbai7");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}

class Music {
    private int id;
    private String name;
    private String artist;
    private String releaseDate;
    private String time;
    private String genre;

    public Music(int id, String name, String artist, String releaseDate, String time, String genre) {
        this.id = id;
        this.name = name;
        this.artist = artist;
        this.releaseDate = releaseDate;
        this.time = time;
        this.genre = genre;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getArtist() {
        return artist;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getTime() {
        return time;
    }

    public String getGenre() {
        return genre;
    }
}

class Playlist extends Music {
    private int userId;

    public Playlist(int id, String name, String artist, String releaseDate, String time, String genre, int userId) {
        super(id, name, artist, releaseDate, time, genre);
        this.userId = userId;
    }

    public void addToPlaylist() {
        try (Connection connection = DB.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO playlist (user_id, song_id) VALUES (?, ?)")) {
            statement.setInt(1, userId);
            statement.setInt(2, getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Song> getSongsInPlaylist(int userId) {
        List<Song> songs = new ArrayList<>();
        try (Connection connection = DB.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT a.id, a.name, a.artist, a.release_date, a.time, g.name AS genre_name " +
                             "FROM playlist p " +
                             "INNER JOIN allsongs a ON p.song_id = a.id " +
                             "INNER JOIN genres g ON a.genre = g.id " +
                             "WHERE p.user_id = ?")) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Song song = new Song(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("artist"),
                        resultSet.getString("release_date"),
                        resultSet.getString("time"),
                        resultSet.getString("genre_name"));
                songs.add(song);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return songs;
    }
}

class Song {
    private int id;
    private String name;
    private String artist;
    private String releaseDate;
    private String time;
    private String genre;

    public Song(int id, String name, String artist, String releaseDate, String time, String genre) {
        this.id = id;
        this.name = name;
        this.artist = artist;
        this.releaseDate = releaseDate;
        this.time = time;
        this.genre = genre;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getArtist() {
        return artist;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getTime() {
        return time;
    }

    public String getGenre() {
        return genre;
    }
}
