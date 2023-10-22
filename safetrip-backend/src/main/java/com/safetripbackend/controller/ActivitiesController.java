package com.safetripbackend.controller;
import com.safetripbackend.service.ActivityService;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/Activities")

public class ActivitiesController {


    private ActivityService activityService;
}