package com.revature.putThread.services;

import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.revature.putThread.models.Threads;
import com.revature.putThread.repositories.ThreadsRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class ThreadsServiceTestSuite {

    ThreadsService sut;
    ThreadsRepo mockThreadsRepo;
    LambdaLogger mockLambdaLogger;

    @BeforeEach
    void setUp(){
        mockThreadsRepo = mock(ThreadsRepo.class);
        mockLambdaLogger = mock(LambdaLogger.class);
        sut = new ThreadsService(mockThreadsRepo, mockLambdaLogger);
    }

    @AfterEach
    void tearDown(){
        sut = null;
        reset(mockThreadsRepo);
    }

    /**
     * author - Charles Mettee
     */
    @Test
    public void isValid_returnsFalse_givenNullThread(){
        Threads thread = null;

        boolean testResult = sut.isValid(thread);

        assertFalse(testResult);
    }

    /**
     * author - Charles Mettee
     */
    @Test
    public void isValid_returnsFalse_givenNullOrEmptyId(){
        Threads thread1 = new Threads(null, "subject", Arrays.asList("parentId"), "parentId",
                "description", 0, "2021-09-28T12:05:13.628", "ownerId", Arrays.asList("tag1Id", "tag2Id"));
        Threads thread2 = new Threads("", "subject", Arrays.asList("parentId"), "parentId",
                "description", 0, "2021-09-28T12:05:13.628", "ownerId", Arrays.asList("tag1Id", "tag2Id"));
        Threads thread3 = new Threads("     ", "subject", Arrays.asList("parentId"), "parentId",
                "description", 0, "2021-09-28T12:05:13.628", "ownerId", Arrays.asList("tag1Id", "tag2Id"));

        boolean testResult1 = sut.isValid(thread1);
        boolean testResult2 = sut.isValid(thread2);
        boolean testResult3 = sut.isValid(thread3);

        assertFalse(testResult1);
        assertFalse(testResult2);
        assertFalse(testResult3);
    }


    /**
     * author - Charles Mettee
     */
    @Test
    public void isValid_returnsFalse_givenNullOrEmptySubject(){
        Threads thread1 = new Threads("threadId", null, Arrays.asList("parentId"), "parentId",
                "description", 0, "2021-09-28T12:05:13.628", "ownerId", Arrays.asList("tag1Id", "tag2Id"));
        Threads thread2 = new Threads("threadId", "", Arrays.asList("parentId"), "parentId",
                "description", 0, "2021-09-28T12:05:13.628", "ownerId", Arrays.asList("tag1Id", "tag2Id"));
        Threads thread3 = new Threads("threadId", "    ", Arrays.asList("parentId"), "parentId",
                "description", 0, "2021-09-28T12:05:13.628", "ownerId", Arrays.asList("tag1Id", "tag2Id"));

        boolean testResult1 = sut.isValid(thread1);
        boolean testResult2 = sut.isValid(thread2);
        boolean testResult3 = sut.isValid(thread3);

        assertFalse(testResult1);
        assertFalse(testResult2);
        assertFalse(testResult3);
    }


    /**
     * author - Charles Mettee, Sean Smith
     */
    @Test
    public void isValid_returnsFalse_givenAncestorsNullOrNotSizeOne(){
        Threads thread1 = new Threads("threadId", "subject", null, "parentId",
                "description", 0, "2021-09-28T12:05:13.628", "ownerId", Arrays.asList("tag1Id", "tag2Id"));
        Threads thread2 = new Threads("threadId", "subject", Arrays.asList("parentId", "ancestorId"), "parentId",
                "description", 0, "2021-09-28T12:05:13.628", "ownerId", Arrays.asList("tag1Id", "tag2Id"));

        boolean testResult1 = sut.isValid(thread1);
        boolean testResult2 = sut.isValid(thread2);

        assertFalse(testResult1);
        assertFalse(testResult2);

    }

    /**
     * author - Sean Smith
     */
    @Test
    public void isValid_returnsFalse_givenNullOrEmptyParent(){
        Threads thread1 = new Threads("threadId", "subject", Arrays.asList("parentId"), null,
                "description", 0, "2021-09-28T12:05:13.628", "ownerId", Arrays.asList("tag1Id", "tag2Id"));
        Threads thread2 = new Threads("threadId", "", Arrays.asList("parentId"), "",
                "description", 0, "2021-09-28T12:05:13.628", "ownerId", Arrays.asList("tag1Id", "tag2Id"));


        boolean testResult1 = sut.isValid(thread1);
        boolean testResult2 = sut.isValid(thread2);

        assertFalse(testResult1);
        assertFalse(testResult2);
    }

    /**
     * author - Sean Smith
     */
    @Test
    public void isValid_returnsFalse_givenNullOrEmptyDescription(){
        Threads thread1 = new Threads("threadId", "subject", Arrays.asList("parentId"), "parentId",
                null, 0, "2021-09-28T12:05:13.628", "ownerId", Arrays.asList("tag1Id", "tag2Id"));
        Threads thread2 = new Threads("threadId", "", Arrays.asList("parentId"), "parentId",
                "", 0, "2021-09-28T12:05:13.628", "ownerId", Arrays.asList("tag1Id", "tag2Id"));


        boolean testResult1 = sut.isValid(thread1);
        boolean testResult2 = sut.isValid(thread2);

        assertFalse(testResult1);
        assertFalse(testResult2);
    }

    /**
     * author - Sean Smith
     */
    @Test
    public void isValid_returnsFalse_givenChildCountLessThanZero(){
        Threads thread1 = new Threads("threadId", "subject", Arrays.asList("parentId"), "parentId",
                "description", -1, "2021-09-28T12:05:13.628", "ownerId", Arrays.asList("tag1Id", "tag2Id"));


        boolean testResult1 = sut.isValid(thread1);

        assertFalse(testResult1);

    }

    /**
     * author - Sean Smith
     */
    @Test
    public void isValid_returnsFalse_givenNullOrEmptyDateCreated(){
        Threads thread1 = new Threads("threadId", "subject", Arrays.asList("parentId"), "parentId",
                "description", 0, "", "ownerId", Arrays.asList("tag1Id", "tag2Id"));
        Threads thread2= new Threads("threadId", "", Arrays.asList("parentId"), "parentId",
                "description", 0, null, "ownerId", Arrays.asList("tag1Id", "tag2Id"));


        boolean testResult1 = sut.isValid(thread1);
        boolean testResult2 = sut.isValid(thread2);

        assertFalse(testResult1);
        assertFalse(testResult2);

    }

    /**
     * author - Sean Smith
     */
    @Test
    public void isValid_returnsFalse_givenNullOrEmptyOwner(){
        Threads thread1 = new Threads("threadId", "subject", Arrays.asList("parentId"), "parentId",
                "description", 0, "2021-09-28T12:05:13.628", "", Arrays.asList("tag1Id", "tag2Id"));
        Threads thread2 = new Threads("threadId", "", Arrays.asList("parentId"), "parentId",
                "description", 0, "2021-09-28T12:05:13.628", null, Arrays.asList("tag1Id", "tag2Id"));


        boolean testResult1 = sut.isValid(thread1);
        boolean testResult2 = sut.isValid(thread2);

        assertFalse(testResult1);
        assertFalse(testResult2);

    }

    /**
     * author - Charles Mettee
     */
    @Test
    public void isValid_returnsTrue_givenValidThread(){
        Threads thread = new Threads("threadId", "subject", Arrays.asList("parentId"), "parentId",
                "description", 0, "2021-09-28T12:05:13.628", "ownerId", Arrays.asList("tag1Id", "tag2Id"));

        boolean testResult = sut.isValid(thread);

        assertTrue(testResult);
    }

    /**
     * author - Charles Mettee
     */
    @Test
    public void updateThreads_throwsException_givenInvalidThread(){
        Threads thread = null;
        boolean testResult = false;

        try{
            sut.updateThreads(thread);
        } catch (Exception e){
            testResult = true;
        }

        assertTrue(testResult);
    }

    /**
     * author - Charles Mettee
     */
    @Test
    public void updateThreads_isSuccessful_givenValidThread(){
        Threads thread = new Threads("threadId", "subject", Arrays.asList("parentId"), "parentId",
                "description", 0, "2021-09-28T12:05:13.628", "ownerId", Arrays.asList("tag1Id", "tag2Id"));

        try{
            sut.updateThreads(thread);
        } catch (Exception e){

        }

        verify(mockThreadsRepo, times(1)).updateThreads(thread);


    }


}
