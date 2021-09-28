package com.revature.putThread.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.Data;

import java.util.List;

@Data
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

    /**
     * @param id - The unique id of the thread being updated
     * @param subject - The title of the thread
     * @param ancestors - List of ancestor node ids
     * @param parent - The id of the subforum the thread belongs to
     * @param description - The content of the original post in a thread
     * @param child_count - The number of comments in a thread
     * @param date_created - The date and time the thread was originally created
     * @param owner - The ID of the thread's creator
     * @param tags - List of tag IDs pertaining to the thread
     */
    public Threads(String id, String subject, List<String> ancestors, String parent, String description, int child_count, String date_created, String owner, List<String> tags) {
        this.id = id;
        this.subject = subject;
        this.ancestors = ancestors;
        this.parent = parent;
        this.description = description;
        this.child_count = child_count;
        this.date_created = date_created;
        this.owner = owner;
        this.tags = tags;
    }
}
