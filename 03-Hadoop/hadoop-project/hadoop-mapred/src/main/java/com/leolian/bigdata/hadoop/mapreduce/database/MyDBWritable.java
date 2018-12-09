package com.leolian.bigdata.hadoop.mapreduce.database;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapreduce.lib.db.DBWritable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @description:
 * @author lianliang
 * @date 2018/12/9 14:16
 */
public class MyDBWritable implements DBWritable, Writable {
    private int id;
    private String words;
    
    private String word;
    private int count;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "MyDBWritable{" +
                "id=" + id +
                ", words='" + words + '\'' +
                '}';
    }

    /**
     * 
     * @param dataOutput
     * @throws IOException
     */
    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeInt(id);
        dataOutput.writeUTF(words);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        this.id = dataInput.readInt();
        this.words = dataInput.readUTF();
    }

    /**
     * 往数据库写
     * @param statement
     * @throws SQLException
     */
    @Override
    public void write(PreparedStatement statement) throws SQLException {
        statement.setString(1, word);
        statement.setInt(2, count);
    }

    /**
     * 从数据库读
     * @param resultSet
     * @throws SQLException
     */
    @Override
    public void readFields(ResultSet resultSet) throws SQLException {
        this.id = resultSet.getInt("id");
        this.words = resultSet.getString("words");
    }
}
