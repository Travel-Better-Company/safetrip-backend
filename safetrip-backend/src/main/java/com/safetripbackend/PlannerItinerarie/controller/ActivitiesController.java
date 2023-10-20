package com.safetripbackend.PlannerItinerarie.controller;
import com.safetripbackend.PlannerItinerarie.service.ActivityService;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/Activities")

public class ActivitiesController {


    private ActivityService activityService;
}