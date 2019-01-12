package com.leolian.bigdata.avro.demo;

import org.apache.avro.Schema;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * @description: 
 * @author lianliang
 * @date 2019/1/12 18:04
 */
public class AvroDemo {

    @Test
    public void writeWithSchema() throws Exception {
        Schema schema = new Schema.Parser().parse(new File("F:/Java-Dev/taobao-it18/05-Avro/Emp.avsc"));
        GenericRecord record = new GenericData.Record(schema);
        record.put("name", "Tom");
        record.put("id", 13);
        DatumWriter<Object> datumWriter = new SpecificDatumWriter<>(schema);
        DataFileWriter<Object> dataFileWriter = new DataFileWriter<>(datumWriter);
        dataFileWriter.create(schema, new File("F:/Java-Dev/taobao-it18/05-Avro/Employee.avro"));
        dataFileWriter.append(record);
        dataFileWriter.append(record);
        dataFileWriter.close();
    }

    @Test
    public void readWithSchema() throws Exception {
        Schema schema = new Schema.Parser().parse(new File("F:/Java-Dev/taobao-it18/05-Avro/Emp.avsc"));
        GenericRecord record = new GenericData.Record(schema);
        DatumReader<Object> datumReader = new SpecificDatumReader<>(schema);
        DataFileReader<Object> dataFileReader = new DataFileReader<Object>(new File("F:/Java-Dev/taobao-it18/05-Avro/Employee.avro"), datumReader);
        while (dataFileReader.hasNext()) {
            record = (GenericRecord) dataFileReader.next();
            System.out.println(record.get("name"));
        }
        dataFileReader.close();
    }
    
}
