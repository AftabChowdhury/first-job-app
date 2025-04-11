package com.ac.firstjobapp.job;

import java.util.List;

public interface JobService {
    List<Job> findAll();
    Job getJobById(Long id);
    void createJob(Job job);
    boolean deleteJob(Long id);
    boolean updateJob(Long id, Job job);
}
