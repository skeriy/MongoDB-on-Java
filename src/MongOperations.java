import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.*;
import org.bson.Document;

import javax.swing.*;

public class MongOperations {

    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> collection;

    public void createConnection(){
        // Подключение к монго
        this.mongoClient = new MongoClient( "localhost" , 27017 );
        this.database = mongoClient.getDatabase("Univer");
        //Получение коллекции
        this.collection = database.getCollection("students");
    }

    public void insertDoc(String id, String fN, String lN, String group, String age){
        // Создание документа
        Document document = new Document("id",id)
                .append("FirstName", fN)
                .append("LastName", lN)
                .append("Group", group)
                .append("Age", age);
        //Вставка документа
        this.collection.insertOne(document);
    }

    public void updateDoc(String id, String col, String value){
        //Обновление документа
        this.collection.updateOne(
                eq("id", id),
                combine(set(col, value)));
    }

    public void deleteDoc(String id){
        //Удаление документа
        this.collection.deleteOne(eq("id", id));
    }

    public JTable getTable(){

        FindIterable<Document> docs = this.collection.find(); //SELECT * FROM table;
        String[] columns = {"id", "FirstName", "LastName", "Group", "Age"};
        String[][] data = new String[(int)this.collection.count()][5];
        int i = 0,j;
        for (Document doc : docs) {
            j = 0;
            data[i][j] = doc.getString("id");
            System.out.print(data[i][j] + "\t");
            j++;
            data[i][j] = doc.getString("FirstName");
            System.out.print(data[i][j] + "\t");
            j++;
            data[i][j] = doc.getString("LastName");
            System.out.print(data[i][j] + "\t");
            j++;
            data[i][j] = doc.getString("Group");
            System.out.print(data[i][j] + "\t");
            j++;
            data[i][j] = doc.getString("Age");
            System.out.println(data[i][j]);
            i++;
        }
        return new JTable(data,columns);
    }

    //Возвращает таблицу для обновления таблицы в окне
    public JTable find_f(String col, String value){
        FindIterable<Document> docs = collection.find(eq(col, value));
        String[] columns = {"id", "FirstName", "LastName", "Group", "Age"};
        String[][] data = new String[(int)this.collection.count()][5];
        int i = 0, j;
        for (Document doc : docs) {
            j = 0;
            data[i][j] = doc.getString("id");
            System.out.print(data[i][j] + "\t");
            j++;
            data[i][j] = doc.getString("FirstName");
            System.out.print(data[i][j] + "\t");
            j++;
            data[i][j] = doc.getString("LastName");
            System.out.print(data[i][j] + "\t");
            j++;
            data[i][j] = doc.getString("Group");
            System.out.print(data[i][j] + "\t");
            j++;
            data[i][j] = doc.getString("Age");
            System.out.println(data[i][j]);
            i++;
        }
        return new JTable(data,columns);
    }

    //Получение доступа к значениям полей
    public void func() {
        FindIterable<Document> docs = collection.find(eq("Age", "20")); //SELECT * FROM students;
        String s = "";
        for (Document doc : docs) {
            s = doc.getString("Age");
            System.out.println(s);
        }
    }

}
