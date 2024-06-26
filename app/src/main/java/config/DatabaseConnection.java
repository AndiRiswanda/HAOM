package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import haom.PostInfo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DatabaseConnection {

    public static Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:haomuser.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public static ObservableList<PostInfo> loadPosts() {
        ObservableList<PostInfo> posts = FXCollections.observableArrayList();
        String sql = "SELECT * FROM posts";
    
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
    
            while (rs.next()) {
                PostInfo post = new PostInfo(
                        rs.getString("title"),
                        rs.getString("genre"),
                        rs.getString("description"),
                        rs.getString("imageURL"),
                        rs.getInt("helpPoints")
                );
                posts.add(post);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return posts;
    }
    
    
    public static void createNewTable() {
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS users (\n"
                + " id INTEGER PRIMARY KEY,\n"
                + " username TEXT NOT NULL,\n"
                + " email TEXT NOT NULL,\n"
                + " password TEXT NOT NULL,\n"
                + " haomicpoint INTEGER DEFAULT 0,\n"
                + " profile_image_path TEXT DEFAULT '/PicAsset/userProfile.png'\n"
                + ");";
    
        String postTable = "CREATE TABLE IF NOT EXISTS posts (\n"
                + " id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                + " title TEXT NOT NULL,\n"
                + " genre TEXT NOT NULL,\n"
                + " description TEXT NOT NULL,\n"
                + " imageURL TEXT NOT NULL,\n"
                + " helpPoints INTEGER DEFAULT 0\n"
                + ");";
        
        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
            stmt.execute(postTable);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static boolean validateLogin(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();

            // If result set has a result, login is valid
            return rs.next();
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }

        
    }

    public static void savePost(PostInfo post) {
        String sql = "INSERT INTO posts(title, genre, description, imageURL, helpPoints) VALUES(?, ?, ?, ?, ?)";
    
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, post.getTitle());
            pstmt.setString(2, post.getGenre());
            pstmt.setString(3, post.getDescription());
            pstmt.setString(4, post.getImageURL());
            pstmt.setInt(5, post.getHelpPoints());
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void incrementPostHelpPoints(PostInfo post) {
        String sql = "UPDATE posts SET helpPoints = helpPoints + 1 WHERE title = ? AND genre = ? AND description = ? AND imageURL = ?";
    
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, post.getTitle());
            pstmt.setString(2, post.getGenre());
            pstmt.setString(3, post.getDescription());
            pstmt.setString(4, post.getImageURL());
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
