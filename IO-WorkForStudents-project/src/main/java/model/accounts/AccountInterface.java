package model.accounts;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import javax.sql.rowset.serial.SerialBlob;
import model.Interface;
import servlets.helper.Helper;

public class AccountInterface extends Interface {

    public ArrayList<String> checkLogin(String login, String password) {
        ArrayList<String> idAndType = new ArrayList<>();

        try {
            String query = "SELECT * FROM users WHERE login = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, login);
            ResultSet results = preparedStatement.executeQuery();

            if (results.next() && results.getString("password").equals(password)) {
                idAndType.add(results.getString("id_user"));
                idAndType.add(results.getString("type"));
            } else {
                idAndType.add("-1");
                idAndType.add("-1");
            }
        } catch (Exception e) {
            System.out.println(e);
            idAndType.add("-1");
            idAndType.add("-1");
        }
        try {
            checkIfDetailsExists(Helper.getIntValueOf(idAndType.get(0)));
        } catch (SQLException e) {
            System.out.println(e);
        }
        return idAndType;
    }

    public ArrayList<String> register(String login, String password, String email, String type) {
        ArrayList<String> idAndType = new ArrayList<>();

        try {
            String query = "SELECT * FROM users WHERE login = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, login);
            ResultSet results = preparedStatement.executeQuery();

            if (results.next()) {
                idAndType.add("-1");
                idAndType.add("-1");
                return idAndType;
            }

            String insertQuery = "INSERT INTO users (id_user, type, login, password, email) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
            String id = String.valueOf(getLastUserId() + 1);
            insertStatement.setString(1, id);
            insertStatement.setString(2, type);
            insertStatement.setString(3, login);
            insertStatement.setString(4, password);
            insertStatement.setString(5, email);

            insertStatement.executeUpdate();

            idAndType.add("id");
            idAndType.add("type");
            return idAndType;
        } catch (Exception e) {
            System.out.println(e);
            idAndType.add("-1");
            idAndType.add("type");
            return idAndType;
        }
    }

    public void checkIfDetailsExists(int userID) throws SQLException {
        System.out.println(userID);
        try {
            String query = "SELECT * FROM students_profile_details WHERE id_user = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userID);
            ResultSet results = preparedStatement.executeQuery();

            if (results.next()) {
                System.out.println("NOPE");
                return;
            }
        } catch (SQLException e) {
            System.out.println("Błąd podczas sprawdzania istnienia szczegółów użytkownika: " + e.getMessage());
        }
        createDetailsInDB(userID, this.getUserType(userID));
    }

    public void createDetailsInDB(int userID, String type) throws SQLException {
        try {
            if (type.equals("student")) {
                String detailID = String.valueOf(this.getLastDetailID("students_profile_details") + 1);
                String insertQuery2 = "INSERT INTO students_profile_details (id_students_profile_details, id_user) VALUES (?, ?)";
                PreparedStatement insertStatement2 = connection.prepareStatement(insertQuery2);

                insertStatement2.setString(1, detailID);
                insertStatement2.setInt(2, userID);
                insertStatement2.executeUpdate();
            } else if (type.equals("employer")) {
                String detailID = String.valueOf(this.getLastDetailID("employers_profile_details") + 1);
                String insertQuery2 = "INSERT INTO employers_profile_details (id_employers_profile_details, id_user) VALUES (?, ?)";
                PreparedStatement insertStatement2 = connection.prepareStatement(insertQuery2);
                insertStatement2.setString(1, detailID);
                insertStatement2.setInt(2, userID);
                insertStatement2.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    int getLastUserId() {
        try {
            String query = "SELECT MAX(id_user) AS max FROM USERS";
            ResultSet results = connection.createStatement().executeQuery(query);

            if (results.next()) {
                return results.getInt("max");
            }
            return 0;
        } catch (Exception e) {
            System.out.println(e);
            return -1;
        }
    }

    int getLastDetailID(String type) {
        try {
            String query = "SELECT MAX(id_" + type + ") AS max FROM " + type;
            ResultSet results = connection.createStatement().executeQuery(query);

            if (results.next()) {
                return results.getInt("max");
            }
            return 0;
        } catch (Exception e) {
            System.out.println(e);
            return -1;
        }
    }

    public int getUserCount() {
        try {
            String query = "SELECT COUNT(*) AS count FROM users";
            ResultSet results = connection.createStatement().executeQuery(query);

            if (results.next()) {
                return results.getInt("count");
            }
            return 0;
        } catch (Exception e) {
            System.out.println(e);
            return -1;
        }
    }

    public int getUserCount(String type) {
        try {
            String query = "SELECT COUNT(*) AS count FROM USERS WHERE type = '" + type + "'";
            ResultSet results = connection.createStatement().executeQuery(query);

            if (results.next()) {
                return results.getInt("count");
            }
            return 0;
        } catch (Exception e) {
            System.out.println(e);
            return -1;
        }
    }

    public String getLogin(int userID) {
        try {
            String query = "SELECT login FROM users WHERE id_user = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userID);
            ResultSet results = preparedStatement.executeQuery();
            if (results.next()) {
                String login = results.getString("login");
                return login;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return "No login assigned or no user";
    }

    public String getUserType(int userID) {
        try {
            String query = "SELECT type FROM users WHERE id_user = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userID);
            ResultSet results = preparedStatement.executeQuery();

            if (results.next()) {
                String type = results.getString("type");
                return type;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return "No type assigned or no user";
    }

    public String getName(int userID) throws SQLException {
        try {
            String query = "SELECT name FROM students_profile_details WHERE id_user = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userID);
            ResultSet results = preparedStatement.executeQuery();

            if (results.next()) {
                String name = results.getString("name");
                return name;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return "No name";
    }

    public String getSurname(int userID) {

        try {
            String query = "SELECT surname FROM students_profile_details WHERE id_user = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userID);
            ResultSet results = preparedStatement.executeQuery();

            if (results.next()) {
                String surname = results.getString("surname");
                return surname;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return "No surname";
    }

    public String getEmail(int userID) {

        try {
            String query = "SELECT email FROM users WHERE id_user = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userID);
            ResultSet results = preparedStatement.executeQuery();

            if (results.next()) {
                String email = results.getString("email");
                return email;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return "No email";
    }

    public String getTitle(int userID) {

        try {
            String query = "SELECT title FROM students_profile_details WHERE id_user = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userID);
            ResultSet results = preparedStatement.executeQuery();

            if (results.next()) {
                String title = results.getString("title");
                return title;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return "No title";
    }

    public String getDescription(int userID) {

        try {
            String query = "SELECT description FROM users WHERE id_user = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userID);
            ResultSet results = preparedStatement.executeQuery();

            if (results.next()) {
                String description = results.getString("description");
                return description;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return "No description";
    }

    public byte[] getProfilePicture(int userID) {
        byte[] blobData = null;
        try {
            String query = "SELECT picture FROM users WHERE id_user = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userID);
            ResultSet results = preparedStatement.executeQuery();

            if (results.next()) {
                Blob picture = results.getBlob("picture");
                if(picture != null)
                {
                    blobData = picture.getBytes(1, (int) picture.length());
                }
                else
                {
                    blobData=new byte[0];
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return blobData;
    }
    

    public String getCity(int userID) {
        try {
            String query = "SELECT city FROM users WHERE id_user = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userID);
            ResultSet results = preparedStatement.executeQuery();

            if (results.next()) {
                String city = results.getString("city");
                return city;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return "No city";
    }

    public String getCompanyName(int userID) {

        try {
            String query = "SELECT company_name FROM employers_profile_details WHERE id_user = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userID);
            ResultSet results = preparedStatement.executeQuery();

            if (results.next()) {
                String company_name = results.getString("company_name");
                return company_name;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return "No company_name";
    }

    public String getNIP(int userID) {

        try {
            String query = "SELECT NIP FROM employers_profile_details WHERE id_user = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userID);
            ResultSet results = preparedStatement.executeQuery();

            if (results.next()) {
                String NIP = results.getString("NIP");
                return NIP;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return "No NIP";
    }

    public void saveName(int userID, String name) {
        try {
            String update = "UPDATE students_profile_details SET name = ? WHERE id_user = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, userID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void saveSurname(int userID, String surname) {
        try {
            String update = "UPDATE students_profile_details SET surname = ? WHERE id_user = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setString(1, surname);
            preparedStatement.setInt(2, userID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void saveCity(int userID, String city) {
        System.out.println("DZIALA");
        try {
            String update = "UPDATE users SET city = ? WHERE id_user = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setString(1, city);
            preparedStatement.setInt(2, userID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void saveDescription(int userID, String description) {
        try {
            String update = "UPDATE users SET description = ? WHERE id_user = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setString(1, description);
            preparedStatement.setInt(2, userID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void saveTitle(int userID, String title) {
        try {
            String update = "UPDATE students_profile_details SET title = ? WHERE id_user = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setString(1, title);
            preparedStatement.setInt(2, userID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void savePicture(int userID, byte[] picture) {
        try {
            Blob pictureBlob = new SerialBlob(picture);
            String update = "UPDATE users SET picture = ? WHERE id_user = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setBlob(1, pictureBlob);
            preparedStatement.setInt(2, userID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void saveCompanyName(int userID, String companyName) {
        try {
            String update = "UPDATE employers_profile_details SET company_name = ? WHERE id_user = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setString(1, companyName);
            preparedStatement.setInt(2, userID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void saveNIP(int userID, String NIP) {
        try {
            System.out.println(NIP);
            String update = "UPDATE employers_profile_details SET NIP = ? WHERE id_user = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setString(1, NIP);
            preparedStatement.setInt(2, userID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
