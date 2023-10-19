package com.safetripbackend.PlannerItinerarie.controller;
import com.safetripbackend.PlannerItinerarie.service*
@RestController
@RequestMapping("/api/Activities")

public class ActivitiesController {
    @Autowired
    private ActivityService activityService;
}