// Built-in packages that are imported
import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
import java.sql.*;


import rpack.PlaceholderTextField; // This is my own package that is imported

/*
 * This is First page of Application which allows the user to connect to database and enter the user name and password .
 * This class implements ActionListener for event Handling.
 */

class Login implements ActionListener {

    // Buttons :
    // submit for submit the entered user name and password
    // clear for clear the entered user name or Password
    // SignIn for create database , set user name and password
    // SignIn for reset user name and password
    // connect for connecting the application to database
    JButton submit, clear, SignIn, forgot, connect;

    JTextField text1; // Textfield that allows the user to enter the user name

    JPasswordField text2; // Passwordfield that allows the user to enter user Password

    JFrame frame; // frame in which every component is added

    String user; // Store the user name entered by the user



    // Database to Store the Database name ,
    // DBpassword to store Mysql Password ,
    // School_Name to store School name,
    // user name to store user name ,
    // user_password that is saved in database and
    // message for storing the text that shows whether the database is connected or
    // not .
    String Database, DBpassword, School_Name, user_name, user_password, message;

    // Label lab3 for the message which shown when the user entered the wrong user
    // name or password or did not connect to database.
    // and lab4 for the message which shown when the database is connected.
    JLabel lab3, lab4;

    // constructor
    Login(String database, String password, String school, String User_Name, String User_password, String Message) {

        Database = database;
        DBpassword = password;
        School_Name = school;
        user_name = User_Name;
        user_password = User_password;
        message = Message;

        // Create a frame
        frame = new JFrame();

        // create labels and set their properties
        JLabel label = new JLabel();
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setSize(1250, 100);
        label.setText("SCHOOL MANAGEMENT SYSTEM");
        label.setFont(new Font("Serif", Font.BOLD, 50));

        JLabel lab1 = new JLabel("User_Name ");
        lab1.setFont(new Font("Serif", Font.PLAIN, 25));
        lab1.setBounds(400, 250, 250, 30);

        JLabel lab2 = new JLabel("Password ");
        lab2.setFont(new Font("Serif", Font.PLAIN, 25));
        lab2.setBounds(400, 290, 250, 30);

        lab3 = new JLabel();
        lab3.setFont(new Font("Serif", Font.PLAIN, 25));
        lab3.setBounds(400, 485, 750, 30);

        lab4 = new JLabel();
        lab4.setFont(new Font("Serif", Font.PLAIN, 25));
        lab4.setBounds(1040, 180, 270, 30);
        lab4.setText(message);

        // create TextFields and set their properties
        text1 = new JTextField();
        text1.setFont(new Font("Serif", Font.PLAIN, 25));
        text1.setBounds(530, 255, 320, 30);

        text2 = new JPasswordField();
        text2.setFont(new Font("Serif", Font.PLAIN, 25));
        text2.setBounds(530, 295, 320, 30);

        // create button and set its properties
        submit = new JButton("Submit");
        submit.setBounds(400, 350, 200, 30);

        clear = new JButton("Clear");
        clear.setBounds(650, 350, 200, 30);

        SignIn = new JButton("Sign In");
        SignIn.setBounds(500, 400, 250, 30);

        forgot = new JButton("Forgot  Password");
        forgot.setBounds(500, 450, 250, 30);

        connect = new JButton("Connect To Database");
        connect.setBounds(1040, 120, 200, 40);

        // Here add label in frame
        frame.add(label);
        frame.add(lab1);
        frame.add(lab2);
        frame.add(lab3);
        frame.add(lab4);

        // here add textfield to frame

        frame.add(text1);
        frame.add(text2);

        // here add buttons to frame
        frame.add(submit);
        frame.add(clear);
        frame.add(SignIn);
        frame.add(forgot);
        frame.add(connect);

        // set the properties of frame
        // frame.setSize(500, 500); // optional
        frame.setLayout(null); // Manager Layout is null
        frame.getContentPane().setBackground(Color.YELLOW); // set the frame color yellow
        frame.setVisible(true); // set frame visible
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // set the properties of frame to open in its maximized state.

        // Register the buttons with event Listener
        submit.addActionListener(this);
        clear.addActionListener(this);
        SignIn.addActionListener(this);
        forgot.addActionListener(this);
        connect.addActionListener(this);

    }

    // All the logic of this class inside this method .
    @Override
    public void actionPerformed(ActionEvent e) {

        String Password = ""; // Store the password entered by the user

        // fetch the user name and password from database and then match with the user
        // input

        try {

            user = text1.getText();
            char[] pass = text2.getPassword();
            for (int i = 0; i < pass.length; i++) {
                Password += pass[i];
            }

            if (e.getSource() == submit) {
                if (Password.equals(user_password) && user.equals(user_name)) {

                    new First(Database, DBpassword, School_Name);
                    frame.dispose();
                }

                else {
                    lab3.setText("User Name or Password is Wrong or Database is not connected");

                }
            }
        } catch (Exception a) {
            lab3.setText("User Name or Password is Wrong or Database is not connected");
        }
        if (e.getSource() == clear) {
            text1.setText(null);
            text2.setText(null);
        }

        if (e.getSource() == SignIn) {

            new CreateDB();
            frame.dispose();
        }
        if (e.getSource() == connect) {
            new ConnectToDB();
            frame.dispose();
        }
        if (e.getSource() == forgot) {
            new ResetPassword();
            frame.dispose();
        }
    }
}

/*
 * This class provides an user interface to create a database.
 * This class implements ActionListener for event handling.
 */

class CreateDB implements ActionListener {

    // Buttons:
    // back for redirect the user to first page of Application.
    // create for creating the database.
    JButton back, create;

    // TextField :
    // text2 allows the user to enter the database name.
    // text3 allows the user to enter the Mysql password.
    JTextField text2, text3;

    // Label:
    // lab2 for message which shows when the user enter wrong Database.
    // lab3 for message which shows when the user enter wrong Password.
    JLabel lab2;

    JFrame frame; // frame in which all components are added.

    // password to store the Mysql password entered by the user.
    // database to store the database name entered by the user.
    String password, database;

    // constructor
    CreateDB() {
        // Create a frame
        frame = new JFrame();

        // create labels and set their properties
        JLabel label = new JLabel();
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setSize(1250, 100);
        label.setText("SCHOOL MANAGEMENT SYSTEM");
        label.setFont(new Font("Serif", Font.BOLD, 50));

        JLabel lab = new JLabel("CREATION  OF  DATABASE ");
        lab.setFont(new Font("Serif", Font.BOLD, 35));
        lab.setBounds(390, 150, 480, 40);

        JLabel label1 = new JLabel("Enter the Database Name you want to create ");
        label1.setFont(new Font("Serif", Font.PLAIN, 20));
        label1.setBounds(445, 240, 530, 40);

        JLabel label2 = new JLabel("NOTE : Database Name must contains only alphabets or Underscore");
        label2.setFont(new Font("Serif", Font.BOLD, 15));
        label2.setBounds(378, 290, 570, 15);

        JLabel lab1 = new JLabel("Enter the password that you have set during download Mysql ");
        lab1.setFont(new Font("Serif", Font.PLAIN, 20));
        lab1.setBounds(385, 390, 530, 40);

        lab2 = new JLabel();
        lab2.setFont(new Font("Serif", Font.PLAIN, 20));
        lab2.setBounds(400, 580, 540, 25);

        JLabel lab3 = new JLabel("NOTE : Remember this Database Name for further use ");
        lab3.setFont(new Font("Serif", Font.BOLD, 15));
        lab3.setBounds(420, 360, 540, 15);

        // create TextFields and set their properties

        text2 = new JTextField();
        text2.setFont(new Font("Serif", Font.PLAIN, 25));
        text2.setBounds(470, 325, 320, 30);

        text3 = new JTextField();
        text3.setFont(new Font("Serif", Font.PLAIN, 25));
        text3.setBounds(470, 470, 320, 30);

        // create button

        back = new JButton("Back");
        back.setBounds(30, 30, 100, 30);

        create = new JButton("Create");
        create.setBounds(580, 540, 100, 25);

        // Here add label in frame
        frame.add(label);
        frame.add(lab);
        frame.add(label1);
        frame.add(lab1);
        frame.add(lab2);
        frame.add(lab3);
        frame.add(label2);

        // here add textfield to frame
        frame.add(text2);
        frame.add(text3);

        // here add buttons to frame
        frame.add(create);

        frame.add(back);

        // set the properties of frame
        // frame.setSize(500, 500); // optional
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.YELLOW);
        frame.setVisible(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Register the buttons with event Listener

        back.addActionListener(this);
        create.addActionListener(this);

    }

    // All logic of this class is inside this method.
    @Override
    public void actionPerformed(ActionEvent e) {

        // save the data to database

        if (e.getSource() == back) {

            new Login(null, null, null, null, null, null);
            frame.dispose();
        }

        if (e.getSource() == create) {

            // First connection of Application to Mysql to create a database.
            try {
                String url = "jdbc:mysql://localhost:3306/";
                String user = "root";
                password = text3.getText();
                database = text2.getText();
                String sql = "create database " + database + ";";

                Connection connection = DriverManager.getConnection(url, user, password);
                Statement statement = connection.createStatement();
                statement.executeUpdate(sql);
                statement.close();
                connection.close();

                // Second connection to create connection of App to database to define the
                // tables in the database.
                String url1 = "jdbc:mysql://localhost:3306/" + database;
                String user1 = "root";

                Connection connection1 = DriverManager.getConnection(url1, user1, password);
                Statement statement1 = connection1.createStatement();

                // Student
                statement1.execute("create table Student_Registration(Student_Id varchar(20) not null ,Student_Name varchar(50) not null, Father_Name varchar(50) not null,Mother_Name varchar(50) not null ,Date_of_Birth Date not null , Gender varchar(10) not null,Mob1 bigint(10) not null, Mob2 bigint(10) not null,Registration_Date Date not null, Address varchar(100) not null,Student_Aadhaar_No bigint(12) not null ,Father_Aadhaar_No bigint(12) not null, Mother_Aadhaar_No bigint(12) not null,Family_ID varchar(20) not null ,Category varchar(10) not null,Father_Occupation varchar(50) ,Mother_Occupation varchar(50) ,School_Leaving_Date date,check(length(Student_Aadhaar_No)=12 and length(Father_Aadhaar_No)=12 and length(Mother_Aadhaar_No)=12),Primary key(Student_Id, Student_Name));");

               
                statement1.execute("create table Enrollment(Student_Id varchar(20) not null,Student_Name varchar(50) not null, Enrollment_No varchar(20) not null,Class varchar(5) not null); ");

                statement1.execute("alter table Enrollment add constraint EnId Primary key(Enrollment_No,Class);");

                statement1.execute("alter table Enrollment add constraint fk_en Foreign key(Student_Id,Student_Name) references Student_Registration(Student_Id,Student_Name) on update cascade;");

                statement1.execute("create table Academic(Serial_No int AUTO_INCREMENT,Student_Id varchar(20) not null, Student_Name varchar(50) not null ,class varchar(5) not null,Subjects varchar(500) not null,Session char(15) not null , Fee int(9) not null, Primary key(Serial_No));");
                
                
                
                statement1.execute("alter table Academic add constraint fk_ac Foreign key(Student_Id,Student_Name) references Student_Registration(Student_Id,Student_Name) on update cascade;");

                statement1.execute(
                        "create table Fee_Details(Serial_No int AUTO_INCREMENT,Student_Id varchar(20) not null, Student_Name varchar(50) not null, Class varchar(5) not null,Paid_Fee int(8) not null, Balance int(8) not null ,P_date date not null, Primary key(Serial_No)  );");

                statement1.execute(
                        "alter table  Fee_Details add constraint fk_fd Foreign key(Student_Id,Student_Name) references Student_Registration(Student_Id,Student_Name) on update cascade;");

                // Teacher
                statement1.execute(
                        "create table Teacher_Registration(Teacher_Id varchar(20) not null,Teacher_Name varchar(50) not null,Father_Name varchar(50) not null,Mother_Name varchar(50) not null , DOB date not null,Gender varchar(6) not null, Mob1 bigint(10) not null,Mob2 bigint(10) not null,Joining_Date date not null,Address varchar(100) not null, Teacher_Aadhaar_No bigint(12) not null,Family_Id varchar(20),Qualification varchar(50) not null , Experience varchar(50) not null,Account_No varchar(15),Job_Leaving_Date date,check(length(Teacher_Aadhaar_No)=12),Primary key(Teacher_Id , Teacher_Name) );");

               
                statement1.execute(
                        "create table Teacher_Salary_Structure(Serial_No int AUTO_INCREMENT,Teacher_Id varchar(20) not null, Teacher_Name varchar(50) not null, Salary int(9) not null,Month varchar(10) not null, Year int(5) not null,  Primary key(Serial_No)) ;");
              
                statement1.execute(
                        "alter table Teacher_Salary_Structure add constraint fk_ts1 Foreign key(Teacher_Id,Teacher_Name) references Teacher_Registration(Teacher_Id,Teacher_Name) on update cascade;");

                statement1.execute(
                        "create table Teacher_Salary_Details(Serial_No int AUTO_INCREMENT,Teacher_Id varchar(20) not null,Teacher_Name varchar(20) not null,Salary_Paid int(9) not null,Month varchar(10) not null , Year int(5) not null,Primary key(Serial_No));");
                
                statement1.execute(
                        "alter table Teacher_Salary_Details add constraint fk_ts2 Foreign key(Teacher_Id,Teacher_Name) references Teacher_Registration(Teacher_Id,Teacher_Name) on update cascade;");

                // Other Employee
                statement1.execute(
                        "create table Employee_Registration(Employee_Id varchar(20) not null,Employee_Name varchar(50) not null,Father_Name varchar(50) not null,Mother_Name varchar(50) not null,DOB date not null,Gender varchar(6) not null, Mob1 bigint(10) not null,Mob2 bigint(10) not null,Joining_Date date not null,Address varchar(100) not null, Employee_Aadhaar_No bigint(12) not null,Family_Id varchar(20),Qualification varchar(50) not null , Experience varchar(50) not null,Account_No varchar(15),Job_Leaving_Date date,check(length(Employee_Aadhaar_No)=12),Primary key(Employee_Id,Employee_Name));");

                statement1.execute(
                        "create table Employee_Salary_Structure(Serial_No int AUTO_INCREMENT,Employee_Id varchar(20) not null, Employee_Name varchar(50) not null, Salary int(9) not null,Month varchar(10) not null, Year int(5) not null,Primary key(Serial_No));");
          
                statement1.execute(
                        "alter table Employee_Salary_Structure add constraint fk_es1 Foreign key(Employee_Id,Employee_Name) references Employee_Registration(Employee_Id,Employee_Name) on update cascade;");

                statement1.execute(
                        "create table Employee_Salary_Details(Serial_No int AUTO_INCREMENT,Employee_Id varchar(20) not null,Employee_Name varchar(20) not null,Salary_Paid int(9) not null,Month varchar(10) not null , Year int(5) not null,Primary key(Serial_No) );");
                
                statement1.execute(
                        "alter table Employee_Salary_Details add constraint fk_es2 Foreign key(Employee_Id,Employee_Name) references Employee_Registration(Employee_Id,Employee_Name) on update cascade;");

                statement1.execute(
                        "create  table credentials(School_Name varchar(50) not null,User_Name varchar(50) not null,Password varchar(50) not null,SNo int AUTO_INCREMENT ,Primary key(SNo));");

                lab2.setText("Database created");

                new Forgot(database, password);
                frame.dispose();
                statement1.close();
                connection1.close();
            } catch (Exception a) {
                System.out.println(a);
                lab2.setText("Password is Wrong or Database is already existed");
            }

        }

    }
}

/*
 * This is third class of App which is responsible for set the password .
 * This class implemnts ActionListener for event handling.
 */
class Forgot implements ActionListener {
    // Buttons :
    // submit for submit the entered user name and password
    // clear for clear the entered user name or Password
    // back for redirect the user to first page of App
    JButton submit, clear, back;

    JTextField text1; // Textfield that allows the user enter the school name

    // TextField :
    // text2 allows the user to enter the User name
    // text3 allows the user to enter the password
    JTextField text2, text3;

    JLabel lab4; // Label for message which shows when the user name and password saved
                 // succesfully or not .

    public String database; // database to store the database name
    public String password1; // password1 to store the password of mysql.
    JFrame frame; // frame in which all the components are added

    // constructor
    Forgot(String DB, String password) {
        database = DB;
        password1 = password;

        // Create a frame
        frame = new JFrame();

        // create labels and set their properties
        JLabel label = new JLabel();
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setSize(1250, 100);
        label.setText("SCHOOL MANAGEMENT SYSTEM");
        label.setFont(new Font("Serif", Font.BOLD, 50));

        JLabel lab = new JLabel("Reset Password");
        lab.setFont(new Font("Serif", Font.PLAIN, 35));
        lab.setBounds(500, 150, 400, 40);

        JLabel lab1 = new JLabel("School Name :");
        lab1.setFont(new Font("Serif", Font.PLAIN, 25));
        lab1.setBounds(400, 250, 250, 30);

        JLabel lab2 = new JLabel("User Name : ");
        lab2.setFont(new Font("Serif", Font.PLAIN, 25));
        lab2.setBounds(400, 290, 250, 30);

        JLabel lab3 = new JLabel("Password :");
        lab3.setFont(new Font("Serif", Font.PLAIN, 25));
        lab3.setBounds(400, 330, 250, 30);

        lab4 = new JLabel();
        lab4.setFont(new Font("Serif", Font.PLAIN, 25));
        lab4.setBounds(430, 420, 550, 25);

        // create TextFields and set their properties
        text1 = new JTextField();
        text1.setFont(new Font("Serif", Font.PLAIN, 25));
        text1.setBounds(550, 255, 320, 30);

        text2 = new JTextField();
        text2.setFont(new Font("Serif", Font.PLAIN, 25));
        text2.setBounds(550, 295, 320, 30);

        text3 = new JTextField();
        text3.setFont(new Font("Serif", Font.PLAIN, 25));
        text3.setBounds(550, 335, 320, 30);

        // create buttons and set their properties
        submit = new JButton("Submit");
        submit.setBounds(400, 380, 200, 30);

        clear = new JButton("Clear");
        clear.setBounds(650, 380, 200, 30);

        back = new JButton("Back");
        back.setBounds(30, 30, 100, 30);

        // Here add labels to frame
        frame.add(label);
        frame.add(lab1);
        frame.add(lab2);
        frame.add(lab3);
        frame.add(lab);
        frame.add(lab4);

        // here add textfield to frame

        frame.add(text1);
        frame.add(text2);

        frame.add(text3);

        // here add buttons to frame
        frame.add(submit);
        frame.add(clear);
        frame.add(back);

        // set the properties of frame
        // frame.setSize(500, 500); // optional
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.YELLOW);
        frame.setVisible(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Register the buttons with event Listener
        submit.addActionListener(this);
        clear.addActionListener(this);
        back.addActionListener(this);

    }

    // All logic for this class is inside this method.
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == clear) {
            text1.setText(null);
            text2.setText(null);
            text3.setText(null);
        }
        if (e.getSource() == submit) {

            // connect app to database to insert School name,User name ,Password into
            // database.
            try {
                String url = "jdbc:mysql://localhost:3306/" + database;
                String user = "root";

                String User_Name = text2.getText();
                String Password = text3.getText();

                Connection connection = DriverManager.getConnection(url, user, password1);
                Statement statement = connection.createStatement();
                statement.execute("insert into credentials(School_Name,User_Name,Password) values(" + "\""
                        + text1.getText() + "\"," + "\"" + User_Name + "\"," + "\"" + Password + "\");");
                statement.close();
                connection.close();
                lab4.setText("Password Saved Successfully");

            } catch (Exception a) {
                lab4.setText("Enter valid User Name and Passsword");
            }

        }
        if (e.getSource() == back) {
            new Login(null, null, null, null, null, null);
            frame.dispose();
        }
    }

}

/*
 * This class provides an user interface to connect thier database to App.
 * This class implements ActionListener for event handling.
 */

class ConnectToDB implements ActionListener {

    JFrame frame; // frame in which all componnets are added

    // Buttons :
    // submit for submit the database name and mysql password .
    // back for redirect the user to first page of App.
    JButton submit, back;

    JLabel lab2; // label which shows message when user enter the wrong mysql password and
                 // database name

    JTextField text1; // textfield that allows the user to enter the database name.

    JPasswordField text2; // Passwordfield hat allows the user to enter the mysql password.

    String Database; // Database to store the database name entered by the user


    String School_Name; // Store the school name from the database
    String User_Name; // store the user name from the database.
    String User_Password;// store the user password from the database.
    String Message; // store the message to send it to login page that shows whether App is
                    // connected to database or not.

    // consructor
    ConnectToDB() {
 
        // Create a frame
        frame = new JFrame();

        // Create labels and set their properties

        JLabel label = new JLabel();
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setSize(1250, 100);
        label.setText("SCHOOL  MANAGEMENT  SYSTEM");
        label.setFont(new Font("Serif", Font.BOLD, 50));

        JLabel label1 = new JLabel("Enter the Database Name you have  created ");
        label1.setFont(new Font("Serif", Font.PLAIN, 20));
        label1.setBounds(445, 240, 530, 40);

        JLabel lab1 = new JLabel("Enter the password that you have set during download Mysql ");
        lab1.setFont(new Font("Serif", Font.PLAIN, 20));
        lab1.setBounds(385, 390, 530, 40);

        lab2 = new JLabel();
        lab2.setFont(new Font("Serif", Font.PLAIN, 20));
        lab2.setBounds(700, 540, 450, 25);

        // create TextFields and set their properties

        text1 = new JTextField();
        text1.setFont(new Font("Serif", Font.PLAIN, 25));
        text1.setBounds(470, 320, 320, 30);

        text2 = new JPasswordField();
        text2.setFont(new Font("Serif", Font.PLAIN, 25));
        text2.setBounds(470, 470, 320, 30);

        // create buttons and add to frame
        submit = new JButton("Submit");
        submit.setBounds(580, 540, 100, 25);
        frame.add(submit);

        back = new JButton("Back");
        back.setBounds(30, 30, 100, 30);
        frame.add(back);

        // add label and textfield to frame
        frame.add(label);
        frame.add(label1);
        frame.add(lab1);
        frame.add(lab2);

        frame.add(text1);
        frame.add(text2);
        // set the frame properties
        frame.setSize(500, 500);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.YELLOW);
        frame.setVisible(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Register the buttons with eventListener
        submit.addActionListener(this);
        back.addActionListener(this);

    }

    // All logics for this class is inside this method.
    @Override
    public void actionPerformed(ActionEvent e) {
        String Password = ""; // Password to store the mysql password enterd by the user.
        if (e.getSource() == submit) {

            try {

                // connection of App to database
                Database = text1.getText();

                char[] pass = text2.getPassword();
                for (int i = 0; i < pass.length; i++) {
                    Password += pass[i];
                }

                String url = "jdbc:mysql://localhost:3306/" + Database;
                String user = "root";

                Connection connection = DriverManager.getConnection(url, user, Password);
                Statement statement = connection.createStatement();

                ResultSet resultSet = statement.executeQuery(
                        "select School_Name,User_Name, Password from credentials order by SNo desc limit 1;");

                while (resultSet.next()) {
                    School_Name = resultSet.getString(1);
                    User_Name = resultSet.getString(2);
                    User_Password = resultSet.getString(3);

                }
                Message = "Database Connected";
                new Login(Database, Password, School_Name, User_Name, User_Password, Message);
                frame.dispose();

                statement.close();
                connection.close();

            } catch (Exception a) {
                lab2.setText("Database name or Password is wrong");

            }

        }
        if (e.getSource() == back) {
            new Login(null, null, null, null, null, null);
            frame.dispose();
        }
    }
}

/*
 * This class provides an user interface to reset thier password.
 * This class implements ActionListener for event handling.
*/

class ResetPassword implements ActionListener {

    JFrame frame; // frame in which all componnets are added

    // Buttons :
    // submit for submit the database name and mysql password .
    // back for redirect the user to first page of App.
    private JButton submit, back;

    JLabel lab2; // label which shows message when user enter the wrong mysql password and
                 // database name

    // TextField
    // text1 that allows the user to enter the database name.
    // text2 hat allows the user to enter the mysql password.
    // text3 that allows the user to enter new user name.
    // text4 that allows the user to enter new user password.
    JTextField text1, text2, text3, text4;

    String User_Name; // store the user name from the database.
    String User_Password;// store the user password from the database.

    // consructor
    ResetPassword() {

        // Create a frame
        frame = new JFrame();

        // Create labels and set their properties

        JLabel label = new JLabel();
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setSize(1250, 100);
        label.setText("SCHOOL  MANAGEMENT  SYSTEM");
        label.setFont(new Font("Serif", Font.BOLD, 50));

        JLabel label1 = new JLabel("Enter the Database Name you have  created ");
        label1.setFont(new Font("Serif", Font.PLAIN, 20));
        label1.setBounds(445, 140, 530, 40);

        JLabel lab1 = new JLabel("Enter the password that you have set during download Mysql ");
        lab1.setFont(new Font("Serif", Font.PLAIN, 20));
        lab1.setBounds(385, 250, 530, 40);

        JLabel lab3 = new JLabel("Enter New User Name ");
        lab3.setFont(new Font("Serif", Font.PLAIN, 20));
        lab3.setBounds(385, 390, 300, 40);

        JLabel lab4 = new JLabel("Enter New Password ");
        lab4.setFont(new Font("Serif", Font.PLAIN, 20));
        lab4.setBounds(385, 440, 350, 40);

        lab2 = new JLabel();
        lab2.setFont(new Font("Serif", Font.PLAIN, 20));
        lab2.setBounds(700, 540, 450, 25);

        // create TextFields and set their properties

        text1 = new JTextField();
        text1.setFont(new Font("Serif", Font.PLAIN, 25));
        text1.setBounds(470, 200, 320, 30);

        text2 = new JTextField();
        text2.setFont(new Font("Serif", Font.PLAIN, 25));
        text2.setBounds(470, 300, 320, 30);

        text3 = new JTextField();
        text3.setFont(new Font("Serif", Font.PLAIN, 25));
        text3.setBounds(620, 400, 300, 30);

        text4 = new JTextField();
        text4.setFont(new Font("Serif", Font.PLAIN, 25));
        text4.setBounds(620, 450, 300, 30);

        // create buttons and add to frame
        submit = new JButton("Submit");
        submit.setBounds(580, 540, 100, 25);
        frame.add(submit);

        back = new JButton("Back");
        back.setBounds(30, 30, 100, 30);
        frame.add(back);

        // add label and textfield to frame
        frame.add(label);
        frame.add(label1);
        frame.add(lab1);
        frame.add(lab2);
        frame.add(lab3);
        frame.add(lab4);

        frame.add(text1);
        frame.add(text2);
        frame.add(text3);
        frame.add(text4);

        // set the frame properties
        frame.setSize(500, 500); // optional
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.YELLOW);
        frame.setVisible(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Register the buttons with eventListener
        submit.addActionListener(this);
        back.addActionListener(this);

    }

    // All logics for this class is inside this method.
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {

            try {

                // connection of App to database to reset user name and password
                String Database = text1.getText();
                String Password = text2.getText();

                String url = "jdbc:mysql://localhost:3306/" + Database;
                String user = "root";

                Connection connection = DriverManager.getConnection(url, user, Password);
                Statement statement = connection.createStatement();

                ResultSet resultSet = statement.executeQuery("Select * from credentials order by SNo desc limit 1 ;");
                while (resultSet.next()) {

                    User_Name = resultSet.getString(2);
                    User_Password = resultSet.getString(3);

                }
                String query = "update credentials set User_Name = ? where User_Name = ? ;";
                PreparedStatement pstmt = connection.prepareStatement(query);
                pstmt.setString(1, text3.getText());
                pstmt.setString(2, User_Name);
                pstmt.executeUpdate();

                String query1 = "update credentials set Password = ? where Password = ? ;";
                PreparedStatement psmt1 = connection.prepareStatement(query1);
                psmt1.setString(1, text4.getText());
                psmt1.setString(2, User_Password);
                psmt1.executeUpdate();

                statement.close();
                connection.close();
                lab2.setText("User Name and Password Updated ");

            } catch (Exception a) {
                lab2.setText("Database name or Password is wrong");

            }

        }
        if (e.getSource() == back) {
            new Login(null, null, null, null, null, null);
            frame.dispose();
        }
    }
}

/*
 * This class provides an user interface which contains some buttons which
 * performs some event when they clicked.
 * This class implements ActionListener for event handling.
*/
class First implements ActionListener {

    JFrame frame; // frame in which all other components are added

    // Buttons:
    // button is responsible for opening the Student Registration Form,
    // button1 is responsible for opening the Teacher Registration Form,
    // buuton2 is responsible for opening the Other Employee Registration Form,
    // button3 is responsible for opening the Enrollment Details page,
    // button4 is responsible for opening the Academic Details page,
    // button5 is responsible for opening the Student's Fee Details page,
    // button6 is responsible for opening the page which contains three buttons that
    // shows all the student details when they clicked,
    // b7 is responsible for opening the Teacher Salary Structure page,
    // b8 is responsible for opening the Teacher Salary details page ,
    // b9 is responsible for opening the page which contains three buttons that all
    // the Teacher Details when they clicked,
    // b10 is responsible for opening the Other Employee Salary Structure page ,
    // b11 is responsible for opening the Other Employee Salary Details page,
    // b12 is responsibe for opening the page in which three buttons are available
    // that shows all Other employee details when they clicked,
    // update1 is responsible for opening the Update Student's Details page,
    // update2 is responsible for openening the Update Teacher's Details page,
    // update3 is reponsible for opening the Other's Employee's Details page,
    // back for redirect the user to first page
    private JButton button, button1, button2, button3, button4, button5, button6, b7, b8, b9, b10, b11, b12, update1,
            update2, update3, back;

    // Database to Store the database name
    // DBPassword to store the mysql password
    // School_Name to store the School name
    String Database, DBPassword, School_Name;

    // constructor
    First(String database, String Password, String School) {

        Database = database;
        DBPassword = Password;
        School_Name = School;

        // Create a frame
        frame = new JFrame();

        // Create labels and set their properties

        JLabel label = new JLabel();
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setSize(1250, 100);
        label.setText(School_Name);
        label.setFont(new Font("Serif", Font.BOLD, 50));

        // create buttons and add to frame
        button = new JButton("STUDENT  REGISTRATION");
        button.setBounds(160, 150, 270, 30);
        frame.add(button);

        button3 = new JButton("ENROLLMENT  DETAILS");
        button3.setBounds(160, 200, 270, 30);
        frame.add(button3);

        button4 = new JButton("ACADEMIC  DETAILS");
        button4.setBounds(160, 250, 270, 30);
        frame.add(button4);

        button5 = new JButton("STUDENT'S  FEE  DETAILS");
        button5.setBounds(160, 300, 270, 30);
        frame.add(button5);

        button6 = new JButton("VIEW  STUDENT'S  DETAILS");
        button6.setBounds(160, 350, 270, 30);
        frame.add(button6);

        update1 = new JButton("UPDATE  STUDENT'S  DETAILS");
        update1.setBounds(160, 400, 270, 30);
        frame.add(update1);

        button1 = new JButton("TEACHER  REGISTRATION");
        button1.setBounds(480, 150, 300, 30);
        frame.add(button1);

        b7 = new JButton("TEACHER'S  SALARY  STRUCTURE");
        b7.setBounds(480, 200, 300, 30);
        frame.add(b7);

        b8 = new JButton("TEACHER'S  SALARY  DETAILS");
        b8.setBounds(480, 250, 300, 30);
        frame.add(b8);

        b9 = new JButton("VIEW  TEACHER'S  DETAILS");
        b9.setBounds(480, 300, 300, 30);
        frame.add(b9);

        update2 = new JButton("UPDATE  TEACHER'S  DETAILS");
        update2.setBounds(480, 350, 300, 30);
        frame.add(update2);

        button2 = new JButton("OTHER  EMPLOYEES  REGISTRATION");
        button2.setBounds(830, 150, 300, 30);
        frame.add(button2);

        b10 = new JButton("EMPLOYEE'S  SALARY  STRUCTURE");
        b10.setBounds(830, 200, 300, 30);
        frame.add(b10);

        b11 = new JButton("EMPLOYEE'S  SALARY  DETAILS");
        b11.setBounds(830, 250, 300, 30);
        frame.add(b11);

        b12 = new JButton("VIEW  EMPLOYEE'S  DETAILS");
        b12.setBounds(830, 300, 300, 30);
        frame.add(b12);

        update3 = new JButton("UPDATE  EMPLOYEE'S  DETAILS");
        update3.setBounds(830, 350, 300, 30);
        frame.add(update3);

        back = new JButton("Back");
        back.setBounds(3, 5, 100, 30);
        frame.add(back);

        // Here add label in frame
        frame.add(label);

        // set the properties of frame
        frame.setSize(500, 500);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.YELLOW);
        frame.setVisible(true);

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Registraton of buttons with event listener
        button.addActionListener(this);
        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);
        button4.addActionListener(this);
        button5.addActionListener(this);
        button6.addActionListener(this);
        b7.addActionListener(this);
        b8.addActionListener(this);
        b9.addActionListener(this);
        b10.addActionListener(this);
        b11.addActionListener(this);
        b12.addActionListener(this);
        back.addActionListener(this);
        update1.addActionListener(this);
        update2.addActionListener(this);
        update3.addActionListener(this);

    }

    // All logic for this class are inside this method
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == button) {

            new Student(null, Database, DBPassword, School_Name);
            frame.dispose();
        }
        if (e.getSource() == button1) {

            new Teacher(null, Database, DBPassword, School_Name);
            frame.dispose();
        }
        if (e.getSource() == button2) {

            new OtherEmployee(null, Database, DBPassword, School_Name);
            frame.dispose();
        }
        if (e.getSource() == button3) {

            new Enrollment(null, Database, DBPassword, School_Name);
            frame.dispose();
        }
        if (e.getSource() == button4) {

            new Academic(null, Database, DBPassword, School_Name);
            frame.dispose();
        }
        if (e.getSource() == button5) {

            new Fee(null, Database, DBPassword, School_Name);
            frame.dispose();
        }
        if (e.getSource() == button6) {
            new StudentData(Database, DBPassword, School_Name);
            frame.dispose();
        }
        if (e.getSource() == b7) {

            new TeacherSalaryStructure(null, Database, DBPassword, School_Name);
            frame.dispose();
        }
        if (e.getSource() == b8) {

            new TeacherSalaryDetails(null, Database, DBPassword, School_Name);
            frame.dispose();
        }
        if (e.getSource() == b9) {

            new TeacherData(Database, DBPassword, School_Name);
            frame.dispose();
        }

        if (e.getSource() == b10) {

            new EmployeeSalaryStructure(null, Database, DBPassword, School_Name);
            frame.dispose();
        }
        if (e.getSource() == b11) {

            new EmployeeSalaryDetails(null, Database, DBPassword, School_Name);
            frame.dispose();
        }
        if (e.getSource() == b12) {
            new EmployeeData(Database, DBPassword, School_Name);
            frame.dispose();
        }

        if (e.getSource() == update1) {
            new StudentDataUpdate(Database, DBPassword, School_Name);
            frame.dispose();
        }

        if (e.getSource() == update2) {
            new TeacherDataUpdate(Database, DBPassword, School_Name);
            frame.dispose();
        }

        if (e.getSource() == update3) {
            new EmployeeDataUpdate(Database, DBPassword, School_Name);
            frame.dispose();
        }

        if (e.getSource() == back) {
            new Login(null, null, null, null, null, null);
            frame.dispose();
        }
    }
}

/*
 * This class provides an user interface that allow user to fill the Student
 * Registration form and insert into database.
 * This class extends PlaceholderTextField to fill the background of textfield
 * that shows how to enter date and which textfield is optional.
 * This class extends ActionListener for event handling.
 */
class Student extends PlaceholderTextField implements ActionListener {

    // Buttons :
    // sub to insert the Student basic details into the database,
    // clear for clear the information that are filled,
    // back for redirect the user to Home page of App
    JButton back, sub, clear;

    JFrame frame1; // frame in which all other components are added

    // TextField:
    // text1 allows the user to enter Student ID,
    // text2 allows the user to enter Student Name,
    // text3 allows the user to enter Student's Father Name,
    // text4 allows the user to enter Student's Mother Name,
    // text5 allows the user to enter Student's Date of Birth,
    // ch1 allows the user to select Student's Gender,
    // text7 allows the user to enter Student's Mobile Number1 ,
    // text8 allows the user to enter Student's Mobile Number2,
    // text9 allows the user to enter Student's Registration Date,
    // text10 allows the user to enter Student's Address,
    // text11 allows the user to enter Student's Aadhaar No,
    // text12 allows the user to enter Student's Father Aadhaar No,
    // text13 allows the user to enter Student's Mother Aadhaar No,
    // text14 allows the user to enter Student's Family ID,
    // ch2 allows the user to select Student's Category ,
    // text15 allows the user to enter Student's Father's Occupation,
    // text16 allows the user to enter Student's Mother's Ocupation,

    JTextField text1, text2, text3, text4, text5, text7, text8, text9, text10, text11, text12, text13, text14, text15,
            text16;

    final JComboBox<String> ch1; // JComboBox ch1 for selecting the Student's gender

    final JComboBox<String> ch2; // JComboBox ch2 for selecting the Student's Cast Category

    String[] gender = { "Male", "Female", "Other" }; // options for the JComboBox ch1

    String[] category = { "General", "OBC", "SC/ST" };// options for the JComboBox ch2

    // Database to Store the database name
    // DBPassword to store the mysql password
    // School_Name to store the School name
    // placeholder for background text of textfield

    String placeholder, Database, DBPassword, School_Name;

    // constructor
    Student(String placeholder, String database, String Password, String School) {
        super(placeholder);
        Database = database;
        DBPassword = Password;
        School_Name = School;

        // create second frame
        frame1 = new JFrame();

        // create labels and set their properties
        JLabel label1 = new JLabel();
        label1.setHorizontalAlignment(JLabel.CENTER);
        label1.setSize(1250, 100);
        label1.setText("STUDENT  REGISTRATION  FORM ");
        label1.setFont(new Font("Serif", Font.BOLD, 50));

        JLabel lab1 = new JLabel("Student ID :");
        lab1.setFont(new Font("Serif", Font.PLAIN, 25));
        lab1.setBounds(70, 160, 250, 30);

        JLabel lab2 = new JLabel("Student Name :");
        lab2.setFont(new Font("Serif", Font.PLAIN, 25));
        lab2.setBounds(70, 200, 250, 30);

        JLabel lab3 = new JLabel("Father Name :");
        lab3.setFont(new Font("Serif", Font.PLAIN, 25));
        lab3.setBounds(70, 240, 250, 30);

        JLabel lab4 = new JLabel("Mother Name :");
        lab4.setFont(new Font("Serif", Font.PLAIN, 25));
        lab4.setBounds(70, 280, 250, 30);

        JLabel lab5 = new JLabel("Date Of Birth :");
        lab5.setFont(new Font("Serif", Font.PLAIN, 25));
        lab5.setBounds(70, 320, 250, 30);

        JLabel lab6 = new JLabel("Gender :");
        lab6.setFont(new Font("Serif", Font.PLAIN, 25));
        lab6.setBounds(70, 360, 250, 30);

        JLabel lab7 = new JLabel("Mobile Number1 :");
        lab7.setFont(new Font("Serif", Font.PLAIN, 25));
        lab7.setBounds(70, 400, 250, 30);

        JLabel lab8 = new JLabel("Mobile Number2 :");
        lab8.setFont(new Font("Serif", Font.PLAIN, 25));
        lab8.setBounds(70, 440, 250, 30);

        JLabel lab9 = new JLabel("Registration Date :");
        lab9.setFont(new Font("Serif", Font.PLAIN, 25));
        lab9.setBounds(70, 480, 250, 30);

        JLabel lab10 = new JLabel("Address :");
        lab10.setFont(new Font("Serif", Font.PLAIN, 25));
        lab10.setBounds(70, 520, 250, 30);

        JLabel lab11 = new JLabel("Student's Aaadhar NO :");
        lab11.setFont(new Font("Serif", Font.PLAIN, 25));
        lab11.setBounds(700, 160, 250, 30);

        JLabel lab12 = new JLabel("Father's Aadhaar NO :");
        lab12.setFont(new Font("Serif", Font.PLAIN, 25));
        lab12.setBounds(700, 200, 250, 30);

        JLabel lab13 = new JLabel("Mother's Aadhaar NO :");
        lab13.setFont(new Font("Serif", Font.PLAIN, 25));
        lab13.setBounds(700, 240, 250, 30);

        JLabel lab14 = new JLabel("Family ID :");
        lab14.setFont(new Font("Serif", Font.PLAIN, 25));
        lab14.setBounds(700, 280, 250, 30);

        JLabel lab15 = new JLabel("Category :");
        lab15.setFont(new Font("Serif", Font.PLAIN, 25));
        lab15.setBounds(700, 320, 250, 30);

        JLabel lab16 = new JLabel("Father's Occupation:");
        lab16.setFont(new Font("Serif", Font.PLAIN, 25));
        lab16.setBounds(700, 360, 250, 30);

        JLabel lab17 = new JLabel("Mother's Occupation");
        lab17.setFont(new Font("Serif", Font.PLAIN, 25));
        lab17.setBounds(700, 400, 250, 30);

        // create TextFields and set their properties

        text1 = new JTextField();
        text1.setFont(new Font("Serif", Font.PLAIN, 25));
        text1.setBounds(300, 165, 300, 30);

        text2 = new JTextField();
        text2.setFont(new Font("Serif", Font.PLAIN, 25));
        text2.setBounds(300, 205, 300, 30);

        text3 = new JTextField();
        text3.setFont(new Font("Serif", Font.PLAIN, 25));
        text3.setBounds(300, 245, 300, 30);

        text4 = new JTextField();
        text4.setFont(new Font("Serif", Font.PLAIN, 25));
        text4.setBounds(300, 285, 300, 30);

        text5 = new PlaceholderTextField("YYYY-MM-DD");
        text5.setFont(new Font("Serif", Font.PLAIN, 25));
        text5.setBounds(300, 325, 300, 30);

        // ComboBox for gender

        ch1 = new JComboBox<>(gender);
        ch1.setBounds(300, 365, 300, 30);
        ch1.setSelectedIndex(-1);

        text7 = new JTextField();
        text7.setFont(new Font("Serif", Font.PLAIN, 25));
        text7.setBounds(300, 405, 300, 30);

        text8 = new JTextField();
        text8.setFont(new Font("Serif", Font.PLAIN, 25));
        text8.setBounds(300, 445, 300, 30);

        text9 = new PlaceholderTextField("YYYY-MM-DD");
        text9.setFont(new Font("Serif", Font.PLAIN, 25));
        text9.setBounds(300, 485, 300, 30);

        text10 = new JTextField();
        text10.setFont(new Font("Serif", Font.PLAIN, 25));
        text10.setBounds(300, 525, 300, 30);

        text11 = new JTextField();
        text11.setFont(new Font("Serif", Font.PLAIN, 25));
        text11.setBounds(950, 165, 300, 30);

        text12 = new JTextField();
        text12.setFont(new Font("Serif", Font.PLAIN, 25));
        text12.setBounds(950, 205, 300, 30);

        text13 = new JTextField();
        text13.setFont(new Font("Serif", Font.PLAIN, 25));
        text13.setBounds(950, 245, 300, 30);

        text14 = new PlaceholderTextField("Optional");
        text14.setFont(new Font("Serif", Font.PLAIN, 25));
        text14.setBounds(950, 285, 300, 30);

        // ComboBox for category

        ch2 = new JComboBox<>(category);
        ch2.setBounds(950, 325, 300, 30);
        ch2.setSelectedIndex(-1);

        text15 = new PlaceholderTextField("Optional");
        text15.setFont(new Font("Serif", Font.PLAIN, 25));
        text15.setBounds(950, 365, 300, 30);

        text16 = new PlaceholderTextField("Optional");
        text16.setFont(new Font("Serif", Font.PLAIN, 25));
        text16.setBounds(950, 405, 300, 30);

        // create buttons for submit ,clear and back

        sub = new JButton(" SUBMIT ");
        sub.setBounds(400, 600, 200, 30);

        clear = new JButton(" CLEAR ");
        clear.setBounds(650, 600, 200, 30);

        back = new JButton("Home");
        back.setBounds(30, 30, 100, 30);

        // Here add label in frame
        frame1.add(label1);
        frame1.add(lab1);
        frame1.add(lab2);
        frame1.add(lab3);
        frame1.add(lab4);
        frame1.add(lab5);
        frame1.add(lab6);
        frame1.add(lab7);
        frame1.add(lab8);
        frame1.add(lab9);
        frame1.add(lab10);
        frame1.add(lab11);
        frame1.add(lab12);
        frame1.add(lab13);
        frame1.add(lab14);
        frame1.add(lab15);
        frame1.add(lab16);
        frame1.add(lab17);

        // here add textfield and choice to frame

        frame1.add(text1);
        frame1.add(text2);
        frame1.add(text3);
        frame1.add(text4);
        frame1.add(text5);
        frame1.add(ch1);
        frame1.add(text7);
        frame1.add(text8);
        frame1.add(text9);
        frame1.add(text10);
        frame1.add(text11);
        frame1.add(text12);
        frame1.add(text13);
        frame1.add(text14);
        frame1.add(text15);
        frame1.add(text16);
        frame1.add(ch2);

        // here add button to frame
        frame1.add(sub);
        frame1.add(clear);
        frame1.add(back);

        // set the properties of frame
        frame1.setSize(1500, 1500); // optional
        frame1.setLayout(null);
        frame1.getContentPane().setBackground(Color.YELLOW);
        frame1.setVisible(true);
        frame1.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Register the buttons with EventListener
        back.addActionListener(this);
        sub.addActionListener(this);
        clear.addActionListener(this);
    }

    // all Logics for this class are inside this method
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back) {

            new First(Database, DBPassword, School_Name);
            frame1.dispose();
        }
        if (e.getSource() == clear) {
            text1.setText(null);
            text2.setText(null);
            text3.setText(null);
            text4.setText(null);
            text5.setText(null);
            text7.setText(null);
            text8.setText(null);
            text9.setText(null);
            text10.setText(null);
            text11.setText(null);
            text12.setText(null);
            text13.setText(null);
            text14.setText(null);

            ch1.setSelectedItem(gender[0]);
            ch2.setSelectedItem(category[0]);

        }
        if (e.getSource() == sub) {
            try {

                if (text14.getText().toString().equals(null)) {
                    text14.setText("-");

                }
                if (text15.getText().toString().equals(null)) {
                    text15.setText("-");
                }

                if (text16.getText().toString().equals(null)) {
                    text16.setText("-");
                }

                // Establish a connection to Mysql to insert Student data into database.
                String url = "jdbc:mysql://localhost:3306/" + Database;
                String user = "root";

                Connection connection = DriverManager.getConnection(url, user, DBPassword);
                Statement statement = connection.createStatement();

                statement.execute(
                        "insert into Student_Registration(Student_Id ,Student_Name , Father_Name ,Mother_Name ,Date_of_Birth,Gender  ,Mob1, Mob2 ,Registration_Date, Address,Student_Aadhaar_No ,Father_Aadhaar_No , Mother_Aadhaar_No ,Family_ID ,Category ,Father_Occupation  ,Mother_Occupation  ) values("
                                + text1.getText() + ", \"" + text2.getText() + "\", \"" + text3.getText() + "\", \""
                                + text4.getText() + "\" , \'" + text5.getText() + "\', \"" + ch1.getSelectedItem()
                                + "\", " + text7.getText() + "," + text8.getText() + ",\'" + text9.getText() + "\',\""
                                + text10.getText() + "\" , " + text11.getText() + ", " + text12.getText() + ", "
                                + text13.getText() + ", \"" + text14.getText() + "\", \"" + ch2.getSelectedItem()
                                + "\", \"" + text15.getText() + "\", \"" + text16.getText() + "\");");
                statement.close();
                connection.close();

                JOptionPane.showMessageDialog(this, "Saved Into Database Successfully."); // showing the message whether
                                                                                          // the Student
                // Details insert into the database or not .

            } catch (Exception a) {
                System.out.println(a);
                JOptionPane.showMessageDialog(this, "Something is missing or Incorrect enteries");

            }
        }
    }

}

/*
 * This class provides an user interface that allows user to insert Student's
 * enrollment details into the database.
 * This class extends PlaceholderTextField to fill the background of textfield
 * that shows how to enter date and which textfield is optional.
 * This class extends ActionListener for event handling.
 */
class Enrollment extends PlaceholderTextField implements ActionListener {
    // Buttons :
    // sub to insert the Student Enrollment details into the database,
    // clear for clear the information that are filled,
    // back for redirect the user to Home page of App

    JButton back, sub, clear;

    // Textfields:
    // text1 allows user to enter student id ,
    // text2 allows user to enter Student Name,
    // text3 allows user to enter Student Enrolllment No,
    // text4 allows user to enter Student Class.
    JTextField text1, text2, text3, text4;

    // Database to Store the database name
    // DBPassword to store the mysql password
    // School_Name to store the School name
    // placeholder for background text of textfield
    String placeholder, Database, DBPassword, School_Name;

    JFrame frame; // frame in which all other components are added

    // constructor
    Enrollment(String placeholder, String database, String Password, String School) {
        super(placeholder);
        Database = database;
        DBPassword = Password;
        School_Name = School;

        // create a frame
        frame = new JFrame();

        // create labels and set their properties
        JLabel label = new JLabel();
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setSize(1250, 100);
        label.setText("ENROLLMENT DETAILS");
        label.setFont(new Font("Serif", Font.BOLD, 50));

        JLabel lab1 = new JLabel("Student ID :");
        lab1.setFont(new Font("Serif", Font.PLAIN, 25));
        lab1.setBounds(150, 160, 250, 30);

        JLabel lab2 = new JLabel("Student Name :");
        lab2.setFont(new Font("Serif", Font.PLAIN, 25));
        lab2.setBounds(150, 200, 250, 30);

        JLabel lab3 = new JLabel("Enrollment NO :");
        lab3.setFont(new Font("Serif", Font.PLAIN, 25));
        lab3.setBounds(150, 240, 250, 30);

        JLabel lab4 = new JLabel("Class :");
        lab4.setFont(new Font("Serif", Font.PLAIN, 25));
        lab4.setBounds(150, 280, 250, 30);

        // Here add labels in frame
        frame.add(label);

        frame.add(lab1);
        frame.add(lab2);
        frame.add(lab3);
        frame.add(lab4);

        // create TextFields and set their properties

        text1 = new JTextField();
        text1.setFont(new Font("Serif", Font.PLAIN, 25));
        text1.setBounds(450, 165, 300, 30);

        text2 = new JTextField();
        text2.setFont(new Font("Serif", Font.PLAIN, 25));
        text2.setBounds(450, 205, 300, 30);

        text3 = new JTextField();
        text3.setFont(new Font("Serif", Font.PLAIN, 25));
        text3.setBounds(450, 245, 300, 30);

        text4 = new PlaceholderTextField("eg : 5th");
        text4.setFont(new Font("Serif", Font.PLAIN, 25));
        text4.setBounds(450, 285, 300, 30);

        // create Buttons and set their properties
        sub = new JButton(" SUBMIT ");
        sub.setBounds(400, 600, 200, 30);

        clear = new JButton(" CLEAR ");
        clear.setBounds(650, 600, 200, 30);

        back = new JButton("Home");
        back.setBounds(30, 30, 100, 30);

        // here add textfields to frame
        frame.add(text1);
        frame.add(text2);
        frame.add(text3);
        frame.add(text4);

        // here add buttons to frame
        frame.add(sub);
        frame.add(clear);
        frame.add(back);

        // set the properties of frame
        frame.setSize(500, 500);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.YELLOW);
        frame.setVisible(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Register the buttons with the event Listener
        back.addActionListener(this);
        sub.addActionListener(this);
        clear.addActionListener(this);

    }

    // All logics for this class are inside this method.
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back) {

            new First(Database, DBPassword, School_Name);
            frame.dispose();
        }
        if (e.getSource() == clear) {
            text1.setText(null);
            text2.setText(null);
            text3.setText(null);
            text4.setText(null);

        }
        if (e.getSource() == sub) {
            try {
                // connect app to mysql to insert Enrollment details into database.
                String url = "jdbc:mysql://localhost:3306/" + Database;
                String user = "root";

                Connection connection = DriverManager.getConnection(url, user, DBPassword);
                Statement statement = connection.createStatement();
                statement.execute("insert into Enrollment(Student_Id,Student_Name,Enrollment_No,Class) values(" + text1.getText() + ", \"" + text2.getText()
                        + "\", \"" + text3.getText() + "\", \"" + text4.getText() + "\");");
                statement.close();
                connection.close();
                JOptionPane.showMessageDialog(this, "Saved Into Database Successfully."); // showing the message whether
                                                                                          // the Student
                // Details insert into the database or not .

            } catch (Exception a) {
                JOptionPane.showMessageDialog(this, "Something is missing or Incorrect enteries");
            }
        }
    }
}

/*
 * This class provides an user interface that allows user to enter Student's
 * Academic details and insert into the database.
 * This class extends PlaceholderTextField to fill the background of textfield
 * that shows how to enter date and the which textfield is optional.
 * This class extends ActionListener for event handling.
 */
class Academic extends PlaceholderTextField implements ActionListener {

    // Buttons :
    // sub to insert the Student Academic details into the database,
    // clear for clear the information that are filled,
    // back for redirect the user to Home page of App
    JButton back, sub, clear;

    // Textfields:
    // text1 allows user to enter student id ,
    // text2 allows user to enter Student Name,
    // text3 allows user to enter Student Class,
    // text4 allows user to enter Student Subject,
    // text5 allows user to enter Student Session,
    // text6 allows user to enter Student Fee.
    JTextField text1, text2, text5, text3, text4, text6;

    JFrame frame; // frame in which all other components are added .

    // Database to Store the database name
    // DBPassword to store the mysql password
    // School_Name to store the School name
    // placeholder for background text of textfield

    String placeholder, Database, DBPassword, School_Name;

    // constructor
    Academic(String placeholder, String database, String Password, String School) {

        super(placeholder);
        Database = database;
        DBPassword = Password;
        School_Name = School;

        // create a frame

        frame = new JFrame();

        // create labels and set their properties
        JLabel label = new JLabel();
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setSize(1250, 100);
        label.setText("ACADEMIC DETAILS");
        label.setFont(new Font("Serif", Font.BOLD, 50));

        JLabel lab1 = new JLabel("Student ID :");
        lab1.setFont(new Font("Serif", Font.PLAIN, 25));
        lab1.setBounds(150, 160, 250, 30);

        JLabel lab2 = new JLabel("Student Name :");
        lab2.setFont(new Font("Serif", Font.PLAIN, 25));
        lab2.setBounds(150, 200, 250, 30);

        JLabel lab3 = new JLabel("Class :");
        lab3.setFont(new Font("Serif", Font.PLAIN, 25));
        lab3.setBounds(150, 240, 250, 30);

        JLabel lab4 = new JLabel("Subject :");
        lab4.setFont(new Font("Serif", Font.PLAIN, 25));
        lab4.setBounds(150, 280, 250, 30);

        JLabel lab5 = new JLabel("Session :");
        lab5.setFont(new Font("Serif", Font.PLAIN, 25));
        lab5.setBounds(150, 320, 250, 30);

        JLabel lab6 = new JLabel("Fee :");
        lab6.setFont(new Font("Serif", Font.PLAIN, 25));
        lab6.setBounds(150, 360, 250, 30);

        // Here add label in frame
        frame.add(label);

        frame.add(lab1);
        frame.add(lab2);
        frame.add(lab3);
        frame.add(lab4);
        frame.add(lab5);
        frame.add(lab6);

        // create TextFields and set their properties

        text1 = new JTextField();
        text1.setFont(new Font("Serif", Font.PLAIN, 25));
        text1.setBounds(450, 165, 300, 30);

        text2 = new JTextField();
        text2.setFont(new Font("Serif", Font.PLAIN, 25));
        text2.setBounds(450, 205, 300, 30);

        text3 = new PlaceholderTextField("eg:5th");
        text3.setFont(new Font("Serif", Font.PLAIN, 25));
        text3.setBounds(450, 245, 300, 30);

        text4 = new PlaceholderTextField("eg : Math,English,Science");
        text4.setFont(new Font("Serif", Font.PLAIN, 25));
        text4.setBounds(450, 285, 300, 30);

        text5 = new JTextField();
        text5.setFont(new Font("Serif", Font.PLAIN, 25));
        text5.setBounds(450, 325, 300, 30);

        text6 = new JTextField();
        text6.setFont(new Font("Serif", Font.PLAIN, 25));
        text6.setBounds(450, 365, 300, 30);

        // add submit,clear and back buttons

        sub = new JButton(" SUBMIT ");
        sub.setBounds(400, 600, 200, 30);

        clear = new JButton(" CLEAR ");
        clear.setBounds(650, 600, 200, 30);

        back = new JButton("Home");
        back.setBounds(30, 30, 100, 30);

        // here add textfields to frame
        frame.add(text1);
        frame.add(text2);
        frame.add(text3);
        frame.add(text4);
        frame.add(text5);
        frame.add(text6);

        // here add buttons to frame
        frame.add(sub);
        frame.add(clear);
        frame.add(back);

        // set the properties of frame
        frame.setSize(500, 500);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.YELLOW);
        frame.setVisible(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Register buttons with event Listener
        back.addActionListener(this);
        sub.addActionListener(this);
        clear.addActionListener(this);

    }

    // All Logics for this class are inside this method.
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back) {

            new First(Database, DBPassword, School_Name);
            frame.dispose();
        }
        if (e.getSource() == clear) {
            text1.setText(null);
            text2.setText(null);
            text3.setText(null);
            text4.setText(null);
            text5.setText(null);
            text6.setText(null);

        }
        if (e.getSource() == sub) {
            try {
                // connect app to mysql to insert Student Academic details into the database.
                String url = "jdbc:mysql://localhost:3306/" + Database;
                String user = "root";

                Connection connection = DriverManager.getConnection(url, user, DBPassword);
                Statement statement = connection.createStatement();
                statement.execute("insert into Academic(Student_Id,Student_Name,class,Subjects,Session,Fee) values(" + text1.getText() + ", \"" + text2.getText() + "\", \""
                        + text3.getText() + "\", \"" + text4.getText() + "\" , \'" + text5.getText() + "\', "
                        + text6.getText() + ");");
                statement.close();
                connection.close();
                JOptionPane.showMessageDialog(this, "Saved Into Database Successfully."); // showing the message whether
                                                                                          // the Student
                // Details insert into the database or not .

            } catch (Exception a) {
                JOptionPane.showMessageDialog(this, "Something is missing or Incorrect enteries");
            }
        }
    }
}

/*
 * This class provides an user interface that allows the user to enter Student
 * Fee datails and insert into database.
 * This class extends PlaceholderTextField to fill the background of textfield
 * that shows how to enter date and which textfield is optional.
 * This class extends ActionListener for event handling.
 */

class Fee extends PlaceholderTextField implements ActionListener {

    JFrame frame; // frame in which all other components are added .

    // Buttons :
    // sub to insert the Student Fee details into the database,
    // clear for clear the information that are filled,
    // back for redirect the user to Home page of App
    JButton back, clear, sub;

    // Textfields:
    // text1 allows user to enter student id ,
    // text2 allows user to enter Student Name,
    // text3 allows user to enter Student Class,
    // text4 allows user to enter Student Paid Fee,
    // text5 allows user to enter Student Balance,
    // text6 allows user to enter Student Date.
    JTextField text1, text2, text3, text4, text5, text6;

    // Database to Store the database name
    // DBPassword to store the mysql password
    // School_Name to store the School name
    // placeholder for background text of textfield
    String placeholder, Database, DBPassword, School_Name;

    // constructor
    Fee(String placeholder, String database, String Password, String School) {
        super(placeholder);
        Database = database;
        DBPassword = Password;
        School_Name = School;

        // create a Frame
        frame = new JFrame();

        // create labels and set their properties
        JLabel label = new JLabel();
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setSize(1250, 100);
        label.setText("FEE DETAILS");
        label.setFont(new Font("Serif", Font.BOLD, 50));

        JLabel lab1 = new JLabel("Student ID :");
        lab1.setFont(new Font("Serif", Font.PLAIN, 25));
        lab1.setBounds(150, 160, 250, 30);

        JLabel lab2 = new JLabel("Student Name :");
        lab2.setFont(new Font("Serif", Font.PLAIN, 25));
        lab2.setBounds(150, 200, 250, 30);

        JLabel lab3 = new JLabel("Class:");
        lab3.setFont(new Font("Serif", Font.PLAIN, 25));
        lab3.setBounds(150, 240, 250, 30);

        JLabel lab4 = new JLabel("Paid Fee :");
        lab4.setFont(new Font("Serif", Font.PLAIN, 25));
        lab4.setBounds(150, 280, 250, 30);

        JLabel lab5 = new JLabel("Balance :");
        lab5.setFont(new Font("Serif", Font.PLAIN, 25));
        lab5.setBounds(150, 320, 250, 30);

        JLabel lab6 = new JLabel("Date");
        lab6.setFont(new Font("Serif", Font.PLAIN, 25));
        lab6.setBounds(150, 360, 250, 30);

        // Here add label in frame
        frame.add(label);

        frame.add(lab1);
        frame.add(lab2);
        frame.add(lab3);
        frame.add(lab4);
        frame.add(lab5);
        frame.add(lab6);

        // create TextFields and set their properties

        text1 = new JTextField();
        text1.setFont(new Font("Serif", Font.PLAIN, 25));
        text1.setBounds(450, 165, 300, 30);

        text2 = new JTextField();
        text2.setFont(new Font("Serif", Font.PLAIN, 25));
        text2.setBounds(450, 205, 300, 30);

        text3 = new PlaceholderTextField("eg : 5th");
        text3.setFont(new Font("Serif", Font.PLAIN, 25));
        text3.setBounds(450, 245, 300, 30);

        text4 = new JTextField();
        text4.setFont(new Font("Serif", Font.PLAIN, 25));
        text4.setBounds(450, 285, 300, 30);

        text5 = new JTextField();
        text5.setFont(new Font("Serif", Font.PLAIN, 25));
        text5.setBounds(450, 325, 300, 30);

        text6 = new PlaceholderTextField("YYYY-MM-DD");
        text6.setFont(new Font("Serif", Font.PLAIN, 25));
        text6.setBounds(450, 365, 300, 30);

        // create button and set its properties
        sub = new JButton(" SUBMIT ");
        sub.setBounds(400, 600, 200, 30);

        clear = new JButton(" CLEAR ");
        clear.setBounds(650, 600, 200, 30);

        back = new JButton("Home");
        back.setBounds(30, 30, 100, 30);

        // here add textfields to frame
        frame.add(text1);
        frame.add(text2);
        frame.add(text3);
        frame.add(text4);
        frame.add(text5);
        frame.add(text6);

        // here add buttons to frame
        frame.add(sub);
        frame.add(clear);
        frame.add(back);

        // set the properties of frame
        frame.setSize(500, 500);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.YELLOW);
        frame.setVisible(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Register the buttons with event Listener
        back.addActionListener(this);
        clear.addActionListener(this);
        sub.addActionListener(this);
    }

    // All the logics for this class are inside this method.
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back) {

            new First(Database, DBPassword, School_Name);
            frame.dispose();
        }

        if (e.getSource() == clear) {
            text1.setText(null);
            text2.setText(null);
            text3.setText(null);
            text4.setText(null);
            text5.setText(null);
            text6.setText(null);

        }
        if (e.getSource() == sub) {
            try {

                // connect app to mysql to insert Student Fee data into the database .
                String url = "jdbc:mysql://localhost:3306/" + Database;
                String user = "root";

                Connection connection = DriverManager.getConnection(url, user, DBPassword);
                Statement statement = connection.createStatement();
                statement.execute(
                        "insert into Fee_Details(Student_Id,Student_Name,Class,Paid_Fee,Balance,P_date) values("
                                + text1.getText() + ", \"" + text2.getText() + "\", \"" + text3.getText() + "\", "
                                + text4.getText() + " , " + text5.getText() + ", \'" + text6.getText() + "\' );");
                statement.close();
                connection.close();
                JOptionPane.showMessageDialog(this, "Saved Into Database Successfully."); // showing the message whether
                                                                                          // the Student
                // Details insert into the database or not .

            } catch (Exception a) {
                JOptionPane.showMessageDialog(this, "Something is missing or Incorrect enteries");
            }
        }
    }
}

/*
 * This class provides an user interface that allows the user to fill the
 * Teacher Registration and insert it into the database.
 * This class extends PlaceholderTextField to fill the background of textfield
 * that shows how to enter date and which textfield is optional.
 * This class extends ActionListener for event handling.
 */
class Teacher extends PlaceholderTextField implements ActionListener {

    // Buttons :
    // sub to insert the Teacher Basic details into the database,
    // clear for clear the information that are filled,
    // back for redirect the user to Home page of App
    JButton back, sub, clear;

    // TextField:
    // text1 allows the user to enter Teacher ID,
    // text2 allows the user to enter Teacher Name,
    // text3 allows the user to enter Teacher's Father Name,
    // text4 allows the user to enter Teacher's Mother Name,
    // text5 allows the user to enter Teacher's Date of Birth,
    // text7 allows the user to enter Teacher's Mobile Number1 ,
    // text8 allows the user to enter Teacher's Mobile Number2,
    // text9 allows the user to enter Teacher's Joining Date,
    // text10 allows the user to enter Teacher's Address,
    // text11 allows the user to enter Teacher's Aadhaar No,
    // text12 allows the user to enter Teacher's Family_ID,
    // text13 allows the user to enter Teacher's Qualification,
    // text14 allows the user to enter Teacher's Experience,
    // text15 allows the user to enter Teacher's Account No,
    JTextField text1, text2, text3, text4, text5, text7, text8, text9, text10, text11, text12, text13, text14, text15;

    // JComboBox ch1 allows the user to select Teacher's Gender,.
    JComboBox<String> ch1;

    String gender[] = { "Male", "Female", "Other" }; // Options for selecting gender

    JFrame frame2; // frame in which all other components are added.

    // Database to Store the database name
    // DBPassword to store the mysql password
    // School_Name to store the School name
    // placeholder for background text of textfield
    String placeholder, Database, DBPassword, School_Name;

    // constructor
    Teacher(String placeholder, String database, String Password, String School) {
        super(placeholder);
        Database = database;
        DBPassword = Password;
        School_Name = School;

        // create a frame
        frame2 = new JFrame();

        // create label and set its properties
        JLabel label2 = new JLabel();
        label2.setHorizontalAlignment(JLabel.CENTER);
        label2.setSize(1250, 100);
        label2.setText("TEACHER  REGISTRATION  FORM");
        label2.setFont(new Font("Serif", Font.BOLD, 50));

        JLabel lab1 = new JLabel("Teacher ID :");
        lab1.setFont(new Font("Serif", Font.PLAIN, 25));
        lab1.setBounds(70, 160, 250, 30);

        JLabel lab2 = new JLabel("Teacher Name :");
        lab2.setFont(new Font("Serif", Font.PLAIN, 25));
        lab2.setBounds(70, 200, 250, 30);

        JLabel lab3 = new JLabel("Father Name :");
        lab3.setFont(new Font("Serif", Font.PLAIN, 25));
        lab3.setBounds(70, 240, 250, 30);

        JLabel lab4 = new JLabel("Mother Name :");
        lab4.setFont(new Font("Serif", Font.PLAIN, 25));
        lab4.setBounds(70, 280, 250, 30);

        JLabel lab5 = new JLabel("Date Of Birth :");
        lab5.setFont(new Font("Serif", Font.PLAIN, 25));
        lab5.setBounds(70, 320, 250, 30);

        JLabel lab6 = new JLabel("Gender :");
        lab6.setFont(new Font("Serif", Font.PLAIN, 25));
        lab6.setBounds(70, 360, 250, 30);

        JLabel lab7 = new JLabel("Mobile_Number1 :");
        lab7.setFont(new Font("Serif", Font.PLAIN, 25));
        lab7.setBounds(70, 400, 250, 30);

        JLabel lab8 = new JLabel("Mobile Number2 :");
        lab8.setFont(new Font("Serif", Font.PLAIN, 25));
        lab8.setBounds(70, 440, 250, 30);

        JLabel lab9 = new JLabel("Joining Date :");
        lab9.setFont(new Font("Serif", Font.PLAIN, 25));
        lab9.setBounds(70, 480, 250, 30);

        JLabel lab10 = new JLabel("Address :");
        lab10.setFont(new Font("Serif", Font.PLAIN, 25));
        lab10.setBounds(70, 520, 250, 30);

        JLabel lab11 = new JLabel("Teacher's Aaadhar NO :");
        lab11.setFont(new Font("Serif", Font.PLAIN, 25));
        lab11.setBounds(700, 160, 250, 30);

        JLabel lab12 = new JLabel("Family ID :");
        lab12.setFont(new Font("Serif", Font.PLAIN, 25));
        lab12.setBounds(700, 200, 250, 30);

        JLabel lab13 = new JLabel("Qualification :");
        lab13.setFont(new Font("Serif", Font.PLAIN, 25));
        lab13.setBounds(700, 240, 250, 30);

        JLabel lab14 = new JLabel("Experience :");
        lab14.setFont(new Font("Serif", Font.PLAIN, 25));
        lab14.setBounds(700, 280, 250, 30);

        JLabel lab15 = new JLabel("Account NO :");
        lab15.setFont(new Font("Serif", Font.PLAIN, 25));
        lab15.setBounds(700, 320, 250, 30);

        // create TextFields and set their properties

        text1 = new JTextField();
        text1.setFont(new Font("Serif", Font.PLAIN, 25));
        text1.setBounds(300, 165, 300, 30);

        text2 = new JTextField();
        text2.setFont(new Font("Serif", Font.PLAIN, 25));
        text2.setBounds(300, 205, 300, 30);

        text3 = new JTextField();
        text3.setFont(new Font("Serif", Font.PLAIN, 25));
        text3.setBounds(300, 245, 300, 30);

        text4 = new JTextField();
        text4.setFont(new Font("Serif", Font.PLAIN, 25));
        text4.setBounds(300, 285, 300, 30);

        text5 = new PlaceholderTextField("YYYY-MM-DD");
        text5.setFont(new Font("Serif", Font.PLAIN, 25));
        text5.setBounds(300, 325, 300, 30);

        // ComboBox for gender
        ch1 = new JComboBox<>(gender);
        ch1.setBounds(300, 365, 300, 30);
        ch1.setSelectedIndex(-1);

        // TextFields
        text7 = new JTextField();
        text7.setFont(new Font("Serif", Font.PLAIN, 25));
        text7.setBounds(300, 405, 300, 30);

        text8 = new JTextField();
        text8.setFont(new Font("Serif", Font.PLAIN, 25));
        text8.setBounds(300, 445, 300, 30);

        text9 = new PlaceholderTextField("YYYY-MM-DD");
        text9.setFont(new Font("Serif", Font.PLAIN, 25));
        text9.setBounds(300, 485, 300, 30);

        text10 = new JTextField();
        text10.setFont(new Font("Serif", Font.PLAIN, 25));
        text10.setBounds(300, 525, 300, 30);

        text11 = new JTextField();
        text11.setFont(new Font("Serif", Font.PLAIN, 25));
        text11.setBounds(950, 165, 300, 30);

        text12 = new PlaceholderTextField("Optional");
        text12.setFont(new Font("Serif", Font.PLAIN, 25));
        text12.setBounds(950, 205, 300, 30);

        text13 = new JTextField();
        text13.setFont(new Font("Serif", Font.PLAIN, 25));
        text13.setBounds(950, 245, 300, 30);

        text14 = new JTextField();
        text14.setFont(new Font("Serif", Font.PLAIN, 25));
        text14.setBounds(950, 285, 300, 30);

        text15 = new PlaceholderTextField("Optional");
        text15.setFont(new Font("Serif", Font.PLAIN, 25));
        text15.setBounds(950, 325, 300, 30);

        // Here add label in frame
        frame2.add(label2);
        frame2.add(lab1);
        frame2.add(lab2);
        frame2.add(lab3);
        frame2.add(lab4);
        frame2.add(lab5);
        frame2.add(lab6);
        frame2.add(lab7);
        frame2.add(lab8);
        frame2.add(lab9);
        frame2.add(lab10);
        frame2.add(lab11);
        frame2.add(lab12);
        frame2.add(lab13);
        frame2.add(lab14);
        frame2.add(lab15);

        // here add textField to frame

        frame2.add(text1);
        frame2.add(text2);
        frame2.add(text3);
        frame2.add(text4);
        frame2.add(text5);
        frame2.add(ch1);
        frame2.add(text7);
        frame2.add(text8);
        frame2.add(text9);
        frame2.add(text10);
        frame2.add(text11);
        frame2.add(text12);
        frame2.add(text13);
        frame2.add(text14);
        frame2.add(text15);

        // create buttons for submit ,clear,back

        sub = new JButton(" SUBMIT ");
        sub.setBounds(400, 600, 200, 30);

        clear = new JButton(" CLEAR ");
        clear.setBounds(650, 600, 200, 30);

        back = new JButton("Home");
        back.setBounds(30, 30, 100, 30);

        // here add button to frame
        frame2.add(sub);
        frame2.add(clear);
        frame2.add(back);

        // set the properties of frame
        frame2.setSize(500, 500);
        frame2.setLayout(null);
        frame2.getContentPane().setBackground(Color.YELLOW);
        frame2.setVisible(true);
        frame2.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Register the button components with frame
        back.addActionListener(this);
        sub.addActionListener(this);
        clear.addActionListener(this);

    }

    // All logics for this class are inside this method.
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back) {

            new First(Database, DBPassword, School_Name);
            frame2.dispose();
        }
        if (e.getSource() == clear) {
            text1.setText(null);
            text2.setText(null);
            text3.setText(null);
            text4.setText(null);
            text5.setText(null);
            text7.setText(null);
            text8.setText(null);
            text9.setText(null);
            text10.setText(null);
            text11.setText(null);
            text12.setText(null);
            text13.setText(null);
            text14.setText(null);
            text15.setText(null);
            ch1.setSelectedItem(gender[0]);

        }
        if (e.getSource() == sub) {
            try {
                if (text12.getText().toString().equals(null)) {
                    text12.setText("null");

                }
                if (text15.getText().toString().equals(null)) {
                    text15.setText("null");
                }

                // connect app to mysql to insert Teacher Basic details into the database.
                String url = "jdbc:mysql://localhost:3306/" + Database;
                String user = "root";

                Connection connection = DriverManager.getConnection(url, user, DBPassword);
                Statement statement = connection.createStatement();
                statement.execute(
                        "insert into Teacher_Registration(Teacher_Id ,Teacher_Name ,Father_Name ,Mother_Name , DOB ,Gender, Mob1 ,Mob2 ,Joining_Date ,Address , Teacher_Aadhaar_No,Family_Id,Qualification, Experience ,Account_No) values("
                                + text1.getText() + ", \"" + text2.getText() + "\", \"" + text3.getText() + "\", \""
                                + text4.getText() + "\" , \'" + text5.getText() + "\', \"" + ch1.getSelectedItem()
                                + "\", " + text7.getText() + "," + text8.getText() + ",\'" + text9.getText() + "\',\""
                                + text10.getText() + "\", " + text11.getText() + ", \"" + text12.getText() + "\", \""
                                + text13.getText() + "\", \"" + text14.getText() + "\", \"" + text15.getText()
                                + "\");");
                statement.close();
                connection.close();
                JOptionPane.showMessageDialog(this, "Saved Into Database Successfully."); // showing the message whether
                                                                                          // the Teacher
                // Details insert into the database or not .

            } catch (Exception a) {
                JOptionPane.showMessageDialog(this, "Something is missing or Incorrect enteries");
            }
        }
    }
}


/*
 * This class provides an user interface that allows the user to insert Teacher
 * Salary Structure into the database.
 * This class extends PlaceholderTextField to fill the background of textfield
 * that shows how to enter date and which textfield is optional.
 * This class extends ActionListener for event handling.
 */

class TeacherSalaryStructure extends PlaceholderTextField implements ActionListener {

    // Buttons :
    // sub to insert the Teacher Salary Structure into the database,
    // clear for clear the information that are filled,
    // back for redirect the user to Home page of App
    JButton back, clear, sub;

    // TextField:
    // text1 allows the user to enter Teacher ID,
    // text2 allows the user to enter Teacher Name,
    // text3 allows the user to enter Teacher's Salary,
    // text4 allows the user to enter Month,
    // text5 allows the user to enter Year
    JTextField text1, text2, text3, text4, text5;

    // Database to Store the database name
    // DBPassword to store the mysql password
    // School_Name to store the School name
    // placeholder for background text of textfield
    String placeholder, Database, DBPassword, School_Name;

    JFrame frame; // frame in which all other components are added

    // constructor
    TeacherSalaryStructure(String placeholder, String database, String Password, String School) {

        super(placeholder);
        Database = database;
        DBPassword = Password;
        School_Name = School;

        // create a frame
        frame = new JFrame();

        // create labels and set their properties
        JLabel label = new JLabel();
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setSize(1250, 100);
        label.setText("SALARY STRUCTURE");
        label.setFont(new Font("Serif", Font.BOLD, 50));

        JLabel lab1 = new JLabel("Teacher ID :");
        lab1.setFont(new Font("Serif", Font.PLAIN, 25));
        lab1.setBounds(150, 160, 250, 30);

        JLabel lab2 = new JLabel("Teacher Name :");
        lab2.setFont(new Font("Serif", Font.PLAIN, 25));
        lab2.setBounds(150, 200, 250, 30);

        JLabel lab3 = new JLabel("Salary :");
        lab3.setFont(new Font("Serif", Font.PLAIN, 25));
        lab3.setBounds(150, 240, 250, 30);

        JLabel lab4 = new JLabel("Month :");
        lab4.setFont(new Font("Serif", Font.PLAIN, 25));
        lab4.setBounds(150, 280, 250, 30);

        JLabel lab5 = new JLabel("Year :");
        lab5.setFont(new Font("Serif", Font.PLAIN, 25));
        lab5.setBounds(150, 320, 250, 30);

        // Here add label in frame
        frame.add(label);

        frame.add(lab1);
        frame.add(lab2);
        frame.add(lab3);
        frame.add(lab4);
        frame.add(lab5);

        // create TextFields and set their properties

        text1 = new JTextField();
        text1.setFont(new Font("Serif", Font.PLAIN, 25));
        text1.setBounds(450, 165, 300, 30);

        text2 = new JTextField();
        text2.setFont(new Font("Serif", Font.PLAIN, 25));
        text2.setBounds(450, 205, 300, 30);

        text3 = new JTextField();
        text3.setFont(new Font("Serif", Font.PLAIN, 25));
        text3.setBounds(450, 245, 300, 30);

        text4 = new PlaceholderTextField("eg : January");
        text4.setFont(new Font("Serif", Font.PLAIN, 25));
        text4.setBounds(450, 285, 300, 30);

        text5 = new JTextField();
        text5.setFont(new Font("Serif", Font.PLAIN, 25));
        text5.setBounds(450, 325, 300, 30);

        // create submit, clear and back button

        sub = new JButton(" SUBMIT ");
        sub.setBounds(400, 600, 200, 30);

        clear = new JButton(" CLEAR ");
        clear.setBounds(650, 600, 200, 30);

        back = new JButton("Home");
        back.setBounds(30, 30, 100, 30);

        // here add textfields to frame
        frame.add(text1);
        frame.add(text2);
        frame.add(text3);
        frame.add(text4);
        frame.add(text5);

        // here add buttons to frame
        frame.add(sub);
        frame.add(clear);
        frame.add(back);

        // set the properties of frame
        frame.setSize(500, 500);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.YELLOW);
        frame.setVisible(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Register the component with EventListener
        back.addActionListener(this);
        clear.addActionListener(this);
        sub.addActionListener(this);
    }

    // All logics for this class are inside this method
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back) {

            new First(Database, DBPassword, School_Name);
            frame.dispose();
        }
        if (e.getSource() == clear) {
            text1.setText(null);
            text2.setText(null);
            text3.setText(null);
            text4.setText(null);
            text5.setText(null);

        }

        if (e.getSource() == sub) {
            try {
                // connect App to mysql to insert Teacher Salary Structure into the database.
                String url = "jdbc:mysql://localhost:3306/" + Database;
                String user = "root";

                Connection connection = DriverManager.getConnection(url, user, DBPassword);
                Statement statement = connection.createStatement();
                statement.execute("insert into Teacher_Salary_Structure(Teacher_Id,Teacher_Name,Salary,Month,Year) values(" + text1.getText() + ", \""
                        + text2.getText() + "\", " + text3.getText() + ", \"" + text4.getText() + "\" , "
                        + text5.getText() + " );");
                statement.close();
                connection.close();
                JOptionPane.showMessageDialog(this, "Saved Into Database Successfully."); // showing the message whether
                                                                                          // the Teacher
                // Details insert into the database or not .

            } catch (Exception a) {
                JOptionPane.showMessageDialog(this, "Something is missing or Incorrect enteries");
            }
        }

    }
}

/*
 * This class provides an user interface that allows the user to insert Teacher
 * Salary details(per month) into the database.
 * This class extends PlaceholderTextField to fill the background of textfield
 * that shows how to enter date and which textfield is optional.
 * This class extends ActionListener for event handling.
 */
class TeacherSalaryDetails extends PlaceholderTextField implements ActionListener {

    // Buttons :
    // sub to insert the Teacher Salary details into the database,
    // clear for clear the information that are filled,
    // back for redirect the user to Home page of App
    JButton back, sub, clear;

    // TextField:
    // text1 allows the user to enter Teacher ID,
    // text2 allows the user to enter Teacher Name,
    // text3 allows the user to enter Teacher's Salary Paid,
    // text4 allows the user to enter Month,
    // text5 allows the user to enter Year

    JTextField text1, text2, text3, text4, text5;

    // Database to Store the database name
    // DBPassword to store the mysql password
    // School_Name to store the School name
    // placeholder for background text of textfield
    String placeholder, Database, DBPassword, School_Name;

    JFrame frame; // frame in which all other components are added.

    // constructor
    TeacherSalaryDetails(String placeholder, String database, String Password, String School) {

        super(placeholder);
        Database = database;
        DBPassword = Password;
        School_Name = School;

        // create a frame
        frame = new JFrame();

        // create labels and set their properties
        JLabel label = new JLabel();
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setSize(1250, 100);
        label.setText("SALARY DETAILS");
        label.setFont(new Font("Serif", Font.BOLD, 50));

        JLabel lab1 = new JLabel("Teacher ID :");
        lab1.setFont(new Font("Serif", Font.PLAIN, 25));
        lab1.setBounds(150, 160, 250, 30);

        JLabel lab2 = new JLabel("Teacher Name :");
        lab2.setFont(new Font("Serif", Font.PLAIN, 25));
        lab2.setBounds(150, 200, 250, 30);

        JLabel lab3 = new JLabel("Salary Paid :");
        lab3.setFont(new Font("Serif", Font.PLAIN, 25));
        lab3.setBounds(150, 240, 250, 30);

        JLabel lab4 = new JLabel("Month :");
        lab4.setFont(new Font("Serif", Font.PLAIN, 25));
        lab4.setBounds(150, 280, 250, 30);

        JLabel lab5 = new JLabel("Year :");
        lab5.setFont(new Font("Serif", Font.PLAIN, 25));
        lab5.setBounds(150, 320, 250, 30);

        // Here add label in frame
        frame.add(label);
        frame.add(lab1);
        frame.add(lab2);
        frame.add(lab3);
        frame.add(lab4);
        frame.add(lab5);

        // create TextFields and set their properties

        text1 = new JTextField();
        text1.setFont(new Font("Serif", Font.PLAIN, 25));
        text1.setBounds(450, 165, 300, 30);

        text2 = new JTextField();
        text2.setFont(new Font("Serif", Font.PLAIN, 25));
        text2.setBounds(450, 205, 300, 30);

        text3 = new JTextField();
        text3.setFont(new Font("Serif", Font.PLAIN, 25));
        text3.setBounds(450, 245, 300, 30);

        text4 = new PlaceholderTextField("eg : January");
        text4.setFont(new Font("Serif", Font.PLAIN, 25));
        text4.setBounds(450, 285, 300, 30);

        text5 = new JTextField();
        text5.setFont(new Font("Serif", Font.PLAIN, 25));
        text5.setBounds(450, 325, 300, 30);

        // create submit, clear and back button

        sub = new JButton(" SUBMIT ");
        sub.setBounds(400, 600, 200, 30);

        clear = new JButton(" CLEAR ");
        clear.setBounds(650, 600, 200, 30);

        back = new JButton("Home");
        back.setBounds(30, 30, 100, 30);

        // here add textfields to frame
        frame.add(text1);
        frame.add(text2);
        frame.add(text3);
        frame.add(text4);
        frame.add(text5);

        // here add buttons to frame
        frame.add(sub);
        frame.add(clear);
        frame.add(back);

        // set the properties of frame
        frame.setSize(500, 500);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.YELLOW);
        frame.setVisible(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Register the buttons with EventListener
        back.addActionListener(this);
        clear.addActionListener(this);
        sub.addActionListener(this);
    }

    // All logics for this class are inside this method
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back) {

            new First(Database, DBPassword, School_Name);
            frame.dispose();
        }
        if (e.getSource() == clear) {
            text1.setText(null);
            text2.setText(null);
            text3.setText(null);
            text4.setText(null);
            text5.setText(null);

        }

        if (e.getSource() == sub) {
            try {

                // connect App to mysql to insert Teacher Salary details into the database.
                String url = "jdbc:mysql://localhost:3306/" + Database;
                String user = "root";

                Connection connection = DriverManager.getConnection(url, user, DBPassword);
                Statement statement = connection.createStatement();
                statement.execute("insert into Teacher_Salary_Details(Teacher_Id,Teacher_Name,Salary_Paid,Month,Year) values(" + text1.getText() + ", \""
                        + text2.getText() + "\", " + text3.getText() + ", \"" + text4.getText() + "\" , "
                        + text5.getText() + " );");
                statement.close();
                connection.close();
                JOptionPane.showMessageDialog(this, "Saved Into Database Successfully."); // showing the message whether
                                                                                          // the Teacher
                // Details insert into the database or not .

            } catch (Exception a) {
                JOptionPane.showMessageDialog(this, "Something is missing or Incorrect enteries");
            }
        }
    }
}

/*
 * This class provides an user interface that allows the user to insert Other
 * Empolyee Basic details into the database.
 * This class extends PlaceholderTextField to fill the background of textfield
 * that shows how to enter date and which textfield is optional.
 * This class extends ActionListener for event handling.
 */
class OtherEmployee extends PlaceholderTextField implements ActionListener {

    // Buttons :
    // sub to insert Other Employee Basic details into the database,
    // clear for clear the information that are filled,
    // back for redirect the user to Home page of App
    JButton back, sub, clear;

    // TextField:
    // text1 allows the user to enter Employee ID,
    // text2 allows the user to enter Employee Name,
    // text3 allows the user to enter Employee's Father Name,
    // text4 allows the user to enter Employee's Mother Name,
    // text5 allows the user to enter Employee's Date of Birth,
    // text7 allows the user to enter Employee's Mobile Number1 ,
    // text8 allows the user to enter Employee's Mobile Number2,
    // text9 allows the user to enter Employee's Joining Date,
    // text10 allows the user to enter Employee's Address,
    // text11 allows the user to enter Employee's Aadhaar No,
    // text12 allows the user to enter Employee's Family_ID,
    // text13 allows the user to enter Employee's Qualification,
    // text14 allows the user to enter Employee's Job,
    // text15 allows the user to enter Employee's Account No,

    JTextField text1, text2, text3, text4, text5, text7, text8, text9, text10, text11, text12, text13, text14, text15;

    // JcomboBox ch1 allows the user to select gender of other employee .
    JComboBox<String> ch1;

    String gender[] = { "Male", "Female", "Other" }; // Option for gender JComboBox ch1

    JFrame frame3; // frame in which all other components are addded.

    // Database to Store the database name
    // DBPassword to store the mysql password
    // School_Name to store the School name
    // placeholder for background text of textfield
    String placeholder, Database, DBPassword, School_Name;

    // constructor
    OtherEmployee(String placeholder, String database, String Password, String School) {

        super(placeholder);
        Database = database;
        DBPassword = Password;
        School_Name = School;

        // create a frame
        frame3 = new JFrame();

        // create labels and set their properties
        JLabel label3 = new JLabel();
        label3.setHorizontalAlignment(JLabel.CENTER);
        label3.setSize(1250, 100);
        label3.setText("EMPLOYEE  REGISTRATION  FORM ");
        label3.setFont(new Font("Serif", Font.BOLD, 50));

        JLabel lab1 = new JLabel("Employee ID :");
        lab1.setFont(new Font("Serif", Font.PLAIN, 25));
        lab1.setBounds(70, 160, 250, 30);

        JLabel lab2 = new JLabel("Employee Name :");
        lab2.setFont(new Font("Serif", Font.PLAIN, 25));
        lab2.setBounds(70, 200, 250, 30);

        JLabel lab3 = new JLabel("Father Name :");
        lab3.setFont(new Font("Serif", Font.PLAIN, 25));
        lab3.setBounds(70, 240, 250, 30);

        JLabel lab4 = new JLabel("Mother_Name :");
        lab4.setFont(new Font("Serif", Font.PLAIN, 25));
        lab4.setBounds(70, 280, 250, 30);

        JLabel lab5 = new JLabel("Date Of Birth :");
        lab5.setFont(new Font("Serif", Font.PLAIN, 25));
        lab5.setBounds(70, 320, 250, 30);

        JLabel lab6 = new JLabel("Gender :");
        lab6.setFont(new Font("Serif", Font.PLAIN, 25));
        lab6.setBounds(70, 360, 250, 30);

        JLabel lab7 = new JLabel("Mobile Number1 :");
        lab7.setFont(new Font("Serif", Font.PLAIN, 25));
        lab7.setBounds(70, 400, 250, 30);

        JLabel lab8 = new JLabel("Mobile Number2 :");
        lab8.setFont(new Font("Serif", Font.PLAIN, 25));
        lab8.setBounds(70, 440, 250, 30);

        JLabel lab9 = new JLabel("Joining Date :");
        lab9.setFont(new Font("Serif", Font.PLAIN, 25));
        lab9.setBounds(70, 480, 250, 30);

        JLabel lab10 = new JLabel("Address :");
        lab10.setFont(new Font("Serif", Font.PLAIN, 25));
        lab10.setBounds(70, 520, 250, 30);

        JLabel lab11 = new JLabel("Employee's Aaadhar NO:");
        lab11.setFont(new Font("Serif", Font.PLAIN, 25));
        lab11.setBounds(700, 160, 258, 30);

        JLabel lab12 = new JLabel("Family ID :");
        lab12.setFont(new Font("Serif", Font.PLAIN, 25));
        lab12.setBounds(700, 200, 250, 30);

        JLabel lab13 = new JLabel("Qualification :");
        lab13.setFont(new Font("Serif", Font.PLAIN, 25));
        lab13.setBounds(700, 240, 250, 30);

        JLabel lab14 = new JLabel("Job :");
        lab14.setFont(new Font("Serif", Font.PLAIN, 25));
        lab14.setBounds(700, 280, 250, 30);

        JLabel lab15 = new JLabel("Account NO :");
        lab15.setFont(new Font("Serif", Font.PLAIN, 25));
        lab15.setBounds(700, 320, 250, 30);

        // create TextFields and set their properties

        text1 = new JTextField();
        text1.setFont(new Font("Serif", Font.PLAIN, 25));
        text1.setBounds(300, 165, 300, 30);

        text2 = new JTextField();
        text2.setFont(new Font("Serif", Font.PLAIN, 25));
        text2.setBounds(300, 205, 300, 30);

        text3 = new JTextField();
        text3.setFont(new Font("Serif", Font.PLAIN, 25));
        text3.setBounds(300, 245, 300, 30);

        text4 = new JTextField();
        text4.setFont(new Font("Serif", Font.PLAIN, 25));
        text4.setBounds(300, 285, 300, 30);

        text5 = new PlaceholderTextField("YYYY-MM-DD");
        text5.setFont(new Font("Serif", Font.PLAIN, 25));
        text5.setBounds(300, 325, 300, 30);

        text7 = new JTextField();
        text7.setFont(new Font("Serif", Font.PLAIN, 25));
        text7.setBounds(300, 405, 300, 30);

        text8 = new JTextField();
        text8.setFont(new Font("Serif", Font.PLAIN, 25));
        text8.setBounds(300, 445, 300, 30);

        text9 = new PlaceholderTextField("YYYY-MM-DD");
        text9.setFont(new Font("Serif", Font.PLAIN, 25));
        text9.setBounds(300, 485, 300, 30);

        text10 = new JTextField();
        text10.setFont(new Font("Serif", Font.PLAIN, 25));
        text10.setBounds(300, 525, 300, 30);

        text11 = new JTextField();
        text11.setFont(new Font("Serif", Font.PLAIN, 25));
        text11.setBounds(960, 165, 300, 30);

        text12 = new PlaceholderTextField("Optional");
        text12.setFont(new Font("Serif", Font.PLAIN, 25));
        text12.setBounds(960, 205, 300, 30);

        text13 = new JTextField();
        text13.setFont(new Font("Serif", Font.PLAIN, 25));
        text13.setBounds(960, 245, 300, 30);

        text14 = new JTextField();
        text14.setFont(new Font("Serif", Font.PLAIN, 25));
        text14.setBounds(960, 285, 300, 30);

        text15 = new PlaceholderTextField("Optional");
        text15.setFont(new Font("Serif", Font.PLAIN, 25));
        text15.setBounds(960, 325, 300, 30);

        // ComboBox for gender
        ch1 = new JComboBox<>(gender);
        ch1.setBounds(300, 365, 300, 30);
        ch1.setSelectedIndex(-1);

        // Here add labels to frame
        frame3.add(label3);
        frame3.add(lab1);
        frame3.add(lab2);
        frame3.add(lab3);
        frame3.add(lab4);
        frame3.add(lab5);
        frame3.add(lab6);
        frame3.add(lab7);
        frame3.add(lab8);
        frame3.add(lab9);
        frame3.add(lab10);
        frame3.add(lab11);
        frame3.add(lab12);
        frame3.add(lab13);
        frame3.add(lab14);
        frame3.add(lab15);

        // here add textFields to frame

        frame3.add(text1);
        frame3.add(text2);
        frame3.add(text3);
        frame3.add(text4);
        frame3.add(text5);
        frame3.add(ch1);
        frame3.add(text7);
        frame3.add(text8);
        frame3.add(text9);
        frame3.add(text10);
        frame3.add(text11);
        frame3.add(text12);
        frame3.add(text13);
        frame3.add(text14);
        frame3.add(text15);

        // create buttons for submit ,clear and back

        sub = new JButton(" SUBMIT ");
        sub.setBounds(400, 600, 200, 30);

        clear = new JButton(" CLEAR ");
        clear.setBounds(650, 600, 200, 30);

        back = new JButton("Home");
        back.setBounds(30, 30, 100, 30);

        // here add buttons to frame
        frame3.add(sub);
        frame3.add(clear);
        frame3.add(back);

        // set the properties of frame
        frame3.setSize(500, 500);
        frame3.setLayout(null);
        frame3.getContentPane().setBackground(Color.YELLOW);
        frame3.setVisible(true);
        frame3.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Register the button with Event listener
        back.addActionListener(this);
        sub.addActionListener(this);
        clear.addActionListener(this);
    }

    // All the logics for this class are inside this method
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back) {

            new First(Database, DBPassword, School_Name);
            frame3.dispose();
        }
        if (e.getSource() == clear) {
            text1.setText(null);
            text2.setText(null);
            text3.setText(null);
            text4.setText(null);
            text5.setText(null);
            text7.setText(null);
            text8.setText(null);
            text9.setText(null);
            text10.setText(null);
            text11.setText(null);
            text12.setText(null);
            text13.setText(null);
            text14.setText(null);
            ch1.setSelectedItem(gender[0]);

        }
        if (e.getSource() == sub) {
            try {
                if (text12.getText().toString().equals(null)) {
                    text12.setText("-");

                }
                if (text15.getText().toString().equals(null)) {
                    text15.setText("-");
                }

                // connect app to mysql to insert Other Employee Basic details into the database
                // .
                String url = "jdbc:mysql://localhost:3306/" + Database;
                String user = "root";

                Connection connection = DriverManager.getConnection(url, user, DBPassword);
                Statement statement = connection.createStatement();
                statement.execute(
                        "insert into Employee_Registration (Employee_Id,Employee_Name ,Father_Name ,Mother_Name ,DOB ,Gender , Mob1 ,Mob2 ,Joining_Date,Address , Employee_Aadhaar_No ,Family_Id,Qualification , Experience,Account_No) values("
                                + text1.getText() + ", \"" + text2.getText() + "\", \"" + text3.getText() + "\", \""
                                + text4.getText() + "\" , \'" + text5.getText() + "\', \"" + ch1.getSelectedItem()
                                + "\", " + text7.getText() + "," + text8.getText() + ",\'" + text9.getText() + "\',\""
                                + text10.getText() + "\", " + text11.getText() + ", \"" + text12.getText() + "\", \""
                                + text13.getText() + "\", \"" + text14.getText() + "\", \"" + text15.getText()
                                + "\");");
                statement.close();
                connection.close();
                JOptionPane.showMessageDialog(this, "Saved Into Database Successfully."); // showing the message whether
                                                                                          // the Employee
                // Details insert into the database or not .

            } catch (Exception a) {
                JOptionPane.showMessageDialog(this, "Something is missing or Incorrect enteries");
            }
        }
    }
}

/*
 * This class provides an user interface that allows the user to insert Other
 * Empolyee Salary Structure into the database.
 * This class extends PlaceholderTextField to fill the background of textfield
 * that shows how to enter date and which textfield is optional.
 * This class extends ActionListener for event handling.
 */
class EmployeeSalaryStructure extends PlaceholderTextField implements ActionListener {

    // Buttons :
    // sub to insert Other Employee Salary Structure into the database,
    // clear for clear the information that are filled,
    // back for redirect the user to Home page of App
    JButton back, sub, clear;

    // TextField:
    // text1 allows the user to enter Employee ID,
    // text2 allows the user to enter Employee Name,
    // text3 allows the user to enter Employee's Salary,
    // text4 allows the user to enter Month,
    // text5 allows the user to enter Year

    JTextField text1, text2, text3, text4, text5;

    // Database to Store the database name
    // DBPassword to store the mysql password
    // School_Name to store the School name
    // placeholder for background text of textfield
    String placeholder, Database, DBPassword, School_Name;

    JFrame frame; // create a frame in which all other components are added .

    // constructor
    EmployeeSalaryStructure(String placeholder, String database, String Password, String School) {

        super(placeholder);
        Database = database;
        DBPassword = Password;
        School_Name = School;

        // create a Frame
        frame = new JFrame();

        // create label and set their properties
        JLabel label = new JLabel();
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setSize(1250, 100);
        label.setText("SALARY STRUCTURE");
        label.setFont(new Font("Serif", Font.BOLD, 50));

        JLabel lab1 = new JLabel("Employee ID :");
        lab1.setFont(new Font("Serif", Font.PLAIN, 25));
        lab1.setBounds(150, 160, 250, 30);

        JLabel lab2 = new JLabel("Employee Name :");
        lab2.setFont(new Font("Serif", Font.PLAIN, 25));
        lab2.setBounds(150, 200, 250, 30);

        JLabel lab3 = new JLabel("Salary :");
        lab3.setFont(new Font("Serif", Font.PLAIN, 25));
        lab3.setBounds(150, 240, 250, 30);

        JLabel lab4 = new JLabel("Month :");
        lab4.setFont(new Font("Serif", Font.PLAIN, 25));
        lab4.setBounds(150, 280, 250, 30);

        JLabel lab5 = new JLabel("Year :");
        lab5.setFont(new Font("Serif", Font.PLAIN, 25));
        lab5.setBounds(150, 320, 250, 30);

        // Here add labels in frame
        frame.add(label);

        frame.add(lab1);
        frame.add(lab2);
        frame.add(lab3);
        frame.add(lab4);
        frame.add(lab5);

        // create TextFields and set their properties

        text1 = new JTextField();
        text1.setFont(new Font("Serif", Font.PLAIN, 25));
        text1.setBounds(450, 165, 300, 30);

        text2 = new JTextField();
        text2.setFont(new Font("Serif", Font.PLAIN, 25));
        text2.setBounds(450, 205, 300, 30);

        text3 = new JTextField();
        text3.setFont(new Font("Serif", Font.PLAIN, 25));
        text3.setBounds(450, 245, 300, 30);

        text4 = new PlaceholderTextField("eg : January");
        text4.setFont(new Font("Serif", Font.PLAIN, 25));
        text4.setBounds(450, 285, 300, 30);

        text5 = new JTextField();
        text5.setFont(new Font("Serif", Font.PLAIN, 25));
        text5.setBounds(450, 325, 300, 30);

        // add submit,clear and back buttons

        sub = new JButton(" SUBMIT ");
        sub.setBounds(400, 600, 200, 30);

        clear = new JButton(" CLEAR ");
        clear.setBounds(650, 600, 200, 30);

        back = new JButton("Home");
        back.setBounds(30, 30, 100, 30);

        // here add textfields to frame
        frame.add(text1);
        frame.add(text2);
        frame.add(text3);
        frame.add(text4);
        frame.add(text5);

        // here add buttons to frame
        frame.add(sub);
        frame.add(clear);
        frame.add(back);

        // set the properties of frame
        frame.setSize(500, 500);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.YELLOW);
        frame.setVisible(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Register the Button with the Event Listener
        back.addActionListener(this);
        sub.addActionListener(this);
        clear.addActionListener(this);
    }

    // All logics for this class are inside this method.
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back) {

            new First(Database, DBPassword, School_Name);
            frame.dispose();
        }
        if (e.getSource() == clear) {
            text1.setText(null);
            text2.setText(null);
            text3.setText(null);
            text4.setText(null);
            text5.setText(null);

        }
        if (e.getSource() == sub) {
            try {

                // connect app to mysql to insert the Employee Salary Structure into database.
                String url = "jdbc:mysql://localhost:3306/" + Database;
                String user = "root";

                Connection connection = DriverManager.getConnection(url, user, DBPassword);
                Statement statement = connection.createStatement();
                statement.execute("insert into Employee_Salary_Structure(Employee_Id,Employee_Name,Salary,Month,Year) values(" + text1.getText() + ", \""
                        + text2.getText() + "\", " + text3.getText() + ", \"" + text4.getText() + "\" , "
                        + text5.getText() + ");");
                statement.close();
                connection.close();
                JOptionPane.showMessageDialog(this, "Saved Into Database Successfully."); // showing the message whether
                                                                                          // the Employee
                // Details insert into the database or not .

            } catch (Exception a) {
                JOptionPane.showMessageDialog(this, "Something is missing or Incorrect enteries");
            }
        }
    }
}

/*
 * This class provides an user interface that allows the user to insert Other
 * Empolyee Salary details into the database.
 * This class extends PlaceholderTextField to fill the background of textfield
 * that shows how to enter date and which textfield is optional.
 * This class extends ActionListener for event handling.
 */
class EmployeeSalaryDetails extends PlaceholderTextField implements ActionListener {

    // Buttons :
    // sub to insert Other Employee Salary details into the database,
    // clear for clear the information that are filled,
    // back for redirect the user to Home page of App
    JButton back, sub, clear;

    // TextField:
    // text1 allows the user to enter Employee ID,
    // text2 allows the user to enter Employee Name,
    // text3 allows the user to enter Employee's Salary Paid,
    // text4 allows the user to enter Month,
    // text5 allows the user to enter Year
    JTextField text1, text2, text3, text4, text5;

    // Database to Store the database name
    // DBPassword to store the mysql password
    // School_Name to store the School name
    // placeholder for background text of textfield
    String placeholder, Database, DBPassword, School_Name;

    JFrame frame; // frame in which all other components are added.

    // constructor
    EmployeeSalaryDetails(String placeholder, String database, String Password, String School) {

        super(placeholder);
        Database = database;
        DBPassword = Password;
        School_Name = School;

        // create frame
        frame = new JFrame();

        // create labels and set their properties
        JLabel label = new JLabel();
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setSize(1250, 100);
        label.setText("SALARY DETAILS");
        label.setFont(new Font("Serif", Font.BOLD, 50));

        JLabel lab1 = new JLabel("Employee ID :");
        lab1.setFont(new Font("Serif", Font.PLAIN, 25));
        lab1.setBounds(150, 160, 250, 30);

        JLabel lab2 = new JLabel("Employee Name :");
        lab2.setFont(new Font("Serif", Font.PLAIN, 25));
        lab2.setBounds(150, 200, 250, 30);

        JLabel lab3 = new JLabel("Salary Paid :");
        lab3.setFont(new Font("Serif", Font.PLAIN, 25));
        lab3.setBounds(150, 240, 250, 30);

        JLabel lab4 = new JLabel("Month :");
        lab4.setFont(new Font("Serif", Font.PLAIN, 25));
        lab4.setBounds(150, 280, 250, 30);

        JLabel lab5 = new JLabel("Year :");
        lab5.setFont(new Font("Serif", Font.PLAIN, 25));
        lab5.setBounds(150, 320, 250, 30);

        // Here add labels in frame
        frame.add(label);

        frame.add(lab1);
        frame.add(lab2);
        frame.add(lab3);
        frame.add(lab4);
        frame.add(lab5);

        // create TextFields and set their properties

        text1 = new JTextField();
        text1.setFont(new Font("Serif", Font.PLAIN, 25));
        text1.setBounds(450, 165, 300, 30);

        text2 = new JTextField();
        text2.setFont(new Font("Serif", Font.PLAIN, 25));
        text2.setBounds(450, 205, 300, 30);

        text3 = new JTextField();
        text3.setFont(new Font("Serif", Font.PLAIN, 25));
        text3.setBounds(450, 245, 300, 30);

        text4 = new PlaceholderTextField("eg : January");
        text4.setFont(new Font("Serif", Font.PLAIN, 25));
        text4.setBounds(450, 285, 300, 30);

        text5 = new JTextField();
        text5.setFont(new Font("Serif", Font.PLAIN, 25));
        text5.setBounds(450, 325, 300, 30);

        // add submit, clear and back button

        sub = new JButton(" SUBMIT ");
        sub.setBounds(400, 600, 200, 30);

        clear = new JButton(" CLEAR ");
        clear.setBounds(650, 600, 200, 30);

        back = new JButton("Home");
        back.setBounds(30, 30, 100, 30);

        // here add textfields to frame
        frame.add(text1);
        frame.add(text2);
        frame.add(text3);
        frame.add(text4);
        frame.add(text5);

        // here add buttons to frame
        frame.add(sub);
        frame.add(clear);
        frame.add(back);

        // set the properties of frame
        frame.setSize(500, 500);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.YELLOW);
        frame.setVisible(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Register the buttons with event Listener
        back.addActionListener(this);
        sub.addActionListener(this);
        clear.addActionListener(this);
    }

    // All logics for this class are inside this method
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back) {

            new First(Database, DBPassword, School_Name);
            frame.dispose();
        }
        if (e.getSource() == clear) {
            text1.setText(null);
            text2.setText(null);
            text3.setText(null);
            text4.setText(null);
            text5.setText(null);

        }

        if (e.getSource() == sub) {
            try {

                // connect app to mysql to insert Employee Salary details into the database.
                String url = "jdbc:mysql://localhost:3306/" + Database;
                String user = "root";

                Connection connection = DriverManager.getConnection(url, user, DBPassword);
                Statement statement = connection.createStatement();
                statement.execute("insert into Employee_Salary_Details(Employee_Id , Employee_Name,Salary_Paid,Month,Year) values(" + text1.getText() + ", \""
                        + text2.getText() + "\", " + text3.getText() + ", \"" + text4.getText() + "\" , "
                        + text5.getText() + " );");
                statement.close();
                connection.close();
                JOptionPane.showMessageDialog(this, "Saved Into Database Successfully."); // showing the message whether
                                                                                          // the Employee
                // Details insert into the database or not .

            } catch (Exception a) {
                JOptionPane.showMessageDialog(this, "Something is missing or Incorrect enteries");
            }
        }
    }
}

/*
 * This class provides an user interface that contains three buttons which
 * allows the user to view Student details when they clicked.
 * This class extends ActionListener for event handling.
 */

class StudentData implements ActionListener {

    JFrame frame; // frame in which all other components are added.

    // Buttons :
    // button to show Student Basic details,
    // button1 to show Student's Academic details,
    // button 2 to show the student Fee Details,
    // back for redirect the user to Home page of App
    JButton button, button1, button2, back;

    // Database to Store the database name
    // DBPassword to store the mysql password
    // School_Name to store the School name
    String Database, DBPassword, School_Name;

    // constructor
    StudentData(String database, String Password, String School) {

        Database = database;
        DBPassword = Password;
        School_Name = School;

        // Create a frame
        frame = new JFrame();

        // Create a label and set its properties

        JLabel label = new JLabel();
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setSize(1250, 100);
        label.setText(School_Name);
        label.setFont(new Font("Serif", Font.BOLD, 50));

        // create buttons and add to frame

        button = new JButton("View Student Basic Details");
        button.setBounds(480, 150, 350, 30);
        frame.add(button);

        button1 = new JButton("View Student Academic Details ");
        button1.setBounds(480, 200, 350, 30);
        frame.add(button1);

        button2 = new JButton("View Student Fee Details");
        button2.setBounds(480, 250, 350, 30);
        frame.add(button2);

        back = new JButton("Back");
        back.setBounds(30, 30, 100, 30);
        frame.add(back);

        // Here add label in frame
        frame.add(label);

        // set the properties of frame
        frame.setSize(500, 500);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.YELLOW);
        frame.setVisible(true);

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Registraton of buttons with action listener
        button.addActionListener(this);
        button1.addActionListener(this);
        button2.addActionListener(this);
        back.addActionListener(this);

    }

    // All logics for this class are inside this method.
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == button) {

            StudentView sv = new StudentView(Database, DBPassword, School_Name);
            sv.view();
            frame.dispose();

        }

        if (e.getSource() == button1) {

            AcademicView ac = new AcademicView(Database, DBPassword, School_Name);
            ac.view();
            frame.dispose();
        }
        if (e.getSource() == button2) {

            FeeView fv = new FeeView(Database, DBPassword, School_Name);
            fv.view();
            frame.dispose();
        }
        if (e.getSource() == back) {

            new First(Database, DBPassword, School_Name);
            frame.dispose();
        }

    }
}

/*
 * This class provides an user interface that allows the user to view Student
 * Basic details.
 * This class extends ActionListener and KeyListener for event handling.
 */
class StudentView implements ActionListener, KeyListener {

    JFrame frame; // frame in which all other componenets are added.

    JButton back; // button back redirects the user to Student data page.

    JTextField text1; // textfield to search data .

    // Database to Store the database name
    // DBPassword to store the mysql password
    // School_Name to store the School name
    String Database, DBPassword, School_Name;

    private JTable tableData; // Table to show Student Basic Details

    private DefaultTableModel model; // This a default table model

    // constructor
    StudentView(String database, String password, String school) {
        Database = database;
        DBPassword = password;
        School_Name = school;
    }

    // method
    public void view() {
        try {
            // Create a frame
            frame = new JFrame();

            // Create Labels and set their properties
            JLabel label = new JLabel();
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setSize(1250, 100);
            label.setText("STUDENT'S  BASIC  DETAILS");
            label.setFont(new Font("Serif", Font.BOLD, 50));

            JLabel lab1 = new JLabel("Search Data");
            lab1.setBounds(450, 100, 150, 30);
            lab1.setFont(new Font("Serif", Font.PLAIN, 25));

            // create TextField and set its properties
            text1 = new JTextField();
            text1.setFont(new Font("Serif", Font.PLAIN, 25));
            text1.setBounds(620, 100, 300, 30);

            // Create button back and add to frame
            back = new JButton("Back");
            back.setBounds(30, 30, 100, 30);
            frame.add(back);

            // Add labels and textfield to frame
            frame.add(label);
            frame.add(lab1);
            frame.add(text1);

            // connect app to mysql to fetch the Student Basic details from database.
            String url = "jdbc:mysql://localhost:3306/" + Database;
            String user = "root";

            Connection connection = DriverManager.getConnection(url, user, DBPassword);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select * from Student_Registration;");
            ResultSetMetaData rsmd = resultSet.getMetaData();

            // Create default table model
            model = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false; // All cells are non-editable
                }
            };

            // store the column name into an array named colname
            int columns = rsmd.getColumnCount();
            final String[] colname = new String[columns];
            for (int i = 0; i < columns; i++) {
                colname[i] = rsmd.getColumnName(i + 1);
            }
            // set the column name into the default table model
            model.setColumnIdentifiers(colname);

            // store the columns into an array named row
            while (resultSet.next()) {
                // For each row, a new String array row is created to hold the data for that
                // row.

                String[] row = new String[columns];
                for (int i = 0; i < columns; i++) {
                    row[i] = resultSet.getString(i + 1);
                }
                model.addRow(row); // add row into the default table model
            }

            // create a table using default table model
            tableData = new JTable(model);

            // set the column width of table
            int[] columnWidths = { 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 250, 250, 250, 200, 200, 250, 250,
                    250 };

            for (int i = 0; i < columns; i++) {
                TableColumn column = tableData.getColumnModel().getColumn(i);
                column.setPreferredWidth(columnWidths[i]);
            }

            tableData.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // change the auto resize properties of table .

            // add table to ScrollPane and add table to it and set its properties
            JScrollPane sp = new JScrollPane(tableData);
            sp.setBounds(30, 150, 1200, 500);

            sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

            // add JScrollPane to frame
            frame.add(sp);

            statement.close();
            connection.close();

        } catch (Exception a) {
            System.out.println(a.getMessage());
        }

        // Set the properties of frame
        frame.setSize(1500, 800); // optional
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.YELLOW);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);

        // Register the back button with action listener
        back.addActionListener(this);

        // Register the textfield with KeyListener
        text1.addKeyListener(this);
    }

    // All Logics for this class are inside these four overridden method
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back) {
            new StudentData(Database, DBPassword, School_Name);
            frame.dispose();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        TableRowSorter<DefaultTableModel> obj = new TableRowSorter<>(model);
        tableData.setRowSorter(obj);
        obj.setRowFilter(RowFilter.regexFilter(text1.getText()));
    }

    @Override
    public void keyTyped(KeyEvent e) {
        TableRowSorter<DefaultTableModel> obj = new TableRowSorter<>(model);
        tableData.setRowSorter(obj);
        obj.setRowFilter(RowFilter.regexFilter(text1.getText()));
    }

    @Override
    public void keyReleased(KeyEvent e) {
        TableRowSorter<DefaultTableModel> obj = new TableRowSorter<>(model);
        tableData.setRowSorter(obj);
        obj.setRowFilter(RowFilter.regexFilter(text1.getText()));
    }

}

/*
 * This class provides an user interface that allows the user to view Student
 * Academic details.
 * This class extends ActionListener and KeyListener for event handling.
 */
class AcademicView implements ActionListener, KeyListener {

    JFrame frame; // frame in which all other components are added

    JButton back; // back button for redirects the user to the Student Data page

    JTextField text1; // textfield to search data

    // Database to Store the database name
    // DBPassword to store the mysql password
    // School_Name to store the School name
    String Database, DBPassword, School_Name;

    // Table to store the Student Academic details
    private JTable tableData;

    // default table model
    private DefaultTableModel model;

    // constructor
    AcademicView(String database, String password, String school) {
        Database = database;
        DBPassword = password;
        School_Name = school;
    }

    // method
    public void view() {
        try {
            // Create a frame
            frame = new JFrame();

            // Create Labels and set their properties
            JLabel label = new JLabel();
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setSize(1250, 100);
            label.setText("STUDENT'S  ACADEMIC  DETAILS");
            label.setFont(new Font("Serif", Font.BOLD, 50));

            JLabel lab1 = new JLabel("Search Data");
            lab1.setBounds(450, 100, 150, 30);
            lab1.setFont(new Font("Serif", Font.PLAIN, 25));

            // create a TextField
            text1 = new JTextField();
            text1.setFont(new Font("Serif", Font.PLAIN, 25));
            text1.setBounds(620, 100, 300, 30);

            // Create button back and add to frame
            back = new JButton("Back");
            back.setBounds(30, 30, 100, 30);
            frame.add(back);

            // Add labels and textfield to frame
            frame.add(label);
            frame.add(lab1);
            frame.add(text1);

            // connect app to mysql to fetch the Student Academic details
            String url = "jdbc:mysql://localhost:3306/" + Database;
            String user = "root";

            Connection connection = DriverManager.getConnection(url, user, DBPassword);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "select a.Serial_No, a.Student_Id,a.Student_Name,a.Class,a.Subjects,a.Session,a.fee,e.Enrollment_No, e.Enrolled_Class from Academic as a ,Enrollment as e where a.Student_Id = e.Student_Id;");
            ResultSetMetaData rsmd = resultSet.getMetaData();

            // create a default table model
            model = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false; // All cells are non-editable
                }
            };

            // store the column name into an array named colname
            int columns = rsmd.getColumnCount(); // count the no of columns in a table of mysql database
            final String[] colname = new String[columns];
            for (int i = 0; i < columns; i++) {
                colname[i] = rsmd.getColumnName(i + 1);
            }

            // set the column name to the model
            model.setColumnIdentifiers(colname);

            // store the rows to an array named row
            while (resultSet.next()) {
                String[] row = new String[columns];
                for (int i = 0; i < columns; i++) {
                    row[i] = resultSet.getString(i + 1);
                }
                // add rows to default table model
                model.addRow(row);
            }

            // Create table using default table model
            tableData = new JTable(model);

            // set the column width of table
            int[] columnWidths = { 200,200, 200, 200, 500, 200, 200, 200,200 };
            for (int i = 0; i < columns; i++) {
                TableColumn column = tableData.getColumnModel().getColumn(i);
                column.setPreferredWidth(columnWidths[i]);
            }

            tableData.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // change the auto resize properties of table .

            // create Scrollpane and add table to it set its properties;

            JScrollPane sp = new JScrollPane(tableData);
            sp.setBounds(30, 150, 1200, 500);

            sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

            // add scrollpane to frame
            frame.add(sp);

            statement.close();
            connection.close();

        } catch (Exception a) {
            a.printStackTrace();
        }

        // Set the properties of frame
        frame.setSize(1500, 800); // optional
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.YELLOW);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);

        // Register the back button with action listener
        back.addActionListener(this);

        // Register the KeyListener with key Listener
        text1.addKeyListener(this);
    }

    // All logics for this class are inside these 4 methods

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back) {
            new StudentData(Database, DBPassword, School_Name);
            frame.dispose();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        TableRowSorter<DefaultTableModel> obj = new TableRowSorter<>(model);
        tableData.setRowSorter(obj);
        obj.setRowFilter(RowFilter.regexFilter(text1.getText()));
    }

    @Override
    public void keyTyped(KeyEvent e) {
        TableRowSorter<DefaultTableModel> obj = new TableRowSorter<>(model);
        tableData.setRowSorter(obj);
        obj.setRowFilter(RowFilter.regexFilter(text1.getText()));
    }

    @Override
    public void keyReleased(KeyEvent e) {
        TableRowSorter<DefaultTableModel> obj = new TableRowSorter<>(model);
        tableData.setRowSorter(obj);
        obj.setRowFilter(RowFilter.regexFilter(text1.getText()));
    }

}

/*
 * This class provides an user interface that allows the user to view Student
 * Fee details.
 * This class extends ActionListener and KeyListener for event handling.
 */

class FeeView implements ActionListener, KeyListener {

    JFrame frame; // frame in which all other components are added.

    JButton back; // back button for redirects the user to the Student Data page.

    JTextField text1; // textfield to search data.

    // Database to Store the database name
    // DBPassword to store the mysql password
    // School_Name to store the School name
    String Database, DBPassword, School_Name;

    private JTable tableData; // table in which all the data stored

    private DefaultTableModel model; // default table model

    // constructor
    FeeView(String database, String password, String school) {
        Database = database;
        DBPassword = password;
        School_Name = school;
    }

    // method
    public void view() {
        try {
            // Create a frame
            frame = new JFrame();

            // Create Labels and set their properties
            JLabel label = new JLabel();
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setSize(1250, 100);
            label.setText("STUDENT'S  FEE  DETAILS");
            label.setFont(new Font("Serif", Font.BOLD, 50));

            JLabel lab1 = new JLabel("Search Data");
            lab1.setBounds(450, 100, 150, 30);
            lab1.setFont(new Font("Serif", Font.PLAIN, 25));

            // create TextField and set their properties
            text1 = new JTextField();
            text1.setFont(new Font("Serif", Font.PLAIN, 25));
            text1.setBounds(620, 100, 300, 30);

            // Create button back and add to frame
            back = new JButton("Back");
            back.setBounds(30, 30, 100, 30);
            frame.add(back);

            // Add labels and textfield to frame
            frame.add(label);
            frame.add(lab1);
            frame.add(text1);

            // connect app to mysql to fetch the Student fee details.
            String url = "jdbc:mysql://localhost:3306/" + Database;
            String user = "root";

            Connection connection = DriverManager.getConnection(url, user, DBPassword);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from Fee_Details;");
            ResultSetMetaData rsmd = resultSet.getMetaData();

            // Create default table model
            model = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false; // All cells are non-editable
                }
            };

            // store the columns into an array named colname
            int columns = rsmd.getColumnCount();
            final String[] colname = new String[columns];
            for (int i = 0; i < columns; i++) {
                colname[i] = rsmd.getColumnName(i + 1);
            }

            // set the column name into the default table model
            model.setColumnIdentifiers(colname);

            // store rows in arary named row
            while (resultSet.next()) {
                // For each row, a new String array row is created to hold the data for that
                // row.
                String[] row = new String[columns];
                for (int i = 0; i < columns; i++) {
                    row[i] = resultSet.getString(i + 1);
                }
                model.addRow(row); // add rows into the Default table model
            }

            // create a table using default table model
            tableData = new JTable(model);

            // Increase the column width of table
            int[] columnWidths = { 200,200, 200, 200, 300, 200, 200, 200 };
            for (int i = 0; i < columns; i++) {
                TableColumn column = tableData.getColumnModel().getColumn(i);
                column.setPreferredWidth(columnWidths[i]);
            }

            tableData.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // change Auto Resize property of table
            // create JSrollPane and add table to it and set its properties
            JScrollPane sp = new JScrollPane(tableData);
            sp.setBounds(30, 150, 1200, 500);

            sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

            // add Scrollpane to frame
            frame.add(sp);

            statement.close();
            connection.close();

        } catch (Exception a) {
            a.printStackTrace();
        }

        // Set the properties of frame
        frame.setSize(1500, 800); // optional
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.YELLOW);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);

        // Register the back button with action listener and text1 textfiled with
        // keylistener
        back.addActionListener(this);
        text1.addKeyListener(this);

    }

    // All logics for this class are inside these 4 methods.
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back) {
            new StudentData(Database, DBPassword, School_Name);
            frame.dispose();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        TableRowSorter<DefaultTableModel> obj = new TableRowSorter<>(model);
        tableData.setRowSorter(obj);
        obj.setRowFilter(RowFilter.regexFilter(text1.getText()));
    }

    @Override
    public void keyTyped(KeyEvent e) {
        TableRowSorter<DefaultTableModel> obj = new TableRowSorter<>(model);
        tableData.setRowSorter(obj);
        obj.setRowFilter(RowFilter.regexFilter(text1.getText()));
    }

    @Override
    public void keyReleased(KeyEvent e) {
        TableRowSorter<DefaultTableModel> obj = new TableRowSorter<>(model);
        tableData.setRowSorter(obj);
        obj.setRowFilter(RowFilter.regexFilter(text1.getText()));
    }

}

/*
 * This class provides an user interface that contains three buttons which
 * allows the user to view Teacher details when they clicked.
 * This class extends ActionListener for event handling.
 */

class TeacherData implements ActionListener {

    JFrame frame; // frame in which all other components are added.

    // Buttons :
    // button to show Teacher Basic details,
    // button1 to show Teacher's Salary Structure details,
    // button 2 to show the Teacher's Salary Details,
    // back for redirect the user to Home page of App
    JButton button, button1, button2, back;

    // Database to Store the database name
    // DBPassword to store the mysql password
    // School_Name to store the School name
    String Database, DBPassword, School_Name;

    // constructor
    TeacherData(String database, String Password, String School) {

        Database = database;
        DBPassword = Password;
        School_Name = School;

        // Create a frame
        frame = new JFrame();

        // Create a label and set its properties

        JLabel label = new JLabel();
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setSize(1250, 100);
        label.setText(School_Name);
        label.setFont(new Font("Serif", Font.BOLD, 50));

        // create buttons and add to frame

        button = new JButton("View Teacher Basic Details");
        button.setBounds(480, 150, 350, 30);
        frame.add(button);

        button1 = new JButton("View Teacher Salary Structure ");
        button1.setBounds(480, 200, 350, 30);
        frame.add(button1);

        button2 = new JButton("View Teacher Salary Details");
        button2.setBounds(480, 250, 350, 30);
        frame.add(button2);

        back = new JButton("Back");
        back.setBounds(30, 30, 100, 30);
        frame.add(back);

        // Here add label in frame
        frame.add(label);

        // set the properties of frame
        frame.setSize(500, 500); // Optional
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.YELLOW);
        frame.setVisible(true);

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Registraton of buttons with action listener
        button.addActionListener(this);
        button1.addActionListener(this);
        button2.addActionListener(this);
        back.addActionListener(this);

    }

    // All logics for this class are inside this method.

    // @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == button) {

            TeacherView tv = new TeacherView(Database, DBPassword, School_Name);
            tv.view();
            frame.dispose();

        }

        if (e.getSource() == button1) {

            SalaryStructureView sv1 = new SalaryStructureView(Database, DBPassword, School_Name);
            sv1.view();
            frame.dispose();
        }
        if (e.getSource() == button2) {

            SalaryView sv2 = new SalaryView(Database, DBPassword, School_Name);
            sv2.view();
            frame.dispose();
        }
        if (e.getSource() == back) {

            new First(Database, DBPassword, School_Name);
            frame.dispose();
        }

    }
}

/*
 * This class provides an user interface that allows the user to view Teacher
 * Basic details.
 * This class extends ActionListener and KeyListener for event handling.
 */

class TeacherView implements ActionListener, KeyListener {

    JFrame frame; // frame in which all other components are added

    JButton back; // back buttons for redirect the user to Teacher Data page

    JTextField text1; // textfield for search data

    // Database to Store the database name
    // DBPassword to store the mysql password
    // School_Name to store the School name
    String Database, DBPassword, School_Name;

    private JTable tableData; // table to store teacher data.

    private DefaultTableModel model; // default table model

    // constructor
    TeacherView(String database, String password, String school) {
        Database = database;
        DBPassword = password;
        School_Name = school;
    }

    // method
    public void view() {
        try {
            // Create a frame
            frame = new JFrame();

            // Create Labels and set their properties
            JLabel label = new JLabel();
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setSize(1250, 100);
            label.setText("TEACHER'S  BASIC  DETAILS");
            label.setFont(new Font("Serif", Font.BOLD, 50));

            JLabel lab1 = new JLabel("Search Data");
            lab1.setBounds(450, 100, 150, 30);
            lab1.setFont(new Font("Serif", Font.PLAIN, 25));

            // create TextFields
            text1 = new JTextField();
            text1.setFont(new Font("Serif", Font.PLAIN, 25));
            text1.setBounds(620, 100, 300, 30);

            // Create button back and add to frame
            back = new JButton("Back");
            back.setBounds(30, 30, 100, 30);
            frame.add(back);

            // Add labels and textfield to frame
            frame.add(label);
            frame.add(lab1);
            frame.add(text1);

            // connect app to mysql to fetch Teacher basic details
            String url = "jdbc:mysql://localhost:3306/" + Database;
            String user = "root";

            Connection connection = DriverManager.getConnection(url, user, DBPassword);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select * from Teacher_Registration;");
            ResultSetMetaData rsmd = resultSet.getMetaData();

            // Create default table model
            model = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false; // All cells are non-editable
                }
            };

            // store column name to an array named colname
            int columns = rsmd.getColumnCount();
            final String[] colname = new String[columns];
            for (int i = 0; i < columns; i++) {
                colname[i] = rsmd.getColumnName(i + 1);
            }

            // set column name to default table model
            model.setColumnIdentifiers(colname);

            // store rows to an array named row
            while (resultSet.next()) {
                String[] row = new String[columns];
                for (int i = 0; i < columns; i++) {
                    row[i] = resultSet.getString(i + 1);
                }
                // add rows to default table model
                model.addRow(row);
            }

            // create table using default table model
            tableData = new JTable(model);

            // set the column width of table
            int[] columnWidths = { 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 250, 250, 250, 200, 200, 250 };
            for (int i = 0; i < columns; i++) {
                TableColumn column = tableData.getColumnModel().getColumn(i);
                column.setPreferredWidth(columnWidths[i]);
            }

            tableData.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // change the auto resize property of table.
            // create JScrollPane and add table to it and set its properties
            JScrollPane sp = new JScrollPane(tableData);
            sp.setBounds(30, 150, 1200, 500);
            sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

            // add JScrollPane to frame
            frame.add(sp);

            statement.close();
            connection.close();

        } catch (Exception a) {
            a.printStackTrace();
        }

        // Set the properties of frame
        frame.setSize(1500, 800); // optional
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.YELLOW);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);

        // Register the back button with action listener
        back.addActionListener(this);

        // Register the text with the key listener
        text1.addKeyListener(this);

    }

    // All logics for this class are inside these 4 method.

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back) {
            new TeacherData(Database, DBPassword, School_Name);
            frame.dispose();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        TableRowSorter<DefaultTableModel> obj = new TableRowSorter<>(model);
        tableData.setRowSorter(obj);
        obj.setRowFilter(RowFilter.regexFilter(text1.getText()));
    }

    @Override
    public void keyTyped(KeyEvent e) {
        TableRowSorter<DefaultTableModel> obj = new TableRowSorter<>(model);
        tableData.setRowSorter(obj);
        obj.setRowFilter(RowFilter.regexFilter(text1.getText()));
    }

    @Override
    public void keyReleased(KeyEvent e) {
        TableRowSorter<DefaultTableModel> obj = new TableRowSorter<>(model);
        tableData.setRowSorter(obj);
        obj.setRowFilter(RowFilter.regexFilter(text1.getText()));
    }
}

/*
 * This class provides an user interface that allows the user to view Teacher
 * Salary Structure details.
 * This class extends ActionListener and KeyListener for event handling.
 */

class SalaryStructureView implements ActionListener, KeyListener {

    JFrame frame; // frame in which all other components are added

    JButton back; // back buttons for redirect the user to Teacher Data page

    JTextField text1; // textfield for search data

    // Database to Store the database name
    // DBPassword to store the mysql password
    // School_Name to store the School name
    String Database, DBPassword, School_Name;

    private JTable tableData; // table to store Teacher Salary Structure details.

    private DefaultTableModel model; // default table model

    // constructor
    SalaryStructureView(String database, String password, String school) {
        Database = database;
        DBPassword = password;
        School_Name = school;
    }

    // method
    public void view() {
        try {
            // Create a frame
            frame = new JFrame();

            // Create Labels and set its properties
            JLabel label = new JLabel();
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setSize(1250, 100);
            label.setText("TEACHER'S  SALARY  STRUCTURE");
            label.setFont(new Font("Serif", Font.BOLD, 50));

            JLabel lab1 = new JLabel("Search Data");
            lab1.setBounds(450, 100, 150, 30);
            lab1.setFont(new Font("Serif", Font.PLAIN, 25));

            // create TextFields
            text1 = new JTextField();
            text1.setFont(new Font("Serif", Font.PLAIN, 25));
            text1.setBounds(620, 100, 300, 30);

            // Create button back and add to frame
            back = new JButton("Back");
            back.setBounds(30, 30, 100, 30);
            frame.add(back);

            // Add labels and textfield to frame
            frame.add(label);
            frame.add(lab1);
            frame.add(text1);

            // connect app to mysql to fetch Teacher Salary Structure
            String url = "jdbc:mysql://localhost:3306/" + Database;
            String user = "root";

            Connection connection = DriverManager.getConnection(url, user, DBPassword);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from Teacher_Salary_Structure;");
            ResultSetMetaData rsmd = resultSet.getMetaData();

            // create a default table model
            model = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false; // All cells are non-editable
                }
            };

            // store the column name into an array named colname
            int columns = rsmd.getColumnCount();
            final String[] colname = new String[columns];
            for (int i = 0; i < columns; i++) {
                colname[i] = rsmd.getColumnName(i + 1);
            }

            // set the column name to the default model
            model.setColumnIdentifiers(colname);

            // store rows into an array named row
            while (resultSet.next()) {
                final String[] row = new String[columns];
                for (int i = 0; i < columns; i++) {
                    row[i] = resultSet.getString(i + 1);
                }
                // add rows to default table model
                model.addRow(row);
            }

            // create a table
            tableData = new JTable(model);

            // set the column width of table
            int[] columnWidths = { 200,200, 200, 200, 500, 200 };
            for (int i = 0; i < columns; i++) {
                TableColumn column = tableData.getColumnModel().getColumn(i);
                column.setPreferredWidth(columnWidths[i]);
            }

            tableData.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // change auto resize property of table

            // create JScrollPane add table to it and set its properties
            JScrollPane sp = new JScrollPane(tableData);
            sp.setBounds(30, 150, 1200, 500);

            sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

            // add Scrollpane to frame
            frame.add(sp);

            statement.close();
            connection.close();

        } catch (Exception a) {
            a.printStackTrace();
        }

        // Set the properties of frame
        frame.setSize(1500, 800); // optional
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.YELLOW);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);

        // Register the back button with action listener
        back.addActionListener(this);

        // Register the textfield text1 with key listener
        text1.addKeyListener(this);

    }

    // All logics for this class are inside these 4 methods
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back) {
            new TeacherData(Database, DBPassword, School_Name);
            frame.dispose();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        TableRowSorter<DefaultTableModel> obj = new TableRowSorter<>(model);
        tableData.setRowSorter(obj);
        obj.setRowFilter(RowFilter.regexFilter(text1.getText()));
    }

    @Override
    public void keyTyped(KeyEvent e) {
        TableRowSorter<DefaultTableModel> obj = new TableRowSorter<>(model);
        tableData.setRowSorter(obj);
        obj.setRowFilter(RowFilter.regexFilter(text1.getText()));
    }

    @Override
    public void keyReleased(KeyEvent e) {
        TableRowSorter<DefaultTableModel> obj = new TableRowSorter<>(model);
        tableData.setRowSorter(obj);
        obj.setRowFilter(RowFilter.regexFilter(text1.getText()));
    }
}

/*
 * This class provides an user interface that allows the user to view Teacher
 * Salary details.
 * This class extends ActionListener and KeyListener for event handling.
 */
class SalaryView implements ActionListener, KeyListener {

    JFrame frame; // frame in which all other components are added

    JButton back; // back button for redirect the user to Teacher data page

    JTextField text1; // textfield to search data

    // Database to Store the database name
    // DBPassword to store the mysql password
    // School_Name to store the School name
    String Database, DBPassword, School_Name;

    private JTable tableData; // table to store the teacher Salary details

    private DefaultTableModel model; // default table model

    // constructor
    SalaryView(String database, String password, String school) {
        Database = database;
        DBPassword = password;
        School_Name = school;
    }

    // method
    public void view() {
        try {
            // Create a frame
            frame = new JFrame();

            // Create Labels and set their properties
            JLabel label = new JLabel();
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setSize(1250, 100);
            label.setText("TEACHER'S  SALARY  DETAILS");
            label.setFont(new Font("Serif", Font.BOLD, 50));

            JLabel lab1 = new JLabel("Search Data");
            lab1.setBounds(450, 100, 150, 30);
            lab1.setFont(new Font("Serif", Font.PLAIN, 25));

            // create TextField
            text1 = new JTextField();
            text1.setFont(new Font("Serif", Font.PLAIN, 25));
            text1.setBounds(620, 100, 300, 30);

            // Create button back and add to frame
            back = new JButton("Back");
            back.setBounds(30, 30, 100, 30);
            frame.add(back);

            // Add labels and textfield to frame
            frame.add(label);
            frame.add(lab1);
            frame.add(text1);

            // connect app to mysql to fetch student data
            String url = "jdbc:mysql://localhost:3306/" + Database;
            String user = "root";

            Connection connection = DriverManager.getConnection(url, user, DBPassword);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from Teacher_Salary_Details;");
            ResultSetMetaData rsmd = resultSet.getMetaData();

            // Create default table model
            model = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false; // All cells are non-editable
                }
            };

            // store the column to an array named colname
            int columns = rsmd.getColumnCount();
            final String[] colname = new String[columns];
            for (int i = 0; i < columns; i++) {
                colname[i] = rsmd.getColumnName(i + 1);
            }

            // set the columns to default table model
            model.setColumnIdentifiers(colname);

            // store the rows to an array named row
            while (resultSet.next()) {
                final String[] row = new String[columns];
                for (int i = 0; i < columns; i++) {
                    row[i] = resultSet.getString(i + 1);
                }

                // add rows to default table model
                model.addRow(row);
            }

            // create table using default table model
            tableData = new JTable(model);

            // set the column width of the table
            int[] columnWidths = { 200,200, 200, 200, 500, 200 };

            for (int i = 0; i < columns; i++) {
                TableColumn column = tableData.getColumnModel().getColumn(i);
                column.setPreferredWidth(columnWidths[i]);
            }

            tableData.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // change the auto resize property of table

            // create a JScrollPane and add table to it and set its properties
            JScrollPane sp = new JScrollPane(tableData);

            sp.setBounds(30, 150, 1200, 500);

            sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

            // add JScrollpane to frame
            frame.add(sp);

            statement.close();
            connection.close();

        } catch (Exception a) {
            a.printStackTrace();
        }

        // Set the properties of frame
        frame.setSize(1500, 800); // optional
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.YELLOW);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);

        // Register the back button with action listener
        back.addActionListener(this);

        // Register the textfield with KeyListener
        text1.addKeyListener(this);

    }

    // All logics for this class are inside these 4 methods
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back) {
            new TeacherData(Database, DBPassword, School_Name);
            frame.dispose();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        TableRowSorter<DefaultTableModel> obj = new TableRowSorter<>(model);
        tableData.setRowSorter(obj);
        obj.setRowFilter(RowFilter.regexFilter(text1.getText()));
    }

    @Override
    public void keyTyped(KeyEvent e) {
        TableRowSorter<DefaultTableModel> obj = new TableRowSorter<>(model);
        tableData.setRowSorter(obj);
        obj.setRowFilter(RowFilter.regexFilter(text1.getText()));
    }

    @Override
    public void keyReleased(KeyEvent e) {
        TableRowSorter<DefaultTableModel> obj = new TableRowSorter<>(model);
        tableData.setRowSorter(obj);
        obj.setRowFilter(RowFilter.regexFilter(text1.getText()));
    }
}

/*
 * This class provides an user interface that contains three buttons which
 * allows the user to view Employee details when they clicked.
 * This class extends ActionListener for event handling.
 */

class EmployeeData implements ActionListener {

    JFrame frame; // frame in which all other components are added.

    // Buttons :
    // button to show Employee Basic details,
    // button1 to show Employee's Salary Structure details,
    // button 2 to show the Employee's Salary Details,
    // back for redirect the user to Home page of App
    JButton button, button1, button2, back;

    // Database to Store the database name
    // DBPassword to store the mysql password
    // School_Name to store the School name
    String Database, DBPassword, School_Name;

    // constructor
    EmployeeData(String database, String Password, String School) {

        Database = database;
        DBPassword = Password;
        School_Name = School;

        // Create a frame
        frame = new JFrame();

        // Create a label and set its properties

        JLabel label = new JLabel();
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setSize(1250, 100);
        label.setText(School_Name);
        label.setFont(new Font("Serif", Font.BOLD, 50));

        // create buttons and add to frame

        button = new JButton("View Employee Basic Details");
        button.setBounds(480, 150, 350, 30);
        frame.add(button);

        button1 = new JButton("View Employee Salary Structure ");
        button1.setBounds(480, 200, 350, 30);
        frame.add(button1);

        button2 = new JButton("View Employee Salary Details");
        button2.setBounds(480, 250, 350, 30);
        frame.add(button2);

        back = new JButton("Back");
        back.setBounds(30, 30, 100, 30);
        frame.add(back);

        // Here add label in frame
        frame.add(label);

        // set the properties of frame
        frame.setSize(500, 500); /// optional
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.YELLOW);
        frame.setVisible(true);

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Registraton of buttons with action listener
        button.addActionListener(this);
        button1.addActionListener(this);
        button2.addActionListener(this);

        back.addActionListener(this);

    }

    // All logics for this class are inside this method
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == button) {

            EmployeeView tv = new EmployeeView(Database, DBPassword, School_Name);
            tv.view();
            frame.dispose();

        }

        if (e.getSource() == button1) {

            EmployeeSalaryStructureView sv = new EmployeeSalaryStructureView(Database, DBPassword, School_Name);
            sv.view();
            frame.dispose();
        }
        if (e.getSource() == button2) {

            EmployeeSalaryView sv = new EmployeeSalaryView(Database, DBPassword, School_Name);
            sv.view();
            frame.dispose();
        }
        if (e.getSource() == back) {

            new First(Database, DBPassword, School_Name);
            frame.dispose();
        }

    }
}

/*
 * This class provides an user interface that allows the user to view Employee
 * Basic details.
 * This class extends ActionListener and KeyListener for event handling.
 */

class EmployeeView implements ActionListener, KeyListener {

    JFrame frame; // frame in which all other components are added

    JButton back; // back buttons for redirect the user to Teacher Data page

    JTextField text1; // textfield for search data

    // Database to Store the database name
    // DBPassword to store the mysql password
    // School_Name to store the School name
    String Database, DBPassword, School_Name;

    private JTable tableData; // table to store teacher data.

    private DefaultTableModel model; // default table model

    // constructor
    EmployeeView(String database, String password, String school) {
        Database = database;
        DBPassword = password;
        School_Name = school;
    }

    // method
    public void view() {
        try {
            // Create a frame
            frame = new JFrame();

            // Create Labels and set its properties
            JLabel label = new JLabel();
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setSize(1250, 100);
            label.setText("EMPLOYEE'S  BASIC  DETAILS");
            label.setFont(new Font("Serif", Font.BOLD, 50));

            JLabel lab1 = new JLabel("Search Data");
            lab1.setBounds(450, 100, 150, 30);
            lab1.setFont(new Font("Serif", Font.PLAIN, 25));

            // create TextField
            text1 = new JTextField();
            text1.setFont(new Font("Serif", Font.PLAIN, 25));
            text1.setBounds(620, 100, 300, 30);

            // Create button back and add to frame
            back = new JButton("Back");
            back.setBounds(30, 30, 100, 30);
            frame.add(back);

            // Add label and textfield to frame
            frame.add(label);
            frame.add(lab1);
            frame.add(text1);

            // connect app to mysql to fetch Employee Basic details
            String url = "jdbc:mysql://localhost:3306/" + Database;
            String user = "root";

            Connection connection = DriverManager.getConnection(url, user, DBPassword);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select * from Employee_Registration;");
            ResultSetMetaData rsmd = resultSet.getMetaData();

            // Create default table model
            model = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false; // All cells are non-editable
                }
            };

            // store the column name into an array named colname
            int columns = rsmd.getColumnCount();
            final String[] colname = new String[columns];
            for (int i = 0; i < columns; i++) {
                colname[i] = rsmd.getColumnName(i + 1);
            }

            // set name of columns to default table model
            model.setColumnIdentifiers(colname);

            // store the row to an arary named rows
            while (resultSet.next()) {
                final String[] row = new String[columns];
                for (int i = 0; i < columns; i++) {
                    row[i] = resultSet.getString(i + 1);
                }
                // add rows to default table model
                model.addRow(row);
            }

            // create table using default table model
            tableData = new JTable(model);

            // set the column width of the table
            int[] columnWidths = { 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 250, 250, 250, 200, 200, 250 };

            for (int i = 0; i < columns; i++) {
                TableColumn column = tableData.getColumnModel().getColumn(i);
                column.setPreferredWidth(columnWidths[i]);
            }

            tableData.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // change the auto resize property of table

            // Create JScrollPane and add table to it and set its properties
            JScrollPane sp = new JScrollPane(tableData);
            sp.setBounds(30, 150, 1200, 500);

            sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

            // add JScrollPane to frame
            frame.add(sp);

            statement.close();
            connection.close();

        } catch (Exception a) {
            a.printStackTrace();
        }

        // Set the properties of frame
        frame.setSize(1500, 800); // optional
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.YELLOW);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);

        // Register the back button with action listener
        back.addActionListener(this);
        // Register the textfield with keylistener
        text1.addKeyListener(this);

    }

    // All logics for this class are inside these 4 method

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back) {
            new EmployeeData(Database, DBPassword, School_Name);
            frame.dispose();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        TableRowSorter<DefaultTableModel> obj = new TableRowSorter<>(model);
        tableData.setRowSorter(obj);
        obj.setRowFilter(RowFilter.regexFilter(text1.getText()));
    }

    @Override
    public void keyTyped(KeyEvent e) {
        TableRowSorter<DefaultTableModel> obj = new TableRowSorter<>(model);
        tableData.setRowSorter(obj);
        obj.setRowFilter(RowFilter.regexFilter(text1.getText()));
    }

    @Override
    public void keyReleased(KeyEvent e) {
        TableRowSorter<DefaultTableModel> obj = new TableRowSorter<>(model);
        tableData.setRowSorter(obj);
        obj.setRowFilter(RowFilter.regexFilter(text1.getText()));
    }
}

/*
 * This class provides an user interface that allows the user to view Employee
 * Salary Structure details.
 * This class extends ActionListener and KeyListener for event handling.
 */

class EmployeeSalaryStructureView implements ActionListener, KeyListener {

    JFrame frame; // frame in which all other components are added

    JButton back; // back buttons for redirect the user to Teacher Data page

    JTextField text1; // textfield for search data

    // Database to Store the database name
    // DBPassword to store the mysql password
    // School_Name to store the School name
    String Database, DBPassword, School_Name;

    private JTable tableData; // table to store Teacher Salary Structure details.

    private DefaultTableModel model; // default table model

    // constructor
    EmployeeSalaryStructureView(String database, String password, String school) {
        Database = database;
        DBPassword = password;
        School_Name = school;
    }

    // method
    public void view() {
        try {
            // Create a frame
            frame = new JFrame();

            // Create Labels and set their properties
            JLabel label = new JLabel();
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setSize(1250, 100);
            label.setText("EMPLOYEE'S  SALARY  STRUCTURE");
            label.setFont(new Font("Serif", Font.BOLD, 50));

            JLabel lab1 = new JLabel("Search Data");
            lab1.setBounds(450, 100, 150, 30);
            lab1.setFont(new Font("Serif", Font.PLAIN, 25));

            // create TextField
            text1 = new JTextField();
            text1.setFont(new Font("Serif", Font.PLAIN, 25));
            text1.setBounds(620, 100, 300, 30);

            // Create button back and add to frame
            back = new JButton("Back");
            back.setBounds(30, 30, 100, 30);
            frame.add(back);

            // Add labels and textfield to frame
            frame.add(label);
            frame.add(lab1);
            frame.add(text1);

            // connect app to mysql to fetch Employee Salary Structure details
            String url = "jdbc:mysql://localhost:3306/" + Database;
            String user = "root";

            Connection connection = DriverManager.getConnection(url, user, DBPassword);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from Employee_Salary_Structure;");
            ResultSetMetaData rsmd = resultSet.getMetaData();

            // Create default table model
            model = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false; // All cells are non-editable
                }
            };

            // store the column name to an array named colname
            int columns = rsmd.getColumnCount();
            final String[] colname = new String[columns];
            for (int i = 0; i < columns; i++) {
                colname[i] = rsmd.getColumnName(i + 1);
            }

            // set the column name to default table model
            model.setColumnIdentifiers(colname);

            // store the rows to an array named rows
            while (resultSet.next()) {
                final String[] row = new String[columns];
                for (int i = 0; i < columns; i++) {
                    row[i] = resultSet.getString(i + 1);
                }
                // add rows to default table model
                model.addRow(row);
            }

            // create a table using default table model
            tableData = new JTable(model);

            // set the column width of the table
            int[] columnWidths = {200, 200, 200, 200, 500, 200 };

            for (int i = 0; i < columns; i++) {
                TableColumn column = tableData.getColumnModel().getColumn(i);
                column.setPreferredWidth(columnWidths[i]);
            }

            tableData.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // change auto resize property of table

            // Create a JScrollPane and add table to it and set its properties
            JScrollPane sp = new JScrollPane(tableData);
            sp.setBounds(30, 150, 1200, 500);

            sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

            // add JScrollPane to frame
            frame.add(sp);

            statement.close();
            connection.close();

        } catch (Exception a) {
            a.printStackTrace();
        }

        // Set the properties of frame
        frame.setSize(1500, 800); // optional
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.YELLOW);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);

        // Register the back button with action listener
        back.addActionListener(this);

        // Register the textfield with keylistener
        text1.addKeyListener(this);

    }

    // all logics for this class are inside these 4 methods

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back) {
            new EmployeeData(Database, DBPassword, School_Name);
            frame.dispose();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        TableRowSorter<DefaultTableModel> obj = new TableRowSorter<>(model);
        tableData.setRowSorter(obj);
        obj.setRowFilter(RowFilter.regexFilter(text1.getText()));
    }

    @Override
    public void keyTyped(KeyEvent e) {
        TableRowSorter<DefaultTableModel> obj = new TableRowSorter<>(model);
        tableData.setRowSorter(obj);
        obj.setRowFilter(RowFilter.regexFilter(text1.getText()));
    }

    @Override
    public void keyReleased(KeyEvent e) {
        TableRowSorter<DefaultTableModel> obj = new TableRowSorter<>(model);
        tableData.setRowSorter(obj);
        obj.setRowFilter(RowFilter.regexFilter(text1.getText()));
    }
}

/*
 * This class provides an user interface that allows the user to view Employee
 * Salary Structure details.
 * This class extends ActionListener and KeyListener for event handling.
 */

class EmployeeSalaryView implements ActionListener, KeyListener {

    JFrame frame; // frame in which all other components are added

    JButton back; // back button for redirect the user to Teacher data page

    JTextField text1; // textfield to search data

    // Database to Store the database name
    // DBPassword to store the mysql password
    // School_Name to store the School name
    String Database, DBPassword, School_Name;

    private JTable tableData; // table to store the teacher Salary details

    private DefaultTableModel model; // default table model

    // constructor
    EmployeeSalaryView(String database, String password, String school) {
        Database = database;
        DBPassword = password;
        School_Name = school;
    }

    // method
    public void view() {
        try {
            // Create a frame
            frame = new JFrame();

            // Create Labels and set their properties
            JLabel label = new JLabel();
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setSize(1250, 100);
            label.setText("EMPLOYEE'S  SALARY  DETAILS");
            label.setFont(new Font("Serif", Font.BOLD, 50));

            JLabel lab1 = new JLabel("Search Data");
            lab1.setBounds(450, 100, 150, 30);
            lab1.setFont(new Font("Serif", Font.PLAIN, 25));

            // create TextField
            text1 = new JTextField();
            text1.setFont(new Font("Serif", Font.PLAIN, 25));
            text1.setBounds(620, 100, 300, 30);

            // Create button back and add to frame
            back = new JButton("Back");
            back.setBounds(30, 30, 100, 30);
            frame.add(back);

            // Add label and textfield to frame
            frame.add(label);
            frame.add(lab1);
            frame.add(text1);

            // connect app to mysql to fetch the Employee Salary details from database
            String url = "jdbc:mysql://localhost:3306/" + Database;
            String user = "root";

            Connection connection = DriverManager.getConnection(url, user, DBPassword);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * FROM Employee_Salary_Details;");
            ResultSetMetaData rsmd = resultSet.getMetaData();

            // Create default table model
            model = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false; // All cells are non-editable
                }
            };

            // store the column name to an array named colname
            int columns = rsmd.getColumnCount();
            final String[] colname = new String[columns];
            for (int i = 0; i < columns; i++) {
                colname[i] = rsmd.getColumnName(i + 1);
            }

            // set column name to default table model
            model.setColumnIdentifiers(colname);

            // store the rows to an array named rows
            while (resultSet.next()) {
                final String[] row = new String[columns];
                for (int i = 0; i < columns; i++) {
                    row[i] = resultSet.getString(i + 1);
                }

                // add rows to default table model
                model.addRow(row);
            }

            // create table using default table model
            tableData = new JTable(model);

            // set the column width of table
            int[] columnWidths = {200, 200, 200, 200, 500, 200 };
            for (int i = 0; i < columns; i++) {
                TableColumn column = tableData.getColumnModel().getColumn(i);
                column.setPreferredWidth(columnWidths[i]);
            }

            tableData.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // change Auto resize property of table

            // create a JScrollPane and add table to it and set its prperties
            JScrollPane sp = new JScrollPane(tableData);
            sp.setBounds(30, 150, 1200, 500);

            sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

            // add JScrollPane to frame
            frame.add(sp);

            statement.close();
            connection.close();

        } catch (Exception a) {
            a.printStackTrace();
        }

        // Set the properties of frame
        frame.setSize(1500, 800); // optional
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.YELLOW);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);

        // Register the back button with action listener
        back.addActionListener(this);

        // Register the textfiled text1 with the keyListener
        text1.addKeyListener(this);

    }

    // All logics are inside these 4 methods
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back) {
            new EmployeeData(Database, DBPassword, School_Name);
            frame.dispose();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        TableRowSorter<DefaultTableModel> obj = new TableRowSorter<>(model);
        tableData.setRowSorter(obj);
        obj.setRowFilter(RowFilter.regexFilter(text1.getText()));
    }

    @Override
    public void keyTyped(KeyEvent e) {
        TableRowSorter<DefaultTableModel> obj = new TableRowSorter<>(model);
        tableData.setRowSorter(obj);
        obj.setRowFilter(RowFilter.regexFilter(text1.getText()));
    }

    @Override
    public void keyReleased(KeyEvent e) {
        TableRowSorter<DefaultTableModel> obj = new TableRowSorter<>(model);
        tableData.setRowSorter(obj);
        obj.setRowFilter(RowFilter.regexFilter(text1.getText()));
    }
}


/*
 * This class provides an user interface that contains three buttons which
 * allows the user to update Student details when they clicked.
 * This class extends ActionListener for event handling.
 */

 class StudentDataUpdate implements ActionListener {

    JFrame frame; // frame in which all other components are added.

    // Buttons :
    // button to update Student Basic details,
    // button1 to update Student's Academic details,
    // button 2 to update the student Fee Details,
    // back for redirect the user to Home page of App
    JButton button, button1, button2, back;

    // Database to Store the database name
    // DBPassword to store the mysql password
    // School_Name to store the School name
    String Database, DBPassword, School_Name;

    // constructor
    StudentDataUpdate(String database, String Password, String School) {

        Database = database;
        DBPassword = Password;
        School_Name = School;

        // Create a frame
        frame = new JFrame();

        // Create a label and set its properties

        JLabel label = new JLabel();
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setSize(1250, 100);
        label.setText(School_Name);
        label.setFont(new Font("Serif", Font.BOLD, 50));

        // create buttons and add to frame

        button = new JButton("Update Student Basic and Enrollment Details");
        button.setBounds(420, 150, 410, 30);
        frame.add(button);

        button1 = new JButton("Update/Delete Student Academic Details ");
        button1.setBounds(420, 200, 410, 30);
        frame.add(button1);

        button2 = new JButton("Update/Delete Student Fee Details");
        button2.setBounds(420, 250, 410, 30);
        frame.add(button2);

        back = new JButton("Back");
        back.setBounds(30, 30, 100, 30);
        frame.add(back);

        // Here add label in frame
        frame.add(label);

        // set the properties of frame
        frame.setSize(500, 500);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.YELLOW);
        frame.setVisible(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Registraton of buttons with action listener
        button.addActionListener(this);
        button1.addActionListener(this);
        button2.addActionListener(this);
        back.addActionListener(this);

    }

    // All logics for this class are inside this method.
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == button) {

            new StudentUpdate(DBPassword, Database, DBPassword, School_Name);
            frame.dispose();

        }

        if (e.getSource() == button1) {

            new AcademicUpdate(null,Database, DBPassword, School_Name);
            
            frame.dispose();
        }
        if (e.getSource() == button2) {

            new FeeUpdate(null,Database, DBPassword, School_Name);
            frame.dispose();
        }
        if (e.getSource() == back) {

            new First(Database, DBPassword, School_Name);
            frame.dispose();
        }

    }
}








/*
 * This class provides an user interface that allows the user to update
 * Student Basic details .
 * This class extends ActionListenerfor event handling.
*/

class StudentUpdate extends PlaceholderTextField implements ActionListener {

    // Buttons :
    // sub to insert the Student basic details into the database,
    // clear for clear the information that are filled,
    // back for redirect the user to Home page of App
    JButton back, sub, clear;

    JFrame frame1; // frame in which all other components are added

    // TextField:
    // text allows the user to enter Student's Enrollment No,
    // text1 allows the user to enter Student ID,
    // text2 allows the user to enter Student Name,
    // text3 allows the user to enter Student's Father Name,
    // text4 allows the user to enter Student's Mother Name,
    // text5 allows the user to enter Student's Date of Birth,
    // ch1 allows the user to select Student's Gender,
    // text7 allows the user to enter Student's Mobile Number1 ,
    // text8 allows the user to enter Student's Mobile Number2,
    // text9 allows the user to enter Student's Registration Date,
    // text10 allows the user to enter Student's Address,
    // text11 allows the user to enter Student's Aadhaar No,
    // text12 allows the user to enter Student's Father Aadhaar No,
    // text13 allows the user to enter Student's Mother Aadhaar No,
    // text14 allows the user to enter Student's Family ID,
    // ch2 allows the user to select Student's Category ,
    // text15 allows the user to enter Student's Father's Occupation,
    // text16 allows the user to enter Student's Mother's Ocupation,
    // text17 allows the user to enter Student's School Leaving date
    // text18 allows the user to enter Student Enrolled class
    JTextField text, text1, text2, text3, text4, text5, text7, text8, text9, text10, text11, text12, text13, text14,
            text15, text16,text17,text18;

    final JComboBox<String> ch1; // JComboBox ch1 to select the gender of Student

    final JComboBox<String> ch2; // JCommboBox ch2 to select the category of Student

    boolean message = false; // for showing the message

    String[] gender = { "Male", "Female", "Other" };
    String[] category = { "General", "OBC", "SC/ST" };

    // Database to Store the database name
    // DBPassword to store the mysql password
    // School_Name to store the School name
    // placeholder for background text of textfield
    String placeholder, Database, DBPassword, School_Name;

    // Student_Id store the student id of student
    String Student_Id;

    // String
    // Enrollment store Student's Enrollment No,
    // Sname store Student Name,
    // Fname store Student's Father Name,
    // Mname store Student's Mother Name,
    // Dob store Student's Date of Birth,
    // gender store select Student's Gender,
    // Mob1 store Student's Mobile Number1 ,
    // Mob2 store Student's Mobile Number2,
    // RDate store Student's Registration Date,
    // Address store Student's Address,
    // student_Aadhaar_No store Student's Aadhaar No,
    // Father_Aadhaar_No store Student's Father Aadhaar No,
    // Mother_Aadhaar store Student's Mother Aadhaar No,
    // Family_Id store Student's Family ID,
    // Category store select Student's Category ,
    // Father_Occupation store Student's Father's Occupation,
    // Mother_Occupation store Student's Mother's Ocupation,
    // School_Leaving_Date store Student's School Leaving date

    String ENROLLMENT, Sname, Fname, Mname, Dob, Gender, Mob1, Mob2, RDate, Address, student_Aadhaar_No,
            Father_Aadhaar_No, Mother_Aadhaar_No, Family_Id, Category, Father_Occupation,
            Mother_Occupation, School_Leaving_Date,EnrolledClass;

    // constructor
    StudentUpdate(String placeholder, String database, String Password, String School) {
        super(placeholder);
        Database = database;
        DBPassword = Password;
        School_Name = School;

        // create a frame
        frame1 = new JFrame();

        // create labels and set their properties
        JLabel label1 = new JLabel();
        label1.setHorizontalAlignment(JLabel.CENTER);
        label1.setSize(1250, 100);
        label1.setText("UPDATE  STUDENT  DATA");
        label1.setFont(new Font("Serif", Font.BOLD, 50));

        JLabel label2 = new JLabel("Note : Admin can update student data using student id");
        label2.setFont(new Font("Serif", Font.PLAIN, 22));
        label2.setBounds(380, 75, 650, 25);

        JLabel lab = new JLabel("Student  ID :");
        lab.setFont(new Font("Serif", Font.PLAIN, 25));
        lab.setBounds(410, 110, 250, 30);

        JLabel lab1 = new JLabel("Enrollment NO :");
        lab1.setFont(new Font("Serif", Font.PLAIN, 25));
        lab1.setBounds(70, 160, 250, 30);

        JLabel lab2 = new JLabel("Student Name :");
        lab2.setFont(new Font("Serif", Font.PLAIN, 25));
        lab2.setBounds(70, 200, 250, 30);

        JLabel lab3 = new JLabel("Father Name :");
        lab3.setFont(new Font("Serif", Font.PLAIN, 25));
        lab3.setBounds(70, 240, 250, 30);

        JLabel lab4 = new JLabel("Mother Name :");
        lab4.setFont(new Font("Serif", Font.PLAIN, 25));
        lab4.setBounds(70, 280, 250, 30);

        JLabel lab5 = new JLabel("Date Of Birth :");
        lab5.setFont(new Font("Serif", Font.PLAIN, 25));
        lab5.setBounds(70, 320, 250, 30);

        JLabel lab6 = new JLabel("Gender :");
        lab6.setFont(new Font("Serif", Font.PLAIN, 25));
        lab6.setBounds(70, 360, 250, 30);

        JLabel lab7 = new JLabel("Mobile Number1 :");
        lab7.setFont(new Font("Serif", Font.PLAIN, 25));
        lab7.setBounds(70, 400, 250, 30);

        JLabel lab8 = new JLabel("Mobile Number2 :");
        lab8.setFont(new Font("Serif", Font.PLAIN, 25));
        lab8.setBounds(70, 440, 250, 30);

        JLabel lab9 = new JLabel("Registration Date :");
        lab9.setFont(new Font("Serif", Font.PLAIN, 25));
        lab9.setBounds(70, 480, 250, 30);

        JLabel lab10 = new JLabel("Address :");
        lab10.setFont(new Font("Serif", Font.PLAIN, 25));
        lab10.setBounds(70, 520, 250, 30);

        JLabel lab11 = new JLabel("Student's Aaadhar NO :");
        lab11.setFont(new Font("Serif", Font.PLAIN, 25));
        lab11.setBounds(700, 160, 250, 30);

        JLabel lab12 = new JLabel("Father's Aadhaar NO :");
        lab12.setFont(new Font("Serif", Font.PLAIN, 25));
        lab12.setBounds(700, 200, 250, 30);

        JLabel lab13 = new JLabel("Mother's Aadhaar NO :");
        lab13.setFont(new Font("Serif", Font.PLAIN, 25));
        lab13.setBounds(700, 240, 250, 30);

        JLabel lab14 = new JLabel("Family ID :");
        lab14.setFont(new Font("Serif", Font.PLAIN, 25));
        lab14.setBounds(700, 280, 250, 30);

        JLabel lab15 = new JLabel("Category :");
        lab15.setFont(new Font("Serif", Font.PLAIN, 25));
        lab15.setBounds(700, 320, 250, 30);

        JLabel lab16 = new JLabel("Father's Occupation:");
        lab16.setFont(new Font("Serif", Font.PLAIN, 25));
        lab16.setBounds(700, 360, 250, 30);

        JLabel lab17 = new JLabel("Mother's Occupation");
        lab17.setFont(new Font("Serif", Font.PLAIN, 25));
        lab17.setBounds(700, 400, 250, 30);

        JLabel lab19 = new JLabel("School Leaving Date");
        lab19.setFont(new Font("Serif", Font.PLAIN, 25));
        lab19.setBounds(700, 440, 250, 30);

        JLabel lab20 = new JLabel("Enrolled Class");
        lab20.setFont(new Font("Serif", Font.PLAIN, 25));
        lab20.setBounds(700, 480, 250, 30);


        // create TextField and set its properties
        text = new JTextField();
        text.setFont(new Font("Serif", Font.PLAIN, 25));
        text.setBounds(580, 115, 300, 30);

        text1 = new JTextField();
        text1.setFont(new Font("Serif", Font.PLAIN, 25));
        text1.setBounds(300, 165, 300, 30);

        text2 = new JTextField();
        text2.setFont(new Font("Serif", Font.PLAIN, 25));
        text2.setBounds(300, 205, 300, 30);

        text3 = new JTextField();
        text3.setFont(new Font("Serif", Font.PLAIN, 25));
        text3.setBounds(300, 245, 300, 30);

        text4 = new JTextField();
        text4.setFont(new Font("Serif", Font.PLAIN, 25));
        text4.setBounds(300, 285, 300, 30);

        text5 = new PlaceholderTextField("YYYY-MM-DD");
        text5.setFont(new Font("Serif", Font.PLAIN, 25));
        text5.setBounds(300, 325, 300, 30);

        ch1 = new JComboBox<>(gender); // ComboBox for gender
        ch1.setBounds(300, 365, 300, 30);
        ch1.setSelectedIndex(-1);

        text7 = new JTextField();
        text7.setFont(new Font("Serif", Font.PLAIN, 25));
        text7.setBounds(300, 405, 300, 30);

        text8 = new JTextField();
        text8.setFont(new Font("Serif", Font.PLAIN, 25));
        text8.setBounds(300, 445, 300, 30);

        text9 = new PlaceholderTextField("YYYY-MM-DD");
        text9.setFont(new Font("Serif", Font.PLAIN, 25));
        text9.setBounds(300, 485, 300, 30);

        text10 = new JTextField();
        text10.setFont(new Font("Serif", Font.PLAIN, 25));
        text10.setBounds(300, 525, 300, 30);

        text11 = new JTextField();
        text11.setFont(new Font("Serif", Font.PLAIN, 25));
        text11.setBounds(950, 165, 300, 30);

        text12 = new JTextField();
        text12.setFont(new Font("Serif", Font.PLAIN, 25));
        text12.setBounds(950, 205, 300, 30);

        text13 = new JTextField();
        text13.setFont(new Font("Serif", Font.PLAIN, 25));
        text13.setBounds(950, 245, 300, 30);

        text14 = new JTextField();
        text14.setFont(new Font("Serif", Font.PLAIN, 25));
        text14.setBounds(950, 285, 300, 30);

        ch2 = new JComboBox<>(category); // ComboBox for category
        ch2.setBounds(950, 325, 300, 30);
        ch2.setSelectedIndex(-1);

        text15 = new JTextField();
        text15.setFont(new Font("Serif", Font.PLAIN, 25));
        text15.setBounds(950, 365, 300, 30);

        text16 = new JTextField();
        text16.setFont(new Font("Serif", Font.PLAIN, 25));
        text16.setBounds(950, 405, 300, 30);

        text17 = new PlaceholderTextField("YYYY-MM-DD");
        text17.setFont(new Font("Serif", Font.PLAIN, 25));
        text17.setBounds(950, 445, 300, 30);

        text18 = new PlaceholderTextField("eg : 5th");
        text18.setFont(new Font("Serif", Font.PLAIN, 25));
        text18.setBounds(950, 485, 300, 30);

        // create buttons for submit ,clear and back

        sub = new JButton(" UPDATE ");
        sub.setBounds(400, 600, 200, 30);

        clear = new JButton(" CLEAR ");
        clear.setBounds(650, 600, 200, 30);

        

        back = new JButton("Back");
        back.setBounds(30, 30, 100, 30);

        // Here add labels in frame
        frame1.add(label1);
        frame1.add(label2);
        frame1.add(lab);
        frame1.add(lab1);
        frame1.add(lab2);
        frame1.add(lab3);
        frame1.add(lab4);
        frame1.add(lab5);
        frame1.add(lab6);
        frame1.add(lab7);
        frame1.add(lab8);
        frame1.add(lab9);
        frame1.add(lab10);
        frame1.add(lab11);
        frame1.add(lab12);
        frame1.add(lab13);
        frame1.add(lab14);
        frame1.add(lab15);
        frame1.add(lab16);
        frame1.add(lab17);
        frame1.add(lab19);
        frame1.add(lab20);

        // here add textfields and JCombobox in frame

        frame1.add(text1);
        frame1.add(text);
        frame1.add(text2);
        frame1.add(text3);
        frame1.add(text4);
        frame1.add(text5);
        frame1.add(ch1);
        frame1.add(text7);
        frame1.add(text8);
        frame1.add(text9);
        frame1.add(text10);
        frame1.add(text11);
        frame1.add(text12);
        frame1.add(text13);
        frame1.add(text14);
        frame1.add(text15);
        frame1.add(text16);
        frame1.add(text17);
        frame1.add(ch2);
        frame1.add(text18);
        // here add buttons to frame
        frame1.add(sub);
        frame1.add(clear);
        frame1.add(back);

        // set the properties of frame
        frame1.setSize(1500, 1500); // optional
        frame1.setLayout(null);
        frame1.getContentPane().setBackground(Color.YELLOW);
        frame1.setVisible(true);

        frame1.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Register the buttons and JComboBox with EventListener
        back.addActionListener(this);
        sub.addActionListener(this);
        clear.addActionListener(this);
        ch1.addActionListener(this);
        ch2.addActionListener(this);

    }

    // All logics for this class are inside these 4 methods
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back) {

            new StudentDataUpdate(Database, DBPassword, School_Name);
            frame1.dispose();
        }

        if (e.getSource() == clear) {
            text.setText(null);
            text1.setText(null);
            text2.setText(null);
            text3.setText(null);
            text4.setText(null);
            text5.setText(null);
            text7.setText(null);
            text8.setText(null);
            text9.setText(null);
            text10.setText(null);
            text11.setText(null);
            text12.setText(null);
            text13.setText(null);
            text14.setText(null);
            text15.setText(null);
            text16.setText(null);
            text17.setText(null);
            text18.setText(null);

            ch1.setSelectedIndex(-1);
            ch2.setSelectedIndex(-1);

        }

        if (e.getSource() == sub) {

            try {
                // connect app to mysql to update Student Basic details

                Student_Id = text.getText();
                String url = "jdbc:mysql://localhost:3306/" + Database;
                String user = "root";

                Connection connection = DriverManager.getConnection(url, user, DBPassword);
                Statement statement = connection.createStatement();

                // Enrollment No
                if (text1.getText() != null && !text1.getText().trim().isEmpty()) {

                    ResultSet resultSet = statement.executeQuery(
                            " select * from Enrollment where Student_Id = " + (String) text.getText() + " limit 1;");
                    while (resultSet.next()) {
                        ENROLLMENT = resultSet.getString(3);
                    }

                    String query = "UPDATE Enrollment SET Enrollment_No = ? WHERE Enrollment_No = ?";
                    PreparedStatement pstmt = connection.prepareStatement(query);
                    pstmt.setString(1, text1.getText());
                    pstmt.setString(2, ENROLLMENT);
                    pstmt.executeUpdate();

                    message = true;
                }

                // Student Name
                if (text2.getText() != null && !text2.getText().trim().isEmpty()) {

                    ResultSet resultSet = statement.executeQuery(
                            " select * from Student_Registration where Student_Id = " + (String) text.getText()
                                    + " limit 1;");
                    while (resultSet.next()) {
                        Sname = resultSet.getString(2);
                    }

                    String query = "UPDATE Student_Registration SET Student_Name = ? WHERE Student_Name = ?";
                    PreparedStatement pstmt = connection.prepareStatement(query);
                    pstmt.setString(1, text2.getText());
                    pstmt.setString(2, Sname);
                    pstmt.executeUpdate();
                    message = true;
                }

                // Father Name
                if (text3.getText() != null && !text3.getText().trim().isEmpty()) {

                    ResultSet resultSet = statement.executeQuery(
                            " select * from Student_Registration where Student_Id = " + (String) text.getText()
                                    + " limit 1;");
                    while (resultSet.next()) {
                        Fname = resultSet.getString(3);
                    }

                    String query = "UPDATE Student_Registration SET Father_Name = ? WHERE Father_Name = ?";
                    PreparedStatement pstmt = connection.prepareStatement(query);
                    pstmt.setString(1, text3.getText());
                    pstmt.setString(2, Fname);
                    pstmt.executeUpdate();
                    message = true;
                }

                // Mother Name
                if (text4.getText() != null && !text4.getText().trim().isEmpty()) {

                    ResultSet resultSet = statement.executeQuery(
                            " select * from Student_Registration where Student_Id = " + (String) text.getText()
                                    + " limit 1;");
                    while (resultSet.next()) {
                        Mname = resultSet.getString(4);
                    }

                    String query = "UPDATE Student_Registration SET Mother_Name = ? WHERE Mother_Name = ?";
                    PreparedStatement pstmt = connection.prepareStatement(query);
                    pstmt.setString(1, text4.getText());
                    pstmt.setString(2, Mname);
                    pstmt.executeUpdate();
                    message = true;
                }

                // Dob
                if (text5.getText() != null && !text5.getText().trim().isEmpty()) {

                    ResultSet resultSet = statement.executeQuery(
                            " select * from Student_Registration where Student_Id = " + (String) text.getText()
                                    + " limit 1;");
                    while (resultSet.next()) {
                        Dob = resultSet.getString(5);
                    }

                    String query = "UPDATE Student_Registration SET Date_Of_Birth = ? WHERE Date_Of_Birth = ?";
                    PreparedStatement pstmt = connection.prepareStatement(query);
                    pstmt.setString(1, text5.getText());
                    pstmt.setString(2, Dob);
                    pstmt.executeUpdate();
                    message = true;
                }

                // Gender
                if (ch1.getSelectedIndex() != -1) {

                    ResultSet resultSet = statement.executeQuery(
                            " select * from Student_Registration where Student_Id = " + (String) text.getText()
                                    + " limit 1;");
                    while (resultSet.next()) {
                        Gender = resultSet.getString(6);
                    }

                    String query = "UPDATE Student_Registration SET Gender = ? WHERE Gender = ?";
                    PreparedStatement pstmt = connection.prepareStatement(query);
                    pstmt.setString(1, (String) ch1.getSelectedItem());
                    pstmt.setString(2, Gender);
                    pstmt.executeUpdate();
                    message = true;
                }

                // mobile Numbr1
                if (text7.getText() != null && !text7.getText().trim().isEmpty()) {

                    ResultSet resultSet = statement.executeQuery(
                            " select * from Student_Registration where Student_Id = " + (String) text.getText()
                                    + " limit 1;");
                    while (resultSet.next()) {
                        Mob1 = resultSet.getString(7);
                    }

                    String query = "UPDATE Student_Registration SET Mob1= ? WHERE Mob1 = ?";
                    PreparedStatement pstmt = connection.prepareStatement(query);
                    pstmt.setString(1, text7.getText());
                    pstmt.setString(2, Mob1);
                    pstmt.executeUpdate();
                    message = true;
                }

                // Mobile Number2
                if (text8.getText() != null && !text8.getText().trim().isEmpty()) {

                    ResultSet resultSet = statement.executeQuery(
                            " select * from Student_Registration where Student_Id = " + (String) text.getText()
                                    + " limit 1;");
                    while (resultSet.next()) {
                        Mob2 = resultSet.getString(8);
                    }

                    String query = "UPDATE Student_Registration SET Mob2= ? WHERE Mob2 = ?";
                    PreparedStatement pstmt = connection.prepareStatement(query);
                    pstmt.setString(1, text8.getText());
                    pstmt.setString(2, Mob2);
                    pstmt.executeUpdate();
                    message = true;
                }

                // Registration Date
                if (text9.getText() != null && !text9.getText().trim().isEmpty()) {

                    ResultSet resultSet = statement.executeQuery(
                            " select * from Student_Registration where Student_Id = " + (String) text.getText()
                                    + " limit 1;");
                    while (resultSet.next()) {
                        RDate = resultSet.getString(9);
                    }

                    String query = "UPDATE Student_Registration SET Registration_Date= ? WHERE Registration_Date = ?";
                    PreparedStatement pstmt = connection.prepareStatement(query);
                    pstmt.setString(1, text9.getText());
                    pstmt.setString(2, RDate);
                    pstmt.executeUpdate();
                    message = true;
                }

                // Address
                if (text10.getText() != null && !text10.getText().trim().isEmpty()) {

                    ResultSet resultSet = statement.executeQuery(
                            " select * from Student_Registration where Student_Id = " + (String) text.getText()
                                    + " limit 1;");
                    while (resultSet.next()) {
                        Address = resultSet.getString(10);
                    }

                    String query = "UPDATE Student_Registration SET Address= ? WHERE Address = ?";
                    PreparedStatement pstmt = connection.prepareStatement(query);
                    pstmt.setString(1, text10.getText());
                    pstmt.setString(2, Address);
                    pstmt.executeUpdate();
                    message = true;
                }

                // Student Aadhaar no
                if (text11.getText() != null && !text11.getText().trim().isEmpty()) {

                    ResultSet resultSet = statement.executeQuery(
                            " select * from Student_Registration where Student_Id = " + (String) text.getText()
                                    + " limit 1;");
                    while (resultSet.next()) {
                        student_Aadhaar_No = resultSet.getString(11);
                    }

                    String query = "UPDATE Student_Registration SET Student_Aadhaar_No= ? WHERE Student_Aadhaar_No = ?";
                    PreparedStatement pstmt = connection.prepareStatement(query);
                    pstmt.setString(1, text11.getText());
                    pstmt.setString(2, student_Aadhaar_No);
                    pstmt.executeUpdate();
                    message = true;
                }

                // Father Aadhaar No
                if (text12.getText() != null && !text12.getText().trim().isEmpty()) {

                    ResultSet resultSet = statement
                            .executeQuery(" select * from Student_Registration where Student_Id = "
                                    + (String) text.getText() + " limit 1;");
                    while (resultSet.next()) {
                        Father_Aadhaar_No = resultSet.getString(12);
                    }

                    String query = "UPDATE Student_Registration SET Father_Aadhaar_No= ? WHERE Father_Aadhaar_No = ?";
                    PreparedStatement pstmt = connection.prepareStatement(query);
                    pstmt.setString(1, text12.getText());
                    pstmt.setString(2, Father_Aadhaar_No);
                    pstmt.executeUpdate();
                    message = true;
                }

                // Mother Aadhaar No
                if (text13.getText() != null && !text13.getText().trim().isEmpty()) {

                    ResultSet resultSet = statement.executeQuery(
                            " select * from Student_Registration where Student_Id = " + (String) text.getText()
                                    + " limit 1;");
                    while (resultSet.next()) {
                        Mother_Aadhaar_No = resultSet.getString(13);
                    }

                    String query = "UPDATE Student_Registration SET Mother_Aadhaar_No= ? WHERE Mother_Aadhaar_No = ?";
                    PreparedStatement pstmt = connection.prepareStatement(query);
                    pstmt.setString(1, text13.getText());
                    pstmt.setString(2, Mother_Aadhaar_No);
                    pstmt.executeUpdate();
                    message = true;
                }

                // Family Id

                if (text14.getText() != null && !text14.getText().trim().isEmpty()) {

                    ResultSet resultSet = statement.executeQuery(
                            " select * from Student_Registration where Student_Id = " + (String) text.getText()
                                    + " limit 1;");
                    while (resultSet.next()) {
                        Family_Id = resultSet.getString(14);
                    }

                    String query = "UPDATE Student_Registration SET Family_Id= ? WHERE Family_Id = ?";
                    PreparedStatement pstmt = connection.prepareStatement(query);
                    pstmt.setString(1, text14.getText());
                    pstmt.setString(2, Family_Id);
                    pstmt.executeUpdate();
                    message = true;
                }

                // Student Category
                if (ch2.getSelectedIndex() != -1) {

                    ResultSet resultSet = statement.executeQuery(
                            " select * from Student_Registration where Student_Id = " + (String) text.getText()
                                    + " limit 1;");
                    while (resultSet.next()) {
                        Category = resultSet.getString(15);
                    }

                    String query = "UPDATE Student_Registration SET Category= ? WHERE Category = ?";
                    PreparedStatement pstmt = connection.prepareStatement(query);
                    pstmt.setString(1, (String) ch2.getSelectedItem());
                    pstmt.setString(2, Category);

                    pstmt.executeUpdate();
                    message = true;
                }

                // Father Occupation

                if (text15.getText() != null && !text15.getText().trim().isEmpty()) {

                    ResultSet resultSet = statement.executeQuery(
                            " select * from Student_Registration where Student_Id = " + (String) text.getText()
                                    + " limit 1;");
                    while (resultSet.next()) {
                        Father_Occupation = resultSet.getString(15);
                    }

                    String query = "UPDATE Student_Registration SET Father_Occupation= ? WHERE Father_Occupation = ?";
                    PreparedStatement pstmt = connection.prepareStatement(query);
                    pstmt.setString(1, text15.getText());
                    pstmt.setString(2, Father_Occupation);
                    pstmt.executeUpdate();
                    message = true;
                }

                // Mother Occupation
                if (text16.getText() != null && !text16.getText().trim().isEmpty()) {

                    ResultSet resultSet = statement.executeQuery(
                            " select * from Student_Registration where Student_Id = " + (String) text.getText()
                                    + " limit 1;");
                    while (resultSet.next()) {
                        Mother_Occupation = resultSet.getString(16);
                    }

                    String query = "UPDATE Student_Registration SET Mother_Occupation= ? WHERE Mother_Occupation = ?";
                    PreparedStatement pstmt = connection.prepareStatement(query);
                    pstmt.setString(1, text16.getText());
                    pstmt.setString(2, Mother_Occupation);
                    pstmt.executeUpdate();
                    message = true;
                }

                // School Leaving Date
                if (text17.getText() != null && !text17.getText().trim().isEmpty()) {

                    String query = "UPDATE Student_Registration SET School_Leaving_Date= ? WHERE School_Leaving_Date = ? and Student_Id= ?";
                    PreparedStatement pstmt = connection.prepareStatement(query);
                    pstmt.setString(1, text17.getText());
                    pstmt.setString(2, null);
                    pstmt.setString(3, text.getText());
                    pstmt.executeUpdate();
                    message = true;
                }

                if (text18.getText() != null && !text1.getText().trim().isEmpty()) {

                    ResultSet resultSet = statement.executeQuery(
                            "select * from Enrollment where Student_Id = " + (String) text.getText() + " limit 1;");
                    while (resultSet.next()) {
                        EnrolledClass = resultSet.getString(4);
                    }

                    String query = "UPDATE Enrollment SET Class = ? WHERE Class = ?";
                    PreparedStatement pstmt = connection.prepareStatement(query);
                    pstmt.setString(1, text18.getText());
                    pstmt.setString(2, EnrolledClass);
                    pstmt.executeUpdate();

                    message = true;
                }

                // if ((text1.getText() == null || text1.getText().trim().isEmpty())
                // && (text2.getText() == null || text2.getText().trim().isEmpty())
                // && (text3.getText() == null || text3.getText().trim().isEmpty())
                // && (text4.getText() == null || text4.getText().trim().isEmpty())
                // && (text5.getText() == null || text5.getText().trim().isEmpty())
                // && (ch1.getSelectedIndex() == -1)
                // && (text7.getText() == null || text7.getText().trim().isEmpty())
                // && (text8.getText() == null || text8.getText().trim().isEmpty())
                // && (text9.getText() == null || text9.getText().trim().isEmpty())
                // && (text10.getText() == null || text10.getText().trim().isEmpty())
                // && (text11.getText() == null || text11.getText().trim().isEmpty())
                // && (text12.getText() == null || text12.getText().trim().isEmpty())
                // && (text13.getText() == null || text13.getText().trim().isEmpty())
                // && (text14.getText() == null || text14.getText().trim().isEmpty())
                // && (ch2.getSelectedIndex() == -1)
                // && (text15.getText() == null || text15.getText().trim().isEmpty())
                // && (text16.getText() == null || text16.getText().trim().isEmpty())
                // && (text17.getText() == null || text17.getText().trim().isEmpty())) {
                // JOptionPane.showMessageDialog(this, "Nothing is updated");

                // }

                if (message == true) {
                    JOptionPane.showMessageDialog(this, "Student Data Updated ");

                }

                if (message == false) {
                    JOptionPane.showMessageDialog(this, "Nothing is updated");

                }

                statement.close();
                connection.close();

            } catch (Exception a) {

                JOptionPane.showMessageDialog(this, "Student_Id is not filled or Wrong Enteries");

            }

        }

    }

}


class AcademicUpdate extends PlaceholderTextField implements ActionListener {

    // Buttons :
    // sub to update the Student Academic details into the database,
    // clear for clear the information that are filled,
    // back for redirect the user to Home page of App
    // Delete for delete the Academic data
    JButton back, sub,Delete, clear;

    // Textfields:
    // text1 allows user to enter serial Number ,
    // text2 allows user to enter Student ID,
    // text3 allows user to enter Student class,
    // text4 allows user to enter Student Subject,
    // text5 allows user to enter Student Session,
    // text6 allows user to enter Student Fee,
    JTextField text1, text2, text5, text3, text4, text6;

    JFrame frame; // frame in which all other components are added .

    // Database to Store the database name
    // DBPassword to store the mysql password
    // School_Name to store the School name
    // placeholder for background text of textfield
    String placeholder, Database, DBPassword, School_Name;


    // Serial No to store serial no of data
    // Student Id to store student id
    // Class to store class of student
    // Subject to store subjects of student
    // Session to store the session of Student
    // Fee to store the Fee of Student
    String Serial_No , Student_Id, Student_Name, Class, Subject, Session, Fee;


    boolean message = false; // message to store the message that shows whether the Student database updated or not.

    // constructor
    AcademicUpdate(String placeholder, String database, String Password, String School) {

        super(placeholder);
        Database = database;
        DBPassword = Password;
        School_Name = School;

        // create a frame

        frame = new JFrame();

        // create labels and set their properties
        JLabel label = new JLabel();
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setSize(1250, 100);
        label.setText("UPDATE/DELETE  ACADEMIC  DETAILS");
        label.setFont(new Font("Serif", Font.BOLD, 50));

        JLabel label2 = new JLabel("Note : Admin can update/Delete student data using student id and Serial No");
        label2.setFont(new Font("Serif", Font.PLAIN, 22));
        label2.setBounds(330, 75, 700, 25);

        JLabel label3 = new JLabel("Note : Admin can find the Serial Number by click on View Student details button");
        label3.setFont(new Font("Serif", Font.PLAIN, 22));
        label3.setBounds(300, 110, 720, 25);

        JLabel lab1 = new JLabel("Serial Number :");
        lab1.setFont(new Font("Serif", Font.PLAIN, 25));
        lab1.setBounds(150, 160, 250, 30);

        JLabel lab2 = new JLabel("Student Id :");
        lab2.setFont(new Font("Serif", Font.PLAIN, 25));
        lab2.setBounds(150, 200, 250, 30);

        JLabel lab3 = new JLabel("Class :");
        lab3.setFont(new Font("Serif", Font.PLAIN, 25));
        lab3.setBounds(150, 240, 250, 30);

        JLabel lab4 = new JLabel("Subject :");
        lab4.setFont(new Font("Serif", Font.PLAIN, 25));
        lab4.setBounds(150, 280, 250, 30);

        JLabel lab5 = new JLabel("Session :");
        lab5.setFont(new Font("Serif", Font.PLAIN, 25));
        lab5.setBounds(150, 320, 250, 30);

        JLabel lab6 = new JLabel("Fee :");
        lab6.setFont(new Font("Serif", Font.PLAIN, 25));
        lab6.setBounds(150, 360, 250, 30);



        // Here add label in frame
        frame.add(label);
        frame.add(label2);
        frame.add(label3);
        frame.add(lab1);
        frame.add(lab2);
        frame.add(lab3);
        frame.add(lab4);
        frame.add(lab5);
        frame.add(lab6);
        

        // create TextFields and set their properties

        text1 = new JTextField();
        text1.setFont(new Font("Serif", Font.PLAIN, 25));
        text1.setBounds(450, 165, 300, 30);

        text2 = new JTextField();
        text2.setFont(new Font("Serif", Font.PLAIN, 25));
        text2.setBounds(450, 205, 300, 30);

        text3 = new PlaceholderTextField("eg:5th");
        text3.setFont(new Font("Serif", Font.PLAIN, 25));
        text3.setBounds(450, 245, 300, 30);

        text4 = new PlaceholderTextField("eg : Math,English,Science");
        text4.setFont(new Font("Serif", Font.PLAIN, 25));
        text4.setBounds(450, 285, 300, 30);

        text5 = new JTextField();
        text5.setFont(new Font("Serif", Font.PLAIN, 25));
        text5.setBounds(450, 325, 300, 30);

        text6 = new JTextField();
        text6.setFont(new Font("Serif", Font.PLAIN, 25));
        text6.setBounds(450, 365, 300, 30);

 

        // add submit,clear and back buttons

        sub = new JButton(" UPDATE ");
        sub.setBounds(250, 600, 200, 30);

        clear = new JButton(" CLEAR ");
        clear.setBounds(500, 600, 200, 30);

        Delete = new JButton(" DELETE ");
        Delete.setBounds(750, 600, 200, 30);

        back = new JButton("Back");
        back.setBounds(30, 30, 100, 30);

        // here add textfields to frame
        frame.add(text1);
        frame.add(text2);
        frame.add(text3);
        frame.add(text4);
        frame.add(text5);
        frame.add(text6);

        // here add buttons to frame
        frame.add(sub);
        frame.add(clear);
        frame.add(Delete);
        frame.add(back);

        // set the properties of frame
        frame.setSize(500, 500);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.YELLOW);
        frame.setVisible(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Register buttons with event Listener
        back.addActionListener(this);
        sub.addActionListener(this);
        Delete.addActionListener(this);
        clear.addActionListener(this);

    }

    // All Logics for this class are inside this method.
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back) {

            new StudentDataUpdate(Database, DBPassword, School_Name);
            frame.dispose();
        }
        if (e.getSource() == clear) {
            text1.setText(null);
            text2.setText(null);
            text3.setText(null);
            text4.setText(null);
            text5.setText(null);
            text6.setText(null);
        

        }
        if(e.getSource()== Delete){
            try{
                // connect app to mysql to delete Student Academic details from the database.

            
                String url = "jdbc:mysql://localhost:3306/" + Database;
                String user = "root";

                Connection connection = DriverManager.getConnection(url, user, DBPassword);
                PreparedStatement pstmt = connection.prepareStatement("delete from Academic where Student_Id = ? and Serial_No = ? ");
                pstmt.setString(1, text2.getText());
                pstmt.setString(2, text1.getText());
                pstmt.executeUpdate();
                JOptionPane.showMessageDialog(this, "Data Deleted Successfully.");
                connection.close();
            }
            catch(Exception b){
                
                JOptionPane.showMessageDialog(this, "No Data Deleted.");
            }
        }
        if (e.getSource() == sub) {
            try {
                // connect app to mysql to update Student Academic details into the database.
                String url = "jdbc:mysql://localhost:3306/" + Database;
                String user = "root";

                Connection connection = DriverManager.getConnection(url, user, DBPassword);
                
                // Class
                if (text3.getText() != null && !text3.getText().trim().isEmpty()) {

                    String selectQuery = "SELECT * FROM Academic WHERE Student_Id = ? AND Serial_No = ?";
                    PreparedStatement selectStmt = connection.prepareStatement(selectQuery);
                    selectStmt.setString(1, text2.getText());
                    selectStmt.setString(2, text1.getText());
                
                    ResultSet resultSet = selectStmt.executeQuery();
                
                    while (resultSet.next()) {
                        Class = resultSet.getString(4);
                    }

                    String query = "UPDATE  Academic  SET class  = ? WHERE class = ? ;";
                    PreparedStatement pstmt = connection.prepareStatement(query);
                    pstmt.setString(1, text3.getText());
                    pstmt.setString(2,Class);
                    pstmt.executeUpdate();

                    message = true;
                }

                // Subjects
                if (text4.getText() != null && !text4.getText().trim().isEmpty()) {

                
                    String selectQuery = "SELECT * FROM Academic WHERE Student_Id = ? AND Serial_No = ?";
                    PreparedStatement selectStmt = connection.prepareStatement(selectQuery);
                    selectStmt.setString(1, text2.getText());
                    selectStmt.setString(2, text1.getText());
                
                    ResultSet resultSet = selectStmt.executeQuery();
                while (resultSet.next()) {
                        Subject = resultSet.getString(5);
                    }

                    String query = "UPDATE Academic SET Subjects = ? WHERE Subjects = ?";
                    PreparedStatement pstmt = connection.prepareStatement(query);
                    pstmt.setString(1, text4.getText());
                    pstmt.setString(2, Subject);
                    pstmt.executeUpdate();
                    message = true;
                }

                // Session 
                if (text5.getText() != null && !text5.getText().trim().isEmpty()) {

                    String selectQuery = "SELECT * FROM Academic WHERE Student_Id = ? AND Serial_No = ?";
                    PreparedStatement selectStmt = connection.prepareStatement(selectQuery);
                    selectStmt.setString(1, text2.getText());
                    selectStmt.setString(2, text1.getText());
                
                    ResultSet resultSet = selectStmt.executeQuery();
                    while (resultSet.next()) {
                    Session = resultSet.getString(6);
                }

                    String query = "UPDATE Academic SET Session = ? WHERE Session = ?";
                    PreparedStatement pstmt = connection.prepareStatement(query);
                    pstmt.setString(1, text5.getText());
                    pstmt.setString(2, Session);
                    pstmt.executeUpdate();
                    message = true;
                }

                // Fee
                if (text6.getText() != null && !text6.getText().trim().isEmpty()) {

                    String selectQuery = "SELECT * FROM Academic WHERE Student_Id = ? AND Serial_No = ?";
                    PreparedStatement selectStmt = connection.prepareStatement(selectQuery);
                    selectStmt.setString(1, text2.getText());
                    selectStmt.setString(2, text1.getText());
                
                    ResultSet resultSet = selectStmt.executeQuery();
                    while (resultSet.next()) {
                    Fee = resultSet.getString(7);
                }

                    String query = "UPDATE Academic SET Fee = ? WHERE Fee = ?";
                    PreparedStatement pstmt = connection.prepareStatement(query);
                    pstmt.setString(1, text6.getText());
                    pstmt.setString(2, Fee);
                    pstmt.executeUpdate();
                    message = true;
                }

              




                if(message == true){
                JOptionPane.showMessageDialog(this, "Student Data Updated."); // showing the message whether
                }
                                                                                       // the Student Details insert into the database or not .
                if(message == false){
                    JOptionPane.showMessageDialog(this, "Nothing is Updated.");
                }
                
            
                connection.close();

            } catch (Exception a) {
                System.out.println(a);
                JOptionPane.showMessageDialog(this, "Something is missing or Incorrect enteries");
            }
        }
    }
}




/*
 * This class provides an user interface that allows the user to update Fee details.
 * This class extends PlaceholderTextField to fill the background of textfield
 * that shows how to enter date and which textfield is optional.
 * This class extends ActionListener for event handling.
 */

 class FeeUpdate extends PlaceholderTextField implements ActionListener {

    JFrame frame; // frame in which all other components are added .

    // Buttons :
    // sub to insert the Student Fee details into the database,
    // clear for clear the information that are filled,
    // Delete for delete the fee data of Student
    // back for redirect the user to Home page of App
    JButton back, clear,Delete, sub;

    // Textfields:
    // text1 allows user to enter serial no ,
    // text2 allows user to enter Student id,
    // text3 allows user to enter Student Class,
    // text4 allows user to enter Student Paid Fee,
    // text5 allows user to enter Student Balance,
    // text6 allows user to enter Student Date.
    JTextField text1, text2, text3, text4, text5, text6;

    // Database to Store the database name
    // DBPassword to store the mysql password
    // School_Name to store the School name
    // placeholder for background text of textfield
    String placeholder, Database, DBPassword, School_Name;

    boolean message = false;  // for message that shows whether the fee details updated or not.

    // class to store the class of student to update
    // Paid_Fee to store the fees paid by Student to update
    // Balance to store the balance left to update
    // Date to store the Data to be updated
    String Class,Paid_Fee,Balance,Date;

    // constructor
    FeeUpdate(String placeholder, String database, String Password, String School) {
        super(placeholder);
        Database = database;
        DBPassword = Password;
        School_Name = School;

        // create a Frame
        frame = new JFrame();

        // create labels and set their properties
        JLabel label = new JLabel();
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setSize(1250, 100);
        label.setText("UPDATE/DELETE  FEE  DETAILS");
        label.setFont(new Font("Serif", Font.BOLD, 50));


        JLabel label2 = new JLabel("Note : Admin can update/Delete student data using student id and Serial No");
        label2.setFont(new Font("Serif", Font.PLAIN, 22));
        label2.setBounds(330, 75, 700, 25);

        JLabel label3 = new JLabel("Note : Admin can find the Serial Number by click on View Student details button");
        label3.setFont(new Font("Serif", Font.PLAIN, 22));
        label3.setBounds(300, 110, 720, 25);

        JLabel lab1 = new JLabel("Serial No :");
        lab1.setFont(new Font("Serif", Font.PLAIN, 25));
        lab1.setBounds(150, 160, 250, 30);

        JLabel lab2 = new JLabel("Student ID :");
        lab2.setFont(new Font("Serif", Font.PLAIN, 25));
        lab2.setBounds(150, 200, 250, 30);

        JLabel lab3 = new JLabel("Class:");
        lab3.setFont(new Font("Serif", Font.PLAIN, 25));
        lab3.setBounds(150, 240, 250, 30);

        JLabel lab4 = new JLabel("Paid Fee :");
        lab4.setFont(new Font("Serif", Font.PLAIN, 25));
        lab4.setBounds(150, 280, 250, 30);

        JLabel lab5 = new JLabel("Balance :");
        lab5.setFont(new Font("Serif", Font.PLAIN, 25));
        lab5.setBounds(150, 320, 250, 30);

        JLabel lab6 = new JLabel("Date");
        lab6.setFont(new Font("Serif", Font.PLAIN, 25));
        lab6.setBounds(150, 360, 250, 30);

        // Here add label in frame
        frame.add(label);
        frame.add(label2);
        frame.add(label3);
        frame.add(lab1);
        frame.add(lab2);
        frame.add(lab3);
        frame.add(lab4);
        frame.add(lab5);
        frame.add(lab6);

        // create TextFields and set their properties

        text1 = new JTextField();
        text1.setFont(new Font("Serif", Font.PLAIN, 25));
        text1.setBounds(450, 165, 300, 30);

        text2 = new JTextField();
        text2.setFont(new Font("Serif", Font.PLAIN, 25));
        text2.setBounds(450, 205, 300, 30);

        text3 = new PlaceholderTextField("eg : 5th");
        text3.setFont(new Font("Serif", Font.PLAIN, 25));
        text3.setBounds(450, 245, 300, 30);

        text4 = new JTextField();
        text4.setFont(new Font("Serif", Font.PLAIN, 25));
        text4.setBounds(450, 285, 300, 30);

        text5 = new JTextField();
        text5.setFont(new Font("Serif", Font.PLAIN, 25));
        text5.setBounds(450, 325, 300, 30);

        text6 = new PlaceholderTextField("YYYY-MM-DD");
        text6.setFont(new Font("Serif", Font.PLAIN, 25));
        text6.setBounds(450, 365, 300, 30);

        // create button and set its properties

        sub = new JButton(" UPDATE ");
        sub.setBounds(250, 600, 200, 30);

        clear = new JButton(" CLEAR ");
        clear.setBounds(500, 600, 200, 30);

        Delete = new JButton(" DELETE ");
        Delete.setBounds(750, 600, 200, 30);

        back = new JButton("Back");
        back.setBounds(30, 30, 100, 30);

        // here add textfields to frame
        frame.add(text1);
        frame.add(text2);
        frame.add(text3);
        frame.add(text4);
        frame.add(text5);
        frame.add(text6);

        // here add buttons to frame
        frame.add(sub);
        frame.add(clear);
        frame.add(Delete);
        frame.add(back);

        // set the properties of frame
        frame.setSize(500, 500);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.YELLOW);
        frame.setVisible(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Register the buttons with event Listener
        back.addActionListener(this);
        clear.addActionListener(this);
        Delete.addActionListener(this);
        sub.addActionListener(this);
    }

    // All the logics for this class are inside this method.
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back) {

            new StudentDataUpdate(Database, DBPassword, School_Name);
            frame.dispose();
        }

        if (e.getSource() == clear) {
            text1.setText(null);
            text2.setText(null);
            text3.setText(null);
            text4.setText(null);
            text5.setText(null);
            text6.setText(null);

        }
        if(e.getSource()== Delete){
            try{
                // connect app to mysql to delete Student Academic details from the database.

            
                String url = "jdbc:mysql://localhost:3306/" + Database;
                String user = "root";

                Connection connection = DriverManager.getConnection(url, user, DBPassword);
                PreparedStatement pstmt = connection.prepareStatement("delete from Fee_Details where Student_Id = ? and Serial_No = ? ");
                pstmt.setString(1, text2.getText());
                pstmt.setString(2, text1.getText());
                pstmt.executeUpdate();
                JOptionPane.showMessageDialog(this, "Data Deleted Successfully.");
                connection.close();
            }
            catch(Exception b){
                
                JOptionPane.showMessageDialog(this, "No Data Deleted.");
            }
        }
        if (e.getSource() == sub) {
            try {
                // connect app to mysql to update Student Academic details into the database.
                String url = "jdbc:mysql://localhost:3306/" + Database;
                String user = "root";

                Connection connection = DriverManager.getConnection(url, user, DBPassword);
               
               
                // Classw
                if (text3.getText() != null && !text3.getText().trim().isEmpty()) {
                    String selectQuery = "SELECT * FROM Fee_Details WHERE Student_Id = ? AND Serial_No = ?";
                    PreparedStatement selectStmt = connection.prepareStatement(selectQuery);
                    selectStmt.setString(1, text2.getText());
                    selectStmt.setString(2, text1.getText());
                
                    ResultSet resultSet = selectStmt.executeQuery();

                    while (resultSet.next()) {
                        Class = resultSet.getString(4);
                    }

                    String query = "UPDATE  Fee_Details  SET Class  = ? WHERE Class = ?";
                    PreparedStatement pstmt = connection.prepareStatement(query);
                    pstmt.setString(1, text3.getText());
                    pstmt.setString(2,Class);
                    pstmt.executeUpdate();

                    message = true;
                }

                // Subjects
                if (text4.getText() != null && !text4.getText().trim().isEmpty()) {

                    String selectQuery = "SELECT * FROM Fee_Details WHERE Student_Id = ? AND Serial_No = ?";
                    PreparedStatement selectStmt = connection.prepareStatement(selectQuery);
                    selectStmt.setString(1, text2.getText());
                    selectStmt.setString(2, text1.getText());
                
                    ResultSet resultSet = selectStmt.executeQuery();
                while (resultSet.next()) {
                        Paid_Fee = resultSet.getString(5);
                    }

                    String query = "UPDATE Fee_Details SET Paid_Fee = ? WHERE Paid_Fee = ?";
                    PreparedStatement pstmt = connection.prepareStatement(query);
                    pstmt.setString(1, text4.getText());
                    pstmt.setString(2, Paid_Fee);
                    pstmt.executeUpdate();
                    message = true;
                }

                // Session 
                if (text5.getText() != null && !text5.getText().trim().isEmpty()) {

                    String selectQuery = "SELECT * FROM Fee_Details WHERE Student_Id = ? AND Serial_No = ?";
                    PreparedStatement selectStmt = connection.prepareStatement(selectQuery);
                    selectStmt.setString(1, text2.getText());
                    selectStmt.setString(2, text1.getText());
                
                    ResultSet resultSet = selectStmt.executeQuery();
                while (resultSet.next()) {
                    Balance = resultSet.getString(6);
                }

                    String query = "UPDATE Fee_Details SET Balance = ? WHERE Balance = ?";
                    PreparedStatement pstmt = connection.prepareStatement(query);
                    pstmt.setString(1, text5.getText());
                    pstmt.setString(2, Balance);
                    pstmt.executeUpdate();
                    message = true;
                }

                // Fee
                if (text6.getText() != null && !text6.getText().trim().isEmpty()) {

                    String selectQuery = "SELECT * FROM Fee_Details WHERE Student_Id = ? AND Serial_No = ?";
                    PreparedStatement selectStmt = connection.prepareStatement(selectQuery);
                    selectStmt.setString(1, text2.getText());
                    selectStmt.setString(2, text1.getText());
                
                    ResultSet resultSet = selectStmt.executeQuery();
                while (resultSet.next()) {
                    Date = resultSet.getString(7);
                }

                    String query = "UPDATE Fee_Details SET Date = ? WHERE Date = ?";
                    PreparedStatement pstmt = connection.prepareStatement(query);
                    pstmt.setString(1, text6.getText());
                    pstmt.setString(2, Date);
                    pstmt.executeUpdate();
                    message = true;
                }

              
                
                connection.close();

                if(message == true){
                JOptionPane.showMessageDialog(this, "Student Data Updated."); // showing the message whether
                }
                                                                                       // the Student Details insert into the database or not .
                if(message == false){
                    JOptionPane.showMessageDialog(this, "Nothing is Updated.");
                }

            } catch (Exception a) {
                JOptionPane.showMessageDialog(this, "Something is missing or Incorrect enteries");
            }
        }
    }
}


/*
 * This class provides an user interface that contains three buttons which
 * allows the user to update Teacher details when they clicked.
 * This class extends ActionListener for event handling.
 */

class TeacherDataUpdate implements ActionListener {

    JFrame frame; // frame in which all other components are added.

    // Buttons :
    // button to update Teacher Basic details,
    // button1 to update Teacher's Salary Structure details,
    // button 2 to update the Teacher's Salary Details,
    // back for redirect the user to Home page of App
    JButton button, button1, button2, back;

    // Database to Store the database name
    // DBPassword to store the mysql password
    // School_Name to store the School name
    String Database, DBPassword, School_Name;

    // constructor
    TeacherDataUpdate(String database, String Password, String School) {

        Database = database;
        DBPassword = Password;
        School_Name = School;

        // Create a frame
        frame = new JFrame();

        // Create a label and set its properties

        JLabel label = new JLabel();
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setSize(1250, 100);
        label.setText(School_Name);
        label.setFont(new Font("Serif", Font.BOLD, 50));

        // create buttons and add to frame

        button = new JButton("Update Teacher Basic Details");
        button.setBounds(480, 150, 350, 30);
        frame.add(button);

        button1 = new JButton("Update/Delete Teacher Salary Structure ");
        button1.setBounds(480, 200, 350, 30);
        frame.add(button1);

        button2 = new JButton("Update/Delete Teacher Salary Details");
        button2.setBounds(480, 250, 350, 30);
        frame.add(button2);

        back = new JButton("Back");
        back.setBounds(30, 30, 100, 30);
        frame.add(back);

        // Here add label in frame
        frame.add(label);

        // set the properties of frame
        frame.setSize(500, 500); // Optional
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.YELLOW);
        frame.setVisible(true);

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Registraton of buttons with action listener
        button.addActionListener(this);
        button1.addActionListener(this);
        button2.addActionListener(this);
        back.addActionListener(this);

    }

    // All logics for this class are inside this method.

    // @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == button) {

            new TeacherUpdate(null,Database, DBPassword, School_Name);
            
            frame.dispose();

        }

        if (e.getSource() == button1) {

            new TeacherSalaryStructureUpdate(null,Database, DBPassword, School_Name);
            
            frame.dispose();
        }
        if (e.getSource() == button2) {

            new TeacherSalaryUpdate(null,Database, DBPassword, School_Name);
        
            frame.dispose();
        }
        if (e.getSource() == back) {

            new First(Database, DBPassword, School_Name);
            frame.dispose();
        }

    }
}








/*
 * This class provides an user interface that allows the user to update
 * Teacher Basic details .
 * This class extends ActionListenerfor event handling.
 */

class TeacherUpdate extends PlaceholderTextField implements ActionListener {

    // Buttons :
    // sub to insert the Teacher basic details into the database,
    // clear for clear the information that are filled,
    // back for redirect the user to Home page of App
    JButton back, sub, clear;

    // TextField:
    // text allows the user to enter Teacher ID,
    // text1 allows the user to enter Teacher Name,
    // text2 allows the user to enter Teacher's Father Name,
    // text3 allows the user to enter Teacher's Mother Name,
    // text4 allows the user to enter Teacher's Date of Birth,
    // ch1 allows the user to select Teacher's Gender,
    // text5 allows the user to enter Teacher's Mobile Number1 ,
    // text7 allows the user to enter Teacher's Mobile Number2,
    // text8 allows the user to enter Teacher's Joining Date,
    // text9 allows the user to enter Teacher's Address,
    // text11 allows the user to enter Teacher's Aadhaar No ,
    // text12 allows the user to enter Teacher's ,Family Id
    // text13 allows the user to enter Teacher's ,
    // text14 allows the user to enter Teacher's Experience ,
    // text15 allows the user to enter Teacher's Account No,
    // text16 allows the user to enter Teacher's Job Leaving date
    JTextField text, text1, text2, text3, text4, text5, text7, text8, text9, text11, text12, text13, text14, text15,
            text16;

    JComboBox<String> ch1; // JComboBox ch1 to select gender of teacher

    String gender[] = { "Male", "Female", "Other" }; // options to select the gender of teacher

    JFrame frame2; // frame in which all other components are added

    // String
    // Teacher_Id store Teacher's Id,
    // Teacher_Name store Teacher Name,
    // Father_Name store Teacher's Father Name,
    // Mother_Name store Teacher's Mother Name,
    // Dob store Teacher's Date of Birth,
    // Gender store select Teacher's Gender,
    // Mob1 store Teacher's Mobile Number1 ,
    // Mob2 store Teacher's Mobile Number2,
    // Joining_Date store Teacher's Joining Date,
    // Address store Teacher's Address,
    // Family_Id store Teacher's Family ID,
    // Qualification store select Teacher's Qualification ,
    // Experience store Teacher's Experience,
    // Account_No store Teacher's Mother's Account No,
    // Job_Leaving_Date store Teacher's Job Leaving date
    String Teacher_Id, Teacher_Name, Father_Name, Mother_Name, Dob, Gender, Mob1, Mob2, Joining_Date, Address,
            Teacher_Aadhaar_No, Family_Id, Qualification, Experience, Account_No, Job_Leaving_Date;

    // Database to Store the database name
    // DBPassword to store the mysql password
    // School_Name to store the School name
    // placeholder for background text of textfield
    String placeholder, Database, DBPassword, School_Name;

    boolean message = false; // for message that shows Teacher Data Updated or not

    // constructor
    TeacherUpdate(String placeholder, String database, String Password, String School) {
        super(placeholder);
        Database = database;
        DBPassword = Password;
        School_Name = School;

        // create a frame
        frame2 = new JFrame();

        // create labels and set their properties
        JLabel label1 = new JLabel();
        label1.setHorizontalAlignment(JLabel.CENTER);
        label1.setSize(1250, 100);
        label1.setText("UPDATE  TEACHER  DATA");
        label1.setFont(new Font("Serif", Font.BOLD, 50));

        JLabel label2 = new JLabel("Note : Admin can update teacher data using Teacher id");
        label2.setFont(new Font("Serif", Font.PLAIN, 22));
        label2.setBounds(380, 75, 650, 25);

        JLabel lab = new JLabel("Teacher  ID :");
        lab.setFont(new Font("Serif", Font.PLAIN, 25));
        lab.setBounds(410, 110, 250, 30);

        JLabel lab1 = new JLabel("Teacher Name :");
        lab1.setFont(new Font("Serif", Font.PLAIN, 25));
        lab1.setBounds(70, 160, 250, 30);

        JLabel lab2 = new JLabel("Father Name :");
        lab2.setFont(new Font("Serif", Font.PLAIN, 25));
        lab2.setBounds(70, 200, 250, 30);

        JLabel lab3 = new JLabel("Mother Name :");
        lab3.setFont(new Font("Serif", Font.PLAIN, 25));
        lab3.setBounds(70, 240, 250, 30);

        JLabel lab4 = new JLabel("Date Of Birth :");
        lab4.setFont(new Font("Serif", Font.PLAIN, 25));
        lab4.setBounds(70, 280, 250, 30);

        JLabel lab5 = new JLabel("Gender :");
        lab5.setFont(new Font("Serif", Font.PLAIN, 25));
        lab5.setBounds(70, 320, 250, 30);

        JLabel lab6 = new JLabel("Mobile Number1");
        lab6.setFont(new Font("Serif", Font.PLAIN, 25));
        lab6.setBounds(70, 360, 250, 30);

        JLabel lab7 = new JLabel("Mobile Number2 :");
        lab7.setFont(new Font("Serif", Font.PLAIN, 25));
        lab7.setBounds(70, 400, 250, 30);

        JLabel lab8 = new JLabel("Joining Date :");
        lab8.setFont(new Font("Serif", Font.PLAIN, 25));
        lab8.setBounds(70, 440, 250, 30);

        JLabel lab9 = new JLabel("Address :");
        lab9.setFont(new Font("Serif", Font.PLAIN, 25));
        lab9.setBounds(70, 480, 250, 30);

        JLabel lab11 = new JLabel("Teacher's Aaadhar NO :");
        lab11.setFont(new Font("Serif", Font.PLAIN, 25));
        lab11.setBounds(700, 160, 250, 30);

        JLabel lab12 = new JLabel("Family ID :");
        lab12.setFont(new Font("Serif", Font.PLAIN, 25));
        lab12.setBounds(700, 200, 250, 30);

        JLabel lab13 = new JLabel("Qualification :");
        lab13.setFont(new Font("Serif", Font.PLAIN, 25));
        lab13.setBounds(700, 240, 250, 30);

        JLabel lab14 = new JLabel("Experience :");
        lab14.setFont(new Font("Serif", Font.PLAIN, 25));
        lab14.setBounds(700, 280, 250, 30);

        JLabel lab15 = new JLabel("Account NO :");
        lab15.setFont(new Font("Serif", Font.PLAIN, 25));
        lab15.setBounds(700, 320, 250, 30);

        JLabel lab17 = new JLabel("Job_Leaving_Date :");
        lab17.setFont(new Font("Serif", Font.PLAIN, 25));
        lab17.setBounds(700, 360, 250, 30);

        // create TextFields and set their properties

        text = new JTextField();
        text.setFont(new Font("Serif", Font.PLAIN, 25));
        text.setBounds(580, 115, 300, 30);

        text1 = new JTextField();
        text1.setFont(new Font("Serif", Font.PLAIN, 25));
        text1.setBounds(300, 165, 300, 30);

        text2 = new JTextField();
        text2.setFont(new Font("Serif", Font.PLAIN, 25));
        text2.setBounds(300, 205, 300, 30);

        text3 = new JTextField();
        text3.setFont(new Font("Serif", Font.PLAIN, 25));
        text3.setBounds(300, 245, 300, 30);

        text4 = new PlaceholderTextField("YYYY-MM-DD");
        text4.setFont(new Font("Serif", Font.PLAIN, 25));
        text4.setBounds(300, 285, 300, 30);

        ch1 = new JComboBox<>(gender); // ComboBox for gender
        ch1.setBounds(300, 325, 300, 30);
        ch1.setSelectedIndex(-1);

        text5 = new JTextField();
        text5.setFont(new Font("Serif", Font.PLAIN, 25));
        text5.setBounds(300, 365, 300, 30);

        text7 = new JTextField();
        text7.setFont(new Font("Serif", Font.PLAIN, 25));
        text7.setBounds(300, 405, 300, 30);

        text8 = new PlaceholderTextField("YYYY-MM-DD");
        text8.setFont(new Font("Serif", Font.PLAIN, 25));
        text8.setBounds(300, 445, 300, 30);

        text9 = new JTextField();
        text9.setFont(new Font("Serif", Font.PLAIN, 25));
        text9.setBounds(300, 485, 300, 30);

        text11 = new JTextField();
        text11.setFont(new Font("Serif", Font.PLAIN, 25));
        text11.setBounds(950, 165, 300, 30);

        text12 = new JTextField();
        text12.setFont(new Font("Serif", Font.PLAIN, 25));
        text12.setBounds(950, 205, 300, 30);

        text13 = new JTextField();
        text13.setFont(new Font("Serif", Font.PLAIN, 25));
        text13.setBounds(950, 245, 300, 30);

        text14 = new JTextField();
        text14.setFont(new Font("Serif", Font.PLAIN, 25));
        text14.setBounds(950, 285, 300, 30);

        text15 = new JTextField();
        text15.setFont(new Font("Serif", Font.PLAIN, 25));
        text15.setBounds(950, 325, 300, 30);

        text16 = new JTextField();
        text16.setFont(new Font("Serif", Font.PLAIN, 25));
        text16.setBounds(950, 365, 300, 30);

        // Here add labels in frame
        frame2.add(label1);
        frame2.add(label2);
        frame2.add(lab);
        frame2.add(lab1);
        frame2.add(lab2);
        frame2.add(lab3);
        frame2.add(lab4);
        frame2.add(lab5);
        frame2.add(lab6);
        frame2.add(lab7);
        frame2.add(lab8);
        frame2.add(lab9);
        frame2.add(lab11);
        frame2.add(lab12);
        frame2.add(lab13);
        frame2.add(lab14);
        frame2.add(lab15);
        frame2.add(lab17);

        // here add textFields to frame
        frame2.add(text);
        frame2.add(text1);
        frame2.add(text2);
        frame2.add(text3);
        frame2.add(text4);
        frame2.add(text5);
        frame2.add(ch1);
        frame2.add(text7);
        frame2.add(text8);
        frame2.add(text9);
        frame2.add(text11);
        frame2.add(text12);
        frame2.add(text13);
        frame2.add(text14);
        frame2.add(text15);
        frame2.add(text16);

        // create buttons for submit ,clear and back

        sub = new JButton(" UPDATE ");
        sub.setBounds(400, 600, 200, 30);

        clear = new JButton(" CLEAR ");
        clear.setBounds(650, 600, 200, 30);

        back = new JButton("Back");
        back.setBounds(30, 30, 100, 30);

        // here add buttons to frame
        frame2.add(sub);
        frame2.add(clear);
        frame2.add(back);

        // set the properties of frame
        frame2.setSize(500, 500); // optional
        frame2.setLayout(null);
        frame2.getContentPane().setBackground(Color.YELLOW);
        frame2.setVisible(true);
        frame2.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Register the button component with frame
        back.addActionListener(this);
        sub.addActionListener(this);
        clear.addActionListener(this);

    }

    // All logics for this class are inside this method
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back) {

            new TeacherDataUpdate(Database, DBPassword, School_Name);
            frame2.dispose();
        }
        if (e.getSource() == clear) {
            text.setText(null);
            text1.setText(null);
            text2.setText(null);
            text3.setText(null);
            text4.setText(null);
            text5.setText(null);
            text7.setText(null);
            text8.setText(null);
            text9.setText(null);
            text11.setText(null);
            text12.setText(null);
            text13.setText(null);
            text14.setText(null);
            text15.setText(null);
            ch1.setSelectedIndex(-1);
            text16.setText(null);

        }

        if (e.getSource() == sub) {
            try {

                // connect app to Mysql to update Teacher Basic details
                Teacher_Id = text.getText();
                String url = "jdbc:mysql://localhost:3306/" + Database;
                String user = "root";

                Connection connection = DriverManager.getConnection(url, user, DBPassword);
                Statement statement = connection.createStatement();

                // Teacher Name
                if (text1.getText() != null && !text1.getText().trim().isEmpty()) {

                    ResultSet resultSet = statement.executeQuery(
                            " select * from Teacher_Registration where Teacher_Id = " + (String) text.getText()
                                    + " limit 1;");
                    while (resultSet.next()) {
                        Teacher_Name = resultSet.getString(2);
                    }

                    String query = "UPDATE Teacher_Registration SET Teacher_Name = ? WHERE Teacher_Name = ?";
                    PreparedStatement pstmt = connection.prepareStatement(query);
                    pstmt.setString(1, text1.getText());
                    pstmt.setString(2, Teacher_Name);
                    pstmt.executeUpdate();
                    message = true;
                }

                // Teacher Father Name
                if (text2.getText() != null && !text2.getText().trim().isEmpty()) {

                    ResultSet resultSet = statement.executeQuery(
                            " select * from Teacher_Registration where Teacher_Id = " + (String) text.getText()
                                    + " limit 1;");
                    while (resultSet.next()) {
                        Father_Name = resultSet.getString(3);
                    }

                    String query = "UPDATE Teacher_Registration SET Father_Name = ? WHERE Father_Name = ?";
                    PreparedStatement pstmt = connection.prepareStatement(query);
                    pstmt.setString(1, text2.getText());
                    pstmt.setString(2, Father_Name);
                    pstmt.executeUpdate();
                    message = true;
                }

                // Teacher Mother Name
                if (text3.getText() != null && !text3.getText().trim().isEmpty()) {

                    ResultSet resultSet = statement.executeQuery(
                            " select * from Teacher_Registration where Teacher_Id = " + (String) text.getText()
                                    + " limit 1;");
                    while (resultSet.next()) {
                        Mother_Name = resultSet.getString(4);
                    }

                    String query = "UPDATE Teacher_Registration SET Mother_Name = ? WHERE Mother_Name = ?";
                    PreparedStatement pstmt = connection.prepareStatement(query);
                    pstmt.setString(1, text3.getText());
                    pstmt.setString(2, Mother_Name);
                    pstmt.executeUpdate();
                    message = true;
                }

                // Dob
                if (text4.getText() != null && !text4.getText().trim().isEmpty()) {

                    ResultSet resultSet = statement.executeQuery(
                            " select * from Teacher_Registration where Teacher_Id = " + (String) text.getText()
                                    + " limit 1;");
                    while (resultSet.next()) {
                        Dob = resultSet.getString(5);
                    }

                    String query = "UPDATE Teacher_Registration SET Date_Of_Birth = ? WHERE Date_Of_Birth = ?";
                    PreparedStatement pstmt = connection.prepareStatement(query);
                    pstmt.setString(1, text4.getText());
                    pstmt.setString(2, Dob);
                    pstmt.executeUpdate();
                    message = true;
                }

                // Gender
                if (ch1.getSelectedIndex() != -1) {

                    ResultSet resultSet = statement.executeQuery(
                            " select * from Teacher_Registration where Teacher_Id = " + (String) text.getText()
                                    + " limit 1;");
                    while (resultSet.next()) {
                        Gender = resultSet.getString(6);
                    }

                    String query = "UPDATE Teacher_Registration SET Gender = ? WHERE Gender = ?";
                    PreparedStatement pstmt = connection.prepareStatement(query);
                    pstmt.setString(1, (String) ch1.getSelectedItem());
                    pstmt.setString(2, Gender);
                    pstmt.executeUpdate();
                    message = true;
                }

                // Mobile Number1
                if (text5.getText() != null && !text5.getText().trim().isEmpty()) {

                    ResultSet resultSet = statement.executeQuery(
                            " select * from Teacher_Registration where Teacher_Id = " + (String) text.getText()
                                    + " limit 1;");
                    while (resultSet.next()) {
                        Mob1 = resultSet.getString(7);
                    }

                    String query = "UPDATE Teacher_Registration  SET Mob1 = ? WHERE Mob1 = ?";
                    PreparedStatement pstmt = connection.prepareStatement(query);
                    pstmt.setString(1, text5.getText());
                    pstmt.setString(2, Mob1);
                    pstmt.executeUpdate();
                    message = true;
                }

                // Mobile Number2
                if (text7.getText() != null && !text7.getText().trim().isEmpty()) {

                    ResultSet resultSet = statement.executeQuery(
                            " select * from Teacher_Registration where Teacher_Id = " + (String) text.getText()
                                    + " limit 1;");
                    while (resultSet.next()) {
                        Mob2 = resultSet.getString(8);
                    }

                    String query = "UPDATE Teacher_Registration  SET Mob2= ? WHERE Mob2 = ?";
                    PreparedStatement pstmt = connection.prepareStatement(query);
                    pstmt.setString(1, text7.getText());
                    pstmt.setString(2, Mob2);
                    pstmt.executeUpdate();
                    message = true;
                }

                // Joining Date
                if (text8.getText() != null && !text8.getText().trim().isEmpty()) {

                    ResultSet resultSet = statement.executeQuery(
                            " select * from Teacher_Registration where Teacher_Id = " + (String) text.getText()
                                    + " limit 1;");
                    while (resultSet.next()) {
                        Joining_Date = resultSet.getString(9);
                    }

                    String query = "UPDATE Teacher_Registration  SET Joining_Date= ? WHERE Joining_Date = ?";
                    PreparedStatement pstmt = connection.prepareStatement(query);
                    pstmt.setString(1, text8.getText());
                    pstmt.setString(2, Joining_Date);
                    pstmt.executeUpdate();
                    message = true;
                }

                // Address
                if (text9.getText() != null && !text9.getText().trim().isEmpty()) {

                    ResultSet resultSet = statement.executeQuery(
                            " select * from Teacher_Registration where Teacher_Id = " + (String) text.getText()
                                    + " limit 1;");
                    while (resultSet.next()) {
                        Address = resultSet.getString(10);
                    }

                    String query = "UPDATE Teacher_Registration  SET Address= ? WHERE Address = ?";
                    PreparedStatement pstmt = connection.prepareStatement(query);
                    pstmt.setString(1, text9.getText());
                    pstmt.setString(2, Address);
                    pstmt.executeUpdate();
                    message = true;
                }

                // Teacher Aadhaar Number
                if (text11.getText() != null && !text11.getText().trim().isEmpty()) {

                    ResultSet resultSet1 = statement.executeQuery(
                            " select * from Teacher_Registration where Teacher_Id = " + (String) text.getText()
                                    + " limit 1;");
                    while (resultSet1.next()) {
                        Teacher_Aadhaar_No = resultSet1.getString(11);
                    }

                    String query = "UPDATE Teacher_Registration SET Teacher_Aadhaar_No= ? WHERE Teacher_Aadhaar_No = ?";
                    PreparedStatement pstmt = connection.prepareStatement(query);
                    pstmt.setString(1, text11.getText());
                    pstmt.setString(2, Teacher_Aadhaar_No);
                    pstmt.executeUpdate();
                    message = true;
                }

                // Family Id
                if (text12.getText() != null && !text12.getText().trim().isEmpty()) {

                    ResultSet resultSet2 = statement
                            .executeQuery(" select * from Teacher_Registration where Teacher_Id = "
                                    + (String) text.getText() + " limit 1;");
                    while (resultSet2.next()) {
                        Family_Id = resultSet2.getString(12);
                    }

                    String query = "UPDATE Teacher_Registration SET Family_Id= ? WHERE Family_Id = ?";
                    PreparedStatement pstmt = connection.prepareStatement(query);
                    pstmt.setString(1, text12.getText());
                    pstmt.setString(2, Family_Id);
                    pstmt.executeUpdate();
                    message = true;
                }

                // Qualification
                if (text13.getText() != null && !text13.getText().trim().isEmpty()) {

                    ResultSet resultSet3 = statement.executeQuery(
                            " select * from Teacher_Registration where Teacher_Id = " + (String) text.getText()
                                    + " limit 1;");
                    while (resultSet3.next()) {
                        Qualification = resultSet3.getString(13);
                    }

                    String query = "UPDATE Teacher_Registration SET Qualification= ? WHERE Qualification = ?";
                    PreparedStatement pstmt = connection.prepareStatement(query);
                    pstmt.setString(1, text13.getText());
                    pstmt.setString(2, Qualification);
                    pstmt.executeUpdate();
                    message = true;
                }

                // Experience
                if (text14.getText() != null && !text14.getText().trim().isEmpty()) {

                    ResultSet resultSet4 = statement.executeQuery(
                            " select * from Teacher_Registration where Teacher_Id = " + (String) text.getText()
                                    + " limit 1;");
                    while (resultSet4.next()) {
                        Experience = resultSet4.getString(14);
                    }

                    String query = "UPDATE Teacher_Registration SET Experience= ? WHERE Experience = ?";
                    PreparedStatement pstmt = connection.prepareStatement(query);
                    pstmt.setString(1, text14.getText());
                    pstmt.setString(2, Experience);
                    pstmt.executeUpdate();
                    message = true;
                }

                // Account No
                if (text15.getText() != null && !text15.getText().trim().isEmpty()) {

                    ResultSet resultSet5 = statement.executeQuery(
                            " select * from Teacher_Registration where Teacher_Id = " + (String) text.getText()
                                    + " limit 1;");
                    while (resultSet5.next()) {
                        Account_No = resultSet5.getString(15);
                    }

                    String query = "UPDATE Teacher_Registration SET Account_No= ? WHERE Account_No = ?";
                    PreparedStatement pstmt = connection.prepareStatement(query);
                    pstmt.setString(1, text15.getText());
                    pstmt.setString(2, Account_No);
                    pstmt.executeUpdate();
                    message = true;
                }

                // Job leaving Date
                if (text16.getText() != null && !text16.getText().trim().isEmpty()) {

                    ResultSet resultSet6 = statement.executeQuery(
                            " select * from Teacher_Registration where Teacher_Id = " + (String) text.getText()
                                    + " limit 1;");
                    while (resultSet6.next()) {
                        Job_Leaving_Date = resultSet6.getString(16);
                    }

                    String query = "UPDATE Teacher_Registration SET Job_Leaving_Date= ? WHERE Job_Leaving_Date = ?";
                    PreparedStatement pstmt = connection.prepareStatement(query);
                    pstmt.setString(1, text16.getText());
                    pstmt.setString(2, Job_Leaving_Date);
                    pstmt.executeUpdate();
                    message = true;
                }

                // if ((text1.getText() == null || text1.getText().trim().isEmpty())
                // && (text2.getText() == null || text2.getText().trim().isEmpty())
                // && (text3.getText() == null || text3.getText().trim().isEmpty())
                // && (text4.getText() == null || text4.getText().trim().isEmpty())
                // && (ch1.getSelectedIndex() == -1)
                // && (text5.getText() == null || text5.getText().trim().isEmpty())
                // && (text7.getText() == null || text7.getText().trim().isEmpty())
                // && (text8.getText() == null || text8.getText().trim().isEmpty())
                // && (text9.getText() == null || text9.getText().trim().isEmpty())
                // && (text11.getText() == null || text11.getText().trim().isEmpty())
                // && (text12.getText() == null || text12.getText().trim().isEmpty())
                // && (text13.getText() == null || text13.getText().trim().isEmpty())
                // && (text14.getText() == null || text14.getText().trim().isEmpty())
                // && (text15.getText() == null || text15.getText().trim().isEmpty())
                // && (text16.getText() == null || text16.getText().trim().isEmpty())) {
                // lab16.setText("Nothing is updated");
                // }

                if (message == true) {
                    JOptionPane.showMessageDialog(this, "Teacher Data Updated");

                }

                if (message == false) {
                    JOptionPane.showMessageDialog(this, "Nothing is Updated");

                }
                statement.close();
                connection.close();

            } catch (Exception a) {
                JOptionPane.showMessageDialog(this, "Teacher_Id is not filled or Wrong Enteries");

            }

        }
    }
}

/*
 * This class provides an user interface that allows the user to update Teacher
 * Salary Structure .
 * This class extends PlaceholderTextField to fill the background of textfield
 * that shows how to enter date and which textfield is optional.
 * This class extends ActionListener for event handling.
 */



class TeacherSalaryStructureUpdate extends PlaceholderTextField implements ActionListener {

    // Buttons :
    // sub to update the Teacher Salary Structure,
    // clear for clear the information that are filled,
    // Delete for delete the Teacher Salary Structure,
    // back for redirect the user to Home page of App
    JButton back, clear,Delete, sub;

    // TextField:
    // text1 allows the user to enter Teacher ID,
    // text2 allows the user to enter Teacher Name,
    // text3 allows the user to enter Teacher's Salary,
    // text4 allows the user to enter Month,
    // text5 allows the user to enter Year
    JTextField text1, text2, text3, text4, text5;

    // Database to Store the database name
    // DBPassword to store the mysql password
    // School_Name to store the School name
    // placeholder for background text of textfield
    String placeholder, Database, DBPassword, School_Name;


    // Salary to store salary of the Teacher from database,
    // Month to store month from database
    // Year to store year from daabase
    String Salary,Month,Year;

    boolean message = false; // message for the message that shows whether the data updated or not.

    JFrame frame; // frame in which all other components are added

    // constructor
    TeacherSalaryStructureUpdate(String placeholder, String database, String Password, String School) {

        super(placeholder);
        Database = database;
        DBPassword = Password;
        School_Name = School;

        // create a frame
        frame = new JFrame();

        // create labels and set their properties
        JLabel label = new JLabel();
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setSize(1250, 100);
        label.setText("UPDATE/DELETE  SALARY  STRUCTURE");
        label.setFont(new Font("Serif", Font.BOLD, 50));

        JLabel label2 = new JLabel("Note : Admin can update/Delete Teacher data using Teacher Id and Serial No");
        label2.setFont(new Font("Serif", Font.PLAIN, 22));
        label2.setBounds(330, 75, 700, 25);

        JLabel label3 = new JLabel("Note : Admin can find the Serial Number by click on View Teacher details button");
        label3.setFont(new Font("Serif", Font.PLAIN, 22));
        label3.setBounds(300, 110, 720, 25);


        JLabel lab1 = new JLabel("Serial No :");
        lab1.setFont(new Font("Serif", Font.PLAIN, 25));
        lab1.setBounds(150, 160, 250, 30);

        JLabel lab2 = new JLabel("Teacher Id :");
        lab2.setFont(new Font("Serif", Font.PLAIN, 25));
        lab2.setBounds(150, 200, 250, 30);

        JLabel lab3 = new JLabel("Salary :");
        lab3.setFont(new Font("Serif", Font.PLAIN, 25));
        lab3.setBounds(150, 240, 250, 30);

        JLabel lab4 = new JLabel("Month :");
        lab4.setFont(new Font("Serif", Font.PLAIN, 25));
        lab4.setBounds(150, 280, 250, 30);

        JLabel lab5 = new JLabel("Year :");
        lab5.setFont(new Font("Serif", Font.PLAIN, 25));
        lab5.setBounds(150, 320, 250, 30);

        // Here add label in frame
        frame.add(label);
        frame.add(label2);
        frame.add(label3);
        frame.add(lab1);
        frame.add(lab2);
        frame.add(lab3);
        frame.add(lab4);
        frame.add(lab5);

        // create TextFields and set their properties

        text1 = new JTextField();
        text1.setFont(new Font("Serif", Font.PLAIN, 25));
        text1.setBounds(450, 165, 300, 30);

        text2 = new JTextField();
        text2.setFont(new Font("Serif", Font.PLAIN, 25));
        text2.setBounds(450, 205, 300, 30);

        text3 = new JTextField();
        text3.setFont(new Font("Serif", Font.PLAIN, 25));
        text3.setBounds(450, 245, 300, 30);

        text4 = new PlaceholderTextField("eg : January");
        text4.setFont(new Font("Serif", Font.PLAIN, 25));
        text4.setBounds(450, 285, 300, 30);

        text5 = new JTextField();
        text5.setFont(new Font("Serif", Font.PLAIN, 25));
        text5.setBounds(450, 325, 300, 30);

        // create submit, clear and back button

        sub = new JButton(" UPDATE ");
        sub.setBounds(250, 600, 200, 30);

        clear = new JButton(" CLEAR ");
        clear.setBounds(500, 600, 200, 30);

        Delete = new JButton(" DELETE ");
        Delete.setBounds(750, 600, 200, 30);

        back = new JButton("Back");
        back.setBounds(30, 30, 100, 30);

        // here add textfields to frame
        frame.add(text1);
        frame.add(text2);
        frame.add(text3);
        frame.add(text4);
        frame.add(text5);

        // here add buttons to frame
        frame.add(sub);
        frame.add(clear);
        frame.add(Delete);
        frame.add(back);

        // set the properties of frame
        frame.setSize(500, 500);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.YELLOW);
        frame.setVisible(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Register the component with EventListener
        back.addActionListener(this);
        clear.addActionListener(this);
        Delete.addActionListener(this);
        sub.addActionListener(this);
    }

    // All logics for this class are inside this method
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back) {

            new TeacherDataUpdate(Database, DBPassword, School_Name);
            frame.dispose();
        }
        if (e.getSource() == clear) {
            text1.setText(null);
            text2.setText(null);
            text3.setText(null);
            text4.setText(null);
            text5.setText(null);

        }

        if(e.getSource()== Delete){
            try{
            
                String url = "jdbc:mysql://localhost:3306/" + Database;
                String user = "root";

                Connection connection = DriverManager.getConnection(url, user, DBPassword);
                PreparedStatement pstmt = connection.prepareStatement("delete from Teacher_Salary_Structure where Teacher_Id = ? and Serial_No = ? ");
                pstmt.setString(1, text2.getText());
                pstmt.setString(2, text1.getText());
                pstmt.executeUpdate();
                JOptionPane.showMessageDialog(this, "Data Deleted Successfully.");
                connection.close();
            }
            catch(Exception b){
                
                JOptionPane.showMessageDialog(this, "No Data Deleted.");
            }
        }
        if (e.getSource() == sub) {
            try {
                // connect app to mysql to update Student Academic details into the database.
                String url = "jdbc:mysql://localhost:3306/" + Database;
                String user = "root";

                Connection connection = DriverManager.getConnection(url, user, DBPassword);
                
               
                // Classw
                if (text3.getText() != null && !text3.getText().trim().isEmpty()) {

                    String selectQuery = "SELECT * FROM Teacher_Salary_Structure WHERE Teacher_Id = ? AND Serial_No = ?";
                    PreparedStatement selectStmt = connection.prepareStatement(selectQuery);
                    selectStmt.setString(1, text2.getText());
                    selectStmt.setString(2, text1.getText());
                
                    ResultSet resultSet = selectStmt.executeQuery();
                    while (resultSet.next()) {
                        Salary = resultSet.getString(4);
                    }

                    String query = "UPDATE  Teacher_Salary_Structure  SET Salary  = ? WHERE Salary = ?";
                    PreparedStatement pstmt = connection.prepareStatement(query);
                    pstmt.setString(1, text3.getText());
                    pstmt.setString(2,Salary);
                    pstmt.executeUpdate();

                    message = true;
                }

                // Subjects
                if (text4.getText() != null && !text4.getText().trim().isEmpty()) {

                    String selectQuery = "SELECT * FROM Teacher_Salary_Structure WHERE Teacher_Id = ? AND Serial_No = ?";
                    PreparedStatement selectStmt = connection.prepareStatement(selectQuery);
                    selectStmt.setString(1, text2.getText());
                    selectStmt.setString(2, text1.getText());
                
                    ResultSet resultSet = selectStmt.executeQuery();
                while (resultSet.next()) {
                    Month = resultSet.getString(5);
                }

                    String query = "UPDATE Teacher_Salary_Structure SET Month = ? WHERE Month = ?";
                    PreparedStatement pstmt = connection.prepareStatement(query);
                    pstmt.setString(1, text4.getText());
                    pstmt.setString(2, Month);
                    pstmt.executeUpdate();
                    message = true;
                }

                // Year
                if (text5.getText() != null && !text5.getText().trim().isEmpty()) {

                    String selectQuery = "SELECT * FROM Teacher_Salary_Structure WHERE Teacher_Id = ? AND Serial_No = ?";
                    PreparedStatement selectStmt = connection.prepareStatement(selectQuery);
                    selectStmt.setString(1, text2.getText());
                    selectStmt.setString(2, text1.getText());
                
                    ResultSet resultSet = selectStmt.executeQuery();
                while (resultSet.next()) {
                    Year = resultSet.getString(6);
                }
                    String query = "UPDATE Teacher_Salary_Structure SET Year = ? WHERE Year = ?";
                    PreparedStatement pstmt = connection.prepareStatement(query);
                    pstmt.setString(1, text5.getText());
                    pstmt.setString(2, Year);
                    pstmt.executeUpdate();
                    message = true;
                }



              
                
                connection.close();

                if(message == true){
                JOptionPane.showMessageDialog(this, "Teacher Data Updated."); // showing the message whether
                }
                                                                                       // the Student Details insert into the database or not .
                if(message == false){
                    JOptionPane.showMessageDialog(this, "Nothing is Updated.");
                }

            } catch (Exception a) {
                JOptionPane.showMessageDialog(this, "Something is missing or Incorrect enteries");
            }
        }

    }
}

/*
 * This class provides an user interface that allows the user to update Teacher
 * Salary details .
 * This class extends PlaceholderTextField to fill the background of textfield
 * that shows how to enter date and which textfield is optional.
 * This class extends ActionListener for event handling.
 */



 class TeacherSalaryUpdate extends PlaceholderTextField implements ActionListener {

    // Buttons :
    // sub to update the Teacher Salary Details,
    // clear for clear the information that are filled,
    // Delete for delete the Teacher Salary Structure,
    // back for redirect the user to Home page of App
    JButton back, clear,Delete, sub;

    // TextField:
    // text1 allows the user to enter Teacher ID,
    // text2 allows the user to enter Teacher Name,
    // text3 allows the user to enter Teacher's Salary,
    // text4 allows the user to enter Month,
    // text5 allows the user to enter Year
    JTextField text1, text2, text3, text4, text5;

    // Database to Store the database name
    // DBPassword to store the mysql password
    // School_Name to store the School name
    // placeholder for background text of textfield
    String placeholder, Database, DBPassword, School_Name;


    // Salary_Paid to store paid salary details of the Teacher from database,
    // Month to store month from database
    // Year to store year from daabase
    String Salary_Paid,Month,Year;

    boolean message = false; // message for the message that shows whether the data updated or not.

    JFrame frame; // frame in which all other components are added

    // constructor
    TeacherSalaryUpdate(String placeholder, String database, String Password, String School) {

        super(placeholder);
        Database = database;
        DBPassword = Password;
        School_Name = School;

        // create a frame
        frame = new JFrame();

        // create labels and set their properties
        JLabel label = new JLabel();
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setSize(1250, 100);
        label.setText("UPDATE/DELETE  SALARY  DETAILS");
        label.setFont(new Font("Serif", Font.BOLD, 50));

        JLabel label2 = new JLabel("Note : Admin can update/Delete Teacher data using Teacher Id and Serial No");
        label2.setFont(new Font("Serif", Font.PLAIN, 22));
        label2.setBounds(330, 75, 700, 25);


        
        JLabel label3 = new JLabel("Note : Admin can find the Serial Number by click on View Teacher details button");
        label3.setFont(new Font("Serif", Font.PLAIN, 22));
        label3.setBounds(300, 110, 720, 25);


        JLabel lab1 = new JLabel("Serial No :");
        lab1.setFont(new Font("Serif", Font.PLAIN, 25));
        lab1.setBounds(150, 160, 250, 30);

        JLabel lab2 = new JLabel("Teacher Id :");
        lab2.setFont(new Font("Serif", Font.PLAIN, 25));
        lab2.setBounds(150, 200, 250, 30);

        JLabel lab3 = new JLabel("Paid Salary :");
        lab3.setFont(new Font("Serif", Font.PLAIN, 25));
        lab3.setBounds(150, 240, 250, 30);

        JLabel lab4 = new JLabel("Month :");
        lab4.setFont(new Font("Serif", Font.PLAIN, 25));
        lab4.setBounds(150, 280, 250, 30);

        JLabel lab5 = new JLabel("Year :");
        lab5.setFont(new Font("Serif", Font.PLAIN, 25));
        lab5.setBounds(150, 320, 250, 30);

        // Here add label in frame
        frame.add(label);
        frame.add(label2);
        frame.add(label3);
        frame.add(lab1);
        frame.add(lab2);
        frame.add(lab3);
        frame.add(lab4);
        frame.add(lab5);

        // create TextFields and set their properties

        text1 = new JTextField();
        text1.setFont(new Font("Serif", Font.PLAIN, 25));
        text1.setBounds(450, 165, 300, 30);

        text2 = new JTextField();
        text2.setFont(new Font("Serif", Font.PLAIN, 25));
        text2.setBounds(450, 205, 300, 30);

        text3 = new JTextField();
        text3.setFont(new Font("Serif", Font.PLAIN, 25));
        text3.setBounds(450, 245, 300, 30);

        text4 = new PlaceholderTextField("eg : January");
        text4.setFont(new Font("Serif", Font.PLAIN, 25));
        text4.setBounds(450, 285, 300, 30);

        text5 = new JTextField();
        text5.setFont(new Font("Serif", Font.PLAIN, 25));
        text5.setBounds(450, 325, 300, 30);

        // create submit, clear and back button

        sub = new JButton(" UPDATE ");
        sub.setBounds(250, 600, 200, 30);

        clear = new JButton(" CLEAR ");
        clear.setBounds(500, 600, 200, 30);

        Delete = new JButton(" DELETE ");
        Delete.setBounds(750, 600, 200, 30);

        back = new JButton("Back");
        back.setBounds(30, 30, 100, 30);

        // here add textfields to frame
        frame.add(text1);
        frame.add(text2);
        frame.add(text3);
        frame.add(text4);
        frame.add(text5);

        // here add buttons to frame
        frame.add(sub);
        frame.add(clear);
        frame.add(Delete);
        frame.add(back);

        // set the properties of frame
        frame.setSize(500, 500);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.YELLOW);
        frame.setVisible(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Register the component with EventListener
        back.addActionListener(this);
        clear.addActionListener(this);
        Delete.addActionListener(this);
        sub.addActionListener(this);
    }

    // All logics for this class are inside this method
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back) {

            new TeacherDataUpdate(Database, DBPassword, School_Name);
            frame.dispose();
        }
        if (e.getSource() == clear) {
            text1.setText(null);
            text2.setText(null);
            text3.setText(null);
            text4.setText(null);
            text5.setText(null);

        }

        if(e.getSource()== Delete){
            try{
            
                String url = "jdbc:mysql://localhost:3306/" + Database;
                String user = "root";

                Connection connection = DriverManager.getConnection(url, user, DBPassword);
                PreparedStatement pstmt = connection.prepareStatement("delete from Teacher_Salary_Details where Teacher_Id = ? and Serial_No = ? ");
                pstmt.setString(1, text2.getText());
                pstmt.setString(2, text1.getText());
                pstmt.executeUpdate();
                JOptionPane.showMessageDialog(this, "Data Deleted Successfully.");
                connection.close();
            }
            catch(Exception b){
                
                JOptionPane.showMessageDialog(this, "No Data Deleted.");
            }
        }
        if (e.getSource() == sub) {
            try {
                // connect app to mysql to update Student Academic details into the database.
                String url = "jdbc:mysql://localhost:3306/" + Database;
                String user = "root";

                Connection connection = DriverManager.getConnection(url, user, DBPassword);
                
               
                // Classw
                if (text3.getText() != null && !text3.getText().trim().isEmpty()) {

                    String selectQuery = "SELECT * FROM Teacher_Salary_Details WHERE Teacher_Id = ? AND Serial_No = ?";
                    PreparedStatement selectStmt = connection.prepareStatement(selectQuery);
                    selectStmt.setString(1, text2.getText());
                    selectStmt.setString(2, text1.getText());
                
                    ResultSet resultSet = selectStmt.executeQuery();
                    while (resultSet.next()) {
                        Salary_Paid = resultSet.getString(4);
                    }

                    String query = "UPDATE  Teacher_Salary_Details  SET Salary_Paid  = ? WHERE Salary_Paid = ?";
                    PreparedStatement pstmt = connection.prepareStatement(query);
                    pstmt.setString(1, text3.getText());
                    pstmt.setString(2,Salary_Paid);
                    pstmt.executeUpdate();

                    message = true;
                }

                // Subjects
                if (text4.getText() != null && !text4.getText().trim().isEmpty()) {

                    String selectQuery = "SELECT * FROM Teacher_Salary_Details WHERE Teacher_Id = ? AND Serial_No = ?";
                    PreparedStatement selectStmt = connection.prepareStatement(selectQuery);
                    selectStmt.setString(1, text2.getText());
                    selectStmt.setString(2, text1.getText());
                
                    ResultSet resultSet = selectStmt.executeQuery();
                while (resultSet.next()) {
                    Month = resultSet.getString(5);
                }

                    String query = "UPDATE Teacher_Salary_Details SET Month = ? WHERE Month = ?";
                    PreparedStatement pstmt = connection.prepareStatement(query);
                    pstmt.setString(1, text4.getText());
                    pstmt.setString(2, Month);
                    pstmt.executeUpdate();
                    message = true;
                }

                // Year
                if (text5.getText() != null && !text5.getText().trim().isEmpty()) {

                    String selectQuery = "SELECT * FROM Teacher_Salary_Details WHERE Teacher_Id = ? AND Serial_No = ?";
                    PreparedStatement selectStmt = connection.prepareStatement(selectQuery);
                    selectStmt.setString(1, text2.getText());
                    selectStmt.setString(2, text1.getText());
                
                    ResultSet resultSet = selectStmt.executeQuery();
                while (resultSet.next()) {
                    Year = resultSet.getString(6);
                }
                    String query = "UPDATE Teacher_Salary_Details SET Year = ? WHERE Year = ?";
                    PreparedStatement pstmt = connection.prepareStatement(query);
                    pstmt.setString(1, text5.getText());
                    pstmt.setString(2, Year);
                    pstmt.executeUpdate();
                    message = true;
                }

                connection.close();

                if(message == true){
                JOptionPane.showMessageDialog(this, "Teacher Data Updated."); // showing the message whether
                }
                                                                                       // the Student Details insert into the database or not .
                if(message == false){
                    JOptionPane.showMessageDialog(this, "Nothing is Updated.");
                }

            } catch (Exception a) {
                JOptionPane.showMessageDialog(this, "Something is missing or Incorrect enteries");
            }
        }

    }
}


/*
 * This class provides an user interface that contains three buttons which
 * allows the user to update Employee details when they clicked.
 * This class extends ActionListener for event handling.
 */

 class EmployeeDataUpdate implements ActionListener {

    JFrame frame; // frame in which all other components are added.

    // Buttons :
    // button to update Employee Basic details,
    // button1 to update Employee's Salary Structure details,
    // button 2 to update the Employee's Salary Details,
    // back for redirect the user to Home page of App
    JButton button, button1, button2, back;

    // Database to Store the database name
    // DBPassword to store the mysql password
    // School_Name to store the School name
    String Database, DBPassword, School_Name;

    // constructor
    EmployeeDataUpdate(String database, String Password, String School) {

        Database = database;
        DBPassword = Password;
        School_Name = School;

        // Create a frame
        frame = new JFrame();

        // Create a label and set its properties

        JLabel label = new JLabel();
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setSize(1250, 100);
        label.setText(School_Name);
        label.setFont(new Font("Serif", Font.BOLD, 50));

        // create buttons and add to frame

        button = new JButton("Update Employee Basic Details");
        button.setBounds(480, 150, 350, 30);
        frame.add(button);

        button1 = new JButton("Update/Delete Employee Salary Structure ");
        button1.setBounds(480, 200, 350, 30);
        frame.add(button1);

        button2 = new JButton("Update/Delete Employee Salary Details");
        button2.setBounds(480, 250, 350, 30);
        frame.add(button2);

        back = new JButton("Back");
        back.setBounds(30, 30, 100, 30);
        frame.add(back);

        // Here add label in frame
        frame.add(label);

        // set the properties of frame
        frame.setSize(500, 500); // Optional
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.YELLOW);
        frame.setVisible(true);

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Registraton of buttons with action listener
        button.addActionListener(this);
        button1.addActionListener(this);
        button2.addActionListener(this);
        back.addActionListener(this);

    }

    // All logics for this class are inside this method.

    // @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == button) {

            new EmployeeUpdate(null,Database, DBPassword, School_Name);
            
            frame.dispose();

        }

        if (e.getSource() == button1) {

            new EmployeeSalaryStructureUpdate(null,Database, DBPassword, School_Name);
            
            frame.dispose();
        }
        if (e.getSource() == button2) {

            new EmployeeSalaryUpdate(null,Database, DBPassword, School_Name);
        
            frame.dispose();
        }
        if (e.getSource() == back) {

            new First(Database, DBPassword, School_Name);
            frame.dispose();
        }

    }
}


/*
 * This class provides an user interface that allows the user to update Employee
 * Salary Structure .
 * This class extends PlaceholderTextField to fill the background of textfield
 * that shows how to enter date and which textfield is optional.
 * This class extends ActionListener for event handling.
 */



 class EmployeeSalaryStructureUpdate extends PlaceholderTextField implements ActionListener {

    // Buttons :
    // sub to update the Employee Salary Structure,
    // clear for clear the information that are filled,
    // Delete for delete the Employee Salary Structure,
    // back for redirect the user to Home page of App
    JButton back, clear,Delete, sub;

    // TextField:
    // text1 allows the user to enter Employee ID,
    // text2 allows the user to enter Employee Name,
    // text3 allows the user to enter Employee's Salary,
    // text4 allows the user to enter Month,
    // text5 allows the user to enter Year
    JTextField text1, text2, text3, text4, text5;

    // Database to Store the database name
    // DBPassword to store the mysql password
    // School_Name to store the School name
    // placeholder for background text of textfield
    String placeholder, Database, DBPassword, School_Name;


    // Salary to store salary of the Employee from database,
    // Month to store month from database
    // Year to store year from daabase
    String Salary,Month,Year;

    boolean message = false; // message for the message that shows whether the data updated or not.

    JFrame frame; // frame in which all other components are added

    // constructor
    EmployeeSalaryStructureUpdate(String placeholder, String database, String Password, String School) {

        super(placeholder);
        Database = database;
        DBPassword = Password;
        School_Name = School;

        // create a frame
        frame = new JFrame();

        // create labels and set their properties
        JLabel label = new JLabel();
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setSize(1250, 100);
        label.setText("UPDATE/DELETE  SALARY  STRUCTURE");
        label.setFont(new Font("Serif", Font.BOLD, 50));

        JLabel label2 = new JLabel("Note : Admin can update/Delete Employee data using Employee Id and Serial No");
        label2.setFont(new Font("Serif", Font.PLAIN, 22));
        label2.setBounds(300, 75, 720, 25);

        
        JLabel label3 = new JLabel("Note : Admin can find the Serial Number by click on View Employee details button");
        label3.setFont(new Font("Serif", Font.PLAIN, 22));
        label3.setBounds(330, 110, 700, 25);


        JLabel lab1 = new JLabel("Serial No :");
        lab1.setFont(new Font("Serif", Font.PLAIN, 25));
        lab1.setBounds(150, 160, 250, 30);

        JLabel lab2 = new JLabel("Employee Id :");
        lab2.setFont(new Font("Serif", Font.PLAIN, 25));
        lab2.setBounds(150, 200, 250, 30);

        JLabel lab3 = new JLabel("Salary :");
        lab3.setFont(new Font("Serif", Font.PLAIN, 25));
        lab3.setBounds(150, 240, 250, 30);

        JLabel lab4 = new JLabel("Month :");
        lab4.setFont(new Font("Serif", Font.PLAIN, 25));
        lab4.setBounds(150, 280, 250, 30);

        JLabel lab5 = new JLabel("Year :");
        lab5.setFont(new Font("Serif", Font.PLAIN, 25));
        lab5.setBounds(150, 320, 250, 30);

        // Here add label in frame
        frame.add(label);
        frame.add(label2);
        frame.add(label3);
        frame.add(lab1);
        frame.add(lab2);
        frame.add(lab3);
        frame.add(lab4);
        frame.add(lab5);

        // create TextFields and set their properties

        text1 = new JTextField();
        text1.setFont(new Font("Serif", Font.PLAIN, 25));
        text1.setBounds(450, 165, 300, 30);

        text2 = new JTextField();
        text2.setFont(new Font("Serif", Font.PLAIN, 25));
        text2.setBounds(450, 205, 300, 30);

        text3 = new JTextField();
        text3.setFont(new Font("Serif", Font.PLAIN, 25));
        text3.setBounds(450, 245, 300, 30);

        text4 = new PlaceholderTextField("eg : January");
        text4.setFont(new Font("Serif", Font.PLAIN, 25));
        text4.setBounds(450, 285, 300, 30);

        text5 = new JTextField();
        text5.setFont(new Font("Serif", Font.PLAIN, 25));
        text5.setBounds(450, 325, 300, 30);

        // create submit, clear and back button

        sub = new JButton(" UPDATE ");
        sub.setBounds(250, 600, 200, 30);

        clear = new JButton(" CLEAR ");
        clear.setBounds(500, 600, 200, 30);

        Delete = new JButton(" DELETE ");
        Delete.setBounds(750, 600, 200, 30);

        back = new JButton("Back");
        back.setBounds(30, 30, 100, 30);

        // here add textfields to frame
        frame.add(text1);
        frame.add(text2);
        frame.add(text3);
        frame.add(text4);
        frame.add(text5);

        // here add buttons to frame
        frame.add(sub);
        frame.add(clear);
        frame.add(Delete);
        frame.add(back);

        // set the properties of frame
        frame.setSize(500, 500);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.YELLOW);
        frame.setVisible(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Register the component with EventListener
        back.addActionListener(this);
        clear.addActionListener(this);
        Delete.addActionListener(this);
        sub.addActionListener(this);
    }

    // All logics for this class are inside this method
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back) {

            new EmployeeDataUpdate(Database, DBPassword, School_Name);
            frame.dispose();
        }
        if (e.getSource() == clear) {
            text1.setText(null);
            text2.setText(null);
            text3.setText(null);
            text4.setText(null);
            text5.setText(null);

        }

        if(e.getSource()== Delete){
            try{
            
                String url = "jdbc:mysql://localhost:3306/" + Database;
                String user = "root";

                Connection connection = DriverManager.getConnection(url, user, DBPassword);
                PreparedStatement pstmt = connection.prepareStatement("delete from Employee_Salary_Structure where Employee_Id = ? and Serial_No = ? ");
                pstmt.setString(1, text2.getText());
                pstmt.setString(2, text1.getText());
                pstmt.executeUpdate();
                JOptionPane.showMessageDialog(this, "Data Deleted Successfully.");
                connection.close();
            }
            catch(Exception b){
                
                JOptionPane.showMessageDialog(this, "No Data Deleted.");
            }
        }
        if (e.getSource() == sub) {
            try {
                // connect app to mysql to update Student Academic details into the database.
                String url = "jdbc:mysql://localhost:3306/" + Database;
                String user = "root";

                Connection connection = DriverManager.getConnection(url, user, DBPassword);
                Statement statement = connection.createStatement();
               
                // Classw
                if (text3.getText() != null && !text3.getText().trim().isEmpty()) {

                    String selectQuery = "SELECT * FROM Employee_Salary_Structure WHERE Employee_Id = ? AND Serial_No = ?";
                    PreparedStatement selectStmt = connection.prepareStatement(selectQuery);
                    selectStmt.setString(1, text2.getText());
                    selectStmt.setString(2, text1.getText());
                
                    ResultSet resultSet = selectStmt.executeQuery();
                    while (resultSet.next()) {
                        Salary = resultSet.getString(4);
                    }

                    String query = "UPDATE  Employee_Salary_Structure  SET Salary  = ? WHERE Salary = ?";
                    PreparedStatement pstmt = connection.prepareStatement(query);
                    pstmt.setString(1, text3.getText());
                    pstmt.setString(2,Salary);
                    pstmt.executeUpdate();

                    message = true;
                }

                // Subjects
                if (text4.getText() != null && !text4.getText().trim().isEmpty()) {

                    String selectQuery = "SELECT * FROM Employee_Salary_Structure WHERE Employee_Id = ? AND Serial_No = ?";
                    PreparedStatement selectStmt = connection.prepareStatement(selectQuery);
                    selectStmt.setString(1, text2.getText());
                    selectStmt.setString(2, text1.getText());
                
                    ResultSet resultSet = selectStmt.executeQuery();
                while (resultSet.next()) {
                    Month = resultSet.getString(5);
                }

                    String query = "UPDATE Employee_Salary_Structure SET Month = ? WHERE Month = ?";
                    PreparedStatement pstmt = connection.prepareStatement(query);
                    pstmt.setString(1, text4.getText());
                    pstmt.setString(2, Month);
                    pstmt.executeUpdate();
                    message = true;
                }

                // Year
                if (text5.getText() != null && !text5.getText().trim().isEmpty()) {
                    String selectQuery = "SELECT * FROM Employee_Salary_Structure WHERE Employee_Id = ? AND Serial_No = ?";
                    PreparedStatement selectStmt = connection.prepareStatement(selectQuery);
                    selectStmt.setString(1, text2.getText());
                    selectStmt.setString(2, text1.getText());
                
                    ResultSet resultSet = selectStmt.executeQuery();
                while (resultSet.next()) {
                    Year = resultSet.getString(6);
                }
                    String query = "UPDATE Employee_Salary_Structure SET Year = ? WHERE Year = ?";
                    PreparedStatement pstmt = connection.prepareStatement(query);
                    pstmt.setString(1, text5.getText());
                    pstmt.setString(2, Year);
                    pstmt.executeUpdate();
                    message = true;
                }



              
                statement.close();
                connection.close();

                if(message == true){
                JOptionPane.showMessageDialog(this, "Employee Data Updated."); // showing the message whether
                }
                                                                                       // the Student Details insert into the database or not .
                if(message == false){
                    JOptionPane.showMessageDialog(this, "Nothing is Updated.");
                }

            } catch (Exception a) {
                JOptionPane.showMessageDialog(this, "Something is missing or Incorrect enteries");
            }
        }

    }
}

/*
 * This class provides an user interface that allows the user to update Employee
 * Salary details .
 * This class extends PlaceholderTextField to fill the background of textfield
 * that shows how to enter date and which textfield is optional.
 * This class extends ActionListener for event handling.
 */



 class EmployeeSalaryUpdate extends PlaceholderTextField implements ActionListener {

    // Buttons :
    // sub to update the Employee Salary Details,
    // clear for clear the information that are filled,
    // Delete for delete the Employee Salary Structure,
    // back for redirect the user to Home page of App
    JButton back, clear,Delete, sub;

    // TextField:
    // text1 allows the user to enter Employee ID,
    // text2 allows the user to enter Employee Name,
    // text3 allows the user to enter Employee's Salary,
    // text4 allows the user to enter Month,
    // text5 allows the user to enter Year
    JTextField text1, text2, text3, text4, text5;

    // Database to Store the database name
    // DBPassword to store the mysql password
    // School_Name to store the School name
    // placeholder for background text of textfield
    String placeholder, Database, DBPassword, School_Name;


    // Salary_Paid to store paid salary details of the Employee from database,
    // Month to store month from database
    // Year to store year from daabase
    String Salary_Paid,Month,Year;

    boolean message = false; // message for the message that shows whether the data updated or not.

    JFrame frame; // frame in which all other components are added

    // constructor
    EmployeeSalaryUpdate(String placeholder, String database, String Password, String School) {

        super(placeholder);
        Database = database;
        DBPassword = Password;
        School_Name = School;

        // create a frame
        frame = new JFrame();

        // create labels and set their properties
        JLabel label = new JLabel();
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setSize(1250, 100);
        label.setText("UPDATE/DELETE SALARY  DETAILS");
        label.setFont(new Font("Serif", Font.BOLD, 50));

        JLabel label2 = new JLabel("Note : Admin can update/Delete Employee data using Employee Id and Serial No");
        label2.setFont(new Font("Serif", Font.PLAIN, 22));
        label2.setBounds(300, 75, 720, 25);

        
        JLabel label3 = new JLabel("Note : Admin can find the Serial Number by click on View Employee details button");
        label3.setFont(new Font("Serif", Font.PLAIN, 22));
        label3.setBounds(330, 110, 700, 25);


        JLabel lab1 = new JLabel("Serial No :");
        lab1.setFont(new Font("Serif", Font.PLAIN, 25));
        lab1.setBounds(150, 160, 250, 30);

        JLabel lab2 = new JLabel("Employee Id :");
        lab2.setFont(new Font("Serif", Font.PLAIN, 25));
        lab2.setBounds(150, 200, 250, 30);

        JLabel lab3 = new JLabel("Paid Salary :");
        lab3.setFont(new Font("Serif", Font.PLAIN, 25));
        lab3.setBounds(150, 240, 250, 30);

        JLabel lab4 = new JLabel("Month :");
        lab4.setFont(new Font("Serif", Font.PLAIN, 25));
        lab4.setBounds(150, 280, 250, 30);

        JLabel lab5 = new JLabel("Year :");
        lab5.setFont(new Font("Serif", Font.PLAIN, 25));
        lab5.setBounds(150, 320, 250, 30);

        // Here add label in frame
        frame.add(label);
        frame.add(label2);
        frame.add(label3);
        frame.add(lab1);
        frame.add(lab2);
        frame.add(lab3);
        frame.add(lab4);
        frame.add(lab5);

        // create TextFields and set their properties

        text1 = new JTextField();
        text1.setFont(new Font("Serif", Font.PLAIN, 25));
        text1.setBounds(450, 165, 300, 30);

        text2 = new JTextField();
        text2.setFont(new Font("Serif", Font.PLAIN, 25));
        text2.setBounds(450, 205, 300, 30);

        text3 = new JTextField();
        text3.setFont(new Font("Serif", Font.PLAIN, 25));
        text3.setBounds(450, 245, 300, 30);

        text4 = new PlaceholderTextField("eg : January");
        text4.setFont(new Font("Serif", Font.PLAIN, 25));
        text4.setBounds(450, 285, 300, 30);

        text5 = new JTextField();
        text5.setFont(new Font("Serif", Font.PLAIN, 25));
        text5.setBounds(450, 325, 300, 30);

        // create submit, clear and back button

        sub = new JButton(" UPDATE ");
        sub.setBounds(250, 600, 200, 30);

        clear = new JButton(" CLEAR ");
        clear.setBounds(500, 600, 200, 30);

        Delete = new JButton(" DELETE ");
        Delete.setBounds(750, 600, 200, 30);

        back = new JButton("Back");
        back.setBounds(30, 30, 100, 30);

        // here add textfields to frame
        frame.add(text1);
        frame.add(text2);
        frame.add(text3);
        frame.add(text4);
        frame.add(text5);

        // here add buttons to frame
        frame.add(sub);
        frame.add(clear);
        frame.add(Delete);
        frame.add(back);

        // set the properties of frame
        frame.setSize(500, 500);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.YELLOW);
        frame.setVisible(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Register the component with EventListener
        back.addActionListener(this);
        clear.addActionListener(this);
        Delete.addActionListener(this);
        sub.addActionListener(this);
    }

    // All logics for this class are inside this method
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back) {

            new EmployeeDataUpdate(Database, DBPassword, School_Name);
            frame.dispose();
        }
        if (e.getSource() == clear) {
            text1.setText(null);
            text2.setText(null);
            text3.setText(null);
            text4.setText(null);
            text5.setText(null);

        }

        if(e.getSource()== Delete){
            try{
            
                String url = "jdbc:mysql://localhost:3306/" + Database;
                String user = "root";

                Connection connection = DriverManager.getConnection(url, user, DBPassword);
                PreparedStatement pstmt = connection.prepareStatement("delete from Employee_Salary_Details where Employee_Id = ? and Serial_No = ? ");
                pstmt.setString(1, text2.getText());
                pstmt.setString(2, text1.getText());
                pstmt.executeUpdate();
                JOptionPane.showMessageDialog(this, "Data Deleted Successfully.");
                connection.close();
            }
            catch(Exception b){
                
                JOptionPane.showMessageDialog(this, "No Data Deleted.");
            }
        }
        if (e.getSource() == sub) {
            try {
                // connect app to mysql to update Student Academic details into the database.
                String url = "jdbc:mysql://localhost:3306/" + Database;
                String user = "root";

                Connection connection = DriverManager.getConnection(url, user, DBPassword);
                
               
                // Classw
                if (text3.getText() != null && !text3.getText().trim().isEmpty()) {

                    String selectQuery = "SELECT * FROM Employee_Salary_Details WHERE Employee_Id = ? AND Serial_No = ?";
                    PreparedStatement selectStmt = connection.prepareStatement(selectQuery);
                    selectStmt.setString(1, text2.getText());
                    selectStmt.setString(2, text1.getText());
                
                    ResultSet resultSet = selectStmt.executeQuery();
                    while (resultSet.next()) {
                        Salary_Paid = resultSet.getString(4);
                    }

                    String query = "UPDATE  Employee_Salary_Details  SET Salary_Paid  = ? WHERE Salary_Paid = ?";
                    PreparedStatement pstmt = connection.prepareStatement(query);
                    pstmt.setString(1, text3.getText());
                    pstmt.setString(2,Salary_Paid);
                    pstmt.executeUpdate();

                    message = true;
                }

                // Subjects
                if (text4.getText() != null && !text4.getText().trim().isEmpty()) {

                    String selectQuery = "SELECT * FROM Employee_Salary_Details WHERE Employee_Id = ? AND Serial_No = ?";
                    PreparedStatement selectStmt = connection.prepareStatement(selectQuery);
                    selectStmt.setString(1, text2.getText());
                    selectStmt.setString(2, text1.getText());
                
                    ResultSet resultSet = selectStmt.executeQuery();
                while (resultSet.next()) {
                    Month = resultSet.getString(5);
                }

                    String query = "UPDATE Employee_Salary_Details SET Month = ? WHERE Month = ?";
                    PreparedStatement pstmt = connection.prepareStatement(query);
                    pstmt.setString(1, text4.getText());
                    pstmt.setString(2, Month);
                    pstmt.executeUpdate();
                    message = true;
                }

                // Year
                if (text5.getText() != null && !text5.getText().trim().isEmpty()) {

                    String selectQuery = "SELECT * FROM Employee_Salary_Details WHERE Employee_Id = ? AND Serial_No = ?";
                    PreparedStatement selectStmt = connection.prepareStatement(selectQuery);
                    selectStmt.setString(1, text2.getText());
                    selectStmt.setString(2, text1.getText());
                
                    ResultSet resultSet = selectStmt.executeQuery();
                while (resultSet.next()) {
                    Year = resultSet.getString(6);
                }
                    String query = "UPDATE Employee_Salary_Details SET Year = ? WHERE Year = ?";
                    PreparedStatement pstmt = connection.prepareStatement(query);
                    pstmt.setString(1, text5.getText());
                    pstmt.setString(2, Year);
                    pstmt.executeUpdate();
                    message = true;
                }

                connection.close();

                if(message == true){
                JOptionPane.showMessageDialog(this, "Employee Data Updated."); // showing the message whether
                }
                                                                                       // the Student Details insert into the database or not .
                if(message == false){
                    JOptionPane.showMessageDialog(this, "Nothing is Updated.");
                }

            } catch (Exception a) {
                JOptionPane.showMessageDialog(this, "Something is missing or Incorrect enteries");
            }
        }

    }
}






/*
 * This class provides an user interface that allows the user to update
 * Employee Basic details .
 * This class extends ActionListenerfor event handling.
 */
class EmployeeUpdate extends PlaceholderTextField implements ActionListener {

    // Buttons :
    // sub to insert the Employee basic details into the database,
    // clear for clear the information that are filled,
    // back for redirect the user to Home page of App
    JButton back, sub, clear;

    // TextField:
    // text allows the user to enter Employee ID,
    // text1 allows the user to enter Employee Name,
    // text2 allows the user to enter Employee's Father Name,
    // text3 allows the user to enter Employee's Mother Name,
    // text4 allows the user to enter Employee's Date of Birth,
    // ch1 allows the user to select Employee's Gender,
    // text5 allows the user to enter Employee's Mobile Number1 ,
    // text7 allows the user to enter Employee's Mobile Number2,
    // text8 allows the user to enter Employee's Joining Date,
    // text9 allows the user to enter Employee's Address,
    // text11 allows the user to enter Employee's Aadhaar No ,
    // text12 allows the user to enter Employee's ,Family Id
    // text13 allows the user to enter Employee's ,
    // text14 allows the user to enter Employee's Experience ,
    // text15 allows the user to enter Employee's Account No,
    // text16 allows the user to enter Employee's Job Leaving date
    JTextField text, text1, text2, text3, text4, text5, text7, text8, text9, text11, text12, text13, text14, text15,
            text16;

    String gender[] = { "Male", "Female", "Other" };

    JComboBox<String> ch1; // JComboBox ch1 to select the gender of Employee

    JFrame frame3; // frame in which all other componenets are added

    // Database to Store the database name
    // DBPassword to store the mysql password
    // School_Name to store the School name
    // placeholder for background text of textfield
    String placeholder, Database, DBPassword, School_Name;

    // String
    // Employee_Id store Employee's Id,
    // Employee_Name store Employee Name,
    // Father_Name store Employee's Father Name,
    // Mother_Name store Employee's Mother Name,
    // Dob store Employee's Date of Birth,
    // Gender store select Employee's Gender,
    // Mob1 store Employee's Mobile Number1 ,
    // Mob2 store Employee's Mobile Number2,
    // Joining_Date store Employee's Joining Date,
    // Address store Employee's Address,
    // Family_Id store Employee's Family ID,
    // Qualification store select Employee's Qualification ,
    // Experience store Employee's Experience,
    // Account_No store Employee's Mother's Account No,
    // Job_Leaving_Date store Employee's Job Leaving date
    String Employee_Id, Employee_Name, Father_Name, Mother_Name, Dob, Gender, Mob1, Mob2, Joining_Date, Address,
            Employee_Aadhaar_No, Family_Id, Qualification, Experience, Account_No, Job_Leaving_Date;

    boolean message = false; // for message that shows employee data updated or not
    // constructor

    EmployeeUpdate(String placeholder, String database, String Password, String School) {

        super(placeholder);
        Database = database;
        DBPassword = Password;
        School_Name = School;

        // create a frame
        frame3 = new JFrame();

        // create labels and set their properties
        JLabel label3 = new JLabel();
        label3.setHorizontalAlignment(JLabel.CENTER);
        label3.setSize(1250, 100);
        label3.setText("UPDATE   EMPLOYEE  DATA ");
        label3.setFont(new Font("Serif", Font.BOLD, 50));

        JLabel label2 = new JLabel("Note : Admin can update employee data using Employee id");
        label2.setFont(new Font("Serif", Font.PLAIN, 22));
        label2.setBounds(380, 75, 650, 25);

        JLabel lab = new JLabel("Employee  ID :");
        lab.setFont(new Font("Serif", Font.PLAIN, 25));
        lab.setBounds(410, 110, 250, 30);

        JLabel lab1 = new JLabel("Employee Name :");
        lab1.setFont(new Font("Serif", Font.PLAIN, 25));
        lab1.setBounds(70, 160, 250, 30);

        JLabel lab2 = new JLabel("Father Name :");
        lab2.setFont(new Font("Serif", Font.PLAIN, 25));
        lab2.setBounds(70, 200, 250, 30);

        JLabel lab3 = new JLabel("Mother Name :");
        lab3.setFont(new Font("Serif", Font.PLAIN, 25));
        lab3.setBounds(70, 240, 250, 30);

        JLabel lab4 = new JLabel("Date Of Birth :");
        lab4.setFont(new Font("Serif", Font.PLAIN, 25));
        lab4.setBounds(70, 280, 250, 30);

        JLabel lab5 = new JLabel("Gender :");
        lab5.setFont(new Font("Serif", Font.PLAIN, 25));
        lab5.setBounds(70, 320, 250, 30);

        JLabel lab6 = new JLabel("Mobile Number1 :");
        lab6.setFont(new Font("Serif", Font.PLAIN, 25));
        lab6.setBounds(70, 360, 250, 30);

        JLabel lab7 = new JLabel("Mobile Number2 :");
        lab7.setFont(new Font("Serif", Font.PLAIN, 25));
        lab7.setBounds(70, 400, 250, 30);

        JLabel lab8 = new JLabel("Joining Date :");
        lab8.setFont(new Font("Serif", Font.PLAIN, 25));
        lab8.setBounds(70, 440, 250, 30);

        JLabel lab9 = new JLabel("Address :");
        lab9.setFont(new Font("Serif", Font.PLAIN, 25));
        lab9.setBounds(70, 480, 250, 30);

        JLabel lab11 = new JLabel("Employee's Aaadhar NO:");
        lab11.setFont(new Font("Serif", Font.PLAIN, 25));
        lab11.setBounds(700, 160, 258, 30);

        JLabel lab12 = new JLabel("Family ID :");
        lab12.setFont(new Font("Serif", Font.PLAIN, 25));
        lab12.setBounds(700, 200, 250, 30);

        JLabel lab13 = new JLabel("Qualification :");
        lab13.setFont(new Font("Serif", Font.PLAIN, 25));
        lab13.setBounds(700, 240, 250, 30);

        JLabel lab14 = new JLabel("Job :");
        lab14.setFont(new Font("Serif", Font.PLAIN, 25));
        lab14.setBounds(700, 280, 250, 30);

        JLabel lab15 = new JLabel("Account NO :");
        lab15.setFont(new Font("Serif", Font.PLAIN, 25));
        lab15.setBounds(700, 320, 250, 30);

        JLabel lab17 = new JLabel("Job_Leaving_Date :");
        lab17.setFont(new Font("Serif", Font.PLAIN, 25));
        lab17.setBounds(700, 360, 250, 30);

        // create TextFields and set their properties

        text = new JTextField();
        text.setFont(new Font("Serif", Font.PLAIN, 25));
        text.setBounds(580, 115, 300, 30);

        text1 = new JTextField();
        text1.setFont(new Font("Serif", Font.PLAIN, 25));
        text1.setBounds(300, 165, 300, 30);

        text2 = new JTextField();
        text2.setFont(new Font("Serif", Font.PLAIN, 25));
        text2.setBounds(300, 205, 300, 30);

        text3 = new JTextField();
        text3.setFont(new Font("Serif", Font.PLAIN, 25));
        text3.setBounds(300, 245, 300, 30);

        text4 = new PlaceholderTextField("YYYY-MM-DD");
        text4.setFont(new Font("Serif", Font.PLAIN, 25));
        text4.setBounds(300, 285, 300, 30);

        ch1 = new JComboBox<>(gender); // ComboBox for gender
        ch1.setBounds(300, 325, 300, 30);
        ch1.setSelectedIndex(-1);

        text5 = new JTextField();
        text5.setFont(new Font("Serif", Font.PLAIN, 25));
        text5.setBounds(300, 365, 300, 30);

        text7 = new JTextField();
        text7.setFont(new Font("Serif", Font.PLAIN, 25));
        text7.setBounds(300, 405, 300, 30);

        text8 = new PlaceholderTextField("YYYY-MM-DD");
        text8.setFont(new Font("Serif", Font.PLAIN, 25));
        text8.setBounds(300, 445, 300, 30);

        text9 = new JTextField();
        text9.setFont(new Font("Serif", Font.PLAIN, 25));
        text9.setBounds(300, 485, 300, 30);

        text11 = new JTextField();
        text11.setFont(new Font("Serif", Font.PLAIN, 25));
        text11.setBounds(960, 165, 300, 30);

        text12 = new JTextField();
        text12.setFont(new Font("Serif", Font.PLAIN, 25));
        text12.setBounds(960, 205, 300, 30);

        text13 = new JTextField();
        text13.setFont(new Font("Serif", Font.PLAIN, 25));
        text13.setBounds(960, 245, 300, 30);

        text14 = new JTextField();
        text14.setFont(new Font("Serif", Font.PLAIN, 25));
        text14.setBounds(960, 285, 300, 30);

        text15 = new JTextField();
        text15.setFont(new Font("Serif", Font.PLAIN, 25));
        text15.setBounds(960, 325, 300, 30);

        text16 = new PlaceholderTextField("YYYY-MM-DD");
        text16.setFont(new Font("Serif", Font.PLAIN, 25));
        text16.setBounds(960, 365, 300, 30);

        // Here add labels to frame
        frame3.add(label3);
        frame3.add(label2);
        frame3.add(lab);
        frame3.add(lab1);
        frame3.add(lab2);
        frame3.add(lab3);
        frame3.add(lab4);
        frame3.add(lab5);
        frame3.add(lab6);
        frame3.add(lab7);
        frame3.add(lab8);
        frame3.add(lab9);
        frame3.add(lab11);
        frame3.add(lab12);
        frame3.add(lab13);
        frame3.add(lab14);
        frame3.add(lab15);
        frame3.add(lab17);

        // here add textFields to frame
        frame3.add(text);
        frame3.add(text1);
        frame3.add(text2);
        frame3.add(text3);
        frame3.add(text4);
        frame3.add(text5);
        frame3.add(ch1);
        frame3.add(text7);
        frame3.add(text8);
        frame3.add(text9);
        frame3.add(text11);
        frame3.add(text12);
        frame3.add(text13);
        frame3.add(text14);
        frame3.add(text15);
        frame3.add(text16);

        // create buttons for submit ,clear and back

        sub = new JButton(" UPDATE ");
        sub.setBounds(400, 600, 200, 30);

        clear = new JButton(" CLEAR ");
        clear.setBounds(650, 600, 200, 30);

        back = new JButton("Back");
        back.setBounds(30, 30, 100, 30);

        // here add buttons to frame
        frame3.add(sub);
        frame3.add(clear);
        frame3.add(back);

        // set the properties of frame
        frame3.setSize(500, 500); // optional
        frame3.setLayout(null);
        frame3.getContentPane().setBackground(Color.YELLOW);
        frame3.setVisible(true);

        frame3.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Register the buttons with Action listener
        back.addActionListener(this);
        sub.addActionListener(this);
        clear.addActionListener(this);
    }

    // All logics for this class are inside this method
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back) {

            new EmployeeDataUpdate(Database, DBPassword, School_Name);
            frame3.dispose();
        }
        if (e.getSource() == clear) {
            text.setText(null);
            text1.setText(null);
            text2.setText(null);
            text3.setText(null);
            text4.setText(null);
            text5.setText(null);
            text7.setText(null);
            text8.setText(null);
            text9.setText(null);
            text11.setText(null);
            text12.setText(null);
            text13.setText(null);
            text14.setText(null);
            ch1.setSelectedIndex(-1);
            text15.setText(null);
            text16.setText(null);
        }

        if (e.getSource() == sub) {
            try {

                // connect app to mysql to update Employee Basic Details update
                Employee_Id = text.getText();
                String url = "jdbc:mysql://localhost:3306/" + Database;
                String user = "root";

                Connection connection = DriverManager.getConnection(url, user, DBPassword);
                Statement statement = connection.createStatement();

                // Employee Name
                if (text1.getText() != null && !text1.getText().trim().isEmpty()) {

                    ResultSet resultSet = statement.executeQuery(
                            " select * from Employee_Registration where Employee_Id = " + (String) text.getText()
                                    + " limit 1;");
                    while (resultSet.next()) {
                        Employee_Name = resultSet.getString(2);
                    }

                    String query = "UPDATE Employee_Registration SET Employee_Name = ? WHERE Employee_Name = ?";
                    PreparedStatement pstmt = connection.prepareStatement(query);
                    pstmt.setString(1, text1.getText());
                    pstmt.setString(2, Employee_Name);
                    pstmt.executeUpdate();
                    message = true;
                }

                // Father Name
                if (text2.getText() != null && !text2.getText().trim().isEmpty()) {

                    ResultSet resultSet = statement.executeQuery(
                            " select * from Employee_Registration where Employee_Id = " + (String) text.getText()
                                    + " limit 1;");
                    while (resultSet.next()) {
                        Father_Name = resultSet.getString(3);
                    }

                    String query = "UPDATE Employee_Registration SET Father_Name = ? WHERE Father_Name = ?";
                    PreparedStatement pstmt = connection.prepareStatement(query);
                    pstmt.setString(1, text2.getText());
                    pstmt.setString(2, Father_Name);
                    pstmt.executeUpdate();
                    message = true;
                }

                // Mother Name
                if (text3.getText() != null && !text3.getText().trim().isEmpty()) {

                    ResultSet resultSet = statement.executeQuery(
                            " select * from Employee_Registration where Employee_Id = " + (String) text.getText()
                                    + " limit 1;");
                    while (resultSet.next()) {
                        Mother_Name = resultSet.getString(4);
                    }

                    String query = "UPDATE Employee_Registration SET Mother_Name = ? WHERE Mother_Name = ?";
                    PreparedStatement pstmt = connection.prepareStatement(query);
                    pstmt.setString(1, text3.getText());
                    pstmt.setString(2, Mother_Name);
                    pstmt.executeUpdate();
                    message = true;
                }

                // dob
                if (text4.getText() != null && !text4.getText().trim().isEmpty()) {

                    ResultSet resultSet = statement.executeQuery(
                            " select * from Employee_Registration where Employee_Id = " + (String) text.getText()
                                    + " limit 1;");
                    while (resultSet.next()) {
                        Dob = resultSet.getString(5);
                    }

                    String query = "UPDATE Employee_Registration SET Date_Of_Birth = ? WHERE Date_Of_Birth = ?";
                    PreparedStatement pstmt = connection.prepareStatement(query);
                    pstmt.setString(1, text4.getText());
                    pstmt.setString(2, Dob);
                    pstmt.executeUpdate();
                    message = true;
                }

                // Gender
                if (ch1.getSelectedIndex() != -1) {

                    ResultSet resultSet = statement.executeQuery(
                            " select * from Employee_Registration where Employee_Id = " + (String) text.getText()
                                    + " limit 1;");
                    while (resultSet.next()) {
                        Gender = resultSet.getString(6);
                    }

                    String query = "UPDATE Employee_Registration SET Gender = ? WHERE Gender = ?";
                    PreparedStatement pstmt = connection.prepareStatement(query);
                    pstmt.setString(1, (String) ch1.getSelectedItem());
                    pstmt.setString(2, Gender);
                    pstmt.executeUpdate();
                    message = true;
                }

                // Mobile number1
                if (text5.getText() != null && !text5.getText().trim().isEmpty()) {

                    ResultSet resultSet = statement.executeQuery(
                            " select * from Employee_Registration where Employee_Id = " + (String) text.getText()
                                    + " limit 1;");
                    while (resultSet.next()) {
                        Mob1 = resultSet.getString(7);
                    }

                    String query = "UPDATE Employee_Registration  SET Mob1 = ? WHERE Mob1 = ?";
                    PreparedStatement pstmt = connection.prepareStatement(query);
                    pstmt.setString(1, text5.getText());
                    pstmt.setString(2, Mob1);
                    pstmt.executeUpdate();
                    message = true;
                }

                // Mobile Number2
                if (text7.getText() != null && !text7.getText().trim().isEmpty()) {

                    ResultSet resultSet = statement.executeQuery(
                            " select * from Employee_Registration where Employee_Id = " + (String) text.getText()
                                    + " limit 1;");
                    while (resultSet.next()) {
                        Mob2 = resultSet.getString(8);
                    }

                    String query = "UPDATE Employee_Registration  SET Mob2= ? WHERE Mob2 = ?";
                    PreparedStatement pstmt = connection.prepareStatement(query);
                    pstmt.setString(1, text7.getText());
                    pstmt.setString(2, Mob2);
                    pstmt.executeUpdate();
                    message = true;
                }

                // Joining Date
                if (text8.getText() != null && !text8.getText().trim().isEmpty()) {

                    ResultSet resultSet = statement.executeQuery(
                            " select * from Employee_Registration where Employee_Id = " + (String) text.getText()
                                    + " limit 1;");
                    while (resultSet.next()) {
                        Joining_Date = resultSet.getString(9);
                    }

                    String query = "UPDATE Employee_Registration  SET Joining_Date= ? WHERE Joining_Date = ?";
                    PreparedStatement pstmt = connection.prepareStatement(query);
                    pstmt.setString(1, text8.getText());
                    pstmt.setString(2, Joining_Date);
                    pstmt.executeUpdate();
                    message = true;
                }

                // Address
                if (text9.getText() != null && !text9.getText().trim().isEmpty()) {

                    ResultSet resultSet = statement.executeQuery(
                            " select * from Employee_Registration where Employee_Id = " + (String) text.getText()
                                    + " limit 1;");
                    while (resultSet.next()) {
                        Address = resultSet.getString(10);
                    }

                    String query = "UPDATE Employee_Registration  SET Address= ? WHERE Address = ?";
                    PreparedStatement pstmt = connection.prepareStatement(query);
                    pstmt.setString(1, text9.getText());
                    pstmt.setString(2, Address);
                    pstmt.executeUpdate();
                    message = true;
                }

                // Employee Aadhaar No
                if (text11.getText() != null && !text11.getText().trim().isEmpty()) {

                    ResultSet resultSet1 = statement.executeQuery(
                            " select * from Employee_Registration where Employee_Id = " + (String) text.getText()
                                    + " limit 1;");
                    while (resultSet1.next()) {
                        Employee_Aadhaar_No = resultSet1.getString(11);
                    }

                    String query = "UPDATE Employee_Registration SET Employee_Aadhaar_No= ? WHERE Employee_Aadhaar_No = ?";
                    PreparedStatement pstmt = connection.prepareStatement(query);
                    pstmt.setString(1, text11.getText());
                    pstmt.setString(2, Employee_Aadhaar_No);
                    pstmt.executeUpdate();
                    message = true;
                }

                // Family Id
                if (text12.getText() != null && !text12.getText().trim().isEmpty()) {

                    ResultSet resultSet2 = statement
                            .executeQuery(" select * from Employee_Registration where Employee_Id = "
                                    + (String) text.getText() + " limit 1;");
                    while (resultSet2.next()) {
                        Family_Id = resultSet2.getString(12);
                    }

                    String query = "UPDATE Employee_Registration SET Family_Id= ? WHERE Family_Id = ?";
                    PreparedStatement pstmt = connection.prepareStatement(query);
                    pstmt.setString(1, text12.getText());
                    pstmt.setString(2, Family_Id);
                    pstmt.executeUpdate();
                    message = true;
                }

                // Qualification
                if (text13.getText() != null && !text13.getText().trim().isEmpty()) {

                    ResultSet resultSet3 = statement.executeQuery(
                            " select * from Employee_Registration where Employee_Id = " + (String) text.getText()
                                    + " limit 1;");
                    while (resultSet3.next()) {
                        Qualification = resultSet3.getString(13);
                    }

                    String query = "UPDATE Employee_Registration SET Qualification= ? WHERE Qualification = ?";
                    PreparedStatement pstmt = connection.prepareStatement(query);
                    pstmt.setString(1, text13.getText());
                    pstmt.setString(2, Qualification);
                    pstmt.executeUpdate();
                    message = true;
                }

                // Experience
                if (text14.getText() != null && !text14.getText().trim().isEmpty()) {

                    ResultSet resultSet4 = statement.executeQuery(
                            " select * from Employee_Registration where Employee_Id = " + (String) text.getText()
                                    + " limit 1;");
                    while (resultSet4.next()) {
                        Experience = resultSet4.getString(14);
                    }

                    String query = "UPDATE Employee_Registration SET Experience= ? WHERE Experience = ?";
                    PreparedStatement pstmt = connection.prepareStatement(query);
                    pstmt.setString(1, text14.getText());
                    pstmt.setString(2, Experience);
                    pstmt.executeUpdate();
                    message = true;
                }

                // Account No
                if (text15.getText() != null && !text15.getText().trim().isEmpty()) {

                    ResultSet resultSet5 = statement.executeQuery(
                            " select * from Employee_Registration where Employee_Id = " + (String) text.getText()
                                    + " limit 1;");
                    while (resultSet5.next()) {
                        Account_No = resultSet5.getString(15);
                    }

                    String query = "UPDATE Employee_Registration SET Account_No= ? WHERE Account_No = ?";
                    PreparedStatement pstmt = connection.prepareStatement(query);
                    pstmt.setString(1, text15.getText());
                    pstmt.setString(2, Account_No);
                    pstmt.executeUpdate();
                    message = true;
                }

                // Job Leaving Date
                if (text16.getText() != null && !text16.getText().trim().isEmpty()) {

                    ResultSet resultSet6 = statement.executeQuery(
                            " select * from Employee_Registration where Employee_Id = " + (String) text.getText()
                                    + " limit 1;");
                    while (resultSet6.next()) {
                        Job_Leaving_Date = resultSet6.getString(16);
                    }

                    String query = "UPDATE Employee_Registration SET Job_Leaving_Date= ? WHERE Job_Leaving_Date = ?";
                    PreparedStatement pstmt = connection.prepareStatement(query);
                    pstmt.setString(1, text16.getText());
                    pstmt.setString(2, Job_Leaving_Date);
                    pstmt.executeUpdate();
                    message = true;
                }

                //
                if (message == true) {
                    JOptionPane.showMessageDialog(this, "Employee Data Updated");
                }
                if (message == false) {
                    JOptionPane.showMessageDialog(this, "Nothing is Updated");
                }

                statement.close();
                connection.close();

            } catch (Exception a) {

                JOptionPane.showMessageDialog(this, "Employee_Id is not filled or Wrong Enteries");

            }

        }

    }
}

/*
 * This is the main class of application
 */
public class App {

    // main method
    public static void main(String[] args) {
        new Login(null, null, null, null, null, null);
    }
}
