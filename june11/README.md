## Updated Jars


##UploadFilesWithMetadata.jar
#Running command : 
```
Proper Usage is: java -jar ObjectUploader.jar <oracle_connection> <user_name> <password> <metadata_path>  <metadata_filename> <object_extention> <sql_query> <s3_endpoint> <accessKey> <secretKey> <bucket_name> <S3_folder_name>


 java -jar OracleBlobReader.jar "/Users/nirakar/Downloads/data-manager/Blob_reader/out_images/"  ".jpg" "/Users/nirakar/Downloads/data-manager/Blob_reader/image_metadata/" "file_images" "https://play.min.io" "Q3AM3UQ867SPQQA43P2F"  "zuf+tfteSlswRu7BJ86wekitnifILbZam1KYY3TG" "nira" "june11"
```
#Parameters to pass in the config file
```
        "/Users/nirakar/Downloads/data-manager/Blob_reader/out_images/"
        ".jpg"
        "/Users/nirakar/Downloads/data-manager/Blob_reader/image_metadata/"
        "file_images"
        "https://play.min.io"
        "Q3AM3UQ867SPQQA43P2F"
        "zuf+tfteSlswRu7BJ86wekitnifILbZam1KYY3TG"
        "nira"
        "june11"
```






#just add a new argument as folder name
```
//  java -jar OracleBlobReader.jar "jdbc:oracle:thin:@localhost:1521:xe"  "system" "oracle" "/Users/nirakar/Downloads/data-manager/Blob_reader/image_metadata/" "oracle_images_info" ".jpg" "select data,rid from TEST.images where data is not null" "https://play.min.io" "Q3AM3UQ867SPQQA43P2F" "zuf+tfteSlswRu7BJ86wekitnifILbZam1KYY3TG" "nira"


//        "jdbc:oracle:thin:@localhost:1521:xe"
//        "system"
//        "oracle"
//        "/Users/nirakar/Downloads/data-manager/Blob_reader/out_images/"
//        "oracle_images"
//        ".jpg"
//        "select data,rid from TEST.images where data is not null"
//        "https://play.min.io"
//        "Q3AM3UQ867SPQQA43P2F"
//        "zuf+tfteSlswRu7BJ86wekitnifILbZam1KYY3TG"
//        "nira"
//        "june11"

```
