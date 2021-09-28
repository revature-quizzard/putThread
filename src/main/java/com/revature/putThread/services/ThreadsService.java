package com.revature.postThread.services;

import com.revature.putThread.models.Threads;
import com.revature.putThread.repositories.ThreadsRepo;

public class ThreadsService {

    private final ThreadsRepo threadsRepo;

    public ThreadsService(ThreadsRepo threadsRepo){
        this.threadsRepo = threadsRepo;
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
            return false;
        }
        if(threads.getSubject() == null || threads.getSubject().trim().equals("")){
            return false;
        }
        if(threads.getAncestors() == null || threads.getAncestors().size() != 1) {
            return false;
        }
        if(threads.getParent() == null || threads.getParent().trim().equals("")){
            return false;
        }
        if(threads.getDescription() == null || threads.getDescription().trim().equals("")){
            return false;
        }
        if(threads.getOwner() == null || threads.getOwner().trim().equals("")){
            return false;
        }
        return true;
    }


}
