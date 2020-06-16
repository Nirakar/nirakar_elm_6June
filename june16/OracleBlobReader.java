package com.pax.elm;

import java.sql.*;
import java.io.*;


public class OracleBlobReader {
    String driverClass = "oracle.jdbc.driver.OracleDriver";

    Connection con;
    static int counter = 0;

    public void init(String connection, String user, String pass) throws ClassNotFoundException, SQLException, FileNotFoundException, IOException {
        Class.forName(driverClass);
        con = DriverManager.getConnection(connection, user, pass);

    }

    public void fetch(String sql, String extention, String out_path) throws SQLException, IOException {

        try {

            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rset = ps.executeQuery();


            ResultSetMetaData rsmd = rset.getMetaData();
            String type = rsmd.getColumnTypeName(1);


            while (rset.next()) {


                String file_name = out_path + rset.getBigDecimal(2) + extention;
                FileOutputStream file = null;
//                File char_file = null;

                try {

                    if (type == "CLOB") {

                        Reader char_stream = rset.getClob(1).getCharacterStream();
                        File char_file = new File(file_name);
                        FileWriter writer = new FileWriter(char_file);
                        char[] buffer = new char[2048];
                        try {
                            while (char_stream.read(buffer) > 0) {
                                writer.write(buffer);
                            }

                        } catch (IOException ex) {
                            System.out.println(ex);
                        } finally {
                            writer.close();
                        }

                        // Close stream to prevent memory leaks.
                        char_stream.close();


                    } else {
                        InputStream gif_data = rset.getBinaryStream(1);
                        file = new FileOutputStream(file_name);
                        int chunk;

                        byte[] data = new byte[55000];
                        while ((chunk = gif_data.read(data, 0, data.length)) != -1) {
                            file.write(data, 0, chunk);
                        }

                    }

                    counter += 1;
                    System.out.println("::: Downloaded successfully File Name :::  " + file_name);


                } catch (Exception e) {
                    String err = e.toString();
                    System.out.println(err);
                } finally {
                    if (file != null) {
                        file.close();
                    }

                }
            }


            // Close resources.
            rset.close();
            con.close();
        } catch (Exception e) {
            String err = e.toString();
            System.out.println(err);
        }

    }

    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {

        String connection = args[0];
        String user = args[1];
        String pass = args[2];
        String out_path = args[3];
        String extention = args[4];
        String sql = args[5];


        long startTime = System.currentTimeMillis();

        OracleBlobReader test = new OracleBlobReader();
        test.init(connection, user, pass);
        test.fetch(sql, extention, out_path);


        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        // converting it into seconds
        float sec = elapsedTime / 1000F;
        // converting it into minutes
        float minutes = sec / 60F;

        System.out.println("::: Images Downloaded Successfully ::: ");
        String count = null;
        System.out.println("::: Number of Objects Downloaded   ::: " + counter);
        System.out.println("::: Execution elapsed Time  in Seconds       ::: " + sec);
        System.out.println("::: Execution elapsed Time  in minutes       ::: " + minutes);


    }
}

// jar -cf read_blob.jar OracleJdbcTest.class /Users/nirakar/Downloads/OJDBC-Full/ojdbc7.jar
//  java -jar OracleBlobReader.jar  "jdbc:oracle:thin:@localhost:1521:xe"  "system" "oracle" "/Users/nirakar/Downloads/data-manager/Blob_reader/out_images/" ".png" "select data,rid from TEST.images where data is not null"
// java -jar Blob_reader.jar  "jdbc:oracle:thin:@localhost:1521:orcl"  "paxdeveloper" "pegasus123" "/Users/nirakar/Downloads/data-manager/Blob_reader/out_images/" ".png" "SELECT  BIO_IMAGE,per_ID  FROM PAXDEVELOPER.EXPORT_TABLE_1 WHERE BIO_IMAGE IS NOT NULL AND per_id IS NOT null"


//        java -jar OracleBlobReader.jar  "jdbc:oracle:thin:@localhost:1521:xe"  "system" "oracle" "/Users/nirakar/Downloads/data-manager/Blob_reader/out_images/" ".xml" "SELECT XML_FILE2 ,ID  FROM  TEST.xmls_tab"

//        "jdbc:oracle:thin:@localhost:1521:xe"
//        "system"
//        "oracle"
//        "/Users/nirakar/Downloads/data-manager/Blob_reader/out_images/"
//        ".xml"
//        "SELECT XML_FILE2, id FROM  TEST.xmls_tab"