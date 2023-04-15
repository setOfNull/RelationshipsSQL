import java.sql.*;


public class Main {

    static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    static final String USER = "postgres";
    static final String PASS = "n550490162";
    static final String QUERY = "SELECT u.id, u.login, u.email, u.fullname, u.age, u.gender, a.id as acc_id, a.currency, a.acc_num " +
            "From users u join accounts a on u.id=a.id join users_accounts ua on u.id=ua.user_id";



    public static void main(String[] args) {


        try(Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
            Statement stmt = conn.createStatement();
        ) {
            ResultSet rs= stmt.executeQuery(QUERY);


            while(rs.next()){
                Account acc = new Account(rs.getLong(("id")), rs.getString(("acc_num")), rs.getBigDecimal(("currency")));

                User user = new User(rs.getInt(("id")),rs.getString(("login")), rs.getString(("email")), rs.getString(("fullname")),
                        rs.getInt(("age")), rs.getString(("gender")), acc);


                System.out.println(user);
                System.out.println();
            }

        }catch(SQLException e){
            e.printStackTrace();
        }

        }
    }

