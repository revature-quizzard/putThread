package com.revature.putThread.services;

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

    @BeforeEach
    void setUp(){
        mockThreadsRepo = mock(ThreadsRepo.class);
        sut = new ThreadsService(mockThreadsRepo);
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
     * author -
     */
    @Test
    public void isValid_returnsFalse_givenAncestorsNullOrNotSizeOne(){


    }

    /**
     * author -
     */
    @Test
    public void isValid_returnsFalse_givenNullOrEmptyParent(){

    }

    /**
     * author -
     */
    @Test
    public void isValid_returnsFalse_givenNullOrEmptyDescription(){

    }

    /**
     * author -
     */
    @Test
    public void isValid_returnsFalse_givenChildCountLessThanZero(){


    }

    /**
     * author -
     */
    @Test
    public void isValid_returnsFalse_givenNullOrEmptyDateCreated(){


    }

    /**
     * author -
     */
    @Test
    public void isValid_returnsFalse_givenNullOrEmptyOwner(){


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
    public void addThreads_throwsException_givenInvalidThread(){
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
    public void addThreads_isSuccessful_givenValidThread(){


    }


}
