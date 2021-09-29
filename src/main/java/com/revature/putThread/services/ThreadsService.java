package com.revature.putThread.services;

import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.revature.putThread.models.Threads;
import com.revature.putThread.repositories.ThreadsRepo;

public class ThreadsService {

    private final ThreadsRepo threadsRepo;
    private final LambdaLogger lambdaLogger;

    public ThreadsService(ThreadsRepo threadsRepo, LambdaLogger lambdaLogger){
        this.threadsRepo = threadsRepo;
        this.lambdaLogger = lambdaLogger;
    }

    /**
     * @param threads - the thread being sent to the repo for updating in the to database
     * @throws Exception
     *
     * @author - Charles Mettee
     */
    public void updateThreads(Threads threads) throws Exception {
        if(!isValid(threads)){
            throw new Exception("An error occurred");
        }
        lambdaLogger.log("The provided thread is valid; Update successful.");
        threadsRepo.updateThreads(threads);
    }

    /**
     * @param threads - the thread being validated
     * @return - boolean value indicating whether or not the thread is valid
     *
     * @author - Charles Mettee
     */
    public boolean isValid(Threads threads){
        if(threads == null){
            lambdaLogger.log("Update rejected because the specified thread does not exist.");
            return false;
        }
        if(threads.getId() == null || threads.getId().trim().equals("")){
            lambdaLogger.log("Update rejected because the ID of the specified thread does not exist.");
            return false;
        }
        if(threads.getSubject() == null || threads.getSubject().trim().equals("")){
            lambdaLogger.log("Update rejected because the thread does not have a title.");
            return false;
        }
        if(threads.getAncestors() == null || threads.getAncestors().size() != 1) {
            lambdaLogger.log("Update rejected because the specified thread does not have exactly one ancestor.");
            return false;
        }
        if(threads.getParent() == null || threads.getParent().trim().equals("")){
            lambdaLogger.log("Update rejected because the specified thread does not belong to a subforum.");
            return false;
        }
        if(threads.getDescription() == null || threads.getDescription().trim().equals("")){
            lambdaLogger.log("Update rejected because the specified thread does not have a description.");
            return false;
        }
        if(threads.getChild_count() < 0){
            lambdaLogger.log("Update rejected because the specified thread contains an invalid child count.");
            return false;
        }
        if(threads.getDate_created() == null || threads.getDate_created().length() != 23){
            lambdaLogger.log("Update rejected because the specified thread contains an improperly formatted date.");
            return false;
        }
        if(threads.getOwner() == null || threads.getOwner().trim().equals("")){
            lambdaLogger.log("Update rejected because the specified thread does not have an owner.");
            return false;
        }
        return true;
    }


}
