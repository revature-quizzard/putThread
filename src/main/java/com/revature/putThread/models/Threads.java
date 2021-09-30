package com.revature.putThread.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamoDBTable(tableName = "ForumNodes")
public class Threads {


    @DynamoDBHashKey
    private String id;

    @DynamoDBAttribute
    private String subject;

    @DynamoDBAttribute
    private List<String> ancestors;

    @DynamoDBAttribute
    private String parent;

    @DynamoDBAttribute
    private String description;

    @DynamoDBAttribute
    private int child_count;

    @DynamoDBAttribute
    private String date_created;

    @DynamoDBAttribute
    private String owner;

    @DynamoDBAttribute
    private List<String> tags;

}
