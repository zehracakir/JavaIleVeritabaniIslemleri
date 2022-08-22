# VERİTABANI İŞLEMLERİ

## Java Database Connectivity (JDBC)

- JDBC, Java diliyle veri tabanlarına bağlanıp sorgu çalıştırmak, veri tabanı ile etkileşimli uygulamalar geliştirmek için ortaya çıkmış bir kütüphanedir. Java Standard Edition (JavaSE) içinde yer almaktadır. Dolayısıyla JDK içinde varsayılan olarak hazır kullanılabilir şekilde gelmektedir.
- JDBC API her veri tabanı yönetim sistemi için yazılmış olan sürücü kütüphanelerini kullanarak veri tabanı işlemlerini yapabilmeyi sağlar. Veri tabanıyla iletişimde kullanılabilecek birçok başka soyutlama olmasına karşın JDBC bunların hepsinin temelinde bulunur. Bu nedenle JDBC'nin standartını öğrenmek önemlidir.
- Java ile veri tabanı ile etkileşimde olan kodları yazdığınızda sürücü kütüphane örneğin MySQL’den Oracle veri tabanı sistemine geçse bile hiçbir değişiklik gerektirmeden kullanımını sağlar. Böylece, Java ile veri tabanıyla işlemler yapabilmek için yazdığınız kodları değiştirmeden dilediğiniz veri tabanı sistemiyle çalışabilirsiniz. Böyle yüksek bir soyutluluk sağlaması Java kodlarının yeniden kullanılabilirliğini arttırmaktadır. JDBC API ile veri tabanı bağlantısı oluşturup, tablolar üzerinde sorgu çalıştırabilirsiniz. Sorgulama, veri güncelleme, silme veya yeni kayıt ekleme işlemlerini yapabilirsiniz.

![Ekran görüntüsü 2022-08-15 172920.png](https://github.com/zehracakir/JavaIleVeritabaniIslemleri/blob/main/images/Ekran_grnts_2022-08-15_172920.png)

## ****5 Adımda JDBC’yi Kullanmak****

- JDBC ile veritabanı etkileşimi kabaca 5 adımdan oluşmaktadır.
1. Veri tabanı sürücü sınıfını kaydetmekle başlayabiliriz. JDBC API hangi veri tabanı sürücüsüyle çalışacağını bilmelidir. Bu nedenle yazılımı gerçekleştirirken bu bilgiyi belirtmek gerekir.

```java
Class.forName("org.postgresql.Driver");
```

2. Sürücü sınıf belirlendikten hemen sonra veri tabanı bağlantısı kurulur. Modern veri tabanı yönetim sistemlerinin istemci-sunucu (client-server) mimarisinden oluştuğunu bahsetmiştik.

```java
Connection dbConnection = DriverManager.getConnection(  
"jdbc:postgresql://host:port/database");
```

- DriverManager sınıfındaki “getConnection” fonksiyonu ile veri tabanına bir bağlantı açarız. Bu fonksiyona uzak bir sunucuda yer alan veri tabanı sunucu adresimizi vereceğiz. Bu adres IP ve Hostname şeklinde olabilir. Burada “localhost” sunucusundaki Postgresql sunucusuna bağlanacağımı söylüyorum. Ardından, bağlantı kuracak kullanıcının, kullanıcı adı ve şifresini veriyorum. Böylece, veri tabanı sunucusuna bir bağlantı açmış oluyorum.

3. Bağlantı kurulduktan sonra JDBC API ile artık sorgu çalıştırabiliriz.

```java
Statement statement = dbConnection.createStatement();
```

- “dbConnection” isimli nesne veri tabanı sunucusuyla aramızdaki bağlantı nesnesidir. Bu nesne üzerinden “createStatement” fonksiyonu ile sorgu hazırlayabileceğimiz “Statement” tipinde bir nesne alırız. SQL sorgumuzu bu sorgu üzerinden yapacağız.

4. Sorgu nesnemiz hazır olduğu için bir SQL ifadesi hazırlayıp veri tabanı sunucusunda bu sorgu işletilir ve sorgu sonucu “ResultSet” tipinde bir nesne ile geri döndürülür.

```java
ResultSet resultSet = statement.executeQuery("select * from employees");  
  
while(resultSet.next())
{  
        System.out.println(resultSet.getInt(1) + " " + resultSet.getString(2));  
}
```

- “executeQuery” fonksiyonu ile veri tabanı sunucusuna basit “SELECT” sorgusu attık. Bunun sonucunda “ResultSet” tipinde bir nesnede veri tabanından dönen kayıtlar geldi. Bu kayıtları bir “while” döngüsü ile işletip erişebiliriz. “next” fonksiyonu her çağrıldığında sonuç kümesinden bir satır kayıt getirir. Bu satır üzerindeki sütunlara indis yoluyla veya direkt sütun isimlerini vererek erişebiliriz.

5. İşimiz bitince veri tabanı sunucu ile olan bağlantımızı kapatırız.

```java
dbConnection.close();
```

## ****Veritabanı Bağlantısı****

- Yukarıda 5 adımda JDBC’den bahsettik, şimdi detaylarına bakalım. Şimdi onların detaylarına geçelim. En baştan veritabanına nasıl bağlantı kurulur...
1. Veritabanı bağlantısı oluşturmada ben VSCode, PostgreSQL ve Docker kullanacağım. Gerekli kurulumları yaptım.
2. Docker’ ı açıyoruz, herhangi bir işlem yapmamıza gerek yok bizim için şuan açık olması önemli.
3. PostgreSQL e geçiş yapıyoruz. 
4. Windows PowerShell’e aşağıdaki komutu yazıyoruz. 

```java
docker run --name docker_postgres -e POSTGRES_PASSWORD=1234 -d -p 15432:5432 -v $HOME/docker/volumes/postgres:/var/lib/postgresql/data postgres
```

![Ekran görüntüsü 2022-08-17 140505.png](https://github.com/zehracakir/JavaIleVeritabaniIslemleri/blob/main/images/Ekran_grnts_2022-08-17_140505.png)

1. Daha sonra PostgreSQL de yeni bir server oluşturuyoruz.

![Screenshot_1.png](https://github.com/zehracakir/JavaIleVeritabaniIslemleri/blob/main/images/Screenshot_1.png)

![Ekran görüntüsü 2022-08-17 141126.png](https://github.com/zehracakir/JavaIleVeritabaniIslemleri/blob/main/images/Ekran_grnts_2022-08-17_141126.png)

![Ekran görüntüsü 2022-08-17 141142.png](https://github.com/zehracakir/JavaIleVeritabaniIslemleri/blob/main/images/Ekran_grnts_2022-08-17_141142.png)

1. Yeni bir database oluşturuyoruz. Ben üniversite sisteminden ilerleyeceğim için “university” isminde bir database oluşturdum.

![Ekran görüntüsü 2022-08-17 141530.png](https://github.com/zehracakir/JavaIleVeritabaniIslemleri/blob/main/images/Ekran_grnts_2022-08-17_141530.png)

1. “student” isminde tablo ekliyorum.

![Ekran görüntüsü 2022-08-17 142044.png](https://github.com/zehracakir/JavaIleVeritabaniIslemleri/blob/main/images/Ekran_grnts_2022-08-17_142044.png)

1. —>Projede VSCode kullanacağım ve bir maven projesi olacak. Şimdi kısaca VSCode’a maven kurulumundan bahsedelim.
- [https://maven.apache.org/download.cgi](https://maven.apache.org/download.cgi) sayfasından bin.zip uzantılı maven dosyasını indirdim.
- Ortam değişiklenlerine maven’ı tanıttım.

![Ekran görüntüsü 2022-08-17 155925.png](https://github.com/zehracakir/JavaIleVeritabaniIslemleri/blob/main/images/Ekran_grnts_2022-08-17_155925.png)

![Ekran görüntüsü 2022-08-17 160028.png](https://github.com/zehracakir/JavaIleVeritabaniIslemleri/blob/main/images/Ekran_grnts_2022-08-17_160028.png)

- Cmd’ den mavenın versiyonunu sorgulayalım, sorun var mı yok mu bakalım.

![Screenshot_5.png](https://github.com/zehracakir/JavaIleVeritabaniIslemleri/blob/main/images/Screenshot_5.png)

- Maven’ı bilgisayarımıza tanıtmamızda bir sorun oluşmadığına göre artık VSCode’dan maven projesi açabiliriz.
- Ctrl+P ile açtığımz arama kısmında maven’ı aratıyoruz. “Maven:Create Maven Project” i seçiyoruz.

![Screenshot_2.png](https://github.com/zehracakir/JavaIleVeritabaniIslemleri/blob/main/images/Screenshot_2.png)

- Daha sonra aşağıdaki işlemleri yaparak bir maven projesi oluşturuyoruz. Paket,proje,dosya adı vs kısmını kendi projemize göre dolduracağımız için o kısımları ekran görüntüsü almadım.

![Screenshot_3.png](https://github.com/zehracakir/JavaIleVeritabaniIslemleri/blob/main/images/Screenshot_3.png)

![Screenshot_4.png](https://github.com/zehracakir/JavaIleVeritabaniIslemleri/blob/main/images/Screenshot_4.png)

- Sıradaki adımımız src içerisine .properties uzantılı bir dosya oluşturmak, ben [db.properties](http://db.properties) olarak oluşturdum. İçerisini şu şekilde doldurdum.

```java
driver=org.postgresql.Driver

url=jdbc:postgresql://localhost:15432/university

username=postgres

password=1234
```

- pom.xml dosyasının içerisine PostgreSQL jdbc yi eklememiz gerekiyor.

```java
<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
    <version>42.3.1</version>
</dependency>
```

- Daha sonra DBUtil adında bir sınıf yazalım. Bu sınıfı bağlantı kurmada ve bağlantı kapatmada kullanacağız.

```java
package com.zehra;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil {
    public static Connection getConnection() {
        Properties properties = new Properties();
        Connection connection = null;

        try {
            File file = new File(".");
            FileInputStream fileInputStream = new FileInputStream("\\src\\db.properties");
            properties.load(fileInputStream);
            String driver = properties.getProperty("driver");
            String url = properties.getProperty("url");
            String username = properties.getProperty("username");
            String password = properties.getProperty("password");

            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Driver is not available: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Connection could not be established:  " + e.getMessage());
        }
        return connection;

    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Connection is off");
        }
    }

}
```

# Veritabanı İşlemleri ve Statement Interface

Statement interface ile veri tabanı tabloları üzerinde SQL komutlarını çalıştırırız. SQL komutuna göre hangi fonksiyonu kullanacağımız değişebilir. Aşağıda bunlar listelenmiştir.

1. public ResultSet executeQuery(String sql): “SELECT” SQL komutuyla tablo üzerinde veri sorgulaması yapılacaksa bu fonksiyon kullanılmalıdır. Sorgu sonucunda “ResultSet” tipinde bir nesne dönecektir. Bu nesne içinde sorgulanan tablodan dönen kayıtlar olacaktır.
2. public int executeUpdate(String sql): DML ve DDL tipinde SQL komutları çalıştırılabilir. INSERT, UPDATE, DELETE gibi tablo verisinde değişikliği sağlayan komutlar işletilebilir. Tabloda veya veri tabanında yapısal değişikliğe yol açan CREATE, DROP gibi komutlar da çalıştırılabilir.
3. public boolean execute(String sql): Eğer çalıştıracağımız SQL komutu birden fazla sonuç dönecekse bu fonksiyonu kullanabiliriz.

1—>İlk olarak student tablomuzun bir örneğini çıkaralım içindeki özellikler postgresql tablomuzdaki özelliklerle aynı olmalı.

```java
package com.zehra;

public class Student {

    private int id;
    private String firstName;
    private String lastName;
    private int class1;

    public Student() {
    }

    public Student(String firstName, String lastName, int class1) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.class1 = class1;
    }

    public Student(int id, String firstName, String lastName, int class1) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.class1 = class1;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getfirstName() {
        return this.firstName;
    }

    public void setfirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getlastName() {
        return this.lastName;
    }

    public void setlastName(String lastName) {
        this.lastName = lastName;
    }

    public int getclass() {
        return this.class1;
    }

    public void setclass(int class1) {
        this.class1 = class1;
    }

}
```

1. Veri tabanında yapacağımız işler için bir Dao sınıfı yazacağız ve çalışan tablosunun Dao sınıfı StudentDao.java olsun.

```java
package com.zehra;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentDao {

    public StudentDao(int int1, String string, String string2, int int2) {
    }

    public StudentDao() {
    }

    public List<StudentDao> getStudent() {
        Connection connection = DBUtil.getConnection();
        List<StudentDao> listStudents = new ArrayList<>();
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from student");

            while (resultSet.next()) {
                new StudentDao(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
                        resultSet.getInt(4));

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            DBUtil.closeConnection(connection);
            try {
                if (statement != null)
                    statement.close();
                if (resultSet != null)
                    resultSet.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }

        return listStudents;

    }

    public void addStudent(Student student) {
        Connection connection = DBUtil.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection
                    .prepareStatement("insert into student(firstName,lastName,class) values (?,?,?)");
            preparedStatement.setString(1, student.getfirstName());
            preparedStatement.setString(2, student.getlastName());
            preparedStatement.setInt(3, student.getclass());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            DBUtil.closeConnection(connection);
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    int getclass() {
        return 0;
    }

    public void updateStudent(Student student) {
        Connection connection = DBUtil.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection
                    .prepareStatement("update student set firstName=?,lastName=?,class=? where id=?");
            preparedStatement.setString(1, student.getfirstName());
            preparedStatement.setString(2, student.getlastName());
            preparedStatement.setInt(3, student.getclass());
            preparedStatement.setInt(4, student.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            DBUtil.closeConnection(connection);
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

    }

    public void deleteStudent(Student student) {
        Connection connection = DBUtil.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("delete from student where id=?");
            preparedStatement.setInt(1, student.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            DBUtil.closeConnection(connection);
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public StudentDao getStudent(int id) {
        Connection connection = DBUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        StudentDao student = null;
        try {
            preparedStatement = connection
                    .prepareStatement("select * from student where id=?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                student = new StudentDao(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
                        resultSet.getInt(4));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            DBUtil.closeConnection(connection);
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }
        return student;

    }

    public String getfirstName() {
        return null;
    }

    public String getlastName() {
        return null;
    }

}
```

2-→ Temiz kod yazmak için oluşturduğumuz Dao sınıfını kullanarak bir Controller sınıfı yazalım.

```java
package com.zehra;

import java.util.List;

public class StudentController {
    

    private StudentDao studentDao;

    public StudentController() {
        studentDao = new StudentDao();
    }
    public List<Student> getAllStudent(){
        return studentDao.getStudent();
    }
    public void addStudent(Student student){
        studentDao.addStudent(student);
    }
    public void updateStudent(Student student){
        studentDao.updateStudent(student);
    }
    public void deleteStudent(Student student){
        studentDao.deleteStudent(student);
    }
    public StudentDao getStudent(int id){
        return studentDao.getStudent(id);
    }

}
```

## **JDBC ile Transaction Yönetimi**

- Yazdığımız uygulamalar bazen bir işlem çağrısıyla bir ya da birden fazla tabloda işlem yapabilir, bir ya da birden fazla sorguyu ardışık olarak çalıştırmayı gerektirebilmektedir. Bu durumlarda ardışık işlemler gerçekleşirken sürecin bir noktasında bir sorgu hata verebilir. Bu durumlarda süreçteki diğer işlemleri de geri almak isteyebiliriz. Bu durumlarda tablolara gitmek ve yaptığımız işlemlerin tersini yapmak bir hayli zor ve karmaşık olacaktır.
- Bunu bir diagram ile açıklamak istersek:

![Ekran görüntüsü 2022-08-22 230211.png](https://github.com/zehracakir/JavaIleVeritabaniIslemleri/blob/main/images/Ekran_grnts_2022-08-22_230211.png)

- şeklinde bir işlemimiz olsun. Yani kullanıcı bir butona basacak ve bu işlemler ardışık olarak gerçekleşecek.

![Ekran görüntüsü 2022-08-22 231823.png](https://github.com/zehracakir/JavaIleVeritabaniIslemleri/blob/main/images/Ekran_grnts_2022-08-22_231823.png)

- Diagramda görüldüğü gibi **Sorgu 3**'te bir nedenden dolayı bir hata gerçekleşti. Bu durumda verilerin bütünlüğünü ve doğruluğunu sağlamamız için işlemi geri almamız gerekir. Bunu yapmak için de JDBC bize bazı fonksiyonlar sağlamaktadır.
- Veri tabanlarında eğer, bir veri tabanı Transaction’ı başarılı ise “commit” edilir, değilse “rollback” edilir. “commit” edildiğinde değişiklikle kalıcı olarak veri tabanına gönderilir. “rollback” yapılırsa ise o ana kadar yapılmış olan tüm değişiklikler geri alınır.

```java
import java.sql.*;

public class DBConnect {
    public static final String DB_URL = "jdbc:mysql://localhost/school";
    public static final String DB_USERNAME = "root";
    public static final String DB_PASSWORD = "";

    public static void main(String[] args) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            conn.setAutoCommit(false);

            // PreparedStatement ile Insert İşlemi
            PreparedStatement pr = conn.prepareStatement("INSERT INTO student (student_fname,student_lname,student_class) VALUES (?,?,?)");
            pr.setString(1, "Harry");
            pr.setString(2, "Potter");
            pr.setString(3, "2");
            pr.executeUpdate();

            if (1 == 1) {
                throw new SQLException();
            }

            // PreparedStatement ile Insert İşlemi
            pr.setString(1, "Ron");
            pr.setString(2, "Weasley");
            pr.setString(3, "1");
            pr.executeUpdate();

            conn.commit();
            conn.close();
        } catch (SQLException ex) {
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

}

```

- “JDBC” veri tabanı bağlantılarında Transaction’lar otomatik olarak commit edilir. “setAutoCommit” fonksiyonu ile otomatik commit işlemi kapatılabilir. Böylece, Transaction yönetimini yazılımcının üstlenmesi gerekmektedir.
- Yukarıdaki örnekte false yaparak Transaction yönetimini üzerimize aldık. “commit()” fonksiyonunu çağırarak değişiklikleri kalıcı olarak gönderebiliriz.
- Sembolik olarak bir if bloğu içine hata fırlatan bir kod ekledik. O hata fırlatan kısmı açtığımızda veri tabanına bir kayıt eklemek komutu işletmiş olsa bile hata alındığından “rollback” fonksiyonu çağırıyoruz. Böylece, o ana kadar yapılmış olan değişikliklerin geri alınmasını sağlıyoruz.